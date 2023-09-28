package com.example.tp13913;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class tropcomp {
    
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java tropcomp <chemin du dossier> <seuil>%");
            return;
        }
        String cheminDossierEntree = args[0];
        int seuil = Integer.parseInt(args[1]);
        List<String> lignesSortie = new ArrayList<>();
        processFolder(new File(cheminDossierEntree), lignesSortie);


        try {
            FileWriter writer = new FileWriter("output.csv");
            for (String line : lignesSortie) {
                writer.write(line + "\n");
            }
            writer.close();
            System.out.println("Resultat en output.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void processFolder(File folder, List<String> ligneCSV) {
        if (folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                if (file.isDirectory()) {
                    processFolder(file, ligneCSV);
                }
            }
        } else if (folder.getName().endsWith(".java")) {
            processJavaFile(folder, ligneCSV);
        }
    }

    public static void processJavaFile(File file, List<String> ligneCSV) {
        try {
            String pathFile = file.getPath();
            String nomPackage = "";
            String nomClasse = "";
            int tloc = 0;
            int tassert = 0;

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("package")) {
                    nomPackage = line.split(" ")[1].replace(";", "");
                } else if (line.startsWith("public class")) {
                    nomClasse = line.split(" ")[2].split("\\{")[0];
                }
                tloc++;

                if (line.contains("assert")) {
                    tassert++;
                }
            }

            double tpcm = calculateTPCM(tloc, tassert);

            if(tloc && tpcm > )

            ligneCSV.add(pathFile + "," + nomPackage + "," + nomClasse + "," + tloc + "," + tassert + "," + tpcm);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static double calculateTPCM(int tloc, int tassert) {

        if (tloc == 0) {
            return 0.0;
        }
        return (double) tassert/tloc;


    }
}
