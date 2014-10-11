package blackjack;
import java.util.Scanner;

public class BlackJack {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Deck theDeck = new Deck(1, true);
		
		BlackjackSpeler me = new BlackjackSpeler("Daan");
		BlackjackSpeler dealer = new BlackjackSpeler("Dealer");
		
		me.addCard(theDeck.dealNextCard());
		dealer.addCard(theDeck.dealNextCard());
		me.addCard(theDeck.dealNextCard());
		dealer.addCard(theDeck.dealNextCard());
		
		System.out.println("Cards are dealt\n");
		me.printHand(true);
		dealer.printHand(false);
		System.out.println("\n");
		
		// Moet een speler kaart nemen of niet
		boolean meDone = false;
		boolean dealerDone = false;
		String ans;
		
		while (!meDone || !dealerDone) {
			
			// Beurt speler
			if (!meDone) {
				System.out.println("Hit or Stay? (Enter H or S): ");
				ans = sc.next();
				System.out.println();
				
				if (ans.compareTo("H") == 0) {
					
					// Deel nieuwe kaart en check of speler busto is
					meDone = !me.addCard(theDeck.dealNextCard());
					me.printHand(true);
				}
				else {
					meDone = true;
				}
			}
		
			// Beurt dealer
			if (!dealerDone) {
				if (dealer.getHandSum() < 17) {
					System.out.println("The Dealer hits\n");
					dealerDone = !dealer.addCard(theDeck.dealNextCard());
					dealer.printHand(false);
				}
				else {
					System.out.println("The Dealer stays\n");
					dealerDone = true;
				}
			}
			
			System.out.println();
			
		}
		// Scanner afsluiten
		sc.close();
		
		// Print uiteindelijke handen
		me.printHand(true);
		dealer.printHand(true);
		
		int mySum = me.getHandSum();
		int dealerSum = dealer.getHandSum();
		
		if (mySum > dealerSum && mySum <= 21 || dealerSum > 21)	{
			System.out.println("You win");
			}
		else
		{
			System.out.println("Dealer wins");
		}
	}
}
