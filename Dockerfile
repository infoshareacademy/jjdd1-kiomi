FROM jboss/wildfly:latest

MAINTAINER "KIOMI"

COPY target/ROOT.war /opt/jboss/wildfly/standalone/deployments/ROOT.war

RUN wildfly/bin/add-user.sh admin admin --silent

EXPOSE 8710

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

