package data;

class ContinuousItem extends Item {

	// metodi

	// Comportamento: richiama il costruttore della super classe
	ContinuousItem(final Attribute attribute, final Double value) {
		super(attribute, value);
	}

	/*
	 * Comportamento: Determina la distanza (in valore assoluto) tra il valore
	 * scalato memorizzato nello item corrente (this.getValue()) e quello scalato
	 * associato al parametro a. Per ottenere valori scalati fare uso di
	 * getScaledValue(...)
	 */
	@Override
	double distance(final Object a) {
		// TODO
		return 0;
	}
	// return Math.abs(this.getValue() - ContinuousAttribute.getScaledValue(a));

}
