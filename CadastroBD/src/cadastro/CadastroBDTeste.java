package cadastro;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;
import cadastro.model.PessoaFisicaDAO;
import cadastro.model.PessoaJuridicaDAO;
import cadastro.model.util.ConectorBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CadastroBDTeste {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = ConectorBD.getConnection();
            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO(connection);
            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO(connection);

            PessoaFisica novaPessoaFisica = new PessoaFisica(0,"NomePessoaFisica", "EnderecoPessoaFisica", "ContatoPessoaFisica", "123578900");
            pessoaFisicaDAO.inclui(novaPessoaFisica);
            System.out.println("Pessoa física incluída com sucesso.");

            int idPessoaFisicaParaAlterar = 1;
            PessoaFisica pessoaFisicaParaAlterar = pessoaFisicaDAO.getPessoa(idPessoaFisicaParaAlterar);
            if (pessoaFisicaParaAlterar != null) {
                pessoaFisicaParaAlterar.setNomePessoas("NovoNome");
                pessoaFisicaParaAlterar.setEnderecoPessoas("NovoEndereco");
                pessoaFisicaParaAlterar.setContatoPessoas("NovoContato");
                pessoaFisicaParaAlterar.setCpf("987610");
                pessoaFisicaDAO.altera(pessoaFisicaParaAlterar);
                System.out.println("Pessoa física alterada com sucesso.");
            } else {
                System.out.println("Pessoa física não encontrada para alteração.");
            }

            List<PessoaFisica> todasPessoasFisicas = pessoaFisicaDAO.getPessoas();
            System.out.println("Todas as pessoas físicas:");
            for (PessoaFisica pessoa : todasPessoasFisicas) {
                pessoa.exibir();
            }

            int idPessoaFisicaParaExcluir = 1;
            pessoaFisicaDAO.exclui(idPessoaFisicaParaExcluir);
            System.out.println("Pessoa física excluída com sucesso.");

            PessoaJuridica novaPessoaJuridica = new PessoaJuridica(0,"NomePessoaJuridica", "EnderecoPessoaJuridica", "ContatoJuridica", "123456790234");
            pessoaJuridicaDAO.inclui(novaPessoaJuridica);
            System.out.println("Pessoa jurídica incluída com sucesso.");

            int idPessoaJuridicaParaAlterar = 1;
            PessoaJuridica pessoaJuridicaParaAlterar = pessoaJuridicaDAO.getPessoaJuridica(idPessoaJuridicaParaAlterar);
            if (pessoaJuridicaParaAlterar != null) {
                pessoaJuridicaParaAlterar.setNomePessoas("NovoNomeJuridica");
                pessoaJuridicaParaAlterar.setEnderecoPessoas("NovoEnderecoJuridica");
                pessoaJuridicaParaAlterar.setContatoPessoas("NovoJuridica");
                pessoaJuridicaParaAlterar.setCnpj("9875234");
                pessoaJuridicaDAO.altera(pessoaJuridicaParaAlterar);
                System.out.println("Pessoa jurídica alterada com sucesso.");
            } else {
                System.out.println("Pessoa jurídica não encontrada para alteração.");
            }

            List<PessoaJuridica> todasPessoasJuridicas = pessoaJuridicaDAO.getPessoasJuridicas();
            System.out.println("Todas as pessoas jurídicas:");
            for (PessoaJuridica pessoa : todasPessoasJuridicas) {
                pessoa.exibir();
            }

            int idPessoaJuridicaParaExcluir = 1;
            pessoaJuridicaDAO.exclui(idPessoaJuridicaParaExcluir);
            System.out.println("Pessoa jurídica excluída com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(connection);
        }
    }
}
