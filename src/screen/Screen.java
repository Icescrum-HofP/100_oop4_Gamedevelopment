package screen;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private String name;
    private boolean online = true;
    private volatile boolean start = false;
    private JRadioButton rdbtnNewRadioButton;
    private JRadioButton rdbtnNewRadioButton_1;
    private JButton btnNewButton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
//                try {
                    Screen frame = new Screen();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Screen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(100, 100, 320, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setBounds(23, 28, 156, 20);
        contentPane.add(textField);
        textField.setColumns(10);

        btnNewButton = new JButton("Start");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                name = textField.getText();
                if (!name.equals("")) {
//                    System.out.println(name);
                    start = true;

//                    System.out.println("Start");
                }
            }
        });
        btnNewButton.setBounds(181, 227, 116, 23);
        contentPane.add(btnNewButton);

        JLabel lblNewLabel = new JLabel("Name:");
        lblNewLabel.setBounds(23, 11, 46, 14);
        contentPane.add(lblNewLabel);

        rdbtnNewRadioButton = new JRadioButton("Offline");
        rdbtnNewRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rdbtnNewRadioButton.setSelected(true);
                online = false;
                rdbtnNewRadioButton_1.setSelected(false);
            }
        });
        rdbtnNewRadioButton.setBounds(23, 85, 109, 23);
        contentPane.add(rdbtnNewRadioButton);

        rdbtnNewRadioButton_1 = new JRadioButton("Online");
        rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rdbtnNewRadioButton_1.setSelected(true);
                rdbtnNewRadioButton.setSelected(false);
                online = true;
            }
        });
        rdbtnNewRadioButton_1.setBounds(23, 111, 109, 23);
        rdbtnNewRadioButton_1.setSelected(true);
        contentPane.add(rdbtnNewRadioButton_1);

        setVisible(true);

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
}