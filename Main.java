import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.LinkedHashMap;
import java.util.Map;
import src.algoritimos.*;
import src.utils.FileUtils;
import src.utils.DataGenerator;

public class Main {

    
    //Limpa a tela do console
     
    public static void limparTela() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException ex) {
            // Ignora a exceção se a limpeza do console falhar
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // ---Caminhos dinâmicos---
        String homeDir = System.getProperty("user.home");
        String pastaBaseProjeto = homeDir + File.separator + "DadosOrdenacao";
        String pastaSaidaBase = pastaBaseProjeto + File.separator + "Saida" + File.separator;
        String pastaEntradaBase = pastaBaseProjeto + File.separator + "Entrada" + File.separator;
        // --- FIM DA ALTERAÇÃO ---
        
        boolean continuar = true;

        do {
            try {
                int[] tamanhos = {100000, 160000, 220000, 280000, 340000, 400000, 450000, 520000, 580000, 640000, 700000};
                String[] tipos = {"Aleatorio com repeticao", "Aleatorio sem repeticao", "Crescente com repeticao", "Crescente sem repeticao", "Decrescente com repeticao", "Decrescente sem repeticao"};

                int escolhaTipo = 0;
                // Validação da escolha do tipo de dado
                do {
                    System.out.println("Escolha o tipo de dado para a ordenacao:");
                    for (int i = 0; i < tipos.length; i++) {
                        System.out.println((i + 1) + ". " + tipos[i]);
                    }
                    System.out.print("Digite o numero correspondente: ");
                    try {
                        escolhaTipo = scanner.nextInt();
                        if (escolhaTipo < 1 || escolhaTipo > tipos.length) {
                            System.out.println("Opcao invalida. Por favor, digite um numero entre 1 e " + tipos.length + ".");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada invalida. Por favor, digite apenas numeros.");
                        scanner.next(); // Limpa o buffer
                    }
                } while (escolhaTipo < 1 || escolhaTipo > tipos.length);
                scanner.nextLine();

                int escolhaTamanho = 0;
                // Validação da escolha da quantidade de números
                do {
                    System.out.println("\nEscolha a quantidade de numeros a serem ordenados:");
                    for (int i = 0; i < tamanhos.length; i++) {
                        System.out.println((i + 1) + ". " + tamanhos[i]);
                    }
                    System.out.print("Digite o numero correspondente: ");
                    try {
                        escolhaTamanho = scanner.nextInt();
                        if (escolhaTamanho < 1 || escolhaTamanho > tamanhos.length) {
                            System.out.println("Opcao invalida. Por favor, digite um numero entre 1 e " + tamanhos.length + ".");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada invalida. Por favor, digite apenas numeros.");
                        scanner.next(); // Limpa o buffer
                    }
                } while (escolhaTamanho < 1 || escolhaTamanho > tamanhos.length);
                scanner.nextLine();
                
                limparTela();
                
                String tipo = tipos[escolhaTipo - 1];
                int tamanho = tamanhos[escolhaTamanho - 1];
                
                String pastaEntrada = pastaEntradaBase + tipo.replace(" ", "") + File.separator + tamanho + File.separator;
                String caminhoArquivoEntrada = pastaEntrada + "entrada.txt";
                
                File diretorioEntrada = new File(pastaEntrada);
                if (!diretorioEntrada.exists()) {
                    diretorioEntrada.mkdirs();
                }

                System.out.println("\nGerando arquivo de entrada: " + caminhoArquivoEntrada + " (Tamanho: " + tamanho + ", Tipo: " + tipo + ")");
                
                switch (tipo) {
                    case "Aleatorio com repeticao":
                        DataGenerator.gerarDadosAleatorios(caminhoArquivoEntrada, tamanho);
                        break;
                    case "Aleatorio sem repeticao":
                        DataGenerator.gerarDadosAleatoriosUnicos(caminhoArquivoEntrada, tamanho);
                        break;
                    case "Crescente sem repeticao":
                        DataGenerator.gerarDadosCrescentes(caminhoArquivoEntrada, tamanho);
                        break;
                    case "Crescente com repeticao":
                        DataGenerator.gerarDadosCrescentesComRepeticao(caminhoArquivoEntrada, tamanho);
                        break;
                    case "Decrescente sem repeticao":
                        DataGenerator.gerarDadosDecrescentes(caminhoArquivoEntrada, tamanho);
                        break;
                    case "Decrescente com repeticao":
                        DataGenerator.gerarDadosDecrescentesComRepeticao(caminhoArquivoEntrada, tamanho);
                        break;
                }

                ArrayList<Integer> numerosOriginais = FileUtils.lerNumerosDoArquivo(caminhoArquivoEntrada);
                if (numerosOriginais.isEmpty()) {
                    System.err.println("Nenhum numero foi lido do arquivo de entrada.");
                    return;
                }

                System.out.println("\n--- Executando Algoritmos para " + numerosOriginais.size() + " numeros (" + tipo + ") ---");
                
                Map<String, String> algoritmos = new LinkedHashMap<>();
                algoritmos.put("Bubble Sort", "BubbleSort");
                algoritmos.put("Selection Sort", "SelectionSort");
                algoritmos.put("Insertion Sort", "InsertionSort");
                algoritmos.put("Merge Sort", "MergeSort");
                algoritmos.put("Quick Sort", "QuickSort");
                algoritmos.put("Heap Sort", "HeapSort");

                for (Map.Entry<String, String> entrada : algoritmos.entrySet()) {
                    String nomeAlgo = entrada.getKey();
                    String classeAlgo = entrada.getValue();
                    long duracaoTotalMs = 0;
                    int numExecucoes = 3; 

                    for (int execucao = 0; execucao < numExecucoes; execucao++) {
                        ArrayList<Integer> numerosParaOrdenar = new ArrayList<>(numerosOriginais);

                        long tempoInicio = System.nanoTime();
                        
                        switch (classeAlgo) {
                            case "BubbleSort":
                                BubbleSort.sort(numerosParaOrdenar);
                                break;
                            case "SelectionSort":
                                SelectionSort.sort(numerosParaOrdenar);
                                break;
                            case "InsertionSort":
                                InsertionSort.sort(numerosParaOrdenar);
                                break;
                            case "MergeSort":
                                MergeSort.sort(numerosParaOrdenar);
                                break;
                            case "QuickSort":
                                QuickSort.sort(numerosParaOrdenar);
                                break;
                            case "HeapSort":
                                HeapSort.sort(numerosParaOrdenar);
                                break;
                        }

                        long tempoFim = System.nanoTime();
                        long duracaoMs = (tempoFim - tempoInicio) / 1000000;
                        duracaoTotalMs += duracaoMs;
                    }
                    
                    long duracaoMediaMs = duracaoTotalMs / numExecucoes;
                    System.out.println(nomeAlgo + ": Tempo medio de execucao (ms) = " + duracaoMediaMs);
                    
                    String pastaSaida = pastaSaidaBase + classeAlgo + File.separator + tipo.replace(" ", "") + File.separator + tamanho + File.separator;
                    String caminhoArquivoSaida = pastaSaida + "saida.txt";
                    
                    File diretorioSaida = new File(pastaSaida);
                    if (!diretorioSaida.exists()) {
                        diretorioSaida.mkdirs();
                    }
                    
                    ArrayList<Integer> numerosParaSalvar = new ArrayList<>(numerosOriginais);
                    switch (classeAlgo) {
                        case "BubbleSort":
                            BubbleSort.sort(numerosParaSalvar);
                            break;
                        case "SelectionSort":
                            SelectionSort.sort(numerosParaSalvar);
                            break;
                        case "InsertionSort":
                            InsertionSort.sort(numerosParaSalvar);
                            break;
                        case "MergeSort":
                            MergeSort.sort(numerosParaSalvar);
                            break;
                        case "QuickSort":
                            QuickSort.sort(numerosParaSalvar);
                            break;
                        case "HeapSort":
                            HeapSort.sort(numerosParaSalvar);
                            break;
                    }
                    
                    FileUtils.escreverNumerosNoArquivo(caminhoArquivoSaida, numerosParaSalvar);
                    System.out.println("Arquivo de saida salvo em: " + caminhoArquivoSaida);
                    System.out.println();
                }
                System.out.println("\nProcesso concluido com sucesso!");

                System.out.print("Deseja realizar outro teste? (s/n): ");
                String resposta = scanner.next();
                continuar = resposta.equalsIgnoreCase("s");
                if (continuar) {
                    limparTela();
                }

            } catch (IOException e) {
                System.err.println("Erro ao gerar ou ler o arquivo: " + e.getMessage());
                e.printStackTrace();
                continuar = false;
            }
        } while (continuar);

        scanner.close();
        System.out.println("\nPrograma encerrado.");
    }
}