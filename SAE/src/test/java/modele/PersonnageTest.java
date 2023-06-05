package modele;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import LectureScenario.LectureFichierTexte;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonnageTest {

    private Personnage personnage;


    @Test
    public void testDeplacement() {
        personnage = new Personnage();
        int[] destination = {3, 4};// Définition d'une destination valide

        personnage.deplacement(destination);

        assertEquals(7, personnage.getTempsEcoule());
        assertArrayEquals(destination, personnage.getPosition());
    }

    @Test
    public void testRealisationQuete() {
        personnage = new Personnage();
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
        personnage = new Personnage();
        // Création d'un scénario avec une quête sans précondition
        Quete queteSansPrecondition = new Quete("1|(4, 3)|()|2|100|explorer pic de Bhanborim");
        Scenario scenario = new Scenario();
        scenario.ajout(queteSansPrecondition);

        // Appel de la méthode queteRealisable()
        ArrayList<Quete> quetesRealisables = personnage.queteRealisable(scenario);

        // Vérification que la quête sans précondition est dans la liste des quêtes réalisables
        assertTrue(quetesRealisables.contains(queteSansPrecondition));
    }

    @Test
    public void testQueteRealisableAvecPrecondition() {
        personnage = new Personnage();
        // Création d'un scénario avec une quête avec préconditions
        Quete queteAFaire = new Quete("1|(4, 3)|()|2|100|explorer pic de Bhanborim");
        Quete queteAvecPrecondition = new Quete("2|(3, 1)|((1,),)|1|150|dialoguer avec Kaela la chaman des esprits");
        Scenario scenario = new Scenario();
        scenario.ajout(queteAvecPrecondition);
        personnage.realisationQuete(queteAFaire);

        // Appel de la méthode queteRealisable()
        ArrayList<Quete> quetesRealisables = personnage.queteRealisable(scenario);

        // Vérification que la quête avec préconditions est dans la liste des quêtes réalisables
        assertTrue(quetesRealisables.contains(queteAvecPrecondition));
    }

    @Test
    public void testQueteRealisableNonRealisable() {
        personnage = new Personnage();
        // Création d'un scénario avec une quête non réalisable
        Quete queteNonRealisable = new Quete("0|(1,1)|((3,4),)|4|350|vaincre Araignée lunaire");
        Scenario scenario = new Scenario();
        scenario.ajout(queteNonRealisable);

        // Appel de la méthode queteRealisable()
        ArrayList<Quete> quetesRealisables = personnage.queteRealisable(scenario);

        // Vérification que la quête non réalisable n'est pas dans la liste des quêtes réalisables
        assertFalse(quetesRealisables.contains(queteNonRealisable));
    }

    @Test
    public void testQueteRealisableDejaFaite() {
        personnage = new Personnage();
        // Création d'un scénario avec une quête déjà réalisée
        Quete queteDejaFaite = new Quete("1|(4, 3)|()|2|100|explorer pic de Bhanborim");
        Scenario scenario = new Scenario();
        scenario.ajout(queteDejaFaite);
        personnage.realisationQuete(queteDejaFaite);

        // Appel de la méthode queteRealisable()
        ArrayList<Quete> quetesRealisables = personnage.queteRealisable(scenario);

        // Vérification que la quête n'est pas réalisable car elle à déja été faite
        assertFalse(quetesRealisables.contains(queteDejaFaite));
    }

    //Tests de la méthode queteFinaleRealisable
    @Test
    public void testQueteFinaleRealisable() {
        personnage = new Personnage();
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
        personnage = new Personnage();
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
        personnage = new Personnage();
        //Réalisation des quetes requise pour la quete finale
        personnage.realisationQuete(new Quete("2|(3, 1)|((1,),)|1|150|dialoguer avec Kaela la chaman des esprits"));
        personnage.realisationQuete(new Quete("4|(3, 2)|((2,),)|6|100|vaincre Loup Géant"));
        //Ajout de la quete finale au scenario
        Quete queteFinale = new Quete("0|(1,1)|((3,4),)|4|350|vaincre Araignée lunaire");
        Scenario scenario = new Scenario();
        scenario.ajout(queteFinale);
        assertFalse(personnage.queteFinaleRealisable(scenario));
    }

    // Tests de la méthode queteLaPlusProche
    @Test
    public void testQueteLaPlusProcheUneSeuleQuete(){
        personnage = new Personnage();
        Quete quete1 = new Quete("1|(4, 3)|()|2|100|explorer pic de Bhanborim");
        Quete quete2 = new Quete("2|(3, 1)|((1,),)|1|150|dialoguer avec Kaela la chaman des esprits");
        Scenario scenario = new Scenario();
        scenario.ajout(quete1);
        scenario.ajout(quete2);

        ArrayList<Quete> listeQueteLaPlusProche = personnage.queteLaPlusProche(personnage.queteRealisable(scenario));
        assertEquals(listeQueteLaPlusProche.size(), 1);
        assertEquals(listeQueteLaPlusProche.get(0).toString(), scenario.getListeQuetes().get(0).toString());
    }

    @Test
    public void testQueteLaPlusProchePlusieursQuetes(){
        personnage = new Personnage();
        Quete quete2 = new Quete("2|(3, 1)|()|1|150|dialoguer avec Kaela la chaman des esprits");
        Quete quete3 = new Quete("3|(2, 0)|((2,),)|3|200|explorer palais de Ahehona");
        Quete quete4 = new Quete("4|(4, 2)|((2,),)|6|100|vaincre Loup Géant");
        Scenario scenario = new Scenario();
        scenario.ajout(quete2);
        scenario.ajout(quete3);
        scenario.ajout(quete4);
        personnage.realisationQuete(quete2);
        ArrayList<Quete> listeQueteLaPlusProche = personnage.queteLaPlusProche(personnage.queteRealisable(scenario));
        assertEquals(listeQueteLaPlusProche.size(), 2);
    }

    // Tests de la méthode annulationQuete
    @Test
    public void testAnnulationQueteUneSeuleQuete(){
        personnage = new Personnage();
        Scenario scenario = new Scenario();
        scenario = LectureFichierTexte.lecture(new File("scenario" + File.separator + "scenario_0.txt"));
        ArrayList<Quete> listeQuete = scenario.getListeQuetes();
        personnage.realisationQuete(listeQuete.get(1));
        personnage.annulationQuete(listeQuete);
        assertEquals(personnage.getQuetesTerminees().size(), 0);
        assertEquals(personnage.getExperience(), 0);
        assertEquals(personnage.getTempsEcoule(), 0);
        assertEquals(Arrays.toString(personnage.getPosition()),Arrays.toString(new int [2]));
    }

    @Test
    public void testAnnulationQuetePlusieursQuete() {
        personnage = new Personnage();
        Scenario scenario = new Scenario();
        scenario = LectureFichierTexte.lecture(new File("scenario" + File.separator + "scenario_0.txt"));
        ArrayList<Quete> listeQuete = scenario.getListeQuetes();
        personnage.realisationQuete(listeQuete.get(1));
        personnage.realisationQuete(listeQuete.get(2));
        personnage.annulationQuete(listeQuete);
        assertEquals(personnage.getQuetesTerminees().size(), 1);
        assertEquals(personnage.getExperience(), 100);
        assertEquals(personnage.getTempsEcoule(), 9);
        int [] position = {4, 3};
        assertEquals(Arrays.toString(personnage.getPosition()),Arrays.toString(position));
    }
}
