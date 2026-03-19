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
  
}