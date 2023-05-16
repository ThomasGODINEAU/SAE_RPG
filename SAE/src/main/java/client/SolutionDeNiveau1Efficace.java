package client;

import LectureScenario.LectureFichierTexte;
import modele.Personnage;
import modele.Quete;
import modele.Scenario;

import java.io.File;
import java.util.ArrayList;

public class SolutionDeNiveau1Efficace {
    public static void main(String [] args) {
        // Création du scénario et lecture du fichier texte
        Scenario scenario = new Scenario();
        scenario = LectureFichierTexte.lecture(new File("scenario" + File.separator + "scenario_0.txt"));
        ArrayList<Quete> listeQuete = scenario.getListeQuetes();

        // Tri de la liste des quêtes en utilisant la méthode compareTo de la classe Quete
        listeQuete.sort(Quete::compareTo);

        System.out.println("#########################################################################################");
        System.out.println("############################### Methode gloutonne efficace ##############################");
        System.out.println("#########################################################################################");

        Personnage perso = new Personnage();

        // Boucle principale jusqu'à ce que la quête finale soit réalisable
        while (!perso.queteFinaleRealisable(scenario)) {
            boolean queteFaitePourCeTourDansLaBoucleWhile = false;

            // Obtention de la liste des quêtes réalisables par le personnage
            ArrayList<Quete> quetesPossibles = perso.queteRealisable(scenario);

            // Suppression de la quête finale de la liste triée, car elle sera traitée dès qu'on sortira de la boucle while
            if (quetesPossibles.contains(listeQuete.get(0)))
                quetesPossibles.remove(listeQuete.get(0));

            // Récupération des préconditions nécessaires pour la quête finale
            int[] preconditionQueteFinaleNecessaireAFaire;
            preconditionQueteFinaleNecessaireAFaire = listeQuete.get(0).getPrecondition();

            // Parcours des préconditions de la quête finale
            for (int i : preconditionQueteFinaleNecessaireAFaire) {
                Quete quetePrecontion = listeQuete.get(i);

                // Vérification si une quête précondition de la quête finale est réalisable
                if (quetesPossibles.contains(quetePrecontion)) {
                    if (!queteFaitePourCeTourDansLaBoucleWhile) {
                        // Réalisation de la quête précondition de la quête finale
                        perso.realisationQuete(quetePrecontion);
                        queteFaitePourCeTourDansLaBoucleWhile = true;
                        break;
                    }
                }
            }

            // Si aucune quête précondition n'est réalisée, réalisation de la quête la plus proche
            if (!queteFaitePourCeTourDansLaBoucleWhile) {
                Quete queteFaite;
                queteFaite = perso.queteLaPlusProche(quetesPossibles).get(0);
                perso.realisationQuete(queteFaite);
            }
        }
        // Réalisation de la quête finale de la liste
        perso.realisationQuete(listeQuete.get(0));
        // Affichage final du tems écoulé des des quêtes que le personnage à réalisé.
        System.out.println("Quetes realisees : " + perso.getQuetesTerminees());
        System.out.println("nombre de quetes realisees : " + perso.getQuetesTerminees().size());
        System.out.println("Temps ecoule : " + perso.getTempsEcoule());
    }
}
