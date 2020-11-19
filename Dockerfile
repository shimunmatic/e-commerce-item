FROM openjdk:15-jdk
MAINTAINER Shimun Matic <shimun.matic@gmail.com>

ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/ecommerce/app.jar

ENTRYPOINT ["java","-jar", "--enable-preview","/usr/share/ecommerce/app.jar"]