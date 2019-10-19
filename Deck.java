/**
 * Deck manipulation 
 *
 * @author Herb Wolfe Jr
 *
 */

package maverick;

import static java.lang.System.*;

public class Deck {

	private Card aDeck[];
	private byte idx;

	public Deck() {
		this.aDeck = new Card[Maverick.DECKSIZE];
		this.resetDeck();
	}

	/**
	 * Prints the deck
	 */

	public void displayDeck() {
		for(Card c : this.aDeck) {
			//out.print("Rank is " + c.getRankChar());
			//out.print("Suit is " + c.getSuitChar());
			out.println(c.toString());
		}
	}

	/**
	 * Prints the deck using the long card names
	 */

	public void displayDeckLong() {
		for(Card c : this.aDeck) {
			//out.print("Rank is " + c.getRankChar());
			//out.print("Suit is " + c.getSuitChar());
			out.println(c.toString() + " "	+ c.toLongString());
		}
	}

	/**
	 * Returns the next card in the deck
	 */

	public Card getNextCard() {
		if (!this.hasMoreCards()) {
			/* throw an error! */
			out.println("Out of cards!");
			System.exit(-1);
		}

		Card aCard = aDeck[idx];
		this.idx++;

		return aCard;
	}

	/**
	 * Returns whether or not there are undealt cards left in the deck
	 */

	public boolean hasMoreCards() {
		return (this.idx < Maverick.DECKSIZE);
	}

	/**
	 * Returns a deck (not needed?)
	 */

	private static Card[] getNewDeck() {
		//Card aNewDeck[] = new Card[Maverick.DECKSIZE];
		//aNewDeck.resetDeck();
		//return aNewDeck;
		return null;
	}

	/**
	 * Resets the deck to its original state
	 */

	private void resetDeck() {
		this.idx = 0;

		byte c = 0;
		for (byte s = 0; s < Maverick.SUITS; s++) {
			for (byte r = 0; r < Maverick.RANKS; r++) {
				/*
				short aCard = (short)(Maverick.RANKLIST[r] + Maverick.SUIT[s]);
				this.aDeck[c] = new Card();
				this.aDeck[c].setCard(aCard);
				*/
				this.aDeck[c] = 
					new Card(Maverick.RANKLIST[r], Maverick.SUIT[s]);
				c++;
			}
		}

	}

	/**
	 * Shuffles the default deck
	 */

	public void shuffle() {
		this.idx = 0;
		for (byte i = 1; i <= 4; i++) {
			for (byte j = 0; j < this.aDeck.length; j++) {
				byte r = (byte)(Math.random() * Maverick.DECKSIZE);
				short tempCard = this.aDeck[r].getCard();
				this.aDeck[r].setCard(this.aDeck[j].getCard());
				this.aDeck[j].setCard(tempCard);
			}
		}
		return;
	}

	public static void main (String[] argv) {
		Deck d = new Deck();
		//d.displayDeck();
		d.shuffle();
		//d.displayDeck();
		d.displayDeckLong();
	}

}
