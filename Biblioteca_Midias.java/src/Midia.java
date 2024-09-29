public abstract class Midia {
    public String titulo;
    public int tamanho; //Tamanho em minutos para filmes e músicas
    public int anoLancamento;
    private boolean emExecucao;

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
        emExecucao = true; // Define que a mídia está em execução
        System.out.println("Iniciando " + titulo);
        // Simula a reprodução da mídia
        for (int i = 0; i < tamanho && emExecucao; i++) {
            System.out.println((i + 1) + " minutos corridos do filme " + titulo);
            try {
                Thread.sleep(1000); // Simula a passagem de tempo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void pausar() throws MsgException {
        emExecucao = false; // Para a execução da mídia
        System.out.println("Pausando " + titulo);
    }

    public void parar() throws MsgException {
        emExecucao = false; // Para a execução da mídia
        System.out.println("Parando " + titulo);
    }

    public boolean isEmExecucao() {
        return emExecucao;
    }
    
}
