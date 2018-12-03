package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import service.Service;


import java.awt.*;

public class ControllerGUI {
    private Service service;

    //Students TAB
    @FXML
    private TableView tvStudentsS;
    @FXML
    private TextField tfIdS;
    @FXML
    private TextField tfNameS;
    @FXML
    private TextField tfGroupS;
    @FXML
    private TextField tfEmailS;
    @FXML
    private TextField tfTeacherS;
    @FXML
    private Button buAddS;
    @FXML
    private Button buUpdateS;
    @FXML
    private Button buRemoveS;
    @FXML
    private Button buClearS;



    public void initialize() {
        setStudentTab();
    }

    public void initService(Service service) {
        this.service = service;
    }

    private void setStudentTab() {

    }
}
