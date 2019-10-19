/**
 * Class to handle hands
 *
 * @author Herb Wolfe Jr
 *
 */

package maverick;

import static java.lang.System.*;
import java.io.*;
import java.util.Scanner;

public class Hand {

	private Card[] aHand;
	private byte cardsInHand;
	private byte handSize;

	/**
	 * Creates a hand with a default size of HANDSIZE
	 */

	public Hand() {
		new Hand(Maverick.HANDSIZE);
	}

	/**
	 * Creates a hand with the passed size
	 * @param byte - the size of the hand to create
	 */

	public Hand(byte size) {
		if ((size > Maverick.MAXHANDSIZE) || (size < 1)) {
			// throw IllegalHandSize
			out.println("Illegal hand size!");
			System.exit(-1);
		}
		this.handSize = size;
		this.aHand = new Card[handSize];
		this.cardsInHand = 0;
	}

	/**
	 * Adds a card to the hand
	 * @param card the card to add
	 */

	public void addCard(Card aCard) {
		// change to exception!
		if (aCard == null) {
			out.println("Error! null card passed to addCard!");
			System.exit(-1);
		}

		if (this.cardsInHand < this.handSize) {
			//this.aHand[cardsInHand] = new Card(aCard);
			this.aHand[cardsInHand] = aCard;
			//this.aHand[cardsInHand] = aCard.clone();

			//this.aHand[cardsInHand].setCard(aCard.getCard());
			cardsInHand++;
		} else {
			/* hand is full! */
			out.println("Hand is full!");
		}
	}

	/**
	 * Returns an array of 5 card arrays, assuming this is a 6 or 7 card hand
	 *
	 * TODO: Modify/update to return an array of Hand objects
	 */

	private Hand[] get5CardHands() {
		Hand[] allHands = null;
		byte aSize, length = this.getHandSize();

		if (length == 6) {
			aSize = 6;
			allHands = new Hand[aSize];
			byte c1 = 0; // one card gets dropped 

			/* build the hands here */
			for (byte i = 0; i < aSize; i++) {
				byte p = 0; // placeholder in aHand
				for (byte j = 0; j < Maverick.HANDSIZE; j++) {
					if (p == c1) p++;
					allHands[i].addCard(this.aHand[p]);
					p++;
				}
				c1++;
			}
		} else if (length == 7) {
			aSize = 21;
			allHands = new Hand[aSize];
			byte c1 = 0, c2 = 1; // 2 cards get dropped

			/* build the hands here */
			for (byte i = 0; i < aSize; i++) {
				byte p = 0; // placeholder in aHand
				for (byte j = 0; j < Maverick.HANDSIZE; j++) {
					if (p == c1) p++;
					if (p == c2) p++;
					allHands[i].addCard(this.aHand[p]);
					p++;
				}
				if (c2 == length) {
					c1++; c2 = (byte)(c1 + 1);
				} else {
					c2++;
				}
			}
		} else {
			// we have an error
		}
		return allHands;
	}

	/**
	 * Returns a string representation of the hand
	 */

	public String getHand() {
		String aHandStr = "";

		for (int i = 0; i < cardsInHand; i++) {
			aHandStr += this.aHand[i] + " ";
		}

		return aHandStr;
	}

	/**
	 * Returns the number of cards in the hand
	 * @return byte - the number of cards in the hand
	 */

	public byte getCardCount() {
		return this.cardsInHand;
	}

	/**
	 * Returns the size of the hand
	 * @return byte - the number of cards the hand can hold
	 */

	public byte getHandSize() {
		return this.handSize;
	}

	/**
	 * Returns the name of the hand
	 * For example, 8 high straight, Ace high flush, Aces full of Treys, etc.
	 *
	 * @return The name of the hand.
	 */

	public String getHandName() {
		String name = "a hand";
		boolean flush = isFlush();
		int aRank = getHandRank(flush);

		/* lookup code goes here */

		if (flush) {
			name += " flush";
		}

		return name;
	}

	/**
	 * Returns the ranking of the hand
	 * Currently 1 for lowest hand, 75432 of mixed suits, 7462 for a royal
	 * flush
	 *
	 * @param boolean - whether or not the hand is a flush
	 * @return the rank of the hand 
	 */

	public short getHandRank(boolean flush) {
		// insert error checking! cardsInHand should == handSize
		// Need to change to exception

		if (this.getCardCount() < this.getHandSize()) {
			out.println("Error, hand is not complete, cannot look up rank!");
			System.exit(-1);
		}

		int aVal = 1;
		int aRank;

		for (Card c: this.aHand) {
			aRank = c.getRank() >> 3;
			aVal *= aRank;
		}

		return (this.lookup(aVal, flush));

		//return aVal;
	}

	/**
	 * Determine if the hand has a (5-card) flush.
	 *
	 * @return true if the hand has a flush
	 */

	private boolean hasFlush() {
		boolean flush = false;

		switch (handSize) {
			case 5:
				flush = isFlush();
				break;
			case 6:
			case 7:
				// generate possible 5 card combinations
				Hand fiveCardHands[] = this.get5CardHands();
				for(byte i = 0; i < fiveCardHands.length && !flush; i++) {
					flush = fiveCardHands[i].isFlush();
				}
				break;
			default:
				// throw error?
		}
		return flush;
	}

	/**
	 * Determines if the 5 card hand is a flush
	 * 
	 * @return true if the hand is a flush.
	 */

	private boolean isFlush() {
		short aSuit = Maverick.SUITMASK;
		boolean flush;

		/*
		 * add error checking?
		 * if this.cardsInHand <> 5 {
		 *		throw invalidHandException;
		 *		}
		 */

		for (Card c : this.aHand) {
			aSuit = (short)(aSuit & c.getSuitByte());
		}

		flush = (aSuit == this.aHand[1].getCard()) ? true : false;

		return flush;

	}

	/**
	 * Performs the actual lookup in the hand rank table
	 * @param int - the value to look for
	 * @param boolean - whether or not to return the flush rank
	 */

	private short lookup(int aVal, boolean flush) {
		short aRank = 0;
		int inRank, inVal;
		String token;

		/* insert lookup code here:
		 *
		 * open file
		 * seek aval
		 * if flush
		 *	   read flushrank
		 * else
		 *	   read rank
		 * endif
		 */

		out.println(aVal);

		Scanner in = null;

		try {
			in = new Scanner(new File("maverick/data/5cardranks.txt"));
			//in.useDelimiter(",");
		} catch (FileNotFoundException ex) {
			out.println(ex.getMessage());
			out.println("in " + System.getProperty("user.dir"));
			System.exit(1);
		}

		boolean found = false;

		while (in.hasNextLine() && !found) {
			inRank = in.nextInt();
			//inRank = Integer.parseInt(in.next());
			//out.print(inRank);
			//inVal = Integer.parseInt(in.next());
			inVal = in.nextInt();
			//out.println(inVal);
			//in.nextLine();

			if (aVal == inVal) {
				found = true;
				aRank = (short)inRank;
				out.println("value found!");
				break;
			}
		}

		if (!found) {
			out.println("Error, hand value not found in lookup file!");
		}


		return aRank;
	}

}
