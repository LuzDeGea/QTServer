package data;

import java.util.Iterator;
import java.util.Set;

public class Tuple {

	private final Item[] tuple;

	// metodi

	// Input: numero di item che costituirà la tupla
	// Comportamento: costruisce l'oggetto riferito da tuple
	Tuple(final int size) {
		tuple = new Item[size];
	}

	public int getLength() { // Comportamento: restituisce tuple.length
		return this.tuple.length;
	}

	public Item get(final int i) { // Comportamento: restituisce Item in posizione i
		return this.tuple[i];
	}

	void add(final Item c, final int i) { // Comportamento: memorizza c in tuple[i]
		this.tuple[i] = c;
	}

	/*
	 * Comportamento: determina la distanza tra la tupla riferita da obj e la tupla
	 * corrente (riferita da this). La distanza è ottenuta come la somma delle
	 * distanze tra gli item in posizioni eguali nelle due tuple. Fare uso di double
	 * distance(Object a) di Item
	 */
	public double getDistance(final Tuple obj) {
		double somma = 0;

		for (int i = 0; i < tuple.length; i++) {
			somma += get(i).distance(obj.get(i).getValue());
		}
		return somma;
	}

	/*
	 * Comportamento: restituisce la media delle distanze tra la tupla corrente e
	 * quelle ottenibili dalle righe della matrice in data aventi indice in
	 * clusteredData.
	 */
	public double avgDistance(final Data data, final Set<Integer> clusteredData) { // - int clusteredData[]
		double p = 0.0, sumD = 0.0;
		Iterator<Integer> it_cdata = clusteredData.iterator();
		for (int i = 0; i < clusteredData.size(); i++) {
			double d = getDistance(data.getItemSet(it_cdata.next())); // - pos i
			sumD += d;
		}
		p = sumD / clusteredData.size();

		return p;
	}

}