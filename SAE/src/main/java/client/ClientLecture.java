package client;

import LectureScenario.LectureFichierTexte;
import modele.Scenario;

import java.io.File;

public class ClientLecture {
    public static void main(String [] args) {
        Scenario scenario = new Scenario();
        scenario = LectureFichierTexte.lecture(new File("scenario" + File.separator + "scenario_0.txt"));
        System.out.println(scenario);
    }
}
