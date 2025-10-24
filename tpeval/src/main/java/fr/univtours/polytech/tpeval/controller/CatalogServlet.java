package fr.univtours.polytech.tpeval.controller;

import fr.univtours.polytech.tpeval.model.Library;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @class CatalogServlet
 * @brief Servlet permettant d'afficher le catalogue complet des livres disponibles.
 *
 * Cette servlet récupère les livres disponibles depuis le modèle {@link Library} 
 * et les transmet à la vue `catalog.jsp` pour affichage.
 */
@WebServlet("/catalog")
public class CatalogServlet extends HttpServlet {

    /**
     * @brief Traite les requêtes GET pour afficher le catalogue des livres.
     *
     * Cette méthode :
     * - Récupère la liste des livres disponibles via le modèle {@link Library}.
     * - Stocke la liste dans l'attribut de requête "catalog".
     * - Forwarde la requête vers `catalog.jsp` pour affichage.
     *
     * @param request  L'objet HttpServletRequest contenant la requête du client.
     * @param response L'objet HttpServletResponse utilisé pour renvoyer la réponse.
     * @throws ServletException Si une erreur survient lors du traitement servlet.
     * @throws IOException Si une erreur d’entrée/sortie survient pendant le forward.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupère tous les livres disponibles
        request.setAttribute("catalog", Library.getAvailableBooks());

        // Forward vers la catalog.jsp
        request.getRequestDispatcher("/catalog.jsp").forward(request, response);
    }
}
