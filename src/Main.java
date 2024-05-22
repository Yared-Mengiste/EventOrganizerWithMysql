import java.sql.SQLException;
import java.sql.*;

public class Main {
    public static void main(String[] arr){
        try {
            AppGuiPart m = new AppGuiPart("event_organizer", "PHW#84#joer");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
