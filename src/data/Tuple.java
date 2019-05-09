package data;

public class Tuple {

	private final Item[] tuple;

	// metodi

	// Input: numero di item che costituirà la tupla
	// Comportamento: costruisce l'oggetto riferito da tuple
	Tuple(int size) {
		tuple = new Item[size];
	}

	public int getLength() { // Comportamento: restituisce tuple.length
		return this.tuple.length;
	}

	public Item get(int i) { // Comportamento: restituisce Item in posizione i
		return this.tuple[i];
	}

	void add(Item c, int i) { // Comportamento: memorizza c in tuple[i]
		this.tuple[i] = c;
	}

	/*
	 * Comportamento: determina la distanza tra la tupla riferita da obj e la tupla
	 * corrente (riferita da this). La distanza è ottenuta come la somma delle
	 * distanze tra gli item in posizioni eguali nelle due tuple. Fare uso di double
	 * distance(Object a) di Item
	 */
	public double getDistance(Tuple obj) {
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
	public double avgDistance(Data data, int clusteredData[]) {
		double p = 0.0, sumD = 0.0;
		for (int i = 0; i < clusteredData.length; i++) {
			final double d = getDistance(data.getItemSet(clusteredData[i]));
			sumD += d;
		}
		p = sumD / clusteredData.length;

		return p;
	}

}