public class No<T> {

    private T valor;
    private No<T> esquerda, direita;

    /**
     * 
     * @param valor
     * @param esquerda
     * @param direita
     */
    public No(T valor, No<T> esquerda, No<T> direita) {
        this.valor = valor;
        this.esquerda = esquerda;
        this.direita = direita;
        int altura = 1;
    }

    /**
     * 
     * @param valor
     */
    public No(T valor) {
        this.valor = valor;
    }

    /**
     * 
     * @return valor
     */
    public T getValor() {
        return valor;
    }

    /**
     * 
     * @param valor
     */
    public void setValor(T valor) {
        this.valor = valor;
    }

    /**
     * 
     * @return filho a esquerda
     */
    public No<T> getEsquerda() {
        return esquerda;
    }

    /**
     * 
     * @param esquerda
     */
    public void setEsquerda(No<T> esquerda) {
        this.esquerda = esquerda;
    }

    /**
     * 
     * @return filho a direita
     */
    public No<T> getDireita() {
        return direita;
    }

    /**
     * 
     * @param direita
     */
    public void setDireita(No<T> direita) {
        this.direita = direita;
    }

    ///////////////////////////////////////////////////////////////
    /// ///
    /// Metodos para calculo de fator de balanceamento ///
    /// ///
    ///////////////////////////////////////////////////////////////

    public int obterAltura(){
        return obterAltura(this);       
    }
    
    private int obterAltura(No<T> r){
        if(r==null)
            return -1;
        else{
            int hd = obterAltura(r.getDireita());
            int he = obterAltura(r.getEsquerda());
            if (hd > he)
                return hd+1;
            else
                return he+1;
        }
    }
    
    
    public int fatorBalanceamento(){
        return obterAltura(this.direita) - obterAltura(this.esquerda);
    }

}
