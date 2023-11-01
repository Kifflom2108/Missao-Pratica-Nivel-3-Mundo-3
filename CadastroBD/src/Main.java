import cadastro.model.PessoaFisicaDAO;
import cadastro.model.PessoaJuridicaDAO;
import cadastro.model.util.ConectorBD;
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = ConectorBD.getConnection();
            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO(connection);
            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO(connection);

            Scanner scanner = new Scanner(System.in);
            int opcao;

            do {
                System.out.println("Menu Principal:");
                System.out.println("1. Incluir");
                System.out.println("2. Alterar");
                System.out.println("3. Excluir");
                System.out.println("4. Obter por ID");
                System.out.println("5. Obter todos");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");

                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println("Escolha o tipo:");
                        System.out.println("1. Pessoa Física");
                        System.out.println("2. Pessoa Jurídica");
                        int tipoIncluir = scanner.nextInt();
                        scanner.nextLine();

                        if (tipoIncluir == 1) {
                            PessoaFisica novaPessoaFisica = lerPessoaFisicaDoUsuario(scanner);
                            pessoaFisicaDAO.inclui(novaPessoaFisica);
                            System.out.println("Pessoa física incluída com sucesso.");
                        } else if (tipoIncluir == 2) {
                            PessoaJuridica novaPessoaJuridica = lerPessoaJuridicaDoUsuario(scanner);
                            pessoaJuridicaDAO.inclui(novaPessoaJuridica);
                            System.out.println("Pessoa jurídica incluída com sucesso.");
                        } else {
                            System.out.println("Opção inválida. Tente novamente.");
                        }
                        break;
                    case 2:

                        System.out.println("Escolha o tipo:");
                        System.out.println("1. Pessoa Física");
                        System.out.println("2. Pessoa Jurídica");
                        int tipoAlterar = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Digite o ID da pessoa a ser alterada: ");
                        int idPessoaParaAlterar = scanner.nextInt();
                        scanner.nextLine();

                        if (tipoAlterar == 1) {
                            PessoaFisica pessoaFisicaParaAlterar = pessoaFisicaDAO.getPessoa(idPessoaParaAlterar);
                            if (pessoaFisicaParaAlterar != null) {
                                atualizarPessoaFisica(pessoaFisicaParaAlterar, scanner);
                                pessoaFisicaDAO.altera(pessoaFisicaParaAlterar);
                                System.out.println("Pessoa física alterada com sucesso.");
                            } else {
                                System.out.println("Pessoa física não encontrada para alteração.");
                            }
                        } else if (tipoAlterar == 2) {
                            PessoaJuridica pessoaJuridicaParaAlterar = pessoaJuridicaDAO.getPessoaJuridica(idPessoaParaAlterar);
                            if (pessoaJuridicaParaAlterar != null) {
                                atualizarPessoaJuridica(pessoaJuridicaParaAlterar, scanner);
                                pessoaJuridicaDAO.altera(pessoaJuridicaParaAlterar);
                                System.out.println("Pessoa jurídica alterada com sucesso.");
                            } else {
                                System.out.println("Pessoa jurídica não encontrada para alteração.");
                            }
                        } else {
                            System.out.println("Opção inválida. Tente novamente.");
                        }
                        break;
                    case 3:
                        System.out.println("Escolha o tipo:");
                        System.out.println("1. Pessoa Física");
                        System.out.println("2. Pessoa Jurídica");
                        int tipoExcluir = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Digite o ID da pessoa a ser excluída: ");
                        int idPessoaParaExcluir = scanner.nextInt();
                        scanner.nextLine();

                        if (tipoExcluir == 1) {
                            pessoaFisicaDAO.exclui(idPessoaParaExcluir);
                            System.out.println("Pessoa física excluída com sucesso.");
                        } else if (tipoExcluir == 2) {
                            pessoaJuridicaDAO.exclui(idPessoaParaExcluir);
                            System.out.println("Pessoa jurídica excluída com sucesso.");
                        } else {
                            System.out.println("Opção inválida. Tente novamente.");
                        }
                        break;
                    case 4:
                        System.out.println("Escolha o tipo:");
                        System.out.println("1. Pessoa Física");
                        System.out.println("2. Pessoa Jurídica");
                        int tipoObter = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Digite o ID da pessoa a ser obtida: ");
                        int idPessoaParaObter = scanner.nextInt();
                        scanner.nextLine();

                        if (tipoObter == 1) {
                            PessoaFisica pessoaFisicaObtida = pessoaFisicaDAO.getPessoa(idPessoaParaObter);
                            if (pessoaFisicaObtida != null) {
                                pessoaFisicaObtida.exibir();
                            } else {
                                System.out.println("Pessoa física não encontrada.");
                            }
                        } else if (tipoObter == 2) {
                            PessoaJuridica pessoaJuridicaObtida = pessoaJuridicaDAO.getPessoaJuridica(idPessoaParaObter);
                            if (pessoaJuridicaObtida != null) {
                                pessoaJuridicaObtida.exibir();
                            } else {
                                System.out.println("Pessoa jurídica não encontrada.");
                            }
                        } else {
                            System.out.println("Opção inválida. Tente novamente.");
                        }
                        break;
                    case 5:
                        System.out.println("Escolha o tipo:");
                        System.out.println("1. Pessoa Física");
                        System.out.println("2. Pessoa Jurídica");
                        int tipoObterTodos = scanner.nextInt();
                        scanner.nextLine();

                        if (tipoObterTodos == 1) {
                            List<PessoaFisica> todasPessoasFisicas = pessoaFisicaDAO.getPessoas();
                            System.out.println("Todas as pessoas físicas:");
                            for (PessoaFisica pessoa : todasPessoasFisicas) {
                                pessoa.exibir();
                            }
                        } else if (tipoObterTodos == 2) {
                            List<PessoaJuridica> todasPessoasJuridicas = pessoaJuridicaDAO.getPessoasJuridicas();
                            System.out.println("Todas as pessoas jurídicas:");
                            for (PessoaJuridica pessoa : todasPessoasJuridicas) {
                                pessoa.exibir();
                            }
                        } else {
                            System.out.println("Opção inválida. Tente novamente.");
                        }
                        break;
                    case 0:
                        System.out.println("Saindo do programa.");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } while (opcao != 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(connection);
        }
    }

    private static PessoaFisica lerPessoaFisicaDoUsuario(Scanner scanner) {
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Digite o contato: ");
        String contato = scanner.nextLine();
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();

        return new PessoaFisica(0,nome, endereco, contato, cpf);
    }

    private static PessoaJuridica lerPessoaJuridicaDoUsuario(Scanner scanner) {
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Digite o contato: ");
        String contato = scanner.nextLine();
        System.out.print("Digite o CNPJ: ");
        String cnpj = scanner.nextLine();

        return new PessoaJuridica(0,nome, endereco, contato, cnpj);
    }

    private static void atualizarPessoaFisica(PessoaFisica pessoa, Scanner scanner) {
        System.out.print("Digite o nome: ");
        pessoa.setNomePessoas(scanner.nextLine());
        System.out.print("Digite o endereço: ");
        pessoa.setEnderecoPessoas(scanner.nextLine());
        System.out.print("Digite o contato: ");
        pessoa.setContatoPessoas(scanner.nextLine());
        System.out.print("Digite o CPF: ");
        pessoa.setCpf(scanner.nextLine());
    }

    private static void atualizarPessoaJuridica(PessoaJuridica pessoa, Scanner scanner) {
        System.out.print("Digite o nome: ");
        pessoa.setNomePessoas(scanner.nextLine());
        System.out.print("Digite o endereço: ");
        pessoa.setEnderecoPessoas(scanner.nextLine());
        System.out.print("Digite o contato: ");
        pessoa.setContatoPessoas(scanner.nextLine());
        System.out.print("Digite o CNPJ: ");
        pessoa.setCnpj(scanner.nextLine());
    }
}
