package fr.univtours.polytech.tpeval.model;

import java.io.Serializable;

/**
 * @class Book
 * @brief Représente un livre dans le système de gestion de bibliothèque.
 *
 * Cette classe contient les informations principales sur un livre :
 * ISBN, titre, auteur, description, nombre d'exemplaires disponibles
 * et le format (physique ou en ligne).
 *
 * Elle implémente l'interface {@link java.io.Serializable} afin de pouvoir
 * être sauvegardée dans la session utilisateur.
 */
public class Book implements Serializable {

    /** Code ISBN unique du livre. */
    private String isbn;

    /** Titre du livre. */
    private String title;

    /** Auteur du livre. */
    private String author;

    /** Nombre d'exemplaires disponibles. */
    private int availableCopies;

    /** Description du livre. */
    private String description;

    /** Format du livre : "physical" ou "online". */
    private String format; 
    
    /**
     * @brief Constructeur principal d'un livre.
     * @param isbn Code ISBN unique.
     * @param title Titre du livre.
     * @param author Auteur du livre.
     * @param copies Nombre d'exemplaires disponibles.
     * @param description Description du livre.
     * @param format Format du livre (ex. "physical" ou "online").
     */
    public Book(String isbn, String title, String author, int copies, String description, String format) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.availableCopies = copies;
        this.description = description;
        this.format = format;
    }
    
    /**
     * @brief Constructeur simplifié -> par défaut 1 exemplaire, sans description 
     * @param isbn Code ISBN unique.
     * @param title Titre du livre.
     * @param author Auteur du livre.
     * @param format Format du livre.
     */
    public Book(String isbn, String title, String author, String format) {
        this(isbn, title, author, 1, "", format);
    }
    
    /**
     * @brief Diminue le nombre d'exemplaires disponibles d'une unite
     */
    public void decreaseCopies() {
        if (availableCopies > 0) availableCopies--;
    }
    
    /**
     * @brief Augmente le nombre d'exemplaires disponibles d'une unite
     */
    public void increaseCopies() {
        availableCopies++;
    }

    //  getteurs 

    /** @return Le code ISBN du livre */
    public String getIsbn() { return isbn; }

    /** @return Le titre du livre. */
    public String getTitle() { return title; }

    /** @return L auteur du livre */
    public String getAuthor() { return author; }

    /** @return Le nombre d'exemplaires disponibles */
    public int getAvailableCopies() { return availableCopies; }

    /** @return La description du livre */
    public String getDescription() { return description; }

    /** @return Le format du livre ->physique ou en ligne  */
    public String getFormat() { return format; }

    //  setteurs 

    /** @param isbn Nouveau code ISBN du livre */
    public void setIsbn(String isbn) { this.isbn = isbn; }

    /** @param title Nouveau titre du livre */
    public void setTitle(String title) { this.title = title; }

    /** @param author Nouvel auteur du livre */
    public void setAuthor(String author) { this.author = author; }

    /** @param availableCopies Nouveau nombre d'exemplaires disponibles */
    public void setAvailableCopies(int availableCopies) { this.availableCopies = availableCopies; }

    /** @param description Nouvelle description du livre */
    public void setDescription(String description) { this.description = description; }

    /** @param format Nouveau format du livre */
    public void setFormat(String format) { this.format = format; }
}
