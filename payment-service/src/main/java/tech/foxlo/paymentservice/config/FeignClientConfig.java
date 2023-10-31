package tech.foxlo.paymentservice.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "tech.foxlo.paymentservice.proxy")
public class FeignClientConfig {
}
