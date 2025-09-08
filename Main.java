import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Solicita os três parâmetros conforme a especificação do trabalho
            System.out.println("Qual a quantidade de numeros a serem ordenados?");
            int quantidadeNumeros = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Qual o nome do arquivo de entrada?");
            String inputFile = scanner.nextLine();

            System.out.println("Qual o nome do arquivo de saida?");
            String outputFile = scanner.nextLine();

            ArrayList<Integer> originalNumbers = FileUtils.readNumbersFromFile(inputFile);
            if (originalNumbers.isEmpty()) {
                System.out.println("Nenhum numero foi lido do arquivo. Verifique o caminho e o conteudo.");
                return;
            }

            System.out.println("\n--- Executando Algoritmos ---");

            // Executando Bubble Sort
            ArrayList<Integer> numbersForSort = new ArrayList<>(originalNumbers);
            System.out.println("Executando Bubble Sort...");
            long startTime = System.nanoTime();
            BubbleSort.sort(numbersForSort);
            long endTime = System.nanoTime();
            long durationMs = (endTime - startTime) / 1000000;
            System.out.println("Tempo de execucao (ms): " + durationMs);
            System.out.println();

            // Executando Selection Sort
            numbersForSort = new ArrayList<>(originalNumbers);
            System.out.println("Executando Selection Sort...");
            startTime = System.nanoTime();
            SelectionSort.sort(numbersForSort);
            endTime = System.nanoTime();
            durationMs = (endTime - startTime) / 1000000;
            System.out.println("Tempo de execucao (ms): " + durationMs);
            System.out.println();

            // Executando Insertion Sort
            numbersForSort = new ArrayList<>(originalNumbers);
            System.out.println("Executando Insertion Sort...");
            startTime = System.nanoTime();
            InsertionSort.sort(numbersForSort);
            endTime = System.nanoTime();
            durationMs = (endTime - startTime) / 1000000;
            System.out.println("Tempo de execucao (ms): " + durationMs);
            System.out.println();
            
            // Executando Merge Sort
            numbersForSort = new ArrayList<>(originalNumbers);
            System.out.println("Executando Merge Sort...");
            startTime = System.nanoTime();
            MergeSort.sort(numbersForSort);
            endTime = System.nanoTime();
            durationMs = (endTime - startTime) / 1000000;
            System.out.println("Tempo de execucao (ms): " + durationMs);
            System.out.println();

            // Executando Quick Sort
            numbersForSort = new ArrayList<>(originalNumbers);
            System.out.println("Executando Quick Sort...");
            startTime = System.nanoTime();
            QuickSort.sort(numbersForSort);
            endTime = System.nanoTime();
            durationMs = (endTime - startTime) / 1000000;
            System.out.println("Tempo de execucao (ms): " + durationMs);
            System.out.println();

            // Executando Heap Sort
            numbersForSort = new ArrayList<>(originalNumbers);
            System.out.println("Executando Heap Sort...");
            startTime = System.nanoTime();
            HeapSort.sort(numbersForSort);
            endTime = System.nanoTime();
            durationMs = (endTime - startTime) / 1000000;
            System.out.println("Tempo de execucao (ms): " + durationMs);
            System.out.println();

            // Salvando o ultimo resultado ordenado
            FileUtils.writeNumbersToFile(outputFile, numbersForSort);

            System.out.println("\nOs numeros ordenados do ultimo teste foram salvos em: " + outputFile);

        } finally {
            scanner.close();
        }
    }
}