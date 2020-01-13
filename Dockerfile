FROM miteshsjdp/openjre8kms
MAINTAINER Sibasish <sibasish.mohanty@cred.club>

WORKDIR /build
ADD . /build/
VOLUME /tmp

WORKDIR /app
COPY build/libs/bootcamp-*.jar /app/app.jar
VOLUME /opt/config

EXPOSE 25050

CMD java $JAVA_OPTS -Djava.security.egd=file:/dev/urandom -Denv.name=dev -javaagent:/root/env/dd-java-agent.jar -jar /app/app.jar
