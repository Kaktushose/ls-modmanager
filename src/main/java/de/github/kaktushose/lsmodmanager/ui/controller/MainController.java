package de.github.kaktushose.lsmodmanager.ui.controller;

import de.github.kaktushose.lsmodmanager.core.App;
import de.github.kaktushose.lsmodmanager.core.SceneManager;
import de.github.kaktushose.lsmodmanager.ui.Dialogs;
import de.github.kaktushose.lsmodmanager.util.CloseEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends Controller {

    @FXML
    public ComboBox<String> modpackComboBox;
    public ListView<String> modpackListView;

    private final SceneManager sceneManager;

    public MainController(App app, Stage stage) {
        super(app, stage);
        sceneManager = app.getSceneManager();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @Override
    public void afterInitialization() {
    }

    @Override
    public void onCloseRequest() {
        onExit();
    }

    @FXML
    public void onSettings() {
        sceneManager.showSettings();
    }

    @FXML
    public void onExit() {
        if (Dialogs.displayCloseOptions("Beenden?", "Möchtest du den LS-ModManager wirklich beenden?")) {
            CloseEvent closeEvent = new CloseEvent("The user has closed the program", 0);
            closeEvent.perform();
        }
    }

    @FXML
    public void onModpackCreate() {
        sceneManager.showModpackCreate();
    }

    @FXML
    public void onModpackEdit() {
        sceneManager.showModpackEdit();
    }

    @FXML
    public void onModpackSelect() {
    }

    @FXML
    public void onModpackLoad() {

    }

    @FXML
    public void onAbout() {
        openURL("https://gadarol.de/board/index.php?thread/4102-ls19-modmanager-diy-java-projekt/");
    }

    @FXML
    public void onHelp() {
        openURL("https://github.com/Kaktushose/ls-modmanager/wiki");
    }

    private void openURL(String url) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                logger.error("Failed to open the url: " + url, e);
            }
        } else {
            logger.warn("Failed to open link. Desktop is not supported");
        }
    }

}
