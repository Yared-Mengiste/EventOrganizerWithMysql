import java.sql.*;


public class Staff extends Person {
    private String DOB;
    private String appointed_date;
    private int supervisorId;
    private String pwd;
    private int positionId;


    public Staff(String firstName, String lastName, String phoneNo1, String phoneNo2, String sex, String databaseName
            , String DOB, String password, String appointedDate, String pwd, int supervisorId, int positionId) {
        super(firstName, lastName, phoneNo1, phoneNo2, databaseName, sex, password);

        this.DOB = DOB;
        this.appointed_date = appointedDate;
        this.supervisorId = supervisorId;
        this.pwd = pwd;
        this.positionId = positionId;
    }

    /**
     * getStaff is a method that is used to get a Staff's ingo by giving it
     * @param fName
     * @param lName
     * @param password
     * @param dataBaseName
     * @param passWord
     * @return and returns A result set that u can use
     * @throws SQLException
     */
    public static ResultSet getStaff(String fName, String lName, String password, String dataBaseName, String passWord) throws SQLException {
        String sql = "SELECT * FROM staff WHERE first_name = ? AND last_name = ? AND password = ?";
        ResultSet result = null;
        Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost/" + dataBaseName, "root",
                passWord);

        try {

            PreparedStatement pst = conn1.prepareStatement(sql);
            pst.setString(1, fName);
            pst.setString(2, lName);
            pst.setString(3, password);

            result = pst.executeQuery();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void addStaff() {

        String sql = "INSERT INTO Staff (first_name, last_name, sex, position_id, DOB, supervisor_id, password," +
                " tellNo1, tellNo2, appointed_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/" + dataBaseName, "root",
                    passWord);
            pst = conn.prepareStatement(sql);
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3, sex);
            pst.setInt(4, positionId);
            pst.setDate(5, Date.valueOf(DOB));//"yyyy-MM-dd" format String
            pst.setInt(6, supervisorId);
            pst.setString(7, pwd);
            pst.setString(8, phoneNo1);
            pst.setString(9, phoneNo2);
            pst.setDate(10, Date.valueOf(appointed_date));

            pst.executeUpdate();
            System.out.println("Record added to Staff table.");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding staff");
        }
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

    public String getAppointed_date() {
        return appointed_date;
    }

    public void setAppointed_date(String appointed_date) {
        this.appointed_date = appointed_date;
    }
}

