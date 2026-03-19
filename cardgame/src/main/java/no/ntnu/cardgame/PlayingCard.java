package cardgame.src.main.java.no.ntnu.cardgame;

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

  /**
   * Returnerer kortets sort. 
   * 
   * @return suit char (S, H, D, C)
   */
  public char getSuit() {
    return suit;
  }

  /**
   * Returnerer kortets verdi. 
   * 
   * @return int fra 1 til 13
   */
  public int getFacevalue() {
    return facevalue;

  }
  /**
   * Returnerer string som representerer kortets sort og verdi, eks. H13 for hjerter konge. 
   * 
   * @return string med sort-bokstav fulgt av verdi
   */
  @Override
  public String toString() {
    return "" + suit + facevalue;
  }
  
}