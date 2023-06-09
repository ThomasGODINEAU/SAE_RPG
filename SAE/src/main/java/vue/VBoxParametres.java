package vue;

import controleur.Controleur;
import LectureScenario.LectureFichierTexte;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modele.Niveau2;
import modele.Personnage;
import modele.Scenario;

import java.io.File;

public class VBoxParametres extends VBox {
    static Spinner<Integer> spinnerChoixScenario;
    static ToggleGroup groupeBoutonsTypeChemin;
    static ToggleGroup groupeBoutonsMeilleurOuPire;
    static ToggleGroup groupeChoixValeurParcours;
    static Spinner<Integer> spinnerChoixNombreCheminsVoulus;
    static Button boutonValider;


    /**
     * Constructeur de la boîte de paramètres.
     * Il crée et configure les éléments de l'interface utilisateur permettant de définir les paramètres de simulation.
     * Il gère également les actions des boutons et les interactions avec les autres éléments.
     */
    public VBoxParametres() {
        super(20);
        setPadding(new Insets(20));
        Controleur controleur = new Controleur();


        // Choix Du Scénario
        HBox choixScenario = new HBox();

        Label labelChoixScenario = new Label("Numéro du scénario : ");

        spinnerChoixScenario = new Spinner<>();
        int minS = 0;
        int maxS = 10;
        int valeurInitialeS = 0;
        SpinnerValueFactory<Integer> valueFactoryScenario = new SpinnerValueFactory.IntegerSpinnerValueFactory(minS, maxS, valeurInitialeS);
        spinnerChoixScenario.setMaxWidth(52);
        spinnerChoixScenario.setValueFactory(valueFactoryScenario);

        choixScenario.getChildren().addAll(labelChoixScenario, spinnerChoixScenario);
        choixScenario.setSpacing(10);


        Separator premierSeparateur = new Separator();

        // Choix Du Type de Chemin
        HBox choixTypeChemin = new HBox();

        Label labelChoixTypeChemin = new Label("Quel type de chemin souhaitez vous ? ");

        groupeBoutonsTypeChemin = new ToggleGroup();

        RadioButton boutonCheminEfficace = new RadioButton("E_fficace");
        boutonCheminEfficace.setMnemonicParsing(true);
        boutonCheminEfficace.setToggleGroup(groupeBoutonsTypeChemin);
        boutonCheminEfficace.setSelected(true);
        boutonCheminEfficace.setUserData("Efficace");

        RadioButton boutonCheminExhaustive = new RadioButton("E_xhaustive");
        boutonCheminExhaustive.setMnemonicParsing(true);
        boutonCheminExhaustive.setToggleGroup(groupeBoutonsTypeChemin);
        boutonCheminExhaustive.setUserData("Exhaustive");

        choixTypeChemin.getChildren().addAll(labelChoixTypeChemin, boutonCheminEfficace, boutonCheminExhaustive);
        choixTypeChemin.setSpacing(10);


        // Choix Meilleur ou Pire chemin
        HBox choixMeilleurOuPire = new HBox();

        Label labelChoixMeilleurOuPire = new Label("Les meilleures ou les pires chemins ? ");

        groupeBoutonsMeilleurOuPire = new ToggleGroup();

        RadioButton boutonMeilleur = new RadioButton("_Meilleures");
        boutonMeilleur.setMnemonicParsing(true);
        boutonMeilleur.setToggleGroup(groupeBoutonsMeilleurOuPire);
        boutonMeilleur.setSelected(true);
        boutonMeilleur.setUserData("Meilleure");

        RadioButton boutonPire = new RadioButton("_Pires");
        boutonPire.setMnemonicParsing(true);
        boutonPire.setToggleGroup(groupeBoutonsMeilleurOuPire);
        boutonPire.setUserData("Pire");

        choixMeilleurOuPire.getChildren().addAll(labelChoixMeilleurOuPire, boutonMeilleur, boutonPire);
        choixMeilleurOuPire.setSpacing(10);

        // Choix Valeur Parcours
        HBox choixValeurParcours = new HBox();

        Label labelChoixValeurParcours = new Label("Par quelle valeur souhaitez-vous vos chemins ? ");

        groupeChoixValeurParcours = new ToggleGroup();

        RadioButton boutonDuree = new RadioButton("_Duree");
        boutonDuree.setMnemonicParsing(true);
        boutonDuree.setToggleGroup(groupeChoixValeurParcours);
        boutonDuree.setSelected(true);
        boutonDuree.setUserData("Duree");

        RadioButton boutonNombreDeQuete = new RadioButton("Nombre de _quêtes");
        boutonNombreDeQuete.setMnemonicParsing(true);
        boutonNombreDeQuete.setToggleGroup(groupeChoixValeurParcours);
        boutonNombreDeQuete.setUserData("NombreDeQuete");

        RadioButton boutonExperience = new RadioButton("_Expérience");
        boutonExperience.setMnemonicParsing(true);
        boutonExperience.setToggleGroup(groupeChoixValeurParcours);
        boutonExperience.setUserData("Experience");

        RadioButton boutonNombreDeCasesParcourues = new RadioButton("Nombre de _cases parcourues");
        boutonNombreDeCasesParcourues.setMnemonicParsing(true);
        boutonNombreDeCasesParcourues.setToggleGroup(groupeChoixValeurParcours);
        boutonNombreDeCasesParcourues.setUserData("NombreDeCasesParcourues");

        choixValeurParcours.getChildren().addAll(labelChoixValeurParcours, boutonDuree, boutonNombreDeQuete, boutonExperience, boutonNombreDeCasesParcourues);
        choixValeurParcours.setSpacing(10);


        // Choix Nombre de Solutions Voulues
        HBox choixNombreCheminsVoulus = new HBox();

        Label labelChoixNombreCheminsVoulus = new Label("Combien de solutions souhaitez-vous ? ");

        spinnerChoixNombreCheminsVoulus = new Spinner<>();
        int minN = 1;
        int maxN = 100;
        int valeurInitialeN = 10;
        SpinnerValueFactory<Integer> valueFactoryNombre = new SpinnerValueFactory.IntegerSpinnerValueFactory(minN, maxN, valeurInitialeN);
        spinnerChoixNombreCheminsVoulus.setMaxWidth(60);
        spinnerChoixNombreCheminsVoulus.setValueFactory(valueFactoryNombre);

        choixNombreCheminsVoulus.getChildren().addAll(labelChoixNombreCheminsVoulus, spinnerChoixNombreCheminsVoulus);
        choixNombreCheminsVoulus.setSpacing(10);


        Separator deuxiemeSeparatoeur = new Separator();


        boutonValider = new Button("Valider");
        boutonValider.addEventHandler(ActionEvent.ACTION, controleur);

        boutonValider.setPrefSize(80, 40);
        setAlignment(Pos.CENTER_RIGHT);

        spinnerChoixScenario.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == 10) {
                boutonCheminExhaustive.setDisable(true);
                boutonPire.setDisable(true);
                boutonCheminEfficace.setSelected(true);
                boutonMeilleur.setSelected(true);
            }
            else {
                boutonCheminExhaustive.setDisable(false);
                boutonPire.setDisable(false);
            }
        });

        getChildren().addAll(choixScenario, premierSeparateur, choixTypeChemin, choixMeilleurOuPire,
                choixValeurParcours, choixNombreCheminsVoulus, deuxiemeSeparatoeur, boutonValider);
    }

    public static Spinner<Integer> getSpinnerChoixScenario() {
        return spinnerChoixScenario;
    }

    public static ToggleGroup getGroupeBoutonsTypeChemin() {
        return groupeBoutonsTypeChemin;
    }

    public static ToggleGroup getGroupeBoutonsMeilleurOuPire() {
        return groupeBoutonsMeilleurOuPire;
    }

    public static ToggleGroup getGroupeChoixValeurParcours() {
        return groupeChoixValeurParcours;
    }

    public static Spinner<Integer> getSpinnerChoixNombreCheminsVoulus() {
        return spinnerChoixNombreCheminsVoulus;
    }
}
