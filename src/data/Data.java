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
		// tuple
		data[0] = new String[] { "Sunny", "Hot", "High", "Weak", "No" };
		data[1] = new String[] { "Sunny", "Hot", "High", "Strong", "No" };
		data[2] = new String[] { "Overcast", "Hot", "High", "Weak", "Yes" };
		data[3] = new String[] { "Rain", "Mild", "High", "Weak", "Yes" };
		data[4] = new String[] { "Rain", "Cool", "Normal", "Weak", "Yes" };
		data[5] = new String[] { "Rain", "Cool", "Normal", "Strong", "No" };
		data[6] = new String[] { "Overcast", "Cool", "Normal", "Strong", "Yes" };
		data[7] = new String[] { "Sunny", "Mild", "High", "Weak", "No" };
		data[8] = new String[] { "Sunny", "Cool", "Normal", "Weak", "Yes" };
		data[9] = new String[] { "Rain", "Mild", "Normal", "Weak", "Yes" };
		data[10] = new String[] { "Sunny", "Mild", "Normal", "Strong", "Yes" };
		data[11] = new String[] { "Overcast", "Mild", "High", "Strong", "Yes" };
		data[12] = new String[] { "Overcast", "Hot", "Normal", "Weak", "Yes" };
		data[13] = new String[] { "Rain", "Mild", "High", "Strong", "No" };

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

		final String temperatureValue[] = new String[3];
		temperatureValue[0] = "cool";
		temperatureValue[1] = "hot";
		temperatureValue[2] = "smild";
		attributeSet.add(1, new DiscreteAttribute("Temperature", 1, temperatureValue));

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

	// Input: indice di riga
	// Comportamento: Crea e restituisce un oggetto di Tuple che modella
	// come sequenza di coppie Attributo-valore la i-esima riga in data
	public Tuple getItemSet(final int index) {
		final Tuple tuple = new Tuple(attributeSet.size());
		for (int i = 0; i < attributeSet.size(); i++) {
			tuple.add(new DiscreteItem((DiscreteAttribute) attributeSet.get(i), (String) data[index][i]), i);
		}
		return tuple;
	}

	public static void main(final String args[]) {
		final Data trainingSet = new Data();
		System.out.println(trainingSet);
	}

}