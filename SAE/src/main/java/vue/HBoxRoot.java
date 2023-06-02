package vue;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

public class HBoxRoot extends HBox {

    VBoxParametres vBoxParametres = new VBoxParametres();
    VBoxResultat vBoxResultat = new VBoxResultat();;

    public HBoxRoot() {
        setPadding(new Insets(10));

        vBoxParametres.setId("parametres");

        getChildren().addAll(vBoxParametres, vBoxResultat);
    }
}
