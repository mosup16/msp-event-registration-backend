package com.example.mspeventregistration.config;

import com.example.mspeventregistration.event.Event;
import com.example.mspeventregistration.event.EventProjection;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(
            RepositoryRestConfiguration repositoryRestConfiguration, CorsRegistry cors) {
        repositoryRestConfiguration.getProjectionConfiguration()
                .addProjection(EventProjection.class);
    }
}