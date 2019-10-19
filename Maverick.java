/**
 * Constants for Maverick package
 *
 * @author Herb Wolfe Jr
 *
 * TODO:
 *	Add/use enumerations for suits/ranks?
 *
 */

package maverick;

import static java.lang.System.out;

public class Maverick {

	/* Suit is represented by 3 bits, byte = 8 bits */
	static final byte DECKSIZE = 52, MAXHANDSIZE = 7, SUITS = 4, RANKS = 13,
		CLUBS = 2, DIAMONDS = 3, HEARTS = 5, SPADES = 7,
		SUIT[] = {CLUBS, DIAMONDS, HEARTS, SPADES};

	/*
	 * this could be any of 5-7. Save determination until later
	 * Potentially could be 4, for badugi & variations
	 */
	static final byte HANDSIZE = 5;

	/* Playing card can be represented in 16 bits, a Java short */
	// short CARD;

	/* Prime values of cards for looking up hand value */
	static final short ACE = 0x148, KING = 0x128, QUEEN = 0x0F8,
		JACK = 0x0E8, TEN = 0x0B8, NINE = 0x098, EIGHT = 0x088,
		SEVEN = 0x068, SIX = 0x058, FIVE = 0x038, FOUR = 0x028,
		TREY = 0x018, DUECE = 0x010;

		
	//static final short RANKMASK = 0xF8, SUITMASK = 0x07;

	static final short SUITMASK = 0x07;
	static final short RANKMASK = 0xFF8;

	static final short RANKLIST[] = {ACE, KING, QUEEN, JACK, TEN, NINE,
		EIGHT, SEVEN, SIX, FIVE, FOUR, TREY, DUECE};

	static final char 
		RANKCHARS[] = {'A','K','Q','J','T','9','8','7','6','5','4','3','2'}, 
		SUITCHARS[] = {'C','D','H','S'};

	static final String
		LONGRANKS[] = {"ACE", "KING", "QUEEN", "JACK", "TEN", "NINE",
			"EIGHT", "SEVEN", "SIX", "FIVE", "FOUR", "THREE", "TWO"},
		LONGSUITS[] = {"CLUBS", "DIAMONDS", "HEARTS", "SPADES"};

	public Maverick() {
	}

	public static void main(String[] argv) {
		Deck d = new Deck();
		Card c;
		byte hs = 5;

		d.shuffle();

		Hand bigBlind = new Hand(hs), smallBlind = new Hand(hs);

		for (int i = 1; i <= hs; i++) {
			c = d.getNextCard();
			bigBlind.addCard(c);

			c = d.getNextCard();
			smallBlind.addCard(c);
		}

		out.println("Cards dealt!");

		short bigHand = bigBlind.getHandRank(false),
			smallHand = smallBlind.getHandRank(false);

		out.println("Big blind has: " + bigBlind.getHand());
		out.println("Its value is: " + bigHand);
		out.println("Small blind has: " + smallBlind.getHand());
		out.println("Its value is: " + smallHand);

	} // end constructor

} // end class
