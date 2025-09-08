package src.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileUtils {

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
        } catch (NumberFormatException e) {
            System.err.println("Erro de formato: O arquivo contem texto que nao e um numero.");
            e.printStackTrace();
        }
        return numbers;
    }

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