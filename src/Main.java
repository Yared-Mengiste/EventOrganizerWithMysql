import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

public class Main extends MySqlConnector implements ActionListener, KeyListener {

    JTextField name;
    JPasswordField signInPasswd;
    JLabel nameL,signInL, signInPasswdL;
    JButton signIn, signUp;
    JPanel signInContainer;
    GridBagConstraints constraint = new GridBagConstraints();
    JMenuBar menuBar;
    JMenuItem signInStaff, signInCustomer;

    /**
     * this constructor accepts
     *
     * @param dataBaseName is a string data type that accepts the name of the dataBase you want to connect
     * @param passWord     is a string data type that accepts the password of the server
     * @throws SQLException accepts error
     */
    public Main(String dataBaseName, String passWord) throws SQLException {
        super(dataBaseName, passWord);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setTitle("Event Organizer");
        getContentPane().setBackground(new Color(101, 1, 1, 255));
        setMinimumSize(new Dimension(500, 500));

        setVisible(true);
    }

    /**
     * this is a method that is used to show signIn page of the customer side
     */
    public void signInGui(){
        removeAllComponents();
        setVisible(false);
        setLayout(new GridBagLayout());

        // menuBar
        menuBar = new JMenuBar();
        JMenu staffSignIn = new JMenu("Sign IN");
        signInStaff = new JMenuItem("Staff SignIn");
        signInCustomer = new JMenuItem("Customer SignIn");
        staffSignIn.add(signInCustomer);
        staffSignIn.add(signInStaff);
        JMenu about = new JMenu("About");
        menuBar.add(staffSignIn);
        menuBar.add(about);
        setJMenuBar(menuBar);

        //signIn JPanel
        signInContainer = new JPanel(new GridBagLayout());
        signInContainer.setBackground(Color.orange);
        signInContainer.setBorder(BorderFactory.createLineBorder(Color.orange, 10));
        signInContainer.setSize(200, 400);
        constraint = frameConstraint(3,1, 1, 1, 10);
        add(signInContainer, constraint);

        signInL = new JLabel("SignIN");
        JLabel empty = new JLabel("                 ");
        constraint = frameConstraint(2,0, 1, 1, 10);
        signInContainer.add(empty, constraint);
        signInL.setFont(new Font("Arial", Font.BOLD, 25));
        constraint = frameConstraint(1,0,1,1, 10);
        signInContainer.add(signInL, constraint);

        nameL = new JLabel("  User Name");
        nameL.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,1, 1, 1, 10);
        signInContainer.add(nameL, constraint);

        name = new JTextField(25);
        name.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,2, 2, 1, 10);
        signInContainer.add(name, constraint);

        signInL = new JLabel("SignIN");
        constraint = frameConstraint(2,0, 1, 1, 10);
        signInContainer.add(empty, constraint);
        signInPasswdL = new JLabel("  Password");
        signInPasswdL.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,4, 1, 1, 10);
        signInContainer.add(signInPasswdL, constraint);

        signInPasswd = new JPasswordField(25);
        signInPasswd.setEchoChar('*');
        setFont(new Font("Arial", Font.BOLD, 15));
        signInPasswd.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,5, 2, 1, 10);
        signInContainer.add(signInPasswd, constraint);

        JLabel empty1 = new JLabel(".");
        constraint = frameConstraint(0,6,1,1, 0);
        signInContainer.add(empty1, constraint);
        //signIn JButton
        signIn = new JButton("Sign In");
        signIn.setFocusable(false);
        signIn.setFont(new Font("Arial", Font.BOLD, 15));
        signIn.setForeground(Color.white);
        signIn.setBackground(new Color(12,100, 255));
        constraint = frameConstraint(1,7,1,1, 0);
        signInContainer.add(signIn, constraint);

        JLabel empty2 = new JLabel(".");
        constraint = frameConstraint(0,8,1,1, 0);
        signInContainer.add(empty2, constraint);
        //signUp JButton
        signUp = new JButton("Sign Up");
        signUp.setFont(new Font("Arial", Font.BOLD, 15));
        signUp.setForeground(Color.white);
        signUp.setBackground(new Color(12,100, 255));
        signUp.setFocusable(false);
        constraint = frameConstraint(1,9,1,1, 0);
        signInContainer.add(signUp, constraint);

        setVisible(true);
    }
    public static void main(String[] arr){
        try {
            Main m = new Main("book_shop", "PHW#84#joer");
            m.signInGui();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

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
    /**
     * set a given component a position
     * @param x set column of the gridBag layout
     * @param y set row of the gridBag layout
     * @param width set how many column will it take
     * @param height set how many rows will it take
     * @return Constraint type object
     */
    public GridBagConstraints frameConstraint(int x, int y, int width, int height, int ipady){
        GridBagConstraints temp = new GridBagConstraints();
        temp.gridx = x;
        temp.gridy = y;
        temp.gridwidth = width;
        temp.gridheight = height;
        temp.ipady =ipady;
        return temp;
    }
}
