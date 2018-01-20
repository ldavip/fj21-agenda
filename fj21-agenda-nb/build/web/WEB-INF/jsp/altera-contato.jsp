<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="caelum" %>
<html>
    <head>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    </head>
    <body>
        <c:import url="cabecalho.jsp"></c:import>
            <form action="salvaContato">
                <label for="id">Nome: </label>
                <input type="text" id="id" name="id" value="${contato.id}">
                <br>
                <label for="nome">Nome: </label>
                <input type="text" id="nome" name="nome" value="${contato.nome}">
                <br>
                <label for="email">Email: </label>
                <input type="email" id="email" name="email" placeholder="email@example.com" value="${contato.email}">
                <br>
                <label for="endereco">Endereço: </label>
                <input type="text" id="endereco" name="endereco" value="${contato.endereco}">
                <br>
                <label for="dataNascimento">Data de Nascimento: </label>
                <caelum:campoData id="dataNascimento" value="${contato.dataNascimento.time}" />
            <br>
            <button type="submit">Gravar</button> | <a href="javascript:history.back()">Voltar</a>
        </form>
        <c:import url="rodape.jsp"></c:import>
    </body>
</html>

