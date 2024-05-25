
package sekolahproject.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Koneksi {
    
    public static Connection getConnection() {
        Connection connection = null;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/db_sekolah";
        String user = "root";
        String password = "";
        if (connection == null) {
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException | SQLException error) {
                System.err.println("Error at Koneksi-getConnection, details : "+error.toString());
                JOptionPane.showMessageDialog(null, "Error at Koneksi-getConnection, details : "+error.toString());
                System.exit(0);
        }
        } return connection;
    }
    
}
