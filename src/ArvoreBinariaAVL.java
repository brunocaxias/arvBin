import java.util.Comparator;

public class ArvoreBinariaAVL<T> extends ArvoreBinaria<T> {

    /**
     * Construtor da classe chamando o contrutor pai e passando o comparator como
     * parametro
     * 
     * @param comparator
     */
    public ArvoreBinariaAVL(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    protected No<T> inserir(T valor, No<T> no) {
        no = super.inserir(valor, no);

        if(raiz.fatorBalanceamento() > 1){
            if(raiz.getDireita().fatorBalanceamento() > 0){
                raiz = this.rotacaoEsquerda(raiz);
            }else{
                raiz = this.rotacaoDireitaEsquerda(raiz);
            }
        }else if(raiz.fatorBalanceamento() < -1){
            if(raiz.getEsquerda().fatorBalanceamento() < 0){
                raiz = this.rotacaoDireita(raiz);
            }else{
                raiz = this.rotacaoEsquerdaDireita(raiz);
            }
        }
        return raiz;
    }

    ///////////////////////////////////////////////////
    /// ///
    /// Metodos de rotacao ///
    // ///
    ///////////////////////////////////////////////////

    private No<T> rotacaoEsquerda(No<T> r) {

        No<T> f = r.getDireita();

        r.setDireita(f.getEsquerda());
        f.setEsquerda(r);
        // Retorno do no filho que passou a ser raiz (r = raiz / f = filho)
        return f;
    }

    private No<T> rotacaoDireita(No<T> r) {

        No<T> f = r.getEsquerda();
        r.setEsquerda(f.getDireita());
        f.setDireita(r);

        // Retorno do no filho que passou a ser raiz (r = raiz / f = filho)
        return f;
    }

    private No<T> rotacaoDireitaEsquerda(No<T> r) {

        // Rotacao esquerda no filho a esquerda
        r.setEsquerda(rotacaoEsquerda(r.getEsquerda()));
        // Rotacao direita na raiz
        return rotacaoDireita(r);
    }

    private No<T> rotacaoEsquerdaDireita(No<T> r) {

        // Rotacao a direita do filho a direita
        r.setDireita(rotacaoDireita(r.getDireita()));
        // Rotacao esquerda na raiz
        return rotacaoEsquerda(r);
    }

}
