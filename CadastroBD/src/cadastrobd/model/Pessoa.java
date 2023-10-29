package cadastrobd.model;

public class Pessoa {
    private int idPessoas;
    private String NomePessoas;
    private String EnderecoPessoas;
    private String ContatoPessoas;

    public int getIdPessoas() {
        return idPessoas;
    }

    public void setIdPessoas(int idPessoas) {
        this.idPessoas = idPessoas;
    }

    public String getNomePessoas() {
        return NomePessoas;
    }

    public void setNomePessoas(String nomePessoas) {
        NomePessoas = nomePessoas;
    }

    public String getEnderecoPessoas() {
        return EnderecoPessoas;
    }

    public void setEnderecoPessoas(String enderecoPessoas) {
        EnderecoPessoas = enderecoPessoas;
    }

    public String getContatoPessoas() {
        return ContatoPessoas;
    }

    public void setContatoPessoas(String contatoPessoas) {
        ContatoPessoas = contatoPessoas;
    }

    public Pessoa() {
    }


    public Pessoa(int idPessoas, String NomePessoas, String EnderecoPessoas, String ContatoPessoas) {
        this.idPessoas = idPessoas;
        this.NomePessoas = NomePessoas;
        this.EnderecoPessoas = EnderecoPessoas;
        this.ContatoPessoas = ContatoPessoas;
    }

    public void exibir() {
        System.out.println("ID: " + idPessoas);
        System.out.println("Nome: " + NomePessoas);
        System.out.println("Endereço: " + EnderecoPessoas);
        System.out.println("Contato: " + ContatoPessoas);
    }
}
