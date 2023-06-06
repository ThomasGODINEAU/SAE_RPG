package vue;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import modele.Solution;

import java.util.ArrayList;

public class VBoxResultat extends VBox {
    static TableView <Solution> tableauDesSolutions;

    public VBoxResultat() {
        super(30);
        tableauDesSolutions = new TableView<>();

        TableColumn<Solution, ArrayList<Integer>> colonneCheminParcouru = new TableColumn<>("Chemin Parcouru");
        colonneCheminParcouru.setCellValueFactory(new PropertyValueFactory<>("chemin"));
        colonneCheminParcouru.setPrefWidth(250);
        colonneCheminParcouru.setResizable(false);

        TableColumn<Solution, Integer> colonneDuree = new TableColumn<>("Duree");
        colonneDuree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        colonneDuree.setPrefWidth(60);
        colonneDuree.setResizable(false);

        TableColumn <Solution, Integer> colonneNombreDeQuete = new TableColumn<>("Nombre De Quetes");
        colonneNombreDeQuete.setCellValueFactory(new PropertyValueFactory<>("nombreDeQuete"));
        colonneNombreDeQuete.setPrefWidth(140);
        colonneNombreDeQuete.setResizable(false);

        TableColumn <Solution, Integer> colonneExperience = new TableColumn<>("Expérience");
        colonneExperience.setCellValueFactory(new PropertyValueFactory<>("experience"));
        colonneExperience.setPrefWidth(140);
        colonneExperience.setResizable(false);

        TableColumn <Solution, Integer> colonneNombreDeCasesParcourues = new TableColumn<>("Nombre De Cases Parcourues");
        colonneNombreDeCasesParcourues.setCellValueFactory(new PropertyValueFactory<>("nombreDeCasesParcourues"));
        colonneNombreDeCasesParcourues.setPrefWidth(180);
        colonneNombreDeCasesParcourues.setResizable(false);

        tableauDesSolutions.getColumns().addAll(colonneCheminParcouru, colonneDuree, colonneNombreDeQuete, colonneExperience, colonneNombreDeCasesParcourues);

        getChildren().add(tableauDesSolutions);

        this.update(null);
        }

        public static void update(ArrayList<Solution> listeSolutions) {
        tableauDesSolutions.getItems().clear();
        if(listeSolutions != null) {
            for (Solution solution : listeSolutions) {
                tableauDesSolutions.getItems().add(solution);
            }
        }
    }
}
