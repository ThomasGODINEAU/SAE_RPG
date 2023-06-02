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
}
