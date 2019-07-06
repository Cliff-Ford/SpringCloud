package cliff.ford;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"service"})
@ComponentScan(basePackages = {"service", "cliff.ford"})
public class DeptConsumer_Feign_App {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer_Feign_App.class, args);
    }
}
