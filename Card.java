package blackjack.programming;

public class Card {
	private Suit suit;
	private Value value;
	
	public Card(Value value, Suit suit) {
		this.value= value;
		this.suit = suit;
		
	}	

	public String toString() {
		return this.value.toString() + " of " + this.suit.toString();
	}
	
	public Value getValue() {
		return this.value;
	}
}