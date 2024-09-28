import java.io.*;
import java.util.*;

public class Biblioteca {
    private Map<String, Midia> midias;

    Biblioteca() {
        midias = new HashMap<>();
    }

    public void adicionarMidia(Midia midia) {
        midias.put(midia.getTitulo(), midia);
    }

    public void listarMidias() {
        if (midias.isEmpty()) {
            System.out.println("Nenhuma mídia cadastrada.");
        } else {
            for (String titulo : midias.keySet()) {
                Midia midia = midias.get(titulo);
                System.out.print("Título: " + titulo + " --> ");
                midia.exibirDetalhes();
            }
        }
    }

    //Método para salvar a biblioteca em um arquivo txt
    public void salvarEmTxt(String nomeArquivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Midia midia : midias.values()) {
                // Formato: Título;Tamanho;AnoLancamento;Tipo;Diretor/Artista
                String linha = midia.getTitulo() + ";" + midia.getTamanho() + ";" + midia.getAnoLancamento();

                if (midia instanceof Filme) {
                    linha += ";" + "Filme" + ";" + ((Filme) midia).diretor;// desse jeito na exibição eu pego o tipo e
                                                                           // separo, comparo se é filme, livro ou
                                                                           // musica
                } else if (midia instanceof Musica) {
                    linha += ";" + "Musica" + ";" + ((Musica) midia).artista;
                } else if (midia instanceof Livro) {
                    linha += ";" + "Livro" + ";" + ((Livro) midia).autor;
                }

                writer.write(linha);
                writer.newLine();// Nova linha pras midias
            }
        }
    }

    //Método para carregar a biblioteca de um arquivo txt
    public void carregarDeTxt(String nomeArquivo) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";"); //usando o ; eu delimito e separo as informações
                String titulo = partes[0];
                int tamanho = Integer.parseInt(partes[1]);
                int anoLancamento = Integer.parseInt(partes[2]);
                String tipo = partes[3];

                if (tipo.equals("Filme")) {
                    String diretor = partes[4];
                    adicionarMidia(new Filme(titulo, tamanho, anoLancamento, diretor));
                } else if (tipo.equals("Musica")) {
                    String artista = partes[4];
                    adicionarMidia(new Musica(titulo, tamanho, anoLancamento, artista));
                } else if (tipo.equals("Livro")) {
                    String autor = partes[4];
                    adicionarMidia(new Livro(titulo, tamanho, anoLancamento, autor));
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar a biblioteca: " + e.getMessage());
        }
    }

    public void removerMidia(String titulo) {
        if (midias.containsKey(titulo)) {
            midias.remove(titulo);
            System.out.println("Mídia \"" + titulo + "\" removida com sucesso.");
        } else {
            System.out.println("Mídia \"" + titulo + "\" não encontrada.");
        }
    }

    public Midia getMidia(String titulo) {
        return midias.get(titulo);
    }
}
