package fr.univtours.polytech.tpeval.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @class Library
 * @brief Classe statique représentant le catalogue global de la bibliothèque.
 *
 * Cette classe agit comme une source de données partagée entre tous les utilisateurs.
 * Elle contient un ensemble de livres accessibles et fournit des méthodes pour
 * récupérer le catalogue complet ou rechercher un livre par son ISBN.
 */
public class Library {

    /** Catalogue global des livres, indexé par ISBN. */
    private static final ConcurrentHashMap<String, Book> catalog = new ConcurrentHashMap<>();

    // ======= Bloc statique d'initialisation =======
    static {
        catalog.put("1234567890", new Book("1234567890", "Java Programming", "John Doe", 3, "A guide to Java", "physical"));
        catalog.put("0987654321", new Book("0987654321", "Web Development", "Jane Smith", 2, "Learn web basics", "online"));
        catalog.put("1122334455", new Book("1122334455", "Data Structures", "Alice Brown", 5, "Advanced Algorithms", "physical"));
        catalog.put("5544332211", new Book("5544332211", "AI Fundamentals", "Bob Green", 0, "Future is now", "online"));
    }

    /**
     * @brief Retourne la liste complète des livres disponibles dans le catalogue.
     * @return Une liste de tous les livres.
     */
    public static List<Book> getAvailableBooks() {
        return new ArrayList<>(catalog.values());
    }
    
    /**
     * @brief Recherche un livre par son code ISBN.
     * @param isbn Code ISBN du livre à rechercher.
     * @return Un {@link java.util.Optional} contenant le livre s’il existe.
     */
    public static Optional<Book> getBookByIsbn(String isbn) {
        return Optional.ofNullable(catalog.get(isbn));
    }
}
