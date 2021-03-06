package cn.eleven.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 启动
 * @date: 2019-11-27 14:32
 * @author: 十一
 */
@SpringBootApplication
public class Application {
    /**
     * 日志集合
     */
    public static List<String> LOG_PATH_ARR = new ArrayList<>(16);

    public static void main(String[] args) {
        String logPath = System.getProperty("logPath");
        if (logPath == null || "".equals(logPath)) {
            throw  new RuntimeException("请输入日志路径");
        }
        LOG_PATH_ARR = Arrays.asList(logPath.split(";"));
        String ip = System.getProperty("ip");
        if (ip == null || "".equals(ip)) {
            // 默认
            ip = "127.0.0.1";
            System.setProperty("ip",ip);
        }

        System.out.println("========================================================================");
        System.out.println(String.format("读取日志路径： 【 %s 】" , logPath));
        System.out.println(String.format("页面访问ip：  【 %s 】" , ip));
        System.out.println("========================================================================");
        SpringApplication.run(Application.class,args);

    }



    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
