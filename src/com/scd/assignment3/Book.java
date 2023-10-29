/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scd.assignment3;

import java.util.Scanner;

/**
 *
 * @author Abdul Ahad
 */
public class Book{


    
    private String title;
    private static int nextId = 1;
    private int id;
    private String author;
    private int year;
    private int popularityCount;
    
    Book (String t, String a, int y, int pCount){
        title = t;
        id = nextId++;
        author = a;
        year = y;
        popularityCount = pCount;
    }
    
    Book (String t, String a, int y){
        title = t;
        id = nextId++;
        author = a;
        year = y;
        popularityCount = 0;
    }
    

    
    public void editItem(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the name of the author: ");
        author = sc.nextLine().trim();
        System.out.print("Enter the year of publication: ");
        year = sc.nextInt();
    }
    
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
        public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Book.nextId = nextId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPopularityCount() {
        return popularityCount;
    }

    public void setPopularityCount(int popularityCount) {
        this.popularityCount = popularityCount;
    }
}
