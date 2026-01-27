FROM eclipse-temurin:21-jdk-jammy

ENV LANG=C.UTF-8
ENV LC_ALL=C.UTF-8
ENV TZ=Asia/Shanghai

# 仅设置时区（不使用 apt）
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime \
    && echo $TZ > /etc/timezone

## 设置时区 + 安装字体
# RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime \
#     && echo $TZ > /etc/timezone \
#     && apt-get update \
#     && apt-get install -y --no-install-recommends fonts-dejavu fontconfig \
#     && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY target/springboot4-demo.jar /app/springboot4-demo.jar

ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-Dsun.jnu.encoding=UTF-8", "-jar", "/app/springboot4-demo.jar"]
CMD ["--spring.profiles.active=prod"]

EXPOSE 58080
