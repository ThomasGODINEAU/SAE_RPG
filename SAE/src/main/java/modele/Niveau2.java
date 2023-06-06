package modele;

import java.util.ArrayList;

public class Niveau2 {
    ArrayList<Integer> dureeTotale; // liste contenant les durées totales des chemins
    ArrayList<Integer> nombreDeQuetesTotal; // liste contenant le nombre total de quete réalisé
    ArrayList<Integer> experienceTotaleChemin; // liste contenant l'expérience total acquise au cours du chemin
    ArrayList<Integer> nombreDeCasesTotalParcourues; // liste contenant le nombre total de cases parcouru
    ArrayList<ArrayList<Integer>> cheminDeQueteEffectueParLePersonnage;

    /**
     * Constructeur du Niveau2
     *
     * Initialise les champs avec des listes vides
     */
    public Niveau2() {
        dureeTotale = new ArrayList<>();
        nombreDeQuetesTotal = new ArrayList<>();
        experienceTotaleChemin = new ArrayList<>();
        nombreDeCasesTotalParcourues = new ArrayList<>();
        cheminDeQueteEffectueParLePersonnage = new ArrayList<>();
    }

    public String toString() {
        return ("Durees totales : " + dureeTotale + "\n" +
        "Nombre de quetes total : " + nombreDeQuetesTotal + "\n" +
        "Experience acquise lors du chemin" + experienceTotaleChemin + "\n" +
        "Nombre de cases parcourues : " + nombreDeCasesTotalParcourues + "\n" +
        "Chemins possibles : " + cheminDeQueteEffectueParLePersonnage + "\n");
    }

    /**
     * Explore de manière efficace toutes les combinaisons possibles de quêtes réalisées par un personnage dans un scénario donné.
     * Enregistre les chemins de quêtes effectués par le personnage, ainsi que les informations relatives à chaque chemin (durée totale, nombre de quêtes réalisées, nombre de cases parcourues).
     * Ne conserve que le nombre de chemins demandé selon sa meilleure ou pire valeur en terme de durée, nombre de quete ou de nombre de case parcourues
     *
     * @param perso        Le personnage qui effectue les quêtes.
     * @param scenario     Le scénario contenant les quêtes disponibles.
     * @param listeQuete   La liste des quêtes du scénario.
     * @param nombreSolutionsVoulues    Le nombre de solutions voulues
     * @param nomSolution  Le parametre selon lequel les chemins sont choisis
     * @param pireOuMeilleureSolution   Le type de solution voulu (pire ou meilleure)
     */
    public void getCheminEfficace(Personnage perso, Scenario scenario, ArrayList<Quete> listeQuete, int nombreSolutionsVoulues, String nomSolution, String pireOuMeilleureSolution) {

        // Récupère l'indice du pire/meilleur chemin parmi les chemins enregistrés
        int indexPireOuMeilleureCheminActuelle = recupererIndicePireOuMeilleureChemin(nomSolution, pireOuMeilleureSolution);

        // Vérifie si la quête finale est réalisable
        if (perso.queteFinaleRealisable(scenario)) {

            // Réalise la quête finale
            perso.realisationQuete(listeQuete.get(0));

            // Calcule la durée totale du chemin
            int tempsChemin = perso.getTempsEcoule();

            // Regarde si la quete réalisée est meilleur que la pire des quete réalisée
            if (meilleureOuPireChemin(perso, listeQuete, nomSolution, pireOuMeilleureSolution, nombreSolutionsVoulues)) {

                // Ajoute les statistiques du chemin au champs
                dureeTotale.add(tempsChemin);
                nombreDeQuetesTotal.add(perso.getQuetesTerminees().size());

                // Calcule le nombre de cases parcourues par le personnage dans le chemin
                int nombreDeCases = perso.getTempsEcoule();
                for (int i = 0; i < perso.getQuetesTerminees().size(); i++) {
                    nombreDeCases -= listeQuete.get(perso.getQuetesTerminees().get(i)).getDuree();
                }
                nombreDeCasesTotalParcourues.add(nombreDeCases);
                experienceTotaleChemin.add(perso.getExperience());

                // Enregistre le chemin de quête effectué par le personnage
                cheminDeQueteEffectueParLePersonnage.add(new ArrayList<>(perso.getQuetesTerminees()));

                // Retire la pire/meilleure quete si il y en a plus que le nombre voulu
                if (nombreSolutionsVoulues < cheminDeQueteEffectueParLePersonnage.size()) {
                    cheminDeQueteEffectueParLePersonnage.remove(indexPireOuMeilleureCheminActuelle);
                    dureeTotale.remove(indexPireOuMeilleureCheminActuelle);
                    nombreDeQuetesTotal.remove(indexPireOuMeilleureCheminActuelle);
                    nombreDeCasesTotalParcourues.remove(indexPireOuMeilleureCheminActuelle);
                    experienceTotaleChemin.remove(indexPireOuMeilleureCheminActuelle);

                }
            }
            // Annule la quête finale réalisée pour explorer d'autres chemins
                perso.annulationQuete(listeQuete);
        }

        // Obtient les quêtes réalisables par le personnage dans le scénario actuel
        ArrayList<Quete> quetesPossibles = perso.queteRealisable(scenario);

        // Vérifie si la quête finale est parmi les quêtes réalisables et la supprime car elle est réalisée séparement du reste
        if (quetesPossibles.contains(listeQuete.get(0))) {
            quetesPossibles.remove(listeQuete.get(0));
        }

        // Explore toutes les quêtes réalisables restantes
        for (int i = 0; i < quetesPossibles.size(); i++) {

            // Vérifie si la quête finale n'est pas réalisable pour éviter d'explorer des chemins inutiles
            if (!perso.queteFinaleRealisable(scenario)) {

                // Réalise une quête
                perso.realisationQuete(quetesPossibles.get(i));

                // Regarde si la quete réalisée est meilleur que la pire des quete réalisée
                int tempsChemin = perso.getTempsEcoule();

                // Ne continue pas le chemin si le chemin en cours est déjà moins bien que la pire solution enregistrée
                if (meilleureOuPireChemin(perso, listeQuete, nomSolution, pireOuMeilleureSolution, nombreSolutionsVoulues) || pireOuMeilleureSolution=="Pire") {

                    // Explore les chemins possibles à partir de cet état
                    getCheminEfficace(perso, scenario, listeQuete, nombreSolutionsVoulues, nomSolution, pireOuMeilleureSolution);
                }

                // Annule la quête réalisée pour revenir à l'état précédent et explorer d'autres chemins
                perso.annulationQuete(listeQuete);
            }
        }
    }


