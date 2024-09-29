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

                case 3: // ja ta ficando o chato o tanto de bug 
                    System.out.print("Digite o título da mídia que deseja reproduzir: ");
                    String tituloMidia = scanner.nextLine();
                    Midia midia = biblioteca.getMidia(tituloMidia);

                    if (midia != null) {
                        // se eh livro eu nem continuo
                        if (midia instanceof Livro) {
                            System.out.println("Não é possível reproduzir livros.");
                        } else {
                            try {
                                midia.iniciar(); //inicio
                                if (midia instanceof Controle) {
                                    Controle controle = (Controle) midia; // olho se é controlavel
                                    boolean reproduzindo = true; 

                                    while (reproduzindo) { // loop para reproduzir 
                                        System.out.println(
                                                "Deseja pausar (p) ou parar (s) a mídia? (qualquer tecla para continuar)"); // pausa do contole
                                        String aux = scanner.nextLine();

                                        if (aux.equals("p")) { 
                                            controle.pausar();
                                            System.out.println("Mídia pausada.");
                                            // Simula a pausa com um tempo de espera
                                            try {
                                                Thread.sleep(3000); // tempo so pra testar
                                            } catch (InterruptedException e) {
                                                Thread.currentThread().interrupt(); // Restaura o status de interrupção
                                                System.out.println("A pausa foi interrompida.");
                                            }
                                        } else if (aux.equals("s")) {
                                            controle.parar(); // chama parar aqui
                                            reproduzindo = false; // Para a reprodução
                                            System.out.println("Mídia parada.");
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
