package lotteryApp1.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;





public class DB {
    private final String DB_URL = "jdbc:mysql://localhost:8889/LotteryApp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String DB_User = "root";
    private final String DB_PWD = "root";
    protected Connection conn;

    public DB() {
        this.openConnection();
    }
    
    /**
     *
     */
    @Override
    protected void finalize()  
    {
        this.closeConnection();
        try {
            super.finalize();
        } catch (Throwable ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void openConnection() {
        if (conn != null) {
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_User, DB_PWD);
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        // TODO Auto-generated catch block
        System.out.println("Connection Open");

    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }
}
