package fr.univtours.polytech.tpeval.controller;
import fr.univtours.polytech.tpeval.model.Library;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet("/catalog")
public class CatalogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Instancie une liste de livres (utilise le Model Library)
        request.setAttribute("catalog", Library.getAvailableBooks());
        
        // 2. Forwarde la liste à catalog.jsp (View)
        request.getRequestDispatcher("/catalog.jsp").forward(request, response);
    }
}