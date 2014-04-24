/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package picturehouse.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author sevabaskin
 */
public class BrowseMoviesView extends JFrame {
    
    public BrowseMoviesView() {
    super("CreateAccountView");
    setSize(800, 600);
    setTitle("Browse Movies");  // avoid repetition with titleLabel, use another title,
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Container contentPane = getContentPane();
    contentPane.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    // start
    JPanel leftPanel = new JPanel();
//    leftPanel.setBackground(Color.blue); //temp
    c.gridx = 0;
    c.gridy = 0;
    c.gridwidth = 1;
    c.gridheight = 2;
    c.fill = GridBagConstraints.BOTH;
    c.weightx = 0.3;
    c.weighty = 1;
    contentPane.add(leftPanel, c);
    
    
    JPanel rightPanel = new JPanel();
    rightPanel.setBackground(Color.green); //temp
    c.gridx = 2;
    c.gridy = 0;
    c.gridwidth = 2;
    c.gridheight = 2;
    c.fill = GridBagConstraints.BOTH;
    c.weightx = 0.7;
    c.weighty = 1;
    contentPane.add(rightPanel, c);


    leftPanel.setLayout(new GridBagLayout());
    
    JPanel sortPanel = new JPanel();
    sortPanel.setBorder(new TitledBorder("Sort By"));
    JToggleButton lastWeekButton = new JToggleButton("Last Week");
    JToggleButton nextWeekButton = new JToggleButton("Next Week");
    sortPanel.add(lastWeekButton);
    sortPanel.add(nextWeekButton);
    c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.NORTH;
    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 1.0;
    c.weighty = 0.0;
    leftPanel.add(sortPanel, c);
    
    
    JPanel movieListPanel = new JPanel();
    movieListPanel.setBorder(new TitledBorder("Select a Movie"));
    String	listData[] = { "Item 1", "Item 2", "Item 3", "Item 4" };
    JList listbox = new JList( listData );
    movieListPanel.add(listbox);
    c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;        
    c.anchor = GridBagConstraints.NORTH;
    c.gridx = 0;
    c.gridy = 1;    
    c.weightx = 1.0;
    c.weighty = 1.0;        
    leftPanel.add(movieListPanel, c);


    
//    pack();
    setLocationRelativeTo(null);    // center
    setResizable( false );
    setVisible(true);
    }
    
    public static void main(String[] args) {    
        // Base.openTransaction();
        new BrowseMoviesView();
        // Base.rollbackTransaction();
    }
}
