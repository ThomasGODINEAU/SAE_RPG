package modele;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonnageTest {

    private Personnage personnage;


    @Test
    public void testDeplacement() {
        personnage = new Personnage(null);
        int[] destination = {3, 4};// Définition d'une destination valide

        personnage.deplacement(destination);

        assertEquals(7, personnage.getTempsEcoule());
        assertArrayEquals(destination, personnage.getPosition());
    }

    @Test
    public void testRealisationQuete() {
        personnage = new Personnage(null);
        // Création d'une quête valide pour le test
        Quete quete = new Quete("1|(4, 3)|()|2|100|explorer pic de Bhanborim");

        // Sauvegarde des quêtes terminées initiales
        Integer[] quetesTermineesInitial = personnage.getQuetesTerminees().toArray(new Integer[0]);

        // Appel de la méthode realisationQuete() avec la quête
        personnage.realisationQuete(quete);

        // Vérification des mises à jour attendues
        assertEquals(9 , personnage.getTempsEcoule());
        assertEquals(100, personnage.getExperience());

        Integer[] quetesTermineesAttendues = Arrays.copyOf(quetesTermineesInitial, quetesTermineesInitial.length + 1);
        quetesTermineesAttendues[quetesTermineesInitial.length] = quete.getNumero();
        int[] quetesTermineesAttenduesInt = Arrays.stream(quetesTermineesAttendues).mapToInt(Integer::intValue).toArray();
        assertArrayEquals(quetesTermineesAttenduesInt, Arrays.stream(personnage.getQuetesTerminees().toArray(new Integer[0])).mapToInt(Integer::intValue).toArray());
    }

    //Tests de la méthode queteRealisable
    @Test
    public void testQueteRealisableSansPrecondition() {
        personnage = new Personnage(null);
        // Création d'un scénario avec une quête sans précondition
        Quete queteSansPrecondition = new Quete("1|(4, 3)|()|2|100|explorer pic de Bhanborim");
        Scenario scenario = new Scenario();
        scenario.ajout(queteSansPrecondition);

        // Appel de la méthode queteRealisable()
        ArrayList<Integer> quetesRealisables = personnage.queteRealisable(scenario);

        // Vérification que la quête sans précondition est dans la liste des quêtes réalisables
        assertTrue(quetesRealisables.contains(queteSansPrecondition.getNumero()));
    }

    @Test
    public void testQueteRealisableAvecPrecondition() {
        personnage = new Personnage(null);
        // Création d'un scénario avec une quête avec préconditions
        Quete queteAFaire = new Quete("1|(4, 3)|()|2|100|explorer pic de Bhanborim");
        Quete queteAvecPrecondition = new Quete("2|(3, 1)|((1,),)|1|150|dialoguer avec Kaela la chaman des esprits");
        Scenario scenario = new Scenario();
        scenario.ajout(queteAvecPrecondition);
        personnage.realisationQuete(queteAFaire);

        // Appel de la méthode queteRealisable()
        ArrayList<Integer> quetesRealisables = personnage.queteRealisable(scenario);

        // Vérification que la quête avec préconditions est dans la liste des quêtes réalisables
        assertTrue(quetesRealisables.contains(queteAvecPrecondition.getNumero()));
    }

    @Test
    public void testQueteRealisableNonRealisable() {
        personnage = new Personnage(null);
        // Création d'un scénario avec une quête non réalisable
        Quete queteNonRealisable = new Quete("0|(1,1)|((3,4),)|4|350|vaincre Araignée lunaire");
        Scenario scenario = new Scenario();
        scenario.ajout(queteNonRealisable);

        // Appel de la méthode queteRealisable()
        ArrayList<Integer> quetesRealisables = personnage.queteRealisable(scenario);

        // Vérification que la quête non réalisable n'est pas dans la liste des quêtes réalisables
        assertFalse(quetesRealisables.contains(queteNonRealisable.getNumero()));
    }

    @Test
    public void testQueteRealisableDejaFaite() {
        personnage = new Personnage(null);
        // Création d'un scénario avec une quête déjà réalisée
        Quete queteDejaFaite = new Quete("1|(4, 3)|()|2|100|explorer pic de Bhanborim");
        Scenario scenario = new Scenario();
        scenario.ajout(queteDejaFaite);
        personnage.realisationQuete(queteDejaFaite);

        // Appel de la méthode queteRealisable()
        ArrayList<Integer> quetesRealisables = personnage.queteRealisable(scenario);

        // Vérification que la quête n'est pas réalisable car elle à déja été faite
        assertFalse(quetesRealisables.contains(queteDejaFaite.getNumero()));
    }

    //Tests de la méthode queteFinaleRealisable
    @Test
    public void testQueteFinaleRealisable() {
        personnage = new Personnage(null);
        //Réalisation des quetes requise pour la quete finale
        personnage.realisationQuete(new Quete("2|(3, 1)|((1,),)|1|150|dialoguer avec Kaela la chaman des esprits"));
        personnage.realisationQuete(new Quete("3|(0, 4)|((2,),)|3|200|explorer palais de Ahehona"));
        //Ajout de la quete finale au scenario
        Quete queteFinale = new Quete("0|(1,1)|((3,4),)|4|350|vaincre Araignée lunaire");
        Scenario scenario = new Scenario();
        scenario.ajout(queteFinale);
        assertTrue(personnage.queteFinaleRealisable(scenario));
    }

    @Test
    public void testQueteFinaleRealisablePreconditionNonRemplies() {
        personnage = new Personnage(null);
        //Réalisation des quetes requise pour la quete finale
        personnage.realisationQuete(new Quete("2|(3, 1)|((1,),)|1|150|dialoguer avec Kaela la chaman des esprits"));
        personnage.realisationQuete(new Quete("1|(4, 3)|()|2|100|explorer pic de Bhanborim"));
        //Ajout de la quete finale au scenario
        Quete queteFinale = new Quete("0|(1,1)|((3,4),)|4|350|vaincre Araignée lunaire");
        Scenario scenario = new Scenario();
        scenario.ajout(queteFinale);
        assertFalse(personnage.queteFinaleRealisable(scenario));
    }

    @Test
    public void testQueteFinaleRealisablePasAssezExperience() {
        personnage = new Personnage(null);
        //Réalisation des quetes requise pour la quete finale
        personnage.realisationQuete(new Quete("2|(3, 1)|((1,),)|1|150|dialoguer avec Kaela la chaman des esprits"));
        personnage.realisationQuete(new Quete("4|(3, 2)|((2,),)|6|100|vaincre Loup Géant"));
        //Ajout de la quete finale au scenario
        Quete queteFinale = new Quete("0|(1,1)|((3,4),)|4|350|vaincre Araignée lunaire");
        Scenario scenario = new Scenario();
        scenario.ajout(queteFinale);
        assertFalse(personnage.queteFinaleRealisable(scenario));
    }
}
