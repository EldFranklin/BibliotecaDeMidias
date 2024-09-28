public class Livro extends Midia {
    String autor;

    Livro(String titulo, int tamanho, int anoLancamento, String autor) {
        super(titulo, tamanho, anoLancamento);
        this.autor = autor;
    }

    public void exibirDetalhes() {
        System.out.println("Livro: " + titulo + ", Autor: " + autor + ", Ano: " + anoLancamento);
    }

    public int getTamanho() {
        return this.tamanho;
    }

    public int getAnoLancamento() {
        return this.anoLancamento;
    }

}
