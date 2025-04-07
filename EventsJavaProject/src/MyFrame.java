import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyFrame extends JFrame {

    Connection conn = null;
    PreparedStatement state = null;
    ResultSet resultSet = null;
    int id = -1;//set unavaliable value

    JPanel upPanel = new JPanel();
    JPanel midPanel = new JPanel();
    JPanel downPanel = new JPanel();

    JLabel fullNameL = new JLabel("Цяло име:");
    JLabel emailL = new JLabel("Имейл:");


    JTextField fullNameTF = new JTextField();
    JTextField emailTF = new JTextField();

    JButton addBTN = new JButton("добавяне");
    JButton deleteBTN = new JButton("изтриване");
    JButton updateBTN = new JButton("редактиране");


    JTable table = new JTable();
    JScrollPane scrollTable = new JScrollPane(table);

    public MyFrame() {
        this.setSize(400, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3, 1));

        //upPanel ----------------------
        upPanel.setLayout(new GridLayout(3, 2));
        upPanel.add(fullNameL);
        upPanel.add(fullNameTF);
        upPanel.add(emailL);
        upPanel.add(emailTF);

        this.add(upPanel);
        //midPanel ----------------------
        midPanel.add(addBTN);
        midPanel.add(deleteBTN);
        midPanel.add(updateBTN);

        this.add(midPanel);

        addBTN.addActionListener(new AddAction());
        //downPanel ----------------------
        scrollTable.setPreferredSize(new Dimension(350, 150));
        downPanel.add(scrollTable);
        this.add(downPanel);
        this.setVisible(true);
    }

    class AddAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "insert into users (full_name,email) values(?,?)";

            try {
                state = conn.prepareStatement(sql);
                state.setString(1,fullNameTF.getText());
                state.setString(2,emailTF.getText());

                state.execute();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}

