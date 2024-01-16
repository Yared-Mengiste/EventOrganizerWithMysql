import java.sql.SQLException;

/**
 * class person is used as a parent class for all other classes
 */
public class Person extends MySqlConnector {
    protected String firstName;
    protected String lastName;
    protected String phoneNo1;
    protected String phoneNo2;
    protected String sex;

    /**
     * person is used to initialize
     * @param firstName is used to initialize firstName
     * @param lastName  is used to initialize lastName
     * @param phoneNo1  is used to initialize phoneNo1
     * @param phoneNo2  is used to initialize phoneNo2
     */
    public Person(String firstName, String lastName, String phoneNo1, String phoneNo2, String databaseName,String sex, String password) {
        super(databaseName, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo1 = phoneNo1;
        this.phoneNo2 = phoneNo2;
        this.sex = sex;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNo1(String phoneNo1) {
        this.phoneNo1 = phoneNo1;
    }

    public void setPhoneNo2(String phoneNo2) {
        this.phoneNo2 = phoneNo2;
    }

    public void setSex(String  sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNo1() {
        return phoneNo1;
    }

    public String getPhoneNo2() {
        return phoneNo2;
    }

    /**
     * this function is used to add an object to a table within a given database
     * @param tableName is string parameter used to specify the tables name which we inset to
     */

}
