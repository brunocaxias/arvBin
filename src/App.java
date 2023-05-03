import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        boolean sair = false;
        boolean excluido;
        Aluno busca, aux;


        ArvoreBinaria arvoreBinariaNome = new ArvoreBinaria<Aluno>(new ComparadorAlunoPorNome());
        ArvoreBinaria arvoreBinariaMatricula = new ArvoreBinaria<Aluno>(new ComparadorAlunoPorMatricula());
        ArvoreBinariaAVL arvoreAVLNome = new ArvoreBinariaAVL<Aluno>(new ComparadorAlunoPorNome());
        ArvoreBinariaAVL arvoreAVLMatricula = new ArvoreBinariaAVL<Aluno>(new ComparadorAlunoPorMatricula());

        long tempoInicial = System.currentTimeMillis();
        List<Aluno> alunos = Menu.lerArquivo("entradaOrdenada10.txt");

        for (Aluno aluno : alunos) {
            arvoreBinariaNome.inserir(aluno);
            arvoreBinariaMatricula.inserir(aluno);
            arvoreAVLNome.inserir(aluno);
            arvoreAVLMatricula.inserir(aluno);
        }
        long tempoFinal = System.currentTimeMillis();
        System.out.println("Tempo Total de geração da arvore em ms: " + (tempoFinal - tempoInicial));

        while (!sair) {
            Menu.clearConsole();
            Menu.showMenu();

            int opcao = scanner.nextInt();
            scanner.nextLine(); // consome a quebra de linha deixada pelo nextInt()

            switch (opcao) {

                case 1: // Busca por matricula
                    
                    Menu.clearConsole();
                    System.out.println("Opção 1 selecionada: Efetuar Busca por Matricula");

                    System.out.print("Digite a matrícula do aluno: ");
                    aux = new Aluno(scanner.nextInt());
                    arvoreBinariaMatricula.search(aux);
                    busca = (Aluno) arvoreAVLMatricula.search(aux);

                    if (busca == null) {
                        System.out.println("Aluno não encontrado");
                    } else {
                        System.out.println(busca.toString());
                    }

                    System.out.println("Aperte ENTER para voltar ao menu");
                    scanner.nextLine();
                    scanner.nextLine();
                    break;
                case 2: // Busca por nome
                    Menu.clearConsole();
                    System.out.println("Opção 2 selecionada: Efetuar Busca por Nome");
                    System.out.print("Digite o nome do aluno: ");
                    aux = new Aluno(scanner.nextLine());
                    busca = (Aluno) arvoreBinariaNome.search(aux);
                    arvoreAVLNome.search(aux);

                    if (busca == null) {
                        System.out.println("Aluno não encontrado");
                    } else {
                        System.out.println(busca.toString());
                    } // busca

                    System.out.println("Aperte ENTER para voltar ao menu");
                    scanner.nextLine();
                    break;
                case 3: // Excluir por matricula
                    Menu.clearConsole();

                    System.out.println("Opção 3 selecionada: Excluir por Matricula");
                    System.out.print("Digite a matrícula do aluno: ");

                    busca = (Aluno) arvoreBinariaMatricula.search(new Aluno(scanner.nextInt()));

                    excluido = arvoreBinariaMatricula.delete(busca);
                    arvoreBinariaNome.delete(busca);
                    arvoreAVLMatricula.delete(busca);
                    arvoreAVLNome.delete(busca);

                    if (!excluido) {
                        System.out.println("No nao encontrado");
                    }
                    System.out.println("Aperte ENTER para voltar ao menu");
                    scanner.nextLine();
                    scanner.nextLine();
                    break;
                case 4: // Excluir por nome
                    Menu.clearConsole();
                    System.out.println("Opção 4 selecionada: Excluir por Nome");
                    System.out.print("Digite o nome do aluno: ");

                    busca = (Aluno) arvoreBinariaNome.search(new Aluno(scanner.nextLine()));
                    excluido = arvoreBinariaNome.delete(busca);
                    arvoreBinariaMatricula.delete(busca);
                    arvoreAVLMatricula.delete(busca);
                    arvoreAVLNome.delete(busca);

                    if (!excluido) {
                        System.out.println("No nao encontrado");
                    }
                    System.out.println("Aperte ENTER para voltar ao menu");
                    scanner.nextLine();
                    break;
                case 5: // Adicionar aluno
                    Menu.clearConsole();
                    System.out.println("Opção 5 selecionada: Incluir Aluno");

                    String nome;
                    int matricula, nota;

                    System.out.print("Digite o nome do aluno: ");
                    nome = scanner.nextLine();

                    System.out.print("Digite a matrícula do aluno: ");
                    matricula = scanner.nextInt();

                    System.out.print("Digite a nota do aluno: ");
                    nota = scanner.nextInt();

                    Aluno aluno = new Aluno(nome, matricula, nota);

                    arvoreBinariaNome.inserir(aluno);
                    arvoreBinariaMatricula.inserir(aluno);
                    arvoreAVLNome.inserir(aluno);
                    arvoreAVLNome.inserir(aluno);

                    break;
                case 6: // Estatistica por nome
                    Menu.clearConsole();
                    System.out.println("Opção 6 selecionada: Exibir Estatística por Nome");
                    System.out.println(
                            "\nA quantidade de elementos na arvore de nomes é: "
                                    + arvoreBinariaNome.getQuantElementos());
                    System.out.println("A altura da arvore de nomes é: " + arvoreBinariaNome.getAltura());
                    System.out.println("O maior termo da arvore de nomes é: "
                            + arvoreBinariaNome.getMaiorValor().getValor().toString());
                    System.out.println("O menor termo da arvore de nomes é: "
                            + arvoreBinariaNome.getMenorValor().getValor().toString());

                    System.out.println(
                            "\nA quantidade de elementos na arvore AVL de nomes é: "
                                    + arvoreAVLNome.getQuantElementos());
                    System.out.println("A altura da arvore AVL de nomes é: " + arvoreAVLNome.getAltura());
                    System.out.println("O maior termo da arvore AVL de nomes é: "
                            + arvoreAVLNome.getMaiorValor().getValor().toString());
                    System.out.println("O menor termo da arvore AVL de nomes é: "
                            + arvoreAVLNome.getMenorValor().getValor().toString());

                    System.out.println("Aperte ENTER para voltar ao menu");
                    scanner.nextLine();
                    break;
                case 7: // Estatisica por matricula
                    Menu.clearConsole();
                    System.out.println("Opção 7 selecionada: Exibir Estatística por Matricula");

                    System.out.println(
                            "\nA quantidade de elementos na arvore de matriculas é: "
                                    + arvoreBinariaMatricula.getQuantElementos());
                    System.out.println("A altura da arvore de matriculas é: " + arvoreBinariaMatricula.getAltura());
                    System.out.println("O maior termo da arvore de matriculas é: "
                            + arvoreBinariaMatricula.getMaiorValor().getValor().toString());
                    System.out.println("O menor termo da arvore de matriculas é: "
                            + arvoreBinariaMatricula.getMenorValor().getValor().toString());

                    System.out.println(
                            "\nA quantidade de elementos na arvore AVL de matriculas é: "
                                    + arvoreAVLMatricula.getQuantElementos());
                    System.out.println("A altura da arvore AVL de matriculas é: " + arvoreAVLMatricula.getAltura());
                    System.out.println("O maior termo da arvore AVL de matriculas é: "
                            + arvoreAVLMatricula.getMaiorValor().getValor().toString());
                    System.out.println("O menor termo da arvore AVL de matriculas é: "
                            + arvoreAVLMatricula.getMenorValor().getValor().toString());

                    System.out.println("Aperte ENTER para voltar ao menu");
                    scanner.nextLine();
                    break;
                case 8: // Printar a arvore
                    Menu.clearConsole();
                    System.out.println("Opção 8 selecionada: Printar Arvore");

                    System.out.println("\nPrint da Arvore de Matricula");
                    arvoreBinariaMatricula.print();

                    System.out.println("\nPrint da Arvore AVL de Matricula");
                    arvoreAVLMatricula.print();

                    System.out.println("\nPrint da Arvore de Nomes");
                    arvoreBinariaNome.print();

                    System.out.println("\nPrint da Arvore AVL de Nomes");
                    arvoreAVLNome.print();

                    System.out.println("\nAperte ENTER para voltar ao menu");
                    scanner.nextLine();
                    break;
                case 9: // Sair do programa
                    System.out.println("Opção 9 selecionada: Sair");
                    arvoreBinariaMatricula.escreverArvoreEmOrdem("saidaArquivo.txt");
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
        scanner.close();

    }
}
