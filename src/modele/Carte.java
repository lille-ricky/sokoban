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

    public Carte(String nomFichier) {
        Lecture lec = new Lecture(nomFichier);
        construire(lec.getLignes());
    }

    public Carte(List<String> lignes) {
        construire(lignes);
    }

    private void construire(List<String> lignes) {
        nbLignes = lignes.size();
        nbColonnes = lignes.stream().mapToInt(String::length).max().orElse(0);
        grille = new Case[nbLignes][nbColonnes];
        destinations = new ArrayList<>();
        nbMouvements = 0;

        for (int y = 0; y < nbLignes; y++) {
            String ligne = lignes.get(y);
            for (int x = 0; x < nbColonnes; x++) {
                char c = x < ligne.length() ? ligne.charAt(x) : '/';
                switch (c) {
                    case '#':
                        grille[y][x] = new Mur();
                        break;
                    case '/':
                        grille[y][x] = new Vide();
                        break;
                    case '.':
                        grille[y][x] = new Destination();
                        destinations.add(new int[]{x, y});
                        break;
                    case '@':
                        grille[y][x] = new Sol();
                        robot = new Robot(x, y);
                        grille[y][x].setJoueur(true);
                        break;
                    case '+':
                        grille[y][x] = new Destination();
                        destinations.add(new int[]{x, y});
                        robot = new Robot(x, y);
                        grille[y][x].setJoueur(true);
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
                    default:
                        grille[y][x] = new Sol();
                        break;

                }
            }
        }
    }

    public boolean deplacer(Direction dir) {
        robot.setDirection(dir);

        int rx = robot.getX();
        int ry = robot.getY();
        int nx = rx + dir.getDx();
        int ny = ry + dir.getDy();

        if (!estDansGrille(nx, ny)) {
            return false;
        }
        Case caseVoulue = grille[ny][nx];

        if (!caseVoulue.estAccesible()) {
            return false;
        }

        if (caseVoulue.aCaisse()) {
            int cx = nx + dir.getDx();
            int cy = ny + dir.getDy();
            if (!estDansGrille(cx, cy)) {
                return false;
            }
            Case caseCaisse = grille[cy][cx];
            if (!caseCaisse.estAccesible() || caseCaisse.aCaisse()) {
                return false;
            }
            caseVoulue.setCaisse(false);
            caseCaisse.setCaisse(true);
        }

        grille[ry][rx].setJoueur(false);
        caseVoulue.setJoueur(true);
        robot.setPosition(nx, ny);
        nbMouvements++;
        return true;
    }

    private boolean estDansGrille(int x, int y) {
        return x >= 0 && x < nbColonnes && y >= 0 && y < nbLignes;
    }

    public boolean finDePartie() {
        for (int[] coord : destinations) {
            if (!grille[coord[1]][coord[0]].aCaisse()) {
                return false;
            }
        }
        return !destinations.isEmpty();
    }

    public Case[][] getGrille() {
        return grille;
    }

    public int getNbColonnes() {
        return nbColonnes;
    }

    public int getNbLignes() {
        return nbLignes;
    }

    public Robot getRobot() {
        return robot;
    }

    public Case getCase(int x, int y) {
        return grille[y][x];
    }

    public int getNbMouvements() {
        return nbMouvements;
    }

    public String toString(){
        StringBuilder ch = new StringBuilder();
        for (int y = 0; y < nbLignes; y++){
            for (int x = 0; x < nbColonnes; x++) {
                Case c = grille[y][x];
                char car;
                if (c.aJoueur()){
                    car = c.estDestination() ? '+' : '@';
                } else if (c.aCaisse()){
                    car = c.estDestination() ? '*' : '$';
                } else {
                    car = c.getCaractereBase();
                }
                ch.append(car);
            }
            ch.append('\n');
        }
        return ch.toString();
    }
}
