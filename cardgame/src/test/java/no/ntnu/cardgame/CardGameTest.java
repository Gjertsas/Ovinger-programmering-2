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

  // DeckOfCards
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

}