/* 
 * Created By Zachary Holme
 * 15th May 2020
 * 
 * */

package blackjack.programming;
import java.util.Scanner; //for user input.


public class Blackjack {

	public static void main(String[] args) {

		System.out.println("WELCOME TO BLACKJACK \nCan you reach £10,000 to win!?");
		Deck playingDeck = new Deck();
		playingDeck.createFullDeck();
		playingDeck.shuffle();
		Deck playerDeck = new Deck();
		Deck dealerDeck = new Deck();
		int valid = 0;
		int win = 10000;
		int rounds = 0;
		float playerstartCash = 10000.00f;
		@SuppressWarnings("resource")
		Scanner userInput = new Scanner(System.in);
		
		//For testing System.out.println(playingDeck);
		while(playerstartCash < win && playerstartCash > 0) {
			Deck.delay(1000);
			valid = 0;
			System.out.println("you have £" + playerstartCash +"0"+ "\nHow much would you like to bet this round: ");
			rounds++;
			float playerBet = userInput.nextFloat();
			if(playerBet > playerstartCash) {
				rounds--;
				System.out.println("We're not a charity! Look at your balance and bet again."); 
				valid++;
			}
		
			
			boolean endRound = false;
			
			while(valid == 0){//validates bet amount
			valid++;
			//for player
			playerDeck.draw(playingDeck);
			playerDeck.draw(playingDeck);
			//for dealer
			dealerDeck.draw(playingDeck); 
			dealerDeck.draw(playingDeck);
			
			while(true) {
				Deck.delay(500);
				System.out.println("Your hand: " + playerDeck.toString());
				System.out.println("--------------------------------\n"+ "Your Hand's Value: " + playerDeck.cardsValue() +"\n--------------------------------");
				//show one of the dealers cards.
				Deck.delay(3000);
				System.out.println("Dealer Hand: " + dealerDeck.getCard(0).toString() + " and [folded over]");
				
				//Players functions
				Deck.delay(2000);
				System.out.println("Would you like to 1 = Twist or 2 = Stick?  :");
				int response = userInput.nextInt();
				if(response == 1){
					Deck.delay(2000);
					playerDeck.draw(playingDeck);
					System.out.println("You draw a " + playerDeck.getCard(playerDeck.deckSize()-1).toString());
					if(playerDeck.cardsValue() > 21) {
						Deck.delay(1000);
 						System.out.println("Bust, Unlucky. Currently valued at: " + playerDeck.cardsValue());
 						playerstartCash -= playerBet;
 						endRound = true;
 						break;
					}
				}
				
				if(response == 2) {
					break;
				}
			}
			//reveal hand
			System.out.println("\n--------------------------------\nDealers Cards: " + dealerDeck.toString());
			//verify game state
			if((dealerDeck.cardsValue()>=17) && (dealerDeck.cardsValue()>playerDeck.cardsValue()) && endRound==false) {
				Deck.delay(1000);
				System.out.println("\nDealer beats you! ");
				playerstartCash -= playerBet;
				endRound = true;
			} 
			//Dealer Draws at 16 stand at 17
			while((dealerDeck.cardsValue() < 17) && endRound == false) {
				Deck.delay(1000);
				dealerDeck.draw(playingDeck);
				System.out.println("Dealer Draws: " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
			}
			//Display total amount for dealer
			System.out.println("\n--------------------------------\nDealer's Hand is valued at: " + dealerDeck.cardsValue() + "\n--------------------------------\n");
			//Win or lose?
			if((dealerDeck.cardsValue() > 21) && endRound == false) {
				Deck.delay(2000);
				System.out.println("\nDealer lost! Congrats you WIN woo!");
				playerstartCash += playerBet;
				endRound = true;
			}
			
			//Is it a draw?
			if((playerDeck.cardsValue() == dealerDeck.cardsValue()) && endRound == false) {
				Deck.delay(2000);
				System.out.println("\nDRAW. That's kinda lame.");
				endRound = true;
			}
			
			if((playerDeck.cardsValue() > dealerDeck.cardsValue()) && endRound == false) {
				Deck.delay(2000);
				System.out.println("Wait, you actually WON. Didn't see that one coming!");
				playerstartCash += playerBet;
				endRound = true;
			}
			else if(endRound == false) {
				Deck.delay(2000);
				System.out.println("HAHA! You lost imagine losing £" + playerBet + "0. \nBetter luck next time!");
				playerstartCash -= playerBet;
				endRound = true;
			}
			
			
			
			playerDeck.returntoDeck(playingDeck);
			dealerDeck.returntoDeck(playingDeck);
			Deck.delay(1000);
			System.out.println("--------------------------------\nEnd of the Round.\n--------------------------------");
			}
			
		}	if(playerstartCash != win && playerstartCash == 0) {
			Deck.delay(1000);
			System.out.println("You silly billy! You are out of money." + "\n--------------------------------\n        G A M E O V E R\n--------------------------------");
			Deck.delay(10000);
			}
			else if(playerstartCash == win){
				System.out.println("            __    __  ____  ____   ____     ___  ____   __            \r\n" + 
						"           |  |__|  ||    ||    \\ |    \\   /  _]|    \\ |  |           \r\n" + 
						" _____     |  |  |  | |  | |  _  ||  _  | /  [_ |  D  )|  |     _____ \r\n" + 
						"|     |    |  |  |  | |  | |  |  ||  |  ||    _]|    / |__|    |     |\r\n" + 
						"|_____|    |  `  '  | |  | |  |  ||  |  ||   [_ |    \\  __     |_____|\r\n" + 
						"            \\      /  |  | |  |  ||  |  ||     ||  .  \\|  |           \r\n" + 
						"             \\_/\\_/  |____||__|__||__|__||_____||__|\\_||__|           \r\n" + 
						"                                                                      ");
				
				Deck.delay(2000);
				System.out.println("\n--------------------------------\nTotal rounds played: " + rounds +"\n--------------------------------\n");
				if(rounds < 10) {
					Deck.delay(2000);
					System.out.println("Wow you're skillful *cough cough* lucky.\n Nevertheless you deserve this:");
					Deck.delay(3000);
					System.out.println("Wait for it...");
					Deck.delay(2000);
					System.out.println("Any second now!");
					Deck.delay(5000);
					System.out.println("  "
							+ "           ___________\r\n" + 
							"            '._==_==_=_.'\r\n" + 
							"            .-\\:      /-.\r\n" + 
							"           | (|:.     |) |\r\n" + 
							"            '-|:.     |-'\r\n" + 
							"              \\::.    /\r\n" + 
							"               '::. .'\r\n" + 
							"                 ) (\r\n" + 
							"               _.' '._\r\n" + 
							"       WOO! `\"\"\"\"\"\"\"`" +
							"\nCongratulations you have beaten this game!");
					Deck.delay(10000);
					}
					else if(rounds >= 10) {
						System.out.println("You played it safe with your bets and now you're here!");
						Deck.delay(3000);
						System.out.println("If you feel this isn't what you hoped it would be, maybe aim for under 10 rounds!");
						Deck.delay(10000);
					}
					
				}
				
			}
		

	}
