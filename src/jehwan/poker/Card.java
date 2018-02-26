package jehwan.poker;

public class Card {
	private int rank, suit;
	private static String[] suits = { "¢¼", "¢À", "¢½", "¡Ş" };
	private static String[] ranks = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

	public static String rankAsString(int __rank) {
		return ranks[__rank];
	}

	Card(int suit, int rank) {
		this.rank = rank;
		this.suit = suit;
	}

	public @Override String toString() {
		return suits[suit] + ranks[rank] +" " ;
	}

	public int getRank() {
		return rank;
	}

	public int getSuit() {
		return suit;
	}
}
