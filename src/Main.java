import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Main extends MySqlConnector implements ActionListener , MouseListener {

    protected JLabel userOptionTitle, create, view, addGuests,signOut, showPriceL;
    protected JTable taskedWork;
    protected JLabel current_date;
    protected double perPersonPrice;
    protected JTable eventInformation, organizerInfo;
    protected JLabel eventTypeChoice, birthDay, wedding, graduation;
    protected JButton backFirst, finishBooking, confirmBooking;
    protected JLabel eventNameL, guestNoL, eventDateL, startTimeL, endTimeL;
    protected JTextField eventNameT, guestNo, eventDate, startTime, endTime, showPrice;
    protected JButton backToCreate;
    protected ArrayList<Double> venuePrice;
    protected ArrayList<Integer> venueId;
    protected JComboBox<String> venueList;
    protected JPanel south, north, east, west, center;
    protected JTextField signUpName, tellNoo1, tellNoo2;
    protected JPasswordField signUpPasswd, confirm;
    protected JLabel signUpNameL, signUpPasswdL, confirmL,messageLabel, tellNo1L, tellNo2L ;
    protected JButton signIn, signUp, signUpIn;
    protected JPanel signInContainer;
    protected GridBagConstraints constraint = new GridBagConstraints();
    protected JTextField name;
    protected Customer customer;
    protected Staff staff;
    protected Event event;
    protected JPasswordField signInPasswd;
    protected JLabel nameL,signInL, signInPasswdL;
    protected JMenuBar menuBar;
    protected JMenuItem signInStaff, signInCustomer, about;
    protected JTextField staffName;
    protected JPasswordField staffSignInPasswd;
    protected JLabel staffNameL, staffSignInL, staffSignInPasswdL;
    protected JButton staffSignIn;
    protected JPanel staffSignInContainer;
    protected JRadioButton male,female;
    protected JTable showGuests;
    protected JTextField inputEventId, inputFullName;
    protected JButton enterEventId, enterGuestName;
    protected JLabel inputEventIdL, inputFullNameL;
    protected ButtonGroup group;
    protected JLabel assignedWorks, viewInfo, staffSignOut;
    protected ArrayList<String> guestList;
    protected JButton gotoStaffFirst;
    protected JLabel staffFullNameInfo,staffGender, staffSupervisor, staffPosition, staffAppointedDate,
           getStaffPosition,getStaffAppointedDate,getStaffSupervisor;
    protected NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);
    protected int givenEventId = 0, maxGuests , guestCount;
    protected String date;
    protected JButton removeGuest;
    protected JButton updateProfile;
    protected JTextField staffPassword, getStaffFullNameInfo, getStaffGender;
    protected JLabel staffPasswordL;
    protected JLabel userInfoView;
    protected JButton updateCustomerProfile;

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
        setMinimumSize(new Dimension(900, 500));

        signInGui();
    }

    public static void main(String[] arr){
        try {
            Main m = new Main("event_organizer", "PHW#84#joer");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * this is a method that is used to show signIn page of the customer side
     */
    protected void signInGui(){
        removeAllComponents();
        setVisible(false);
        setLayout(new GridBagLayout());

        try{
            ResultSet resultSet = giveQuery("select curdate() as date;");
            resultSet.next();
            date = String.valueOf(resultSet.getDate("date"));
        }catch(SQLException e){
            e.printStackTrace();
        }

        // menuBar
        menuBar = new JMenuBar();
        menuBar.setBackground(Color.orange);
        JMenu staffSignIn = new JMenu("Sign IN");
        menuBar.add(staffSignIn);

        signInStaff = new JMenuItem("Staff SignIn");
        signInStaff.addActionListener(this);
        staffSignIn.add(signInStaff);

        about = new JMenuItem("About");
        about.addActionListener(this);
        JMenu aboutInfo = new JMenu("About");
        menuBar.add(aboutInfo);
        aboutInfo.add(about);
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
        name.addActionListener(this);
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
        signInPasswd.addActionListener(this);

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

        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.RED);
        constraint = frameConstraint(0, 13, 2, 1, 10);
        signInContainer.add(messageLabel, constraint);

        setVisible(true);
    }

    /**
     * this method is used to Staff sign in
     */
    protected void staffSignInGui(){
        removeAllComponents();
        setVisible(false);
        setLayout(new GridBagLayout());

        // menuBar
        menuBar = new JMenuBar();
        menuBar.setBackground(Color.orange);
        JMenu staffSignIn = new JMenu("Sign IN");

        signInCustomer = new JMenuItem("Customer SignIn");
        signInCustomer.addActionListener(this);
        staffSignIn.add(signInCustomer);

        JMenu about = new JMenu("About");
        about.add(this.about);
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
        staffName.addActionListener(this);
        staffName.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,2, 2, 1, 10);
        staffSignInContainer.add(staffName, constraint);
        staffName.requestFocus();
        //staff password label
        staffSignInPasswdL = new JLabel("  Password");
        staffSignInPasswdL.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,4, 1, 1, 10);
        staffSignInContainer.add(staffSignInPasswdL, constraint);
        //staff password passwordField
        staffSignInPasswd = new JPasswordField(25);
        staffSignInPasswd.addActionListener(this);
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

        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.RED);
        constraint = frameConstraint(0, 12, 2, 1, 10);
        staffSignInContainer.add(messageLabel, constraint);
        //signUp JButton
        setVisible(true);
    }

    protected void pressedAbout(){
        beginning();
        eventTypeChoice = new JLabel("Event Organizing App");
        eventTypeChoice.setForeground(Color.orange);
        eventTypeChoice.setFont(new Font("Serif", Font.PLAIN, 50));
        north.add(eventTypeChoice);

        center.setLayout(new GridBagLayout());
        JTextArea aboutInfo = new JTextArea("The purpose of this project is to develop\n and implement an event organizing" +
                " database system\n that can provide user-friendly solution that can facilitate\n and improve event organizing." +
                " The system will\n allow users to create and manage events, book and allocate venues,\n register and communicate" +
                " with attendees, and perform\n other related tasks. The system will aim to improve the \nefficiency, reduce the" +
                " costs, and enhance\n the quality of event organizing.The project will address the current\n problems and " +
                "limitations of existing event\n organizing methods or systems, such as manual processes,\n paper-based records," +
                " lack of integration\n, and so on. These problems can cause errors, delays, waste,\n and dissatisfaction " +
                "among the users and the stakeholders.");
        aboutInfo.setFont(new Font("Serif", Font.PLAIN, 18));
        aboutInfo.setBackground(Color.orange);
        aboutInfo.setBorder(BorderFactory.createDashedBorder(Color.red, 1f, 3f, 1f, true));
        aboutInfo.setForeground(new Color(101, 1, 1, 255));
        center.add(new JScrollPane(aboutInfo));
        setVisible(true);
    }

    protected void signUpGui(){
        removeAllComponents();
        setVisible(false);
        setLayout(new GridBagLayout());

        // menuBar
        menuBar = new JMenuBar();
        menuBar.setBackground(Color.orange);
        JMenu staffSignIn = new JMenu("Sign IN");

        signInStaff = new JMenuItem("Staff SignIn");
        staffSignIn.add(signInStaff);
        signInStaff.addActionListener(this);

        signInCustomer = new JMenuItem("Customer SignIn");
        signInCustomer.addActionListener(this);
        staffSignIn.add(signInCustomer);

        JMenu about = new JMenu("About");
        about.add(this.about);
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

        signUpNameL = new JLabel("  Full Name");
        signUpNameL.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,1, 1, 1, 10);
        signInContainer.add(signUpNameL, constraint);

        signUpName = new JTextField(25);
        signUpName.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,2, 2, 1, 10);
        signInContainer.add(signUpName, constraint);
        signUpName.requestFocus();
        signUpName.addActionListener(this); //todo signUpName to tellNoo1

        tellNo1L = new JLabel("  Phone 1");
        tellNo1L.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,3, 1, 1, 10);
        signInContainer.add(tellNo1L, constraint);

        tellNoo1 = new JTextField(25);
        tellNoo1.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,4, 2, 1, 10);
        signInContainer.add(tellNoo1, constraint);
        tellNoo1.addActionListener(this); //todo tellNoo1 to tellNoo2

        tellNo2L = new JLabel("  Phone 2");
        tellNo2L.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,5, 1, 1, 10);
        signInContainer.add(tellNo2L, constraint);

        tellNoo2 = new JTextField(25);
        tellNoo2.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,6, 2, 1, 10);
        tellNoo2.addActionListener(this);//todo tellNoo2 to signUpPasswd
        signInContainer.add(tellNoo2, constraint);

