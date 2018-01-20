package br.com.caelum.mvc.logica;

import br.com.caelum.dao.ContatoDao;
import br.com.caelum.model.Contato;
import java.sql.Connection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListaContatosLogic implements Logica {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String filtro = req.getParameter("filtro") != null ? req.getParameter("filtro") : "";
        String tipoFiltro = req.getParameter("tipoFiltro");

        Connection connection = (Connection) req.getAttribute("connection");
        ContatoDao dao = new ContatoDao(connection);

        List<Contato> contatos = null;
        try {
            if (filtro.isEmpty()) {
                contatos = dao.getLista();
            } else {
                ContatoDao.Parameter parametro = ContatoDao.Parameter.getParameter(tipoFiltro);
                contatos = dao.getLista(parametro, filtro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute("contatos", contatos);

        return "/WEB-INF/jsp/lista-contatos.jsp";
    }

}
