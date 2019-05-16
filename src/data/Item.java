package data;

abstract class Item {

	private Attribute attribute; // attributo coinvolto nell'item
	private Object value; // valore assegnato all'attributo

	// metodi
	Item(final Attribute attribute, final Object value) { // Comportamento: inizializza i valori dei membri attributi
		this.attribute = attribute;
		this.value = value;
	}

	Attribute getAttribute() { // Comportamento: restituisce attribute
		return this.attribute;
	}

	Object getValue() { // Comportamento: restituisce value
		return this.value;
	}

	@Override
	public String toString() { // Comportamento: restituisce value
		return value.toString();
	}

	abstract double distance(Object a); // L’implementazione sarà diversa per item discreto e item continuo

}
