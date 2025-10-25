<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"  %> 
<!DOCTYPE html>
<html>
<head>
    <title>Mes Livres Empruntés</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">

</head>
<body>
    <h1>Mes Livres Empruntés</h1>
    

    <%-- 2. Recuperer l'objet BorrowedList de la session. SESSION_KEY est "borrowedList" --%>
    <c:set var="borrowedList" value="${sessionScope.borrowedList}" />

    
    <c:if test="${empty borrowedList or empty borrowedList.borrowedBooks}">
        <p>Vous n'avez actuellement aucun livre emprunté.</p>
    </c:if>

    <c:if test="${not empty borrowedList and not empty borrowedList.borrowedBooks}">
        <p>Nombre de livres empruntés : ${borrowedList.bookCount}</p>
    <c:forEach var="b" items="${borrowedList.borrowedBooks}">
        <p>${b.title} - ${b.author} - ${b.format}</p>
    </c:forEach>

        <table border="1">
            <thead>
                <tr>
                    <th>Titre</th>
                    <th>Auteur</th>
                    <th>Format</th>
                    <th>Coût Mensuel</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="book" items="${borrowedList.borrowedBooks}">
                    <c:set var="cost" value="${book.format eq 'physical' ? 10 : 5}" />
                    <tr>
                        <td>${book.title}</td>
                        <td>${book.author}</td>
                        <td>${book.format}</td>
                        <td>€${cost}</td>
                        <td>
                            <form action="<c:url value='/return' />" method="post">

                                <input type="hidden" name="isbn" value="${book.isbn}">
                                <button type="submit">Retourner</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <p><a href="<c:url value='/checkout' />">Passer à la Page de Paiement (Checkout)</a></p>
    </c:if>
    
    <p><a href="<c:url value='/catalog' />">Retour au Catalogue</a></p>
</body>
</html>