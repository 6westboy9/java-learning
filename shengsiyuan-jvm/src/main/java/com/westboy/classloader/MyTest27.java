package com.westboy.classloader;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyTest27 {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytestdb", "username", "password");


        // Class<Cat> catClass = (Class<Cat>) Class.forName("com.westboy.classloader.Cat", false, MyTest27.class.getClassLoader());
        // Cat cat = catClass.newInstance();
        // System.out.println(cat);
    }
}
