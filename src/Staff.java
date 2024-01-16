import java.sql.SQLException;


public class Staff extends Person {
    private int position;
    private String DOB;
    private int supervisorId;
    private String pwd;
    private int positionId;


    // Constructor
    public Staff(String firstName, String lastName, String phoneNo1, String phoneNo2, String sex, int position,
                 String databaseName,String DOB, String password,String pwd, int supervisorId, int positionId) {
        // Call the constructor of the superclass (Person)
        super(firstName, lastName, phoneNo1, phoneNo2, databaseName, sex, password);

        // Set the position specific to the Staff class
        this.position = position;
        this.DOB = DOB;
        this.supervisorId = supervisorId;
        this.pwd = pwd;
        this.positionId = positionId;
    }

    public void addStaff() {

        String sql = "INSERT INTO Staff (first_name, last_name, sex, position_id, DOB, supervisor_id, password," +
                " tellNo1, tellNo2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3, sex);
            pst.setInt(4, positionId);
            pst.setString(5, DOB);
            pst.setInt(6, supervisorId);
            pst.setString(7, pwd);
            pst.setString(8, phoneNo1);
            pst.setString(9, phoneNo2);

            pst.executeUpdate();
            System.out.println("Record added to Staff table.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        // Setter method for position
        public void setPosition ( int position){
            this.position = position;
        }

        // Getter method for position
        public int getPosition () {
            return position;
        }
    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getDOB() {
        return DOB;
    }

    // Setter and Getter methods for supervisorId
    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }

    public int getSupervisorId() {
        return supervisorId;
    }

    // Setter and Getter methods for pwd
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwd() {
        return pwd;
    }

    // Setter and Getter methods for positionId
    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public int getPositionId() {
        return positionId;
    }

    }

