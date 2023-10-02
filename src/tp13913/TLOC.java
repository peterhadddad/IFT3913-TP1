package tp13913;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

 class TLOC{
    public static int calculerNombredeLigneTloc(String fichierSource){

        int nombreTloc=0;

        try(BufferedReader br=new BufferedReader(new FileReader(fichierSource))){
            String ligne;
            while((ligne=br.readLine())!=null){
                ligne=ligne.trim();
                if(ligne.startsWith("/*")||ligne.startsWith("//")||ligne.startsWith("*")||
                ligne.isEmpty()){
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
