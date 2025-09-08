package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileUtils {

    /**
     * Lê números inteiros de um arquivo e os armazena em um ArrayList.
     * @param filename O nome do arquivo a ser lido.
     * @return Um ArrayList de inteiros com os dados do arquivo.
     */
    public static ArrayList<Integer> readNumbersFromFile(String filename) {
        ArrayList<Integer> numbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                numbers.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + filename);
            e.printStackTrace();
        }
        return numbers;
    }

    /**
     * Grava uma lista de números em um arquivo de saída.
     * @param filename O nome do arquivo para gravação.
     * @param numbers O ArrayList de inteiros a ser gravado.
     */
    public static void writeNumbersToFile(String filename, ArrayList<Integer> numbers) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Integer number : numbers) {
                writer.write(number + "\n");
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + filename);
            e.printStackTrace();
        }
    }
}