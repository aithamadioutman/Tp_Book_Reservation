package fr.univtours.polytech.tpeval.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BorrowedList implements Serializable {
    private List<Book> borrowedBooks = new ArrayList<>();
    private static final long serialVersionUID = 1L;

    public void addBook(Book book) {
        
        // Logique : ajoute la référence du livre emprunté à la liste de l'utilisateur
        // Note: L'état de Book (copies disponibles) est géré par la logique métier/controller.
        borrowedBooks.add(book);
    }
    
    public void returnBook(String isbn) {
        borrowedBooks.removeIf(b -> b.getIsbn().equals(isbn));
    }
    
    public double calculateTotalCost() {
        double total = 0.0;
        for (Book book : borrowedBooks) {
            if ("physical".equalsIgnoreCase(book.getFormat())) {
                total += 10.0;
            } else if ("online".equalsIgnoreCase(book.getFormat())) {
                total += 5.0;
            }
        }
        return total;
    }
    
    // --- Getters et Setters ---
    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
    
    public int getBookCount() {
        return borrowedBooks.size();
    }
    
    public void clear() {
        borrowedBooks.clear();
    }
}