package de.github.kaktushose.lsmodmanager.ui.controller;

import de.github.kaktushose.lsmodmanager.core.App;
import de.github.kaktushose.lsmodmanager.ui.model.FileModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.*;

public class FileChooserController extends Controller {

    private final ObservableList<FileModel> fileModelObservableList;
    @FXML
    public TableView<FileModel> tableView;
    @FXML
    public TableColumn<FileModel, String> nameColumn;
    @FXML
    public TableColumn<FileModel, Button> buttonColumn;
    private Set<File> fileCache;
    private List<File> selectedFiles;

    public FileChooserController(App app, Stage stage) {
        super(app, stage);
        this.selectedFiles = new ArrayList<>();
        fileModelObservableList = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        buttonColumn.setCellValueFactory(new PropertyValueFactory<>("button"));
        buttonColumn.setEditable(true);
        fileCache = new HashSet<>();
    }

    @Override
    public void afterInitialization() {

    }

    @Override
    public void onCloseRequest() {
        onClose();
    }

    public void setFiles(Collection<File> selectedFiles) {
        this.selectedFiles = new ArrayList<>(selectedFiles);
        this.selectedFiles.forEach(this::addItemToTableView);
        fileCache.addAll(selectedFiles);
    }

    @FXML
    public void onAddFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Mods auswählen");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Mods", "*.zip"));

        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(stage);
        if (selectedFiles != null) {
            selectedFiles.forEach(file -> {
                if (fileCache.add(file)) {
                    addItemToTableView(file);
                }
            });
        }
    }

    @FXML
    public void onClose() {
        save();
        stage.close();
    }

    public List<File> getSelectedFiles() {
        Collections.sort(selectedFiles);
        return selectedFiles;
    }

    private void save() {
        selectedFiles.clear();
        selectedFiles.addAll(fileCache);
        fileModelObservableList.forEach(fileModel -> {
            if (fileModel.isDelete()) {
                selectedFiles.remove(fileModel.getFile());
            }
        });
    }

    private void addItemToTableView(File file) {
        fileModelObservableList.add(new FileModel(file));
        tableView.setItems(fileModelObservableList);
    }

}
