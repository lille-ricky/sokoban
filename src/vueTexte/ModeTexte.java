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
}


