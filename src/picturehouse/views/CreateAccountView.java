/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package picturehouse.views;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.validation.ValidationException;
import picturehouse.controllers.CustomerController;
import picturehouse.models.Customer;

/**
 *
 * @author sevabaskin
 */
public class CreateAccountView extends JFrame {
    
    
    private JTextField customerNameField;
    private JTextField passwordField;
    private JTextField creditCardField;
    private JButton submitButton;
    
    public CreateAccountView() {
        super("CreateAccountView");
        setSize(300, 330);
        
        setTitle("Create an account");  // avoid repetition with titleLabel, use another title,
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set up the main frame
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        
        JPanel middlePanel = new JPanel();
        container.add(middlePanel, BorderLayout.CENTER);
        setContentPane(container);

        JLabel titleLabel = new JLabel("Create an account");         
        titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        middlePanel.add(titleLabel);
        
        
        middlePanel.setLayout(new GridLayout(0,1));
        middlePanel.setBorder(new EmptyBorder(50, 50, 50, 50) );
        
        middlePanel.add(new JLabel("Customer Name"));
        customerNameField = new JTextField();
        middlePanel.add(customerNameField);
        
        middlePanel.add(new JLabel("Password"));
        passwordField = new JTextField();
        middlePanel.add(passwordField);
        
        middlePanel.add(new JLabel("Credit Card Number"));
        creditCardField = new JTextField();
        middlePanel.add(creditCardField);
        
        submitButton = new JButton("Create Account");
        middlePanel.add(submitButton);
        
        // pack();
        setLocationRelativeTo(null);    // center
        setResizable( false );
        setVisible(true);
        
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // FIXME: can't move Base.open to controller, cuz it will ruin the tests. Remove before stuff from tests
                submitUser();
            }
        });
    }
    
    private void submitUser() {
        try {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/picturehouse_development", "testuser", "testuserpassword");
            CustomerController controller = new CustomerController();
            controller.create(customerNameField.getText(), passwordField.getText(), creditCardField.getText());
            Base.close();
        } catch (ValidationException e) {
            Map<String, String> errors = e.errors();
            String errorMsgs = "You've got the following errors:\n";
            for (Map.Entry<String, String> entry : errors.entrySet()) {
                errorMsgs = errorMsgs + entry.getValue() + "\n";
            }

            JOptionPane.showMessageDialog(this, errorMsgs, "Validation Errors", JOptionPane.WARNING_MESSAGE);
            Base.close();
        }
        
        
    }
    
    public static void main(String[] args) {
        
        // Better code org: http://docs.oracle.com/javase/tutorial/uiswing/examples/layout/GridBagLayoutDemoProject/src/layout/GridBagLayoutDemo.java
        
        // Base.openTransaction();
        new CreateAccountView();
        // Base.rollbackTransaction();
		
        
    }
    
}