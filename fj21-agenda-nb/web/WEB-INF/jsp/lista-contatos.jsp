<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <style>
            td, th {
                padding-left: 20px;
            }
        </style>
    </head>
    <body>
        <c:import url="cabecalho.jsp"/>
        <h2>Lista Contatos</h2>
        <a href="mvc?logica=AdicionaContatoLogic">Novo Contato</a>
        <form>
            <input type="hidden" name="logica" value="${param.logica}" />
            <label for='filtro'>Filtro: </label>
            <select id="tipoFiltro" name="tipoFiltro">
                <option value="nome">Nome</option>
                <option value="email">E-mail</option>
                <option value="endereco">Endereço</option>
            </select>
            <script>
                var select = document.getElementById('tipoFiltro');
                select.value = "${not empty param.tipoFiltro ? param.tipoFiltro : 'nome'}";
            </script>
            <input id="filtro" name='filtro' value='${param.filtro}' />
            <input type="submit" value="Filtrar" /> | 
            <a href="mvc?logica=ListaContatosLogic">Voltar para lista</a>
        </form>
        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Nome</th>
                    <th>E-mail</th>
                    <th>Endereço</th>
                    <th>Data de Nascimento</th>
                    <th>Operações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="contato" items="${contatos}">
                    <tr>
                        <th>${contato.id}</th>
                        <td>${contato.nome}</td>
                        <td>
                            <c:if test="${not empty contato.email }">
                                <a href="mailto:${contato.email}">${contato.email}</a>
                            </c:if> 
                            <c:if test="${empty contato.email }">
                                E-mail não cadastrado
                            </c:if>
                        </td>
                        <td>${contato.endereco}</td>
                        <td>
                            <fmt:formatDate value="${contato.dataNascimento.time}" pattern="dd/MM/yyyy" />
                        </td>
                        <td>
                            <a href="mvc?logica=AlteraContatoLogic&id=${contato.id}"><i class="glyphicon glyphicon-pencil" title="Alterar"></i></a> |
                            <a href="mvc?logica=RemoveContatoLogic&id=${contato.id}"><i class="glyphicon glyphicon-trash" title="Remover"></i></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <c:import url="rodape.jsp" />
    </body>
</html>