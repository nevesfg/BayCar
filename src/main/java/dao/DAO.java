package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Manager;

public class DAO{

    /** The driver. */
    private String driver = "com.mysql.cj.jdbc.Driver";

    /** The url. */
    private String url = "jdbc:mysql://127.0.0.1:3306/baycars?useTimezone=true&serverTimezone=UTC";

    /** The user. */
    private String user = "root";

    /** The password. */
    private String password = "";
//    private String password = "root";

    public Connection conectar() {
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            return con;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void verify(Manager manager){
        String read = "SELECT * FROM manager WHERE email = ? AND password = ?;";
        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(read);
            pst.setString(1, manager.getEmail());
            pst.setString(2, manager.getPassword());
            ResultSet rst = pst.executeQuery();
            if(rst.next()){
               manager.setId(rst.getString("id"));
               manager.setName(rst.getString("name"));
               manager.setEmail(rst.getString("email"));
               manager.setPassword(rst.getString("password"));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}