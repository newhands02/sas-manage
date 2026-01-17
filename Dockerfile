# 使用官方的OpenJDK 1.8镜像作为基础镜像
FROM openjdk:27-ea-jdk

# 设置工作目录
WORKDIR /xyj

# 将构建好的JAR文件复制到镜像中
COPY target/sas-project-1.0.jar /xyj/sas-project-1.0.jar

# 暴露应用的端口
EXPOSE 8087

# 运行应用
CMD ["java", "-jar", "sas-project-1.0.jar"]
