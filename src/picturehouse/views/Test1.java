/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package picturehouse.views;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Test1 {
    private void _panel1(JPanel panel){
        panel.setBorder(new LineBorder(Color.RED));
        panel.setLayout(new GridBagLayout());

        JPanel panel1 = new JPanel();
        panel1.add(new JLabel(" Testing Label 1 "));
        panel1.setBorder(new TitledBorder("P1"));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 0.0;
        panel.add(panel1, c);
        
        JPanel panel2 = new JPanel();
        panel2.add(new JLabel(" Testing Label 2"));
        panel2.setBorder(new TitledBorder("P2"));
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;        
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 0;
        c.gridy = 1;    
        c.weightx = 1.0;
        c.weighty = 1.0;        
        panel.add(panel2, c);

        JPanel panel3 = new JPanel();
        panel3.setBorder(new TitledBorder("P3"));
        c = new GridBagConstraints();   
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 0;
        c.gridy = 2;    
        c.weightx = 1.0;
        c.weighty = 0.0;

        panel3.setLayout(new GridLayout(5,1));
        for(int i=0; i<5; i++)
            panel3.add(new JButton("button "+i));
        panel.add(panel3, c);   
    }
    public Test1(){
        JFrame frame = new JFrame();
        frame.setTitle("test 1");
        frame.getContentPane().setLayout(new GridLayout(1,3));

        JPanel panel1 = new JPanel();
        _panel1(panel1);

        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        frame.getContentPane().add(panel1);
        frame.getContentPane().add(panel2);
//        frame.getContentPane().add(panel3);

        frame.setSize(800, 600);
        frame.setVisible(true);     
    }

    public static void main(String[] args) {
        Test1 t = new Test1();
    }

}