package br.com.caelum.dao;

import br.com.caelum.jdbc.ConnectionFactory;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.caelum.model.Contato;

public class ContatoDao {

    private Connection connection;

    public enum Parameter {
        ID, NOME, EMAIL, ENDERECO, DATA_NASCIMENTO;

        public static Parameter getParameter(String name) {
            for (Parameter value : Parameter.values()) {
                if (name.toUpperCase().equals(value.toString())) {
                    return value;
                }
            }
            return null;
        }
    }

    public ContatoDao() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }

    public ContatoDao(Connection connection) {
        this.connection = connection;
    }

    public void adiciona(Contato contato) throws SQLException {
        String sql = "insert into contatos (nome, email, endereco, dataNascimento) " + "values (?, ?, ?, ?)";
        Parameter[] parametros = new Parameter[]{Parameter.NOME, Parameter.EMAIL, Parameter.ENDERECO,
            Parameter.DATA_NASCIMENTO};

        PreparedStatement pst = connection.prepareStatement(sql);
        setParameters(pst, parametros, contato);
        String query = pst.toString();
        System.out.println(pst);

        try {
            connection.setAutoCommit(false);
            pst.execute();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            pst.close();
        }

    }

    public Contato busca(Long id) throws SQLException {
        String sql = "select * from contatos where id = ? ";

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, id);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            return getContato(rs);
        }

        throw new SQLException("Contato não encontrado");
    }

    public List<Contato> busca(String nome) throws SQLException {
        List<Contato> contatos = new ArrayList<Contato>();
        String sql = "select * from contatos where nome like ? ";

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, "%" + nome + "%");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            contatos.add(getContato(rs));
        }
        return contatos;
    }

    public List<Contato> getLista(Parameter filtro, String valor) throws SQLException {
        String sql = "select * from contatos "
                + "where ";
        switch (filtro) {
            case NOME:
                sql += "nome like ?";
                break;
            case EMAIL:
                sql += "email like ?";
                break;
            case ENDERECO:
                sql += "endereco like ?";
                break;
        }
        PreparedStatement pst = connection.prepareStatement(sql);
        setParameter(pst, 1, filtro, "%" + valor + "%");

        ResultSet rs = pst.executeQuery();
        List<Contato> lista = new ArrayList<Contato>();
        while (rs.next()) {
            lista.add(getContato(rs));
        }
        rs.close();
        return lista;
    }

    public List<Contato> getLista() throws SQLException {
        List<Contato> contatos = new ArrayList<Contato>();

        String sql = "select * from contatos ";
        ResultSet rs = connection.prepareStatement(sql).executeQuery();

        while (rs.next()) {
            contatos.add(getContato(rs));
        }
        rs.close();

        return contatos;
    }

    public void altera(Contato contato) throws SQLException {
        String sql = "update contatos " + "set " + "nome = ?, " + "email = ?, " + "endereco = ?, "
                + "dataNascimento= ? " + "where id = ? ";
        Parameter[] parametros = new Parameter[]{Parameter.NOME, Parameter.EMAIL, Parameter.ENDERECO,
            Parameter.DATA_NASCIMENTO, Parameter.ID};

        PreparedStatement pst = connection.prepareStatement(sql);
        setParameters(pst, parametros, contato);

        try {
            connection.setAutoCommit(false);
            pst.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            pst.close();
        }
    }

    public void removeContato(Contato contato) throws SQLException {
        removeContato(contato.getId());
    }

    public void removeContato(long id) throws SQLException {
        String sql = "delete from contatos where id = ? ";

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, id);

        try {
            connection.setAutoCommit(false);
            pst.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            pst.close();
        }
    }

    private void setParameters(PreparedStatement pst, Parameter[] parameters, Contato contato) throws SQLException {
        int cont = 1;
        for (int i = 0; i < parameters.length; i++) {
            setParameter(pst, cont++, parameters[i], contato);
        }
    }

    private void setParameter(PreparedStatement pst, int index, Parameter parameter, Contato contato)
            throws SQLException {
        Object value;
        switch (parameter) {
            case ID:
                value = contato.getId();
                pst.setLong(index, (long) value);
                break;
            case NOME:
                value = contato.getNome();
                pst.setString(index, (String) value);
                break;
            case EMAIL:
                value = contato.getEmail();
                pst.setString(index, (String) value);
                break;
            case ENDERECO:
                value = contato.getEndereco();
                pst.setString(index, (String) value);
                break;
            case DATA_NASCIMENTO:
                value = contato.getDataNascimento();
                pst.setDate(index, new Date(((Calendar) value).getTimeInMillis()));
                break;
        }
    }

    private void setParameter(PreparedStatement pst, int index, Parameter parameter, Object value) throws SQLException {
        switch (parameter) {
            case ID:
                pst.setLong(index, (long) value);
                break;
            case NOME:
                pst.setString(index, (String) value);
                break;
            case EMAIL:
                pst.setString(index, (String) value);
                break;
            case ENDERECO:
                pst.setString(index, (String) value);
                break;
            case DATA_NASCIMENTO:
                pst.setDate(index, new Date(((Calendar) value).getTimeInMillis()));
                break;
        }
    }

    private Contato getContato(ResultSet rs) throws SQLException {
        Contato c = new Contato();
        c.setId(rs.getLong("id"));
        c.setNome(rs.getString("nome"));
        c.setEmail(rs.getString("email"));
        c.setEndereco(rs.getString("endereco"));

        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.setTime(rs.getDate("dataNascimento"));
        c.setDataNascimento(dataNascimento);
        return c;
    }
}
