package com.johnie.johnieserver;

import com.johnie.johnieframework.jpa.repository.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.johnie.johnieframework", "com.johnie.johniesystem"})
@EnableJpaRepositories(
        repositoryBaseClass = BaseRepositoryImpl.class,
        basePackages = {"com.johnie.**.repository"})
@EntityScan({"com.johnie.**.entity"})
public class JohnieServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(JohnieServerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JohnieServerApplication.class);
    }
}
