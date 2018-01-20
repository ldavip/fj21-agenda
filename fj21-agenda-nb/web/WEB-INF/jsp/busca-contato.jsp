<%-- 
    Document   : busca-contato
    Created on : Jan 13, 2018, 10:34:02 PM
    Author     : dev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="cabecalho.jsp"></c:import>
<jsp:useBean id="dao" class="br.com.caelum.dao.ContatoDao"></jsp:useBean>
    <h2>Busca Contato</h2>
    <hr>
    <div class="container">
        <form class="form-inline">
            <div class="form-group row">
                <label for="nome" class="col-sm-2 col-form-label col-form-label-lg">Nome: </label>
                <div class="col-sm-10">
                    <input type="text" class="form-control form-control-lg" id="nome" name="nome">
                </div>
            </div>
            <button type="submit" class="btn btn-primary mb-2">Pesquisar</button>
        </form>

        <table class='table table-striped'>
            <thead>
                <tr>
                    <th scope='row'>Id</th>
                    <th scope='col'>Nome</th>
                    <th scope='col'>E-mail</th>
                    <th scope='col'>Endereço</th>
                    <th scope='col'>Data de Nascimento</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="contato" items="${dao.lista}">
                <tr>
                    <th scope='row' class="col-md-1">${contato.id}</th>
                    <td class="col-md-3">${contato.nome}</td>
                    <td class="col-md-3"><c:if test="${not empty contato.email }">
                            <a href="mailto:${contato.email}">${contato.email}</a>
                        </c:if> <c:if test="${empty contato.email }">
                            E-mail não cadastrado
                        </c:if></td>
                    <td class="col-md-3">${contato.endereco}</td>
                    <td class="col-md-2"><fmt:formatDate
                value="${contato.dataNascimento.time}" pattern="dd/MM/yyyy"
                type="date" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<hr>
<c:import url="rodape.jsp"></c:import>