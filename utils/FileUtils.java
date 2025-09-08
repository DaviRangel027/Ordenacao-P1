


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileUtils {

    /**
     * Lê os pesos das arestas de um arquivo .gr e os armazena em um ArrayList.
     * @param filename O nome do arquivo .gr a ser lido.
     * @return Um ArrayList de inteiros com os pesos do arquivo.
     */
    public static ArrayList<Integer> readNumbersFromFile(String filename) {
        ArrayList<Integer> numbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Ignora linhas de comentário (c) e problema (p)
                if (line.startsWith("c") || line.startsWith("p")) {
                    continue;
                }

                // Linhas de aresta (a)
                if (line.startsWith("a")) {
                    String[] parts = line.split(" ");
                    // O peso é a terceira parte da linha (índice 3, pois a primeira é o 'a')
                    // O formato é: a <nó_origem> <nó_destino> <peso>
                    int weight = Integer.parseInt(parts[3].trim());
                    numbers.add(weight);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + filename);
            e.printStackTrace();
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Erro ao processar dados. Verifique o formato do arquivo: " + filename);
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