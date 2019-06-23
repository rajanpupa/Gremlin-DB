package com.rajan.graph.gremlin.config;

import com.microsoft.spring.data.gremlin.common.GremlinConfiguration;
import com.microsoft.spring.data.gremlin.config.AbstractGremlinConfiguration;
import com.microsoft.spring.data.gremlin.repository.config.EnableGremlinRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableGremlinRepositories(basePackages = "com.rajan.graph.gremlin.repository")
@EnableConfigurationProperties(GremlinConfiguration.class)
@PropertySource("classpath:application.yml")
public class OTAConfiguration  extends AbstractGremlinConfiguration {

    @Autowired
    private GremlinConfiguration configuration;

    @Override
    public GremlinConfiguration getGremlinConfiguration() {
        return configuration;
    }

}
