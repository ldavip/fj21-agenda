<%-- 
    Document   : index
    Created on : Jan 13, 2018, 8:30:03 PM
    Author     : dev
--%>

<%@page import="java.sql.Connection"%>
<%@page import="br.com.caelum.jdbc.ConnectionFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="cabecalho.jsp" />
    <div class="jumbotron">
        <h1>Página Principal</h1>
    </div>
    <ul>
        <a href="mvc?logica=AdicionaContatoLogic"><li>Adiciona Contato</li></a>
        <a href="mvc?logica=ListaContatosLogic"><li>Lista Contatos</li></a>
    </ul>
<c:import url="rodape.jsp"></c:import>