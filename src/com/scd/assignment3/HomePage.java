/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scd.assignment3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Abdul Ahad
 */
public class HomePage {
    
    public static void main(String[] args) throws FileNotFoundException, ParseException{
        
        LibraryManagementSystem library = new LibraryManagementSystem();
        library.readFromFile();
        
        String[] columns = {"Title", "Author", "Year", "Read"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        JScrollPane scrollpane = new JScrollPane(table);
        scrollpane.setPreferredSize(new Dimension(430, 400));
        table.setPreferredSize(new Dimension(430, 400));
        table.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer());
        table.addMouseListener(new MouseAdapter(){
            int highlightedRow = -1;
            @Override
            public void mouseEntered(MouseEvent e){
                int row = table.rowAtPoint(e.getPoint());
                if (row != highlightedRow){
                    highlightedRow = row;
                    table.setRowSelectionInterval(highlightedRow, highlightedRow);
                }
            }
            @Override
            public void mouseExited(MouseEvent e){
                highlightedRow = -1;
                table.clearSelection();
            }
        });
        
        JFrame home = new JFrame("Home");
        home.getContentPane().setBackground(new Color(37, 118, 133));
        home.setVisible(true);
        home.setSize(600, 500);
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Box container = Box.createHorizontalBox();
        
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Colors.getBackgroundColor1());
        leftPanel.setPreferredSize(new Dimension(150, leftPanel.getPreferredSize().height));
        LineBorder border = new LineBorder(Color.WHITE, 3);
        leftPanel.setBorder(border);
        
        JPanel rightPanel = new JPanel();
        rightPanel.add(new Label("Library Management System"));
        rightPanel.setBackground(Colors.getBackgroundColor2());
        rightPanel.setPreferredSize(new Dimension(450, rightPanel.getPreferredSize().height));
        rightPanel.add(scrollpane);

        
        container.add(leftPanel);
        container.add(rightPanel);
        home.add(container);
        
        
        JPanel dashboard = new JPanel();
        dashboard.setBackground(Colors.getBackgroundColor1());
        JLabel header = new JLabel("Dashboard");
        header.setForeground(Colors.getForegroundColor1());
        header.setFont(new Font(Font.SANS_SERIF,Font.BOLD, 20 ));
        dashboard.add(header);
        leftPanel.add(dashboard);
        
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBackground(Colors.getBackgroundColor1());
        menu.add(Box.createVerticalStrut(70));
        
        
        JPanel option1 = new JPanel();
        option1.setPreferredSize(new Dimension(147, 20));
        option1.setLayout(new BorderLayout());
        option1.setBackground(Colors.getBackgroundColor1());
        option1.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel addButton = new JLabel("- Add Book");
        addButton.setForeground(Colors.getForegroundColor1());
        addButton.setBackground(Colors.getBackgroundColor1());
        addButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        addButton.setOpaque(true);
        option1.add(addButton);
        
        addButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                option1.setBackground(Colors.getBackgroundColor3());
                addButton.setBackground(Colors.getBackgroundColor3());
                addButton.setFont(Fonts.getFont1());
            }
            @Override
            public void mouseExited(MouseEvent e){
                option1.setBackground(Colors.getBackgroundColor1());
                addButton.setBackground(Colors.getBackgroundColor1());
                addButton.setFont(Fonts.getFont2());
            }
            @Override
            public void mousePressed(MouseEvent e){
                rightPanel.remove(scrollpane);
            }
        });
        
        
        
        JPanel option2 = new JPanel();
        option2.setLayout(new BorderLayout());
        option2.setBackground(Colors.getBackgroundColor1());
        option2.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel viewButton = new JLabel("- View Books");
        viewButton.setBackground(Colors.getBackgroundColor1());
        viewButton.setForeground(Color.WHITE);
        viewButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        option2.add(viewButton, BorderLayout.WEST);
        option2.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                option2.setBackground(Colors.getBackgroundColor3());
                viewButton.setBackground(Colors.getBackgroundColor3());
                viewButton.setFont(Fonts.getFont1());
            }
            @Override
            public void mouseExited(MouseEvent e){
                option2.setBackground(Colors.getBackgroundColor1());
                viewButton.setBackground(Colors.getBackgroundColor1());
                viewButton.setFont(Fonts.getFont2());
            }
            @Override
            public void mousePressed(MouseEvent e){

                ArrayList<Book> books = new ArrayList<>(library.displayAllItems());
                for (Book book: books){
                    Object[] row = {book.getTitle(), book.getYear(), book.getAuthor(), new JButton("Read")};
                    model.addRow(row);
                }
                
                
            }
        });
        
        
        JPanel option3 = new JPanel();
        option3.setLayout(new BorderLayout());
        option3.setAlignmentX(Component.LEFT_ALIGNMENT);
        option3.setBackground(Colors.getBackgroundColor1());
        JLabel viewPopularity = new JLabel("- Popularity");
        viewPopularity.setBackground(Colors.getBackgroundColor1());
        viewPopularity.setForeground(Colors.getForegroundColor1());
        viewPopularity.setAlignmentX(Component.LEFT_ALIGNMENT);
        option3.add(viewPopularity);
        
        JPanel option4 = new JPanel();
        option4.setLayout(new BorderLayout());
        option4.setAlignmentX(0);
        option4.setBackground(Colors.getBackgroundColor1());
        option4.setForeground(Colors.getForegroundColor1());
        JLabel deleteButton = new JLabel("Delete Book");
        deleteButton.setBackground(Colors.getBackgroundColor1());
        deleteButton.setForeground(Colors.getForegroundColor1());
        deleteButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        option4.add(deleteButton);
        
        
        menu.add(option1);
        menu.add(Box.createVerticalStrut(20));
        menu.add(option2);
        menu.add(Box.createVerticalStrut(20));
        menu.add(option3);
        leftPanel.add(menu);


       
//        JLabel title = new JLabel("Library Management System");
//        title.setOpaque(true);
//        title.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
//        title.setForeground(Color.red);
//        JPanel titlePanel = new JPanel();
//        titlePanel.add(title);
//        titlePanel.setOpaque(true);
//        
//        home.add(titlePanel, BorderLayout.NORTH);
//        
//        JPanel optionsPanel = new JPanel();
//        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
//        JButton addButton = new JButton("Add Item");
//        addButton.setSize(20, 30);
//        addButton.setAlignmentX(JFrame.CENTER_ALIGNMENT);
//        JButton deleteButton = new JButton("Delete Item");
//        deleteButton.setAlignmentX(JFrame.CENTER_ALIGNMENT);
//        JButton viewButton = new JButton("View Items");
//        viewButton.setBounds(0, 0, 20, 30);
//        JButton borrowItem = new JButton("Borrow Item");
//        borrowItem.setBounds(0, 0, 20, 30);
//        JButton viewBorrowedItems = new JButton("View Borrowed Items");
//        optionsPanel.add(addButton);
//        optionsPanel.add(deleteButton);
//        optionsPanel.add(deleteButton);
//        optionsPanel.add(viewButton);
//        optionsPanel.add(borrowItem);
//        optionsPanel.add(viewBorrowedItems);
//        optionsPanel.setOpaque(true);
//        optionsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
//       
//        
//        home.add(optionsPanel, BorderLayout.CENTER);
       
    }
}

