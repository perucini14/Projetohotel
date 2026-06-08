package util;

import java.io.*;

public class ArquivoUtil {

    public static void salvar(String path, String conteudo) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {

            writer.write(conteudo);

        } catch (IOException e) {

            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    public static String ler(String path) {

        StringBuilder conteudo = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            String linha;

            while ((linha = reader.readLine()) != null) {

                conteudo.append(linha);
            }

        } catch (IOException e) {

            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }

        return conteudo.toString();
    }
}