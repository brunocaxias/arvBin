import java.util.Comparator;

/*
 * Esse arquivo é multiclasse e possui as classes Aluno,
 * ComparadorAlunoPorMatricula e ComparadorAlunoPorNome
 */
public class Aluno implements Comparable<Aluno> {

    private String nome;
    private int matricula;
    private int nota;

    /**
     * Construtor completo da classe Aluno
     * 
     * @param nome      nome do aluno
     * @param matricula número da matrícula do aluno
     * @param nota      nota do aluno
     */
    public Aluno(String nome, int matricula, int nota) {
        this.nome = nome;
        this.matricula = matricula;
        this.nota = nota;
    }

    
    /**
     * Construtor auxiliar para metodos 
     * @param matricula
     */
    public Aluno(int matricula) {
        this.matricula = matricula;
    }


    /**
     * Construtor auxiliar para metodos
     * @param nome
     */
    public Aluno(String nome) {
        this.nome = nome;
    }



    /**
     * Retorna o nome do aluno
     * 
     * @return nome do aluno
     */
    public String getNome() {
        return nome;
    }

    /**
     * Altera o nome do aluno
     * 
     * @param nome novo nome do aluno
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna a matrícula do aluno
     * 
     * @return matrícula do aluno
     */
    public int getMatricula() {
        return matricula;
    }

    /**
     * Altera a matrícula do aluno
     * 
     * @param matricula nova matrícula do aluno
     */
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    /**
     * Retorna a nota do aluno
     * 
     * @return nota do aluno
     */
    public int getNota() {
        return nota;
    }

    /**
     * Altera a nota do aluno
     * 
     * @param nota nova nota do aluno
     */
    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Aluno aluno = (Aluno) obj;
        return matricula == aluno.matricula;
    }

    @Override
    public int compareTo(Aluno aluno) {
        return Integer.compare(matricula, aluno.matricula);
    }

    @Override
    public String toString() {
        return (this.matricula+ ";" + this.nome + ";" + this.nota);
    }
}

class ComparadorAlunoPorMatricula implements Comparator<Aluno> {

    @Override
    public int compare(Aluno aluno1, Aluno aluno2) {
        return Integer.compare(aluno1.getMatricula(), aluno2.getMatricula());
    }
}

class ComparadorAlunoPorNome implements Comparator<Aluno> {

    @Override
    public int compare(Aluno aluno1, Aluno aluno2) {
        return aluno1.getNome().compareTo(aluno2.getNome());
    }
}
