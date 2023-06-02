package vue;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

public class HBoxRoot extends HBox {

    VBoxParametres vBoxParametres = new VBoxParametres();
    VBoxResultat vBoxResultat = new VBoxResultat();

    /**
     * Constructeur de la racine de l'interface utilisateur.
     * Il crée et configure les conteneurs VBoxParametres et VBoxResultat,
     * et les ajoute à la HBox principale.
     */
    public HBoxRoot() {
        // Configuration des marges intérieures de la HBox principale
        setPadding(new Insets(10));

        // Configuration de l'ID du VBoxParametres pour les paramètres de style
        vBoxParametres.setId("parametres");

        // Ajout des VBoxParametres et VBoxResultat à la HBox principale
        getChildren().addAll(vBoxParametres, vBoxResultat);
    }
}
