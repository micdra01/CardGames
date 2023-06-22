package gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SeatController {
    @FXML
    private TextField txtName;
    private PokerController pokerController;
    private int seatNo;

    public void setPokerController(PokerController pokerController, int seatNo) {
        this.pokerController = pokerController;
        this.seatNo = seatNo;
    }
    public void handleConfirm() {
        pokerController.updateSeat(seatNo, txtName.getText());
        handleCancel();
    }

    public void handleCancel() {
        Stage stage = (Stage) txtName.getScene().getWindow();
        stage.close();
    }
}
