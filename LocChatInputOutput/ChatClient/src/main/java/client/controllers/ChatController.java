package client.controllers;

import client.NetworkClient;
import client.models.Network;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import readerandwriter.ClientReaderAndWriter;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class ChatController {

    @FXML
    public ListView<String> usersList;

    @FXML
    private Button sendButton;
    @FXML
    private TextArea chatHistory;
    @FXML
    private TextField textField;
    @FXML
    private Label usernameTitle;

    private Network network;
    private String selectedRecipient;

    ClientReaderAndWriter writer = new ClientReaderAndWriter("test1.txt");
    ClientReaderAndWriter reader = new ClientReaderAndWriter("test1.txt");




    public void setLabel(String usernameTitle) {
        this.usernameTitle.setText(usernameTitle);
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    @FXML
    public void initialize() {
//        usersList.setItems(FXCollections.observableArrayList(NetworkClient.USERS_TEST_DATA));
        sendButton.setOnAction(event -> ChatController.this.sendMessage());
        textField.setOnAction(event -> ChatController.this.sendMessage());


        usersList.setCellFactory(lv -> {
            MultipleSelectionModel<String> selectionModel = usersList.getSelectionModel();
            ListCell<String> cell = new ListCell<>();
            cell.textProperty().bind(cell.itemProperty());
            cell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                usersList.requestFocus();
                if (! cell.isEmpty()) {
                    int index = cell.getIndex();
                    if (selectionModel.getSelectedIndices().contains(index)) {
                        selectionModel.clearSelection(index);
                        selectedRecipient = null;
                    } else {
                        selectionModel.select(index);
                        selectedRecipient = cell.getItem();
                    }
                    event.consume();
                }
            });
            return cell ;
        });
        appendMessagesFromHistory();
    }

    private void sendMessage() {
        String message = textField.getText();

        if(message.isBlank()) {
            return;
        }

        appendMessage("Я: " + message);
        textField.clear();

        try {
            if (selectedRecipient != null) {
                network.sendPrivateMessage(message, selectedRecipient);
            }
            else {
                network.sendMessage(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
            NetworkClient.showErrorMessage("Ошибка подключения", "Ошибка при отправке сообщения", e.getMessage());
        }

    }

    private void write(String msg){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter( "writtenS.txt"))) {
            writer.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendMessage(String message) {

        String timestamp = DateFormat.getInstance().format(new Date());
        chatHistory.appendText(timestamp);
        writer.fileWrite("<s1>" + timestamp );
        chatHistory.appendText(System.lineSeparator());
        writer.fileWrite(System.lineSeparator());
        chatHistory.appendText(message);
        write(message);
        writer.fileWrite("<s2>" + message);
        chatHistory.appendText(System.lineSeparator());
        write(System.lineSeparator());
        writer.fileWrite(System.lineSeparator());
        chatHistory.appendText(System.lineSeparator());
        write(System.lineSeparator());
        writer.fileWrite(System.lineSeparator());

    }

    public void appendMessagesFromHistory() {

        for (int i = 0; i < 100; i++) {
            if (reader.readLastLine(i).contains("s2")){
                chatHistory.appendText(System.lineSeparator());
                chatHistory.appendText(reader.readLastLine(i).replaceAll("<s2>", ""));
            }else if (reader.readLastLine(i).contains("s1")) {
                chatHistory.appendText(System.lineSeparator());
                chatHistory.appendText(reader.readLastLine(i).replaceAll("<s1>", ""));
            } else{
                chatHistory.appendText(System.lineSeparator());
            }
        }
    }

    public void setUsernameTitle(String username) {

    }

    public void updateUsers(List<String> users) {
        usersList.setItems(FXCollections.observableArrayList(users));
    }

}