import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Controller implements Initializable {

    @FXML
    private ToggleGroup accident;

    @FXML
    private RadioButton ancienNon;

    @FXML
    private RadioButton ancienOui;

    @FXML
    private Pane anciennetePan;

    @FXML
    private CheckBox ans;

    @FXML
    private Button btnCalculer;

    @FXML
    private ChoiceBox<String> choiceBox;
    private String[] permis = { "moins de 2 ans", "2 ans et plus" };

    @FXML
    private TextField nbAccident;

    @FXML
    private Label resultat;

    @FXML
    void calc(ActionEvent event) {
        resultat.setVisible(false);
        boolean plus25 = get25ansBox();
        boolean pluspermis = getPermis(event);
        boolean ancienneteCal = getAnciennete();
        int nbAccidentCal = getNbAccident();
        Devis calculs = new Devis();
        int resultatDevis = calculs.faireDevis(plus25, pluspermis, ancienneteCal, nbAccidentCal);

        switch (resultatDevis) {

            case 1:
                resultat.setText("Vous bénéficiez du tarif Rouge.");
                resultat.setTextFill(Color.web("#CF0013"));
                resultat.setVisible(true);
                break;

            case 2:
                resultat.setText("Vous bénéficiez du tarif Orange.");
                resultat.setTextFill(Color.web("#D98306"));
                resultat.setVisible(true);
                break;

            case 3:
                resultat.setText("Vous bénéficiez du tarif Vert.");
                resultat.setTextFill(Color.web("#3ABB06"));
                resultat.setVisible(true);
                break;

            case 4:
                resultat.setText("Vous bénéficiez du tarif Bleu.");
                resultat.setTextFill(Color.web("#062EB8"));
                resultat.setVisible(true);
                break;

            default:
                resultat.setText("Désolé nous ne pouvons pas vous assurer.");
                resultat.setTextFill(Color.web("#000001"));
                resultat.setVisible(true);
                break;

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.getItems().addAll(permis);
        choiceBox.setOnAction(this::getPermis);

    }

    public boolean getPermis(ActionEvent e) {
        String sonPermis = choiceBox.getValue();
        if (sonPermis.equals("2 ans et plus")) {
            return true;
        }
        return false;

    }

    public boolean get25ansBox() {
        if (ans.isSelected()) {
            return true;
        }
        return false;

    }

    public boolean getAnciennete() {
        if (ancienOui.isSelected()) {
            return true;
        }
        if (ancienNon.isSelected()) {
            return false;
        }
        return false;

    }

    public int getNbAccident() {
        return Integer.valueOf(nbAccident.getText());

    }

}
