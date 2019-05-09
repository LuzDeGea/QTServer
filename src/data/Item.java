package data;

abstract class Item {
	
	private Attribute attribute; 	// attributo coinvolto nell'item
	private Object value; 			//	valore assegnato all'attributo
	
	//metodi
	Item(Attribute attribute, Object value) { 	//Comportamento: inizializza i valori dei membri attributi
		this.attribute=attribute;
		this.value=value;
	}
	
	Attribute getAttribute() { 			//Comportamento: restituisce attribute
		return this.attribute;
	}
	
	Object getValue() {					//Comportamento: restituisce value
		return this.value;
	}
	
	public String toString() {			//Comportamento: restituisce value
		return "value: "+value;
	}
	
	abstract double distance(Object a);		//L’implementazione sarà diversa per item discreto e item continuo
	
}
