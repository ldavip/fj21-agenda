<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag import="java.text.SimpleDateFormat"%>
<%@tag import="java.util.Date"%>
<%@tag import="java.util.Calendar"%>
<%@tag description="Campo de data com calendário JQuery" pageEncoding="UTF-8"%>
<%@attribute name="id" required="true" type="java.lang.String" %>
<%@attribute name="value" required="false" type="java.util.Date" %>
<%
    String date = "";
    try {
        date = new SimpleDateFormat("dd/MM/yyyy").format(value);
    } catch (NullPointerException e) {
    }
%>
<input type="text" id="${id}" name="${id}" value="<%=date%>" />
<script>
    $(function () {
        $("#${id}").datepicker({
            dateFormat: "dd/mm/yy",
            changeMonth: true,
            changeYear: true
        });
    });
</script>