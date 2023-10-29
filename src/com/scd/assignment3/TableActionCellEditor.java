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
    
    
    public TableActionCellEditor(){
        super(new JCheckBox());
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int col){
        ReadButton b = new ReadButton();
        String name = jtable.getValueAt(row, 0).toString();
        b.setRow(row, name);
        return b.returnButton();
    }
    
}
