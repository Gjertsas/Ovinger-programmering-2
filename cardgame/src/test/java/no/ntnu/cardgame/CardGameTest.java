package no.ntnu.cardgame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tester for DeckOfCards og HandOfCards.
 */

class CardGameTest {

  private DeckOfCards deck;

  @BeforeEach
  void setUp() {
      deck = new DeckOfCards();
  }

  // DeckOfCards tester
  @Test
  void dealHand_returnsCorrectNumberOfCards() {
    HandOfCards hand = deck.dealHand(5);
    assertEquals(5, hand.getCards().size());
  }

  @Test
  void dealHand_returnsUniqueCards() {
    HandOfCards hand = deck.dealHand(52);
    long uniqueCount = hand.getCards().stream().distinct().count();
    assertEquals(52, uniqueCount);
  }

  @Test
  void dealHand_throwsOnInvalidArgument_zero() {
    assertThrows(IllegalArgumentException.class, () -> deck.dealHand(0));
    }

  @Test
  void dealHand_throwsOnInvalidArgument_tooMany() {
    assertThrows(IllegalArgumentException.class, () -> deck.dealHand(53));
  }

  
  //HandOfCards tester

  @Test
  void sumOfFaces_calculatesCorrectly() {
    List<PlayingCard> cards = List.of(
            new PlayingCard('H', 1),
            new PlayingCard('S', 5),
            new PlayingCard('D', 10),
            new PlayingCard('C', 3),
            new PlayingCard('H', 2)
    );
    HandOfCards hand = new HandOfCards(cards);
    assertEquals(21, hand.sumOfFaces());
  }

  @Test
  void heartsAsString_returnsHeartsOnly() {
    List<PlayingCard> cards = List.of(
            new PlayingCard('H', 7),
            new PlayingCard('S', 4),
            new PlayingCard('H', 12),
            new PlayingCard('D', 3),
            new PlayingCard('C', 9)
    );
    HandOfCards hand = new HandOfCards(cards);
    String result = hand.heartsAsString();
    assertTrue(result.contains("H7"));
    assertTrue(result.contains("H12"));
    assertFalse(result.contains("S") || result.contains("D") || result.contains("C"));
  }

  @Test
  void heartsAsString_returnsNoHeartsMessage_whenNonePresent() {
    List<PlayingCard> cards = List.of(
            new PlayingCard('S', 1),
            new PlayingCard('D', 5),
            new PlayingCard('C', 13)
    );
    HandOfCards hand = new HandOfCards(cards);
    assertEquals("No Hearts", hand.heartsAsString());
  }

  @Test
  void hasQueenOfSpades_returnsTrueWhenPresent() {
    List<PlayingCard> cards = List.of(
            new PlayingCard('S', 12),
            new PlayingCard('H', 3),
            new PlayingCard('D', 7)
    );
    HandOfCards hand = new HandOfCards(cards);
    assertTrue(hand.hasQueenOfSpades());
  }

  @Test
  void hasQueenOfSpades_returnsFalseWhenAbsent() {
    List<PlayingCard> cards = List.of(
            new PlayingCard('S', 11),
            new PlayingCard('H', 12),
            new PlayingCard('D', 7)
    );
    HandOfCards hand = new HandOfCards(cards);
    assertFalse(hand.hasQueenOfSpades());
  }

  @Test
  void isFlush_returnsTrueForFiveOfSameSuit() {
    List<PlayingCard> cards = List.of(
            new PlayingCard('H', 1),
            new PlayingCard('H', 4),
            new PlayingCard('H', 7),
            new PlayingCard('H', 10),
            new PlayingCard('H', 13)
    );
    HandOfCards hand = new HandOfCards(cards);
    assertTrue(hand.isFlush());
  }

  @Test
  void isFlush_returnsFalseForMixedSuits() {
    List<PlayingCard> cards = List.of(
            new PlayingCard('H', 1),
            new PlayingCard('S', 4),
            new PlayingCard('H', 7),
            new PlayingCard('D', 10),
            new PlayingCard('C', 13)
    );
    HandOfCards hand = new HandOfCards(cards);
    assertFalse(hand.isFlush());
  }

  @Test
  void toString_formatsCorrectly() {
    List<PlayingCard> cards = List.of(
            new PlayingCard('H', 4),
            new PlayingCard('C', 3)
    );
    HandOfCards hand = new HandOfCards(cards);
    assertEquals("H4 C3", hand.toString());
  }
}