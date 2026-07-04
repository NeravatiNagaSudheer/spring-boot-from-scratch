package com.example.logging.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggingExample {

    private static final Logger logger =
            LoggerFactory.getLogger(LoggingExample.class);

    public void fetchUsers() {

        logger.trace("TRACE: Entering fetchUsers() method");

        logger.debug("DEBUG: Fetching user data from database");

        logger.info("INFO: Fetch Users API Called");

        logger.warn("WARN: User list is getting large");

        try {

            int number = 10 / 0;

        } catch (Exception e) {

            logger.error("ERROR: Exception occurred while fetching users", e);

        }

    }

}
