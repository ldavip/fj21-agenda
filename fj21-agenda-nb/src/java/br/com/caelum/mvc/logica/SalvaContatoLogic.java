package br.com.caelum.mvc.logica;

import br.com.caelum.dao.ContatoDao;
import br.com.caelum.model.Contato;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SalvaContatoLogic implements Logica {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        long id = 0;
        if (req.getParameter("id") != null) {
            id = Long.parseLong(req.getParameter("id"));
        }
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String endereco = req.getParameter("endereco");
        String dataTexto = req.getParameter("dataNascimento");

        Calendar dataNascimento = Calendar.getInstance();

        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataTexto);
            dataNascimento.setTime(date);
        } catch (Exception e) {
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dataTexto);
                dataNascimento.setTime(date);
            } catch (Exception e1) {
                e.printStackTrace();
                throw new ServletException(e1);
            }
        }

        Contato contato = new Contato();
        contato.setNome(nome);
        contato.setEmail(email);
        contato.setEndereco(endereco);
        contato.setDataNascimento(dataNascimento);

        try {
            ContatoDao dao = new ContatoDao();
            if (id != 0) {
                contato.setId(id);
                dao.altera(contato);
            } else {
                dao.adiciona(contato);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
        
        return "/WEB-INF/jsp/lista-contatos.jsp";
    }
    
}
