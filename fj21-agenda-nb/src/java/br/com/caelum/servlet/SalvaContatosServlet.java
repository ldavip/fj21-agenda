package br.com.caelum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.dao.ContatoDao;
import br.com.caelum.model.Contato;
import java.sql.Connection;
import java.util.List;
import javax.servlet.RequestDispatcher;

@WebServlet("/salvaContato")
public class SalvaContatosServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
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
                e1.printStackTrace();
                return;
            }
        }

        Contato contato = new Contato();
        contato.setNome(nome);
        contato.setEmail(email);
        contato.setEndereco(endereco);
        contato.setDataNascimento(dataNascimento);

        try {
            Connection connection = (Connection) req.getAttribute("connection");
            ContatoDao dao = new ContatoDao(connection);
            
            String pagina = "";
            if (id == 0) {
                dao.adiciona(contato);
                pagina = "/WEB-INF/jsp/contato-adicionado.jsp";
            } else {
                contato.setId(id);
                dao.altera(contato);
                pagina = "mvc?logica=ListaContatosLogic";
            }
            
            List<Contato> lista = dao.getLista();
            req.setAttribute("contatos", lista);

            RequestDispatcher rd = req.getRequestDispatcher(pagina);
            rd.forward(req, res);
        } catch (SQLException e) {
//            res.getWriter().println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
