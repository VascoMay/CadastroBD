/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastrobd;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaJuridicaDAO;
import java.util.List;
import java.util.Scanner;


public class CadastroBDTeste {

    public static void main(String[] args) {
//        PessoaFisica pessoaFisica = new PessoaFisica("Marcos", "Rua 7", "Londrina", "PR", "43 1234-5678", "teste@gmail.com", "11122233344");
//        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
//        
//        pessoaFisicaDAO.incluir(pessoaFisica);
//        
//        pessoaFisica.setEmail("Marcos@gmail.com");
//        
//        pessoaFisicaDAO.alterar(pessoaFisica);
//        
//        List<PessoaFisica> listaPessoasFisicas = pessoaFisicaDAO.getPessoas();
//        
//        for(PessoaFisica p : listaPessoasFisicas){
//            p.exibir();
//        }
//
//        pessoaFisicaDAO.excluir(pessoaFisica.getId());
//        
//        PessoaJuridica pessoaJuridica = new PessoaJuridica("EMPRESA Marcos LTDA", "Rua 29", "Londrina", "PR", "43 1234-5678", "teste@gmail.com", "52775121000109");
//        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
//        
//        pessoaJuridicaDAO.incluir(pessoaJuridica);
//        
//        pessoaJuridica.setCnpj("87794913000105");
//        
//        pessoaJuridicaDAO.alterar(pessoaJuridica);
//        
//        List<PessoaJuridica> listaPessoasJuridicas = pessoaJuridicaDAO.getPessoas();
//        
//        for(PessoaJuridica p : listaPessoasJuridicas){ p.exibir(); }
//        
//        pessoaJuridicaDAO.excluir(pessoaJuridica.getId());
        
        
        PessoaFisicaDAO pfDAO = new PessoaFisicaDAO();
        PessoaJuridicaDAO pjDAO = new PessoaJuridicaDAO();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Incluir");
            System.out.println("2. Alterar");
            System.out.println("3. Excluir");
            System.out.println("4. Exibir pelo ID");
            System.out.println("5. Exibir todos");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha
            try {
                switch (opcao) {
                    case 1:
                        incluir(scanner, pfDAO, pjDAO);
                        break;
                    case 2:
                        alterar(scanner, pfDAO, pjDAO);
                        break;
                    case 3:
                        excluir(scanner, pfDAO, pjDAO);
                        break;
                    case 4:
                        exibirPeloId(scanner, pfDAO, pjDAO);
                        break;
                    case 5:
                        exibirTodos(scanner, pfDAO, pjDAO);
                        break;
                    case 0:
                        System.out.println("Finalizando...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.err.println("Ocorreu um erro: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static void incluir(Scanner scanner, PessoaFisicaDAO pfDAO, PessoaJuridicaDAO pjDAO) {
        System.out.print("Tipo (1 - Física, 2 - Jurídica): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        if (tipo == 1) {
            PessoaFisica pf = new PessoaFisica();
            System.out.print("Nome: ");
            pf.setNome(scanner.nextLine());
            System.out.print("Logradouro: ");
            pf.setLogradouro(scanner.nextLine());
            System.out.print("Cidade: ");
            pf.setCidade(scanner.nextLine());
            System.out.print("Estado: ");
            pf.setEstado(scanner.nextLine());
            System.out.print("Telefone: ");
            pf.setTelefone(scanner.nextLine());
            System.out.print("Email: ");
            pf.setEmail(scanner.nextLine());
            System.out.print("CPF: ");
            pf.setCpf(scanner.nextLine());
            pfDAO.incluir(pf);
            System.out.println("Pessoa física incluída com sucesso.");
        } else if (tipo == 2) {
            PessoaJuridica pj = new PessoaJuridica();
            System.out.print("Nome: ");
            pj.setNome(scanner.nextLine());
            System.out.print("Logradouro: ");
            pj.setLogradouro(scanner.nextLine());
            System.out.print("Cidade: ");
            pj.setCidade(scanner.nextLine());
            System.out.print("Estado: ");
            pj.setEstado(scanner.nextLine());
            System.out.print("Telefone: ");
            pj.setTelefone(scanner.nextLine());
            System.out.print("Email: ");
            pj.setEmail(scanner.nextLine());
            System.out.print("CNPJ: ");
            pj.setCnpj(scanner.nextLine());
            pjDAO.incluir(pj);
            System.out.println("Pessoa jurídica incluída com sucesso.");
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void alterar(Scanner scanner, PessoaFisicaDAO pfDAO, PessoaJuridicaDAO pjDAO) {
        System.out.print("Tipo (1 - Física, 2 - Jurídica): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        if (tipo == 1) {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            PessoaFisica pf = pfDAO.getPessoa(id);
            if (pf == null) {
                System.out.println("Pessoa física não encontrada.");
                return;
            }
            System.out.println("Dados atuais:");
            pf.exibir();
            System.out.print("Nome: ");
            pf.setNome(scanner.nextLine());
            System.out.print("Logradouro: ");
            pf.setLogradouro(scanner.nextLine());
            System.out.print("Cidade: ");
            pf.setCidade(scanner.nextLine());
            System.out.print("Estado: ");
            pf.setEstado(scanner.nextLine());
            System.out.print("Telefone: ");
            pf.setTelefone(scanner.nextLine());
            System.out.print("Email: ");
            pf.setEmail(scanner.nextLine());
            System.out.print("CPF: ");
            pf.setCpf(scanner.nextLine());
            pfDAO.alterar(pf);
            System.out.println("Pessoa física alterada com sucesso.");
        } else if (tipo == 2) {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); 

            PessoaJuridica pj = pjDAO.getPessoa(id);
            if (pj == null) {
                System.out.println("Pessoa jurídica não encontrada.");
                return;
            }

            System.out.println("Dados atuais:");
            pj.exibir();

            System.out.print("Nome: ");
            pj.setNome(scanner.nextLine());
            System.out.print("Logradouro: ");
            pj.setLogradouro(scanner.nextLine());
            System.out.print("Cidade: ");
            pj.setCidade(scanner.nextLine());
            System.out.print("Estado: ");
            pj.setEstado(scanner.nextLine());
            System.out.print("Telefone: ");
            pj.setTelefone(scanner.nextLine());
            System.out.print("Email: ");
            pj.setEmail(scanner.nextLine());
            System.out.print("CNPJ: ");
            pj.setCnpj(scanner.nextLine());
            pjDAO.alterar(pj);
            System.out.println("Pessoa jurídica alterada com sucesso.");
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void excluir(Scanner scanner, PessoaFisicaDAO pfDAO, PessoaJuridicaDAO pjDAO) {
        System.out.print("Tipo (1 - Física, 2 - Jurídica): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); 
        if (tipo == 1) {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            if (pfDAO.excluir(id)) {
                System.out.println("Pessoa física excluída com sucesso.");
            } else {
                System.out.println("Erro ao excluir pessoa física.");
            }
        } else if (tipo == 2) {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); 

            if (pjDAO.excluir(id)) {
                System.out.println("Pessoa jurídica excluída com sucesso.");
            } else {
                System.out.println("Erro ao excluir pessoa jurídica.");
            }
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void exibirPeloId(Scanner scanner, PessoaFisicaDAO pfDAO, PessoaJuridicaDAO pjDAO) {
        System.out.print("Tipo (1 - Física, 2 - Jurídica): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); 

            PessoaFisica pf = pfDAO.getPessoa(id);
            if (pf != null) {
                pf.exibir();
            } else {
                System.out.println("Pessoa física não encontrada.");
            }
        } else if (tipo == 2) {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            PessoaJuridica pj = pjDAO.getPessoa(id);
            if (pj != null) {
                pj.exibir();
            } else {
                System.out.println("Pessoa jurídica não encontrada.");
            }
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void exibirTodos(Scanner scanner, PessoaFisicaDAO pfDAO, PessoaJuridicaDAO pjDAO) {
        System.out.print("Tipo (1 - Física, 2 - Jurídica): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            List<PessoaFisica> pessoasFisicas = pfDAO.getPessoas();
            for (PessoaFisica pessoa : pessoasFisicas) {
                pessoa.exibir();
            }
        } else if (tipo == 2) {
            List<PessoaJuridica> pessoasJuridicas = pjDAO.getPessoas();
            for (PessoaJuridica pessoa : pessoasJuridicas) {
                pessoa.exibir();
            }
        } else {
            System.out.println("Tipo inválido.");
        }
    }
}
