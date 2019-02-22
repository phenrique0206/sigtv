FROM daggerok/jboss:eap-7.0

ARG env=dev
ENV env ${env}

COPY --chown=jboss-eap-7.0:jboss-eap-7.0 . ./sigtv
WORKDIR sigtv

RUN ./mvnw clean package -P $env;

RUN cp ./jboss-eap-deploy/war/sigtv.war ${JBOSS_HOME}/standalone/deployments/ \
 && mkdir -p ${JBOSS_HOME}/modules/org/postgresql/main \
 && cp ./jboss-eap-deploy/postgresql-42.2.5.jar ${JBOSS_HOME}/modules/org/postgresql/main \
 && cp ./jboss-eap-deploy/module.xml ${JBOSS_HOME}/modules/org/postgresql/main/module.xml \
 && cp ./jboss-eap-deploy/standalone.xml ${JBOSS_HOME}/standalone/configuration/standalone.xml

