package no.ntnu.cardgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Representerer en full kortstokk med 52 kort. 
 * Inkluderer metode for å dele ut en hånd med n tilfeldige kort.
 */
public class DeckOfCards {

  private final char[] suits = {'S', 'H', 'D', 'C'};
  private final List<PlayingCard> deck;
  private final Random random;

  /**
   * Lager full stokk med 52 kort, 4 sorter og 13 verdier
   */
  public DeckOfCards() {
    deck = new ArrayList<>();
    random = new Random();

    for (char suit : suits) {
      for (int facevalue = 1; facevalue <= 13; facevalue++) {
        deck.add(new PlayingCard(0, facevalue));
      }
    }
  }
  
}