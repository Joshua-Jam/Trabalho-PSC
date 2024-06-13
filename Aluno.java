public class Aluno {
    private String nome;
    private String[] materias;
    private double[][] notas;
    private int numeroFaltas;
    private String classificacaoFinal;

    public Aluno(String nome, String[] materias, double[][] notas, int numeroFaltas) {
        this.nome = nome;
        this.materias = materias;
        this.notas = notas;
        this.numeroFaltas = numeroFaltas;
    }

    public void setClassificacaoFinal(String classificacaoFinal) {
        this.classificacaoFinal = classificacaoFinal;
    }

    public String getNome() {
        return nome;
    }

    public String[] getMaterias() {
        return materias;
    }

    public double[][] getNotas() {
        return notas;
    }

    public int getNumeroFaltas() {
        return numeroFaltas;
    }

    public String getClassificacaoFinal() {
        return classificacaoFinal;
    }
}