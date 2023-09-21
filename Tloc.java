package com.example.tp1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Tloc {
    public static void main(String[] args){
        if(args.length!=1){ // Permet de savoir si il ya plus qu'un argument dans le fichier
                            // si oui le progamme s'arrete
            System.out.println("Utilisation plus qu'un ficher: java tloc <fichier_source>");
            System.exit(1); //permet d'indiquer qu'il ya une erreur
        }

        //source permet de reccuperer le premier argument
        String source=args[0];

        //Source est envoyer a calculerNombredeLigneTloc pour calculer le nombre de ligne
        int nombreDelignesTloc= calculerNombredeLigneTloc(source);
        System.out.println("Nombre de Ligne :" +nombreDelignesTloc);

    }


    public static int calculerNombredeLigneTloc(String fichierSource){

        //permet de calculer le nombre de ligne
        int nombreTloc=0;

        //Permet de lire le code
        try(BufferedReader br=new BufferedReader(new FileReader(fichierSource))){
            //chaque ligne est un string
            String ligne;


            //estCommentaire permet de detecter un commentaire
            boolean estCommentaire=false;


            // la boucle va permettre de trim la ligne et voir si il y a des commentaire sinon nombre tloc va incrementer de 1
            while((ligne=br.readLine())!=null){
                ligne=ligne.trim();
                if(ligne.isEmpty()){
                    continue;
                }
                if(ligne.startsWith("/*")){
                    estCommentaire=true;
                    if(ligne.endsWith("*/")){
                        estCommentaire=false;
                    }
                    continue;

                }
                if(estCommentaire){
                    if(ligne.endsWith("*/")){
                        estCommentaire=false;
                    }
                    continue;
                }
                if(ligne=="//"){
                    continue;
                }


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
