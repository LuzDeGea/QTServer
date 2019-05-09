package data;

class ContinuousAttribute extends Attribute{

	private double max;  // rappresentano l'estremo superiore del dominio
	private double min ; // rappresentano l'estremo inferiore del dominio

	//Input: nome, identificativo numerico, valore minimo e valore massimo dell'attributo
	//Output: Comportamento: Invoca il costruttore della classe madre e inizializza i membri aggiunti per estensione
	ContinuousAttribute(String name, int index, double min, double max){
		super(name, index);
		this.min=min;
		this.max=max;
	}

	//Input: valore dell'attributo da normalizzare
	//Output: valore normalizzato
	//Comportamento: Calcola e restituisce il valore normalizzato del parametro passato in input. La normalizzazione ha come codominio lo
	//               intervallo [0,1]. La normalizzazione di v è quindi calcolata come segue: v'=(v-min)/(max-min)
	double getScaledValue(double v){
		double v1=((v-min)/(max-min));
		return v1;
	}	
}