<%-- 
    Document   : contato-adicionado
    Created on : Jan 16, 2018, 10:03:40 PM
    Author     : dev
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:import url="cabecalho.jsp"></c:import>
        Contato <strong>${param.nome}</strong> adicionado com sucesso
        <br />
        <a href="mvc?logica=ListaContatosLogic">Voltar para a lista</a>
        <c:import url="rodape.jsp"></c:import>
    </body>
</html>
