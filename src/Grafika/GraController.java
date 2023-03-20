package Grafika;

import Kodzik.Akcje.Action;
import Kodzik.Lokacje.Forest;
import Kodzik.Lokacje.Location;
import Kodzik.Lokacje.Plain;
import Kodzik.Lokacje.Water;
import Kodzik.Postacie.Gracz.Archer;
import Kodzik.Postacie.Gracz.Mage;
import Kodzik.Postacie.Gracz.Warrior;
import Kodzik.Postacie.Monster;
import Kodzik.Postacie.Player;
import Kodzik.Postacie.Potwor.Boss;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

public class GraController {
    static public int championNumber;
    static public String name;
    static public int level = 1;
    public VBox buttonsAction;
    public VBox buttonsLocation;
    public ChoiceBox chooseLocation;
    public TextArea locationText;
    public ImageView monster;
    public ImageView background;
    public ImageView champion;
    public Text championName;
    public Text championLevel;
    public Text championHealth;
    public Text championEnergy;
    public Text championAtack;
    public Text championDefense;
    public Button weakbutton;
    public Button strongbutton;
    public Button blockbutton;
    public Button restbutton;
    public Button retreatbutton;
    public AnchorPane anchorPane;

    private Location location;
    private List<Location> locations = new LinkedList<>();
    private Image image;
    private Player player;
    private int dmgMonster = 0;
    private int dmgPlayer = 0;

    public void initialize() {
        System.setOut(new PrintStream(new OutputStream(){
            @Override
            public void write(int b) throws IOException {
                locationText.appendText(String.valueOf((char) b));
            }
        }));
        
        if (championNumber == 0){
            player = new Archer(name,level);
            Image image = new Image("/modele/archer.png");
            champion.setImage(image);
        } else if (championNumber == 1){
            player = new Mage(name, level);
            Image image = new Image("/modele/mage.png");
            champion.setImage(image);
        } else if (championNumber == 2){
            player = new Warrior(name, level);
            Image image = new Image("/modele/warrior.png");
            champion.setImage(image);
        }

        weakbutton.setText(player.actions[0].getName());
        strongbutton.setText(player.actions[1].getName());
        blockbutton.setText(player.actions[2].getName());
        restbutton.setText(player.actions[3].getName());
        retreatbutton.setText("Wycofaj sie");
        refresh();

        /*new location template
        locations.add(new Water("", 1, new String[]{"","","",""," "},new String[][]{
                {"","",""},
                {"","",""},
                {"","",""},
                {"","",""},
                {"","",""}
        }));
*/

        //Nazwa pliku z gifem potwora musi być o tej samej nazwie co imie potwora
        locations.add(new Water("Kanaly", 1, new String[]{"Pajak","Szczur","Nietoperz","Bandyta","Potwor blotny"},new String[][]{
                {"strzal pajeczyna","dziabniecie","unikniecie"},
                {"smagniecie ogonem","atak pazurami","odskok"},
                {"wyssanie krwi","szarza","szybki zwrot"},
                {"kopniak","strzal z bani","samoobrona"},
                {"blotny cios","blotny sierpowy","blotna bariera"}
        }));

        locations.add(new Forest("Knieja", 5, new String[]{"Dzik","Wilk","Niedzwiedz","Goryl","Lesny Elf"},new String[][]{
                {"taranowanie","szarza z klami","schowanie sie"},
                {"atak lapą","ostre zebiska","szybki zwrot"},
                {"atak pazurami","mocne ugryzienie","zasloniecie"},
                {"rzut kokosem","potezna piacha","zaslona z belki"},
                {"rzut ostrzem","strzal z luku","kamuflaz"}
        }));

        locations.add(new Plain("Pustynia", 10, new String[]{"Wonsz","Skorpion","Kobra","Lew","Krokodyl"},new String[][]{
                {"atak ogonem","proba zaduszenia","zwiniecie sie"},
                {"atak szczypcami","atak zadłem","blok szczypcami"},
                {"atak klami","atak jadem","przewidzenie"},
                {"atak pazurami","atak zebiskami","rozproszenie uwagi"},
                {"atak cielskiem","atak paszczą","odwrocenie sie"}
        }));


        List<String> names = new LinkedList<>();
        for (Location loc:locations) {
            names.add(loc.toString());
        }
        chooseLocation.setItems(FXCollections.observableList(names));
        chooseLocation.setValue(names.get(0));
    }

