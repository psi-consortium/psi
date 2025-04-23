{{- define "mission-management.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{- end }}

{{- define "mission-management.fullname" -}}
{{- printf "%s-mission-management-backend" .Release.Name | trunc 63 | trimSuffix "-" }}
{{- end }}

{{- define "mission-template-management.fullname" -}}
{{- printf "%s-mission-template-management-backend" .Release.Name | trunc 63 | trimSuffix "-" }}
{{- end }}

{{- define "mission-management.selectorLabels" -}}
app.kubernetes.io/name: mission-management-backend
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}

{{- define "mission-template-management.selectorLabels" -}}
app.kubernetes.io/name: mission-template-management-backend
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}

{{- define "mission-management.labels" -}}
helm.sh/chart: {{ include "mission-management.chart" . }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{ include "mission-management.selectorLabels" . }}
{{- end }}

{{- define "mission-template-management.labels" -}}
helm.sh/chart: {{ include "mission-management.chart" . }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{ include "mission-template-management.selectorLabels" . }}
{{- end }}
