# Dockerfile for a pre-build statically linked go binary
FROM alpine:3.21
# FROM scratch

COPY ./mission-management ./config.yml ./internal/api/PSID002-MissionManagement_v2.oas.json ./app/

EXPOSE 7000

# Run the go binary
ENV CONFIG_PATH=/app/config.yml
ENV APP_OPENAPI_DEFINITION=/app/PSID002-MissionManagement_v2.oas.json
ENV GIN_MODE=release
CMD ["/app/mission-management"]
