#!/bin/bash

##
# Copyright 2022 THE PSI CONSORTIUM
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
##


# ======================================================================================
# User's manual
# ======================================================================================
#
# This prototypic render pipeline renders one or many markdown documents to individual
# PDF files. It consists of multiple stages that handle the following tasks:
# - clone the source repository
# - check out a branch to be rendered
# - stitch the markdown tree to a single markdown file
# - report the chapter structure of the markdown file to a file or a database
# - check out another branch to compare the document to
# - stitch the markdown tree again to another markdown file
# - Compare both markdown files to a delta markdown
# - Convert all the stitched or compared markdown files to individual LaTeX files
# - Convert the LaTeX to PDF multiple times to populate TOC and BIB
# - Check the resulting file for errors
#
# Not all tasks and stages have to be performed.
# Due to the complexity of the process, it is split into individual "stages".
# Each stage has a dedicated shell script.
#
# All stages are controlled by variables.
# The variables are defined below.
#
# The idea of this file is provide a full-blown example of a markdown-to-PDF-pipeline
# for a project. Of course, everything can be adjusted or overwritten by projects.
#
# The script writes to a common log file in WORKINGDIR/rhod.log.
# Additionally, it tracks the progress (completed files) in WORKINGDIR/rhod.done.txt.
#
# Should the markdown documents contain PlantUML images, they are rendered to bitmaps.
# To speed up this process, it makes sense to cache the WORKINGDIR/plantuml-images.
#

# -e: Exit on error
# -o pipefail: Exit on command pipe failures
# Further information: https://vaneyckt.io/posts/safer_bash_scripts_with_set_euxo_pipefail/
set -eo pipefail

script_dir="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" &>/dev/null && pwd)"

# unshallow the repo to allow build of history
git fetch --unshallow || true

