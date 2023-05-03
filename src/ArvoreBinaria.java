import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Bruno Carvalho Caxias
 */
public class ArvoreBinaria<T> {

    protected No<T> raiz;
    protected Comparator<T> comparator;
    protected int quantElementos;

    /**
     * 
     * @param comparator
     */
    public ArvoreBinaria(Comparator<T> comparator){
        this.raiz = null;
        this.quantElementos = 0;
        this.comparator = comparator;
    }


    public No<T> getRaiz() {
        return raiz;
    }


    public int getQuantElementos() {
        return quantElementos;
    }


    public void setQuantElementos(int quantElementos) {
        this.quantElementos = quantElementos;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void inserir(T valor) {
        if (this.raiz == null) {
            this.raiz = new No<T>(valor);
            quantElementos++;
        } else {
            this.raiz = inserir(valor, this.raiz);
        }
    }

    protected No<T> inserir(T valor, No<T> no) {
        if (comparator.compare(valor, no.getValor()) < 0) {
            if (no.getEsquerda() == null) {
                no.setEsquerda(new No<T>(valor)); 
                quantElementos++;
            } else {
                no.setEsquerda(inserir(valor, no.getEsquerda()));
            }
        } else {
            if (no.getDireita() == null) {
                no.setDireita(new No<T>(valor)); 
                quantElementos++;
            } else {
                no.setDireita(inserir(valor, no.getDireita()));
            }
        }

        no.atualizaAltura();
        return no;
    }

    /**
     * Metodo para deletar nos na arvore
     * @param value
     * @return
     */
    public boolean delete(T value) {

        // A arvore nao possui nos
        if (this.raiz == null) {
            return false;
        }
    
        No<T> pai = null;
        No<T> atual = this.raiz;
    
        while (atual != null) {
            int compare = comparator.compare(value, atual.getValor());

            // Valor encontrado
            if (compare == 0) {
                break;
            }
    
            pai = atual;

            // Condicional para definir qual no sera pego (Um if else poderia ser utilizado aqui tambem)
            atual = compare < 0 ? atual.getEsquerda() : atual.getDireita();
        }
        
        // No nao encontrado
        if (atual == null) {
            return false;
        }
    
        // Caso 1: O nó a ser removido não possui filhos
        if (atual.getEsquerda() == null && atual.getDireita() == null) {
            if (pai == null) {
                this.raiz = null;
            } else if (atual == pai.getEsquerda()) {
                pai.setEsquerda(null);
            } else {
                pai.setDireita(null);
            }
            System.out.println("Nó excluído: " + atual.getValor().toString());
        }
    
        // Caso 2: O nó a ser removido possui somente um filho
        else if (atual.getEsquerda() == null || atual.getDireita() == null) {

            // Condicional para definir qual no sera pego (Um if else poderia ser utilizado aqui tambem)
            No<T> filho = atual.getEsquerda() == null ? atual.getDireita() : atual.getEsquerda();
    
            if (pai == null) {
                this.raiz = filho;
            } else if (atual == pai.getEsquerda()) {
                pai.setEsquerda(filho);
            } else {
                pai.setDireita(filho);
            }
            System.out.println("Nó excluído: " + atual.getValor().toString());
        }
    
        // Caso 3: O nó a ser removido possui dois filhos
        else {
            No<T> successorPai = atual;
            // Pega o filho a direita do nó a ser deletado
            No<T> successor = atual.getDireita();
    
            // Procura na subarvore da direita o nó mais a esquerda
            while (successor.getEsquerda() != null) {
                successorPai = successor;
                successor = successor.getEsquerda();
            }

            // Print ocorre antes pois o nó tera seu valor alterado
            System.out.println("Nó excluído: " + atual.getValor().toString());

            // Altera o valor do nó que seria deletado para o do menor filho a esquerda da subarvore da direira
            atual.setValor(successor.getValor());
    
            // Cria um condicional colocando como nulo o menor filho a esquerda da subarvore da direita
            if (successorPai.getEsquerda() == successor) { //Verifica se o successorPai ta guardando realmento o termo alterado
                successorPai.setEsquerda(successor.getDireita()); // Se sim ele pega e coloca o filho a esquerda igual ao sucessor do filho a esquerda (nulo)
            } else {
                successorPai.setDireita(successor.getDireita()); 
            }
            
        }
    
        quantElementos--;
        return true;
    }
    
    /**
     * Metodo para uso public em outras classes
     * @return altura da arvore
     */
    public int getAltura() {
        return this.raiz.obterAltura();
    }

    /**
     * 
     * @param valor
     * @return valor do no procurado ou nulo caso nenhum no exista com o valor
     */
    public T search(T valor) {
        int nosPercorridos = 0;
        No<T> current = this.raiz;
        while (current != null) {
            int cmp = comparator.compare(valor, current.getValor());
            nosPercorridos++;
            if (cmp == 0) {
                System.out.println("A quantidade de nos percorridos foi "+nosPercorridos);
                return current.getValor();
            } else if (cmp < 0) {
                current = current.getEsquerda();
            } else {
                current = current.getDireita();
            }
        }
        System.out.println("A quantidade de nos percorridos foi "+nosPercorridos);
        return null;
    }

    /*////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     * 
     *  Funcoes de print e caminhada
     * 
     */////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void caminhaEmOrdem(){
        System.out.println("Saída do Caminhamento em Ordem");
        caminhaEmOrdem(this.raiz);
        System.out.println("Fim");
    }

