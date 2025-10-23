package fr.univtours.polytech.tpeval.controller;

import fr.univtours.polytech.tpeval.model.BorrowedList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @class CheckoutServlet
 * @brief Gère la validation et la confirmation de l'emprunt des livres par l'utilisateur.
 *
 * Cette servlet permet de :
 * - Vérifier le contenu du panier (liste d'emprunt)
 * - Appliquer la règle métier limitant le nombre de livres empruntés
 * - Calculer le coût total des livres empruntés
 * - Confirmer la commande et réinitialiser la session
 *
 * @author
 *   Outman Aithamadi
 * @version 1.0
 * @date 2025-10-23
 */
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    /** Clé de session utilisée pour stocker la liste des livres empruntés. */
    private static final String SESSION_KEY = "borrowedList";

    /** Nombre maximum de livres qu'un utilisateur peut emprunter. */
    private static final int MAX_BOOKS = 2;

    /**
     * @brief Traite les requêtes GET pour afficher la page de validation de commande.
     *
     * Cette méthode :
     * - Vérifie si la session et la liste des livres empruntés existent.
     * - Vérifie que le nombre de livres n'excède pas la limite autorisée.
     * - Calcule le coût total des emprunts si tout est valide.
     * - Redirige vers la page `checkout.jsp` avec les informations nécessaires.
     *
     * @param request  L'objet HttpServletRequest contenant les informations de la requête.
     * @param response L'objet HttpServletResponse utilisé pour répondre au client.
     * @throws ServletException Si une erreur survient au niveau du traitement servlet.
     * @throws IOException Si une erreur d'entrée/sortie survient pendant la redirection.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); ///< Récupère la session sans en créer une nouvelle.
        BorrowedList borrowedList = null;

        if (session != null) {
            borrowedList = (BorrowedList) session.getAttribute(SESSION_KEY);
        }

        // Vérifie si le panier est vide
        if (borrowedList == null || borrowedList.getBookCount() == 0) {
            request.setAttribute("error", "Votre panier est vide.");
            request.getRequestDispatcher("/catalog").forward(request, response);
            return;
        }

        // Applique la règle métier : nombre maximum de livres
        if (borrowedList.getBookCount() > MAX_BOOKS) {
            request.setAttribute("error",
                    "Erreur de validation : Vous ne pouvez pas emprunter plus de " + MAX_BOOKS + " livres.");
        } else {
            // Calcule le coût total des livres empruntés
            double totalCost = borrowedList.calculateTotalCost();
            request.setAttribute("totalCost", totalCost);
        }

        // Redirection vers la page de validation (checkout.jsp)
        request.getRequestDispatcher("/checkout.jsp").forward(request, response);
    }

    /**
     * @brief Traite les requêtes POST pour confirmer le checkout.
     *
     * Cette méthode :
     * - Supprime la liste des livres empruntés de la session.
     * - (Optionnellement) invalide complètement la session.
     * - Affiche un message de confirmation à l'utilisateur.
     * - Redirige vers la page d'accueil `index.jsp`.
     *
     * @param request  L'objet HttpServletRequest contenant les informations de la requête.
     * @param response L'objet HttpServletResponse utilisé pour répondre au client.
     * @throws ServletException Si une erreur survient au niveau du traitement servlet.
     * @throws IOException Si une erreur d'entrée/sortie survient pendant la redirection.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); ///< Récupère la session si elle existe.
        if (session != null) {
            // Supprime la liste d'emprunt
            session.removeAttribute(SESSION_KEY);
            // Optionnel : session.invalidate();
            request.setAttribute("message", "Votre commande a été confirmée. Votre session d'emprunt a été effacée.");
        }

        // Redirige vers la page d'accueil après confirmation
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
