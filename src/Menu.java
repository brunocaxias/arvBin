import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void showMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Efetuar Busca por Matricula");
        System.out.println("2. Efetuar Busca por Nome");
        System.out.println("3. Excluir por Matricula");
        System.out.println("4. Excluir por Nome");
        System.out.println("5. Incluir Aluno");
        System.out.println("6. Exibir Estatística por Nome");
        System.out.println("7. Exibir Estatística por Matricula");
        System.out.println("8. Printar Arvore");
        System.out.println("9. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("bash", "-c", "clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException ex) {
            System.err.println("Erro ao limpar o console: " + ex.getMessage());
        }
    }

    public static List<Aluno> lerArquivo(String caminhoArquivo) {
        // Inicializa a lista de alunos a ser retornada
        List<Aluno> alunos = new ArrayList<>();

        // Abre o arquivo
        File arquivo = new File(caminhoArquivo);

        try (Scanner scanner = new Scanner(arquivo)) {

            // Enquanto houver linhas para ler no arquivo
            while (scanner.hasNextLine()) {
                // Lê a próxima linha do arquivo
                String linha = scanner.nextLine();
                // Divide a linha em três atributos separados por ";"
                String[] atributos = linha.split(";");
                // Verifica se a linha contém os três atributos esperados
                if (atributos.length == 3) {
                    // Converte o primeiro atributo (matrícula) para um número inteiro
                    int matricula = Integer.parseInt(atributos[0]);
                    // Obtém o segundo atributo (nome completo)
                    String nomeCompleto = atributos[1];
                    // Converte o terceiro atributo (nota) para um número inteiro
                    int nota = Integer.parseInt(atributos[2]);

                    // Adiciona um novo objeto Aluno à lista de alunos
                    alunos.add(new Aluno(nomeCompleto, matricula, nota));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
        } catch (NumberFormatException e) {
            System.out.println("Erro na leitura do arquivo: formato inválido.");
        }

        // Retorna a lista de alunos lidos do arquivo
        return alunos;
    }
}
