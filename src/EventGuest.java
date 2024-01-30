import java.sql.SQLException;

public class EventGuest extends MySqlConnector{
    protected int event_id;
    protected int guest_id;

   public EventGuest(String dataBaseName, String password){
       super(dataBaseName,password);
   }
    /**
     * this method is used to add event Guest to the event
     * @param event_id
     * @param guest_id
     * @param dataBaseName
     * @param password
     */
   public static void addEventGuest(int event_id, int guest_id, String dataBaseName, String password){
       try{
            MySqlConnector m = new MySqlConnector(dataBaseName, password);
            m.connect();
            m.pst = m.conn.prepareStatement("insert into eventGuests(event_id, guest_id) values(?, ?)");
            m.pst.setInt(1, event_id);
            m.pst.setInt(2, guest_id);
            m.pst.executeUpdate();
           System.out.println("successfully added to eventStaffs");
       } catch (SQLException e){
           e.printStackTrace();
       }

   }

    public static void removeEventGuest(int event_id, int guest_id, String dataBaseName, String password){
        try{
            MySqlConnector m = new MySqlConnector(dataBaseName, password);
            m.connect();
            m.pst = m.conn.prepareStatement("delete from eventGuests where event_id = ? and guest_id = ?");
            m.pst.setInt(1, event_id);
            m.pst.setInt(2, guest_id);
            m.pst.executeUpdate();
            System.out.println("successfully removed from eventGuest");
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}
