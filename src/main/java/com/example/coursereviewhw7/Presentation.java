package com.example.coursereviewhw7;
import java.util.Scanner;
import java.sql.*;
import java.sql.Connection;

public class Presentation {
    
    Connection c;


    Statement s;

    {
        try {
            s = c.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
