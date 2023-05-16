package client;

import LectureScenario.LectureFichierTexte;
import modele.Personnage;
import modele.Quete;
import modele.Scenario;

import java.io.File;
import java.util.ArrayList;

public class SolutionDeNiveau1Exhaustive {
    public static void main(String [] args) {
        // Création du scénario et lecture du fichier texte
        Scenario scenario = new Scenario();
        scenario = LectureFichierTexte.lecture(new File("scenario" + File.separator + "scenario_0.txt"));
        ArrayList<Quete> listeQuete = scenario.getListeQuetes();

        // Tri de la liste des quêtes en utilisant la méthode compareTo de la classe Quete
        listeQuete.sort(Quete::compareTo);

        System.out.println("#########################################################################################");
        System.out.println("############################## Methode gloutonne exhaustive #############################");
        System.out.println("#########################################################################################");

        Personnage perso = new Personnage();

        // Boucle principale jusqu'à ce que toutes les quêtes sauf la quête finale soient réalisées
        while (perso.getQuetesTerminees().size() != listeQuete.size()-1) {
            // Obtention de la liste des quêtes réalisables par le personnage
            ArrayList<Quete> quetesPossibles = perso.queteRealisable(scenario);

            // Suppression de la quête finale de la liste, car elle sera traitée en dehors de la boucle while
            if (quetesPossibles.contains(listeQuete.get(0)))
                quetesPossibles.remove(listeQuete.get(0));

            // Réalisation de la quête la plus proche parmi les quêtes réalisables
            Quete queteFaite;
            queteFaite = perso.queteLaPlusProche(quetesPossibles).get(0);
            perso.realisationQuete(queteFaite);
        }

        // Réalisation de la quête finale de la liste
        perso.realisationQuete(listeQuete.get(0));
        // Affichage final du tems écoulé des des quêtes que le personnage à réalisé.
        System.out.println("Quetes realisees : " + perso.getQuetesTerminees());
        System.out.println("Nombre de quetes realisees : " + perso.getQuetesTerminees().size());
        System.out.println("Temps ecoule : " + perso.getTempsEcoule());
    }
}
