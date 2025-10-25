package fr.univtours.polytech.tpeval.controller;

import fr.univtours.polytech.tpeval.model.Book;
import fr.univtours.polytech.tpeval.model.BorrowedList;
import fr.univtours.polytech.tpeval.model.Library;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * @class BorrowServlet
 * @brief Gère les actions liées à l’emprunt, la restitution et la consultation des livres.
 *
 * Cette servlet centralise la logique métier pour :
 * - Réserver un livre (réduction du nombre d'exemplaires disponibles)
 * - Retourner un livre (augmentation du stock)
 * - Afficher la liste des livres empruntés
 *
 * Les routes associees sont :
 * - `/reserve` : pour emprunter un livre.
 * - `/return` : pour retourner un livre emprunté.
 * - `/borrowed` : pour afficher les livres actuellement empruntés.
 *
 * @author
 *   Outman Aithamadi
 */
@WebServlet({"/reserve", "/return", "/borrowed"})
public class BorrowServlet extends HttpServlet {

    /** Cle de session utilisee pour stocker la liste des livres empruntes. */
    private static final String SESSION_KEY = "borrowedList";

    /**
     * @brief Traite les requêtes GET pour afficher la liste des livres empruntés.
     *
     * Cette méthode :
     * - Récupère la session et initialise la liste des emprunts si elle n’existe pas.
     * - Envoie la liste des livres empruntés à la vue `borrowed.jsp`.
     * - Affiche les informations de débogage dans la console.
     *
     * @param request  L'objet HttpServletRequest contenant la requête du client.
     * @param response L'objet HttpServletResponse utilisé pour renvoyer la réponse.
     * @throws ServletException Si une erreur survient au niveau du traitement servlet.
     * @throws IOException Si une erreur d’entrée/sortie survient pendant le forward.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true); 
        BorrowedList borrowedList = (BorrowedList) session.getAttribute(SESSION_KEY);

        // Initialise la liste d’emprunt si nécessaire
        if (borrowedList == null) {
            borrowedList = new BorrowedList();
            session.setAttribute(SESSION_KEY, borrowedList);
        }
        request.setAttribute("borrowedList", borrowedList);
        request.getRequestDispatcher("/borrowed.jsp").forward(request, response);
    }

    /**
     * @brief Traite les requêtes POST pour gérer les actions de réservation et de retour.
     *
     * Cette méthode :
     * - Identifie l’action à effectuer (`/reserve` ou `/return`)
     * - Met à jour le stock de livres dans la bibliothèque
     * - Met à jour la liste des livres empruntés dans la session
     * - Redirige l’utilisateur vers la page `borrowed.jsp` ou `catalog.jsp` selon le cas
     *
     * @param request  L'objet HttpServletRequest contenant la requête du client.
     * @param response L'objet HttpServletResponse utilisé pour renvoyer la réponse.
     * @throws ServletException Si une erreur survient lors du traitement.
     * @throws IOException Si une erreur d’entrée/sortie survient lors du forward.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath(); 
        String isbn = request.getParameter("isbn");
        HttpSession session = request.getSession(true);

        // création de la liste d’emprunt
        BorrowedList borrowedList = (BorrowedList) session.getAttribute(SESSION_KEY);
        if (borrowedList == null) {
            borrowedList = new BorrowedList();
            session.setAttribute(SESSION_KEY, borrowedList);
        }

        // Recherche du livre dans la bibliotheque
        Optional<Book> libraryBookOpt = Library.getBookByIsbn(isbn);
        if (!libraryBookOpt.isPresent()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Livre non trouvé.");
            return;
        }

        Book libraryBook = libraryBookOpt.get();

        // premiere Cas 1 : Rservation d'un livre 
        if ("/reserve".equals(action)) {
            System.out.println("➡ Action : RESERVE");

            if (libraryBook.getAvailableCopies() > 0) {
                libraryBook.decreaseCopies();

                // Creation d’une copie du livre emprunte
                Book borrowedBook = new Book(
                        libraryBook.getIsbn(),
                        libraryBook.getTitle(),
                        libraryBook.getAuthor(),
                        libraryBook.getAvailableCopies(),
                        libraryBook.getDescription(),
                        libraryBook.getFormat()
                );

                System.out.println("Avant ajout : " + borrowedList.getBookCount());
                borrowedList.addBook(borrowedBook);
                System.out.println("Après ajout : " + borrowedList.getBookCount());

                session.setAttribute(SESSION_KEY, borrowedList);
            } else {
                request.setAttribute("error", "Ce livre n'a plus d'exemplaires disponibles.");
                request.getRequestDispatcher("/catalog").forward(request, response);
                return;
            }

        // deuxieme Cas 2 : Retour d’un livre 
        } else if ("/return".equals(action)) {
            System.out.println("➡ Action : RETURN");
            borrowedList.returnBook(isbn);
            libraryBook.increaseCopies();
            session.setAttribute(SESSION_KEY, borrowedList);
        }

        // Met a jour les attributs et redirige vers la page des emprunts
        request.setAttribute("borrowedList", borrowedList);
        request.getRequestDispatcher("/borrowed.jsp").forward(request, response);
    }
}
