{{- define "mission-management-frontend.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{- end }}

{{- define "mission-management-frontend.fullname" -}}
{{- printf "%s-mission-management-frontend" .Release.Name | trunc 63 | trimSuffix "-" }}
{{- end }}

{{- define "mission-management-frontend.selectorLabels" -}}
app.kubernetes.io/name: mission-management-frontend
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}

{{- define "mission-management-frontend.labels" -}}
helm.sh/chart: {{ include "mission-management-frontend.chart" . }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{ include "mission-management-frontend.selectorLabels" . }}
{{- end }}
