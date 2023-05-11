package simulation;

import modele.Quete;
import modele.Scenario;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.abs;

public class Personnage {

    private ArrayList<Integer> quetesTerminees; // Liste des quêtes terminées par le personnage

    private int experience; // Niveau d'expérience du personnage

    private int [] position;// Position actuelle du personnage (coordonnées x et y)

    private int tempsEcoule;    // Temps écoulé depuis le début de la simulation

    /**
     * Constructeur du constructeur de la classe personnage
     *
     * @param scenario (Scenario)
     */
    public Personnage(Scenario scenario) {
        // Initialisation de la liste des quêtes terminées vide
        quetesTerminees = new ArrayList<Integer>();
        // Initialisation de l'expérience à zéro
        experience = 0;
        // Initialisation de la position à (0, 0)
        position = new int[2];
        // Initialisation du temps écoulé à zéro
        tempsEcoule = 0;
    }


    public String toString(){
        return "Quetes terminees : "+ quetesTerminees.toString() + "\n" + "experience : " + experience + "\n" + "position : " + Arrays.toString(position) + "\n" + "temps ecoule : " + tempsEcoule;
    }

    /**
     * Méthode déplacement
     * Prend en parametre un tableau de 2 entiers pour les coordonnées de la destination
     * Ne se déplace pas case par case
     * Ajoute le nombre de case de distance entre notre case et la case de destination au temps écoulé
     *
     * @param destination (destination)
     */
    public void deplacement(int[] destination){
        // Calcul du temps nécessaire pour se déplacer de la position actuelle à la destination
        tempsEcoule += abs(position[0] - destination[0]);
        tempsEcoule += abs(position[1] - destination[1]);
        // Mise à jour de la position du personnage
        position = destination;
    }

    /**
     * Méthode realisationQuete
     * Effectue la réalisation d'une quête par le personnage, en mettant à jour ses quêtes terminées, son expérience et le temps écoulé
     *
     * @param quete : La quête à réaliser
     */
    public void realisationQuete(Quete quete){
        // Déplacement sur la case de la quête réalisée par le personnage
        deplacement(quete.getPosition());
        // Ajout du numéro de la quête à la liste des quêtes terminées par le personnage
        quetesTerminees.add(quete.getNumero());
        // Ajout de l'expérience obtenue avec cette quête à l'expérience du personnage
        experience += quete.getExperience();
        // Ajout de la durée de la quête au temps écoulé
        tempsEcoule += quete.getDuree();
    }

    /**
     * Méthode queteRealisable
     * Renvoie la liste des quêtes réalisables par le personnage, en fonction de ses quêtes déjà terminées et des préconditions des quêtes
     *
     * @param scenario (Scenario) Le scénario contenant les quêtes disponibles
     *
     * @return La liste des quêtes réalisables par le personnage
     */
    public ArrayList<Integer> queteRealisable(Scenario scenario) {
        ArrayList<Integer> listeQuetesRealisables = new ArrayList<>();
        // Parcours de toutes les quêtes du scénario
        for (int i=0; i < scenario.getListeQuetes().size(); i++){
            Quete queteEtudiee = scenario.getListeQuetes().get(i);
            // Vérification que la quête n'a pas déjà été terminée par le personnage
            if (! quetesTerminees.contains(queteEtudiee.getNumero())){
                // Vérification des préconditions de la quête pour déterminer si elle est réalisable ou non
                if (queteEtudiee.sansPrecondition()) listeQuetesRealisables.add(queteEtudiee.getNumero());
                else{
                    Boolean precondition1 = false;
                    Boolean precondition2 = false;
                    // Vérifie que la première paire de précondition n'est pas vide
                    if(queteEtudiee.getPrecondition()[0] == queteEtudiee.getPrecondition()[1]) precondition1 = true;
                    // Vérifie que la deuxième paire de précondition n'est pas vide
                    if(queteEtudiee.getPrecondition()[2] == queteEtudiee.getPrecondition()[3]) precondition2 = true;

                    // Teste pour chaque précondition si elle a déjà été efféctuée par le personnage
                    for (int j=0; j<2; j++){
                        if(quetesTerminees.contains(queteEtudiee.getPrecondition()[j])) precondition1 = true;
                    }
                    for (int j=2; j<4; j++){
                        if(quetesTerminees.contains(queteEtudiee.getPrecondition()[j])) precondition2 = true;
                    }
                    // Ajout de la quête à la liste des quêtes réalisables si toutes les préconditions sont satisfaites
                    if (precondition1 && precondition2) listeQuetesRealisables.add(queteEtudiee.getNumero());
                }
            }
        }
        return listeQuetesRealisables;
    }



    // Ci-dessous se trouvent les méthodes "get"

    public ArrayList<Integer> getQuetesTerminees() {
        return quetesTerminees;
    }

    public int getExperience() {
        return experience;
    }

    public int[] getPosition() {
        return position;
    }

    public int getTempsEcoule() {
        return tempsEcoule;
    }
}
