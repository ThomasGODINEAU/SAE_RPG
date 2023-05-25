package modele;

import java.util.ArrayList;

public class Niveau1 {

    public static void solutionDeNiveau1Exhaustive(Personnage perso, ArrayList<Quete> listeQuete, Scenario scenario) {

        // Boucle principale jusqu'à ce que toutes les quêtes sauf la quête finale soient réalisées
        while (perso.getQuetesTerminees().size() != listeQuete.size() - 1) {
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
    }

    public static void solutionDeNiveau1Efficace(Personnage perso, ArrayList<Quete> listeQuete, Scenario scenario) {

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
    }

}
