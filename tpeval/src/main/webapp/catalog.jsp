<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Catalogue des Livres</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">

</head>
<body>
    <h1>Catalogue des Livres Disponibles</h1>

    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>

    <table border="1">  
        <thead>
            <tr>
                <th>Titre</th>
                <th>Auteur</th>
                <th>Format</th>
                <th>Copies Dispo.</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="book" items="${catalog}">
                <tr>
                    <td>${book.title}</td>
                    <td>${book.author}</td>
                    <td>${book.format}</td>
                    <td>${book.availableCopies}</td>
                    <td>
                        <c:choose>
                            <c:when test="${book.availableCopies > 0}">
                                <form action="<c:url value='/reserve' />" method="post">
                                    <input type="hidden" name="isbn" value="${book.isbn}">
                                    <button type="submit">Emprunter</button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <button disabled>Indisponible</button>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <p><a href="<c:url value='/borrowed' />">Voir mes emprunts</a></p>
</body>
</html>
