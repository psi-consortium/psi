FROM alpine:3.17

RUN echo 'http://dl-cdn.alpinelinux.org/alpine/v3.9/main' >> /etc/apk/repositories
RUN echo 'http://dl-cdn.alpinelinux.org/alpine/v3.9/community' >> /etc/apk/repositories
RUN apk update && apk add openjdk17-jre-headless supervisor mongodb yarn

COPY --chmod=775 ./source/docker/docker-entrypoint.sh /
COPY --chmod=775 ./source/docker/10-init-mongodb.sh /docker-entrypoint.d/10-init-mongodb.sh
COPY ./source/docker/supervisord/* /etc/supervisor.d/
COPY ./source/psid-mockup/build/libs/psid-mockup.jar /opt/psi/psid-mockup.jar
COPY ./aiv/testing/aitf/testdata/ /opt/psi/testdata/

RUN cd /opt/ && yarn add mongo-express@1.0.0
ENV ME_CONFIG_MONGODB_ENABLE_ADMIN="true" \
    ME_CONFIG_MONGODB_ADMINPASSWORD="admin" \
    VCAP_APP_HOST="0.0.0.0"

VOLUME /data/db
ENTRYPOINT ["/docker-entrypoint.sh"]
CMD ["supervisord", "-n"]
