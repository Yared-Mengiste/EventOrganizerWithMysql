public class Staff extends Person {
    private String position;

    // Constructor
    public Staff(String firstName, String lastName, String phoneNo1, String phoneNo2, String position) {
        // Call the constructor of the superclass (Person)
        super(firstName, lastName, phoneNo1, phoneNo2);

        // Set the position specific to the Staff class
        this.position = position;
    }

    // Setter method for position
    public void setPosition(String position) {
        this.position = position;
    }

    // Getter method for position
    public String getPosition() {
        return position;
    }
}
