public class Filme extends Midia implements Controle {
    private String diretor;
    private boolean isPlaying = false;
    private boolean isPaused = false;

    Filme(String titulo, int tamanho, int anoLancamento, String diretor) {
        super(titulo, tamanho, anoLancamento);
        this.diretor = diretor;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Título: " + titulo + ", Diretor: " + diretor + ", Tamanho: " + tamanho
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

    // Implementação de iniciar, com lançamento
    @Override
    public void iniciar() throws MsgException {
        System.out.println("Iniciando filme: " + titulo);
        isPlaying = true;
        // Aqui eu se eu quiser implementar a reprpdução, vou ter que usar threads para poder reproduzir e pausar/parar a midia 
        // tendei usando só o sleep, mas n consegui fazer pausar/parar
        //Criar uma nova thread para a reprodução
        new Thread(() -> {
            try {
                for (int i = 0; i < tamanho; i++) {
                    // Verifica se está pausado
                    while (isPaused) {
                        Thread.sleep(100); // Aguarda enquanto está pausado
                    }

                    // Simulando a reprodução do filme
                    System.out.printf("%d minutos corridos do filme %s%n", i + 1, titulo);
                    Thread.sleep(1000); // Espera 1 segundo para cada "segundo" do filme

                    if (i == tamanho - 1) {
                        System.out.println("Filme " + titulo + " acabou.");
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
            System.out.println("Pausando filme: " + titulo);
        } else {
            System.out.println("O filme não está em execução.");
        }
    }

    @Override
    public void parar() throws MsgException {
        isPlaying = false;
        isPaused = false; // Para garantir que o estado de pausa é resetado
        System.out.println("Parando filme: " + titulo);
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}
