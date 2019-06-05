package data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import database.DatabaseConnectionException;
import database.DbAccess;
import database.EmptySetException;
import database.Example;
import database.TableSchema;
import database.Table_Data;

public class Data {

	// una matrice nXm di tipo Object dove ogni riga modella una transazioni
	private List<Example> data = new ArrayList<Example>();
	// cardinalità dell’insieme di transazioni (numero di righe in data)
	private int numberOfExamples;
	// un vettore degli attributi in ciascuna tupla (schema della tabella di dati)
	private List<Attribute> attributeSet = new LinkedList<Attribute>();

	// costruttore (con nome della tabella)
	public Data(final String T_name) {
		DbAccess access = new DbAccess();
		try {
			access.initConnection();
		} catch (DatabaseConnectionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Table_Data dataSet = new Table_Data(access);

		try {
			data = dataSet.getDistinctTransazioni(T_name);
		} catch (SQLException | EmptySetException e) {
			// TODO Auto-generated catch block
		}

		numberOfExamples = data.size();

		try {
			TableSchema dataSchema = new TableSchema(access, T_name);
			for (int i = 0; i < dataSchema.getNumberOfAttributes(); i++) {
				if (dataSchema.getColumn(i).isNumber()) {
					attributeSet.add(new ContinuousAttribute(dataSchema.getColumn(i).getColumnName(), i,
							(float) dataSet.getAggregateColumnValue(T_name, dataSchema.getColumn(i),
									database.QUERY_TYPE.MIN),
							(float) dataSet.getAggregateColumnValue(T_name, dataSchema.getColumn(i),
									database.QUERY_TYPE.MAX)));
				} else {
					Set<Object> valueSet = dataSet.getDistinctColumnValues(T_name, dataSchema.getColumn(i));

					String[] ary = new String[valueSet.size()];
					int x = 0;
					for (Object j : valueSet) {
						ary[x] = (String) j;
						x++;
					}
					attributeSet.add(new DiscreteAttribute(dataSchema.getColumn(i).getColumnName(), i, ary));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		access.closeConnection();
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

	public Object getAttributeValue(final int exampleIndex, final int dataIndex) {
		return data.get(exampleIndex).get(dataIndex);
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
				tuple.add(
						new DiscreteItem((DiscreteAttribute) attributeSet.get(i), (String) getAttributeValue(index, i)),
						i);
			} else {
				tuple.add(new ContinuousItem(attributeSet.get(i), (double) getAttributeValue(index, i)), i);
			}
		}
		return tuple;
	}

	/*
	 * public static void main(final String args[]) { final Data trainingSet = new
	 * Data(); System.out.println(trainingSet); }
	 */

}