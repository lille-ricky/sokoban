package vueTexte;

public class SokobanTexte {

    public static void main(String[] args) {
        int niveau = 1;
        if (args.length > 0){
            try {
                niveau = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
        }

        }

        String fichier = "bin/map/map" + niveau + ".txt";
        ModeTexte jeu = new ModeTexte(fichier, niveau);
        jeu.jouer();

    }

}
