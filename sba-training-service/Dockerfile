FROM openjdk:8-jdk-alpine
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
  && echo 'Asia/Shanghai' >/etc/timezone
ENV LANG C.UTF-8
ADD target/sba-training-service-1.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]