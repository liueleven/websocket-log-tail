# 介绍
通过 websocket 读取指定路径的日志，日志路径在启动的时候通过 -D 动态传入，默认端口是 8080

# 启动

## 打包编译
mvn clean package -Dmaven.test.skip=true

## 启动
支持配置多个日志路径，日志之间用分号 “;” 隔开，例如：
java -DlogPath=/mnt/logs/****/log-info.log;/app/log-error.log  -jar wallet-log-tail-1.0.jar


或者指定端口启动：java -DlogPath=/mnt/logs/****/log-info.log -Dip=127.0.0.1 -jar wallet-log-tail-1.0.jar --server.port=8081

在 idea 中启动，需要在 vm options 中配置 -D 参数，例如：-DlogPath=/mnt/logs/zzex-wallet/2019-11-29/log-info.log

## 访问页面
在浏览器输入：http://ip+port/wallet/log