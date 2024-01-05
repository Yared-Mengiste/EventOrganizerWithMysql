/**
 * class person is used as a parent class for all other classes
 */
public class Person {
    protected String firstName;
    protected String lastName;
    protected String phoneNo1;
    protected String phoneNo2;

    /**
     * person is used to initialize
     * @param firstName is used to initialize firstName
     * @param lastName is used to initialize lastName
     * @param phoneNo1 is used to initialize phoneNo1
     * @param phoneNo2 is used to initialize phoneNo2
     */
    public Person(String firstName, String lastName, String phoneNo1, String phoneNo2){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo1 = phoneNo1;
        this.phoneNo2 = phoneNo2;
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

    // Getter methods
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
}
