/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scd.assignment3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
/**
 *
 * @author Abdul Ahad
 */
public class HomePage {
    
    static private ArrayList<Book> books = new ArrayList<>();
    
    public static void main(String[] args) throws FileNotFoundException, ParseException{
        
        LibraryManagementSystem library = new LibraryManagementSystem();
        library.readFromFile();
        books.addAll(library.displayAllItems());
        
        JFrame home = new JFrame("Home");

        
        String[] columns = {"ID", "Title", "Author", "Year", "Read"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        JScrollPane scrollpane = new JScrollPane(table);
        scrollpane.setPreferredSize(new Dimension(530, 400));
        table.setPreferredSize(new Dimension(530, 400));
        table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor());
        
        table.addMouseMotionListener(new MouseAdapter(){
            int highlightedRow = -1;
            @Override
            public void mouseMoved(MouseEvent e){
                int row = table.rowAtPoint(e.getPoint());
                if (row != highlightedRow && row >= 0){
                    if (highlightedRow >= 0 && highlightedRow < table.getRowCount()){
                        table.removeRowSelectionInterval(highlightedRow, highlightedRow);
                    }
                    highlightedRow = row;
                    table.setRowSelectionInterval(highlightedRow, highlightedRow);
                }
                
            }
        });
        
        
        home.getContentPane().setBackground(new Color(37, 118, 133));
        home.setVisible(true);
        home.setSize(700, 540);
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
        rightPanel.setPreferredSize(new Dimension(550, 300));
        rightPanel.add(scrollpane);
        
        JPanel buttonPanel = new JPanel();
        JButton edit = new JButton("Edit");
        JButton delete = new JButton("Delete");
        buttonPanel.add(edit);
        buttonPanel.add(delete);
        delete.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                JFrame deleteFrame = new JFrame("Delete Book");
                deleteFrame.setSize(200, 100);
                deleteFrame.setLayout(new FlowLayout());
                JLabel id = new JLabel("Book ID: ");
                JTextField idField = new JTextField(5);
                JButton delete = new JButton("Delete");
                deleteFrame.add(id);
                deleteFrame.add(idField);
                deleteFrame.add(delete);
                delete.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e){
                        int id = Integer.parseInt(idField.getText());
                        library.deleteItem(id);
                        
                        System.out.println(model.getRowCount());
                        for (int i = 0; i < model.getRowCount(); i++){
                            model.removeRow(i);
                            i = -1;
                        }
                        table.removeAll();
                        
                        table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
                        table.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor());
                        table.revalidate();
                        table.repaint();
                        for (Book book: library.displayAllItems()){
                            Object[] row = {book.getId(), book.getTitle(), book.getYear(), book.getAuthor()};
                            model.addRow(row);
                        }
                        
