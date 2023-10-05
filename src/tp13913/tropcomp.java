package tp13913;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class tropcomp {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 2) {
            System.out.println("Usage: java tropcomp <chemin du dossier> <seuil>%");
            return;
        }
        String outputFile = args[1];
        String cheminDentree = args[2];
        double  seuil = Double.valueOf(args[3]);

        List<String> ligneSortie = new ArrayList<>();
        ligneSortie = processFolder(new File(cheminDentree), ligneSortie);
        try{
            if ("-o".equals(args[0])) {
                outputFile = args[1];
                args = Arrays.copyOfRange(args, 2, args.length);
                writeToFile(outputFile,ligneSortie);
                printConsole(ligneSortie,seuil);
            }
        }
        catch(NumberFormatException e){
            System.out.println("Valide");
        }

    }

    private static void writeToFile(String filePath,List<String> ligneCSV) {
        try(FileWriter writer=new FileWriter(filePath)){
            for(String lines:ligneCSV){
                writer.write(lines+"\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> processFolder(File folder,List<String> ligneCSV) throws FileNotFoundException {

        if (folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                List<String> Array = new ArrayList<>();
                if (file.isDirectory()) {
                    ligneCSV.addAll(processFolder(file, Array));
                } else if (file.getName().endsWith(".java")) {
                    ligneCSV.addAll(tls.processJavaFile(file, Array));
                    continue;
                }
            }
            isOrganized(ligneCSV);
        }
        return ligneCSV;
    }

    public static void isOrganized(List<String> allClasses) {
        allClasses.sort(Comparator.comparing(testClass -> {
            String[] metrics = testClass.split(",");
            double tpcmDeClass = Double.parseDouble(metrics[5]);
            return tpcmDeClass;
        }));

    }
    public static int pourcentageSeuil(double seuil,int longeur){
        double valeur;
        if(longeur==0){
            throw new IllegalArgumentException("Pas faisable");
        }
        valeur=(seuil*longeur)/100;
        return (int) valeur;
    }
    private static void printConsole(List<String> ligneSortie,double seuil) {
        int valeur=pourcentageSeuil(seuil,ligneSortie.size());
        for(int i=0;i<=valeur;i++){
            System.out.println(ligneSortie.get(i));
        }
    }
}