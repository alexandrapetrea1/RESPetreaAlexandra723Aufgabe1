package org.example;

import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        String file = "src/main/resources/ninja_events.tsv";
        List<Map<String, String>> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String headerLine = br.readLine();
            while (headerLine == null) {
                System.out.println("Fisierul este gol");
                return;
            }
            String[] headers = headerLine.split("\t");
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t");
                Map<String, String> row = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    row.put(headers[i], values[i]);
                }
                data.add(row);
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea fisierului" + e.getMessage());
        }

        System.out.println("Datele citite din fisierul TSV: ");
        for (Map<String, String> row : data) {
            System.out.println(row);
        }

    }
}