    public void goTo(ActionEvent actionEvent) {
        locationText.setText("");
        buttonsAction.setVisible(true);
        buttonsLocation.setVisible(false);
        location = locations.get(chooseLocation.getSelectionModel().getSelectedIndex());
        location.enter(player);
        setLocationImage();
        location.encounter();
        setMonsterImage();
        beforeAction();
    }

    public void weak(ActionEvent actionEvent) {
        Action action = player.actions[0];
        dmgPlayer = action.execute(player);
        if (dmgPlayer < 0){
            System.out.println("Masz za malo energi");
        } else {
            System.out.println(name + " uzywa " + action.getName());
            afterAction();
        }
    }

    public void strong(ActionEvent actionEvent) {
        Action action = player.actions[1];
        dmgPlayer = action.execute(player);
        if (dmgPlayer < 0){
            System.out.println("Masz za malo energi");
        } else {
            System.out.println(name + " uzywa " + action.getName());
            afterAction();
        }
    }

    public void block(ActionEvent actionEvent) {
        Action action = player.actions[2];
        dmgPlayer = action.execute(player);
        if (dmgPlayer < 0){
            System.out.println("Masz za malo energi");
        } else {
            System.out.println(name + " uzywa " + action.getName());
            afterAction();
            this.refresh();
        }
    }

    public void rest(ActionEvent actionEvent) {
        Action action = player.actions[3];
        dmgPlayer = action.execute(player);
        System.out.println(name + " uzywa " + action.getName());
        afterAction();
    }

    public void Retreat() {
        locationText.setText("Wracasz do bazy");
        location.reset();
        player.reset();
        refresh();
        buttonsAction.setVisible(false);
        buttonsLocation.setVisible(true);
    }

    private void beforeAction() {
        dmgMonster = location.enemyRound();
        System.out.println("Jaka jest twoja reakcja?");
    }

    private void afterAction() {
        Monster tempMonster = location.monsters.get(location.counter);
        if (dmgPlayer > 0) {
            tempMonster.takeDamage((int)(dmgPlayer * player.getAttmod()));
        }
        if (dmgMonster > 0) {
            player.takeDamage(dmgMonster);
        }

        if(tempMonster.isDead()) {
            player.setExp(tempMonster.getExp() + player.getExp());
            location.counter += 1;
            locationText.setText("");
            System.out.println(tempMonster.getName() + " umiera.");
            player.levelUp();
            if (tempMonster.getClass() == Boss.class){
                Retreat();
                return;
            } else {
                location.encounter();
                setMonsterImage();
            }
        }
        if(player.isDead()) {
            locationText.setText("");
            System.out.println("Straciles cale zycie.");
            Retreat();
            player.setExp(player.getExp()/2);
        }
        refresh();
        beforeAction();
    }
    
    private void refresh() {
        championName.setText(player.getName());
        championLevel.setText(String.valueOf(player.getLevel()));
        championHealth.setText(player.getHP() + "/" + player.getMaxHP());
        if (player.getClass() == Mage.class) {
            championHealth.setText(player.getHP() + "/" + player.getMaxHP() + "(+" + player.getShield() + ")");
        }
        championEnergy.setText(String.valueOf(player.getEnergy()));
        championAtack.setText(String.valueOf(player.getAtt()));
        championDefense.setText(String.valueOf(player.getDef()));
    }

    private void setLocationImage(){
        String loc = location.getName();

        if (loc.equals("Kanaly")){
            image = new Image("/lokacje/sewere.jpg");
            background.setImage(image);
        }
        else if (loc.equals("Knieja")){
            image = new Image("/lokacje/oldforest.jpg");
            background.setImage(image);
        }
        if (loc.equals("Pustynia")){
            image = new Image("/lokacje/desert.jpg");
            background.setImage(image);
        }
    }

    private void setMonsterImage(){
        Monster temp = location.monsters.get(location.counter);
        image = new Image("/potwory/"+ temp.getName() +".gif");
        monster.setImage(image);
    }

    public void saveChampion() {
        try(PlayerDao dao = new PlayerDao()) {
            dao.write(player);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goBack(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/fxml/menu.fxml"));
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
