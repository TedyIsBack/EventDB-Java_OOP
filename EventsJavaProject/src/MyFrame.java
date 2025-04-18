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

public class MyFrame extends JFrame {

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

    JLabel eventNameL = new JLabel("Име на събитие:");
    JLabel eventLocationL = new JLabel("Място:"); // all
    JLabel eventOrganizerL = new JLabel("Организатор:"); // only first name
    JLabel eventDurationL = new JLabel("Продължителност:");
    JLabel eventDateL = new JLabel("Дата:");
    JLabel ticketPriceL = new JLabel("Цена на билет:");
    JLabel eventDescriptionL = new JLabel("Описание:");

    JTextField eventNameTF = new JTextField();
    //JTextField eventLocationTF = new JTextField();// all
    //JTextField eventOrganizerTF = new JTextField(); // only first name
    JComboBox<String> eventLocationCombo = new JComboBox<>();
    JComboBox<String> eventOrgCombo = new JComboBox<>();
    JTextField eventDurationTF = new JTextField();

    JTextField eventDateTF = new JTextField();

    JTextField ticketPriceTF = new JTextField();

    JTextField eventDescriptionTF = new JTextField();


    JButton addEventBTN = new JButton("добавяне");
    JButton deleteEventBTN = new JButton("изтриване");
    JButton updateEventBTN = new JButton("редактиране");
    JButton searchEventBTN = new JButton("търсне");
    JButton refreshEventBTN = new JButton("обнови");

    JTable eventTable = new JTable();
    JScrollPane eventScrollTable = new JScrollPane(eventTable);


    //spravka elements

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
       // eventUpPanel.add(eventLocationTF);
        eventUpPanel.add(eventOrganizerL);
        //eventUpPanel.add(eventOrganizerTF);
        eventUpPanel.add(eventDurationL);
        eventUpPanel.add(eventDurationTF);
        eventUpPanel.add(eventDateL);
        eventUpPanel.add(eventDateTF);
        eventUpPanel.add(ticketPriceL);
        eventUpPanel.add(ticketPriceTF);
        eventUpPanel.add(eventDescriptionL);
        eventUpPanel.add(eventDescriptionTF);

        eventNameTF.setPreferredSize(new Dimension(200, 30));
        //eventLocationTF.setPreferredSize(new Dimension(200, 30));
       // eventOrganizerTF.setPreferredSize(new Dimension(200, 30));
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
        //deleteEventBTN.addActionListener(new DeleteEventAction());
        //updateEventBTN.addActionListener(new UpdateEventAction());
        //searchEventBTN.addActionListener(new SearchEventAction());
        //refreshEventBTN.addActionListener(new RefreshEventAction());

        //downPanel ----------------------
        eventScrollTable.setPreferredSize(new Dimension(800, 150));
        eventDownPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 50, 5));
        eventDownPanel.add(eventScrollTable, BorderLayout.CENTER);

        //eventTable.addMouseListener(new MouseEventAction());
        refreshTable("event", eventTable);
        //endregion

        this.add(mainTabbedPane);
        this.setVisible(true);
    }

    /*public void refreshOrgTable() {
        conn = DBConnection.getConnection();
        String sql = "select * from organizer";

        try {
            state = conn.prepareStatement(sql);
            resultSet = state.executeQuery();
            orgTable.setModel(new MyTModel(resultSet));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/

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

    public void clearOrgForm() {
        organizerNameTF.setText("");
        emailTF.setText("");
    }

    public void clearLocForm() {
        locationNameTF.setText("");
        cityTF.setText("");
    }

    public void clearEventsForm() {
        locationNameTF.setText("");
        cityTF.setText("");
    }

    //region Organizer Actions
    class AddOrgAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "insert into organizer (organizer_name ,contact_email) values(?,?)";

            try {
                state = conn.prepareStatement(sql);
                state.setString(1, organizerNameTF.getText());
                state.setString(2, emailTF.getText());
                state.execute();
                refreshTable("organizer", orgTable);
                clearOrgForm();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
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
                clearOrgForm();
                organizer_id = -1;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
    }

    class UpdateOrgAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (organizer_id > 0) {
                String sql = "update organizer set organizer_name=?, contact_email=? where organizer_id =?";

                try {
                    state = conn.prepareStatement(sql);
                    state.setString(1, organizerNameTF.getText());
                    state.setString(2, emailTF.getText());
                    state.setInt(3, organizer_id);
                    state.execute();
                    refreshTable("organizer", orgTable);

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                organizerNameTF.setText("");
                emailTF.setText("");
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
                state = conn.prepareStatement(sql);
                state.setString(1, locationNameTF.getText());
                state.setString(2, cityTF.getText());
                state.execute();
                refreshTable("location", locationTable);
                clearLocForm();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
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
                location_id = -1;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }


        }
    }

    class UpdateLocationAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (location_id > 0) {
                String sql = "update location set location_name=?, city=? where location_id =?";

                try {
                    state = conn.prepareStatement(sql);
                    state.setString(1, locationNameTF.getText());
                    state.setString(2, cityTF.getText());
                    state.setInt(3, location_id);
                    state.execute();
                    refreshTable("location", locationTable);

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                locationNameTF.setText("");
                cityTF.setText("");
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

    //region Even Actions

    class AddEventAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "insert into event (event_name,location_id,organizer_id,duration,event_date,ticket_price,description) values (?,?,?,?,?,?,?)";
            try {
                state = conn.prepareStatement(sql);
                state.setString(1, eventNameTF.getText());
                state.setInt(2, location_id);
                state.setInt(3, organizer_id);
                state.setString(4, eventDurationTF.getText());
                state.setString(5, eventDateTF.getText());
                state.setString(6, ticketPriceTF.getText());
                state.setString(7, eventDescriptionTF.getText());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    //endregion
}

