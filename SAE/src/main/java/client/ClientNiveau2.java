package client;

import LectureScenario.LectureFichierTexte;
import modele.Niveau2;
import modele.Personnage;
import modele.Quete;
import modele.Scenario;

import java.io.File;
import java.util.ArrayList;
public class ClientNiveau2 {
    public static void main(String [] args) {
        Scenario scenario = new Scenario();
        scenario = LectureFichierTexte.lecture(new File("scenario" + File.separator + "scenario_1.txt"));
        ArrayList<Quete> listeQuete = scenario.getListeQuetes();
        System.out.println(listeQuete);
        Personnage personnage = new Personnage();
        Niveau2 solutionNiveau2 = new Niveau2();
        solutionNiveau2.getCheminEfficace(personnage, scenario, listeQuete, 1, "Duree", "Meilleure");
        System.out.println(solutionNiveau2);
    }
}
