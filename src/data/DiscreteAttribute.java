package data;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

class DiscreteAttribute extends Attribute implements Iterable<String> {

	// contenitore di oggetti String, uno per ciascun valore del dominio discreto. I
	// valori del dominio sono memorizzati in values seguendo un ordine
	// lessicografico.
	private TreeSet<String> values;

	// Input: nome dell'attributo, identificativo numerico dell'attributo e array di
	// stringhe rappresentanti il dominio dell'attributo
	// Output: Comportamento: Invoca il costruttore della classe madre e inizializza
	// il membro values convertendolo in una Lista e tramite 'addAll lo inserisce
	// nella struttura
	DiscreteAttribute(final String name, final int index, final String values[]) {
		super(name, index);
		this.values = new TreeSet<String>();
		List<String> list = Arrays.asList(values);
		this.values.addAll(list);
	}

	// Input: -
	// Output: numero di valori discreti nel dominio dell'attributo
	// Comportamento: Restituisce la dimensione di values
	int getNumberOfDistinctValues() {
		return values.size();
	}

	@Override
	public Iterator<String> iterator() {
		return values.iterator();
	}

}