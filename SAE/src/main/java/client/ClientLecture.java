package client;

import LectureScenario.LectureFichierTexte;
import modele.Quete;
import modele.Scenario;

import java.io.File;
import java.util.ArrayList;

public class ClientLecture {
    public static void main(String [] args) {
        Scenario scenario = new Scenario();
        scenario = LectureFichierTexte.lecture(new File("scenario" + File.separator + "scenario_0.txt"));
        ArrayList<Quete> listeQuetes = scenario.getListeQuetes();
        System.out.println(listeQuetes);
    }
}
