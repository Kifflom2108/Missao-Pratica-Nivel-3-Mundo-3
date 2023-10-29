    package cadastrobd.model;

    public class PessoaFisica extends Pessoa {
        private String Cpf;

        public String getCpf() {
            return Cpf;
        }

        public void setCpf(String cpf) {
            Cpf = cpf;
        }

        public PessoaFisica() {
        }


        public PessoaFisica(int idPessoas, String NomePessoas, String EnderecoPessoas, String ContatoPessoas, String Cpf) {
            super(idPessoas, NomePessoas, EnderecoPessoas, ContatoPessoas);
            this.Cpf = Cpf;
        }

        @Override
        public void exibir() {
            super.exibir();
            System.out.println("CPF: " + Cpf);
        }
    }
