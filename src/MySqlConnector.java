
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

/**
 * This class is used to connect to Mysql and help to retrieve and add data to the database
 */
public class MySqlConnector extends JFrame {
    protected String dataBaseName;
    protected String passWord;
    protected Connection conn;
    protected PreparedStatement pst;

    /**
     * this constructor accepts
     * @param dataBaseName is a string data type that accepts the name of the dataBase you want to connect
     * @param passWord is a string data type that accepts the password of the server
     */
    public MySqlConnector(String dataBaseName, String passWord) {
        this.dataBaseName = dataBaseName;
        this.passWord = passWord;
        connect();
    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/" + dataBaseName, "root", passWord);
            System.out.println("Success");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param queries a String variable that accepts a query eg 'insert into java(id,fname,lname)values(?,?,?)'
     * @param values is An array of String that accepts the values of the query's rows eg [1, 'Yared', 'Mengiste']
     */
    public void add(String queries, String[] values) {

        try {
            pst = conn.prepareStatement(queries);
            for(int i = 0; i < values.length; i++)
                pst.setString(i+1, values[i]);
            System.out.println("Added");

            pst.executeUpdate();
        }

        catch (SQLException e1) {

            e1.printStackTrace();
        }


    }

    /**
     *
     * @param query a string param which accepts the command from the user
     * @param table is a JTable param that will show the query
     */
    public void showTable(String query, JTable table) {
        try (Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
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
            System.out.println("success");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception accordingly (show message, log, etc.)
        }
    /*public void showTable(String query, JTable table) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData resultSetMetaData = rs.getMetaData();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int columns = resultSetMetaData.getColumnCount();
        String[] columnName = new String[columns];
        for(int i = 0; i < columns; i++){
            columnName[i] = resultSetMetaData.getColumnName(i + 1);
        }
        model.setColumnIdentifiers(columnName);
        String[] rows = new String[columns];
        while (rs.next()){
            for (int j = 0; j < columns ; j++) {
                rows[j] = rs.getString(j + 1);
            }
            model.addRow(rows);
        }*/

    }

}