    /** Renvoie l'indice de la pire ou de la meilleure quete
     *
     * @param nomSolution
     * @param pireOuMeilleureQuete
     *
     * @return indexPireOuMeilleureQueteActuelle
     */
    public int recupererIndicePireOuMeilleureChemin(String nomSolution, String pireOuMeilleureQuete) {
        // Initialise l'indice du pire/meilleur chemin au premier chemin
        int indexPireOuMeilleureCheminActuelle = 0;

        // Renvoie l'indice de la pire quete pour la meilleure solution possible
        if (pireOuMeilleureQuete == "Meilleure") {

            // Si la solution demandée est par durée
            if (nomSolution == "Duree") {
                for (int i = 0; i < dureeTotale.size(); i++) {
                    if (dureeTotale.get(i) > dureeTotale.get(indexPireOuMeilleureCheminActuelle)) {
                        indexPireOuMeilleureCheminActuelle = i;
                    }
                }
            }

            // Si la solution demandée est par nombre de quete
            else if (nomSolution == "NombreDeQuete") {
                for (int i = 0; i < nombreDeQuetesTotal.size(); i++) {
                    if (nombreDeQuetesTotal.get(i) > nombreDeQuetesTotal.get(indexPireOuMeilleureCheminActuelle)) {
                        indexPireOuMeilleureCheminActuelle = i;
                    }
                }
            }

            // Si la solution demandée est par experience
            else if (nomSolution == "Experience") {
                for (int i = 0; i < experienceTotaleChemin.size(); i++) {
                    if (experienceTotaleChemin.get(i) > experienceTotaleChemin.get(indexPireOuMeilleureCheminActuelle)) {
                        indexPireOuMeilleureCheminActuelle = i;
                    }
                }
            }

            // Si la solution demandée est par nombre de cases parcourues
            else {
                for (int i = 0; i < nombreDeCasesTotalParcourues.size(); i++) {
                    if (nombreDeCasesTotalParcourues.get(i) > nombreDeCasesTotalParcourues.get(indexPireOuMeilleureCheminActuelle)) {
                        indexPireOuMeilleureCheminActuelle = i;
                    }
                }
            }
        }

        // Renvoie l'indice de la meilleure quete pour la pire solution possible
        else {
            // Si la solution demandée est par durée
            if (nomSolution == "Duree") {
                for (int i = 0; i < dureeTotale.size(); i++) {
                    if (dureeTotale.get(i) < dureeTotale.get(indexPireOuMeilleureCheminActuelle)) {
                        indexPireOuMeilleureCheminActuelle = i;
                    }
                }
            }

            // Si la solution demandée est par nombre de quete
            else if (nomSolution == "NombreDeQuete") {
                for (int i = 0; i < nombreDeQuetesTotal.size(); i++) {
                    if (nombreDeQuetesTotal.get(i) < nombreDeQuetesTotal.get(indexPireOuMeilleureCheminActuelle)) {
                        indexPireOuMeilleureCheminActuelle = i;
                    }
                }
            }

            // Si la solution demandée est par experience
            else if (nomSolution == "Experience") {
                for (int i = 0; i < experienceTotaleChemin.size(); i++) {
                    if (experienceTotaleChemin.get(i) < experienceTotaleChemin.get(indexPireOuMeilleureCheminActuelle)) {
                        indexPireOuMeilleureCheminActuelle = i;
                    }
                }
            }

            else {
                // Si la solution demandée est par nombre de cases parcourues
                for (int i = 0; i < nombreDeCasesTotalParcourues.size(); i++) {
                    if (nombreDeCasesTotalParcourues.get(i) < nombreDeCasesTotalParcourues.get(indexPireOuMeilleureCheminActuelle)) {
                        indexPireOuMeilleureCheminActuelle = i;
                    }
                }
            }
        }
        return indexPireOuMeilleureCheminActuelle;
    }


