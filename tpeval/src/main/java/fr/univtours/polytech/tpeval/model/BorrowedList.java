package fr.univtours.polytech.tpeval.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @class BorrowedList
 * @brief Représente la liste des livres empruntés par un utilisateur.
 *
 * Cette classe gère la collection de livres empruntés, leur ajout,
 * leur suppression (retour) ainsi que le calcul du coût total mensuel
 * selon le format du livre.
 */
public class BorrowedList implements Serializable {

    /** Liste des livres actuellement empruntés. */
    private List<Book> borrowedBooks = new ArrayList<>();

    /**
     * @brief Ajoute un livre à la liste des emprunts.
     * @param book Livre à ajouter.
     */
    public void addBook(Book book) {
        borrowedBooks.add(book);
    }
    
    /**
     * @brief Retourne (supprime) un livre en fonction de son ISBN.
     * @param isbn Code ISBN du livre à retourner.
     */
    public void returnBook(String isbn) {
        borrowedBooks.removeIf(b -> b.getIsbn().equals(isbn));
    }
    
    /**
     * @brief Calcule le coût total mensuel des livres empruntés.
     * @details
     * - Livre physique : 10 €
     * - Livre en ligne : 5 €
     * @return Montant total du coût mensuel.
     */
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
    
    /**
     * @return La liste complète des livres empruntés.
     */
    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
    
    /**
     * @return Le nombre total de livres empruntés.
     */
    public int getBookCount() {
        return borrowedBooks.size();
    }
    
    /**
     * @brief Vide complètement la liste des livres empruntés.
     */
    public void clear() {
        borrowedBooks.clear();
    }
}
