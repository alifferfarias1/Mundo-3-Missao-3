package cadastrobd.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import cadastrobd.model.util.ConectorBD;

/**
 *
 * @author Aliffer
 */
public class PessoaFisicaDAO {

    private final ConectorBD connector;

    public PessoaFisicaDAO() {
        connector = new ConectorBD();
    }

    public PessoaFisica getPessoa(Integer pessoaId) throws SQLException {
        String sql = "SELECT pf.FK_Pessoa_idPessoa, pf.cpf, p.nome, p.endereco, p.cidade, p.estado, p.telefone, p.email "
            + "FROM PessoaFisica pf "
            + "INNER JOIN Pessoa p ON pf.FK_Pessoa_idPessoa = p.idPessoa "
            + "WHERE pf.FK_Pessoa_idPessoa = ?";
        try (Connection con = connector.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, pessoaId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new PessoaFisica(
                        rs.getInt("FK_Pessoa_idPessoa"),
                        rs.getString("nome"),
                        rs.getString("endereco"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cpf")
                    );
                }
            }
        }
        return null;
    }

    public ArrayList<PessoaFisica> getPessoas() throws SQLException {
        ArrayList<PessoaFisica> list = new ArrayList<>();
        String sql = "SELECT pf.FK_Pessoa_idPessoa, pf.cpf, p.nome, p.endereco, p.cidade, p.estado, p.telefone, p.email "
                + "FROM PessoaFisica pf "
                + "INNER JOIN Pessoa p ON pf.FK_Pessoa_idPessoa = p.idPessoa";
        try (Connection con = connector.getConnection(); PreparedStatement stmt =
            con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new PessoaFisica(
                    rs.getInt("FK_Pessoa_idPessoa"),
                    rs.getString("nome"),
                    rs.getString("endereco"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cpf")));
            }
        }
        return list;
    }

    public int incluir(PessoaFisica pessoaFisica) throws SQLException {
        if (pessoaFisica.getNomeCompleto() == null || pessoaFisica.getNomeCompleto().trim().isEmpty()) {
            throw new IllegalArgumentException("'nomeCompleto' cannot be empty or null.");
        }
        String sqlInsertPessoa = "INSERT INTO Pessoa(nome, endereco, cidade, estado, telefone, email) VALUES(?, ?, ?, ?, ?, ?)";
        String sqlInsertPessoaFisica = "INSERT INTO PessoaFisica(FK_Pessoa_idPessoa, cpf) VALUES(?, ?)";
        try (Connection con = connector.getConnection(); PreparedStatement stmtPessoa =
            con.prepareStatement(sqlInsertPessoa, Statement.RETURN_GENERATED_KEYS)) {
            String[] pessoaArray = {"", pessoaFisica.getNomeCompleto(), pessoaFisica.getEnderecoCompleto(), pessoaFisica.getCidade(), pessoaFisica.getEstado(), pessoaFisica.getTelefoneContato(), pessoaFisica.getEmailContato()};
            for(int i = 1; i < 7; i++) {
                stmtPessoa.setString(i, pessoaArray[i]);
            }
            if (stmtPessoa.executeUpdate() != 0) {
                System.out.println("INSERT INTO PessoaFisica success.");
            } else {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = stmtPessoa.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idNovaPessoa = generatedKeys.getInt(1);
                    pessoaFisica.setPessoaId(idNovaPessoa);
                    try (PreparedStatement stmtPessoaFisica = con.prepareStatement(sqlInsertPessoaFisica, Statement.RETURN_GENERATED_KEYS)) {
                        stmtPessoaFisica.setInt(1, idNovaPessoa);
                        stmtPessoaFisica.setString(2, pessoaFisica.getCpf());
                        stmtPessoaFisica.executeUpdate();
                    }
                    return idNovaPessoa;
                } else {
                    throw new SQLException("Creating user failed. No ID obtained.");
                }
            }
        }
    }

    public void alterar(PessoaFisica pessoaFisica) throws SQLException {
        String sqlUpdatePessoa = "UPDATE Pessoa SET nome = ?, endereco = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE idPessoa = ?;";
        String sqlUpdatePessoaFisica = "UPDATE PessoaFisica SET cpf = ? WHERE FK_Pessoa_idPessoa = ?;";
        try (Connection con = connector.getConnection();
            PreparedStatement stmtPessoa = con.prepareStatement(sqlUpdatePessoa);
            PreparedStatement stmtPessoaFisica = con.prepareStatement(sqlUpdatePessoaFisica)) {
            String[] pessoaArray = {"", pessoaFisica.getNomeCompleto(), pessoaFisica.getEnderecoCompleto(), pessoaFisica.getCidade(), pessoaFisica.getEstado(), pessoaFisica.getTelefoneContato(), pessoaFisica.getEmailContato()};
            for(int i = 1; i < 7; i++) {
            stmtPessoa.setString(i, pessoaArray[i]);
            }
            stmtPessoa.setInt(7, pessoaFisica.getPessoaId());
            stmtPessoa.executeUpdate();
            stmtPessoaFisica.setString(1, pessoaFisica.getCpf());
            stmtPessoaFisica.setInt(2, pessoaFisica.getPessoaId());
            stmtPessoaFisica.executeUpdate();
                                                }       
                                             }
            public void excluir(PessoaFisica pessoaFisica) throws SQLException {
            String sqlDeletePessoaFisica = "DELETE FROM PessoaFisica WHERE FK_Pessoa_idPessoa = ?;";
            String sqlDeletePessoa = "DELETE FROM Pessoa WHERE idPessoa = ?;";
            try (Connection con = connector.getConnection(); PreparedStatement stmtPessoaFisica = con.prepareStatement(sqlDeletePessoaFisica); PreparedStatement stmtPessoa = con.prepareStatement(sqlDeletePessoa)) {
                stmtPessoaFisica.setInt(1, pessoaFisica.getPessoaId());
                stmtPessoaFisica.executeUpdate();
                stmtPessoa.setInt(1, pessoaFisica.getPessoaId());
                stmtPessoa.executeUpdate();
                                        }
                                    }
                                            
            public void close() throws SQLException {
                connector.close();
                                }