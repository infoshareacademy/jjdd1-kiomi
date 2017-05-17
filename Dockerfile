FROM jboss/wildfly

MAINTAINER "Arkadiusz Zileazny"

RUN /opt/jboss/wildfly/bin/add-user.sh admin admin123 --silent

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]