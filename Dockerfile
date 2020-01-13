FROM miteshsjdp/openjre8kms
MAINTAINER Sibasish <sibasish.mohanty@cred.club>

# Installs latest Chromium package.
RUN echo @edge http://nl.alpinelinux.org/alpine/edge/community >> /etc/apk/repositories \
    && echo @edge http://nl.alpinelinux.org/alpine/edge/main >> /etc/apk/repositories \
    && apk add --no-cache \
    chromium@edge \
    harfbuzz@edge \
    nss@edge \
    freetype@edge \
    ttf-freefont@edge \
    chromium-chromedriver \
    && rm -rf /var/cache/* \
    && mkdir /var/cache/apk

WORKDIR /build
ADD . /build/
VOLUME /tmp

WORKDIR /app
COPY build/libs/bootcamp-*.jar /app/app.jar
VOLUME /opt/config

EXPOSE 25050

CMD java $JAVA_OPTS -Djava.security.egd=file:/dev/urandom -Denv.name=dev -javaagent:/root/env/dd-java-agent.jar -jar /app/app.jar
