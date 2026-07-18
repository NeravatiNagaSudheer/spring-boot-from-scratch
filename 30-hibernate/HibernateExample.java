package com.example.hibernate;

import com.example.hibernate.entity.User;
import com.example.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateExample {

    public static void main(String[] args) {

        Session session =
                HibernateUtil
                        .getSessionFactory()
                        .openSession();

        Transaction transaction =
                session.beginTransaction();

        User user = new User();

        user.setName("Sudheer");
        user.setEmail("sudheer@gmail.com");

        session.persist(user);

        transaction.commit();

        session.close();

        HibernateUtil.shutdown();

        System.out.println("User saved successfully.");
    }
}
