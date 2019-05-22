package data;

import java.util.LinkedList;
import java.util.List;

public class Data {

	// una matrice nXm di tipo Object dove ogni riga modella una transazioni
	private final Object data[][];
	// cardinalità dell’insieme di transazioni (numero di righe in data)
	private final int numberOfExamples;
	// un vettore degli attributi in ciascuna tupla (schema della tabella di dati)
	private List<Attribute> attributeSet;

	public Data() {
		data = new Object[14][5];
		// inserimento tuple
		data[0] = new Object[] { "Sunny", 30.3, "High", "Weak", "No" };
		data[1] = new Object[] { "Sunny", 30.3, "High", "Strong", "No" };
		data[2] = new Object[] { "Overcast", 30d, "High", "Weak", "Yes" };
		data[3] = new Object[] { "Rain", 13d, "High", "Weak", "Yes" };
		data[4] = new Object[] { "Rain", 0d, "Normal", "Weak", "Yes" };
		data[5] = new Object[] { "Rain", 0d, "Normal", "Strong", "No" };
		data[6] = new Object[] { "Overcast", 0.1, "Normal", "Strong", "Yes" };
		data[7] = new Object[] { "Sunny", 13d, "High", "Weak", "No" };
		data[8] = new Object[] { "Sunny", 0.1, "Normal", "Weak", "Yes" };
		data[9] = new Object[] { "Rain", 12d, "Normal", "Weak", "Yes" };
		data[10] = new Object[] { "Sunny", 12.5, "Normal", "Strong", "Yes" };
		data[11] = new Object[] { "Overcast", 12.5, "High", "Strong", "Yes" };
		data[12] = new Object[] { "Overcast", 29.21, "Normal", "Weak", "Yes" };
		data[13] = new Object[] { "Rain", 12.5, "High", "Strong", "No" };

		numberOfExamples = 14; // numberOfExamples

		attributeSet = new LinkedList<Attribute>(); // explanatory Set

		// avvalorare ciascune elemento di attributeSet con un oggetto della classe
		// DiscreteAttribute che modella il corrispondente attributo (e.g. outlook,
		// temperature,etc)
		// nel seguito si fornisce l'esempio per outlook
		final String outLookValues[] = new String[3];
		outLookValues[0] = "overcast";
		outLookValues[1] = "rain";
		outLookValues[2] = "sunny";
		attributeSet.add(0, new DiscreteAttribute("Outlook", 0, outLookValues));

		// dove 0 rappresenta la temperaltura minima osservata in Data e 30.3
		// rappresenta la temperatura massima
		final String temperatureValue[] = new String[3];
		temperatureValue[0] = "cool";
		temperatureValue[1] = "hot";
		temperatureValue[2] = "smild";
		attributeSet.add(1, new ContinuousAttribute("Temperature", 1, 0, 30.3));

		final String HumidityValues[] = new String[2];
		HumidityValues[0] = "high";
		HumidityValues[1] = "normal";
		attributeSet.add(2, new DiscreteAttribute("Humidity", 2, HumidityValues));

		final String windValues[] = new String[2];
		windValues[0] = "weak";
		windValues[1] = "strong";
		attributeSet.add(3, new DiscreteAttribute("Wind", 3, windValues));

		final String playTennisValues[] = new String[2];
		playTennisValues[0] = "yes";
		playTennisValues[1] = "no";
		attributeSet.add(4, new DiscreteAttribute("PlayTennis", 4, playTennisValues));
	}

	public int getNumberOfExamples() {
		// Input: -
		// Output: cardinalità dell'insieme di transazioni
		// Comportamento: restituisce numberOfExamples
		return this.numberOfExamples;
	}

	public int getNumberOfAttributes() {
		// Input: -
		// Output: cardinalità dell'insieme degli attributi
		// Comportamento: restituisce la dimensione di explanatorySet
		return attributeSet.size();
	}

	public Object getAttributeValue(final int exampleIndex, final int attributeIndex) {
		// Input: indice di riga , indice di colonna in riferimento alla matirce
		// memorizzata in data
		// Output: valore assunto in data dall'attributo in posizione attributeIndex,
		// nella riga in posizione exampleIndex
		// Comportamento: restituisce data[exampleIndex][attributeIndex]
		return data[exampleIndex][attributeIndex];
	}

	public Attribute getAttribute(final int index) {
		return attributeSet.get(index);
	}

	public List<Attribute> getAttributeSchema() {
		return attributeSet;
	}

	@Override
	public String toString() {
		// Input: -
		// Output: stringa che modella lo stato dell'oggetto
		// Comportamento: crea una stringa in cui memorizza lo schema della tabella
		// (vedi explanatorySet) e le transazioni memorizzate in data,
		// opportunamente enumerate. Restituisce tale stringa
		String schema = "";
		for (int i = 0; i < getNumberOfAttributes(); i++) {
			schema += getAttribute(i).getName() + "\t";
		}
		schema += "\n";
		for (int i = 0; i < getNumberOfExamples(); i++) {
			schema += i + " : ";
			for (int j = 0; j < getNumberOfAttributes(); j++) {
				schema += getAttributeValue(i, j) + "\t";
			}
			schema += "\n";
		}
		return schema;
	}

	/*
	 * Input: indice di riga Comportamento: Crea e un istanza di Tuple che modelli
	 * la transazione con indice di riga index in data. Restituisce il riferimento a
	 * tale istanza. Usare lo RTTI per distinguere tra ContinuousAttribute e
	 * DiscreteAttribute (e quindi creare nella tupla un ContinuousItem o un
	 * DiscreteItem)
	 */
	public Tuple getItemSet(final int index) {
		final Tuple tuple = new Tuple(attributeSet.size());
		for (int i = 0; i < attributeSet.size(); i++) {
			if (attributeSet.get(i) instanceof DiscreteAttribute) {
				tuple.add(new DiscreteItem((DiscreteAttribute) attributeSet.get(i), (String) data[index][i]), i);
			} else {
				tuple.add(new ContinuousItem(attributeSet.get(i), (double) data[index][i]), i);
			}
		}
		return tuple;
	}

	public static void main(final String args[]) {
		final Data trainingSet = new Data();
		System.out.println(trainingSet);
	}

}