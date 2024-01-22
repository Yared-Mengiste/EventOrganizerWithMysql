import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Main extends MySqlConnector implements ActionListener, KeyListener , MouseListener {

    protected JLabel userOptionTitle, create, view, addGuests,signOut, showPriceL;
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
    protected JMenuItem signInStaff, signInCustomer;
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
    protected ArrayList<String> guestList;
    protected NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);
    protected int givenEventId = 0, maxGuests , guestCount, lastGuestId = 0;

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

        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.RED);
        constraint = frameConstraint(0, 9, 2, 1, 10);
        signInContainer.add(messageLabel, constraint);
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
        signUpName.setText("userName fatherName");
        signUpName.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,2, 2, 1, 10);
        signInContainer.add(signUpName, constraint);

        tellNo1L = new JLabel("  Phone 1");
        tellNo1L.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,3, 1, 1, 10);
        signInContainer.add(tellNo1L, constraint);

        tellNoo1 = new JTextField(25);
        tellNoo1.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,4, 2, 1, 10);
        signInContainer.add(tellNoo1, constraint);

        tellNo2L = new JLabel("  Phone 2");
        tellNo2L.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,5, 1, 1, 10);
        signInContainer.add(tellNo2L, constraint);

        tellNoo2 = new JTextField(25);
        tellNoo2.setFont(new Font("Arial", Font.BOLD, 15));
        constraint = frameConstraint(0,6, 2, 1, 10);
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
    public  void userOption(){
        beginning();

        center.setLayout(new GridLayout(4,1));

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

        addGuests = new JLabel("  3. Add Guests");
        addGuests.setBorder(BorderFactory.createDashedBorder(Color.red, 1f, 3f, 1f, true));
        addGuests.setFont(new Font("Serif", Font.PLAIN, 40));
        addGuests.setBackground(Color.orange);
        addGuests.addMouseListener(this);
        center.add(addGuests);


        signOut = new JLabel("  4. SignOut");
        signOut.setBorder(BorderFactory.createDashedBorder(Color.red, 1f, 3f, 1f, true));
        signOut.setFont(new Font("Serif", Font.PLAIN, 40));
        signOut.setBackground(Color.orange);
        signOut.addMouseListener(this);
        center.add(signOut);

        setVisible(true);
    }

    public void addGuestsGui(){
        beginning();
        center.setLayout(new GridLayout(1,1, 20,10));
        north.setLayout(new FlowLayout(FlowLayout.TRAILING, 30, 10));

        eventTypeChoice = new JLabel("Add Guests");
        eventTypeChoice.setForeground(Color.orange);
        eventTypeChoice.setFont(new Font("Serif", Font.PLAIN, 50));
        north.add(eventTypeChoice);

        inputEventIdL = new JLabel("Event Id ");
        inputEventIdL.setFont(new Font("Serif", Font.PLAIN, 20));
        inputEventIdL.setForeground(Color.orange);
        north.add(inputEventIdL);

        inputEventId = new JTextField(20);
        inputEventId.setFont(new Font("Serif", Font.PLAIN, 20));
        inputEventId.setBackground(Color.orange);
        north.add(inputEventId);

        enterEventId = new JButton("enter");
        enterEventId.setBackground(new Color(12,100, 255));
        north.add(enterEventId);
        enterEventId.addActionListener(this);

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
        south.add(inputFullName);

        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.GREEN);
        west.add(messageLabel);

        enterGuestName = new JButton("Add");
        enterGuestName.setBackground(new Color(12,100, 255));
        enterGuestName.setEnabled(false);
        south.add(enterGuestName);
        enterGuestName.addActionListener(this);

        backFirst = new JButton("Back");
        backFirst.setFont(new Font("Serif", Font.PLAIN, 15));
        backFirst.setBackground(new Color(12,100, 255));
        backFirst.addActionListener(this);
        south.add(backFirst);
        backFirst.setFocusable(false);

        setVisible(true);
    }

    public  void showBookedEvents(){
        beginning();

        center.setLayout(new GridLayout(2,1));

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

    public  void bookEvent(){
        beginning();

        center.setLayout(new GridLayout(11,1));

        eventTypeChoice = new JLabel("Fill The Form");
        eventTypeChoice.setForeground(Color.orange);
        eventTypeChoice.setFont(new Font("Serif", Font.PLAIN, 50));
        north.add(eventTypeChoice);

        eventNameL = new JLabel("Event Name");
        eventNameL.setFont(new Font("Serif", Font.PLAIN, 25));
        center.add(eventNameL);

        eventNameT = new JTextField();
        eventNameT.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(eventNameT);

        guestNoL = new JLabel("Guest No ");
        guestNoL.setFont(new Font("Serif", Font.PLAIN, 25));
        center.add(guestNoL);

        guestNo = new JTextField();
        guestNo.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(guestNo);

        eventDateL = new JLabel("Event Date yyyy-MM-dd");
        eventDateL.setFont(new Font("Serif", Font.PLAIN, 25));
        center.add(eventDateL);

        eventDate = new JTextField();
        eventDate.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(eventDate);

        startTimeL = new JLabel("Start Time 00:00 pm/am format");
        startTimeL.setFont(new Font("Serif", Font.PLAIN, 25));
        center.add(startTimeL);

        startTime = new JTextField();
        startTime.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(startTime);

        endTimeL = new JLabel("End Time 00:00 am/pm format");
        endTimeL.setFont(new Font("Serif", Font.PLAIN, 25));
        center.add(endTimeL);

        endTime = new JTextField();
        endTime.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(endTime);

        venueList  = new JComboBox<>();
        venueList.setFont(new Font("Serif", Font.PLAIN, 20));
        center.add(venueList);

        try {
            ResultSet eventResult = giveQuery("SELECT CONCAT(id , \".\", name, \" max \", capacity, ' $', price), price, id from venue");
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

    public  void eventOptions(){
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
                        customer = new Customer(fName, lName, phoneNo1, phoneNo2, dataBaseName, passWord, userPassword, sex);
                        customer.setId(id);
                        userOption();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else messageLabel.setText("Password is > 3!!");
            else messageLabel.setText("User Name includes father name!");

        }else if (e.getSource() == staffSignIn) {
            String fullName, fName, lName, userPassword, phoneNo1, phoneNo2, dob, sex;
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
                        staffName.setText("Incorrect Customer name or password");
                        staffSignInPasswd.setText("");
                    } else {
                        id = resultSet.getInt("id");
                        positionId = resultSet.getInt("position_id");
                        supervisorId = resultSet.getInt("supervisor_id");
                        dob = resultSet.getString("DOB");
                        sex = resultSet.getString("sex");
                        phoneNo1 = resultSet.getString("tellNo1");
                        phoneNo2 = resultSet.getString("tellNo2");
                        staff = new Staff(fName, lName,phoneNo1,phoneNo2,sex,dataBaseName,dob,passWord,userPassword
                                ,supervisorId,positionId);
                        staff.setId(id);
                        staffName.setText(staff.getDOB());
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }else messageLabel.setText("Password is > 3!!");
            else messageLabel.setText("User Name includes father name!");

        } else if (e.getSource() == signUp) {
            signUpGui();
        } else if(e.getSource() == signUpIn){
            if(checkPasswordMatch(signUpPasswd.getPassword() , confirm.getPassword()))
                    if (signUpPasswd.getPassword().length > 3)
                        if(checkSpace(signUpName.getText())){
                String fullName = signUpName.getText();
                String firstName = separateName(fullName).getFirst(),
                 lastName = separateName(fullName).getLast(),
                 tellNo1 = tellNoo1.getText(),
                tellNo2 = tellNoo2.getText(),
                sex;
                if (male.isSelected())
                    sex = "M";
                else sex = "F";
                customer = new Customer(firstName, lastName,tellNo1,tellNo2,dataBaseName,new String(signUpPasswd.getPassword()),
                        passWord, sex);
                customer.add();
                messageLabel.setForeground(Color.green);
                messageLabel.setText("works up to this");

            }
            else{
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Input full name correctly!!");
                        }
            else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("password must be > 3");
                    }
            else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("confirm and password aren't equal");
            }
        } else if (e.getSource() == name) {
            signInPasswd.requestFocus();
        } else if (e.getSource() == backFirst) {
            userOption();
        } else if (e.getSource() == backToCreate) {
            eventOptions();
        } else if (e.getSource() == finishBooking) {
            try {
                int gustCheck = Integer.parseInt(guestNo.getText());
                if (gustCheck > 20 ) {
                    if(eventDate.getText().charAt(4) == '-' && eventDate.getText().charAt(7) == '-') {
                        if (!eventNameT.getText().isBlank()) {
                            if (!endTime.getText().isBlank()) {
                                if (!startTime.getText().isBlank()) {
                                    int selected = venueList.getSelectedIndex();
                                    if (!(selected == -1)) {
                                        double sPrice = (venuePrice.get(venueList.getSelectedIndex()) +
                                                Integer.parseInt(guestNo.getText()) * perPersonPrice);
                                        String giveFormula = currency.format(venuePrice.get(venueList.getSelectedIndex())) + " + " +
                                                "(" + currency.format(perPersonPrice) + " * " + guestNo.getText() + ")";
                                        messageLabel.setText(giveFormula);
                                        showPrice.setText(String.valueOf(currency.format(venuePrice.get(venueList.getSelectedIndex()) +
                                                Integer.parseInt(guestNo.getText()) * perPersonPrice)));
                                        confirmBooking.setEnabled(true);
                                    } else messageLabel.setText(" first Choose a Venue!!");
                                } else messageLabel.setText("Start time can't be empty!!");
                            } else messageLabel.setText("End Time can't be empty!!");
                        } else messageLabel.setText("Event Name can't be empty!!");
                    }else messageLabel.setText("Date format yyyy-MM-dd");
                } else messageLabel.setText("Guest No should be > 20");
            }catch (NumberFormatException e1){
                messageLabel.setText("No Guests can be only no");
            }
        } else if (e.getSource() == confirmBooking) {
            int staff_id = 0, dateCheck = 0;
            event.setEventName(eventNameT.getText().toUpperCase());
            event.setGuests(Integer.parseInt(guestNo.getText()));
            event.setEventDate(eventDate.getText());
            event.setStartTime(startTime.getText());
            event.setEndTime(endTime.getText());
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
                                " ORDER BY event_work DESC LIMIT 1");
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
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    //todo change event_work by one
                    try {
                        connect();
                        pst = conn.prepareStatement("update staff set event_work = event_work + 1 where staff_id = "
                                + staff_id);
                        conn.close();
                    } catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                }else messageLabel.setText("The Date is Booked!!");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        } else if (e.getSource() == enterEventId) {
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
        } else if (e.getSource() == enterGuestName) {
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
                                        resultSet = giveQuery("select id from guest order by id desc limit 1");
                                                resultSet.next();
                                                newGuest.setId(resultSet.getInt("id"));
                                        EventGuest.addEventGuest(givenEventId, newGuest.getId(),dataBaseName,passWord);
                                        showTable("select concat(first_name, ' ', last_name) as 'Guest Name' FROM event JOIN" +
                                                " eventGuests on event.id = event_id JOIN guest on guest_id = guest.id " +
                                                "WHERE customer_id = "+ customer.getId() + " and event_id = " + givenEventId, showGuests);
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
                }else messageLabel.setText("Input Full Name correctly!!");
            }else messageLabel.setText("First Add To Full Name!!");
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
        }
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
