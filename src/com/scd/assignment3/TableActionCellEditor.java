/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scd.assignment3;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.*;

/**
 *
 * @author Abdul Ahad
 */
public class TableActionCellEditor extends DefaultCellEditor{
    
    private JButton button;
    
    public TableActionCellEditor(){
        super(new JCheckBox());
        button = new JButton("Read");
        button.setFocusPainted(false);
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int col){
        return button;
    }
    
}
