package blackjack.programming;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	
	private ArrayList<Card> cards;
	
	public Deck() {
		//adds all cards into array package
		this.cards = new ArrayList<Card>();
	}
	
	public void createFullDeck() {
		//Creates the 52 cards using for loops
		for(Value cardValue: Value.values()) {
			for(Suit cardSuit : Suit.values()) {
				this.cards.add(new Card(cardValue,cardSuit));
			}
		}
	}
	public static void delay(int a) { //adds delay to make game run smoother for user.
		try {
			Thread.sleep(a);
		}
		catch(Exception e){
			
		}
	}
	
	public void shuffle() {
		ArrayList<Card>shuffDeck = new ArrayList<Card>();
		Random rand = new Random();
		int randCard = 0;
		int amountofCards = this.cards.size();
		//randomises
		for (int i = 0; i < amountofCards; i++) {
			randCard = rand.nextInt((this.cards.size()-1 - 0)+ 1) + 0;
			shuffDeck.add(this.cards.get(randCard));
			this.cards.remove(randCard);
	} //puts back into original deck.
		this.cards = shuffDeck;
}
	public void returntoDeck(Deck moveTo) {
		int deckSize = this.cards.size();
		
		for(int i = 0; i < deckSize; i++) {
			moveTo.addCard(this.getCard(i));
		}
		for(int i = 0; i < deckSize; i++) {
			this.removeCard(0);
		}
	}
	
	
	public String toString() {
		String cardListOutput="";
		for(Card aCard : this.cards) {
			cardListOutput += "\n" + aCard.toString();
		}
		return cardListOutput;
	}
	
	public int deckSize(){
		return this.cards.size();
	}
	
	//Getters and Setters
	
	public void removeCard(int i) {
		this.cards.remove(i);
		
	}
	public Card getCard(int i) {
		return this.cards.get(i);
	}
	
	public void addCard(Card addCard) {
		this.cards.add(addCard);
	}
	
	//Draws a card from the deck
	public void draw(Deck comingFrom) {
		this.cards.add(comingFrom.getCard(0));
		comingFrom.removeCard(0);
	}
	
	public int cardsValue(){ //return the actual value of cards
		int totalValue = 0;
		int aces = 0;
		
		for(Card aCard : this.cards) {
			switch(aCard.getValue()) {
			case Two: totalValue += 2; break;
			case Three: totalValue += 3; break;
			case Four: totalValue += 4; break;
			case Five: totalValue += 5; break;
			case Six: totalValue += 6; break;
			case Seven: totalValue += 7; break;
			case Eight: totalValue += 8; break;
			case Nine: totalValue += 9; break;
			case Ten: totalValue += 10; break;
			case Jack: totalValue += 10; break;
			case Queen: totalValue += 10; break;
			case King: totalValue += 10; break;
			case Ace: aces += 1; break;
			
			}		
		}
		
		for(int i = 0; i < aces; i++) { //Changes the value of the ace dependent on hand
			if(totalValue > 10){
				totalValue += 1;
			}
				else{
					totalValue += 11;
				}
		}
			return totalValue;
		
	}
}
