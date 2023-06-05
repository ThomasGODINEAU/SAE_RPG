package modele;

import java.util.ArrayList;

public class Scenario {
    private ArrayList <Quete> listeQuetes; // Déclare un champ privé de type ArrayList qui contiendra une liste de Quetes

    /**
     * Constructeur de la classe Scenraio
     * Initialise la liste de Quetes vide
     */
    public Scenario() {
        listeQuetes = new ArrayList<Quete>();
    }


    public String toString(){
        return listeQuetes.size() + " \n" + listeQuetes.toString();
    }

    /** Méthode ajout
     * Ajoute la quête en parmetre à la liste des quetes
     *
     * @param parQuete (Quete)
     */
    public void ajout(Quete parQuete){
        listeQuetes.add(parQuete);
        listeQuetes.sort(Quete::compareTo);
    }

    public ArrayList<Quete> getListeQuetes() {
        return listeQuetes;
    }
}
