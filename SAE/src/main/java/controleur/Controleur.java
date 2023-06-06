package controleur;

import LectureScenario.LectureFichierTexte;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import modele.Niveau2;
import modele.Personnage;
import modele.Scenario;
import vue.HBoxRoot;
import vue.VBoxParametres;
import vue.VBoxResultat;

import java.io.File;

public class Controleur implements EventHandler<Event> {

    @Override
    public void handle(Event event) {
        VBoxParametres vBoxParametres = HBoxRoot.getvBoxParametres();
        if (event.getSource() instanceof Button) {
                Niveau2 niveau2 = new Niveau2();
                Scenario scenario;
                scenario = LectureFichierTexte.lecture(new File("scenario" + File.separator + "scenario_" +
                        vBoxParametres.getSpinnerChoixScenario().getValue() + ".txt"));

                if (vBoxParametres.getGroupeBoutonsTypeChemin().getSelectedToggle().getUserData() == "Efficace") {
                    niveau2.getCheminEfficace(new Personnage(), scenario, scenario.getListeQuetes(),
                            vBoxParametres.getSpinnerChoixNombreCheminsVoulus().getValue(),
                            vBoxParametres.getGroupeChoixValeurParcours().getSelectedToggle().getUserData().toString(),
                            vBoxParametres.getGroupeBoutonsMeilleurOuPire().getSelectedToggle().getUserData().toString());
                } else {
                    niveau2.getCheminExhaustif(new Personnage(), scenario, scenario.getListeQuetes(),
                            vBoxParametres.getSpinnerChoixNombreCheminsVoulus().getValue(),
                            vBoxParametres.getGroupeChoixValeurParcours().getSelectedToggle().getUserData().toString(),
                            vBoxParametres.getGroupeBoutonsMeilleurOuPire().getSelectedToggle().getUserData().toString());
                }
                VBoxResultat.update(niveau2.getListeSolutions());
            }
        }
}
