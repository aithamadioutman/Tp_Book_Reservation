package fr.univtours.polytech.tpeval.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

// Stockage centralisé du catalogue pour tous les utilisateurs.
public class Library {
    private static final ConcurrentHashMap<String, Book> catalog = new ConcurrentHashMap<>();

    static {
        // Initialisation des données (Exemple 1)
        catalog.put("1234567890", new Book("1234567890", "Java Programming", "John Doe", 3, "A guide to Java", "physical"));
        catalog.put("0987654321", new Book("0987654321", "Web Development", "Jane Smith", 2, "Learn web basics", "online"));
        catalog.put("1122334455", new Book("1122334455", "Data Structures", "Alice Brown", 5, "Advanced Algorithms", "physical"));
        catalog.put("5544332211", new Book("5544332211", "AI Fundamentals", "Bob Green", 0, "Future is now", "online"));
    }

    public static List<Book> getAvailableBooks() {
        return new ArrayList<>(catalog.values());
    }
    
    public static Optional<Book> getBookByIsbn(String isbn) {
        return Optional.ofNullable(catalog.get(isbn));
    }
}