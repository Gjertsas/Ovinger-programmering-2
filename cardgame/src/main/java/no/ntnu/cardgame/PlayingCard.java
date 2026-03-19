package no.ntnu.cardgame;

/** 
 * Representerer ett kort med farge/sort og verdi. 
 * Farge/sort: S = Spades, H = Hearts, D = Diamonds, C = Clubs
 * Verdi: 1 (Ace) -> 13 (King) 
 */

public class PlayingCard {

  private final char suit;
  private final int facevalue;

  /** 
   * Lager et spillekort med en farge/sort og verdi. 
   * 
   * @param suit en av S, H, D og C. 
   * @param facevalue verdi fra 1 til 13
   */
  public PlayingCard(char suit, int facevalue) {
    this.suit = suit;
    this.facevalue = facevalue;
  }
}