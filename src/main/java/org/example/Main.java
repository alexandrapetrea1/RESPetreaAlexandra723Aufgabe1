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

        Scanner sc = new Scanner(System.in);
        System.out.print("\nIntroduceți o capacitate minimă: ");
        double capacity = sc.nextDouble();
        sc.nextLine();

        List<Map<String, String>> evenimente = new ArrayList<>();
        for (Map<String, String> row : data) {
            if (row.containsKey("Kraftpunkte")) {
                try {
                    double eventCapacity = Double.parseDouble(row.get("Kraftpunkte"));
                    if (eventCapacity > capacity) {
                        evenimente.add(row);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Eroare la conversia capacității pentru: " + row);
                }
            }
        }

        if (evenimente.isEmpty()) {
            System.out.println("Nu există evenimente cu această capacitate minimă.");
        } else {
            System.out.println("\nEvenimente cu capacitate de cel puțin " + capacity + ":");
            for (Map<String, String> row : evenimente) {
                System.out.println(row.get("Charaktername"));
            }
        }

        List<Map<String, String>> Joninevent = new ArrayList<>();
        for (Map<String, String> row : data) {
            if ("Jonin".equals(row.get("Stufe"))) {
                Joninevent.add(row);
            }
        }

        Joninevent.sort(Comparator.comparing(row -> row.get("Datum")));
        System.out.println("Evenimentele Jonin sortate cronologic:");
        for (Map<String, String> event : Joninevent) {
            String date = event.get("Datum");
            String name = event.get("Charaktername");
            String description = event.get("Beschreibung");
            System.out.println(date + " : " + name + " - " + description);
        }

    }
}