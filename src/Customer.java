import java.sql.SQLException;

public class Customer extends Person{

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
            , String pwd,String password) {
        super(firstName, lastName, phoneNo1, phoneNo2, databaseName, "", password);
        this.pwd = pwd;
    }

    public void add(String tableName) {

        try {
            pst = conn.prepareStatement("insert into " + tableName + "(first_name,last_name,password ,tellNo1, tellNo2)values(?,?,?,?,?)");
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(4, phoneNo1);
            pst.setString(5, phoneNo2);
            pst.setString(3, pwd);
            System.out.println("Added");

            pst.executeUpdate();
        } catch (SQLException e1) {

            e1.printStackTrace();
        }
    }
}
