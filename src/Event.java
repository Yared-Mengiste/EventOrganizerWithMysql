import java.sql.SQLException;

public class Event extends MySqlConnector{
    private int id;
    private String eventName;
    private int typeId;
    private int venueId;
    private String eventDate;
    private String startTime;
    private String endTime;
    private int customerId;

    public Event(int id, String eventName, int typeId, int venueId, String eventDate,
                 String startTime, String endTime, int customerId,String databaseName, String password) {
        super(databaseName, password);
        this.id = id;
        this.eventName = eventName;
        this.typeId = typeId;
        this.venueId = venueId;
        this.eventDate = eventDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerId = customerId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public void addEvent() {

        String sql = "INSERT INTO Event (event_name, type_id, venue_id, event_date, start_time," +
                " end_time, customer_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {pst = conn.prepareStatement(sql);
            pst.setString(1, eventName);
            pst.setInt(2, typeId);
            pst.setInt(3, venueId);
            pst.setString(4, eventDate);
            pst.setString(5, startTime);
            pst.setString(6, endTime);
            pst.setInt(7, customerId);

            pst.executeUpdate();
            System.out.println("Record added to Event table.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
