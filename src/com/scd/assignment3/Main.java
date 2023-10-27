/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scd.assignment3;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame homePage = new JFrame("Home");
        homePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homePage.setSize(700, 500);
    
        
       
        
        homePage.getContentPane().setBackground(new Color(37,118,133));
        
        JLabel title = new JLabel("Library Mangement System");
        title.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 30));
        //homePage.add(title);

        // Create a panel with a BoxLayout set to PAGE_AXIS
        Container contentPanel = homePage.getContentPane();
        contentPanel.setLayout(new GridLayout(2,1));
        JPanel leftPanel = new JPanel();
        JLabel details = new JLabel("Details:-");
        leftPanel.add(details);
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        JLabel Name = new JLabel("Name: ");
        Name.setAlignmentX(Component.LEFT_ALIGNMENT);
        JTextField nameField = new JTextField(20);
        panel1.add(Name);
        panel1.add(nameField);
        rightPanel.add(panel1);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
//        panel2.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        JLabel system = new JLabel("System: ");
        system.setAlignmentX(Component.LEFT_ALIGNMENT);
        JRadioButton unix = new JRadioButton("Unix");
        JRadioButton windows = new JRadioButton("Windows");
        ButtonGroup btngrp = new ButtonGroup();
        btngrp.add(unix);
        btngrp.add(windows);
        panel2.add(system);
        panel2.add(unix);
        panel2.add(windows);
        rightPanel.add(panel2);
        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());
        panel3.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        JLabel language = new JLabel("Language: ");
        JCheckBox java = new JCheckBox("Java");
        JCheckBox cplusplus = new JCheckBox("C++");
        JCheckBox perl = new JCheckBox("Perl");
        panel3.add(language);
        panel3.add(java);
        panel3.add(cplusplus);
        panel3.add(perl);
        rightPanel.add(panel3);
        JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout());
        panel4.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        String[] years = {"2023", "2022", "2021"};
        JLabel yearLabel = new JLabel("Years: ");
        JComboBox year = new JComboBox(years);
        panel4.add(yearLabel);
        panel4.add(year);
        rightPanel.add(panel4);
        
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(1,2));
        upperPanel.add(leftPanel);
        upperPanel.add(rightPanel);
        
        
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new GridLayout(1,2));
        JButton ok = new JButton("Ok");
        JButton cancel = new JButton("Cancel");
        lowerPanel.add(ok);
        lowerPanel.add(cancel);
        
        contentPanel.add(upperPanel);
        contentPanel.add(lowerPanel);
        // Create a title label


        // Create buttons
        JButton addbutton = new JButton("Add");
        JButton deletebutton = new JButton("Delete");
        JButton viewbutton = new JButton("View");
        
        
        Image image = new ImageIcon("Book.png").getImage();
        image = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(image);
        JLabel imageicon = new JLabel(icon);
        JLabel name = new JLabel("Image");
        JPanel images = new JPanel();
        images.setLayout(new BoxLayout(images, BoxLayout.X_AXIS));
        images.add(imageicon);
        images.add(name);
        
        homePage.add(images);
        
        
//        addbutton.setBounds(550, 600, 150, 20);
//        deletebutton.setBounds(550, 700, 150, 20);
//        viewbutton.setBounds(550, 800, 150, 20);

        

//        // Add components with vertical spacing
//        contentPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing
//        contentPanel.add(title);
//        contentPanel.add(Box.createRigidArea(new Dimension(0, 60))); // Add spacing
//        contentPanel.add(addbutton);
//        contentPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing
//        contentPanel.add(deletebutton);
//        contentPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing
//        contentPanel.add(viewbutton);
//
//        // Center align the components
//        title.setAlignmentX(Component.CENTER_ALIGNMENT);
//        addbutton.setAlignmentX(Component.CENTER_ALIGNMENT);
//        deletebutton.setAlignmentX(Component.CENTER_ALIGNMENT);
//        viewbutton.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        // Add the content panel to the frame's content pane
//        homePage.add(contentPanel);

        homePage.setVisible(true);
        
    }
}

