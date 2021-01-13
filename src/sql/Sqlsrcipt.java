package sql;

import org.sqlite.SQLiteConnection;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class Sqlsrcipt {

    private SQLiteConnection c = null;
    private Statement stmt = null;
    private ArrayList<Questions> questions;
    private Questions qr;

    public Sqlsrcipt() {
        try {
            questions = new ArrayList<>();

            c = (SQLiteConnection) DriverManager.getConnection("jdbc:sqlite:db/fragen-spiel-bauer.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM FRAGEN;");

            while (rs.next()) {
                int id = rs.getInt("id");
                String fragentext = rs.getString("fragentext");
                String loesungstext = rs.getString("loesungstext");
                int punkte = rs.getInt("punkte");

                Questions q = new Questions(id, fragentext, loesungstext, punkte);
                questions.add(q);

//                System.out.println( "ID = " + id );
//                System.out.println( "FRAGENTEXT = " + fragentext );
//                System.out.println( "LÖSUNGSTEXT = " + loesungstext );
//                System.out.println( "PUNKTE = " + punkte );
//                System.out.println("-------------------------------------------");
//                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Questions getRandomq() {
        Random in = new Random();
        int number = in.nextInt(questions.size()) - 1;
//        System.out.println(number);
        qr = questions.get(number);
        return qr;
    }

    public boolean nextqestion() {
        boolean status = false;
        getRandomq();

        String in = JOptionPane.showInputDialog(null, "F: " + qr.getFrage() + " A: " + qr.getAntwort(), null);
        if (in.equals(qr.getAntwort())) {
            status = true;
        }

        return status;
    }


    public Questions getQuestion() {
        return qr;
    }
}
