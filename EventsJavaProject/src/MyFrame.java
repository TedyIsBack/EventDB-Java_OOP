import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MyFrame extends JFrame {

    //important: in event table location will be city and organizer will be only fullName. In
    //region Properties
    Connection conn = null;
    PreparedStatement state = null;
    ResultSet resultSet = null;
    //int id = -1; //this will be id in the "справка" tab
    int organizer_id = -1;
    int location_id = -1;
    int event_id = -1;

    JTabbedPane mainTabbedPane = new JTabbedPane();

    //JPanel contentPane = new JPanel();
    JPanel organizerPanel = new JPanel(new BorderLayout());
    JPanel locationPanel = new JPanel(new BorderLayout());
    JPanel eventsPanel = new JPanel(new BorderLayout());
    JPanel sprPanel = new JPanel(new BorderLayout());

    //organizer elements
    JPanel orgUpPanel = new JPanel();
    JPanel orgMidPanel = new JPanel();
    JPanel orgDownPanel = new JPanel();

    JLabel organizerNameL = new JLabel("Цяло име:");
    JLabel emailL = new JLabel("Имейл:");

    JTextField organizerNameTF = new JTextField();
    JTextField emailTF = new JTextField();

    JButton addOrgBTN = new JButton("добавяне");
    JButton deleteOrgBTN = new JButton("изтриване");
    JButton updateOrgBTN = new JButton("редактиране");
    JButton searchOrgBTN = new JButton("търсне");
    JButton refreshOrgBTN = new JButton("обнови");

    JTable orgTable = new JTable();
    JScrollPane orgScrollTable = new JScrollPane(orgTable);

    //location elements
    JPanel locationUpPanel = new JPanel();
    JPanel locationMidPanel = new JPanel();
    JPanel locationDownPanel = new JPanel();

    JLabel locationNameL = new JLabel("Име на локация:");
    JLabel cityL = new JLabel("Град:");

    JTextField locationNameTF = new JTextField();
    JTextField cityTF = new JTextField();

    JButton addLocationBTN = new JButton("добавяне");
    JButton deleteLocationBTN = new JButton("изтриване");
    JButton updateLocationBTN = new JButton("редактиране");
    JButton searchLocationBTN = new JButton("търсне");
    JButton refreshLocationBTN = new JButton("обнови");

    JTable locationTable = new JTable();
    JScrollPane locationScrollTable = new JScrollPane(locationTable);

    //events elements
    JPanel eventUpPanel = new JPanel();
    JPanel eventMidPanel = new JPanel();
    JPanel eventDownPanel = new JPanel();

    //event labels
    JLabel eventNameL = new JLabel("Име на събитие:");
    JLabel eventLocationL = new JLabel("Място:");
    JLabel eventOrganizerL = new JLabel("Организатор:");
    JLabel eventDurationL = new JLabel("Продължителност (в часове):");
    JLabel eventDateL = new JLabel("Дата (YYYY-MM-DD):");
    JLabel ticketPriceL = new JLabel("Цена на билет:");
    JLabel eventDescriptionL = new JLabel("Описание:");

    //event text fields
    JTextField eventNameTF = new JTextField();
    JComboBox<String> locationCombo = new JComboBox<>();
    JComboBox<String> orgCombo = new JComboBox<>();
    JTextField eventDurationTF = new JTextField();
    JTextField eventDateTF = new JTextField();
    JTextField ticketPriceTF = new JTextField();
    JTextField eventDescriptionTF = new JTextField();


    JButton addEventBTN = new JButton("добавяне");
    JButton deleteEventBTN = new JButton("изтриване");
    JButton updateEventBTN = new JButton("редактиране");
    JButton searchEventBTN = new JButton("търсне по име ");
    JButton refreshEventBTN = new JButton("обнови");

    JTable eventTable = new JTable();
    JScrollPane eventScrollTable = new JScrollPane(eventTable);

    //spravka elements

    JPanel sprUpPanel = new JPanel();
    JPanel sprMidPanel = new JPanel();
    JPanel sprDownPanel = new JPanel();

    JTextField sprLocationTF = new JTextField();
    JTextField sprOrgTF = new JTextField();
    JLabel sprLocationL = new JLabel("Търси по град:");
    JLabel sprOrganizerL = new JLabel("Търси по име на организатор:");

    JButton searchSprBTN = new JButton("Търси");
    JButton refreshSprBTN = new JButton("Изчисти");

    JTable sprTable = new JTable();
    JScrollPane sprScrollTable = new JScrollPane(sprTable);


    public MyFrame() {

        // this.setContentPane(contentPane);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setLayout(new GridLayout(3, 1));

        //region Organizer Tab

        mainTabbedPane.addTab("Организатори", organizerPanel);
        organizerPanel.add(orgUpPanel, BorderLayout.NORTH);
        organizerPanel.add(orgMidPanel, BorderLayout.CENTER);
        organizerPanel.add(orgDownPanel, BorderLayout.SOUTH);
        //organizerPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 50, 10));

        //orgUpPanel ----------------------
        orgUpPanel.setLayout(new GridLayout(2, 2));
        orgUpPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 0, 10));
        orgUpPanel.add(organizerNameL);
        orgUpPanel.add(organizerNameTF);
        orgUpPanel.add(emailL);
        orgUpPanel.add(emailTF);

        organizerNameTF.setPreferredSize(new Dimension(200, 30));
        emailTF.setPreferredSize(new Dimension(200, 30));

        //midPanel ----------------------
        orgMidPanel.add(addOrgBTN);
        orgMidPanel.add(deleteOrgBTN);
        orgMidPanel.add(updateOrgBTN);
        orgMidPanel.add(searchOrgBTN);
        orgMidPanel.add(refreshOrgBTN);

        orgMidPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));

        addOrgBTN.addActionListener(new AddOrgAction());
        deleteOrgBTN.addActionListener(new DeleteOrgAction());
        updateOrgBTN.addActionListener(new UpdateOrgAction());
        searchOrgBTN.addActionListener(new SearchOrgAction());
        refreshOrgBTN.addActionListener(new RefreshOrgAction());

        //downPanel ----------------------
        orgScrollTable.setPreferredSize(new Dimension(350, 150));
        //orgDownPanel.add(orgScrollTable);
        orgDownPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 50, 5));
        orgDownPanel.add(orgScrollTable, BorderLayout.CENTER);

        orgTable.addMouseListener(new MouseOrgAction());
        refreshTable("organizer", orgTable);
        //endregion

        //region Location Tab
        mainTabbedPane.addTab("Локации", locationPanel);

        locationPanel.add(locationUpPanel, BorderLayout.NORTH);
        locationPanel.add(locationMidPanel, BorderLayout.CENTER);
        locationPanel.add(locationDownPanel, BorderLayout.SOUTH);


        //UpPanel ----------------------
        locationUpPanel.setLayout(new GridLayout(2, 2));
        locationUpPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 0, 10));
        locationUpPanel.add(locationNameL);
        locationUpPanel.add(locationNameTF);
        locationUpPanel.add(cityL);
        locationUpPanel.add(cityTF);

        locationNameTF.setPreferredSize(new Dimension(200, 30));
        cityTF.setPreferredSize(new Dimension(200, 30));

        //midPanel ----------------------
        locationMidPanel.add(addLocationBTN);
        locationMidPanel.add(deleteLocationBTN);
        locationMidPanel.add(updateLocationBTN);
        locationMidPanel.add(searchLocationBTN);
        locationMidPanel.add(refreshLocationBTN);

        locationMidPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));

        addLocationBTN.addActionListener(new AddLocationAction());
        deleteLocationBTN.addActionListener(new DeleteLocationAction());
        updateLocationBTN.addActionListener(new UpdateLocationAction());
        searchLocationBTN.addActionListener(new SearchLocationAction());
        refreshLocationBTN.addActionListener(new RefreshLocationAction());

        //downPanel ----------------------
        locationScrollTable.setPreferredSize(new Dimension(350, 150));
        locationDownPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 50, 5));
        locationDownPanel.add(locationScrollTable, BorderLayout.CENTER);

        locationTable.addMouseListener(new MouseLocAction());
        refreshTable("location", locationTable);
        //endregion

        //region Events Tab
        mainTabbedPane.addTab("Събития", eventsPanel);

        eventsPanel.add(eventUpPanel, BorderLayout.NORTH);
        eventsPanel.add(eventMidPanel, BorderLayout.CENTER);
        eventsPanel.add(eventDownPanel, BorderLayout.SOUTH);


        //UpPanel ----------------------
        eventUpPanel.setLayout(new GridLayout(7, 2));
        eventUpPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 0, 10));
        eventUpPanel.add(eventNameL);
        eventUpPanel.add(eventNameTF);
        eventUpPanel.add(eventLocationL);
        eventUpPanel.add(locationCombo);
        eventUpPanel.add(eventOrganizerL);
        eventUpPanel.add(orgCombo);
        eventUpPanel.add(eventDurationL);
        eventUpPanel.add(eventDurationTF);
        eventUpPanel.add(eventDateL);
        eventUpPanel.add(eventDateTF);
        eventUpPanel.add(ticketPriceL);
        eventUpPanel.add(ticketPriceTF);
        eventUpPanel.add(eventDescriptionL);
        eventUpPanel.add(eventDescriptionTF);

        eventNameTF.setPreferredSize(new Dimension(200, 30));

        eventDurationTF.setPreferredSize(new Dimension(200, 30));
        eventDateTF.setPreferredSize(new Dimension(200, 30));
        ticketPriceTF.setPreferredSize(new Dimension(200, 30));
        eventDescriptionTF.setPreferredSize(new Dimension(200, 30));

        //midPanel ----------------------
        eventMidPanel.add(addEventBTN);
        eventMidPanel.add(deleteEventBTN);
        eventMidPanel.add(updateEventBTN);
        eventMidPanel.add(searchEventBTN);
        eventMidPanel.add(refreshEventBTN);

        eventMidPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));

        addEventBTN.addActionListener(new AddEventAction());
        deleteEventBTN.addActionListener(new DeleteEventAction());
        updateEventBTN.addActionListener(new UpdateEventAction());
        searchEventBTN.addActionListener(new SearchEventAction());
        refreshEventBTN.addActionListener(new RefreshEventAction());

        //downPanel ----------------------
        eventScrollTable.setPreferredSize(new Dimension(800, 150));
        eventDownPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 50, 5));
        eventDownPanel.add(eventScrollTable, BorderLayout.CENTER);

        eventTable.addMouseListener(new MouseEventAction());

        //endregion

        //region Spravka Tab
        mainTabbedPane.addTab("Справка", sprPanel);

        sprPanel.add(sprUpPanel, BorderLayout.NORTH);
        sprPanel.add(sprMidPanel, BorderLayout.CENTER);
        sprPanel.add(sprDownPanel, BorderLayout.SOUTH);
        sprUpPanel.setLayout(new GridLayout(4, 1));
        sprUpPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 0, 10));

        sprUpPanel.add(sprOrganizerL);
        sprUpPanel.add(sprOrgTF);
        //sprUpPanel.add(orgCombo);
        sprUpPanel.add(sprLocationL);
        sprUpPanel.add(sprLocationTF);
        //sprUpPanel.add(locationCombo);

        sprMidPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));

        sprMidPanel.add(searchSprBTN);
        sprMidPanel.add(refreshSprBTN);

        searchSprBTN.addActionListener(new SearchSprAction());
        refreshSprBTN.addActionListener(new RefreshSprAction());

        sprScrollTable.setPreferredSize(new Dimension(800, 150));
        sprDownPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 50, 5));
        sprDownPanel.add(sprScrollTable, BorderLayout.CENTER);

        //endregion
        loadLocationCombo();
        loadOrganizerCombo();

        refreshEventTable();
        refreshSprTable();

        refreshTable("organizer", orgTable);
        refreshTable("location", locationTable);

        this.add(mainTabbedPane);
        this.setVisible(true);
    }

    public void refreshTable(String name, JTable table) {
        conn = DBConnection.getConnection();
        String str = "select * from " + name;
        try {
            state = conn.prepareStatement(str);
            resultSet = state.executeQuery();
            table.setModel(new MyTModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshEventTable() {
        conn = DBConnection.getConnection();
        String sql = "SELECT E.EVENT_ID, E.EVENT_NAME , L.CITY, L.LOCATION_NAME , E.LOCATION_ID, O.ORGANIZER_NAME, O.CONTACT_EMAIL,E.ORGANIZER_ID, E.DURATION,E.EVENT_DATE , E.TICKET_PRICE , E.DESCRIPTION " +
                "FROM EVENT E, LOCATION L, ORGANIZER O " +
                "WHERE E.LOCATION_ID = L.LOCATION_ID " +
                "AND E.ORGANIZER_ID = O.ORGANIZER_ID";
        try {
            state = conn.prepareStatement(sql);
            resultSet = state.executeQuery();
            eventTable.setModel(new MyTModel(resultSet));

            eventTable.getColumnModel().getColumn(3).setMinWidth(0);
            eventTable.getColumnModel().getColumn(3).setMaxWidth(0);
            eventTable.getColumnModel().getColumn(3).setWidth(0);
            eventTable.getColumnModel().getColumn(4).setMinWidth(0);
            eventTable.getColumnModel().getColumn(4).setMaxWidth(0);
            eventTable.getColumnModel().getColumn(4).setWidth(0);

            eventTable.getColumnModel().getColumn(6).setMinWidth(0);
            eventTable.getColumnModel().getColumn(6).setMaxWidth(0);
            eventTable.getColumnModel().getColumn(6).setWidth(0);
            eventTable.getColumnModel().getColumn(7).setMinWidth(0);
            eventTable.getColumnModel().getColumn(7).setMaxWidth(0);
            eventTable.getColumnModel().getColumn(7).setWidth(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void refreshSprTable() {
        conn = DBConnection.getConnection();
        String sql = "SELECT E.EVENT_ID, E.EVENT_NAME , L.LOCATION_NAME, L.CITY , E.LOCATION_ID, O.ORGANIZER_NAME, O.CONTACT_EMAIL," + "E.ORGANIZER_ID, E.DURATION, E.EVENT_DATE , E.TICKET_PRICE , E.DESCRIPTION " + "FROM EVENT E " + "JOIN LOCATION L ON E.LOCATION_ID = L.LOCATION_ID " + "JOIN ORGANIZER O ON E.ORGANIZER_ID = O.ORGANIZER_ID";
        try {
            state = conn.prepareStatement(sql);
            resultSet = state.executeQuery();

            sprTable.setModel(new MyTModel(resultSet));

            sprTable.getColumnModel().getColumn(4).setMinWidth(0);
            sprTable.getColumnModel().getColumn(4).setMaxWidth(0);
            sprTable.getColumnModel().getColumn(4).setWidth(0);

            sprTable.getColumnModel().getColumn(7).setMinWidth(0);
            sprTable.getColumnModel().getColumn(7).setMaxWidth(0);
            sprTable.getColumnModel().getColumn(7).setWidth(0);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void clearOrgForm() {
        organizerNameTF.setText("");
        emailTF.setText("");
    }

    public void clearLocForm() {
        locationNameTF.setText("");
        cityTF.setText("");
    }

    public void clearEventsForm() {
        eventNameTF.setText("");
        eventDateTF.setText("");
        ticketPriceTF.setText("");
        eventDescriptionTF.setText("");
        eventDurationTF.setText("");
    }

    public void loadLocationCombo() {
        locationCombo.removeAllItems();
        location_id = -1;
        conn = DBConnection.getConnection();
        String sql = "select location_id,location_name,city from location";
        String item = "";
        try {
            state = conn.prepareStatement(sql);
            resultSet = state.executeQuery();
            if (resultSet.next()) {
                location_id = Integer.parseInt(resultSet.getObject(1).toString());
                do {
                    item = resultSet.getObject(1).toString() + ". " + resultSet.getObject(2).toString() + ", " + resultSet.getObject(3).toString();
                    locationCombo.addItem(item);
                } while (resultSet.next());
            }
            location_id = -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadOrganizerCombo() {
        organizer_id = -1;
        orgCombo.removeAllItems();
        conn = DBConnection.getConnection();
        String item = "";
        String sql = "select organizer_id,organizer_name,contact_email from organizer";
        try {
            state = conn.prepareStatement(sql);
            resultSet = state.executeQuery();
            if (resultSet.next()) {
                organizer_id = Integer.parseInt(resultSet.getObject(1).toString());
                do {
                    item = resultSet.getObject(1).toString() + ". " + resultSet.getObject(2).toString() + " (" + resultSet.getObject(3).toString() + ")";
                    orgCombo.addItem(item);
                } while (resultSet.next());
            }
            organizer_id = -1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkFields(JTextField[] fields) {
        boolean allValid = true;

        for (JTextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                field.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                allValid = false;
            } else {
                field.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
            }
        }

        return allValid;
    }

    public void clearFields(JTextField[] fields) {
        for (JTextField field : fields) {
            field.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        }
    }

    //region Organizer Actions
    class AddOrgAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "insert into organizer (organizer_name ,contact_email) values(?,?)";

            try {
                if (!checkFields(new JTextField[]{organizerNameTF, emailTF})) return;

                state = conn.prepareStatement(sql);
                state.setString(1, organizerNameTF.getText());
                state.setString(2, emailTF.getText());
                state.execute();
                refreshTable("organizer", orgTable);
                loadOrganizerCombo();
                clearOrgForm();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            clearFields(new JTextField[]{organizerNameTF, emailTF});
        }
    }

    class DeleteOrgAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "delete from organizer where organizer_id=?";
            try {
                state = conn.prepareStatement(sql);
                state.setInt(1, organizer_id);
                state.execute();
                refreshTable("organizer", orgTable);
                loadOrganizerCombo();
                clearOrgForm();
                organizer_id = -1;
            } catch (SQLException ex) {
                //23503 е грешка, която се връща, когато има нарушение с външния ключ
                if (ex.getSQLState().equals("23503")) {
                    JOptionPane.showMessageDialog(null, "Не може да изтриете този елемент. Вече е свързан със съществуващо събитие.", "Грешка", JOptionPane.ERROR_MESSAGE);
                } else {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Възнинка неочаквана грешка", "Грешка", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    class UpdateOrgAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (organizer_id > 0) {
                String sql = "update organizer set organizer_name=?, contact_email=? where organizer_id =?";

                try {
                    if (!checkFields(new JTextField[]{organizerNameTF, emailTF})) return;
                    state = conn.prepareStatement(sql);
                    state.setString(1, organizerNameTF.getText());
                    state.setString(2, emailTF.getText());
                    state.setInt(3, organizer_id);
                    state.execute();
                    refreshTable("organizer", orgTable);
                    loadOrganizerCombo();
                    refreshEventTable();
                    refreshSprTable();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                organizerNameTF.setText("");
                emailTF.setText("");
                clearFields(new JTextField[]{organizerNameTF, emailTF});
            }
        }
    }

    class SearchOrgAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "select * from organizer where organizer_name=?";
            try {
                state = conn.prepareStatement(sql);
                state.setString(1, organizerNameTF.getText());
                resultSet = state.executeQuery();
                orgTable.setModel(new MyTModel(resultSet));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    class RefreshOrgAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            refreshTable("organizer", orgTable);
            clearOrgForm();
        }
    }

    class MouseOrgAction implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int row = orgTable.getSelectedRow();
            organizer_id = Integer.parseInt(orgTable.getValueAt(row, 0).toString());

            organizerNameTF.setText(orgTable.getValueAt(row, 1).toString());
            emailTF.setText(orgTable.getValueAt(row, 2).toString());
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

    //endregion

    //region Location Actions
    class AddLocationAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "insert into location (location_name,city) values(?,?)";
            try {
                if (!checkFields(new JTextField[]{locationNameTF, cityTF})) return;
                state = conn.prepareStatement(sql);
                state.setString(1, locationNameTF.getText());
                state.setString(2, cityTF.getText());
                state.execute();
                refreshTable("location", locationTable);
                loadLocationCombo();
                clearLocForm();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            clearFields(new JTextField[]{locationNameTF, cityTF});
        }
    }

    class DeleteLocationAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "delete from location where location_id=?";
            try {
                state = conn.prepareStatement(sql);
                state.setInt(1, location_id);
                state.execute();
                refreshTable("location", locationTable);
                clearLocForm();
                loadLocationCombo();
                location_id = -1;
            } catch (SQLException ex) {
                //23503 е грешка, която се връща, когато има нарушение с външния ключ
                if (ex.getSQLState().equals("23503")) {
                    JOptionPane.showMessageDialog(null, "Не може да изтриете този елемент. Вече е свързан със съществуващо събитие.", "Грешка", JOptionPane.ERROR_MESSAGE);
                } else {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Възнинка неочаквана грешка", "Грешка", JOptionPane.ERROR_MESSAGE);
                }
            }


        }
    }

    class UpdateLocationAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (location_id > 0) {
                String sql = "update location set location_name=?, city=? where location_id =?";

                try {
                    if (!checkFields(new JTextField[]{locationNameTF, cityTF})) return;

                    state = conn.prepareStatement(sql);
                    state.setString(1, locationNameTF.getText());
                    state.setString(2, cityTF.getText());
                    state.setInt(3, location_id);
                    state.execute();
                    refreshTable("location", locationTable);
                    loadLocationCombo();
                    refreshEventTable();
                    refreshSprTable();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                locationNameTF.setText("");
                cityTF.setText("");
                clearFields(new JTextField[]{locationNameTF, cityTF});
            }
        }
    }

    //search by city
    class SearchLocationAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "select * from location where city=?";
            try {
                state = conn.prepareStatement(sql);
                state.setString(1, cityTF.getText());
                resultSet = state.executeQuery();
                locationTable.setModel(new MyTModel(resultSet));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    class RefreshLocationAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            refreshTable("location", locationTable);
            clearLocForm();
        }
    }

    class MouseLocAction implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int row = locationTable.getSelectedRow();
            location_id = Integer.parseInt(locationTable.getValueAt(row, 0).toString());

            locationNameTF.setText(locationTable.getValueAt(row, 1).toString());
            cityTF.setText(locationTable.getValueAt(row, 2).toString());
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


    //endregion

    //region Event Actions

    class AddEventAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "insert into event (event_name,location_id,organizer_id,duration, event_date,ticket_price,description) values (?,?,?,?,?,?,?)";
            try {
                if (!checkFields(new JTextField[]{eventNameTF, eventDurationTF, eventDateTF})) return;

                String selectedLoc = locationCombo.getSelectedItem().toString();
                location_id = Integer.parseInt(selectedLoc.split("\\.")[0]);

                String selectedOrg = orgCombo.getSelectedItem().toString();
                organizer_id = Integer.parseInt(selectedOrg.split("\\.")[0]);

                state = conn.prepareStatement(sql);
                state.setString(1, eventNameTF.getText());
                state.setInt(2, location_id);
                state.setInt(3, organizer_id);
                state.setInt(4, Integer.parseInt(eventDurationTF.getText()));
                LocalDate eventDate = LocalDate.parse(eventDateTF.getText());
                state.setDate(5, java.sql.Date.valueOf(eventDate));
                double ticketPrice = ticketPriceTF.getText().isEmpty() ? 0.00 : Double.parseDouble(ticketPriceTF.getText());
                state.setDouble(6, ticketPrice);
                state.setString(7, eventDescriptionTF.getText());

                state.execute();
                refreshEventTable();
                refreshSprTable();
                clearEventsForm();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            clearFields(new JTextField[]{eventNameTF, eventDurationTF, eventDateTF});
        }
    }

    class DeleteEventAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "delete from event where event_id=?";
            try {
                state = conn.prepareStatement(sql);
                state.setInt(1, event_id);
                state.execute();
                refreshEventTable();
                refreshSprTable();
                clearEventsForm();
                event_id = -1;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    class UpdateEventAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (location_id > 0 && organizer_id > 0) {
                if (!checkFields(new JTextField[]{eventNameTF, eventDurationTF, eventDateTF})) return;

                // взимаме ID-тата от избраните ComboBox стойности
                String selectedLoc = locationCombo.getSelectedItem().toString();
                location_id = Integer.parseInt(selectedLoc.split("\\.")[0]);

                String selectedOrg = orgCombo.getSelectedItem().toString();
                organizer_id = Integer.parseInt(selectedOrg.split("\\.")[0]);


                String sql = "update event set event_name=?, location_id=?, organizer_id=?, duration=?,event_date=?,ticket_price=?,description=? where event_id=?";

                try {
                    state = conn.prepareStatement(sql);

                    state.setString(1, eventNameTF.getText());
                    state.setInt(2, location_id);
                    state.setInt(3, organizer_id);
                    state.setInt(4, Integer.parseInt(eventDurationTF.getText()));
                    LocalDate eventDate = LocalDate.parse(eventDateTF.getText());
                    state.setDate(5, java.sql.Date.valueOf(eventDate));
                    double ticketPrice = ticketPriceTF.getText().isEmpty() ? 0.00 : Double.parseDouble(ticketPriceTF.getText());
                    state.setDouble(6, ticketPrice);
                    state.setString(7, eventDescriptionTF.getText());
                    state.setInt(8, event_id);
                    state.execute();
                    refreshEventTable();
                    refreshSprTable();

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                eventNameTF.setText("");
                eventDurationTF.setText("");
                eventDateTF.setText("");
                ticketPriceTF.setText("");
                eventDescriptionTF.setText("");
                clearFields(new JTextField[]{eventNameTF, eventDurationTF, eventDateTF});
            }
        }
    }

    class RefreshEventAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            refreshEventTable();
            clearEventsForm();
        }
    }

    class SearchEventAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();

            // "SELECT E.EVENT_ID, E.EVENT_NAME , L.CITY, L.LOCATION_NAME , E.LOCATION_ID, O.ORGANIZER_NAME, O.CONTACT_EMAIL,E.ORGANIZER_ID, E.DURATION,E.EVENT_DATE , E.TICKET_PRICE , E.DESCRIPTION " +
            //                "FROM EVENT E, LOCATION L, ORGANIZER O " +
            //                "WHERE E.LOCATION_ID = L.LOCATION_ID " +
            //                "AND E.ORGANIZER_ID = O.ORGANIZER_ID";
            String sql = "SELECT e.event_id, e.event_name, l.city, l.location_name, l.location_id ,o.organizer_name, o.contact_email, o.organizer_id, e.duration, e.event_date, e.ticket_price, e.description " +
                    "FROM event e " +
                    "JOIN location l ON e.location_id = l.location_id " +
                    "JOIN organizer o ON e.organizer_id = o.organizer_id " +
                    "WHERE e.event_name like ?";
            try {
                state = conn.prepareStatement(sql);
                state.setString(1, "%" + eventNameTF.getText() + "%");
                resultSet = state.executeQuery();
                eventTable.setModel(new MyTModel(resultSet));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    class MouseEventAction implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int row = eventTable.getSelectedRow();
            //SELECT e.event_id, e.event_name, l.location_name, l.location_id ,o.organizer_name,o.organizer_id,
            // e.duration, e.event_date, e.ticket_price, e.description " +
            //                    "FROM event e " +
            //                    "JOIN location l ON e.location_id = l.location_id " +
            //                    "JOIN organizer o ON e.organizer_id = o.organizer_id " +
            //                    "WHERE UPPER(e.event_name) LIKE UPPER(?)";


            // "SELECT E.EVENT_ID, E.EVENT_NAME , L.CITY, L.LOCATION_NAME , E.LOCATION_ID, O.ORGANIZER_NAME,
            // O.CONTACT_EMAIL,E.ORGANIZER_ID, E.DURATION,E.EVENT_DATE , E.TICKET_PRICE , E.DESCRIPTION " +
            //                "FROM EVENT E, LOCATION L, ORGANIZER O " +
            //                "WHERE E.LOCATION_ID = L.LOCATION_ID " +
            //                "AND E.ORGANIZER_ID = O.ORGANIZER_ID";
            event_id = Integer.parseInt(eventTable.getValueAt(row, 0).toString());

            eventNameTF.setText(eventTable.getValueAt(row, 1).toString());

            String city = eventTable.getValueAt(row, 2).toString();
            String locationName = eventTable.getValueAt(row, 3).toString();
            location_id = Integer.parseInt(eventTable.getValueAt(row, 4).toString());
            locationCombo.setSelectedItem(location_id + ". " + locationName + ", " + city);

            organizer_id = Integer.parseInt(eventTable.getValueAt(row, 7).toString());
            String orgName = eventTable.getValueAt(row, 5).toString();
            String orgEmail = eventTable.getValueAt(row, 6).toString();
            orgCombo.setSelectedItem(organizer_id + ". " + orgName + " (" + orgEmail + ")");

            eventDurationTF.setText(eventTable.getValueAt(row, 8).toString());
            eventDateTF.setText(eventTable.getValueAt(row, 9).toString());
            ticketPriceTF.setText(eventTable.getValueAt(row, 10).toString());
            eventDescriptionTF.setText(eventTable.getValueAt(row, 11).toString());

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
    //endregion

    class SearchSprAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (!sprOrgTF.getText().isEmpty() || !sprLocationTF.getText().isEmpty()) {
                conn = DBConnection.getConnection();
                String sql = "SELECT E.EVENT_ID, E.EVENT_NAME , L.LOCATION_NAME, L.CITY , E.LOCATION_ID, O.ORGANIZER_NAME, O.CONTACT_EMAIL," + "E.ORGANIZER_ID, E.DURATION, E.EVENT_DATE , E.TICKET_PRICE , E.DESCRIPTION " + "FROM EVENT E " + "JOIN LOCATION L ON E.LOCATION_ID = L.LOCATION_ID " + "JOIN ORGANIZER O ON E.ORGANIZER_ID = O.ORGANIZER_ID " + "WHERE L.CITY LIKE ?" + "AND O.ORGANIZER_NAME LIKE ?";
                try {

                    state = conn.prepareStatement(sql);
                    if (!sprLocationTF.getText().isEmpty()) state.setString(1, "%" + sprLocationTF.getText() + "%");
                    else state.setString(1, "%");

                    if (!sprOrgTF.getText().isEmpty()) state.setString(2, "%" + sprOrgTF.getText() + "%");
                    else state.setString(2, "%");

                    resultSet = state.executeQuery();

                    sprTable.setModel(new MyTModel(resultSet));

                    sprTable.getColumnModel().getColumn(4).setMinWidth(0);
                    sprTable.getColumnModel().getColumn(4).setMaxWidth(0);
                    sprTable.getColumnModel().getColumn(4).setWidth(0);

                    sprTable.getColumnModel().getColumn(7).setMinWidth(0);
                    sprTable.getColumnModel().getColumn(7).setMaxWidth(0);
                    sprTable.getColumnModel().getColumn(7).setWidth(0);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
    }

    class RefreshSprAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            refreshSprTable();
            sprLocationTF.setText("");
            sprOrgTF.setText("");
        }
    }
}