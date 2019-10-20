package com.orbitrondev.View;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import com.orbitrondev.Model.Card;
import com.orbitrondev.Model.HandType;
import com.orbitrondev.Model.Player;

public class PlayerPane extends VBox {
    private Label lblName = new Label();
    private HBox hboxCards = new HBox();
    private Label lblEvaluation = new Label("--");

    // Link to player object
    private Player player;

    public PlayerPane() {
        super(); // Always call super-constructor first !!
        this.getStyleClass().add("player"); // CSS style class

        // Add child nodes
        lblName.getStyleClass().add("player-text");
        lblEvaluation.getStyleClass().add("player-text");
        this.getChildren().addAll(lblName, hboxCards, lblEvaluation);

        // Add CardLabels for the cards
        for (int i = 0; i < 5; i++) {
            Label lblCard = new CardLabel();
            hboxCards.getChildren().add(lblCard);
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
        updatePlayerDisplay(); // Immediately display the player information
    }

    public void updatePlayerDisplay() {
        lblName.setText(player.getPlayerName());
        for (int i = 0; i < Player.HAND_SIZE; i++) {
            Card card = null;
            if (player.getCards().size() > i) card = player.getCards().get(i);
            CardLabel cl = (CardLabel) hboxCards.getChildren().get(i);
            cl.setCard(card);
            HandType evaluation = player.evaluateHand();
            if (evaluation != null)
                lblEvaluation.setText(evaluation.toString());
            else
                lblEvaluation.setText("--");
        }
    }
}
