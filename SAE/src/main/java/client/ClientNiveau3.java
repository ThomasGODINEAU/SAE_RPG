package client;

import LectureScenario.LectureFichierTexte;
import modele.Niveau2;
import modele.Personnage;
import modele.Quete;
import modele.Scenario;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientNiveau3 {
    public static void main(String [] args) {

        Scanner saisieUtilisateur = new Scanner(System.in);
        System.out.println("Quel scenario voulez vous faire ? " );
        int numeroScenario = saisieUtilisateur.nextInt();
        System.out.println("Souhaitez vous les solutions exhaustives ou les solutions efficaces ? (Efficace / Exhaustive)" );
        String efficaceOuExhaustive = saisieUtilisateur.next();
        System.out.println("Souhaitez vous les pires ou les meilleures solutions ? (Meilleure / Pire)");
        String pireOuMeilleureSolution = saisieUtilisateur.next();
        System.out.println("Combien de solutions souhaitez-vous ? ");
        int nombreDeSolutions = saisieUtilisateur.nextInt();
        System.out.println("Par quelle valeur souhaitez-vous vos chemins ? \n" +
                "Par Duree ? (Duree)\t Par nombre de quetes realisees ? (NombreDeQuete)\t Par nombre de cases parcourues ? (NombreDeCasesParcourues)");
        String nomSolution = saisieUtilisateur.next();



        Scenario scenario = new Scenario();
        scenario = LectureFichierTexte.lecture(new File("scenario" + File.separator + "scenario_" + numeroScenario + ".txt"));
        ArrayList<Quete> listeQuete = scenario.getListeQuetes();
        Personnage personnage = new Personnage();
        Niveau2 solutionNiveau3 = new Niveau2();



        if (efficaceOuExhaustive == "Efficace")
            solutionNiveau3.getCheminEfficace(personnage, scenario, listeQuete, nombreDeSolutions, nomSolution, pireOuMeilleureSolution);
        else
            solutionNiveau3.getCheminExhaustif(personnage, scenario, listeQuete, nombreDeSolutions, nomSolution, pireOuMeilleureSolution);
        System.out.println(solutionNiveau3);
    }
}
