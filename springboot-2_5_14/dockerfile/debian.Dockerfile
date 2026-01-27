# FROM openjdk:8u322-jdk-bullseye
FROM registry.cn-hangzhou.aliyuncs.com/guanglian_docker_prod/openjdk:8u322-jdk-bullseye

MAINTAINER "Mr.Yan"
ENV LANG="C.UTF-8"
ENV LC_ALL="C.UTF-8"
ENV TZ=Asia/Shanghai

# 设置时区 + 安装字体（DejaVu Font）
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone \
    && apt-get update \
    && apt-get install -y --no-install-recommends fonts-dejavu fontconfig \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /data/app

COPY target/st-remit-console.jar /data/app/st-remit-console-8000.jar
#COPY target/classes/skywalking /data/app/agent

ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-Dsun.jnu.encoding=UTF-8", "-jar","/data/app/st-remit-console-8000.jar"]
# CMD ["--spring.profiles.active=prod"]

EXPOSE 8000
