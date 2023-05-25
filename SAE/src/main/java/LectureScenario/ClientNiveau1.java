package client;

import LectureScenario.LectureFichierTexte;
import modele.Niveau1;
import modele.Personnage;
import modele.Quete;
import modele.Scenario;

import java.io.File;
import java.util.ArrayList;

public class ClientNiveau1 {
    public static void main(String [] args) {

        // Création du scénario et lecture du fichier texte
        Scenario scenario = new Scenario();
        scenario = LectureFichierTexte.lecture(new File("scenario" + File.separator + "scenario_0.txt"));
        ArrayList<Quete> listeQuete = scenario.getListeQuetes();
        Personnage perso = new Personnage();

        Niveau1.solutionDeNiveau1Efficace(perso, listeQuete, scenario);

        // Affichage final du temps écoulé des des quêtes que le personnage à réalisé.
        System.out.println("Quetes realisees : " + perso.getQuetesTerminees());
        System.out.println("nombre de quetes realisees : " + perso.getQuetesTerminees().size());
        System.out.println("Temps ecoule : " + perso.getTempsEcoule());
    }
}