    private void caminhaEmOrdem(No<T> raiz) {
        if(raiz!=null){
            caminhaEmOrdem(raiz.getEsquerda());
            System.out.print(raiz.getValor());
            System.out.print(", ");
            caminhaEmOrdem(raiz.getDireita());
        }
    }

    public void caminhaEmNivel() {
        if (this.raiz == null) {
            return;
        }
    
        Queue<No<T>> queue = new LinkedList<>();
        queue.add(this.raiz);
    
        while (!queue.isEmpty()) {
            No<T> node = queue.poll();
            System.out.print(node.getValor() + " ");
    
            if (node.getEsquerda() != null) {
                queue.add(node.getEsquerda());
            }
            if (node.getDireita() != null) {
                queue.add(node.getDireita());
            }
        }
    }

    public void print() {
        printTree(this.raiz, 0);
    }

    private void printTree(No<T> no, int level) {
        if (no == null){
            return;
        }
        printTree(no.getDireita(), level + 1);
        if (level != 0) {
            for (int i = 0; i < level - 1; i++)
                System.out.print("|\t");
            System.out.println("|-------" + no.getValor().toString());
        } else {
            System.out.println(no.getValor().toString());
        }
        printTree(no.getEsquerda(), level + 1);
    }

    public No<T> getMaiorValor(){
        if (raiz == null) {
            return null;
        }
    
        No<T> atual = raiz;
    
        while (atual.getDireita() != null) {
            atual = atual.getDireita();
        }

        return atual;

    }

    public No<T> getMenorValor(){

        if (raiz == null) {
            return null;
        }
    
        No<T> atual = raiz;
    
        while (atual.getEsquerda() != null) {
            atual = atual.getEsquerda();
        }

        return atual;
    }
    
    public  void escreverArvoreEmOrdem (String caminhoArquivo) {
        try (PrintWriter writer = new PrintWriter(new File(caminhoArquivo))) {
            escreverSubarvoreEmOrdem(this.getRaiz(), writer);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
        }
    }
    
    private  void escreverSubarvoreEmOrdem(No<T> no, PrintWriter writer) {
        if (no != null) {
            escreverSubarvoreEmOrdem(no.getEsquerda(), writer);
            writer.println(no.getValor().toString());
            escreverSubarvoreEmOrdem(no.getDireita(), writer);
        }
    }
}
