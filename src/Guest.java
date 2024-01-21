import java.sql.SQLException;

public class Guest extends Person{

    /**
     * Guest is used to initialize
     *
     * @param firstName is used to initialize firstName
     * @param lastName  is used to initialize lastName
     * @param phoneNo1  is used to initialize phoneNo1
     */
    public Guest(String firstName, String lastName, String phoneNo1, String databaseName
            ,String password) {
        super(firstName, lastName, phoneNo1, "0", databaseName,"", password);
    }
    public void add() {

        try {
            connect();
            pst = conn.prepareStatement("insert into Guest (first_name,last_name,tellNo)values(?,?,?)");
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3, phoneNo1);
            System.out.println("Added");

            pst.executeUpdate();
            conn.close();
        } catch (SQLException e1) {

            e1.printStackTrace();
        }
    }
}

