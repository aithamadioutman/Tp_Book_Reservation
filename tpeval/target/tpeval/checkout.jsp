<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Récapitulatif de Commande</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">

</head>
<body>
    <h1>Récapitulatif de Commande</h1>

    <c:set var="borrowedList" value="${sessionScope.borrowedList}" />
    
    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
        <p>Veuillez retourner certains livres pour continuer. <a href="${pageContext.request.contextPath}/borrowed">Retourner un livre</a></p>
    </c:if>

    <c:if test="${empty error and not empty borrowedList and borrowedList.bookCount > 0}">
        <table border="1">
            <thead>
                <tr>
                    <th>Titre</th>
                    <th>Format</th>
                    <th>Coût</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="book" items="${borrowedList.borrowedBooks}">
                    <c:set var="cost" value="${book.format eq 'physical' ? 10 : 5}" />
                    <tr>
                        <td>${book.title}</td>
                        <td>${book.format}</td>
                        <td>€<fmt:formatNumber value="${cost}" minFractionDigits="2" maxFractionDigits="2" /></td>
                    </tr>
                </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="2"><strong>Total Mensuel:</strong></td>
                    <td><strong>€<fmt:formatNumber value="${requestScope.totalCost}" minFractionDigits="2" maxFractionDigits="2" /></strong></td>
                </tr>
            </tfoot>
        </table>
        
        <p>Nombre total de livres empruntés : ${borrowedList.bookCount}</p>

        <form action="${pageContext.request.contextPath}/checkout" method="post">
            <button type="submit">Confirmer l'Emprunt et Finaliser</button>
        </form>

    </c:if>
    
    <c:if test="${empty borrowedList.borrowedBooks and not empty sessionScope.message}">
        <p style="color:green;">${message}</p>
        <p>Votre session d'emprunt a été finalisée. Merci!</p>
    </c:if>
    
    <p><a href="${pageContext.request.contextPath}/catalog">Retourner au Catalogue</a></p>
</body>
</html>