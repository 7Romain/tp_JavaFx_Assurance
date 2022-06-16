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
        boolean plus25 = getAnciennete();
        boolean pluspermis = getPermis(event);
        boolean ancienneteCal = getAnciennete();
        int nbAccidentCal = getNbAccident();
        Devis calculs = new Devis();
        int resultatDevis = calculs.faireDevis(plus25, pluspermis, ancienneteCal, nbAccidentCal);

        switch (resultatDevis) {

            case 1:
                resultat.setText("Vous bénéficiez du tarif Rouge.");
                break;

            case 2:
                resultat.setText("Vous bénéficiez du tarif Orange.");
                break;

            case 3:
                resultat.setText("Vous bénéficiez du tarif Vert.");
                break;

            case 4:
                resultat.setText("Vous bénéficiez du tarif Bleu.");
                break;

            default:
                resultat.setText("Désolé nous ne pouvons pas vous assurer.");
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
        return (sonPermis.equals("2 ans et plus"));

    }

    public boolean get25ansBox() {
        return (ans.isSelected());

    }

    public boolean getAnciennete() {
        if (ancienOui.isSelected())
            return true;
        return (ancienNon.isSelected());

    }

    public int getNbAccident() {
        return Integer.valueOf(nbAccident.getText());

    }

}
