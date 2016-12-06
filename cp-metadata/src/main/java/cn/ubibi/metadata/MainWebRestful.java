package cn.ubibi.metadata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class MainWebRestful extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer{
    public static void main(String[] args) {
        SpringApplication.run(MainWebRestful.class, args);
    }


    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(9001);
//        container.setContextPath("/api/v1");
    }
}
