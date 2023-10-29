/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scd.assignment3;

import java.awt.Component;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        ReadButton b = null;
        try {
            b = new ReadButton();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TableActionCellEditor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TableActionCellEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        String name = jtable.getValueAt(row, 1).toString();
        int id = Integer.parseInt(jtable.getValueAt(row, 0).toString());
        b.setRow(row, name, id);
        return b.returnButton();
    }
    
}
