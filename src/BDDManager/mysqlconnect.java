package BDDManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import javax.swing.*;
import java.sql.*;

/**
 * @author Jerome
 * get user list from database
 */
public class mysqlconnect {
    Connection conn = null;

    // connect to the database
    public static Connection ConnectDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8");
            JOptionPane.showMessageDialog(null, "ConnectionEstablished");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    // get list of data users from database
    public static ObservableList<User> getDataUsers() {
        Connection conn = ConnectDb();
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from utilisateur");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //list.add(new Users(Integer.parseInt(rs.getString("ID_utilisateur")), Integer.parseInt(rs.getString("droit_acces")), rs.getString("nom_utilisateur"), rs.getString("prenom"), rs.getString("pseudo")));
            }
        } catch (Exception e) {

        }

        return list;
    }
}
