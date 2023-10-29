/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scd.assignment3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
    ReadButton(){
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
                    
                } catch (IOException ex) {
                    Logger.getLogger(ReadButton.class.getName()).log(Level.SEVERE, null, ex);
                }
                JScrollPane scrollpane = new JScrollPane(data);
                bookContent.add(scrollpane);
                bookContent.setSize(400, 400);
                bookContent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                bookContent.setVisible(true);
            }
        });
    }
    
    public void setRow(int r, String n){
        row = r;
        bookTitle = n;
    }
    public Component returnButton(){
        return read;
    }
    
    
}
