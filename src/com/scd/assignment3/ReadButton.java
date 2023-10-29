/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scd.assignment3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


/**
 *
 * @author Abdul Ahad
 */
public class ReadButton {
    JButton read;
    int row;
    String bookTitle;
    int id;
    ReadButton() throws FileNotFoundException, ParseException{
        read = new JButton("Read");

        read.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e){
                JFrame bookContent = new JFrame(bookTitle);
                JTextArea data = new JTextArea();
                
                Path p = Path.of(bookTitle + ".txt");
                try {
                    String fileData = Files.readString(p);
                    data.setText(fileData);
                    data.setEditable(false);
                    LibraryManagementSystem library = new LibraryManagementSystem();
                    try {
                        library.readFromFile();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ReadButton.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(ReadButton.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    library.increment(id);
                    
                } catch (IOException ex) {
                    Logger.getLogger(ReadButton.class.getName()).log(Level.SEVERE, null, ex);
                }
                JScrollPane scrollpane = new JScrollPane(data);
                bookContent.add(scrollpane);
                bookContent.setSize(400, 400);
                bookContent.setVisible(true);
                bookContent.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                bookContent.addWindowListener(new WindowAdapter(){
                    @Override
                    public void windowClosing(WindowEvent e){
                        int result = JOptionPane.showConfirmDialog(bookContent, "Are you sure you want to close this window?",
                                "Close Window?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        
                        if (result == JOptionPane.YES_OPTION){
                            bookContent.dispose();
                        }

                    }
                });
            }
        });
    }
    
    public void setRow(int r, String n, int id){
        row = r;
        bookTitle = n;
        this.id = id;
    }
    public Component returnButton(){
        return read;
    }
    
    
}
