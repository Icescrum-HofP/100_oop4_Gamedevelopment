package screen;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField1;
    private String name;
    private boolean online = true;
    private volatile boolean start = false;
    private JRadioButton rdbtnNewRadioButton;
    private JRadioButton rdbtnNewRadioButton_1;
    private JButton btnNewButton;
    private JButton btnNewButton1;
    private JLabel lblNewLabel1;
    private String ip;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
//                try {
                Screen frame = new Screen(false);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Screen(boolean first) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(100, 100);

        setBounds(100, 100, 320, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setBounds(23, 28, 156, 20);
        contentPane.add(textField);
        textField.setColumns(10);

        textField1 = new JTextField();
        textField1.setBounds(23, 68, 156, 20);
        contentPane.add(textField1);
        textField1.setColumns(10);
        textField1.setText("62.75.216.145");

        String reset = "Start";

        if (!first) {
            reset = "Restart";
        }

        btnNewButton = new JButton(reset);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                name = textField.getText();
                ip = textField1.getText();
                if (!name.equals("")) {
                    if (ip.equals("")) {
                        ip = "localhost";
                    }
                    start = true;
//                    System.out.println("Start");
                }
            }
        });
        btnNewButton.setBounds(200, 227, 90, 23);
        contentPane.add(btnNewButton);

        btnNewButton1 = new JButton("Exit");
        btnNewButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnNewButton1.setBounds(25, 227, 90, 23);
        contentPane.add(btnNewButton1);

        JLabel lblNewLabel = new JLabel("Name:");
        lblNewLabel.setBounds(23, 11, 46, 14);
        contentPane.add(lblNewLabel);

        lblNewLabel1 = new JLabel("IP:");
        lblNewLabel1.setBounds(23, 51, 46, 14);
        contentPane.add(lblNewLabel1);


        rdbtnNewRadioButton = new JRadioButton("Offline");
        rdbtnNewRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rdbtnNewRadioButton.setSelected(true);
                online = false;
                rdbtnNewRadioButton_1.setSelected(false);
                lblNewLabel1.setVisible(false);
                textField1.setVisible(false);
            }
        });
        rdbtnNewRadioButton.setBounds(23, 105, 109, 23);
        contentPane.add(rdbtnNewRadioButton);

        rdbtnNewRadioButton_1 = new JRadioButton("Online");
        rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rdbtnNewRadioButton_1.setSelected(true);
                rdbtnNewRadioButton.setSelected(false);
                lblNewLabel1.setVisible(true);
                textField1.setVisible(true);
                online = true;
            }
        });
        rdbtnNewRadioButton_1.setBounds(23, 131, 109, 23);
        rdbtnNewRadioButton_1.setSelected(true);
        contentPane.add(rdbtnNewRadioButton_1);

        setVisible(true);

    }

    public void setOnline(boolean online_) {

        if (online_) {
            rdbtnNewRadioButton_1.setSelected(true);
            rdbtnNewRadioButton.setSelected(false);
            lblNewLabel1.setVisible(true);
            textField1.setVisible(true);
            online = true;
        } else {
            rdbtnNewRadioButton.setSelected(true);
            online = false;
            rdbtnNewRadioButton_1.setSelected(false);
            lblNewLabel1.setVisible(false);
            textField1.setVisible(false);
        }

    }

    public void setName(String name_) {
        textField.setText(name_);
    }

    public void setIp(String ip_) {
        if (ip_.equals("")) {
            textField.setText(ip_);
        }
    }

    public boolean isStart() {
        return start;
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean isOnline() {
        return online;
    }

    public String getIp() {
        return ip;
    }
}