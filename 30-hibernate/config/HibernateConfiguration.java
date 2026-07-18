package com.example.hibernate.config;

import org.hibernate.cfg.Configuration;

public class HibernateConfiguration {

    public static Configuration getConfiguration() {

        Configuration configuration = new Configuration();

        configuration.configure();

        return configuration;
    }
}
