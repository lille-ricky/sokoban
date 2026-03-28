package vueTexte;

import modele.Carte;
import modele.Direction;

import java.util.HashMap;
import java.util.Map;
import javax.management.modelmbean.ModelMBean;

public class ModeTexte {

    private Carte modele;
    private Map<Character, Direction> touches;
    private int niveau;

    public ModeTexte(String nomFichier, int niveau) {
        this.modele = new Carte(nomFichier);
        this.niveau = niveau;
        touches = new HashMap<>();

        touches.put('w', Direction.HAUT);
        touches.put('s', Direction.BAS);
        touches.put('a', Direction.GAUCHE);
        touches.put('d', Direction.DROITE);

        touches.put('k', Direction.HAUT);
        touches.put('j', Direction.BAS);
        touches.put('h', Direction.GAUCHE);
        touches.put('l', Direction.DROITE);
    }

    private char lireAction() {
        while (true) {
            char c = Outil.lireCaractere();
            if (touches.containsKey(c) || c == 'x' || c == 'r') {
                return c;
            }
            System.out.println("Touche inconnue. Veuillez utiliser que w/a/s/d pour vous deplacer , x pour quitter et r pour recommencer.");

        }
    }

    public void jouer() {
        System.out.println("=== SOKOBAN - Niveau " + niveau + " ===");
        System.out.println("w=haut a=gauche s=bas d=droite r=recommencer x=quitter\n");

        boolean continuer = true;
        while (continuer && !modele.finDePartie()) {
            System.out.println(modele);
            System.out.println("Mouvements : " + modele.getNbMouvements());
            System.out.println("Action : ");

            char action = lireAction();
            switch (action) {
                case 'x':
                    continuer = false;
                    System.out.println("Quitting...");
                    break;
                case 'r':
                    modele = new Carte("maps/map" + niveau + ".txt");
                    System.out.println("Carte recommencee.");
                    break;
                default:
                    modele.deplacer(touches.get(action));
            }
        }
        if (modele.finDePartie()) {
            System.out.println(modele);
            System.out.println("Felicitations ! Niveau " + niveau + "termine en "
                    + modele.getNbMouvements() + " mouvements!");
        }
    }
}
