import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        // Carregar a biblioteca do arquivo ao iniciar
        File arquivo = new File("biblioteca.txt");
        if (arquivo.exists()) {
            try {
                biblioteca.carregarDeTxt("biblioteca.txt");
                System.out.println("Biblioteca carregada com sucesso!");
            } catch (IOException e) {
                System.out.println("Erro ao carregar a biblioteca: " + e.getMessage());
            }
        }

        boolean loop = true;

        while (loop) {
            System.out.println("\n--- Menu Biblioteca de Mídias ---");
            System.out.println("1. Adicionar Mídia");
            System.out.println("2. Listar Mídias");
            System.out.println("3. Reproduzir Mídia");
            System.out.println("4. Remover Mídia");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    System.out.print("Digite o tipo da mídia (Livro, Filme, Música): ");
                    String tipoMidia = scanner.nextLine().toLowerCase();
                    // tenho que tirar a possibilidade de nao entrar em um caso por causa de acento
                    // ou letras maiusculas e minusculas
                    System.out.print("Digite o título: ");
                    String titulo = scanner.nextLine();

                    System.out.print("Digite o tamanho (em páginas/minutos): ");
                    int tamanho = scanner.nextInt();

                    System.out.print("Digite o ano de lançamento: ");
                    int anoLancamento = scanner.nextInt();
                    scanner.nextLine(); // consumir a nova linha

                    String autorDiretorArtista;

                    switch (tipoMidia) {
                        case "livro":
                            System.out.print("Digite o autor: ");
                            autorDiretorArtista = scanner.nextLine();
                            biblioteca.adicionarMidia(new Livro(titulo, tamanho, anoLancamento, autorDiretorArtista));
                            // inves de ter um case para atualizar a biblioteca, posso salvar toda vez que
                            // eu adiconar uma midia // feito
                            System.out.println("Livro adicionado com sucesso!");
                            try {
                                biblioteca.salvarEmTxt("biblioteca.txt");
                                System.out.println("Biblioteca salva com sucesso!");
                            } catch (IOException e) {
                                System.out.println("Erro ao salvar a biblioteca: " + e.getMessage());
                            }

                            break;

                        case "filme":
                            System.out.print("Digite o diretor: ");
                            autorDiretorArtista = scanner.nextLine();
                            biblioteca.adicionarMidia(new Filme(titulo, tamanho, anoLancamento, autorDiretorArtista));
                            System.out.println("Filme adicionado com sucesso!");
                            try {
                                biblioteca.salvarEmTxt("biblioteca.txt");
                                System.out.println("Biblioteca salva com sucesso!");
                            } catch (IOException e) {
                                System.out.println("Erro ao salvar a biblioteca: " + e.getMessage());
                            }

                            break;

                        case "musica":
                            System.out.print("Digite o artista: ");
                            autorDiretorArtista = scanner.nextLine();
                            biblioteca.adicionarMidia(new Musica(titulo, tamanho, anoLancamento, autorDiretorArtista));
                            System.out.println("Música adicionada com sucesso!");
                            try {
                                biblioteca.salvarEmTxt("biblioteca.txt");
                                System.out.println("Biblioteca salva com sucesso!");
                            } catch (IOException e) {
                                System.out.println("Erro ao salvar a biblioteca: " + e.getMessage());
                            }

                            break;

                        default:
                            System.out.println("Tipo de mídia inválido.");
                    }
                    break;

                case 2:
                    biblioteca.listarMidias();
                    break;

                case 3:
                    System.out.print("Digite o título da mídia que deseja reproduzir: ");
                    String tituloMidia = scanner.nextLine();
                    Midia midia = biblioteca.getMidia(tituloMidia);

                    if (midia != null) {
                        // Se for um livro, não é possível reproduzir
                        if (midia instanceof Livro) {
                            System.out.println("Não é possível reproduzir livros.");
                        } else {
                            try {
                                midia.iniciar(); // Inicia a reprodução
                                if (midia instanceof Controle) {
                                    Controle controle = (Controle) midia;

                                    boolean reproduzindo = true; // Flag para controle de reprodução
                                    boolean pausado = false; // Flag para indicar se está pausado

                                    while (reproduzindo) { // Loop de controle de reprodução
                                        if (!pausado) {
                                            System.out.println(
                                                    "Reproduzindo... Deseja pausar (p) ou parar (s) a mídia? (qualquer tecla para continuar)");
                                        } else {
                                            System.out.println(
                                                    "Mídia pausada. Deseja retomar a reprodução (p) ou parar (s)?");
                                        }

                                        String aux = scanner.nextLine();

                                        if (aux.equals("p")) {
                                            if (!pausado) {
                                                controle.pausar();
                                                System.out.println("Mídia pausada.");
                                                pausado = true;
                                            } else {
                                                controle.continuarReproducao(); // Método para continuar a reprodução
                                                System.out.println("Mídia retomada.");
                                                pausado = false;
                                            }
                                        } else if (aux.equals("s")) {
                                            controle.parar(); // Chama parar aqui
                                            reproduzindo = false; // Para a reprodução
                                            System.out.println("Mídia parada.");
                                        } else {
                                            if (!pausado) {
                                                // Se a mídia não está pausada, continua a reprodução normalmente
                                                continue; // Pode adicionar mais lógica para o progresso da reprodução
                                                          // aqui
                                            }
                                        }
                                    }
                                }
                            } catch (MsgException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    } else {
                        System.out.println("Mídia não encontrada!");
                    }
                    break;

                case 4:
                    System.out.println("Digite o titulo da midia que deseja remover:");
                    String tituloAremover = scanner.nextLine();
                    biblioteca.removerMidia(tituloAremover);
                    System.out.println(
                            "Midia removida com sucesso!");
                    try {
                        biblioteca.salvarEmTxt("biblioteca.txt");
                        System.out.println("Biblioteca salva com sucesso!");
                    } catch (IOException e) {
                        System.out.println("Erro ao salvar a biblioteca: " + e.getMessage());

                    }
                    break;
                case 5:
                    loop = false;
                    System.out.println("Saindo do programa...");
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}
