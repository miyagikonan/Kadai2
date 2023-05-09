package com.example.kadai2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController {
    //ユーザー追加
    @FXML
    private ComboBox<String> company1;
    @FXML
    private TextField name1;
    @FXML
    private TextField score1;
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, Integer> column1;
    @FXML
    private TableColumn<User, Integer> column2;
    @FXML
    private TableColumn<User, Integer> column3;
    @FXML
    private TableColumn<User, Integer> column4;

    ObservableList<User> users = FXCollections.observableArrayList();

    //ユーザー編集
    @FXML
    private ComboBox<String> company2;
    @FXML
    private TextField name2;
    @FXML
    private TextField score2;

    @FXML
    private Label errorMessage;


    @FXML
    private void initialize() {
        users.add(new User("AAA",50,"株式会社A",1));
        users.add(new User("BBB",70,"株式会社B",2));
        users.add(new User("CCC",30,"株式会社C",3));

        column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        column2.setCellValueFactory(new PropertyValueFactory<>("company"));
        column3.setCellValueFactory(new PropertyValueFactory<>("name"));
        column4.setCellValueFactory(new PropertyValueFactory<>("score"));

        table.setItems(users);
        System.out.println(table.getItems());

        ObservableList<String> items = FXCollections.observableArrayList("株式会社A", "株式会社B", "株式会社C", "株式会社D");
        company1.setItems(items);
        company2.setItems(items);


        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                company2.setValue(newValue.getCompany());
                name2.setText(newValue.getName());
                score2.setText(Integer.toString(newValue.getScore()));
            }
        });

    }

    //追加ボタン
    @FXML
    public void tuikaBotton(ActionEvent event) {
        String company = company1.getValue();
        String name = name1.getText();
        String scoreStr = score1.getText();

        // 入力値チェック
        if (company == null || company.isEmpty() || name.isEmpty() || scoreStr.isEmpty()) {
            setErrorMessage("入力項目が空白です");
            return;
        }
        int score;
        try {
            score = Integer.parseInt(scoreStr);
            if (score < 0 || score > 100) {
                setErrorMessage("スコアは0～100の範囲で入力");
                return;
            }
        } catch (NumberFormatException e) {
            setErrorMessage("スコアには数値を入力");
            return;
        }

        User newUser = new User(name, score, company, users.size()+1);
        users.add(newUser);
        table.setItems(users);
        name1.clear();
        score1.clear();
        company1.setValue(null);
        table.getSelectionModel().clearSelection();
        setErrorMessage("");
    }


    //削除ボタン
    @FXML
    public void sakujoBotton(ActionEvent event){
        User selectedUser = table.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            company2.setValue(selectedUser.getCompany());
            name2.setText(selectedUser.getName());
            score2.setText(Integer.toString(selectedUser.getScore()));
            table.getItems().remove(selectedUser);
            name2.clear();
            score2.clear();
            company2.setValue(null);
            table.getSelectionModel().clearSelection();
        }
    }


    public void koushinBotton(ActionEvent event) {

        User selectedUser = table.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            String company = company2.getValue();
            String name = name2.getText();
            String scoreText = score2.getText();

            try {
                int score = Integer.parseInt(scoreText);

                if (company == null || company.isEmpty() || name.isEmpty() || score < 0 || score > 100) {
                    throw new IllegalArgumentException("入力が正しくありません");
                }

                selectedUser.setCompany(company);
                selectedUser.setName(name);
                selectedUser.setScore(score);
                table.refresh();
                table.getSelectionModel().clearSelection();
                setErrorMessage("");
            } catch (NumberFormatException e) {
                setErrorMessage("スコアは整数値で入力");
            } catch (IllegalArgumentException e) {
                setErrorMessage(e.getMessage());
            }
        }
    }

    //エラーメッセージ
    private void setErrorMessage(String message) {
        errorMessage.setText(message);
    }
}