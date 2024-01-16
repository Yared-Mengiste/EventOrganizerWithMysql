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
}

