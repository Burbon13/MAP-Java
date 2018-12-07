package view;

import domain.MessageTask;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Box;
import validation.ValidationException;

import java.awt.event.ActionEvent;

public class View {

    private TableView<MessageTask> tableView;
    private TextField idText;
    private TextField descriptionText;
    private TextField messageText;
    private Controller controller;

    //StackPane
    //GreedPane
    //Box
    public View(Controller controller){
        tableView = new TableView<>();
        this.controller = controller;
    }

    public BorderPane getView(){
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(createTable());
        borderPane.setRight(createTask());
        return borderPane;
    }

    private void initTableView(){
        tableView.setItems(controller.getList());
        TableColumn<MessageTask, String> idColumn = new TableColumn<>("Id");
        TableColumn<MessageTask, String> descriptionColumn = new TableColumn<>("Description");
        TableColumn<MessageTask, String> messageColumn = new TableColumn<>("Message");
        tableView.getColumns().addAll(idColumn, descriptionColumn, messageColumn);
        idColumn.setCellValueFactory(new PropertyValueFactory<MessageTask, String>("taskID"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<MessageTask, String>("descriere"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<MessageTask, String>("mesaj"));
        tableView.getSelectionModel().selectedItemProperty().addListener((observer, oldData, newData)-> showDetails(newData));
    }

    private void showDetails(MessageTask msg){
        if(msg!=null){
            idText.setText(msg.getId());
            descriptionText.setText(msg.getDescriere());
            messageText.setText(msg.getMesaj());
        }
    }

    private StackPane createTable(){
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(tableView);
        initTableView();
        return stackPane;
    }

    private GridPane createTask(){
        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Id"), 0, 0);
        gridPane.add(idText = new TextField(), 1, 0);
        gridPane.add(new Label("Description"), 0, 1);
        gridPane.add(descriptionText = new TextField(), 1, 1);
        gridPane.add(new Label("Message"), 0, 2);
        gridPane.add(messageText = new TextField(), 1, 2);

        HBox buttonsBox = new HBox();
        Button add = new Button("Add");
        add.setOnAction(event -> {
            this.addHandler();
        });
        buttonsBox.getChildren().add(add);
        Button clearAll = new Button("Clear All");
        clearAll.setOnAction(event -> {
            this.clearAllHandler();
        });
        buttonsBox.getChildren().add(clearAll);

        Button update = new Button("Update");
        update.setOnAction(event -> {
            this.updateHandler();
        });
        buttonsBox.getChildren().add(update);
        Button delete = new Button("Delete");
        delete.setOnAction(event -> {
            this.deleteHandler();
        });
        buttonsBox.getChildren().add(delete);
        gridPane.add(buttonsBox, 0, 4,2, 1);
        return gridPane;
    }

    private void addHandler(){
        try {
            controller.addTask(idText.getText(), descriptionText.getText(), messageText.getText());
        } catch (ValidationException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Task error");
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }
    private Node deleteHandler(){return null;}
    private Node clearAllHandler(){return null;}
    private Node updateHandler(){return null;}



}
