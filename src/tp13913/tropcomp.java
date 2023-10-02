package tp13913;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class tropcomp {

    public static void main(String[] args) {
        if (args.length < 2 || args.length>4 || args[0].equals("-o") ) {
            System.out.println("Usage: java tropcomp <chemin du dossier> <seuil>%");
            return;
        }
        String entree=args[args.length-2];
        String sortie= args.length==4?args[1]:null;
        double seuil =Double.parseDouble(args[args.length-1]);
        List<String> lignesSortie = new ArrayList<>();
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


}