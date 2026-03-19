package no.ntnu.cardgame;
 
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Representerer en hånd med kort utdelt til en spiller.
 * Streams-baserte metoder for å analysere hånden.
 */
public class HandOfCards {

  private final List<PlayingCard> cards;

  /**
   * Lager en hånd fra listen med kort. 
   * 
   * @param cards list
   */
  public HandOfCards(List<PlayingCard> cards) {
        this.cards = List.copyOf(cards);
    }
    
}