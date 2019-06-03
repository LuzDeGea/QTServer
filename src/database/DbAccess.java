package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbAccess {

	// attributi
	/*
	 * (Per utilizzare questo Driver scaricare e aggiungere al classpath il
	 * connettore mysql connector)
	 */
	private String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	private final String DBMS = "jdbc:mysql";
	/*
	 * contiene l�identificativo del server su cui risiede la base di dati (per
	 * esempio localhost)
	 */
	private final String SERVER = "localhost";
	// contiene il nome della base di dati
	private final String DATABASE = "MapDB";
	// La porta su cui il DBMS MySQL accetta le connessioni
	private final String PORT = "3306";
	// contiene il nome dell�utente per l�accesso allabase di dati
	private final String USER_ID = "MapUser";
	/*
	 * contiene la password di autenticazione per l�utente identificato da USER_ID
	 */
	private final String PASSWORD = "map";
	// gestisce una connessione
	private Connection conn;

	// metodi
	/*
	 * impartisce al class loader l�ordine di caricare il driver mysql, inizializza
	 * la connessione riferita da conn. Il metodo solleva e propaga una eccezione di
	 * tipo DatabaseConnectionException in caso di fallimento nella connessione al
	 * database. Suggerimento: La stringa di connessione sar�: DBMS+"://" + SERVER +
	 * ":" + PORT + "/" + DATABASE
	 */
	public void initConnection() throws DatabaseConnectionException {
		try {
			Class.forName(DRIVER_CLASS_NAME).newInstance();
		} catch (Exception ex) {
			System.out.println("Driver error.");
		}

		try {
			this.conn = DriverManager.getConnection(DBMS + "://" + SERVER + ":" + PORT + "/" + DATABASE + "?user="
					+ USER_ID + "&password=" + PASSWORD + "&serverTimezone=UTC");
		} catch (SQLException e) {
			System.out.println("DBSM non trovato.");
		}
	}

	// restituisce conn;
	public Connection getConnection() {
		return this.conn;
	}

	// chiude la connessione conn;
	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("chiusura DBSM non riuscita.");
		}
	}

}
