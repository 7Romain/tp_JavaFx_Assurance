import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import com.gluonhq.charm.glisten.control.Icon;
import com.gluonhq.charm.glisten.control.ToggleButtonGroup;

public class Controller implements Initializable {

    @FXML
    private ToggleGroup accident;

    @FXML
    private RadioButton ancienNon;

    @FXML
    private RadioButton rbtn0;

    @FXML
    private RadioButton rbtn1;

    @FXML
    private RadioButton rbtn2;

    @FXML
    private RadioButton rbtn3;

    @FXML
    private RadioButton rbtn4;

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
    private ToggleGroup resp;

    @FXML
    private Label resultat;

    @FXML
    private ToggleButton tglRespNo;

    @FXML
    private ToggleButton tglRespYes;

    @FXML
    private ToggleButtonGroup tgbrs;

    @FXML
    private Label lblres;

    @FXML
    private Label lblnbacc;

    @FXML
    private HBox rBtnPn;

    @FXML
    private Label lblAnc;

    @FXML
    private HBox rBtnBAnc;

    @FXML
    void mouseclic(MouseEvent event) {
        menuAnciennete();

    }

    private void menuAnciennete() {
        if (choiceBox.getValue() != null) {
            String permisAnc = choiceBox.getValue();
            if (permisAnc.equals("2 ans et plus")) {
                lblAnc.setVisible(true);
                rBtnBAnc.setVisible(true);
            }
            if (permisAnc.equals("moins de 2 ans")) {
                lblAnc.setVisible(false);
                rBtnBAnc.setVisible(false);
            }
        }

    }

    @FXML
    void montrerNbAcc(ActionEvent event) {
        rBtnPn.setVisible(true);
        lblnbacc.setVisible(true);

    }

    @FXML
    void cacherNbAcc(ActionEvent event) {
        rBtnPn.setVisible(false);
        lblnbacc.setVisible(false);

    }

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

        Image imageyes = new Image("yesImage.png");
        ImageView iv1 = new ImageView();
        iv1.setImage(imageyes);
        iv1.setFitWidth(27);
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);
        tglRespYes.setGraphic(iv1);

        Image imageno = new Image("nonImage.png");
        ImageView iv2 = new ImageView();
        iv2.setImage(imageno);
        iv2.setFitWidth(27);
        iv2.setPreserveRatio(true);
        iv2.setSmooth(true);
        iv2.setCache(true);
        tglRespNo.setGraphic(iv2);

        rbtn0.setVisible(false);
        rBtnPn.setVisible(false);
        lblnbacc.setVisible(false);
        lblAnc.setVisible(false);
        rBtnBAnc.setVisible(false);

        // choiceBox.getSelectionModel().selectedItemProperty().addListener(e ->
        // menuAnciennete());

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

        if (tglRespNo.isSelected()) {
            return 0;
        }
        if (tglRespYes.isSelected()) {
            if (rbtn1.isSelected()) {
                return 1;
            }
            if (rbtn2.isSelected()) {
                return 2;
            }
            if (rbtn3.isSelected()) {
                return 3;
            }
        }
        return 4;

    }

}
