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

