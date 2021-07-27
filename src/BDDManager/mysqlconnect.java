package BDDManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Users;

import javax.swing.*;
import java.sql.*;

/**
 * @author Jerome
 */
public class mysqlconnect {
    Connection conn = null;

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

    public static ObservableList<Users> getDataUsers() {
        Connection conn = ConnectDb();
        ObservableList<Users> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from utilisateur");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Users(Integer.parseInt(rs.getString("ID_utilisateur")), Integer.parseInt(rs.getString("droit_acces")), rs.getString("nom_utilisateur")));
            }
        } catch (Exception e) {

        }

        return list;
    }
}
