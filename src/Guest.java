import com.mysql.cj.protocol.Resultset;

import java.sql.ResultSet;
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

            connect();
            try {
                ResultSet resultSet = giveQuery("select * from guest where first_name = '" + firstName + "' and " +
                        "last_name ='" + lastName + "'");
                if (resultSet.next()) {
                    id = resultSet.getInt("id");
                    System.out.println(id);
                    System.out.println("already there is guest");
                }else {
                    try {
                        pst = conn.prepareStatement("insert into Guest (first_name,last_name) values(?,?)");
                        pst.setString(1, firstName);
                        pst.setString(2, lastName);

                        pst.executeUpdate();
                        id = 0;
                        System.out.println("Added to Guests Successfully");
                        conn.close();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                        System.out.println("error adding to guests");
                    }
                }
            } catch (SQLException e) {
                System.out.println("found a guest");
            }

    }
        public void getGuestId() {
        try {
            connect();
            pst = conn.prepareStatement("select * from guest where first_name = ? and last_name = ?");
            pst.setString(1, firstName);
            pst.setString(2, lastName);

            ResultSet resultset = pst.executeQuery();
            while(resultset.next()){
                id = resultset.getInt("id");
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
            System.out.println("error selecting guest");
            id = 0;
        }
    }
}

