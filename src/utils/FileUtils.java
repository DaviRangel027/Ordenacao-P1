package src.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileUtils {

 
    
    public static ArrayList<Integer> lerNumerosDoArquivo(String nomeArquivo) {
        ArrayList<Integer> numeros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                // Tenta adicionar o número à lista após remover espaços em branco
                numeros.add(Integer.parseInt(linha.trim()));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + nomeArquivo);
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Erro de formato: O arquivo contem texto que nao e um numero.");
            e.printStackTrace();
        }
        return numeros;
    }

  
    public static void escreverNumerosNoArquivo(String nomeArquivo, ArrayList<Integer> numeros) {
        try (FileWriter escritor = new FileWriter(nomeArquivo)) {
            for (Integer numero : numeros) {
                escritor.write(numero + "\n");
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + nomeArquivo);
            e.printStackTrace();
        }
    }
}