package com.albertsons.application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import soya.framework.bean.ClassPathTree;
import soya.framework.reflect.ClassPath;

import java.io.File;

@Configuration
public class ServiceLocator {
    private static ServiceLocator me;

    private ApplicationContext applicationContext;

    private File workspace;

    protected ServiceLocator(@Autowired ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.workspace = new File(applicationContext.getEnvironment().getProperty("workspace.home"));
        me = this;
    }

    public static ServiceLocator getInstance() {
        return me;
    }

    public File getWorkspace() {
        return workspace;
    }

    @Bean
    public ClassPathTree classPathTree() {
        return new ClassPathTree();
    }
}
