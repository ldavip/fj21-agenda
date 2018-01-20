package br.com.caelum.mvc.logica;

import br.com.caelum.dao.ContatoDao;
import br.com.caelum.model.Contato;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlteraContatoLogic implements Logica {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {

        long id = Long.parseLong(req.getParameter("id"));
        
        Contato contato = new ContatoDao().busca(id);
        req.setAttribute("contato", contato);
        
        return "/WEB-INF/jsp/altera-contato.jsp";
    }

}
