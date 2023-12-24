package top.unii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@SpringBootApplication
@RestController
public class AppRun {
    
    
    public static void main(String[] args) {
        SpringApplication.run(AppRun.class, args);
    }
    
    
    @GetMapping("/sayHi")
    public String sayHi(String name){
        return "Hi:" + name;
    }
    
}




























































