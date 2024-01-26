import java.sql.SQLException;
import java.sql.Time;

public class Guest extends Person{

    /**
     * Guest is used to initialize
     *
     * @param firstName is used to initialize firstName
     * @param lastName  is used to initialize lastName
     */
    public Guest(String firstName, String lastName, String databaseName
            ,String password) {
        super(firstName, lastName, null, null, databaseName,null, password);
    }
    public void add() {
        try {
            connect();
            pst = conn.prepareStatement("insert into Guest (first_name,last_name) values(?,?)");
            pst.setString(1, firstName);
            pst.setString(2, lastName);

            pst.executeUpdate();
            System.out.println("Added to Guests Successfully");
            conn.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
            System.out.println("error adding to guests");
        }
    }
}

