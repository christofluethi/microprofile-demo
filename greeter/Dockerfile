FROM openjdk:11.0-jre

# Timezone
RUN ln -sf /usr/share/zoneinfo/Europe/Zurich /etc/localtime \
    && echo "Europe/Zurich" > /etc/timezone

# Thorntail
COPY target/greeterservice-thorntail.jar /opt/thorntail.jar

EXPOSE 8080

CMD ["sh", "-c", "java $JVM_ARGS -Djava.net.preferIPv4Stack=true -Djava.net.preferIPv4Addresses=true -jar /opt/thorntail.jar $JAVA_ARGS"]
