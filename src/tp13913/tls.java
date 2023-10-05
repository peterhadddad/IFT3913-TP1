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
        processFolder(new File(cheminDossierEntree), lignesSortie ,cheminDossierEntree);

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

    public static void processFolder(File folder, List<String> ligneCSV,String cheminDossierEntree) {
        if (folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                if (file.isDirectory()) {
                    processFolder(file, ligneCSV,cheminDossierEntree);
                }
            }
        } else if (folder.getName().endsWith(".java")) {
            processJavaFile(folder, ligneCSV, cheminDossierEntree);
        }
    }

    public static void processJavaFile(File file, List<String> ligneCSV, String cheminDossierEntree) {
        try {
            String pathFile = file.getPath();
            String nomPackage = "";
            String nomClasse = "";

            int breTLOC= calculerNombredeLigneTloc(cheminDossierEntree);

            int breTassert= compterAssession(cheminDossierEntree);

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
            double tpcm = calculateTPCM(breTLOC, breTassert);
            ligneCSV.add(pathFile + "," + nomPackage + "," + nomClasse + "," + breTLOC + "," + breTassert + "," + tpcm);

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




