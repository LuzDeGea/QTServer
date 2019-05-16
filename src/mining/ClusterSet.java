package mining;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import data.Data;

public class ClusterSet implements Iterable<Cluster> {

	private Set<Cluster> C = new TreeSet<Cluster>();

	// Metodi

	ClusterSet() {
	}

	// aggiunge a C il cluster c
	void add(final Cluster c) {
		C.add(c);
	}

	// Comportamento: Restituisce una stringa fatta da ciascun centroide
	// dell’insieme dei cluster.
	// toString a 0 parametri.
	@Override
	public String toString() {
		String str = "";
		int i = 0;
		Iterator<Cluster> it_C = C.iterator();
		while (it_C.hasNext()) {
			str += i + ":" + it_C.next().getCentroid() + "\n";
			i++;
		}
		return str;
	}

	// Comportamento: Restituisce una stringa che descriva lo stato di
	// ciascun cluster in C
	public String toString(final Data data) {
		String str = "";
		int i = 1;
		Iterator<Cluster> it_C = C.iterator();
		while (it_C.hasNext()) {
			// stampa: Centroid=(...).
			str += i + ":" + it_C.next().toString(data) + "\n";
			i++;
		}
		return str;
	}

	@Override
	public Iterator<Cluster> iterator() {
		return this.C.iterator();
	}

}