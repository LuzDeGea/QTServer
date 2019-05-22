package mining;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import data.Data;
import data.Tuple;

class Cluster implements Iterable<Integer>, Comparable<Cluster>, Serializable {

	private final Tuple centroid;
	private Set<Integer> clusteredData = new HashSet<Integer>();

	/*
	 * Cluster(){
	 *
	 * }
	 */

	Cluster(final Tuple centroid) {
		this.centroid = centroid;
	}

	Tuple getCentroid() {
		return centroid;
	}

	// return true if the tuple is changing cluster
	boolean addData(final int id) {
		return clusteredData.add(id);

	}

	// verifica se una transazione è clusterizzata nell'array corrente
	boolean contain(final int id) {
		return this.clusteredData.contains(id);
	}

	// remove the tuplethat has changed the cluster
	void removeTuple(final int id) {
		clusteredData.remove(id);

	}

	int getSize() {
		return this.clusteredData.size();
	}

	@Override
	public String toString() {
		String str = "Centroid=(";
		for (int i = 0; i < centroid.getLength(); i++) {
			str += centroid.get(i);
		}
		str += ")";
		return str;

	}

	public String toString(final Data data) {
		String str = "Centroid=(";
		for (int i = 0; i < centroid.getLength(); i++) {
			str += centroid.get(i) + " ";
		}
		str += ")\nExamples:\n";
		// Errore ????
		for (Integer i : clusteredData) {
			str += "[";
			for (int j = 0; j < data.getNumberOfAttributes(); j++) {
				str += data.getAttributeValue(i, j) + " ";
			}

			str += "] dist=" + getCentroid().getDistance(data.getItemSet(i)) + "\n";
		}
		str += "\nAvgDistance=" + getCentroid().avgDistance(data, clusteredData);
		return str;
	}

	@Override
	public Iterator<Integer> iterator() {
		return clusteredData.iterator();
	}

	// Il comparatore confronta due cluster
	// in base alla popolosità restituendo
	// -1 oppure +1. Usare l'iteratore dove possibile.
	@Override
	public int compareTo(final Cluster clu) {
		if (clusteredData.size() > clu.getSize()) {
			return 1;
		} else {
			return -1;
		}
	}

}