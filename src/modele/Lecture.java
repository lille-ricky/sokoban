package modele;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Lecture {

    private List<String> lignes;
    private int nbLignes;
    private int tailleLigne;

    public Lecture(String nomFichier) {
        lignes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                lignes.add(ligne);
            }
        } catch (IOException e) {
            System.err.println("Erreur de lecture fichier : " + nomFichier);
        }
        nbLignes = lignes.size();
        tailleLigne = nbLignes > 0 ? lignes.get(0).length() : 0;
    }

    public List<String> getLignes() {
        return lignes;
    }

    public int getNbLignes() {
        return nbLignes;
    }

    public int getTailleLigne() {
        return tailleLigne;
    }
}
