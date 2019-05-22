package mining;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;

import data.Data;
import data.EmptyDatasetException;
import data.Tuple;

public class QTMiner {

	private ClusterSet C;
	private double radius;

	// Metodi

	// Input: raggio dei cluster
	// Comportamento: Crea l'oggetto array riferito da C e inizializza radius
	// con il parametro passato come input
	public QTMiner(final double radius) {
		C = new ClusterSet();
		this.radius = radius;
	}

	// Comportamento: Restituisce una stringa fatta da ciascun centroide
	// dell’insieme dei cluster.
	// toString a 0 parametri.
	@Override
	public String toString() {
		String str = "";
		int i = 1;
		Iterator<Cluster> it_C = C.iterator();
		while (it_C.hasNext()) {
			str += i + ":Centroid=(" + it_C.next().getCentroid() + ")\n";
			i++;
		}
		return str;
	}

	/*
	 * Input: percorso+ nome file Comportamento: Apre il file identificato da
	 * fileName, legge l'oggetto ivi memorizzato e lo assegna a C.
	 */
	public QTMiner(final String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
		C = (ClusterSet) in.readObject();
		in.close();
	}

	/*
	 * Input: percorso+ nome file Comportamento: Apre il file identificato da
	 * fileName e salva l'oggetto riferito da C in tale file.
	 */
	public void salva(final String fileName) throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
		out.writeObject(C);
		out.close();
	}

	// Comportamento: restituisce C
	public ClusterSet getC() {
		return C;
	}

	/*
	 * Output: numero di cluster scoperti
	 *
	 * Comportamento: Esegue l’algoritmo QT eseguendo i passi dello pseudo-codice:
	 * 1. Costruisce un cluster per ciascuna tupla (di data) non ancora
	 * clusterizzata, includendo nel cluster i punti (non ancora clusterizzati in
	 * alcun altro cluster) che ricadano nel vicinato sferico della tuple avente
	 * raggio radius da input. Salva il candidato cluster più popoloso e rimuove
	 * tutti punti di tale cluster dall'elenco delle tuple ancora da clusterizzare
	 * 3. Ritorna al passo 1 finchè ci sono ancora tuple da assegnare ad un cluster
	 */
	public int compute(final Data data) throws ClusteringRadiusException, EmptyDatasetException {
		if (data.getNumberOfExamples() == 0) {
			throw new EmptyDatasetException();
		}

		int numclusters = 0;
		final boolean isClustered[] = new boolean[data.getNumberOfExamples()];
		for (int i = 0; i < isClustered.length; i++) {
			isClustered[i] = false;
		}

		int countClustered = 0;
		while (countClustered != data.getNumberOfExamples()) {
			// Ricerca cluster più popoloso
			Cluster c = buildCandidateCluster(data, isClustered);
			C.add(c);
			numclusters++;

			// Rimuovo tuple clusterizzate da dataset
			Iterator<Integer> clusteredTupleId = c.iterator();
			while (clusteredTupleId.hasNext()) {
				isClustered[clusteredTupleId.next()] = true;
			}
			countClustered += c.getSize();
		}
		if (numclusters == 1) {
			throw new ClusteringRadiusException();
		}
		return numclusters;
	}

	/*
	 * Input: insieme di tuple da raggruppare in cluster, informazione booleana
	 * sullo stato di clusterizzazione di una tupla (per esempio
	 * isClustered[i]=false se la tupla i-esima di data non è ancora assegnata ad
	 * alcun cluster di C, true altrimenti)
	 *
	 * Comportamento: costruisce un cluster per ciascuna tupla di data non ancora
	 * clusterizzata in un cluster di C e restituisce il cluster candidato più
	 * popoloso
	 */
	Cluster buildCandidateCluster(final Data data, final boolean isClustered[]) {
		// vettore di Cluster
		int pos = 0;
		final Cluster C[] = new Cluster[data.getNumberOfExamples()];

		for (int i = 0; i < C.length; i++) {
			if (isClustered[i] == false) {
				final Tuple tupla = data.getItemSet(i);
				final Cluster cluster = new Cluster(tupla);

				for (int j = 0; j < C.length; j++) {
					if ((isClustered[j] == false) && (tupla.getDistance(data.getItemSet(j)) <= radius)) {
						cluster.addData(j);
					}
				}
				C[pos] = cluster;
				pos++;
			}
		}

		int max = 0;
		Cluster Cpopoloso = C[0];
		for (int i = 0; i < pos; i++) {
			if (C[i].getSize() > max) {
				max = C[i].getSize();
				Cpopoloso = C[i];
			}
		}
		return Cpopoloso;
	}

}