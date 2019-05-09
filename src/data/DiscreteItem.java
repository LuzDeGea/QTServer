package data;

class DiscreteItem extends Item{

	//metodi
	
	//Comportamento: Invoca il costruttore della classe madre 
	DiscreteItem(DiscreteAttribute attribute, String value) {	
		super(attribute,value);
	}
	
	// Comportamento: Restituisce 0 se (getValue().equals(a)) , 1 altrimenti.
	double distance(Object a) {			
		if(getValue().equals(a)) {
			return 0;
		}else 
		{
			return 1;
		}
	}
	
	
}