FROM openjdk:11.0-jre

# Timezone
RUN ln -sf /usr/share/zoneinfo/Europe/Zurich /etc/localtime \
    && echo "Europe/Zurich" > /etc/timezone

# Thorntail
COPY target/infoservice-thorntail.jar /opt/infoservice-thorntail.jar

EXPOSE 8080

CMD ["sh", "-c", "java -Djava.net.preferIPv4Stack=true -Djava.net.preferIPv4Addresses=true -jar /opt/infoservice-thorntail.jar"]
