import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main extends MySqlConnector implements ActionListener, KeyListener {

    protected JTextField signUpName;
    protected JPasswordField signUpPasswd, confirm;
    protected JLabel signUpNameL, signUpPasswdL, confirmL,messageLabel ;
    protected JButton signIn, signUp;
    protected JPanel signInContainer;
    protected GridBagConstraints constraint = new GridBagConstraints();
    protected JTextField name;
    protected Customer customer;
    protected Staff staff;
    protected JPasswordField signInPasswd;
    protected JLabel nameL,signInL, signInPasswdL;
    protected JMenuBar menuBar;
    protected JMenuItem signInStaff, signInCustomer;
    protected JTextField staffName;
    protected JPasswordField staffSignInPasswd;
    protected JLabel staffNameL, staffSignInL, staffSignInPasswdL;
    protected JButton staffSignIn;
    protected JPanel staffSignInContainer;


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

    public static void main(String[] arr){
        try {
            Main m = new Main("event_organizer", "PHW#84#joer");
            m.signInGui();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * removeAllComponents is used for removing all components from a given component you put in
     * @param container is an object of the class that is used to get a container like jFrame, JPanel
     */
    protected static void removeAllComponents(Container container) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            container.remove(component);
        }
        container.revalidate();
        container.repaint();
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
        signInStaff.addActionListener(this);
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
        signIn.addActionListener(this);
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
        signUp.addActionListener(this);
        constraint = frameConstraint(1,9,1,1, 0);
        signInContainer.add(signUp, constraint);

        setVisible(true);
    }

    /**
     * this method is used to Staff sign in
     */
    public void staffSignInGui(){
        removeAllComponents();
        setVisible(false);
        setLayout(new GridBagLayout());

        // menuBar
        menuBar = new JMenuBar();
        JMenu staffSignIn = new JMenu("Sign IN");

        signInCustomer = new JMenuItem("Customer SignIn");
        signInCustomer.addActionListener(this);
        staffSignIn.add(signInCustomer);

        JMenu about = new JMenu("About");
        menuBar.add(staffSignIn);
        menuBar.add(about);
        setJMenuBar(menuBar);

        //signIn JPanel
        staffSignInContainer = new JPanel(new GridBagLayout());
        staffSignInContainer.setBackground(Color.orange);
        staffSignInContainer.setBorder(BorderFactory.createLineBorder(Color.orange, 10));
        staffSignInContainer.setSize(200, 400);
        constraint = frameConstraint(3,1, 1, 1, 10);
        add(staffSignInContainer, constraint);
        //staff sign in label
        staffSignInL = new JLabel("       Staff SignIN");
        JLabel empty = new JLabel("                 ");
        constraint = frameConstraint(2,0, 1, 1, 10);
        staffSignInContainer.add(empty, constraint);
        staffSignInL.setFont(new Font("Arial", Font.BOLD, 25));
        constraint = frameConstraint(0,0,2,1, 10);
        staffSignInContainer.add(staffSignInL, constraint);
        //staff name label
        staffNameL = new JLabel("  Staff Name");
        staffNameL.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,1, 1, 1, 10);
        staffSignInContainer.add(staffNameL, constraint);
        //staff name text field
        staffName = new JTextField(25);
        staffName.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,2, 2, 1, 10);
        staffSignInContainer.add(staffName, constraint);
        //staff password label
        staffSignInPasswdL = new JLabel("  Password");
        staffSignInPasswdL.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,4, 1, 1, 10);
        staffSignInContainer.add(staffSignInPasswdL, constraint);
        //staff password passwordField
        staffSignInPasswd = new JPasswordField(25);
        staffSignInPasswd.setEchoChar('*');
        setFont(new Font("Arial", Font.BOLD, 15));
        staffSignInPasswd.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,5, 2, 1, 10);
        staffSignInContainer.add(staffSignInPasswd, constraint);

        JLabel empty1 = new JLabel(".");
        constraint = frameConstraint(0,6,1,1, 0);
        staffSignInContainer.add(empty1, constraint);
        //signIn JButton
        this.staffSignIn = new JButton("Sign In");
        this.staffSignIn.setFocusable(false);
        this.staffSignIn.setFont(new Font("Arial", Font.BOLD, 15));
        this.staffSignIn.setForeground(Color.white);
        this.staffSignIn.setBackground(new Color(12, 100, 255));
        constraint = frameConstraint(1,7,1,1, 0);
        staffSignInContainer.add(this.staffSignIn, constraint);
        this.staffSignIn.addActionListener(this);

        JLabel empty2 = new JLabel(".");
        constraint = frameConstraint(0,8,1,1, 0);
        staffSignInContainer.add(empty2, constraint);
        //signUp JButton
        setVisible(true);
    }

    public void signUpGui(){
        removeAllComponents();
        setVisible(false);
        setLayout(new GridBagLayout());

        // menuBar
        menuBar = new JMenuBar();
        JMenu staffSignIn = new JMenu("Sign IN");

        signInStaff = new JMenuItem("Staff SignIn");
        staffSignIn.add(signInStaff);
        signInStaff.addActionListener(this);

        signInCustomer = new JMenuItem("Customer SignIn");
        signInCustomer.addActionListener(this);
        staffSignIn.add(signInCustomer);

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

        signInL = new JLabel("SignUp");
        JLabel empty = new JLabel("                 ");
        constraint = frameConstraint(2,0, 1, 1, 10);
        signInContainer.add(empty, constraint);
        signInL.setFont(new Font("Arial", Font.BOLD, 25));
        constraint = frameConstraint(1,0,1,1, 10);
        signInContainer.add(signInL, constraint);

        signUpNameL = new JLabel("  User Name");
        signUpNameL.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,1, 1, 1, 10);
        signInContainer.add(signUpNameL, constraint);

        signUpName = new JTextField(25);
        signUpName.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,2, 2, 1, 10);
        signInContainer.add(signUpName, constraint);

