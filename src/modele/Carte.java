package modele;

import java.util.ArrayList;
import java.util.List;



public class Carte {
    private Case[][] grille;
    private int nbColonnes;
    private int nbLignes;
    private Robot robot;
    private List<int[]> destinations;
    private int nbMouvements;

    public Carte(String nomFichier){
        Lecture lec = new Lecture(nomFichier);
        construire(lec.getLignes());
    }

    public Carte(List<String> lignes){
        construire(lignes);
    }
}
