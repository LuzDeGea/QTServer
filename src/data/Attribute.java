package data;

abstract class Attribute{

	private String name; // nome simbolico dell'attributo
	private int index; // identificativo numerico dell'attributo

	//Input: nome dell'attributo e identificativo numerico dell'attributo (primo,secondo ... attributo della tupla)
	//Output: -
	//Comportamento: inizializza i valori dei membri name, index
	Attribute(String name, int index){
		this.name=name;
		this.index=index;
	}

	//Input: -
	//Output: nome dell'attributo
	//Comportamento: restituisce name;
	String getName(){return this.name;}

	//Input: -
	//Output: identificativo numerico dell'attributo
	//Comportamento: restituisce index;
	int getIndex(){return this.index;}

	//Input: -
	//Output: stringa rappresentante lo stato dell'oggetto
	//Comportamento: restituisce name;
	public String toString(){return "status:  name:"+name+" index:"+index;}
	
}