//        signInL = new JLabel("SignUN");
        constraint = frameConstraint(2,0, 1, 1, 10);
        signInContainer.add(empty, constraint);

        signUpPasswdL = new JLabel("  Password");
        signUpPasswdL.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,7, 1, 1, 10);
        signInContainer.add(signUpPasswdL, constraint);

        signUpPasswd = new JPasswordField(25);
        signUpPasswd.setEchoChar('*');
        setFont(new Font("Arial", Font.BOLD, 15));
        signUpPasswd.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,8, 2, 1, 10);
        signInContainer.add(signUpPasswd, constraint);
        signUpPasswd.addActionListener(this);//todo signUpPasswd to confirm

        JLabel empty1 = new JLabel(".");
        constraint = frameConstraint(0,9,1,1, 0);
        signInContainer.add(empty1, constraint);
        //signIn JButton
        confirmL = new JLabel("Confirm");
        confirmL.setFocusable(false);
        confirmL.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,10,1,1, 0);
        signInContainer.add(confirmL, constraint);

        confirm = new JPasswordField(25);
        confirm.setEchoChar('*');
        confirm.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,11,2,1, 10);
        signInContainer.add(confirm, constraint);
        confirm.addActionListener(this);//todo confirm signUp

        group = new ButtonGroup();
        male = new JRadioButton("Male");
        male.setFocusable(false);
        male.setSelected(true);
        male.setBackground(Color.ORANGE);
        group.add(male);
        constraint = frameConstraint(0,12, 1, 1, 10);
        signInContainer.add(male, constraint);

        female = new JRadioButton("Female");
        group.add(female);
        female.setFocusable(false);
        female.setBackground(Color.orange);
        constraint = frameConstraint(1,12, 1, 1, 10);
        signInContainer.add(female, constraint);

        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.RED);
        constraint = frameConstraint(0, 15, 2, 1, 10);
        signInContainer.add(messageLabel, constraint);

        JLabel empty2 = new JLabel(".");
        constraint = frameConstraint(0,13,1,1, 0);
        signInContainer.add(empty2, constraint);
        //signUp JButton
        signUpIn = new JButton("Sign Up");
        signUpIn.addActionListener(this);
        signUpIn.setFont(new Font("Arial", Font.BOLD, 15));
        signUpIn.setForeground(Color.white);
        signUpIn.setBackground(new Color(12,100, 255));
        signUpIn.setFocusable(false);
        constraint = frameConstraint(1,14,1,1, 0);
        signInContainer.add(signUpIn, constraint);

        setVisible(true);
    }

    public void beginning(){
        removeAllComponents();
        setLayout(new BorderLayout());
        setVisible(false);
        north = new JPanel();
        north.setBackground(new Color(101, 1, 1, 255));
        north.setPreferredSize(new Dimension(50, 100));
        add(north, BorderLayout.NORTH);

        south = new JPanel();
        south.setBackground(new Color(101, 1, 1, 255));
        south.setPreferredSize(new Dimension(50, 100));
        add(south, BorderLayout.SOUTH);

        east = new JPanel();
        east.setBackground(new Color(101, 1, 1, 255));
        east.setPreferredSize(new Dimension(200, 100));
        add(east, BorderLayout.EAST);

        west = new JPanel();
        west.setBackground(new Color(101, 1, 1, 255));
        west.setPreferredSize(new Dimension(200, 100));
        add(west, BorderLayout.WEST);

        center = new JPanel();
        center.setBackground(Color.orange);
        add(center);

    }

    protected void userInformationView(){
        beginning();
        center.setLayout(new GridLayout(3,2, 20,0));
        north.setLayout(new FlowLayout(FlowLayout.TRAILING, 300, 10));
        south.setLayout(new FlowLayout(FlowLayout.TRAILING, 20,20));

        eventTypeChoice = new JLabel("Your Information");
        eventTypeChoice.setForeground(Color.orange);
        eventTypeChoice.setFont(new Font("Serif", Font.PLAIN, 50));
        north.add(eventTypeChoice);

        staffFullNameInfo = new JLabel("Name ");
        staffFullNameInfo.setForeground(new Color(101, 1, 1, 255));
        staffFullNameInfo.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(staffFullNameInfo);

        getStaffFullNameInfo = new JTextField(customer.getFirstName() + " "+ customer.getLastName());
        getStaffFullNameInfo.setBackground(Color.orange);
        getStaffFullNameInfo.setForeground(new Color(101, 1, 1, 255));
        getStaffFullNameInfo.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(getStaffFullNameInfo);

        staffGender = new JLabel("Gender ");
        staffGender.setForeground(new Color(101, 1, 1, 255));
        staffGender.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(staffGender);

        getStaffGender = new JTextField(customer.getSex());
        getStaffGender.setBackground(Color.orange);
        getStaffGender.setForeground(new Color(101, 1, 1, 255));
        getStaffGender.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(getStaffGender);

        staffPasswordL = new JLabel("Password  ");
        staffPasswordL.setForeground(new Color(101, 1, 1, 255));
        staffPasswordL.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(staffPasswordL);

        staffPassword = new JTextField(customer.getPwd());
        staffPassword.setForeground(new Color(101, 1, 1, 255));
        staffPassword.setFont(new Font("Serif", Font.PLAIN, 20));
        staffPassword.setBackground(Color.orange);
        center.add(staffPassword);

        current_date = new JLabel(date);
        current_date.setFont(new Font("Serif", Font.PLAIN, 15));
        current_date.setForeground(Color.orange);
        north.add(current_date);

        updateCustomerProfile = new JButton("Update");
        updateCustomerProfile.setFont(new Font("Serif", Font.PLAIN, 15));
        updateCustomerProfile.setBackground(new Color(12,100, 255));
        updateCustomerProfile.addActionListener(this);
        south.add(updateCustomerProfile);
        updateCustomerProfile.setFocusable(false);

        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.GREEN);
        west.add(messageLabel);

        backFirst = new JButton("Back");
        backFirst.setFont(new Font("Serif", Font.PLAIN, 15));
        backFirst.setBackground(new Color(12,100, 255));
        backFirst.addActionListener(this);
        south.add(backFirst);
        backFirst.setFocusable(false);

        setVisible(true);
    }

    protected  void staffInformationView(){
        beginning();
        center.setLayout(new GridLayout(7,2, 20,0));
        north.setLayout(new FlowLayout(FlowLayout.TRAILING, 300, 10));
        south.setLayout(new FlowLayout(FlowLayout.TRAILING, 20,20));

        eventTypeChoice = new JLabel("Your Information");
        eventTypeChoice.setForeground(Color.orange);
        eventTypeChoice.setFont(new Font("Serif", Font.PLAIN, 50));
        north.add(eventTypeChoice);

        staffFullNameInfo = new JLabel("Name ");
        staffFullNameInfo.setForeground(new Color(101, 1, 1, 255));
        staffFullNameInfo.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(staffFullNameInfo);

        getStaffFullNameInfo = new JTextField(staff.getFirstName() + " "+ staff.getLastName());
        getStaffFullNameInfo.setBackground(Color.orange);
        getStaffFullNameInfo.setForeground(new Color(101, 1, 1, 255));
        getStaffFullNameInfo.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(getStaffFullNameInfo);

        staffGender = new JLabel("Gender ");
        staffGender.setForeground(new Color(101, 1, 1, 255));
        staffGender.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(staffGender);

        getStaffGender = new JTextField(staff.getSex());
        getStaffGender.setBackground(Color.orange);
        getStaffGender.setForeground(new Color(101, 1, 1, 255));
        getStaffGender.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(getStaffGender);

        staffPosition = new JLabel("Position ");
        staffPosition.setForeground(new Color(101, 1, 1, 255));
        staffPosition.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(staffPosition);

        String position = "";
        double salary = 0;
        try{
            ResultSet resultSet = giveQuery("select * from staffPosition where id = " + staff.getPositionId());
            resultSet.next();
            position = resultSet.getString("position");
            salary = resultSet.getDouble("salary");
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("failed to get position");
        }



        getStaffPosition = new JLabel(position);
        getStaffPosition.setForeground(new Color(101, 1, 1, 255));
        getStaffPosition.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(getStaffPosition);

        JLabel staffSalary, getStaffSalary;
        staffSalary = new JLabel("Salary ");
        staffSalary.setForeground(new Color(101, 1, 1, 255));
        staffSalary.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(staffSalary);

        getStaffSalary = new JLabel(currency.format(salary));
        getStaffSalary.setForeground(new Color(101, 1, 1, 255));
        getStaffSalary.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(getStaffSalary);

        String supervisor = "";
        try {
            ResultSet resultSet = giveQuery("select concat(first_name, ' ', last_name) as name from staff where id = "
                    + staff.getSupervisorId());
            resultSet.next();
            supervisor = resultSet.getString("name");
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("failed to get supervisor name");
        }

        staffSupervisor = new JLabel("Supervisor ");
        staffSupervisor.setForeground(new Color(101, 1, 1, 255));
        staffSupervisor.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(staffSupervisor);

        getStaffSupervisor = new JLabel(supervisor);
        getStaffSupervisor.setForeground(new Color(101, 1, 1, 255));
        getStaffSupervisor.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(getStaffSupervisor);

        staffAppointedDate = new JLabel("Appointed Date ");
        staffAppointedDate.setForeground(new Color(101, 1, 1, 255));
        staffAppointedDate.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(staffAppointedDate);

        getStaffAppointedDate = new JLabel(staff.getAppointed_date());
        getStaffAppointedDate.setForeground(new Color(101, 1, 1, 255));
        getStaffAppointedDate.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(getStaffAppointedDate);

        staffPasswordL = new JLabel("Password  ");
        staffPasswordL.setForeground(new Color(101, 1, 1, 255));
        staffPasswordL.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(staffPasswordL);

        staffPassword = new JTextField(staff.getPwd());
        staffPassword.setForeground(new Color(101, 1, 1, 255));
        staffPassword.setFont(new Font("Serif", Font.PLAIN, 20));
        staffPassword.setBackground(Color.orange);
        center.add(staffPassword);

        current_date = new JLabel(date);
        current_date.setFont(new Font("Serif", Font.PLAIN, 15));
        current_date.setForeground(Color.orange);
        north.add(current_date);

        updateProfile = new JButton("Update");
        updateProfile.setFont(new Font("Serif", Font.PLAIN, 15));
        updateProfile.setBackground(new Color(12,100, 255));
        updateProfile.addActionListener(this);
        south.add(updateProfile);
        updateProfile.setFocusable(false);

        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.GREEN);
        west.add(messageLabel);

        gotoStaffFirst = new JButton("Back");
        gotoStaffFirst.setFont(new Font("Serif", Font.PLAIN, 15));
        gotoStaffFirst.setBackground(new Color(12,100, 255));
        gotoStaffFirst.addActionListener(this);
        south.add(gotoStaffFirst);
        gotoStaffFirst.setFocusable(false);

        setVisible(true);
    }

    protected   void viewAssignedWork(){

        beginning();

        center.setLayout(new GridLayout(3,1, 20,0));

        eventTypeChoice = new JLabel("Welcome " + staff.getFirstName() + " " + staff.getLastName());
        eventTypeChoice.setForeground(Color.orange);
        eventTypeChoice.setFont(new Font("Serif", Font.PLAIN, 50));
        north.add(eventTypeChoice);

        assignedWorks = new JLabel("  1. View Assigned Work ");
        assignedWorks.setFont(new Font("Serif", Font.PLAIN, 40));
        assignedWorks.setBorder(BorderFactory.createDashedBorder(Color.red, 1f, 3f, 1f, true));
        assignedWorks.setBackground(Color.orange);
        assignedWorks.addMouseListener(this);
        center.add(assignedWorks);

        viewInfo = new JLabel("  2. View or Update Personal Info ");
        viewInfo.setFont(new Font("Serif", Font.PLAIN, 40));
        viewInfo.setBorder(BorderFactory.createDashedBorder(Color.red, 1f, 3f, 1f, true));
        viewInfo.setBackground(Color.orange);
        viewInfo.addMouseListener(this);
        center.add(viewInfo);

        staffSignOut = new JLabel("  3. SignOut ");
        staffSignOut.setFont(new Font("Serif", Font.PLAIN, 40));
        staffSignOut.setBorder(BorderFactory.createDashedBorder(Color.red, 1f, 3f, 1f, true));
        staffSignOut.setBackground(Color.orange);
        staffSignOut.addMouseListener(this);
        center.add(staffSignOut);

        setVisible(true);
    }

    protected void userOption(){
        beginning();

        center.setLayout(new GridLayout(5,1));

        userOptionTitle = new JLabel("Welcome "+ customer.getFirstName() + " " + customer.getLastName() );
        userOptionTitle.setForeground(Color.orange);
        userOptionTitle.setFont(new Font("Serif", Font.PLAIN, 50));
        north.add(userOptionTitle);

        create = new JLabel("   1. Book an Event");
        create.setFont(new Font("Serif", Font.PLAIN, 40));
        create.setBorder(BorderFactory.createDashedBorder(Color.red, 1f, 3f, 1f, true));
        create.setBackground(Color.orange);
        create.addMouseListener(this);
        center.add(create);

        view = new JLabel("   2. View Booked Event");
        view.setFont(new Font("Serif", Font.PLAIN, 40));
        view.setBorder(BorderFactory.createDashedBorder(Color.red, 1f, 3f, 1f, true));
        view.setBackground(Color.orange);
        view.addMouseListener(this);
        center.add(view);

        addGuests = new JLabel("  3. Add or Remove Guests");
        addGuests.setBorder(BorderFactory.createDashedBorder(Color.red, 1f, 3f, 1f, true));
        addGuests.setFont(new Font("Serif", Font.PLAIN, 40));
        addGuests.setBackground(Color.orange);
        addGuests.addMouseListener(this);
        center.add(addGuests);

        userInfoView = new JLabel("  4. User Info view and Update");
        userInfoView.setBorder(BorderFactory.createDashedBorder(Color.red, 1f, 3f, 1f, true));
        userInfoView.setFont(new Font("Serif", Font.PLAIN, 40));
        userInfoView.setBackground(Color.orange);
        userInfoView.addMouseListener(this);
        center.add(userInfoView);


        signOut = new JLabel("  5. SignOut");
        signOut.setBorder(BorderFactory.createDashedBorder(Color.red, 1f, 3f, 1f, true));
        signOut.setFont(new Font("Serif", Font.PLAIN, 40));
        signOut.setBackground(Color.orange);
        signOut.addMouseListener(this);
        center.add(signOut);

        setVisible(true);
    }

    protected void addGuestsGui(){
        beginning();
        center.setLayout(new GridLayout(1,1, 20,10));
        north.setLayout(new FlowLayout(FlowLayout.TRAILING, 30, 10));

        eventTypeChoice = new JLabel("Add or Remove Guests");
        eventTypeChoice.setForeground(Color.orange);
        eventTypeChoice.setFont(new Font("Serif", Font.PLAIN, 50));
        north.add(eventTypeChoice);

        inputEventIdL = new JLabel("Event Id ");
        inputEventIdL.setFont(new Font("Serif", Font.PLAIN, 20));
        inputEventIdL.setForeground(Color.orange);
        north.add(inputEventIdL);

        inputEventId = new JTextField(20);
        inputEventId.addActionListener(this);
        inputEventId.setFont(new Font("Serif", Font.PLAIN, 20));
        inputEventId.setBackground(Color.orange);
        north.add(inputEventId);

        enterEventId = new JButton("enter");
        enterEventId.setBackground(new Color(12,100, 255));
        north.add(enterEventId);
        enterEventId.addActionListener(this);
        enterEventId.setFocusable(false);

        showGuests = new JTable();
        showGuests.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(new JScrollPane(showGuests));

        inputFullNameL = new JLabel("Guest Full Name ");
        inputFullNameL.setFont(new Font("Serif", Font.PLAIN, 20));
        inputFullNameL.setForeground(Color.orange);
        south.add(inputFullNameL);

        inputFullName = new JTextField(30);
        inputFullName.setFont(new Font("Serif", Font.PLAIN, 20));
        inputFullName.setBackground(Color.orange);
        inputFullName.addActionListener(this);
        south.add(inputFullName);

        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.GREEN);
        west.add(messageLabel);

        enterGuestName = new JButton("Add");
        enterGuestName.setBackground(new Color(12,100, 255));
        enterGuestName.setEnabled(false);
        south.add(enterGuestName);
        enterGuestName.addActionListener(this);
        enterGuestName.setFocusable(false);

        removeGuest = new JButton("Remove");
        removeGuest.setBackground(new Color(12,100, 255));
        removeGuest.setEnabled(false);
        south.add(removeGuest);
        removeGuest.addActionListener(this);
        removeGuest.setFocusable(false);

        backFirst = new JButton("Back");
        backFirst.setFont(new Font("Serif", Font.PLAIN, 15));
        backFirst.setBackground(new Color(12,100, 255));
        backFirst.addActionListener(this);
        south.add(backFirst);
        backFirst.setFocusable(false);

        setVisible(true);
    }

    protected   void assignedTask(){
        beginning();
        south.setLayout(new FlowLayout(FlowLayout.TRAILING, 20,20));
        center.setLayout(new GridLayout(1,1, 20,0));

        eventTypeChoice = new JLabel("Assigned Tasks");
        eventTypeChoice.setForeground(Color.orange);
        eventTypeChoice.setFont(new Font("Serif", Font.PLAIN, 50));
        north.add(eventTypeChoice);

        taskedWork = new JTable();
        taskedWork.setBackground(Color.orange);
        taskedWork.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(new JScrollPane(taskedWork));

            showTable("select event_id ,event_name ,venue.name, event_date,guest, CONCAT(customer.first_name, ' '" +
                    ", customer.last_name) as customer \n,customer.`tellNo1` from staff join eventstaff on staff.id " +
                    "= eventstaff.staff_id join event on event.id = eventstaff.event_id\njoin venue on event.venue_id =" +
                    " venue.id Join customer on event.customer_id = customer.id " +
                    "where staff.id = " + staff.getId(), taskedWork);

        gotoStaffFirst = new JButton("Back");
        gotoStaffFirst.setFont(new Font("Serif", Font.PLAIN, 15));
        gotoStaffFirst.setBackground(new Color(12,100, 255));
        gotoStaffFirst.addActionListener(this);
        south.add(gotoStaffFirst);
        gotoStaffFirst.setFocusable(false);

        setVisible(true);
    }

    protected  void showBookedEvents(){
        beginning();

        center.setLayout(new GridLayout(2,1, 20, 10));

        eventTypeChoice = new JLabel("Events Information");
        eventTypeChoice.setForeground(Color.orange);
        eventTypeChoice.setFont(new Font("Serif", Font.PLAIN, 50));
        north.add(eventTypeChoice);

        eventInformation = new JTable();
        eventInformation.setFont(new Font("Serif", Font.PLAIN, 20));
        showTable("select e.id,event_name, event_date, type_name, name  from event e join venue v on e.venue_id = v.id" +
                " join eventType et on et.id = e.type_id where customer_id ="
                + customer.getId(), eventInformation);
        eventInformation.setBackground(Color.orange);
        center.add(new JScrollPane(eventInformation));

        organizerInfo = new JTable();
        organizerInfo.setBackground(Color.orange);
        organizerInfo.setFont(new Font("Serif", Font.PLAIN, 15));
        showTable("select concat(first_name, ' ', last_name) as Organizer , tellNo1, tellNo2 from\n" +
                "event e join eventStaff es on e.id = es.event_id join staff s on es.staff_id = s.id where " +
                "customer_id =" +customer.getId(), organizerInfo);
        organizerInfo.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(new JScrollPane(organizerInfo));

        backFirst = new JButton("Back");
        backFirst.setFont(new Font("Serif", Font.PLAIN, 15));
        backFirst.setBackground(new Color(12,100, 255));
        backFirst.addActionListener(this);
        south.add(backFirst);
        backFirst.setFocusable(false);

        setVisible(true);
    }

    protected   void bookEvent(){
        beginning();

        north.setLayout(new FlowLayout(FlowLayout.TRAILING, 300, 10));
        center.setLayout(new GridLayout(11,1, 0,10));
        center.setBorder(BorderFactory.createLineBorder(Color.orange, 10));

        eventTypeChoice = new JLabel("Fill The Form");
        eventTypeChoice.setForeground(Color.orange);
        eventTypeChoice.setFont(new Font("Serif", Font.PLAIN, 50));
        north.add(eventTypeChoice);

        current_date = new JLabel(date);
        current_date.setFont(new Font("Serif", Font.PLAIN, 15));
        current_date.setForeground(Color.orange);
        north.add(current_date);


        eventNameL = new JLabel("Event Name");
        eventNameL.setFont(new Font("Serif", Font.PLAIN, 25));
        center.add(eventNameL);

        eventNameT = new JTextField();
        eventNameT.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(eventNameT);
        eventNameT.requestFocus();
        eventNameT.addActionListener(this);//todo eventNameT to guestNo

        guestNoL = new JLabel("Guest No ");
        guestNoL.setFont(new Font("Serif", Font.PLAIN, 25));
        center.add(guestNoL);

        guestNo = new JTextField();
        guestNo.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(guestNo);
        guestNo.addActionListener(this);//todo guestNO to eventDate

        eventDateL = new JLabel("Event Date yyyy-MM-dd");
        eventDateL.setFont(new Font("Serif", Font.PLAIN, 25));
        center.add(eventDateL);

        eventDate = new JTextField();
        eventDate.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(eventDate);
        eventDate.addActionListener(this);//todo eventDate to startTime

        startTimeL = new JLabel("Start Time 00:00 pm/am format");
        startTimeL.setFont(new Font("Serif", Font.PLAIN, 25));
        center.add(startTimeL);

        startTime = new JTextField();
        startTime.setFont(new Font("Serif", Font.PLAIN, 20));
        startTime.addActionListener(this);//todo startTime to endTime
        center.add(startTime);

        endTimeL = new JLabel("End Time 00:00 am/pm format");
        endTimeL.setFont(new Font("Serif", Font.PLAIN, 25));
        center.add(endTimeL);

        endTime = new JTextField();
        endTime.addActionListener(this);//todo endTime to venueList and enable
        endTime.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(endTime);

        venueList  = new JComboBox<>();
        venueList.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(venueList);
        venueList.addActionListener(this);//todo venueList to showPrice

        try {
            ResultSet eventResult = giveQuery("SELECT CONCAT(id , \".\", name, \" max \", capacity, ' $', price)," +
                    " price, id from venue");
            venuePrice = new ArrayList<>();
            venueId = new ArrayList<>();
            while (eventResult.next()){
                venueList.addItem(eventResult.getString(1));
                venuePrice.add(eventResult.getDouble("price"));
                venueId.add(eventResult.getInt("id"));
            }
            System.out.println(venuePrice);
        } catch (SQLException e){
            e.printStackTrace();
        }

        finishBooking = new JButton("Enter");
        finishBooking.addActionListener(this);
        //todo connect the finish listener button
        finishBooking.setFont(new Font("Serif", Font.PLAIN, 15));
        finishBooking.setBackground(new Color(12,100, 255));
        south.setLayout(new FlowLayout(FlowLayout.TRAILING, 20, 10));
        south.add(finishBooking);
        finishBooking.setFocusable(false);

        showPriceL = new JLabel("Price");
        showPriceL.setForeground(Color.white);
        showPriceL.setFont(new Font("Serif", Font.PLAIN, 25));
        south.add(showPriceL);

        showPrice = new JTextField(20);
        showPrice.setFont(new Font("Serif", Font.BOLD, 20));
        south.add(showPrice);
        showPrice.addActionListener(this);//todo confirmBooking

        confirmBooking = new JButton("Confirm");
        //todo connect the confirm button
        confirmBooking.setFont(new Font("Serif", Font.PLAIN, 15));
        confirmBooking.setBackground(new Color(12,100, 255));
        confirmBooking.addActionListener(this);
        confirmBooking.setEnabled(false);
        south.add(confirmBooking);
        confirmBooking.setFocusable(false);

        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.GREEN);
        west.add(messageLabel);

        backToCreate = new JButton("Back");
        backToCreate.setFont(new Font("Serif", Font.PLAIN, 15));
        backToCreate.setBackground(new Color(12,100, 255));
        backToCreate.addActionListener(this);
        south.setLayout(new FlowLayout(FlowLayout.TRAILING));
        south.add(backToCreate);
        backToCreate.setFocusable(false);


        setVisible(true);
    }

    protected   void eventOptions(){
        beginning();

        center.setLayout(new GridLayout(3,1));

        eventTypeChoice = new JLabel("Choose Event Type");
        eventTypeChoice.setForeground(Color.orange);
        eventTypeChoice.setFont(new Font("Serif", Font.PLAIN, 50));
        north.add(eventTypeChoice);

        wedding = new JLabel("   1. Wedding");
        wedding.addMouseListener(this);
        wedding.setFont(new Font("Serif", Font.PLAIN, 40));
        wedding.setBorder(BorderFactory.createDashedBorder(Color.red, 1f, 3f, 1f, true));
        wedding.setBackground(Color.orange);
        wedding.addMouseListener(this);
        center.add(wedding);

        birthDay = new JLabel("   2. Birthday");
        birthDay.addMouseListener(this);
        birthDay.setFont(new Font("Serif", Font.PLAIN, 40));
        birthDay.setBorder(BorderFactory.createDashedBorder(Color.red, 1f, 3f, 1f, true));
        birthDay.setBackground(Color.orange);
        birthDay.addMouseListener(this);
        center.add(birthDay);


        graduation = new JLabel("  3. Graduation");
        graduation.addMouseListener(this);
        graduation.setBorder(BorderFactory.createDashedBorder(Color.red, 1f, 3f, 1f, true));
        graduation.setFont(new Font("Serif", Font.PLAIN, 40));
        graduation.setBackground(Color.orange);
        graduation.addMouseListener(this);
        center.add(graduation);

        backFirst = new JButton("Back");
        backFirst.addActionListener(this);
        backFirst.setFont(new Font("Serif", Font.PLAIN, 15));
        backFirst.setBackground(new Color(12,100, 255));
        south.setLayout(new FlowLayout(FlowLayout.TRAILING));
        south.add(backFirst);
        backFirst.setFocusable(false);

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signInStaff) {
            staffSignInGui();
        } else if (e.getSource() == signInCustomer) {
            signInGui();
        } else if (e.getSource() == signIn) {
            pressedSignIn();
        }else if (e.getSource() == staffSignIn) {
            pressedStaffSignIn();
        } else if (e.getSource() == signUp) {
            signUpGui();
        } else if(e.getSource() == signUpIn){
            pressedSignUp();
        } else if (e.getSource() == name) {
            signInPasswd.requestFocus();
        } else if (e.getSource() == backFirst) {
            userOption();
        } else if (e.getSource() == backToCreate) {
            eventOptions();
        } else if (e.getSource() == finishBooking) {
            pressedFinishBooking();
        } else if (e.getSource() == confirmBooking) {
            pressedConfirmBooking();
        } else if (e.getSource() == enterEventId) {
            pressedEnterEventId();
        } else if (e.getSource() == enterGuestName) {
            pressedAddGuestName();
        }else if (e.getSource() == gotoStaffFirst) {
            viewAssignedWork();
        } else if (e.getSource() == signInPasswd) {
            pressedSignIn();
        } else if (e.getSource() == inputEventId) {
            pressedEnterEventId();
        } else if (e.getSource() == inputFullName) {
            pressedAddGuestName();
        } else if (e.getSource() == staffName) {
            staffSignInPasswd.requestFocus();
        } else if (e.getSource() == staffSignInPasswd) {
            pressedStaffSignIn();
        } else if (e.getSource() == eventNameT) {
            guestNo.requestFocus();
        } else if (e.getSource() == guestNo) {
            eventDate.requestFocus();
        } else if (e.getSource() == eventDate) {
            startTime.requestFocus();
        } else if (e.getSource() == startTime) {
            endTime.requestFocus();
        } else if (e.getSource() == endTime) {
            venueList.requestFocus();
        } else if (e.getSource() == showPrice) {
            pressedConfirmBooking();
        } else if (e.getSource() == signUpName) {
            tellNoo1.requestFocus();
        } else if (e.getSource() == tellNoo1) {
            tellNoo2.requestFocus();
        } else if (e.getSource() == tellNoo2) {
            signUpPasswd.requestFocus();
        } else if (e.getSource() == signUpPasswd) {
            confirm.requestFocus();
        } else if (e.getSource() == confirm) {
            pressedSignUp();
        } else if (e.getSource() == about) {
            pressedAbout();
        } else if (e.getSource() == removeGuest) {
            pressedRemoveGuest();
        } else if (e.getSource() == updateProfile) {
            pressedStaffUpdate();
        } else if (e.getSource() == updateCustomerProfile) {
            pressedUpdateCustomerInfo();
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
        fullName = fullName.trim();
        ArrayList<String> separatedName = new ArrayList<>();
        for (int i = 0; i < fullName.length(); i++) {
            if (fullName.charAt(i) == ' ') {
                separatedName.add(fullName.substring(0, i).toUpperCase());
                separatedName.add(fullName.substring(i + 1).trim().toUpperCase());
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
        String trimmed = word.trim();

        if (trimmed.length() > 2)
            for (int i = 0; i < trimmed.length(); i++) {
                if (trimmed.charAt(i) == ' ') {
                    for (int j = 0; j < trimmed.substring(i).trim().length(); j++)
                        if (trimmed.substring(i).trim().charAt(j) == ' ') {
                            return false;
                        }
                    return true;
                }

            }

        return false;
    }
    protected boolean checkPasswordMatch(char[] password, char[] confirmPassword) {
        return new String(password).equals(new String(confirmPassword));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == create){
            eventOptions();
            System.out.println("create");
        } else if (e.getSource() == view) {
            showBookedEvents();
            System.out.println("view");
        } else if (e.getSource() == signOut) {
            signInGui();
        } else if (e.getSource() == addGuests) {
            addGuestsGui();
            System.out.println("guests");
        } else if (e.getSource() == wedding) {
            perPersonPrice = 500;
            event = new Event(dataBaseName, passWord);
            event.setTypeId(1);
            bookEvent();
        } else if (e.getSource() == birthDay) {
            perPersonPrice = 400;
            event = new Event(dataBaseName, passWord);
            event.setTypeId(2);
            bookEvent();
        }else if (e.getSource() == graduation) {
            perPersonPrice = 350;
            event = new Event(dataBaseName, passWord);
            event.setTypeId(3);
            bookEvent();
        } else if (e.getSource() == assignedWorks) {
            //todo create the gui
            assignedTask();
        } else if (e.getSource() == viewInfo) {
            //todo create the gue
            staffInformationView();
        } else if (e.getSource() == staffSignOut) {
            staffSignInGui();
        } else if (e.getSource() == userInfoView) {
            userInformationView();
        }
    }

    /**
     * this method compares if a given date is greater than a given date at least by 10 days
     * @param cDate current date in yyyy-MM-dd form
     * @param sDate the date to compare to the current date
     * @return true if it is greater else false
     */
    public boolean compareDate(String cDate, String sDate){
        boolean year = Integer.parseInt(sDate.substring(0,4)) > Integer.parseInt(cDate.substring(0,4));
        boolean yearE = Integer.parseInt(sDate.substring(0,4)) == Integer.parseInt(cDate.substring(0,4));
        boolean month = Integer.parseInt(sDate.substring(5,7)) > Integer.parseInt(cDate.substring(5,7));
        boolean monthE = Integer.parseInt(sDate.substring(5,7)) == Integer.parseInt(cDate.substring(5,7));
        boolean day = Integer.parseInt(sDate.substring(8)) >= Integer.parseInt(cDate.substring(8) + 10);
            if (year)
                return true;
            else if (yearE && month)
                return true;
            else if (yearE && monthE)
                return day;
            return false;
    }

    protected void pressedRemoveGuest(){
        String fullName = inputFullName.getText();
        if(!fullName.isBlank()){
            if(checkSpace(fullName)){
                    ResultSet resultSet ;
                    try {
                            String firstName, lastName;
                            firstName = separateName(fullName).getFirst();
                            lastName = separateName(fullName).getLast();
                            if (!guestList.contains(firstName + " " + lastName))
                                messageLabel.setText("No Guest by that name to remove");
                            else {
                                System.out.println(guestList);
                                    Guest newGuest = new Guest(firstName, lastName, dataBaseName,passWord);
                                    newGuest.getGuestId();
                                System.out.println(newGuest.getId());
                                    if(newGuest.getId() != 0) {
                                        EventGuest.removeEventGuest(givenEventId, newGuest.getId(), dataBaseName, passWord);
                                        showTable("select concat(first_name, ' ', last_name) as 'Guest Name' FROM event JOIN" +
                                                " eventGuests on event.id = event_id JOIN guest on guest_id = guest.id " +
                                                "WHERE customer_id = " + customer.getId() + " and event_id = " + givenEventId, showGuests);
                                        guestList.remove(firstName +" "+ lastName);
                                    } else messageLabel.setText("failed to remove guest");
                            }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
            }else messageLabel.setText("Full name is up to father name!!");
        }else messageLabel.setText("First Add To Full Name!!");
    }
    protected void pressedSignIn(){
        String fullName, fName, lName, userPassword, phoneNo1, phoneNo2, sex;
        int id;
        ResultSet resultSet;
        fullName = name.getText();
        System.out.println(fullName);
        if (checkSpace(fullName))
            if(signInPasswd.getPassword().length > 3) {
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
                        messageLabel.setText("No account by that name!!");
                        signInPasswd.setText("");
                    } else {
                        sex = resultSet.getString("sex");
                        phoneNo1 = resultSet.getString("tellNo1");
                        phoneNo2 = resultSet.getString("tellNo2");
                        id = resultSet.getInt("id");
                        customer = new Customer(fName, lName, phoneNo1, phoneNo2, dataBaseName, userPassword,passWord, sex);
                        customer.setId(id);
                        userOption();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else messageLabel.setText("Password is > 3!!");
        else messageLabel.setText("User Name is up to father name!");

    }

    public void pressedStaffSignIn(){
        String fullName, fName, lName, userPassword, phoneNo1, phoneNo2, dob, sex, appointedDate;
        int id, positionId, supervisorId;
        ResultSet resultSet;
        fullName = staffName.getText();
        System.out.println(fullName);
        if (checkSpace(fullName) )
            if(staffSignInPasswd.getPassword().length > 3) {
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
                        messageLabel.setText("Unknown Staff name or password");
                        staffSignInPasswd.setText("");
                    } else {
                        id = resultSet.getInt("id");
                        positionId = resultSet.getInt("position_id");
                        supervisorId = resultSet.getInt("supervisor_id");
                        dob = resultSet.getString("DOB");
                        sex = resultSet.getString("sex");
                        phoneNo1 = resultSet.getString("tellNo1");
                        phoneNo2 = resultSet.getString("tellNo2");
                        appointedDate = String.valueOf(resultSet.getDate("appointed_date"));
                        staff = new Staff(fName, lName,phoneNo1,phoneNo2,sex,dataBaseName,dob,passWord,appointedDate
                                ,userPassword,supervisorId,positionId);
                        staff.setId(id);
                        viewAssignedWork();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }else messageLabel.setText("Password is > 3!!");
        else messageLabel.setText("User Name is up to father name!");

    }

    public void pressedSignUp(){
        try {
            if (checkPasswordMatch(signUpPasswd.getPassword(), confirm.getPassword()))
                if (signUpPasswd.getPassword().length > 3)
                    if (checkSpace(signUpName.getText())) {
                        int tempTell1 = Integer.parseInt(tellNoo1.getText()),
                                tempTell2 = Integer.parseInt(tellNoo2.getText());
                        if ((tellNoo1.getText().length() > 9 && tellNoo2.getText().length() > 9)) {
                            String fullName = signUpName.getText();
                            String firstName = separateName(fullName).getFirst(),
                                    lastName = separateName(fullName).getLast(),
                                    tellNo1 = tellNoo1.getText(),
                                    tellNo2 = tellNoo2.getText(),
                                    sex;
                            if (male.isSelected())
                                sex = "M";
                            else sex = "F";
                            try {
                                int count = 0;
                              ResultSet  resultSet = Customer.getCustomer(firstName,lastName,
                                      String.valueOf(signUpPasswd.getPassword()), dataBaseName, passWord);
                                if (resultSet.next()) {
                                    messageLabel.setText("There is already an account by that name!!");
                                }else {
                                    customer = new Customer(firstName, lastName, tellNo1, tellNo2, dataBaseName,
                                            new String(signUpPasswd.getPassword()), passWord, sex);
                                    customer.add();
                                    messageLabel.setForeground(Color.green);
                                    messageLabel.setText("Successfully signedUp");
                                }
                            }catch(SQLException e1){
                                e1.printStackTrace();
                            }

                        } else messageLabel.setText("contact info should be correctly filled!");
                    } else {
                        messageLabel.setForeground(Color.red);
                        messageLabel.setText("Name is up to father name!!");
                    }
                else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("password must be > 3");
                }
            else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("confirm and password aren't equal");
            }
        }catch (NumberFormatException e1){
            messageLabel.setText("Contact info should be filled correctly");
        }
    }
    protected void pressedUpdateCustomerInfo(){
        if(checkSpace(getStaffFullNameInfo.getText())){
            if(staffPassword.getText().length() > 3){
                if(getStaffGender.getText().length() == 1 && (getStaffGender.getText().equalsIgnoreCase("M"))
                        || getStaffGender.getText().equalsIgnoreCase("F") ){
                    String firstName = separateName(getStaffFullNameInfo.getText()).getFirst();
                    String lastName = separateName(getStaffFullNameInfo.getText()).getLast();
                    String gender = getStaffGender.getText().toUpperCase();
                    String password = staffPassword.getText();
                    customer.setFirstName(firstName);
                    customer.setLastName(lastName);
                    customer.setSex(gender);
                    customer.setPwd(password);
                    customer.updateCustomer();
                    userInformationView();
                    messageLabel.setText("successfully updated");
                }else messageLabel.setText("gender isn't correctly formatted");
            }else messageLabel.setText("password should be > 3");

        }else messageLabel.setText("name includes father's name");
    }


    protected void pressedStaffUpdate(){
        if(checkSpace(getStaffFullNameInfo.getText())){
            if(staffPassword.getText().length() > 3){
                if(getStaffGender.getText().length() == 1 && (getStaffGender.getText().equalsIgnoreCase("M"))
               || getStaffGender.getText().equalsIgnoreCase("F") ){
                    String firstName = separateName(getStaffFullNameInfo.getText()).getFirst();
                    String lastName = separateName(getStaffFullNameInfo.getText()).getLast();
                    String gender = getStaffGender.getText().toUpperCase();
                    String password = staffPassword.getText();
                    staff.setFirstName(firstName);
                    staff.setLastName(lastName);
                    staff.setSex(gender);
                    staff.setPwd(password);
                    staff.updateStaff();
                    staffInformationView();
                    messageLabel.setText("successfully updated");
                }else messageLabel.setText("gender isn't correctly formatted");
            }else messageLabel.setText("password should be > 3");

        }else messageLabel.setText("name includes father's name");
    }

    public void pressedFinishBooking() {
        try {
            int eventDay = Integer.parseInt(eventDate.getText().substring(8));
            int eventMonth = Integer.parseInt(eventDate.getText().substring(5, 7));
            int eventYear = Integer.parseInt(eventDate.getText().substring(0, 4));
            int gustCheck = Integer.parseInt(guestNo.getText());
            System.out.println(eventDay);
            System.out.println(eventMonth);
            System.out.println(eventYear);
            if(eventMonth < 13){
                System.out.println(eventMonth);
            if (eventDay < 31) {
                if (gustCheck > 20) {
                    if (eventDate.getText().charAt(4) == '-' && eventDate.getText().charAt(7) == '-') {
                        if(compareDate(date, eventDate.getText())) {
                            if (!eventNameT.getText().isBlank()) {
                                if (!endTime.getText().isBlank()) {
                                    if (!startTime.getText().isBlank()) {
                                        int selected = venueList.getSelectedIndex();
                                        if (!(selected == -1)) {
                                            if (checkTime(endTime.getText()) && checkTime(startTime.getText())){
                                            double sPrice = (venuePrice.get(venueList.getSelectedIndex()) +
                                                    Integer.parseInt(guestNo.getText()) * perPersonPrice);
                                            String giveFormula = currency.format(venuePrice.get(venueList.getSelectedIndex())) + " + " +
                                                    "(" + currency.format(perPersonPrice) + " * " + guestNo.getText() + ")";
                                            messageLabel.setText(giveFormula);
                                            showPrice.setText(String.valueOf(currency.format(venuePrice.get(venueList.getSelectedIndex()) +
                                                    Integer.parseInt(guestNo.getText()) * perPersonPrice)));
                                            confirmBooking.setEnabled(true);
                                            } else messageLabel.setText("Write time in correct form");
                                        } else messageLabel.setText(" first Choose a Venue!!");
                                    } else messageLabel.setText("Start time can't be empty!!");
                                } else messageLabel.setText("End Time can't be empty!!");
                            } else messageLabel.setText("Event Name can't be empty!!");
                        } else messageLabel.setText("Event Date > current date + 10");
                    } else messageLabel.setText("Date format yyyy-MM-dd");
                } else messageLabel.setText("Guest No should be > 20");
            }else messageLabel.setText("Check the date");
        }else messageLabel.setText("Check Month");
        }catch (NumberFormatException e1){
            messageLabel.setText("No Guests can be only no");
        }
    }

    public void pressedConfirmBooking(){
        double givenMoney;
        double eventCost = (venuePrice.get(venueList.getSelectedIndex()) + Integer.parseInt(guestNo.getText()) * perPersonPrice);
        try {
            String money = showPrice.getText().trim();
            if(showPrice.getText().equals(currency.format(eventCost))){
                givenMoney = eventCost;
                System.out.println(showPrice.getText().equals(currency.format(eventCost)));
            } else if (showPrice.getText().isBlank()) {
                messageLabel.setText("Input Price!!");
                showPrice.setText(currency.format(event.getEventCost()));
                return;
            }else {
                if (money.charAt(0) == '$')
                    givenMoney = Double.parseDouble(money.substring(1));
                else
                    givenMoney = Double.parseDouble(money);
            }
            System.out.println("get here");
            if(givenMoney >= eventCost) {
                int staff_id = 0, dateCheck = 0;
                event.setEventName(eventNameT.getText().toUpperCase());
                event.setGuests(Integer.parseInt(guestNo.getText()));
                event.setEventDate(eventDate.getText());
                event.setStartTime(changeTime(startTime.getText()));
                System.out.println(changeTime(startTime.getText()));
                event.setEndTime(changeTime(endTime.getText()));
                event.setEventCost((venuePrice.get(venueList.getSelectedIndex()) + Integer.parseInt(guestNo.getText()) * perPersonPrice));
                event.setVenueId(venueId.get(venueList.getSelectedIndex()));
                event.setCustomerId(customer.getId());
                event.setId();
                System.out.println(event.getId());
                //todo check date
                try {
                    connect();
                    //should use a single quotation to notify the event_date is a date
                    ResultSet resultSet = giveQuery("select  count(*) as count from event where event_date = '" +
                            event.getEventDate() + "' and venue_id = " + event.getVenueId());
                    while (resultSet.next()) {
                        dateCheck = resultSet.getInt("count");
                        System.out.println(dateCheck);
                    }
                    conn.close();
                    if (dateCheck == 0) {
                        event.addEvent();
                        //todo add event id and staff id
                        try {
                            ResultSet eventResult = giveQuery("SELECT * from staff where position_id = " + event.getTypeId() +
                                    " ORDER BY event_work asc LIMIT 1");
                            while (eventResult.next()) {
                                staff_id = eventResult.getInt("id");
                                System.out.println(staff_id);
                            }
                            eventResult.close();
                            try {
                                connect();
                                pst = conn.prepareStatement("UPDATE staff SET event_work = event_work + 1 WHERE id = ?");
                                pst.setInt(1, staff_id);
                                pst.executeUpdate();
                                conn.close();
                            } catch (SQLException e2) {
                                e2.printStackTrace();
                                System.out.println("staff updated work + 1");
                            }
                            //todo add staff_id and event_id to eventStaff
                            try {
                                connect();
                                pst = conn.prepareStatement("INSERT INTO eventStaff(event_id, staff_id) VALUES (?, ?)");
                                pst.setInt(1, event.getId());
                                pst.setInt(2, staff_id);
                                pst.executeUpdate();
                                System.out.println("Recorded is added to eventStaff");
                                conn.close();
                                messageLabel.setText("you have  Successfully Booked ");
                                showPrice.setText(currency.format(givenMoney - event.getEventCost()) + " return");
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        //todo change event_work by one

                    } else messageLabel.setText("The Date is Booked!!");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }else {
                showPrice.setText(currency.format(eventCost - givenMoney) + " less");
            }
        }catch (NumberFormatException e1){
            messageLabel.setText("Input Only numbers");
        }
    }
    public void pressedEnterEventId(){
        //todo check weather the
        try {
            if (!inputEventId.getText().isBlank()) {
                int eventThere = 0;
                givenEventId = Integer.parseInt(inputEventId.getText());
                try {
                    connect();
                    ResultSet resultSet = giveQuery("Select count(*) as count from event where customer_id = " +
                            customer.getId() + " and id = " + givenEventId);
                    resultSet.next();
                    eventThere = resultSet.getInt("count");
                    System.out.println(eventThere);
                    if (eventThere != 0) {
                        resultSet.close();
                        try {
                            resultSet = giveQuery("Select * from event where customer_id = " +
                                    customer.getId() + " and id = " + givenEventId);
                            resultSet.next();
                            maxGuests = resultSet.getInt("guest");
                            messageLabel.setText(String.valueOf(maxGuests));
                            try {
                                resultSet = giveQuery("select count(*) as count from eventGuests where event_id =" + givenEventId);
                                resultSet.next();
                                guestCount = resultSet.getInt("count");
                                showTable("select concat(first_name, ' ', last_name) as 'Guest Name' FROM event JOIN" +
                                        " eventGuests on event.id = event_id JOIN guest on guest_id = guest.id " +
                                        "WHERE customer_id = " + customer.getId() + " and event_id = " + givenEventId, showGuests);
                                if (guestCount < maxGuests) {
                                    guestList = new ArrayList<>();
                                    try {
                                        resultSet = giveQuery("select concat(first_name, ' ', last_name) as 'Guest Name'" +
                                                " FROM event JOIN eventGuests on event.id = event_id JOIN guest on guest_id = guest.id " +
                                                "WHERE customer_id = " + customer.getId() + " and event_id = " + givenEventId);
                                        while (resultSet.next()) {
                                            guestList.add(resultSet.getString("Guest Name"));
                                        }
                                        System.out.println(guestList);
                                    } catch (SQLException e1) {
                                        e1.printStackTrace();
                                        System.out.println("failed to add to the guestList!");
                                    }
                                    enterGuestName.setEnabled(true);
                                    removeGuest.setEnabled(true);
                                } else messageLabel.setText("Guests are fully added");
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                                System.out.println("can't show count of eventGuests eid, cid");
                            }
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    } else messageLabel.setText("No event by that id!!");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } else messageLabel.setText("enter number first!!");
        }catch(NumberFormatException e1){
            messageLabel.setText("Input number not letter");
        }
    }
    public void pressedAddGuestName(){
        String fullName = inputFullName.getText();
        if(!fullName.isBlank()){
            if(checkSpace(fullName)){
                try {
                    ResultSet resultSet = giveQuery("select count(*) as count from guest");
                    resultSet.next();
                    try {
                        if (guestCount < maxGuests) {
                            String firstName, lastName;
                            firstName = separateName(fullName).getFirst();
                            lastName = separateName(fullName).getLast();
                            if (guestList.contains(firstName + " " + lastName))
                                messageLabel.setText("Already added this guest!!");
                            else {
                                Guest newGuest = new Guest(firstName, lastName, dataBaseName, passWord);
                                newGuest.add();
                                guestList.add(newGuest.getFirstName() +" "+ newGuest.getLastName());
                                System.out.println(guestList);

                                try {
                                    if(newGuest.getId() == 0) {
                                        resultSet = giveQuery("select id from guest order by id desc limit 1");
                                        resultSet.next();
                                        newGuest.setId(resultSet.getInt("id"));
                                    }
                                    EventGuest.addEventGuest(givenEventId, newGuest.getId(),dataBaseName,passWord);
                                    showTable("select concat(first_name, ' ', last_name) as 'Guest Name' FROM event JOIN" +
                                            " eventGuests on event.id = event_id JOIN guest on guest_id = guest.id " +
                                            "WHERE customer_id = "+ customer.getId() + " and event_id = " + givenEventId
                                            , showGuests);
                                }catch (SQLException e1){
                                    e1.printStackTrace();
                                    System.out.println("can't get id!!");
                                }
                            }
                        } else messageLabel.setText("Guests are fully added");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }catch (SQLException e2){
                    e2.printStackTrace();
                }
            }else messageLabel.setText("Full name is up to father name!!");
        }else messageLabel.setText("First Add To Full Name!!");
    }

    public boolean checkTime(String time) {
        if (time.trim().length() == 8){
            try {
                time = time.trim();
                int hr = Integer.parseInt(time.substring(0, 2));
                int min = Integer.parseInt(time.substring(3, 5));
            if(time.charAt(2) == ':')
              if(hr <= 12) {
                  if (min < 60){
                      if (time.charAt(5) == ' ') {
                          if (time.substring(6).equalsIgnoreCase("am") ||
                                  time.substring(6).equalsIgnoreCase("pm"))
                              return true;
                      }
                }
                }
            } catch (NumberFormatException e1) {
                return false;
            }
            return false;
    }
       else return false;
    }

    public String changeTime(String time){
      time= time.trim();
        if (time.substring(6).equalsIgnoreCase("PM")){
            int hr = Integer.parseInt(time.substring(0,2)) + 12;
            return hr + time.substring(2, 5) + ":00";
        }
        return time.substring(0,5) + ":00";
    }
    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
