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

@WebServlet({"/reserve", "/return", "/borrowed"})
public class BorrowServlet extends HttpServlet {

    private static final String SESSION_KEY = "borrowedList";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        BorrowedList borrowedList = (BorrowedList) session.getAttribute(SESSION_KEY);

        if (borrowedList == null) {
            borrowedList = new BorrowedList();
            session.setAttribute(SESSION_KEY, borrowedList);
        }

        System.out.println("GET /borrowed → livres dans la session : " + borrowedList.getBookCount());
        for (Book b : borrowedList.getBorrowedBooks()) {
            System.out.println(" - " + b.getTitle());
        }

        request.setAttribute("borrowedList", borrowedList);
        request.getRequestDispatcher("/borrowed.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();
        String isbn = request.getParameter("isbn");
        HttpSession session = request.getSession(true);

        BorrowedList borrowedList = (BorrowedList) session.getAttribute(SESSION_KEY);
        if (borrowedList == null) {
            borrowedList = new BorrowedList();
            session.setAttribute(SESSION_KEY, borrowedList);
        }

        Optional<Book> libraryBookOpt = Library.getBookByIsbn(isbn);
        if (!libraryBookOpt.isPresent()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Livre non trouvé.");
            return;
        }

        Book libraryBook = libraryBookOpt.get();

        if ("/reserve".equals(action)) {
            System.out.println("➡ Action : RESERVE");
            if (libraryBook.getAvailableCopies() > 0) {
                libraryBook.decreaseCopies();

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
        } else if ("/return".equals(action)) {
            System.out.println("➡ Action : RETURN");
            borrowedList.returnBook(isbn);
            libraryBook.increaseCopies();
            session.setAttribute(SESSION_KEY, borrowedList);
        }

        request.setAttribute("borrowedList", borrowedList);
        request.getRequestDispatcher("/borrowed.jsp").forward(request, response);
    }
}