# When running in a Container, ususally a non-interactive shell is used.
# Therefore, we make sure the logon scripts are executed to have ruby, java etc on the path.
for f in /etc/profile.d/*; do source "$f" >/dev/null 2>&1 || true; done

CURRENT_PSI_VERSION=${CURRENT_PSI_VERSION:="unknown"}

# For the Docker workspace, we need an absolute path.
# We derive it from the current script's name.
# This allows to run the script from anywhere in the repository.
#CURRENT_FILE="${BASH_SOURCE[0]}"
#CURRENT_DIR=$( dirname -- "$CURRENT_FILE"; )
#LOCAL_REPO=${LOCAL_REPO:="$CURRENT_DIR/../.."}
#echo "relative repo root is: $LOCAL_REPO"
#LOCAL_REPO=$( readlink -f "$LOCAL_REPO" )
#echo "absolute repo root is: $LOCAL_REPO"
#WORKINGDIR="$LOCAL_REPO/.pdfout"
#mkdir -p $WORKINGDIR

# ======================================================================================
# Global settings
# ======================================================================================

# This is the folder where RUBY RHOD is installed.
# To allow local debugging, you can override this directory.
# In the container, this defaults to "/rhod".
# Set this e.g. to "/home/wsl/devops/rhod" for local execution (not in Container)!
RHOD_DIR=${RHOD_DIR:="/rhod"}

# install helper function.
# Note: console-writing helpers must not be called before WORKINGDIR is assgined
# because these helpers write to a log file!
source "$RHOD_DIR/src/shell/helpers.sh"

if [[ -z $RHOD_SHELL_HELPERS_INSTALLED ]]; then
    echo "FATAL: RHOD SHELL EXECUTION CONTEXT HAS NOT BEEN SET UP"
    exit 1
fi

# read RHOD version
RHOD_VERSION=$(cat $RHOD_DIR/VERSION.txt)
if [[ -z $RHOD_VERSION ]]; then
    RHOD_VERSION="unknown"
fi

# include the predefined RHOD "stages" into the pipeline
# Of course, you can redefine any of this stages in your project.
source "$RHOD_DIR/src/shell/stage_rhod.sh"
source "$RHOD_DIR/src/shell/stage_rename_final.sh"
source "$RHOD_DIR/src/shell/stage_pdflatex.sh"
source "$RHOD_DIR/src/shell/stage_checkpdf.sh"
source "$RHOD_DIR/src/shell/stage_before_pipeline.sh"
source "$RHOD_DIR/src/shell/stage_cleanup.sh"

# ======================================================================================
# Projects settings
# ======================================================================================

repo_working_dir="${script_dir}/../../.pdfout"
mkdir -p "${repo_working_dir}"

# This is the directory where the result artefacts are generated to
# Set this e.g. to "/home/wsl/dump" for local execution (not in Container)!
WORKINGDIR=${WORKINGDIR:="${repo_working_dir}"}
# This is the folder where the source document's files are stored
# In case the checkout/pull verbs are used, this should point to a git checkout!
# Set this e.g. to "/home/wsl/my-project" for local execution (not in Container)!
LOCAL_REPO=${LOCAL_REPO:="${script_dir}/../.."}
# In this folder, the files that belong to the LaTeX template should reside
LATEX_TEMPLATE_DIR=${LATEX_TEMPLATE_DIR:="${script_dir}/template"}
# This is the actual file name of the LaTeX template to be applied
LATEX_TEMPLATE=${LATEX_TEMPLATE:="psi_template.tex"}
# Title of the Table-Of-Contents chapter
TOCTITLE="Table Of Contents"
# Maximum level of TOC entries to be displayed. Pandoc supports up to 6 levels max!
TOCDEPTH=6
# If the pull verbs are used, the following settings provide access to
# the remote git repository to pull from
# This makes sense if you run the pipeline "as a service" and want to clone/pull foreign repos
#REMOTE_REPO_USER=${REMOTE_REPO_USER:="USER"}
#REMOTE_REPO_PASSWORD=${REMOTE_REPO_PASSWORD:="TOKEN"}
#REMOTE_REPO=${REMOTE_REPO:="https://github.com/psi-consortium/psi.git"}

# If the report_db verb is given, this configures the target database
#DB_SERVER=${DB_SERVER:="server"}
#DB_USER=${DB_USER:="user"}
#DB_NAME=${DB_NAME:="db"}
#DB_PASSWORD=${DB_PASSWORD:="secret"}

# In case, draft documents are rendered, specify a review link here.
# Review links are prepended to the markdown source path.
# The resulting links are inserted into the PDF to e.g. open the web ide of
# GitLab to direct-edit the source markdown.
# Set this to and empty string to not have review links in the resulting document.
REVIEW_LINK=${REVIEW_LINK:="https://github.com/psi-consortium/psi"}
# set to 1 to render a draft document
DRAFT=${DRAFT:=1}
# The checkout to be rendered - in case the checkout verb is used
CHECKOUT=${CHECKOUT:="refs/heads/main"}
# the checkout to compare to - in case the compare verb is given
# Options are a git head (e.g. "refs/tags/MS1") or a git tag (e.g. "refs/heads/MS1-2002-06-24")
CMP_CHECKOUT=${CMP_CHECKOUT:="refs/tags/V1"}
# Additional debbuging options that can be passed to RHOD
#    --missing-pages
#    --skip-change-log
#    --verbose
#    --very-verbose
#    --skip-change-log
#    --save-ast
#    --fail-on-compare-failures
DEFAULT_OPTIONS=${DEFAULT_OPTIONS:="--syntax-highlight --fail-on-compare-failures --verbose"}
# What should be done? Multiple verbs can be given!
# Possible options are:
#    clone      - git clone from REMOTE_REPO into LOCAL_REPO
#    pull       - git pull from REMOTE_REPO into LOCAL_REPO
#    checkout   - checkout CHECKOUT in LOCAL_REPO
#    stitch     - stitch the start document into WORKINGDIR - see below
#    compare    - stitch (see before), checkout CMP_CHECKOUT, stitch again and compare the documents
#    report_db  - report the document structure into the DB
#    report_csv - report the document structure into a local CSV file
#    filedelta  - checkout CMP_CHECKOUT and find differences of markdown files to the current checkout
VERBS=${VERBS:="stitch"}
# target format of embedded images (PlantUML, Mermaid...)
# The default is "png" but you can set it to "pdf" to generate vector images instead of raster.
EMBEDDED_IMAGE_FILE_TYPE=${EMBEDDED_IMAGE_FILE_TYPE:="pdf"}

# ======================================================================================
# Stage definitions - stages can be suppressed to speed up debugging
# Find a detailed description of what every stage does in the stage scripts in src/shell
# For a regular run, all stages should be activated.
# ======================================================================================

STAGE_RHOD=1
STAGE_PDFLATEX=1
STAGE_CHECKPDF=1
STAGE_RENAMEFINAL=0
STAGE_CLEANUP=1

# ======================================================================================
# Definition of documents
# -doc_path should point to the directory where the start document (index.md) resides
# -doc_abbr gives a unique, short title for the generated document in WORKINGDIR
# -cmp_strt in case a document has to be compared to a different path (e.g. because
#           it has been renamed), the path within CMP_CHECKOUT can be supplied here.
#           If omitted, doc_path is reused.
# -doc_opts Additional options to be supplied to RUBY RHOD, e.g. "--no-compare" to
#           skip the comparison for individual documents
# -doc_skip Can be set to 1 to skip the rendering of a particular document
# ======================================================================================
unset doc_path
unset doc_abbr
unset cmp_strt
unset doc_opts
unset doc_skip
declare -A doc_path
declare -A doc_abbr
declare -A cmp_strt
declare -A doc_opts
declare -A doc_skip

global_skip=0

doc_path["PSI-DL"]=doc/index.md
doc_abbr["PSI-DL"]=PSI-DL
doc_skip["PSI-DL"]=$global_skip

doc_path["PSI-ADR"]=doc/PSI/PSI-ADR/index.md
doc_abbr["PSI-ADR"]=PSI-ADR
doc_skip["PSI-ADR"]=$global_skip

doc_path["PSI-CST"]=doc/PSI/PSI-CST/index.md
doc_abbr["PSI-CST"]=PSI-CST
doc_skip["PSI-CST"]=$global_skip

doc_path["PSI-DAC"]=doc/PSI/PSI-DAC/index.md
doc_abbr["PSI-DAC"]=PSI-DAC
doc_opts["PSI-DAC"]=--no-compare #new in MS11
doc_skip["PSI-DAC"]=$global_skip

doc_path["PSI-GID"]=doc/PSI/PSI-GID/index.md
doc_abbr["PSI-GID"]=PSI-GID
doc_skip["PSI-GID"]=$global_skip

doc_path["PSI-ICD"]=doc/PSI/PSI-ICD/index.md
doc_abbr["PSI-ICD"]=PSI-ICD
doc_skip["PSI-ICD"]=$global_skip

doc_path["PSI-READFIRST"]=doc/PSI/PSI-READFIRST/index.md
doc_abbr["PSI-READFIRST"]=PSI-READFIRST
doc_skip["PSI-READFIRST"]=$global_skip

doc_path["PSI-REQ"]=doc/PSI/PSI-REQ/index.md
doc_abbr["PSI-REQ"]=PSI-REQ
doc_skip["PSI-REQ"]=$global_skip

doc_path["PSI-SLF"]=doc/PSI/PSI-SLF/index.md
doc_abbr["PSI-SLF"]=PSI-SLF
doc_skip["PSI-SLF"]=$global_skip

doc_path["PSI-TAD"]=doc/PSI/PSI-TAD/index.md
doc_abbr["PSI-TAD"]=PSI-TAD
doc_skip["PSI-TAD"]=$global_skip

doc_path["PSI-TOD"]=doc/PSI/PSI-TOD/index.md
doc_abbr["PSI-TOD"]=PSI-TOD
doc_skip["PSI-TOD"]=$global_skip

# ======================================================================================
# Business logic starts here
# The following loop renders all of the previously defined documents
# by calling the individual "stages".
# ======================================================================================

stage_before_pipeline

number_of_docs="${#doc_path[@]}"
doc_counter=0

for d in "${!doc_path[@]}"
do
  doc_counter=$((doc_counter+1))
  SKIP=${doc_skip[$d]}
  SUBFOLDER=${doc_path[$d]}
  CMP_START=${cmp_strt[$d]}
  DOC_ABBREV=${doc_abbr[$d]}
  RESULTNAME=${doc_abbr[$d]}
  OPTIONS="$DEFAULT_OPTIONS ${doc_opts[$d]}"
  if [ $SKIP -ne 1 ] ;then
    step "Document $doc_counter of $number_of_docs - $SUBFOLDER => $RESULTNAME"
    [ $STAGE_RHOD -ne 0 ] && stage_rhod "$SUBFOLDER"
    [ $STAGE_PDFLATEX -ne 0 ] && stage_pdflatex
    [ $STAGE_CHECKPDF -ne 0 ] && stage_checkpdf
    [ $STAGE_RENAMEFINAL -ne 0 ] && stage_rename_final
    [ $STAGE_CLEANUP -ne 0 ] && stage_cleanup
    stepDone "Document $doc_counter of $number_of_docs - $SUBFOLDER => $RESULTNAME"
    echo "$SUBFOLDER" >> "$WORKINGDIR"/rhod.done.txt
  fi
done
