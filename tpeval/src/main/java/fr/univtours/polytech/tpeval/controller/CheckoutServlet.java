package fr.univtours.polytech.tpeval.controller;

import fr.univtours.polytech.tpeval.model.BorrowedList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    
    private static final String SESSION_KEY = "borrowedList";
    private static final int MAX_BOOKS = 2; // Règle métier

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false); // Récupérer la session sans en créer une
        BorrowedList borrowedList = null;
        if (session != null) {
            borrowedList = (BorrowedList) session.getAttribute(SESSION_KEY);
        }

        if (borrowedList == null || borrowedList.getBookCount() == 0) {
            request.setAttribute("error", "Votre panier est vide.");
            request.getRequestDispatcher("/catalog").forward(request, response);
            return;
        }
        
        // Exercise 4: Règle métier - Limite de livres
        if (borrowedList.getBookCount() > MAX_BOOKS) {
            request.setAttribute("error", "Erreur de validation: Vous ne pouvez pas emprunter plus de " + MAX_BOOKS + " livres.");
        } else {
            // Calcul du coût et passage à la vue
            double totalCost = borrowedList.calculateTotalCost();
            request.setAttribute("totalCost", totalCost);
        }
        
        request.getRequestDispatcher("/checkout.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Confirmer le checkout et vider la session
        HttpSession session = request.getSession(false);
        if (session != null) {
            // Effacer l'objet BorrowedList de la session
            session.removeAttribute(SESSION_KEY);
            // Invalider complètement la session (optionnel, mais plus sûr)
            // session.invalidate(); 
            request.setAttribute("message", "Votre commande a été confirmée. Votre session d'emprunt a été effacée.");
        }
        // Redirection pour éviter le re-post
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}