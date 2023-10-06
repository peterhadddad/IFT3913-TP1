package tp13913;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

 class TLOC{
     public static void main(String[] args){
         if(args.length!=1){
             System.out.println("Utilisation plus qu'un ficher: java tloc <fichier_source>");
             System.exit(1);
         }
         String source=args[0];
         int nombreDelignesTloc= calculerNombredeLigneTloc(source);
         System.out.println("Nombre de Ligne :" +nombreDelignesTloc);

     }
    public static int calculerNombredeLigneTloc(String fichierSource){
        int nombreTloc=0;
        try(BufferedReader br=new BufferedReader(new FileReader(fichierSource))){
            String ligne;
            while((ligne=br.readLine())!=null){
                ligne=ligne.trim();

                if(ligne.startsWith("/*")||ligne.startsWith("//")||ligne.startsWith("*")||ligne.isEmpty()){
                         continue;
                }
                else
                nombreTloc++;
            }
        }
        catch(IOException e){
            System.err.println("Erreur"+e.getMessage());
            System.exit(2);
        }
        return nombreTloc;
    }
}
