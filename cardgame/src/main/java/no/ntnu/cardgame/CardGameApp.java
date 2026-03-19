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
 * JavaFX app for Card Game. 
 * GUI bygget med java-kode.
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
    primaryStage.setTitle("Øving 5: Card Game");

    // Tittel
    Label title = new Label("♥ Card Game ♥ ");
    title.setFont(Font.font("Georgia", FontWeight.BOLD, 28));
    title.setTextFill(Color.WHITE);

    // Display
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

    // Knapper
    Button dealButton  = new Button("Deal Hand");
    Button checkButton = new Button("Check Hand");
 
    styleButton(dealButton,  "#e63946");
    styleButton(checkButton, "#457b9d");
 
    dealButton.setOnAction(e -> dealHand());
    checkButton.setOnAction(e -> checkHand());
 
    HBox buttons = new HBox(16, dealButton, checkButton);
    buttons.setAlignment(Pos.CENTER);

    // Resultat
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

    //Layout
    VBox root = new VBox(18, title, cardArea, buttons, results);
    root.setPadding(new Insets(24));
    root.setAlignment(Pos.TOP_CENTER);
    root.setStyle("-fx-background-color: #0d1b2a;");
 
    Scene scene = new Scene(root, 540, 430);
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();
  }
}