package gui.controller;

import be.Card;
import gui.model.PokerModel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PokerController implements Initializable {
    @FXML
    private FlowPane board;
    @FXML
    private HBox buttonArea, seat1, seat2, seat3, seat4, seat5, seat6;
    @FXML
    private Button btnClear, btnNewHand, btnShuffle, btnDealFlip, btnDealHand;
    @FXML
    private Button btnSeat1, btnSeat2, btnSeat3, btnSeat4, btnSeat5, btnSeat6;
    @FXML
    private Label lblSeat1, lblSeat2, lblSeat3, lblSeat4, lblSeat5, lblSeat6;

    private PokerModel pokerModel;
    private List<HBox> players = new ArrayList<>();
    private int index = 0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pokerModel = new PokerModel();
    }

    public void handleShuffle() {
        handleClearTable();
        pokerModel.shuffle();
        disableDealButtons(false);
    }

    public void handleDealFlip() {
        players.clear();

        players.add(seat2);
        lblSeat2.setText("HERO");

        players.add(seat5);
        lblSeat5.setText("VILLAIN");

        handleDealHand();
    }

    public void handleDealHand() {
        disableDealButtons(true);
        btnClear.setDisable(true);
        btnNewHand.setDisable(true);

        if(players.isEmpty()) {
            handleDealFlip();
        } else {

            //Deal hole cards to each player
            Timeline holeCards = new Timeline(new KeyFrame(Duration.seconds(2.5/ (players.size()*2) ), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    dealCard(players.get(index));
                    index++;
                    if(index >= players.size()) index = 0;
                }
            }));
            holeCards.setCycleCount(players.size()*2);
            holeCards.play();


            //Deal the flop
            Timeline flop = new Timeline(new KeyFrame(Duration.millis(250), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    dealCommunityCard(3); //Flop
                }
            }));
            flop.setDelay(Duration.seconds(3.5));
            flop.play();


            //Deal the turn and river
            Timeline turnAndRiver = new Timeline(new KeyFrame(Duration.seconds(1.5), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    dealCommunityCard(1);
                }
            }));

            turnAndRiver.setDelay(Duration.seconds(4));
            turnAndRiver.setCycleCount(2);
            turnAndRiver.play();


            //Enable clear & new hand buttons once hand is over
            Timeline enableClearBtn = new Timeline(new KeyFrame(Duration.ONE, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    btnClear.setDisable(false);
                    btnNewHand.setDisable(false);
                }
            }));
            enableClearBtn.setDelay(Duration.seconds(7));
            enableClearBtn.play();
        }
    }

    private void dealCommunityCard(int cardsToDeal) {
        for(int i = 0; i < cardsToDeal; i++) {
            displayCard(pokerModel.getCard());
        }
    }

    private void displayCard(Card c) {
        board.getChildren().add(createCardImage(c));
    }

    private void dealCard(HBox seat) {
        seat.getChildren().add(createCardImage(pokerModel.getCard()));
    }

    private Pane createCardImage(Card c) {
        Pane pane = new Pane();
        pane.setPadding(new Insets(1));

        ImageView iv = new ImageView();
        iv.setPreserveRatio(true);
        iv.setFitWidth(50);

        iv.setImage(pokerModel.getCardImage(c));

        pane.getChildren().add(iv);

        return pane;
    }

    public void handleSeat1() {
        openTakeSeat(1);
    }

    public void handleSeat2() {
        openTakeSeat(2);
    }

    public void handleSeat3() {
        openTakeSeat(3);
    }

    public void handleSeat4() {
        openTakeSeat(4);
    }

    public void handleSeat5() {
        openTakeSeat(5);
    }

    public void handleSeat6() {
        openTakeSeat(6);
    }

    private void openTakeSeat(int seatNo) {
        //Load the new stage & view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/TakeSeat.fxml"));
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SeatController seatController = loader.getController();
        seatController.setPokerController(this, seatNo);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setMinWidth(300);
        stage.setMinHeight(150);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void updateSeat(int seatNo, String name) {
        if(seatNo == 1) {
            lblSeat1.setText(name);
            players.add(seat1);
            btnSeat1.setDisable(true);
            btnSeat1.setVisible(false);
        }
        if(seatNo == 2) {
            lblSeat2.setText(name);
            players.add(seat2);
            btnSeat2.setDisable(true);
            btnSeat2.setVisible(false);
        }
        if(seatNo == 3) {
            lblSeat3.setText(name);
            players.add(seat3);
            btnSeat3.setDisable(true);
            btnSeat3.setVisible(false);
        }
        if(seatNo == 4) {
            lblSeat4.setText(name);
            players.add(seat4);
            btnSeat4.setDisable(true);
            btnSeat4.setVisible(false);
        }
        if(seatNo == 5) {
            lblSeat5.setText(name);
            players.add(seat5);
            btnSeat5.setDisable(true);
            btnSeat5.setVisible(false);
        }
        if(seatNo == 6) {
            lblSeat6.setText(name);
            players.add(seat6);
            btnSeat6.setDisable(true);
            btnSeat6.setVisible(false);
        }
    }

    public void handleNewHand() {
        handleClearTable();
    }

    public void handleClearAll() {
        handleClearPlayers();
        handleClearTable();
    }

    private void handleClearTable() {
        board.getChildren().clear();

        seat1.getChildren().clear();
        seat2.getChildren().clear();
        seat3.getChildren().clear();
        seat4.getChildren().clear();
        seat5.getChildren().clear();
        seat6.getChildren().clear();

        btnClear.setDisable(true);
        btnNewHand.setDisable(true);
        btnShuffle.setDisable(false);
        btnDealFlip.setDisable(true);
    }

    private void handleClearPlayers() {
        players.clear();

        lblSeat1.setText("Seat 1");
        lblSeat2.setText("Seat 2");
        lblSeat3.setText("Seat 3");
        lblSeat4.setText("Seat 4");
        lblSeat5.setText("Seat 5");
        lblSeat6.setText("Seat 6");

        btnSeat1.setDisable(false);
        btnSeat2.setDisable(false);
        btnSeat3.setDisable(false);
        btnSeat4.setDisable(false);
        btnSeat5.setDisable(false);
        btnSeat6.setDisable(false);

        btnSeat1.setVisible(true);
        btnSeat2.setVisible(true);
        btnSeat3.setVisible(true);
        btnSeat4.setVisible(true);
        btnSeat5.setVisible(true);
        btnSeat6.setVisible(true);
    }

    private void disableDealButtons(boolean disabled) {
        btnClear.setDisable(!disabled);
        btnNewHand.setDisable(!disabled);
        btnShuffle.setDisable(disabled);
        btnDealFlip.setDisable(disabled);
        btnDealHand.setDisable(disabled);
    }
}
