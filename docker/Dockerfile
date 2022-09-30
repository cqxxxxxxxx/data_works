FROM openjdk:8-jdk-alpine
VOLUME /tmp
WORKDIR /data
ADD *.jar app.jar
#ADD conf conf
ADD docker-entrypoint.sh docker-entrypoint.sh
#ADD conf/jfxrt.jar /usr/lib/jvm/java-1.8-openjdk/jre/lib/ext/jfxrt.jar
#RUN echo 'http://mirrors.aliyun.com/alpine/v3.8/main/' > /etc/apk/repositories \
#&& echo 'http://mirrors.aliyun.com/alpine/v3.8/community/' >> /etc/apk/repositories \
#&& apk update \
#&& apk add --update font-adobe-100dpi ttf-dejavu fontconfig
ENTRYPOINT ["sh", "docker-entrypoint.sh"]