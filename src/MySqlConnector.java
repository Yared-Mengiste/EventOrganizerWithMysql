
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

/**
 * This class is used to connect to Mysql and help to retrieve and add data to
 * the database
 */
public class MySqlConnector extends JFrame {
    protected String dataBaseName;
    protected String passWord;
    protected Connection conn;
    protected PreparedStatement pst;

    /**
     * this constructor accepts
     * 
     * @param dataBaseName is a string data type that accepts the name of the
     *                     dataBase you want to connect
     * @param passWord     is a string data type that accepts the password of the
     *                     server
     */
    public MySqlConnector(String dataBaseName, String passWord) {
        this.dataBaseName = dataBaseName;
        this.passWord = passWord;
    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/" + dataBaseName, "root", passWord);
            System.out.println("Successfully connected to database");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param query a string param which accepts the command from the user
     * @param table is a JTable param that will show the query
     */
    public void showTable(String query, JTable table) {
        try {
            connect();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);

            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int columns = resultSetMetaData.getColumnCount();

            String[] columnName = new String[columns];
            for (int i = 0; i < columns; i++) {
                columnName[i] = resultSetMetaData.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(columnName);

            String[] rows = new String[columns];
            while (rs.next()) {
                for (int j = 0; j < columns; j++) {
                    rows[j] = rs.getString(j + 1);
                }
                model.addRow(rows);
            }
            System.out.println("success showed table table");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("can't show table");
        }
    }

    public ResultSet giveQuery(String sql) {
        try {
            connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            return pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
