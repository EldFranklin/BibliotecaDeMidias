public abstract class Midia implements Controle {
    public String titulo;
    public int tamanho; //Tamanho em minutos para filmes e músicas
    public int anoLancamento;
    private boolean emExecucao;
    public boolean estaPausada;

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
        int minutosCorridos = 0; // Controle dos minutos já executados
    
        System.out.println("Iniciando " + titulo);
    
        // Simula a reprodução da mídia
        while (minutosCorridos < tamanho && emExecucao) {
            if (estaPausada) {
                // Se estiver pausada, aguarda até que seja continuada
                System.out.println("Mídia pausada. Aguardando continuar...");
                try {
                    Thread.sleep(1000); // Intervalo para não sobrecarregar o loop
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } else {
                // Se não estiver pausada, continua a execução
                System.out.println((minutosCorridos + 1) + " minutos corridos " + titulo);
                minutosCorridos++;
                try {
                    Thread.sleep(1000); // Simula a passagem de tempo
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    
        // Finaliza a execução
        if (minutosCorridos == tamanho) {
            System.out.println("Mídia concluída.");
            emExecucao = false;
        }
    }
    

    @Override
    public void pausar() throws MsgException {
        estaPausada = true;
        System.out.println("Pausando a mídia...");
    }

    @Override
    public void parar() throws MsgException {
        estaPausada = false;
        System.out.println("Parando a mídia...");
    }

    @Override
    public void continuarReproducao() throws MsgException {
        estaPausada = false;
        System.out.println("Continuando a reprodução...");
        // Lógica para continuar a reprodução da mídia a partir do ponto onde parou
    }
    
}
