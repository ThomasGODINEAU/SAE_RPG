package modele;

import java.util.ArrayList;

public class Solution {
    ArrayList<Integer> chemin;
    int duree;
    int nombreDeQuete;
    int nombreDeCasesParcourues;
    int experience;

    public Solution(ArrayList<Integer> chemin, int duree, int nombreDeQuete, int nombreDeCasesParcourues, int experience) {
        this.chemin = chemin;
        this.duree = duree;
        this.nombreDeQuete = nombreDeQuete;
        this.nombreDeCasesParcourues = nombreDeCasesParcourues;
        this.experience = experience;
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

    public int getExperience() {
        return experience;
    }
}
