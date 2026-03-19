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

  /**
   * Returnerer kort i denne hånden. 
   * 
   * @return unmodifiable liste av kort
   */
  public List<PlayingCard> getCards() {
    return cards;
  }

  /**
   * Oppgave 5
   * Summerer verdien av facevalue i hånden. 
   * 
   * @return total sum av kortenes verdi. 
   */
  public int sumOfFaces() {
    return cards.stream()
      .mapToInt(PlayingCard::getFacevalue)
      .sum();
  }

  /**
   * Returnerer string av alle hjerter i hånden. 
   * 
   * @return formattert string av hjerterkort. 
   */
  public String heartsAsString() {
    String hearts = cards.stream()
      .filter(c -> c.getSuit() == 'H')
      .map(PlayingCard::toString)
      .collect(Collectors.joining(" "));

    return hearts.isEmpty() ? "No hearts" : hearts;
  }

  /**
   * Sjekker om spardame er i hånden. 
   * 
   * @return true om spardame er i hånden
   */
  public boolean hasQueenOfSpades() {
    return cards.stream()
      .anyMatch(c -> c.getSuit() == 'S' && c.getFacevalue() == 12);
  }

  /**
   * Sjekker om kortene utgjør en 5-flush. 
   * 
   * @return true om 5 kort fra samme sort er på hånden. 
   */
  public boolean isFlush() {
    Map<Character, Long> suitCounts = cards.stream()
      .collect(Collectors.groupingBy(PlayingCard::getSuit, Collectors.counting()));

    return suitCounts.values().stream()
      .anyMatch(count -> count >= 5);
  }
  
  /**
   * Returnere string med kortene på hånden. 
   * 
   * @return kortstring med rom mellom kortene, eks "H4 H5 H8 H9 D2"
   */
  @Override
  public String toString() {
    return cards.stream()
      .map(PlayingCard::toString)
      .collect(Collectors.joining(" "));
  }
}