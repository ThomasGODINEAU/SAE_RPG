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
        while (! perso.queteFinaleRealisable(scenario)) {
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
}
