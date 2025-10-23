package fr.univtours.polytech.tpeval.model;

import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private String isbn;
    private String title;
    private String author;
    private int availableCopies;
    private String description;
    private String format; 
    
    public Book(String isbn, String title, String author, int copies, String description, String format) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.availableCopies = copies;
        this.description = description;
        this.format = format;
    }
    
    public Book(String isbn, String title, String author, String format) {
        this(isbn, title, author, 1, "", format);
    }
    
    // Gestion des copies
    public void decreaseCopies() {
        if (availableCopies > 0) availableCopies--;
    }
    
    public void increaseCopies() {
        availableCopies++;
    }

    // --- Getters ---
    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public String getDescription() {
        return description;
    }

    public String getFormat() {
        return format;
    }

    // --- Setters ---
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
