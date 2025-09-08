package src.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    public static void generateRandomData(String filename, int size) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            Random random = new Random();
            for (int i = 0; i < size; i++) {
                writer.write(random.nextInt(size * 10) + "\n");
            }
        }
    }

    public static void generateIncreasingData(String filename, int size) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            for (int i = 0; i < size; i++) {
                writer.write(i + "\n");
            }
        }
    }

    public static void generateDecreasingData(String filename, int size) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            for (int i = size - 1; i >= 0; i--) {
                writer.write(i + "\n");
            }
        }
    }
    
    public static void generateRandomUniqueData(String filename, int size) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                list.add(i);
            }
            Collections.shuffle(list);
            for (Integer number : list) {
                writer.write(number + "\n");
            }
        }
    }

    public static void main(String[] args) {
        int[] sizes = {100000, 160000, 220000, 280000, 340000, 400000, 450000, 520000, 580000, 640000, 700000};
        String outputFolder = "C:\\Users\\Arthur - Home\\Desktop\\Dados";

        try {
            for (int size : sizes) {
                generateRandomData(outputFolder + "random_" + size + ".txt", size);
                System.out.println("Arquivo 'random_" + size + ".txt' gerado.");

                generateIncreasingData(outputFolder + "increasing_" + size + ".txt", size);
                System.out.println("Arquivo 'increasing_" + size + ".txt' gerado.");

                generateDecreasingData(outputFolder + "decreasing_" + size + ".txt", size);
                System.out.println("Arquivo 'decreasing_" + size + ".txt' gerado.");
            }
            System.out.println("\nTodos os arquivos de entrada foram gerados com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao gerar arquivos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}