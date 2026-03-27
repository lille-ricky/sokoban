package modele;

public abstract class Case {

    protected boolean aJoueur;
    protected boolean aCaisse;

    public Case() {
        this.aJoueur = false;
        this.aCaisse = false;
    }

    public boolean aJoueur() {
        return aJoueur;
    }

    public boolean aCaisse() {
        return aCaisse;
    }

    public void setjoueur(boolean b) {
        this.aJoueur = b;
    }

    public void setCaisse(boolean b) {
        this.aCaisse = b;
    }

    public abstract boolean estAccesible();

    public abstract char getCaractereBase();

    @Override
    public String toString() {
        if (aJoueur && aCaisse) {
            return "+";
        }
        if (aJoueur) {
            return aJoueur() ? (estDestination() ? "+" : "@") : "@";
        }
        if (aCaisse) {
            return estDestination() ? "*" : "$";
        
        }return String.valueOf(getCaractereBase());
    }

    public boolean estDestination() {
        return false;
    }

}