//        signInL = new JLabel("SignUN");
        constraint = frameConstraint(2,0, 1, 1, 10);
        signInContainer.add(empty, constraint);
        signUpPasswdL = new JLabel("  Password");
        signUpPasswdL.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,4, 1, 1, 10);
        signInContainer.add(signUpPasswdL, constraint);

        signUpPasswd = new JPasswordField(25);
        signUpPasswd.setEchoChar('*');
        setFont(new Font("Arial", Font.BOLD, 15));
        signUpPasswd.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,5, 2, 1, 10);
        signInContainer.add(signUpPasswd, constraint);

        JLabel empty1 = new JLabel(".");
        constraint = frameConstraint(0,6,1,1, 0);
        signInContainer.add(empty1, constraint);
        //signIn JButton
        confirmL = new JLabel("Confirm");
        confirmL.setFocusable(false);
        confirmL.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,7,1,1, 0);
        signInContainer.add(confirmL, constraint);

        confirm = new JPasswordField(25);
        confirm.setEchoChar('*');
        confirm.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,8,2,1, 10);
        signInContainer.add(confirm, constraint);

        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.RED);
        constraint = frameConstraint(0, 11, 2, 1, 10);
        signInContainer.add(messageLabel, constraint);

        JLabel empty2 = new JLabel(".");
        constraint = frameConstraint(0,9,1,1, 0);
        signInContainer.add(empty2, constraint);
        //signUp JButton
        signUp = new JButton("Sign Up");
        signUp.setFont(new Font("Arial", Font.BOLD, 15));
        signUp.setForeground(Color.white);
        signUp.setBackground(new Color(12,100, 255));
        signUp.setFocusable(false);
        constraint = frameConstraint(1,10,1,1, 0);
        signInContainer.add(signUp, constraint);

        setVisible(true);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signInStaff) {
            staffSignInGui();
        } else if (e.getSource() == signInCustomer) {
            signInGui();
        } else if (e.getSource() == signIn) {
            String fullName, fName, lName, userPassword, phoneNo1, phoneNo2;
            int id;
            ResultSet resultSet = null;
            fullName = name.getText();
            System.out.println(fullName);
            if (checkSpace(fullName) && signInPasswd.getPassword().length > 3) {
                ArrayList<String> separatedName = separateName(fullName);
                fName = separatedName.getFirst();
                System.out.println(fName);
                lName = separatedName.getLast();
                System.out.println(lName);
                userPassword = new String(signInPasswd.getPassword());
                System.out.println(userPassword);
                try {
                    resultSet = Customer.getCustomer(fName, lName, userPassword, dataBaseName, passWord);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    if (!(resultSet == null || !resultSet.next())) {
                        phoneNo1 = resultSet.getString(4);
                        phoneNo2 = resultSet.getString(5);
                        id = resultSet.getInt(1);
                        customer = new Customer(fName, lName, phoneNo1, phoneNo2, dataBaseName, passWord, userPassword);
                        customer.setId(id);
                        name.setText(customer.getFirstName());

                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        } else if (e.getSource() == signIn) {
            String fullName, fName, lName, userPassword, phoneNo1, phoneNo2;
            int id;
            ResultSet resultSet;
            fullName = name.getText();
            System.out.println(fullName);
            if (checkSpace(fullName) && signInPasswd.getPassword().length > 3) {
                ArrayList<String> separatedName = separateName(fullName);
                fName = separatedName.getFirst();
                System.out.println(fName);
                lName = separatedName.getLast();
                System.out.println(lName);
                userPassword = new String(signInPasswd.getPassword());
                System.out.println(userPassword);
                try {
                    resultSet = Customer.getCustomer(fName, lName, userPassword, dataBaseName, passWord);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    if (!resultSet.next()) {
                        name.setText("Incorrect Customer name or password");
                        signInPasswd.setText("");
                    } else {
                        phoneNo1 = resultSet.getString(4);
                        phoneNo2 = resultSet.getString(5);
                        id = resultSet.getInt(1);
                        customer = new Customer(fName, lName, phoneNo1, phoneNo2, dataBaseName, passWord, userPassword);
                        customer.setId(id);
                        name.setText(customer.getFirstName());
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }else if (e.getSource() == staffSignIn) {
            String fullName, fName, lName, userPassword, phoneNo1, phoneNo2, dob, sex;
            int id, positionId;
            ResultSet resultSet;
            fullName = staffName.getText();
            System.out.println(fullName);
            if (checkSpace(fullName) && staffSignInPasswd.getPassword().length > 3) {
                ArrayList<String> separatedName = separateName(fullName);
                fName = separatedName.getFirst();
                System.out.println(fName);
                lName = separatedName.getLast();
                System.out.println(lName);
                userPassword = new String(staffSignInPasswd.getPassword());
                System.out.println(userPassword);
                try {
                    resultSet = Staff.getStaff(fName, lName, userPassword, dataBaseName, passWord);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    if (!resultSet.next()) {
                        staffName.setText("Incorrect Customer name or password");
                        staffSignInPasswd.setText("");
                    } else {
                        id = resultSet.getInt("id");
                        //TODO position id and supervisor id yikeral
                        dob = resultSet.getString("DOB");
                        sex = resultSet.getString("sex");
                        phoneNo1 = resultSet.getString("tellNo1");
                        phoneNo2 = resultSet.getString("tellNo2");
                        staff = new Staff(fName, lName,phoneNo1,phoneNo2,sex,dataBaseName,dob,passWord,userPassword
                                ,2,1);
                        staff.setId(id);
                        staffName.setText(staff.getDOB());
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        } else if (e.getSource() == signUp) {
            signUpGui();
        }

    }

    /**
     * removeAllComponents is used for removing all components from the extended JFrame container
     * */
    protected void removeAllComponents() {
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

    /**
     * this method separate full name father and username
     *
     * @param fullName this will accept a string
     * @return and returns an arrayList of String<> with 2 stings in it
     */
    public ArrayList<String> separateName(String fullName) {
        ArrayList<String> separatedName = new ArrayList<>();
        for (int i = 0; i < fullName.length(); i++) {
            if (fullName.charAt(i) == ' ') {
                separatedName.add(fullName.substring(0, i).toUpperCase());
                separatedName.add(fullName.substring(i + 1).toUpperCase());
            }

        }
        return separatedName;
    }

    /**
     * check if a given String is greater than 2 and if it has space in between
     *
     * @param word accepts String
     * @return boolean if it is greater than 2 and have space in between it returns true else false
     */
    public boolean checkSpace(String word) {
        if (word.length() > 2)
            for (int i = 1; i < word.length() - 1; i++) {
                if (word.charAt(i) == ' ')
                    return true;
            }

        return false;
    }
    protected boolean checkPasswordMatch(char[] password, char[] confirmPassword) {
        return new String(password).equals(new String(confirmPassword));
    }
}
