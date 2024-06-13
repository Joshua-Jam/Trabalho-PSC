import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SistemaEscolar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Coleta do nome do aluno
        System.out.print("Digite o nome do aluno: ");
        String nomeAluno = scanner.nextLine();

        // Coleta das matérias
        String[] materias = new String[3];
        for (int i = 0; i < 3; i++) {
            System.out.print("Digite o nome da matéria " + (i + 1) + ": ");
            materias[i] = scanner.nextLine();
        }

        // Coleta das notas e validação
        double[][] notas = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("Digite a nota " + (j + 1) + " para a matéria " + materias[i] + ": ");
                double nota = scanner.nextDouble();
                if (nota >= 30) {
                    System.err.println("Erro: Nota inválida. Notas devem ser menores que 30.");
                    return;
                }
                notas[i][j] = nota;
            }
        }

        // Coleta do número de faltas
        System.out.print("Digite o número de faltas: ");
        int numeroFaltas = scanner.nextInt();

        // Instancia o aluno
        Aluno aluno = new Aluno(nomeAluno, materias, notas, numeroFaltas);

        // Calcula as médias e verifica a classificação
        String classificacao = calcularClassificacao(aluno);
        aluno.setClassificacaoFinal(classificacao);

        // Salva as informações no arquivo
        salvarInformacoesNoArquivo(aluno);

        // Imprime a classificação final
        System.out.println("Classificação Final: " + aluno.getClassificacaoFinal());
    }

    public static String calcularClassificacao(Aluno aluno) {
        double[] medias = new double[3];
        int reprovadas = 0;

        for (int i = 0; i < 3; i++) {
            double somaNotas = 0;
            for (int j = 0; j < 3; j++) {
                somaNotas += aluno.getNotas()[i][j];
            }
            medias[i] = somaNotas / 3;

            if (medias[i] <= 70) {
                reprovadas++;
            }
        }

        if (aluno.getNumeroFaltas() >= 10) {
            return "Reprovado por faltas";
        } else if (reprovadas >= 2) {
            return "Reprovado por notas";
        } else if (reprovadas == 1) {
            return "Recuperação";
        } else {
            return "Aprovado";
        }
    }

    public static void salvarInformacoesNoArquivo(Aluno aluno) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("informacoes_aluno.txt"))) {
            writer.write("Nome do Aluno: " + aluno.getNome() + "\n");
            writer.write("Matérias e Notas:\n");

            for (int i = 0; i < 3; i++) {
                writer.write(aluno.getMaterias()[i] + ": ");
                for (int j = 0; j < 3; j++) {
                    writer.write(aluno.getNotas()[i][j] + " ");
                }
                writer.write("\n");
            }

            writer.write("Número de Faltas: " + aluno.getNumeroFaltas() + "\n");
            writer.write("Classificação Final: " + aluno.getClassificacaoFinal() + "\n");
        } catch (IOException e) {
            System.err.println("Erro ao salvar as informações no arquivo.");
            e.printStackTrace();
        }
    }
}