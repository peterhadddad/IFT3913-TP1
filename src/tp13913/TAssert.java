package tp13913;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class TAssert {
    public static void main(String args[]){
        if(args.length!=1){
            System.out.println("Utilisation incorrect:<fichier_source>");
            System.exit(1); //permet d'indiquer qu'il ya une erreur
        }
        String source=args[0];
        int nombreAssession= compterAssession(source);
        System.out.println("Nombre de test :" +nombreAssession);
    }
    public static int compterAssession(String sourceFile){
        int nombreAssert=0;
        try(BufferedReader br=new BufferedReader(new FileReader(sourceFile))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                ligne=ligne.trim();
                if((ligne.contains("assert") &&!((ligne.contains("/*")  ||
                        ligne.contains("//")||ligne.contains("*/"))|| ligne.startsWith("*"))
                        &&!ligne.contains("import"))){
                    nombreAssert++;
                }
            }
        }
        catch(IOException e){
            System.err.println("Erreur"+e.getMessage());
            System.exit(2);
        }
        return nombreAssert;
    }
}
