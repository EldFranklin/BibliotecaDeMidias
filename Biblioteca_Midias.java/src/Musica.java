public class Musica extends Midia implements Controle {
    public String artista;
    private boolean isPlaying = false;
    private boolean isPaused = false;

    Musica(String titulo, int tamanho, int anoLancamento, String artista) {
        super(titulo, tamanho, anoLancamento);
        this.artista = artista;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Título: " + titulo + ", Artista: " + artista + ", Tamanho: " + tamanho
                + " min, Ano de Lançamento: " + anoLancamento);
    }

    @Override
    public int getTamanho() {
        return this.tamanho;
    }

    @Override
    public int getAnoLancamento() {
        return this.anoLancamento;
    }

   
    @Override
    public void iniciar() throws MsgException {
        System.out.println("Reproduzindo música: " + this.getTitulo());
        isPlaying = true;

        
        new Thread(() -> {
            try {
                for (int i = 0; i < tamanho; i++) {
                    // Verifica se está pausado
                    while (isPaused) {
                        Thread.sleep(100); 
                    }

                    // Simulando a reprodução da música
                    System.out.printf("%d minutos corridos da música %s%n", i + 1, titulo);
                    Thread.sleep(1000); // Espera 1 segundo para cada "segundo" da música

                    if (i == tamanho - 1) {
                        System.out.println("Música " + titulo + " acabou.");
                        break;
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Reprodução interrompida.");
            } finally {
                isPlaying = false;
            }
        }).start();
    }

    @Override
    public void pausar() throws MsgException {
        if (isPlaying) {
            isPaused = true;
            System.out.println("Música foi pausada: " + titulo);
        } else {
            System.out.println("A música não está em execução.");
        }
    }

    @Override
    public void parar() throws MsgException {
        isPlaying = false;
        isPaused = false; // Para garantir que o estado de pausa é resetado
        System.out.println("Música parada: " + titulo);
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}
