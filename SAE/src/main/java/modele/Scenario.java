package modele;

import java.util.ArrayList;

public class Scenario {
    ArrayList <Quete> listeQuetes;
    public Scenario(){
        listeQuetes = new ArrayList<Quete>();
    }

    public String toString(){
        return listeQuetes.size() + " \n" + listeQuetes.toString();
    }

    public void ajout(Quete parQuete){
        listeQuetes.add(parQuete);
    }
}
