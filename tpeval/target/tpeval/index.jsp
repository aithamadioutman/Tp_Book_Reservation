<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Système d'Emprunt de Livres</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">

</head>
<body>
    <h1>Bienvenue au Système d'Emprunt de Livres</h1>
    
    <c:if test="${not empty message}">
        <p style="color:green;">${message}</p>
    </c:if>

    <ul>
        <li><a href="${pageContext.request.contextPath}/catalog">Voir le Catalogue des Livres</a></li>
        <li><a href="${pageContext.request.contextPath}/borrowed">Voir mes Livres Empruntés</a></li>
        <li><a href="${pageContext.request.contextPath}/checkout">Passer à la Page de Paiement</a></li>
    </ul>
    
    <p>Architecture: MVC (Servlet: Controller, JSP/JSTL: View, JavaBeans: Model)</p>
</body>
</html>