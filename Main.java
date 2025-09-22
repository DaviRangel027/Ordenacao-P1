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

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException ex) {
            // Ignora erros de limpeza de console
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String baseOutputFolder = "C:\\Users\\Arthur - Home\\Desktop\\Dados\\Saida\\";
        String baseInputFolder = "C:\\Users\\Arthur - Home\\Desktop\\Dados\\Entrada\\";
        boolean continuar = true;

        do {
            try {
                int[] tamanhos = {100000, 160000, 220000, 280000, 340000, 400000, 450000, 520000, 580000, 640000, 700000};
                String[] tipos = {"Aleatorio com repeticao", "Aleatorio sem repeticao", "Crescente com repeticao", "Crescente sem repeticao", "Decrescente com repeticao", "Decrescente sem repeticao"};

                int tipoEscolha = 0;
                // Validação da escolha do tipo de dado
                do {
                    System.out.println("Escolha o tipo de dado para a ordenacao:");
                    for (int i = 0; i < tipos.length; i++) {
                        System.out.println((i + 1) + ". " + tipos[i]);
                    }
                    System.out.print("Digite o numero correspondente: ");
                    try {
                        tipoEscolha = scanner.nextInt();
                        if (tipoEscolha < 1 || tipoEscolha > tipos.length) {
                            System.out.println("Opcao invalida. Por favor, digite um numero entre 1 e " + tipos.length + ".");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada invalida. Por favor, digite apenas numeros.");
                        scanner.next(); // Limpa o buffer do scanner
                    }
                } while (tipoEscolha < 1 || tipoEscolha > tipos.length);
                scanner.nextLine();

                int tamanhoEscolha = 0;
                // Validação da escolha da quantidade de números
                do {
                    System.out.println("\nEscolha a quantidade de numeros a serem ordenados:");
                    for (int i = 0; i < tamanhos.length; i++) {
                        System.out.println((i + 1) + ". " + tamanhos[i]);
                    }
                    System.out.print("Digite o numero correspondente: ");
                    try {
                        tamanhoEscolha = scanner.nextInt();
                        if (tamanhoEscolha < 1 || tamanhoEscolha > tamanhos.length) {
                            System.out.println("Opcao invalida. Por favor, digite um numero entre 1 e " + tamanhos.length + ".");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada invalida. Por favor, digite apenas numeros.");
                        scanner.next(); // Limpa o buffer do scanner
                    }
                } while (tamanhoEscolha < 1 || tamanhoEscolha > tamanhos.length);
                scanner.nextLine();
                
                clearScreen();
                
                String tipo = tipos[tipoEscolha - 1];
                int tamanho = tamanhos[tamanhoEscolha - 1];
                
                String inputFolder = baseInputFolder + tipo.replace(" ", "") + File.separator + tamanho + File.separator;
                String inputFilePath = inputFolder + "entrada.txt";
                
                File inputDir = new File(inputFolder);
                if (!inputDir.exists()) {
                    inputDir.mkdirs();
                }

                System.out.println("\nGerando arquivo de entrada: " + inputFilePath + " (Tamanho: " + tamanho + ", Tipo: " + tipo + ")");
                
                switch (tipo) {
                    case "Aleatorio com repeticao":
                        DataGenerator.generateRandomData(inputFilePath, tamanho);
                        break;
                    case "Aleatorio sem repeticao":
                        DataGenerator.generateRandomUniqueData(inputFilePath, tamanho);
                        break;
                    case "Crescente sem repeticao":
                        DataGenerator.generateIncreasingData(inputFilePath, tamanho);
                        break;
                    case "Crescente com repeticao":
                        DataGenerator.generateIncreasingDataWithRepetition(inputFilePath, tamanho);
                        break;
                    case "Decrescente sem repeticao":
                        DataGenerator.generateDecreasingData(inputFilePath, tamanho);
                        break;
                    case "Decrescente com repeticao":
                        DataGenerator.generateDecreasingDataWithRepetition(inputFilePath, tamanho);
                        break;
                }

                ArrayList<Integer> originalNumbers = FileUtils.readNumbersFromFile(inputFilePath);
                if (originalNumbers.isEmpty()) {
                    System.err.println("Nenhum numero foi lido do arquivo de entrada.");
                    return;
                }

                System.out.println("\n--- Executando Algoritmos para " + originalNumbers.size() + " numeros (" + tipo + ") ---");
                
                Map<String, String> algorithms = new LinkedHashMap<>();
                algorithms.put("Bubble Sort", "BubbleSort");
                algorithms.put("Selection Sort", "SelectionSort");
                algorithms.put("Insertion Sort", "InsertionSort");
                algorithms.put("Merge Sort", "MergeSort");
                algorithms.put("Quick Sort", "QuickSort");
                algorithms.put("Heap Sort", "HeapSort");

                for (Map.Entry<String, String> entry : algorithms.entrySet()) {
                    String algoName = entry.getKey();
                    String algoClass = entry.getValue();
                    long totalDurationMs = 0;
                    int numRuns = 3; 

                    for (int run = 0; run < numRuns; run++) {
                        ArrayList<Integer> numbersForSort = new ArrayList<>(originalNumbers);

                        long startTime = System.nanoTime();
                        
                        switch (algoClass) {
                            case "BubbleSort":
                                BubbleSort.sort(numbersForSort);
                                break;
                            case "SelectionSort":
                                SelectionSort.sort(numbersForSort);
                                break;
                            case "InsertionSort":
                                InsertionSort.sort(numbersForSort);
                                break;
                            case "MergeSort":
                                MergeSort.sort(numbersForSort);
                                break;
                            case "QuickSort":
                                QuickSort.sort(numbersForSort);
                                break;
                            case "HeapSort":
                                HeapSort.sort(numbersForSort);
                                break;
                        }

                        long endTime = System.nanoTime();
                        long durationMs = (endTime - startTime) / 1000000;
                        totalDurationMs += durationMs;
                    }
                    
                    long averageDurationMs = totalDurationMs / numRuns;
                    System.out.println(algoName + ": Tempo medio de execucao (ms) = " + averageDurationMs);
                    
                    String outputFolder = baseOutputFolder + algoClass + File.separator + tipo.replace(" ", "") + File.separator + tamanho + File.separator;
                    String outputFilePath = outputFolder + "saida.txt";
                    
                    File outputDir = new File(outputFolder);
                    if (!outputDir.exists()) {
                        outputDir.mkdirs();
                    }
                    
                    ArrayList<Integer> numbersForSave = new ArrayList<>(originalNumbers);
                    switch (algoClass) {
                        case "BubbleSort":
                            BubbleSort.sort(numbersForSave);
                            break;
                        case "SelectionSort":
                            SelectionSort.sort(numbersForSave);
                            break;
                        case "InsertionSort":
                            InsertionSort.sort(numbersForSave);
                            break;
                        case "MergeSort":
                            MergeSort.sort(numbersForSave);
                            break;
                        case "QuickSort":
                            QuickSort.sort(numbersForSave);
                            break;
                        case "HeapSort":
                            HeapSort.sort(numbersForSave);
                            break;
                    }
                    
                    FileUtils.writeNumbersToFile(outputFilePath, numbersForSave);
                    System.out.println("Arquivo de saida salvo em: " + outputFilePath);
                    System.out.println();
                }
                System.out.println("\nProcesso concluido com sucesso!");

                // Pergunta se deseja continuar
                System.out.print("Deseja realizar outro teste? (s/n): ");
                String resposta = scanner.next();
                continuar = resposta.equalsIgnoreCase("s");
                if (continuar) {
                    clearScreen();
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