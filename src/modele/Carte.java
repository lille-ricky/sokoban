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

    private void construire(List<String> lignes){
        nbLignes = lignes.size();
        nbColonnes = lignes.stream().mapToInt(String::length).max().orElse(0);
        grille = new Case[nbLignes][nbColonnes];
        destinations = new ArrayList<>();
        nbMouvements = 0;

        for (int y = 0; y < nbLignes; y++){
            String ligne = lignes.get(y);
            for (int x = 0; x < nbColonnes; x++){
                char c = x < ligne.length() ? ligne.charAt(x) : '/';
                switch(c) {
                    case '#':
                        grille[y][x] = new Mur(); break;
                    case '/':
                        grille[y][x] = new Vide(); break;
                    case '.':
                        grille[y][x] = new Destination(); 
                        destinations.add(new int[]{x, y}); 
                        break;
                    case '@':
                        grille[y][x] = new Sol();
                        robot = new Robot(x, y);
                        grille[y][x].setjoueur(true);
                        break;
                    case '+':
                        grille[y][x] = new Destination();
                        destinations.add(new int[]{x, y});
                        robot = new Robot(x, y);
                        grille[y][x].setjoueur(true);
                        break;
                    case '$':
                        grille[y][x] = new Sol();
                        grille[y][x].setCaisse(true);
                        break;
                    case '*':
                        grille[y][x] = new Destination();
                        destinations.add(new int[]{x, y});
                        grille[y][x].setCaisse(true);
                        break;
                    default :
                        grille[y][x] = new Sol(); break;

                }
            }
        }
    }
    
}
