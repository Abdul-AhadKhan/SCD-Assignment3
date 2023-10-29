/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scd.assignment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Abdul Ahad
 */
public class LibraryManagementSystem {
        
    ArrayList<Book> ItemsList = new ArrayList<>();
    
    public void readFromFile() throws FileNotFoundException, ParseException
    {
        
        File fileObj = new File("Books.txt");
        Scanner sc = new Scanner(fileObj);
        String line = "";
        String type, title, author, publisher;
        int year, popularityCount;
        float price;
        Date date;
        ArrayList<String> authorsList = new ArrayList<>();
        
        while(sc.hasNextLine()){
            
            line = sc.nextLine();

            title = line.substring(0, line.indexOf(','));
            line = line.substring(line.indexOf(',') + 2);
            author = line.substring(0, line.indexOf(','));
            line = line.substring(line.indexOf(',') + 2);
            year = Integer.parseInt(line.substring(0, line.indexOf(',')));
            line = line.substring(line.indexOf(',') + 2);
            popularityCount = Integer.parseInt(line.substring(0));
            ItemsList.add(new Book(title, author, year, popularityCount));
            
        }
        
        System.out.println("Items Loaded From File");
    }
    
    public void addItem(Book b){
        
        ItemsList.add(b);
        
    }
    
    public void deleteItem(int itemId){
        
        for (int i = 0; i < ItemsList.size(); i++){
            if (itemId == ItemsList.get(i).getId()){

                ItemsList.remove(i);
                System.out.println("Item Deleted Successfully");
                return;
                
            }
        }
        System.out.println("Item Not Found");
    }
    
    public void editItem(int itemId) throws ParseException{
        
        for (int i = 0; i < ItemsList.size(); i++){
            if (ItemsList.get(i).getId() == itemId){
                System.out.println("Previous Information:- ");
                ItemsList.get(i).editItem();
                System.out.println("Item Updated Successfully");
            }
        } 
    }
    
    
    public ArrayList<Book> displayAllItems(){
        return ItemsList;
    }
    
//    public void displayItemById(int itemId){
//        
//        for (int i = 0; i < ItemsList.size(); i++){
//            if (ItemsList.get(i).getId() == itemId){
//                displayItem(ItemsList.get(i));
//                System.out.println("Item Deleted");
//                return;
//            }
//        }
//        System.out.println("Item Not Found");
//    }
    

    
    public void hotPicks(){
        
        ItemsList.sort((item1, item2) -> Integer.compare(item2.getPopularityCount(), item1.getPopularityCount()));
        displayAllItems();
    }
    

    


}
