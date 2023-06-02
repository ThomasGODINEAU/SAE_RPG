package modele;

import java.util.ArrayList;

public class Solution {
    ArrayList<Integer> chemin;
    int duree;
    int nombreDeQuete;
    int nombreDeCasesParcourues;

    public Solution(ArrayList<Integer> chemin, int duree, int nombreDeQuete, int nombreDeCasesParcourues) {
        this.chemin = chemin;
        this.duree = duree;
        this.nombreDeQuete = nombreDeQuete;
        this.nombreDeCasesParcourues = nombreDeCasesParcourues;
    }

    public ArrayList<Integer> getChemin() {
        return chemin;
    }

    public int getDuree() {
        return duree;
    }

    public int getNombreDeQuete() {
        return nombreDeQuete;
    }

    public int getNombreDeCasesParcourues() {
        return nombreDeCasesParcourues;
    }
}
