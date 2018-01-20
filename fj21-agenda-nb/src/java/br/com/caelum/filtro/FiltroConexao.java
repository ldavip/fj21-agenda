package br.com.caelum.filtro;

import br.com.caelum.jdbc.ConnectionFactory;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class FiltroConexao implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            Connection connection = ConnectionFactory.getConnection();

            request.setAttribute("connection", connection);

            chain.doFilter(request, response);

            connection.close();
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void destroy() {

    }

}
