package com.example.logging.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger logger =
            LoggerFactory.getLogger(UserService.class);

    public void getUsers() {

        logger.info("Fetching users");

        logger.debug("Calling repository layer");

        logger.warn("Response time is slightly higher");

        try {

            int number = 10 / 0;

        } catch (Exception ex) {

            logger.error("Exception while fetching users", ex);

        }

    }

}
