package transportManagement;

import java.util.HashMap;

import transportManagement.supportClasses.TransportClass;

public class CruiseSection extends TransportSection {

	private HashMap<Character, Cabin[]> decks;
	
	CruiseSection( int cabinsPerDeck, String deck, TransportClass seatClass ) {
		super(seatClass);
		
		for( int index = 0; index < deck.length(); index++ ) {
			Cabin[] cabins = new Cabin[cabinsPerDeck];
			
			for( int x = 0; x < cabinsPerDeck; x++ )
				cabins[x] = new Cabin(x, deck.charAt(index));
				
			decks.put(new Character(deck.charAt(index)), cabins);
		}
	}
	
	protected void bookSeat(int cabin, char deck)
	{ decks.get(deck)[cabin].bookSeat(); }

	@Override
	protected boolean isSeatAvailable(int cabin, char deck)
	{ return decks.get(deck)[cabin].isAvailable(); }

	@Override
	public String toString()
	{ return seatingClass.toString(); }
}
