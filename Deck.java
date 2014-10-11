package blackjack;
import java.util.Random;

public class Deck {

	// Array van kaarten in het deck. Bovenste kaart is in de eerste index.
	private Card[]	myCards;
	
	// Aantal kaarten momenteel in het deck.
	private int numCards;
	
	// Constructor met standaard van één deck zonder shuffle.
	public Deck() {
		// Aanroepen andere constructor met één deck zonder shuffle.
		this(1, false);
	}
	
	// Constructor met hoeveel decks en of het geschud moet worden.
	public Deck(int numDecks, boolean shuffle) {
		
		this.numCards = numDecks * 52;
		this.myCards = new Card[this.numCards];
		
		// Kaart index
		int c = 0;		
		
		// Voor elk deck
		for (int d = 0; d < numDecks; d++) {
			
			// Voor elke suit
			for (int s = 0; s < 4; s++) {
				
				// Voor elk nummer
				for (int n = 1; n <= 13; n++ ) {
					
					// Nieuwe kaart toevoegen aan het deck.
					this.myCards[c] = new Card(Suit.values()[s], n);
					c++;
					
				}
			}
		}
		
		// Schudden indien nodig.
		if (shuffle) {
			this.shuffle();
		}
		
		
	}

	// Shuffle door random twee kaarten te wisselen
	public void shuffle() {
		
		// Random number generator
		Random rng = new Random();
		
		// Temp kaart
		Card temp;
		
		int j;
		for (int i = 0; i < this.numCards; i++) {
			
			// Pak een willekeurige kaart j om de waarde van i mee te wisselen
			j = rng.nextInt(this.numCards);
			
			// Wissel
			temp = this.myCards[i];
			this.myCards[i] = this.myCards[j];
			this.myCards[j] = temp;
			
		}
	}

	// Volgende kaart uit deck halen
	public Card dealNextCard() {
		
		// Bovenste kaart
		Card top = this.myCards[0];
		
		// Alle volgende kaarten één plek naar links in de index
		for (int c = 1; c < this.numCards; c++) {
			this.myCards[c-1] = this.myCards[c];
		}
		// Dubbele aan einde van de loop verwijderen
		this.myCards[this.numCards-1] = null;
		
		// Verminderen van het aantal kaarten in deck
		this.numCards--;
		
		return top;
	}
	
	// Print de bovenste kaarten in het deck
	public void printDeck(int numToPrint) {
		for (int c = 0; c < numToPrint; c++) {
			System.out.printf("% 3d/%d %s\n", c+1, this.numCards, this.myCards[c].toString());
		}
		System.out.printf("\t[%d others]\n", this.numCards-numToPrint);
	}
}

