package mining;

import data.Data;

public class ClusterSet {

	private Cluster C[] = new Cluster[0];

	// Metodi

	ClusterSet() {
	}

	void add(Cluster c) {
		final Cluster tempC[] = new Cluster[C.length + 1];
		for (int i = 0; i < C.length; i++) {
			tempC[i] = C[i];
		}
		tempC[C.length] = c;
		C = tempC;
	}

	// Comportamento: restituisce C[i]
	Cluster get(int i) {
		return C[i];
	}

	// Comportamento: Restituisce una stringa fatta da ciascun centroide
	// dell’insieme dei cluster.
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < C.length; i++) {
			if (C[i] != null) {
				str += i + ":" + C[i].getCentroid() + "\n";
			}
		}
		return str;
	}

	// Comportamento: Restituisce una stringa che descriva lo stato di
	// ciascun cluster in C
	public String toString(Data data) {
		String str = "";
		for (int i = 0; i < C.length; i++) {
			if (C[i] != null) {
				str += i + ":" + C[i].toString(data) + "\n";
			}
		}
		return str;
	}

}