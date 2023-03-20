package Grafika;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MenuController {

    @FXML
    public ChoiceBox chooseCharacter;
    public ChoiceBox chooseLoad;
    public TextField characterName;
    public AnchorPane backgroud;
    public ImageView champion;

    List<String> postacie = new ArrayList<>();
    List<Integer> IDs = new LinkedList<>();
    List<String> savedchar = new LinkedList<>();
    public void initialize() {
        setCharacter();
        setLoad();
    }

    public void action(ActionEvent actionEvent) {
        GraController.name = characterName.getText();
        GraController.championNumber = chooseCharacter.getSelectionModel().getSelectedIndex();
        nextWindow();
    }

    public void load(ActionEvent actionEvent) {
        int index = chooseLoad.getSelectionModel().getSelectedIndex();
        int id = IDs.get(index);
        String[] temp = new String[0];
        try(PlayerDao dao = new PlayerDao()) {
            temp = dao.read(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        GraController.championNumber = chooseCharacter.getSelectionModel().getSelectedIndex();
        GraController.name = temp[1];
        GraController.level = Integer.parseInt(temp[2]);
        nextWindow();
    }

    public void setLoad() {
        List<String[]> temp = null;
        try(PlayerDao dao = new PlayerDao()) {
            temp = dao.readAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (temp == null) {
            return;
        }
        for (String[] row:temp) {
            IDs.add(Integer.valueOf(row[0]));
            savedchar.add(row[1]+": "+ row[2] + " lvl: "+ row[3]);
        }
        chooseLoad.setItems(FXCollections.observableList(savedchar));
        chooseLoad.setValue(savedchar.get(0));
    }

    public void setCharacter() {
        postacie.add("Archer");
        postacie.add("Mage");
        postacie.add("Warrior");
        chooseCharacter.setItems(FXCollections.observableList(postacie));
        chooseCharacter.setValue("Archer");
    }

    public void change(){
        if (chooseCharacter.getValue()=="Archer"){
            Image image = new Image("/modele/archer.png");
            champion.setImage(image);
        }
        if (chooseCharacter.getValue()=="Mage"){
            Image image = new Image("/modele/mage.png");
            champion.setImage(image);
        }
        if (chooseCharacter.getValue()=="Warrior"){
            Image image = new Image("/modele/warrior.png");
            champion.setImage(image);
        }
    }

    public void nextWindow() {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/fxml/gra.fxml"));
            Stage stage = (Stage) backgroud.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