    /**
     * Méthode meilleurOuPireChemin
     *
     * Renvoie true si notre chemin est meilleur que le pire des meilleurs chemin que nous avons
     * ou si notre chemin est pire que le meilleur des pires chemins que nous avons
     *
     * Renvoie false sinon
     *
     * @param personnage    Le personnage qui réalise les quêtes
     * @param listeQuete    La liste des quêtes du scenario
     * @param nomSolution   Le parametre selon lequel on choisi le chemin
     * @param pireOuMeilleureSolution   Le choix du pire/meilleur chemin
     * @param nombreSolutionsVoulues    Le nombre de solutions voulues
     *
     * @return (boolean) true / false
     */
    public boolean meilleureOuPireChemin(Personnage personnage, ArrayList<Quete> listeQuete, String nomSolution, String pireOuMeilleureSolution, int nombreSolutionsVoulues){

        // Récupère le pire/meilleur chemin actuel
        int indexPireOuMeilleureCheminActuelle = recupererIndicePireOuMeilleureChemin(nomSolution, pireOuMeilleureSolution);

        // Regarde si la solution demandée est la meilleure
        if (pireOuMeilleureSolution == "Meilleure"){

            // Regarde si le type de solution demandé est par durée
            if (nomSolution == "Duree") {
                return (dureeTotale.size() == 0 ||
                        personnage.getTempsEcoule() <= dureeTotale.get(indexPireOuMeilleureCheminActuelle) ||
                        dureeTotale.size() < nombreSolutionsVoulues);
            }

            // Regarde si le type de solution demandé est par nombre de quêtes
            else if (nomSolution == "NombreDeQuete") {
                return (nombreDeQuetesTotal.size() == 0 ||
                        personnage.getQuetesTerminees().size() <= nombreDeQuetesTotal.get(indexPireOuMeilleureCheminActuelle) ||
                        nombreDeQuetesTotal.size() < nombreSolutionsVoulues);
            }

            // Regarde si le type de solution demandé est par nombre de quêtes
            else if (nomSolution == "Experience") {
                return (experienceTotaleChemin.size() == 0 ||
                        personnage.getQuetesTerminees().size() <= experienceTotaleChemin.get(indexPireOuMeilleureCheminActuelle) ||
                        experienceTotaleChemin.size() < nombreSolutionsVoulues);
            }

            // Regarde si le type de solution demandé est par nombre de cases parcourues
            else {
                int nombreDeCases = personnage.getTempsEcoule();
                for (int i = 0; i < personnage.getQuetesTerminees().size(); i++) {
                    nombreDeCases -= listeQuete.get(personnage.getQuetesTerminees().get(i)).getDuree();
                }
                return (nombreDeCasesTotalParcourues.size() == 0 ||
                        nombreDeCases <= nombreDeCasesTotalParcourues.get(indexPireOuMeilleureCheminActuelle) ||
                        nombreDeCasesTotalParcourues.size() < nombreSolutionsVoulues);
            }
        }

        // Sinon la solution demandée est la pire
        else {

            // Regarde si le type de solution demandé est par durée
            if (nomSolution == "Duree") {
                return (dureeTotale.size() == 0 ||
                        personnage.getTempsEcoule() >= dureeTotale.get(indexPireOuMeilleureCheminActuelle) ||
                        dureeTotale.size() < nombreSolutionsVoulues);
            }

            // Regarde si le type de solution demandé est par nombre de quêtes
            else if (nomSolution == "NombreDeQuete") {
                return (nombreDeQuetesTotal.size() == 0 ||
                        personnage.getQuetesTerminees().size() >= nombreDeQuetesTotal.get(indexPireOuMeilleureCheminActuelle) ||
                        nombreDeQuetesTotal.size() < nombreSolutionsVoulues);
            }

            // Regarde si le type de solution demandé est par nombre de quêtes
            else if (nomSolution == "Experience") {
                return (experienceTotaleChemin.size() == 0 ||
                        personnage.getQuetesTerminees().size() >= experienceTotaleChemin.get(indexPireOuMeilleureCheminActuelle) ||
                        experienceTotaleChemin.size() < nombreSolutionsVoulues);
            }

            // Regarde si le type de solution demandé est par nombre de cases parcourues
            else {
                int nombreDeCases = personnage.getTempsEcoule();
                for (int i = 0; i < personnage.getQuetesTerminees().size(); i++) {
                    nombreDeCases -= listeQuete.get(personnage.getQuetesTerminees().get(i)).getDuree();
                }
                return (nombreDeCasesTotalParcourues.size() == 0 ||
                        nombreDeCases >= nombreDeCasesTotalParcourues.get(indexPireOuMeilleureCheminActuelle) ||
                        nombreDeCasesTotalParcourues.size() < nombreSolutionsVoulues);
            }
        }
    }


