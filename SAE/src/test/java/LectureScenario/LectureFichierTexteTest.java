package LectureScenario;

import modele.Scenario;
import org.junit.jupiter.api.Test;

import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class LectureFichierTexteTest {
    // Partition de test : fichier existant
    @Test
    public void testLectureFichierExistant() {
        File fichier = new File("scenario" + File.separator + "scenario_1.txt");
        Scenario scenario = LectureFichierTexte.lecture(fichier);
        assertEquals(6, scenario.getListeQuetes().size());
    }

    // Partition de test : fichier inexistant
    @Test
    public void testLectureFichierInexistant() {
        File fichier = new File("scenario" + File.separator + "scenario_inexistant.txt");
        Scenario scenario = LectureFichierTexte.lecture(fichier);
        assertEquals(0, scenario.getListeQuetes().size());
    }

    // Partition de test : fichier vide
    @Test
    public void testLectureFichierVide() {
        File fichier = new File("scenario" + File.separator + "scenario_vide.txt");
        Scenario scenario = LectureFichierTexte.lecture(fichier);
        assertEquals(0, scenario.getListeQuetes().size());
    }
}


