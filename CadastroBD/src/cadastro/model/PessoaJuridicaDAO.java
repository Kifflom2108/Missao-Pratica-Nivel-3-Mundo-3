package cadastro.model;

import cadastrobd.model.PessoaJuridica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaDAO {
    private Connection connection;

    public PessoaJuridicaDAO(Connection connection) {
        this.connection = connection;
    }

    public PessoaJuridica getPessoaJuridica(int id) throws SQLException {
        String sql = "SELECT pj.idPessoasJuridicas, pj.Cnpj, p.NomePessoas, p.EndereçoPessoas, p.ContatoPessoas " +
                "FROM PessoaJuridica pj " +
                "INNER JOIN Pessoas p ON pj.idPessoasJuridicas = p.idPessoas " +
                "WHERE pj.idPessoasJuridicas = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int pessoaId = resultSet.getInt("idPessoasJuridicas");
                    String cnpj = resultSet.getString("Cnpj");
                    String nome = resultSet.getString("NomePessoas");
                    String endereco = resultSet.getString("EndereçoPessoas");
                    String contato = resultSet.getString("ContatoPessoas");

                    return new PessoaJuridica(pessoaId, nome, endereco, contato, cnpj);
                }
            }
        }
        return null;
    }

    public List<PessoaJuridica> getPessoasJuridicas() throws SQLException {
        List<PessoaJuridica> pessoas = new ArrayList<>();
        String sql = "SELECT pj.idPessoasJuridicas, pj.Cnpj, p.NomePessoas, p.EndereçoPessoas, p.ContatoPessoas " +
                "FROM PessoaJuridica pj " +
                "INNER JOIN Pessoas p ON pj.idPessoasJuridicas = p.idPessoas";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int pessoaId = resultSet.getInt("idPessoasJuridicas");
                String cnpj = resultSet.getString("Cnpj");
                String nome = resultSet.getString("NomePessoas");
                String endereco = resultSet.getString("EndereçoPessoas");
                String contato = resultSet.getString("ContatoPessoas");

                pessoas.add(new PessoaJuridica(pessoaId, nome, endereco, contato, cnpj));
            }
        }
        return pessoas;
    }

    public void inclui(PessoaJuridica pessoa) throws SQLException {
        String sqlInserirPessoas = "INSERT INTO Pessoas (NomePessoas, EndereçoPessoas, ContatoPessoas) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInserirPessoas, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, pessoa.getNomePessoas());
            preparedStatement.setString(2, pessoa.getEnderecoPessoas());
            preparedStatement.setString(3, pessoa.getContatoPessoas());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int pessoaId = generatedKeys.getInt(1);

                    String sqlInserirPessoaJuridica = "INSERT INTO PessoaJuridica (idPessoasJuridicas, Cnpj) VALUES (?, ?)";
                    try (PreparedStatement psPessoaJuridica = connection.prepareStatement(sqlInserirPessoaJuridica)) {
                        psPessoaJuridica.setInt(1, pessoaId);
                        psPessoaJuridica.setString(2, pessoa.getCnpj());
                        psPessoaJuridica.executeUpdate();
                    }
                }
            }
        }
    }

    public void altera(PessoaJuridica pessoa) throws SQLException {
        String sqlAtualizaPessoas = "UPDATE Pessoas SET NomePessoas = ?, EndereçoPessoas = ?, ContatoPessoas = ? WHERE idPessoas = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlAtualizaPessoas)) {
            preparedStatement.setString(1, pessoa.getNomePessoas());
            preparedStatement.setString(2, pessoa.getEnderecoPessoas());
            preparedStatement.setString(3, pessoa.getContatoPessoas());
            preparedStatement.setInt(4, pessoa.getIdPessoas());
            preparedStatement.executeUpdate();
        }

        String sqlAtualizaPessoaJuridica = "UPDATE PessoaJuridica SET Cnpj = ? WHERE idPessoasJuridicas = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlAtualizaPessoaJuridica)) {
            preparedStatement.setString(1, pessoa.getCnpj());
            preparedStatement.setInt(2, pessoa.getIdPessoas());
            preparedStatement.executeUpdate();
        }
    }

    public void exclui(int id) throws SQLException {
        String sqlExcluirPessoaJuridica = "DELETE FROM PessoaJuridica WHERE idPessoasJuridicas = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlExcluirPessoaJuridica)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }

        String sqlExcluirPessoas = "DELETE FROM Pessoas WHERE idPessoas = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlExcluirPessoas)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
