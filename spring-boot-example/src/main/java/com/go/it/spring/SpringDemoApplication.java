package com.go.it.spring;

import com.go.it.spring.configuration.ContextFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SpringDemoApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(SpringDemoApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .contextFactory(new ContextFactory())
                .build()
                .run(args);
    }
}
