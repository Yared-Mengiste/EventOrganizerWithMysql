import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

public class Main extends MySqlConnector implements ActionListener, KeyListener {
    JTable table;
    JTextArea text;
    JButton button;
    JButton button1;
    JPanel panel;
    GridBagConstraints con = new GridBagConstraints();


    /**
     * this constructor accepts
     *
     * @param dataBaseName is a string data type that accepts the name of the dataBase you want to connect
     * @param passWord     is a string data type that accepts the password of the server
     * @throws SQLException accepts error
     */
    public Main(String dataBaseName, String passWord) throws SQLException {
        super(dataBaseName, passWord);
        panel = new JPanel();
        panel.setBackground(Color.orange);
        panel.setLayout(new GridBagLayout());
        button = new JButton("Enter");
        con.gridx = 1;
        con.gridy = 1;
        con.gridwidth = 1;
        con.fill = GridBagConstraints.HORIZONTAL;
        panel.add(button, con);
        button1 = new JButton("Clear");
        con.gridx = 0;
        con.gridy = 1;
        con.gridwidth = 1;
        con.fill = GridBagConstraints.HORIZONTAL;
        panel.add(button1, con);
        text = new JTextArea("Enter query",1,30);
        con.gridx = 0;
        con.gridy = 0;
        con.gridwidth = 2;
        con.fill = GridBagConstraints.HORIZONTAL;
        panel.add(text, con);
        text.setBorder(BorderFactory.createLineBorder(Color.green, 5));
        text.addKeyListener(this);
        button.addActionListener(this);
        button1.addActionListener(this);
        setSize(500, 500);

        add(panel, BorderLayout.SOUTH);
        table = new JTable();
        showTable("select title, rating , CONCAT(first_name, ' ', last_name) as reviewer from reviews natural " +
                "JOIN reviewers NATURAL JOIN series", table);
        add(new JScrollPane(table));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }
    public static void main(String[] arr){
        try {
            new Main("book_shop", "PHW#84#joer");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button)
        {
            String queries = text.getText();
            text.setText("");
            System.out.println(queries);
            showTable(queries, table);

        } else if (e.getSource() == button1) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
//            removeAllComponents(panel);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            String queries = text.getText();
            System.out.println(queries);
            showTable(queries, table);

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * removeAllComponents is used for removing all components from a given component you put in
     * @param container is an object of the class that is used to get a container like jFrame, JPanel
     */
    private static void removeAllComponents(Container container) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            container.remove(component);
        }
        container.revalidate();
        container.repaint();
    }
    /**
     * removeAllComponents is used for removing all components from the extended JFrame container
     * */
    private void removeAllComponents() {
        Component[] components = getContentPane().getComponents();
        for (Component component : components) {
            getContentPane().remove(component);
        }
        revalidate();
        repaint();
    }
}
