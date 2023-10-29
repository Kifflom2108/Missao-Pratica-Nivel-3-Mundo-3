package cadastrobd.model;

public class PessoaJuridica extends Pessoa {
    private String Cnpj;

    public String getCnpj() {
        return Cnpj;
    }

    public void setCnpj(String cnpj) {
        Cnpj = cnpj;
    }

    public PessoaJuridica() {
    }

    public PessoaJuridica(int idPessoas, String NomePessoas, String EnderecoPessoas, String ContatoPessoas, String Cnpj) {
        super(idPessoas, NomePessoas, EnderecoPessoas, ContatoPessoas);
        this.Cnpj = Cnpj;
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CNPJ: " + Cnpj);
    }
}
