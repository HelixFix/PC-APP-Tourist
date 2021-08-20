package BDDManager;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class My_CNX {

    public  static Connection getConnection() throws SQLException {

        Connection cnx = null;

        MysqlDataSource dataSource = new MysqlDataSource();

        String servername = "localhost";
        dataSource.setServerName(servername);
        String username = "root";
        dataSource.setUser(username);
        String password = "";
        dataSource.setPassword(password);
        String dbname = "voyage?characterEncoding=utf8";
        dataSource.setDatabaseName(dbname);
        int portnumber = 3306;
        dataSource.setPortNumber(portnumber);

        try {
            cnx = dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(" Get Connection -> " + My_CNX.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cnx;
    }
}
