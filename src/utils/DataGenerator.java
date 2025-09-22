package src.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    private static void createFolder(String filename) {
        File file = new File(filename);
        File parentFolder = file.getParentFile();
        if (parentFolder != null && !parentFolder.exists()) {
            parentFolder.mkdirs();
        }
    }

    public static void generateRandomData(String filename, int size) throws IOException {
        createFolder(filename);
        try (FileWriter writer = new FileWriter(filename)) {
            Random random = new Random();
            for (int i = 0; i < size; i++) {
                writer.write(random.nextInt(size * 10) + "\n");
            }
        }
    }

    public static void generateIncreasingData(String filename, int size) throws IOException {
        createFolder(filename);
        try (FileWriter writer = new FileWriter(filename)) {
            for (int i = 0; i < size; i++) {
                writer.write(i + "\n");
            }
        }
    }

    public static void generateDecreasingData(String filename, int size) throws IOException {
        createFolder(filename);
        try (FileWriter writer = new FileWriter(filename)) {
            for (int i = size - 1; i >= 0; i--) {
                writer.write(i + "\n");
            }
        }
    }
    
    public static void generateRandomUniqueData(String filename, int size) throws IOException {
        createFolder(filename);
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
    
    // NOVO MÉTODO: Gera dados crescentes com repetição
    public static void generateIncreasingDataWithRepetition(String filename, int size) throws IOException {
        createFolder(filename);
        try (FileWriter writer = new FileWriter(filename)) {
            Random random = new Random();
            int currentNumber = 0;
            for (int i = 0; i < size; i++) {
                // Adiciona um número aleatório pequeno para garantir repetição e um aumento gradual
                currentNumber += random.nextInt(5); 
                writer.write(currentNumber + "\n");
            }
        }
    }

    // NOVO MÉTODO: Gera dados decrescentes com repetição
    public static void generateDecreasingDataWithRepetition(String filename, int size) throws IOException {
        createFolder(filename);
        try (FileWriter writer = new FileWriter(filename)) {
            Random random = new Random();
            int currentNumber = size * 2; // Começa com um valor alto
            for (int i = 0; i < size; i++) {
                // Subtrai um número aleatório pequeno para garantir repetição e uma queda gradual
                currentNumber -= random.nextInt(5);
                if (currentNumber < 0) currentNumber = 0; // Evita números negativos
                writer.write(currentNumber + "\n");
            }
        }
    }
}