/**
 * Class to handle hands
 *
 * @author Herb Wolfe Jr
 *
 * TODO:
 *	Add validation to setCard()
 *
 */

package maverick;

import static java.lang.System.*;

public class Card {

	private short aCard;

	private Card() {
		this.setCard((short)0);
	}

	public Card(Card c) {
		this.aCard = c.aCard;
	}

	public Card(short rank, short suit) {
		this();
		this.setCard((short)(rank + suit));
	}

	boolean isValidRank(short rank) {
		boolean valid = false;

		//return valid;

		return true;
	}

	boolean isValidSuit(short suit) {
		boolean valid = false;

		//return valid;

		return true;
	}

	/**
	 * Returns the internal representation of the current card
	 */

	short getCard() {
		return this.aCard;
	}

	/**
	 * Returns a short representation of the rank
	 */

	short getRank() {
		return (short)(this.aCard & Maverick.RANKMASK);
	}

	/**
	 * Returns a character representing the rank of a card, such as 'A' or '7'
	 */

	char getRankChar() {
		short idx = this.getRankIdx();
		return Maverick.RANKCHARS[idx];
	}

	/**
	 * Returns the index in the SUIT array of the card
	 */

	private short getRankIdx() {
		short aRank = this.getRank();
		short idx = -1;
		for (byte i = 0; i < Maverick.RANKS; i++) {
			if (aRank == Maverick.RANKLIST[i]) {
				idx = i;
				break;
			}
		}

		if (idx == -1) {
			// change to exception ?
			out.println("Error! card's rank not found!");
			out.println("Card: " + this.aCard);
			out.println("Rank: " + aRank);
			System.exit(-1);
		}

		return idx;
	}

	/**
	 * Returns a String representing the rank of the card, such as 'ACE'
	 */

	private String getRankStr() {
		short idx = this.getRankIdx();

		return Maverick.LONGRANKS[idx];
	}

	/**
	 * Returns a byte representation of the suit
	 */

	byte getSuitByte() {
		return (byte)(this.aCard & Maverick.SUITMASK);
	}

	/**
	 * Returns a character representing the suit of a card, 'C','D','H', or 'S'
	 */

	char getSuitChar() {
		short idx = this.getSuitIdx();

		return Maverick.SUITCHARS[idx];
	}

	/**
	 * Returns the index in the SUIT array of the card
	 */

	private short getSuitIdx() {
		byte aSuit = this.getSuitByte();
		short idx = -1;
		for (byte i = 0; i < Maverick.SUITS; i++) {
			if (aSuit == Maverick.SUIT[i]) {
				idx = i;
				break;
			}
		}

		if (idx == -1) {
			// change to exception ?
			out.println("Error! card's suit not found!");
			out.println("Card: " + aCard);
			out.println("Suit: " + aSuit);
			System.exit(-1);
		}

		return idx;
	}

	/**
	 * Returns the name of the suit, such as "CLUBS"
	 */

	private String getSuitStr() {
		short idx = this.getSuitIdx();

		return Maverick.LONGSUITS[idx];
	}

	/**
	 * Set the card
	 * @param short - the internal representation of the card
	 */

	public void setCard(short aCard) {
		short rank, suit;

		rank = (short)(aCard & Maverick.RANKMASK);
		suit = (short)(aCard & Maverick.SUITMASK);

		if (this.isValidRank(rank) && this.isValidSuit(suit)) {
			this.aCard = aCard;
		} else {
			// throw exception?
			out.println("Error! Invalid card!");
		}
	}

	/**
	 * Converts a card to a long string, such as "ACE of CLUBS"
	 */

	public String toLongString() {
		return this.getRankStr() + " of " + this.getSuitStr();
	}

	/**
	 * Converts a card to a 2 character string, such as "AH" or "7C"
	 */

	public String toString() {
		char rankCh, suitCh;
		String str = "";

		rankCh = this.getRankChar();
		suitCh = this.getSuitChar();

		str += rankCh;
		str += suitCh;

		/* test code
		str += (" " + Short.toString(this.getCard()));
		*/

		return str;
	}

}
