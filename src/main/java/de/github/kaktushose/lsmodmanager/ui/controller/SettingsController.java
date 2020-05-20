package de.github.kaktushose.lsmodmanager.ui.controller;

import de.github.kaktushose.lsmodmanager.core.App;
import de.github.kaktushose.lsmodmanager.ui.Dialogs;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController extends Controller {

    @FXML
    public TextField textFieldLsPath, textFieldBackupPath;
    @FXML
    public CheckBox toggleBackup;

    private boolean unsaved;

    public SettingsController(App app, Stage stage) {
        super(app, stage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        unsaved = false;
        textFieldLsPath.setText(app.getLsPath());
        // TODO if actually implemented add backup settings
    }

    @Override
    public void afterInitialization() {

    }

    @Override
    public void onCloseRequest() {
        onClose();
    }

    @FXML
    public void onLsPath() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home") + "\\Documents\\My Games"));
        directoryChooser.setTitle("Pfad auswählen");
        File path = directoryChooser.showDialog(stage);
        if (path == null) return;
        textFieldLsPath.setText(path.getAbsolutePath());
        unsaved = !app.getLsPath().equals(path.getAbsolutePath());
    }

    @FXML
    public void onBackupPath() {
        Dialogs.displayInfoMessage("", "not implemented yet");
        toggleBackup.setSelected(false);
    }

    @FXML
    public void onAutoBackup() {
        Dialogs.displayInfoMessage("", "not implemented yet");
        toggleBackup.setSelected(false);

    }

    @FXML
    public void onBackupCreate() {
        Dialogs.displayInfoMessage("", "not implemented yet");
        toggleBackup.setSelected(false);
    }

    @FXML
    public void onSave() {
        save();
    }

    @FXML
    public void onClose() {
        if (unsaved) {
            switch (Dialogs.displaySaveOptions("Speichern?", "Einige Änderungen wurden noch nicht gespeichert.\r\nEinstellungen trotzdem verlassen?")) {
                case 0:
                    save();
                    stage.close();
                    return;
                case 1:
                    stage.close();
            }
        } else stage.close();
    }

    private void save() {
        app.setLsPath(textFieldLsPath.getText());
        // TODO if actually implemented add backup settings
        unsaved = false;
    }

}