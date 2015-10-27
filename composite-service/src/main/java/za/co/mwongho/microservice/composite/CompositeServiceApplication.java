package za.co.mwongho.microservice.composite;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class CompositeServiceApplication {
    public static void main(String[] args) {
    	new SpringApplicationBuilder(CompositeServiceApplication.class).web(true).run(args);
    }
}
