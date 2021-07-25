# 打包构建方式

执行命令：./gradlew app:clean app:bootJar

# 生产环境运行
java -jar -Dspring.profiles.active=prod BdpOne-1.0.0.jar > msg-1.0.0.log 2>&1