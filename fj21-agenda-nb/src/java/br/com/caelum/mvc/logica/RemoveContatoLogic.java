package br.com.caelum.mvc.logica;

import br.com.caelum.dao.ContatoDao;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveContatoLogic implements Logica {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {

        long id = Long.parseLong(req.getParameter("id"));

        Connection connection = (Connection) req.getAttribute("connection");

        ContatoDao dao = new ContatoDao(connection);
        dao.removeContato(id);

        System.out.println("Excluindo contato ...");

        return "mvc?logica=ListaContatosLogic";
    }

}
