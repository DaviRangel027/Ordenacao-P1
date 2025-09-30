package src.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    
    private static void criarPasta(String nomeArquivo) {
        File arquivo = new File(nomeArquivo);   
        File pastaPai = arquivo.getParentFile();
        if (pastaPai != null && !pastaPai.exists()) {
            pastaPai.mkdirs();
        }
    }

  
    public static void gerarDadosAleatorios(String nomeArquivo, int tamanho) throws IOException {
        criarPasta(nomeArquivo);
        try (FileWriter escritor = new FileWriter(nomeArquivo)) {
            Random random = new Random();
            for (int i = 0; i < tamanho; i++) {
                escritor.write(random.nextInt(tamanho * 10) + "\n");
            }
        }
    }

  
    public static void gerarDadosCrescentes(String nomeArquivo, int tamanho) throws IOException {
        criarPasta(nomeArquivo);
        try (FileWriter escritor = new FileWriter(nomeArquivo)) {
            for (int i = 0; i < tamanho; i++) {
                escritor.write(i + "\n");
            }
        }
    }

  
    public static void gerarDadosDecrescentes(String nomeArquivo, int tamanho) throws IOException {
        criarPasta(nomeArquivo);
        try (FileWriter escritor = new FileWriter(nomeArquivo)) {
            for (int i = tamanho - 1; i >= 0; i--) {
                escritor.write(i + "\n");
            }
        }
    }
    
  
    public static void gerarDadosAleatoriosUnicos(String nomeArquivo, int tamanho) throws IOException {
        criarPasta(nomeArquivo);
        try (FileWriter escritor = new FileWriter(nomeArquivo)) {
            List<Integer> lista = new ArrayList<>();
            for (int i = 0; i < tamanho; i++) {
                lista.add(i);
            }
            Collections.shuffle(lista);
            for (Integer numero : lista) {
                escritor.write(numero + "\n");
            }
        }
    }
    
    
    public static void gerarDadosCrescentesComRepeticao(String nomeArquivo, int tamanho) throws IOException {
        criarPasta(nomeArquivo);
        try (FileWriter escritor = new FileWriter(nomeArquivo)) {
            Random random = new Random();
            int numeroAtual = 0;
            for (int i = 0; i < tamanho; i++) {
                numeroAtual += random.nextInt(5); 
                escritor.write(numeroAtual + "\n");
            }
        }
    }

    
    public static void gerarDadosDecrescentesComRepeticao(String nomeArquivo, int tamanho) throws IOException {
        criarPasta(nomeArquivo);
        try (FileWriter escritor = new FileWriter(nomeArquivo)) {
            Random random = new Random();
            int numeroAtual = tamanho * 2; 
            for (int i = 0; i < tamanho; i++) {
                numeroAtual -= random.nextInt(5);
                if (numeroAtual < 0) numeroAtual = 0;
                escritor.write(numeroAtual + "\n");
            }
        }
    }
}