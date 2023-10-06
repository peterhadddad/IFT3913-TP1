package tp13913;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static tp13913.TAssert.*;
import static tp13913.TLOC.*;


public class tls {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java tls <chemin du dossier>");
            return;
        }
        String cheminDossierEntree = args[0];
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

    public static List<String> processJavaFile(File file, List<String> ligneCSV) {
        try {
            String pathFile = file.getPath();
            String nomPackage = "";
            String nomClasse = "";

            int TLOC= calculerNombredeLigneTloc(pathFile);

            int Tassert= compterAssession(pathFile);

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("package")) {
                    nomPackage = line.split(" ")[1].replace(";", "");
                }
                else if (line.startsWith("public class")) {
                    nomClasse = line.split(" ")[2].split("\\{")[0];
                }
            }
            double tpcm = calculateTPCM(TLOC, Tassert);


            ligneCSV.add(pathFile + "," + nomPackage + "," + nomClasse + "," + TLOC + "," + Tassert + "," + tpcm);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ligneCSV;
    }
    public static double calculateTPCM(int tloc, int tassert) {
        if (tloc == 0) {
            return 0.0;
        }
        return (double) tassert/tloc;
    }
}




