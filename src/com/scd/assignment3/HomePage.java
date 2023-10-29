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
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
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
        scrollpane.setPreferredSize(new Dimension(430, 400));
        table.setPreferredSize(new Dimension(430, 400));
        table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor());
        table.addMouseMotionListener(new MouseAdapter(){
            int highlightedRow = -1;
            @Override
            public void mouseMoved(MouseEvent e){
                int row = table.rowAtPoint(e.getPoint());
                if (row != highlightedRow && row >= 0){
                    if (highlightedRow >= 0){
                        table.removeRowSelectionInterval(highlightedRow, highlightedRow);
                    }
                    highlightedRow = row;
                    table.setRowSelectionInterval(highlightedRow, highlightedRow);
                }
                
            }
            
            @Override
            public void mouseClicked(MouseEvent e){
                int row = table.rowAtPoint(e.getPoint());
                
                JLabel label1 = new JLabel("Title");
                JLabel label2 = new JLabel("Author");
                JLabel label3 = new JLabel("Year");
                JTextField field1 = new JTextField(10);
                field1.setText(table.getValueAt(row, 0).toString());
                JTextField field2 = new JTextField(10);
                field2.setText(table.getValueAt(row, 1).toString());
                JTextField field3 = new JTextField(10);
                field3.setText(table.getValueAt(row, 2).toString());
                
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                
                JPanel panel1 = new JPanel();
                panel1.setLayout(new FlowLayout());
                panel1.add(label1);
                panel1.add(field1);
                JPanel panel2 = new JPanel();
                panel2.setLayout(new FlowLayout());
                panel2.add(label2);
                panel2.add(field2);
                JPanel panel3 = new JPanel();
                panel3.setLayout(new FlowLayout());
                panel3.add(label3);
                panel3.add(field3);
                
                panel.add(panel1);
                panel.add(panel2);
                panel.add(panel3);
                
                int result = JOptionPane.showConfirmDialog(home, panel, "Book Details", JOptionPane.CANCEL_OPTION);
                
                if (result == JOptionPane.CANCEL_OPTION){
                    System.out.println("OK");
                }
            }
            
  

        });
        
        
        home.getContentPane().setBackground(new Color(37, 118, 133));
        home.setVisible(true);
        home.setSize(600, 540);
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Box container = Box.createHorizontalBox();
        
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Colors.getBackgroundColor1());
        leftPanel.setPreferredSize(new Dimension(140, leftPanel.getPreferredSize().height));
        LineBorder border = new LineBorder(Color.WHITE, 3);
        leftPanel.setBorder(border);
        
        JPanel rightPanel = new JPanel();
        rightPanel.add(new Label("Library Management System"));
        rightPanel.setBackground(Colors.getBackgroundColor2());
        rightPanel.setPreferredSize(new Dimension(460, 300));
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
                deleteFrame.setSize(200, 200);
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
                        model.getDataVector().removeAllElements();
                        for (Book book: library.displayAllItems()){
                            Object[] row = {book.getId(), book.getTitle(), book.getYear(), book.getAuthor(), book.getId()};
                            model.addRow(row);
                            home.revalidate();
                            home.repaint();
                        }
                        deleteFrame.dispose();
                    }
                    
                });
                deleteFrame.setVisible(true);
                
                deleteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
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
                
                int result = JOptionPane.showConfirmDialog(home, InputPanel,"Input Dialog", JOptionPane.OK_OPTION);
                
                if (result == JOptionPane.OK_OPTION){
                    Book b = new Book((String)field1.getText(),(String)field2.getText(), Integer.parseInt((String)field3.getText()));
                    library.addItem(b);
                    Object[] row = {b.getId(), b.getTitle(), b.getYear(), b.getAuthor(), b.getId()};
                    
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
                    Object[] row = {book.getId(), book.getTitle(), book.getYear(), book.getAuthor(), book.getId()};
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
                for (Book book: books){
                    dataset.setValue(book.getTitle(), book.getPopularityCount());
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

        
       
    }
}

