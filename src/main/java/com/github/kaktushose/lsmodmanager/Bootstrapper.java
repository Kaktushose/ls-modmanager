package com.github.kaktushose.lsmodmanager;

import com.github.kaktushose.lsmodmanager.ui.App;
import com.github.kaktushose.lsmodmanager.utils.Constants;
import javafx.application.Application;

public class Bootstrapper {

    public static void main(String[] args) {
        System.setProperty("lsmm.log", Constants.LOGGING_PATH);
        Application.launch(App.class);
    }

}