    // Partie Exhaustive

    /**
     * Explore de manière exhaustive toutes les combinaisons possibles de quêtes réalisées par un personnage dans un scénario donné.
     * Enregistre les chemins de quêtes effectués par le personnage, ainsi que les informations relatives à chaque chemin (durée totale, nombre de quêtes réalisées, nombre de cases parcourues).
     * Ne conserve que le nombre de chemins demandé selon sa meilleure ou pire valeur en terme de durée, nombre de quete ou de nombre de case parcourues
     *
     * @param perso        Le personnage qui effectue les quêtes.
     * @param scenario     Le scénario contenant les quêtes disponibles.
     * @param listeQuete   La liste des quêtes du scénario.
     * @param nombreSolutionsVoulues    Le nombre de solutions voulues
     * @param nomSolution  Le parametre selon lequel les chemins sont choisis
     * @param pireOuMeilleureSolution   Le type de solution voulu (pire ou meilleure)
     */
    public void getCheminExhaustif(Personnage perso, Scenario scenario, ArrayList<Quete> listeQuete, int nombreSolutionsVoulues, String nomSolution, String pireOuMeilleureSolution) {
        // Récupère le pire/meilleur chemin actuel
        int indexPireOuMeilleureCheminActuelle = recupererIndicePireOuMeilleureChemin(nomSolution, pireOuMeilleureSolution);

        // Vérifie si toutes les autres quêtes ont été réalisées
        if (perso.getQuetesTerminees().size() == listeQuete.size() - 1) {
            // Réalise la quête finale
            perso.realisationQuete(listeQuete.get(0));

            // Calcule la durée totale du chemin
            int tempsChemin = perso.getTempsEcoule();

            // Regarde si la quete réalisée est meilleur que la pire des quete réalisée
            if (meilleureOuPireChemin(perso, listeQuete, nomSolution, pireOuMeilleureSolution, nombreSolutionsVoulues)) {

                dureeTotale.add(tempsChemin);
                nombreDeQuetesTotal.add(perso.getQuetesTerminees().size());

                // Calcule le nombre de cases parcourues par le personnage dans le chemin
                int nombreDeCases = perso.getTempsEcoule();
                for (int i = 0; i < perso.getQuetesTerminees().size(); i++) {
                    nombreDeCases -= listeQuete.get(perso.getQuetesTerminees().get(i)).getDuree();
                }
                nombreDeCasesTotalParcourues.add(nombreDeCases);
                experienceTotaleChemin.add(perso.getExperience());
                // Enregistre le chemin de quête effectué par le personnage
                cheminDeQueteEffectueParLePersonnage.add(new ArrayList<>(perso.getQuetesTerminees()));

                // Retire la pire/meilleure quete si il y en a plus que le nombre voulu
                if (nombreSolutionsVoulues < cheminDeQueteEffectueParLePersonnage.size()) {
                    cheminDeQueteEffectueParLePersonnage.remove(indexPireOuMeilleureCheminActuelle);
                    dureeTotale.remove(indexPireOuMeilleureCheminActuelle);
                    nombreDeQuetesTotal.remove(indexPireOuMeilleureCheminActuelle);
                    nombreDeCasesTotalParcourues.remove(indexPireOuMeilleureCheminActuelle);
                    experienceTotaleChemin.remove(indexPireOuMeilleureCheminActuelle);
                }
            }
            // Annule la quête finale réalisée pour explorer d'autres chemins
            perso.annulationQuete(listeQuete);
        }

        // Obtient les quêtes réalisables par le personnage dans le scénario actuel
        ArrayList<Quete> quetesPossibles = perso.queteRealisable(scenario);

        // Vérifie si la quête finale est parmi les quêtes réalisables et la supprime car elle est réalisée séparement du reste
        if (quetesPossibles.contains(listeQuete.get(0))) {
            quetesPossibles.remove(listeQuete.get(0));
        }

        // Explore toutes les quêtes réalisables restantes
        for (int i = 0; i < quetesPossibles.size(); i++) {
            // Réalise une quête
            perso.realisationQuete(quetesPossibles.get(i));

            // Explore les chemins possibles à partir de cet état
            getCheminExhaustif(perso, scenario, listeQuete, nombreSolutionsVoulues, nomSolution, pireOuMeilleureSolution);

            // Annule la quête réalisée pour revenir à l'état précédent et explorer d'autres chemins
            perso.annulationQuete(listeQuete);

        }
    }

    public ArrayList<Integer> getDureeTotale() {
        return dureeTotale;
    }

    public ArrayList<Integer> getNombreDeQuetesTotal() {
        return nombreDeQuetesTotal;
    }

    public ArrayList<Integer> getNombreDeCasesTotalParcourues() {
        return nombreDeCasesTotalParcourues;
    }

    public ArrayList<ArrayList<Integer>> getCheminDeQueteEffectueParLePersonnage() {
        return cheminDeQueteEffectueParLePersonnage;
    }

    public ArrayList<Integer> getExperienceTotaleChemin() {
        return experienceTotaleChemin;
    }

    public ArrayList<Solution> getListeSolutions() {
        ArrayList<Solution> listeSolutions = new ArrayList<>();
        for (int i = 0; i < getDureeTotale().size(); i++) {
            Solution solution = new Solution(getCheminDeQueteEffectueParLePersonnage().get(i),
                    getDureeTotale().get(i),
                    getNombreDeQuetesTotal().get(i),
                    getNombreDeCasesTotalParcourues().get(i),
                    getExperienceTotaleChemin().get(i));
            listeSolutions.add(solution);
        }
        return listeSolutions;
    }
}
