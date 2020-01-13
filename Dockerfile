FROM miteshsjdp/openjre8kms
MAINTAINER Sibasish <sibasish.mohanty@dreamplug.in>

WORKDIR /app
COPY build/libs/bootcamp-*.jar /app/app.jar
COPY src/main/resources/env_config.yml /app/app.yml

EXPOSE 25050

ENV CONFIG_FILE_PATH /app/app.yml

CMD java -Djava.security.egd=file:/dev/urandom $JAVA_OPTS -javaagent:/root/env/dd-java-agent.jar -jar /app/app.jar