                        home.revalidate();
                        home.repaint();
                        deleteFrame.dispose();
                    }
                    
                });
                deleteFrame.setVisible(true);
                
                deleteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
            }
        });
        edit.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                JFrame editFrame = new JFrame("Delete Book");
                editFrame.setSize(250, 300);
                editFrame.setLayout(new FlowLayout());
                JLabel id = new JLabel("Book ID: ");
                JTextField idField = new JTextField(5);
                JButton get = new JButton("Get Book");
                JLabel label1 = new JLabel("Title");
                JLabel label2 = new JLabel("Author");
                JLabel label3 = new JLabel("Year");
                JTextField namefield = new JTextField(20);
                JTextField authorfield = new JTextField(20);
                JTextField yearfield = new JTextField(20);
                
                get.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e){
                        Book b = library.returnItem(Integer.parseInt(idField.getText()));

                        namefield.setText(b.getTitle());
                        authorfield.setText(b.getAuthor());
                        yearfield.setText(String.valueOf(b.getYear()));
                    }
                });
                JButton edit = new JButton("Edit");
                editFrame.add(id);
                editFrame.add(idField);
                editFrame.add(get);
                editFrame.add(label1);
                editFrame.add(namefield);
                editFrame.add(label2);
                editFrame.add(authorfield);
                editFrame.add(label3);
                editFrame.add(yearfield);
                editFrame.add(edit);
                editFrame.setVisible(true);
                editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                edit.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e){
                        try {
                            library.editItem(Integer.parseInt(idField.getText()), namefield.getText(), 
                                    authorfield.getText(), Integer.parseInt(yearfield.getText()));
                            
                            model.getDataVector().removeAllElements();
                            for (Book book: library.displayAllItems()){
                                Object[] row = {book.getId(), book.getTitle(), book.getYear(), book.getAuthor()};
                                model.addRow(row);
                                home.revalidate();
                                home.repaint();
                            }
                            editFrame.dispose();
                        } catch (ParseException ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
        });
        rightPanel.add(buttonPanel);

        
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
        option1.setPreferredSize(new Dimension(147, 30));
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
            public void mouseClicked(MouseEvent e){
                
                JPanel InputPanel = new JPanel();
                InputPanel.setLayout(new BoxLayout(InputPanel, BoxLayout.Y_AXIS));
                
                JPanel namePanel = new JPanel();
                namePanel.setLayout(new FlowLayout());
                JLabel title = new JLabel("Title: ");
                JTextField field1 = new JTextField(15);
                namePanel.add(title);
                namePanel.add(field1);
                
                JPanel authorPanel = new JPanel();
                authorPanel.setLayout(new FlowLayout());
                JLabel author = new JLabel("Author: ");
                JTextField field2 = new JTextField(15);
                authorPanel.add(author);
                authorPanel.add(field2);
                
                JPanel yearPanel = new JPanel();
                yearPanel.setLayout(new FlowLayout());
                JLabel year = new JLabel("Year: ");
                JTextField field3 = new JTextField(15);
                yearPanel.add(year);
                yearPanel.add(field3);
                
                InputPanel.add(namePanel);
                InputPanel.add(authorPanel);
                InputPanel.add(yearPanel);
                
                int result = JOptionPane.showConfirmDialog(home, InputPanel,"Input Dialog", JOptionPane.OK_CANCEL_OPTION);
                
                if (result == JOptionPane.OK_OPTION){
                    Book b = new Book((String)field1.getText(),(String)field2.getText(), Integer.parseInt((String)field3.getText()));
                    library.addItem(b);
                    Object[] row = {b.getId(), b.getTitle(), b.getYear(), b.getAuthor()};
                    
                    model.addRow(row);
                    home.revalidate();
                    home.repaint();
                }
            }
        });
        
        
        
        JPanel option2 = new JPanel();
        option2.setLayout(new BorderLayout());
        option2.setPreferredSize(new Dimension(147, 30));
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

                //ButtonRenderer b = new ButtonRenderer(table);
                ArrayList<Book> books = new ArrayList<>(library.displayAllItems());
                for (Book book: books){
                    Object[] row = {book.getId(), book.getTitle(), book.getYear(), book.getAuthor()};
                    model.addRow(row);
                }
                
                
            }
        });
        
        
        JPanel option3 = new JPanel();
        option3.setLayout(new BorderLayout());
        option3.setPreferredSize(new Dimension(147, 30));
        option3.setAlignmentX(Component.LEFT_ALIGNMENT);
        option3.setBackground(Colors.getBackgroundColor1());
        JLabel viewPopularity = new JLabel("- Popularity");
        viewPopularity.setBackground(Colors.getBackgroundColor1());
        viewPopularity.setForeground(Colors.getForegroundColor1());
        viewPopularity.setAlignmentX(Component.LEFT_ALIGNMENT);
        option3.add(viewPopularity);
        option3.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                option3.setBackground(Colors.getBackgroundColor3());
                viewPopularity.setBackground(Colors.getBackgroundColor3());
                viewPopularity.setFont(Fonts.getFont1());
            }
            @Override
            public void mouseExited(MouseEvent e){
                option3.setBackground(Colors.getBackgroundColor1());
                viewPopularity.setBackground(Colors.getBackgroundColor1());
                viewPopularity.setFont(Fonts.getFont2());
            }
            @Override
            public void mouseClicked(MouseEvent e){
                DefaultPieDataset dataset = new DefaultPieDataset();
                for (Book book: library.displayAllItems()){
                    dataset.setValue(book.getTitle(), book.getPopularityCount());
                    System.out.println("Popularity Count: "  + book.getPopularityCount());
                }
                JFreeChart pieChart = ChartFactory.createPieChart3D("Pie Chart", dataset, true, true, false);
                PiePlot pieplot = (PiePlot) pieChart.getPlot();
                ChartPanel chartpanel = new ChartPanel(pieChart);
                chartpanel.validate();
                
                JFrame pieFrame = new JFrame("Pie Chart");
                pieFrame.setSize(800, 500);
                pieFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                pieFrame.setVisible(true);
                JPanel panel = new JPanel();
                panel.add(chartpanel);
                pieFrame.add(panel);
                
            }
        });
        

                
        menu.add(option1);
        menu.add(Box.createVerticalStrut(20));
        menu.add(option2);
        menu.add(Box.createVerticalStrut(20));
        menu.add(option3);
        leftPanel.add(menu);
        
        home.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                try {
                    library.saveInFile();
                } catch (IOException ex) {
                    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
       
    }
}

