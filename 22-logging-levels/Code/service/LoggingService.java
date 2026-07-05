package com.example.logging.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggingService {

    private static final Logger logger =
            LoggerFactory.getLogger(LoggingService.class);

    public void fetchUsers() {

        logger.trace("Entering fetchUsers()");

        logger.debug("Fetching users from database");

        logger.info("Users retrieved successfully");

        logger.warn("Database response is slower than expected");

        try {

            int number = 10 / 0;

        } catch (Exception ex) {

            logger.error("Unexpected error occurred", ex);

        }
    }
}
