public abstract class Midia {
    public String titulo;
    public int tamanho; //Tamanho em minutos para filmes e músicas
    public int anoLancamento;

    Midia(String titulo, int tamanho, int anoLancamento) {
        this.titulo = titulo;
        this.tamanho = tamanho;
        this.anoLancamento = anoLancamento;
    }

    // Getters
    public int getTamanho() {
        return this.tamanho;
    }

    public int getAnoLancamento() {
        return this.anoLancamento;
    }

    public String getTitulo() {
        return this.titulo;
    }

    
    public void exibirDetalhes() {
        System.out.println("Título: " + titulo + ", Tamanho: " + tamanho + " min, Ano de Lançamento: " + anoLancamento);
    }

    //Inicio reprodução da mídia, pode lançar exceção personalizada
    public void iniciar() throws MsgException {
        System.out.println("Iniciando reprodução de: " + titulo);
        //Simulo uma interrupção
        if (tamanho > 120) {
            throw new MsgException("Erro: Reprodução foi interrompida.");
        }
    }

    //parar reprodução da mídia
    public void parar() throws MsgException {
        System.out.println("Parando reprodução de: " + titulo);
    }

    //P]]ausar reprodução da mídia
    public void pausar() throws MsgException {
        System.out.println("Pausando reprodução de: " + titulo);
    }
}
