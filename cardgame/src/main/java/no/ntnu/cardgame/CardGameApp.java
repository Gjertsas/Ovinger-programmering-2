package no.ntnu.cardgame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * JavaFX application entry point for the Card Game.
 * GUI is built entirely in Java code (no FXML).
 */
public class CardGameApp extends Application {

    private final DeckOfCards deck = new DeckOfCards();
    private HandOfCards currentHand = null;

    private HBox cardBox;
    private Label sumValue;
    private Label heartsValue;
    private Label queenValue;
    private Label flushValue;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Card Game");

        // ── Title ─────────────────────────────────────────────
        Label title = new Label("♠ ♥  Card Game  ♦ ♣");
        title.setFont(Font.font("Georgia", FontWeight.BOLD, 28));
        title.setTextFill(Color.WHITE);

        // ── Card display area ─────────────────────────────────
        cardBox = new HBox(10);
        cardBox.setAlignment(Pos.CENTER);
        cardBox.setMinHeight(120);
        cardBox.setPadding(new Insets(10));

        Label placeholder = new Label("Press 'Deal Hand' to start");
        placeholder.setTextFill(Color.LIGHTGRAY);
        placeholder.setFont(Font.font("Arial", 14));
        cardBox.getChildren().add(placeholder);

        StackPane cardArea = new StackPane(cardBox);
        cardArea.setStyle(
                "-fx-background-color: #2d6a4f;" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: #95d5b2;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 12;"
        );
        cardArea.setPadding(new Insets(10));

        // ── Buttons ───────────────────────────────────────────
        Button dealButton  = new Button("🂠  Deal Hand");
        Button checkButton = new Button("✔  Check Hand");

        styleButton(dealButton,  "#e63946");
        styleButton(checkButton, "#457b9d");

        dealButton.setOnAction(e -> dealHand());
        checkButton.setOnAction(e -> checkHand());

        HBox buttons = new HBox(16, dealButton, checkButton);
        buttons.setAlignment(Pos.CENTER);

        // ── Results panel ─────────────────────────────────────
        sumValue    = resultLabel();
        heartsValue = resultLabel();
        queenValue  = resultLabel();
        flushValue  = resultLabel();

        GridPane results = new GridPane();
        results.setHgap(16);
        results.setVgap(10);
        results.setPadding(new Insets(16));
        results.setStyle(
                "-fx-background-color: #1d3557;" +
                "-fx-background-radius: 10;"
        );

        results.add(rowLabel("Sum of faces:"),    0, 0); results.add(sumValue,    1, 0);
        results.add(rowLabel("Hearts:"),          0, 1); results.add(heartsValue, 1, 1);
        results.add(rowLabel("Queen of Spades:"), 0, 2); results.add(queenValue,  1, 2);
        results.add(rowLabel("5-card Flush:"),    0, 3); results.add(flushValue,  1, 3);

        // ── Root layout ───────────────────────────────────────
        VBox root = new VBox(18, title, cardArea, buttons, results);
        root.setPadding(new Insets(24));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #0d1b2a;");

        Scene scene = new Scene(root, 540, 430);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /** Deals 5 cards and displays them as styled card tiles. */
    private void dealHand() {
        currentHand = deck.dealHand(5);
        cardBox.getChildren().clear();
        for (PlayingCard card : currentHand.getCards()) {
            cardBox.getChildren().add(makeCardTile(card));
        }
        clearResults();
    }

    /** Runs all stream-based analyses on the current hand. */
    private void checkHand() {
        if (currentHand == null) return;

        sumValue.setText(String.valueOf(currentHand.sumOfFaces()));
        heartsValue.setText(currentHand.heartsAsString());

        boolean hasQueen = currentHand.hasQueenOfSpades();
        queenValue.setText(hasQueen ? "Yes ✔" : "No");
        queenValue.setTextFill(hasQueen ? Color.LIGHTGREEN : Color.WHITE);

        boolean flush = currentHand.isFlush();
        flushValue.setText(flush ? "Flush! 🏆" : "No Flush");
        flushValue.setTextFill(flush ? Color.GOLD : Color.WHITE);
    }

    /** Clears all result labels. */
    private void clearResults() {
        sumValue.setText("-");
        heartsValue.setText("-");
        queenValue.setText("-");
        queenValue.setTextFill(Color.WHITE);
        flushValue.setText("-");
        flushValue.setTextFill(Color.WHITE);
    }

    /** Creates a styled card tile for a single PlayingCard. */
    private VBox makeCardTile(PlayingCard card) {
        boolean isRed = card.getSuit() == 'H' || card.getSuit() == 'D';
        String suitSymbol = getSuitSymbol(card.getSuit());
        String faceText   = getFaceText(card.getFacevalue());

        Label faceLabel = new Label(faceText);
        faceLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 18));
        faceLabel.setTextFill(isRed ? Color.RED : Color.BLACK);

        Label suitLabel = new Label(suitSymbol);
        suitLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        suitLabel.setTextFill(isRed ? Color.RED : Color.BLACK);

        VBox tile = new VBox(4, faceLabel, suitLabel);
        tile.setAlignment(Pos.CENTER);
        tile.setPrefSize(72, 96);
        tile.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: #cccccc;" +
                "-fx-border-radius: 8;" +
                "-fx-border-width: 1;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 6, 0, 2, 2);"
        );
        return tile;
    }

    /** Returns the Unicode suit symbol for a suit character. */
    private String getSuitSymbol(char suit) {
        return switch (suit) {
            case 'H' -> "♥";
            case 'D' -> "♦";
            case 'C' -> "♣";
            case 'S' -> "♠";
            default  -> "?";
        };
    }

    /** Returns a readable face string (A, J, Q, K or number). */
    private String getFaceText(int face) {
        return switch (face) {
            case 1  -> "A";
            case 11 -> "J";
            case 12 -> "Q";
            case 13 -> "K";
            default -> String.valueOf(face);
        };
    }

    /** Creates a styled row label for the results panel. */
    private Label rowLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        label.setTextFill(Color.LIGHTGRAY);
        return label;
    }

    /** Creates a styled value label for the results panel. */
    private Label resultLabel() {
        Label label = new Label("-");
        label.setFont(Font.font("Arial", 13));
        label.setTextFill(Color.WHITE);
        label.setMinWidth(200);
        return label;
    }

    /** Applies consistent styling to a button with hover effect. */
    private void styleButton(Button button, String color) {
        button.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        button.setTextFill(Color.WHITE);
        button.setPadding(new Insets(8, 20, 8, 20));
        String base  = "-fx-background-color:" + color + ";-fx-background-radius:8;-fx-cursor:hand;";
        String hover = "-fx-background-color:derive(" + color + ",-15%);-fx-background-radius:8;-fx-cursor:hand;";
        button.setStyle(base);
        button.setOnMouseEntered(e -> button.setStyle(hover));
        button.setOnMouseExited(e  -> button.setStyle(base));
    }

    public static void main(String[] args) {
        launch(args);
    }
}