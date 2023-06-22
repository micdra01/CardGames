package gui.controller;

import be.Card;
import gui.model.PokerModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class PokerController implements Initializable {
    @FXML
    private FlowPane board;
    @FXML
    private HBox buttonArea, seat0, seat1, seat2, seat3, seat4, seat5, seat6, seat7, seat8, seat9;
    @FXML
    private Button btnClear, btnShuffle, btnShowNext, btnDealNext, btnDealHand;

    private PokerModel pokerModel;
    private boolean isOdd;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pokerModel = new PokerModel();
    }

    public void handleClear() {
        board.getChildren().clear();
        seat2.getChildren().clear();
        seat8.getChildren().clear();

        btnClear.setDisable(true);
        btnShuffle.setDisable(false);
        btnShowNext.setDisable(true);
        btnDealNext.setDisable(true);
        btnDealHand.setDisable(true);
    }

    public void handleShuffle() {
        handleClear();
        pokerModel.shuffle();

        btnClear.setDisable(true);
        btnShuffle.setDisable(true);
        btnShowNext.setDisable(false);
        btnDealNext.setDisable(false);
        btnDealHand.setDisable(false);
    }

    public void handleShowNext() {
        if(!pokerModel.hasEmptyDeck()) {
            displayCard(pokerModel.getCard());

            btnClear.setDisable(false);
            btnShuffle.setDisable(true);
            btnShowNext.setDisable(false);
            btnDealNext.setDisable(false);
            btnDealHand.setDisable(true);
        } else {
            btnShowNext.setDisable(true);
            btnDealNext.setDisable(true);
        }
    }

    public void handleDealNext() {
        if(isOdd) {
            dealCard(seat8);

            isOdd = false;
        } else {
            dealCard(seat2);

            isOdd = true;
        }

        btnDealHand.setDisable(true);
    }

    private Pane createCardImage(Card c) {
        Pane pane = new Pane();
        pane.setPadding(new Insets(1));

        ImageView iv = new ImageView();
        iv.setPreserveRatio(true);
        iv.setFitWidth(60);

        iv.setImage(pokerModel.getCardImage(c));

        pane.getChildren().add(iv);

        return pane;
    }

    private void displayCard(Card c) {
        board.getChildren().add(createCardImage(c));
    }

    private void dealCard(HBox seat) {
        seat.getChildren().add(createCardImage(pokerModel.getCard()));
    }

    public void handleDealHand() {
        for(int i = 0; i < 4; i++) {
            handleDealNext();
        }

        for(int i = 0; i < 5; i++) {
            displayCard(pokerModel.getCard());
        }

        btnClear.setDisable(false);
        btnShowNext.setDisable(true);
        btnDealNext.setDisable(true);
        btnDealHand.setDisable(true);
    }
}
