import java.nio.channels.ScatteringByteChannel;
import java.sql.*;

public class Customer extends Person {

    String pwd;

    /**
     * Guest is used to initialize
     *
     * @param firstName is used to initialize firstName
     * @param lastName  is used to initialize lastName
     * @param phoneNo1  is used to initialize phoneNo1
     * @param phoneNo2  is used to initialize phoneNo2
     */
    public Customer(String firstName, String lastName, String phoneNo1, String phoneNo2, String databaseName
            , String pwd, String password,String sex) {
        super(firstName, lastName, phoneNo1, phoneNo2, databaseName, sex, password);
        this.pwd = pwd;
    }

    public static ResultSet getCustomer(String fName, String lName, String password, String dataBaseName, String passWord) throws SQLException {
        String sql = "SELECT * FROM customer WHERE first_name = ? AND last_name = ? AND password = ?";


        try {
            Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost/" + dataBaseName, "root",
                    passWord);
            PreparedStatement pst = conn1.prepareStatement(sql);
            pst.setString(1, fName);
            pst.setString(2, lName);
            pst.setString(3, password);

            ResultSet result = pst.executeQuery();
            System.out.println("successfully got a result set from customer");
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("can't get a result set from customer");
        }
        return null;
    }

    public void add() {

        try {
            connect();
            pst = conn.prepareStatement("insert into customer(first_name,last_name,password ,tellNo1, tellNo2, sex)" +
                    "values(?,?,?,?,?,?)");
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(4, phoneNo1);
            pst.setString(5, phoneNo2);
            pst.setString(3, pwd);
            pst.setString(6, sex);

            pst.executeUpdate();
            System.out.println("Added to customer table successfully");
            conn.close();
        } catch (SQLException e1) {
            System.out.println("can't add to customer table successfully");
            e1.printStackTrace();
        }
    }



    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwd() {
        return pwd;
    }
}
