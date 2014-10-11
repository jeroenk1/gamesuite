package blackjack;

public class BlackjackSpeler {

	// Naam 	(Zou eigenlijk moeten erven uit de class Speler)
	private String name;
	
	
	// Kaarten in de hand van de speler
	private Card[] hand = new Card[10];
	
	// Het aantal kaarten in de hand
	private int numCards;
	
	
	// Constructor voor speler
	public BlackjackSpeler(String aName) {
		this.name = aName;
		
		// Hand leegmaken
		this.emptyHand();
	}
	
	// Hand van de speler leegmaken.
	public void emptyHand() {
		for (int c = 0; c < 10; c++) {
			this.hand[c] = null;
		}
		this.numCards = 0;
	}
	
	// Kaart delen aan speler. Boolean geeft return of de som boven of gelijk aan 21 is.
	public boolean addCard(Card aCard) {
		
		// Geef error als maximum aantal kaarten is bereikt.
		if (this.numCards == 10) {
			System.err.printf("%s's already has 10 cards; " + "cannot add another\n", this.name);
			System.exit(1);
		}
		
		// Voeg nieuwe kaart toe en verhoog teller van kaarten
		this.hand[this.numCards] = aCard;
		this.numCards++;
		
		return (this.getHandSum() <= 21);
	}
	
	// Bepaal de waarde van de hand van de speler
	public int getHandSum() {
		
		int handSum = 0;
		int cardNum;
		int numAces = 0;
		
		// Bereken de waarde van elke kaart
		for (int c = 0; c < this.numCards; c++) {
			// Haal nummer van kaart op (uit de switch)
			cardNum = this.hand[c].getNumber();
			
			// Voor aas
			if (cardNum == 1) { 
				numAces++;
				handSum += 11;
				}
			// Voor plaatje
			else if (cardNum > 10) {
				handSum += 10;
			}
			// Voor 2 tot en met 10
			else {
				handSum += cardNum;
			}
		}
		
		// Als sum groter dan 21 is, zet aas en/of azen op 1
		while (handSum > 21 && numAces > 0) {
			handSum -= 10;
			numAces--;
		}
		
		
		return handSum;
	}
	
	// Print de kaarten in de hand. Boolean voor of de eerste kaart wel of niet te zien is.
	public void printHand(boolean showFirstCard) {
		
		System.out.printf("%s's cards:\n", this.name);
		for (int c = 0; c < this.numCards; c++) {
			if (c == 0 && !showFirstCard) {
				System.out.println(" [hidden]");
			}
			else {
				System.out.printf(" %s\n", this.hand[c].toString());
			
			}
		}
	}
}
