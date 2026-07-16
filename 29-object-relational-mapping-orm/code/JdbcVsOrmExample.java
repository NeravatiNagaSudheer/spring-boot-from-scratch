package com.example.orm;

public class JdbcVsOrmExample {

    public static void main(String[] args) {

        System.out.println("=============== JDBC ===============");

        System.out.println("""
                Connection connection =
                    DriverManager.getConnection(...);

                PreparedStatement ps =
                    connection.prepareStatement(
                    "INSERT INTO users(name,email) VALUES(?, ?)");

                ps.setString(1,"Sudheer");
                ps.setString(2,"sudheer@gmail.com");

                ps.executeUpdate();

                ps.close();
                connection.close();
                """);

        System.out.println();

        System.out.println("=============== ORM ===============");

        System.out.println("""
                User user = new User();

                user.setName("Sudheer");
                user.setEmail("sudheer@gmail.com");

                repository.save(user);
                """);

        System.out.println();

        System.out.println("Notice how ORM eliminates boilerplate JDBC code.");

    }

}
