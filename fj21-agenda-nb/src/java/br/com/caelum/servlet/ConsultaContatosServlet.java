package br.com.caelum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.dao.ContatoDao;
import br.com.caelum.model.Contato;

@WebServlet("/listaContatos")
public class ConsultaContatosServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			ContatoDao dao = new ContatoDao();
			List<Contato> lista = dao.getLista();
			
			PrintWriter out = res.getWriter();
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Lista contatos</title>");
			out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css' integrity='sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy' crossorigin='anonymous'>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Lista de Contatos</h1>");
			out.println("<table class='table table-striped'>");
			out.println("<thead>");
			out.println("  <tr>");
			out.println("    <th scope='row'>Id</th>");
			out.println("    <th scope='col'>Nome</th>");
			out.println("    <th scope='col'>Email</th>");
			out.println("    <th scope='col'>Endereço</th>");
			out.println("    <th scope='col'>Data de Nascimento</th>");
			out.println("  </tr>");
			out.println("</thead>");
			out.println("<tbody>");
			
			for (Contato contato : lista) {
				out.println("  <tr>");
				out.println("    <th scope='row'>" + contato.getId() + "</th>");
				out.println("    <td>" + contato.getNome() + "</td>");
				out.println("    <td>" + contato.getEmail() + "</td>");
				out.println("    <td>" + contato.getEndereco() + "</td>");
				out.println("    <td>" + new java.text.SimpleDateFormat("dd/MM/yyyy").format(contato.getDataNascimento().getTime()) + "</td>");
				out.println("  </tr>");
			}
			out.println("</tbody>");
			out.println("</table>");
			out.println("</body>");
			
			out.println("</html>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
