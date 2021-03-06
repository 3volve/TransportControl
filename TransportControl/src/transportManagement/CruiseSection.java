package transportManagement;

import java.util.HashMap;

import transportManagement.supportClasses.TransportClass;

public class CruiseSection extends TransportSection implements Cloneable {

	private HashMap<Character, TransportSeating[]> decks;
	
	CruiseSection( int cabinsPerDeck, String deck, TransportClass seatClass, int price ) {
		super(seatClass, price);
		
		decks = new HashMap<Character, TransportSeating[]>();
		
		for( int index = 0; index < deck.length(); index++ ) {
			Cabin[] cabins = new Cabin[cabinsPerDeck];
			
			for( int x = 0; x < cabinsPerDeck; x++ )
				cabins[x] = new Cabin(x, deck.charAt(index));
				
			decks.put(new Character(deck.charAt(index)), cabins);
		}
	}
	
	protected void bookSeat(int cabin, char deck)
	{ decks.get(deck)[cabin].bookSeat(); }

	
	protected boolean isSeatAvailable(int cabin, char deck)
	{ return decks.get(deck)[cabin].isAvailable(); }
	
	private int numberCabinsAvailable() {
		int count = 0;
		
		for( TransportSeating[] rows : decks.values() ) 
			for( TransportSeating cabin : rows ) 
				if( cabin != null) 
					if( cabin.isAvailable() ) count++;
					
		 return count;
	}
	
	@Override
	protected CruiseSection clone() {
		CruiseSection clone = null;
		
		try { clone = (CruiseSection)super.clone(); }
		catch (CloneNotSupportedException e) { e.printStackTrace(); }
		
		if( clone != null ){
			for( Character charact : decks.keySet() ) {
				TransportSeating[] temp = new TransportSeating[decks.get(charact).length];
	
				for( int index = 0; index < decks.get(charact).length; index++ )
					temp[index] = ((Cabin)decks.get(charact)[index]).clone();
					
				clone.decks.put(charact, temp);
			}
		}
		
		return clone;
	}

	protected void printLayout() {
		for( Character deck : decks.keySet() ) {
				TransportSeating[] temp = decks.get(deck);
				System.out.print("Deck " + deck + " has " + temp.length + " cabins in this class on it (O = available, X = taken).\n  ");
			for( int index = 0; index < temp.length; index++ ) {
				System.out.println(temp[index].bookStr());
			}
		}
	}
	
	protected void printDetailedString() {
		String str = "\n      " + seatingClass + " class, costing " + pricing + " per cabin,"
				+ " with " + numberCabinsAvailable() + " cabins still available";
		System.out.println(str);
		printLayout();
	}
	
	protected String toViewingString() {
		return "\n         " + seatingClass + " class, costing " + pricing + " per cabin,"
			+ " with " + numberCabinsAvailable() + " cabins still available";
	}
}
