package cadastro.model;

import cadastrobd.model.PessoaFisica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDAO {
    private Connection connection;

    public PessoaFisicaDAO(Connection connection) {
        this.connection = connection;
    }

    public PessoaFisica getPessoa(int id) throws SQLException {
        String sql = "SELECT pf.idPessoasFisicas, pf.Cpf, p.NomePessoas, p.EndereçoPessoas, p.ContatoPessoas " +
                "FROM PessoaFisica pf " +
                "INNER JOIN Pessoas p ON pf.idPessoasFisicas = p.idPessoas " +
                "WHERE pf.idPessoasFisicas = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int pessoaId = resultSet.getInt("idPessoasFisicas");
                    String cpf = resultSet.getString("Cpf");
                    String nome = resultSet.getString("NomePessoas");
                    String endereco = resultSet.getString("EndereçoPessoas");
                    String contato = resultSet.getString("ContatoPessoas");

                    return new PessoaFisica(pessoaId, nome, endereco, contato, cpf);
                }
            }
        }
        return null;
    }

    public List<PessoaFisica> getPessoas() throws SQLException {
        List<PessoaFisica> pessoas = new ArrayList<>();
        String sql = "SELECT pf.idPessoasFisicas, pf.Cpf, p.NomePessoas, p.EndereçoPessoas, p.ContatoPessoas " +
                "FROM PessoaFisica pf " +
                "INNER JOIN Pessoas p ON pf.idPessoasFisicas = p.idPessoas";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int pessoaId = resultSet.getInt("idPessoasFisicas");
                String cpf = resultSet.getString("Cpf");
                String nome = resultSet.getString("NomePessoas");
                String endereco = resultSet.getString("EndereçoPessoas");
                String contato = resultSet.getString("ContatoPessoas");

                pessoas.add(new PessoaFisica(pessoaId, nome, endereco, contato, cpf));
            }
        }
        return pessoas;
    }

    public void inclui(PessoaFisica pessoa) throws SQLException {
        String sqlInserirPessoas = "INSERT INTO Pessoas (NomePessoas, EndereçoPessoas, ContatoPessoas) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInserirPessoas, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, pessoa.getNomePessoas());
            preparedStatement.setString(2, pessoa.getEnderecoPessoas());
            preparedStatement.setString(3, pessoa.getContatoPessoas());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int pessoaId = generatedKeys.getInt(1);

                    String sqlInserirPessoaFisica = "INSERT INTO PessoaFisica (idPessoasFisicas, Cpf) VALUES (?, ?)";
                    try (PreparedStatement psPessoaFisica = connection.prepareStatement(sqlInserirPessoaFisica)) {
                        psPessoaFisica.setInt(1, pessoaId);
                        psPessoaFisica.setString(2, pessoa.getCpf());
                        psPessoaFisica.executeUpdate();
                    }
                }
            }
        }
    }

    public void altera(PessoaFisica pessoa) throws SQLException {
        String sqlAtualizaPessoas = "UPDATE Pessoas SET NomePessoas = ?, EndereçoPessoas = ?, ContatoPessoas = ? WHERE idPessoas = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlAtualizaPessoas)) {
            preparedStatement.setString(1, pessoa.getNomePessoas());
            preparedStatement.setString(2, pessoa.getEnderecoPessoas());
            preparedStatement.setString(3, pessoa.getContatoPessoas());
            preparedStatement.setInt(4, pessoa.getIdPessoas());
            preparedStatement.executeUpdate();
        }

        String sqlAtualizaPessoaFisica = "UPDATE PessoaFisica SET Cpf = ? WHERE idPessoasFisicas = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlAtualizaPessoaFisica)) {
            preparedStatement.setString(1, pessoa.getCpf());
            preparedStatement.setInt(2, pessoa.getIdPessoas());
            preparedStatement.executeUpdate();
        }
    }

    public void exclui(int id) throws SQLException {
        String sqlExcluirPessoaFisica = "DELETE FROM PessoaFisica WHERE idPessoasFisicas = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlExcluirPessoaFisica)) {
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
