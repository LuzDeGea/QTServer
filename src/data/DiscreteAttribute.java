package data;

class DiscreteAttribute extends Attribute{

	// array di oggetti String, uno per ciascun valore del dominio discreto. I valori del dominio sono memorizzati in values seguendo un ordine lessicografico.
	private String values[ ]; 

	//Input: nome dell'attributo, identificativo numerico dell'attributo e array di stringhe rappresentanti il dominio dell'attributo
	//Output: Comportamento: Invoca il costruttore della classe madre e inizializza il membro values con il parametro in input.
	DiscreteAttribute(String name, int index, String values[ ]){
		super(name, index);
		this.values=values.clone();
	}

	//Input:  -
	//Output: numero di valori discreti nel dominio dell'attributo 
	//Comportamento: Restituisce la dimensione di values
	int getNumberOfDistinctValues(){return values.length;}

	//Input:  posizione di un valore in values
	//Output: valore discreto in posizione “i” di values
	//Comportamento: Restituisce values[i]
	String getValue(int i){		
		return values[i];
	}
}