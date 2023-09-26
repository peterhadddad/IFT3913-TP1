package com.example.tp13913;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.nio.file.*;
import java.util.regex.*;


public class tls {

    public static  void main(String[] args){
        if(args.length!=1){
            System.out.println("Usage: java tls <chemin du dosser>");
            return;
        }
        String cheminDossierEntree=args[0];
        List<String> lignesSortie=new ArrayList<>();
        lignesSortie.add("Chemin du fichier,Nom du packet,Nom de la Classe, TLOC de la classe" +
                ", TAssert de la classe, TCPM de la classe ");



        processFolder(new File(cheminDossierEntree),lignesSortie);

        try {
            FileWriter writer = new FileWriter("output.csv");
            for (String line : lignesSortie){
                writer.write(line+ "\n");
            }
            writer.close();
            System.out.println("Resultat en output.csv");


        } catch(IOException e){
            e.printStackTrace();

        }
    }
    public static void processFolder(File folder,List<String> ligneCSV){
        if(folder.isDirectory()){
            for(File file: folder.listFiles()){
                if(file.isDirectory()){
                    processFolder(file,ligneCSV);
                } else if (file.getName().endsWith(".java")) {
                    processJavaFile(file,ligneCSV);

                }

            }
        }

    }
    public static void processJavaFile(File folder,List<String> ligneCSV){
        try {
            String pathFile= folder.getPath();
            String nomPackage="";
            String nomClasse="";
            int tloc=0;
            int tassert=0;

            Scanner scanner= new Scanner(folder);
            while(scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                if (ligne.startsWith("package")) {
                    nomPackage = ligne.split(" ")[1].replace(";", "");
                } else if (ligne.startsWith("public class")) {
                    nomClasse = ligne.split(" ")[2].split("\\{")[0];
                }
                tloc++;

                if (ligne.contains("assert ")) {
                    tassert++;
                }
            }
        }catch(IOException e){
            e.printStackTrace();

        }
    }

}




