# 介绍
通过 websocket 读取指定路径的日志，日志路径在启动的时候通过 -D 动态传入，默认端口是 8080

# 启动
## 打包编译
mvn clean package -Dmaven.test.skip=true

## 启动
java -DlogPath=/mnt/logs/****/log-info.log  -jar wallet-log-tail-1.0.jar

或者指定端口启动：java -DlogPath=/mnt/logs/****/log-info.log  -jar wallet-log-tail-1.0.jar --server.port=8081

## 访问页面
在浏览器输入：http://ip+port/wallet/log