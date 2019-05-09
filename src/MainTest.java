import data.Data;
import keyboardinput.Keyboard;
import mining.ClusteringRadiusException;
import mining.EmptyDatasetException;
import mining.QTMiner;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		char continuare = 'x';
		do {
			double radius = 0;

			final Data data = new Data();
			System.out.println(data);
			// final double radius = 2.0;
			do {
				System.out.println("Insert radius (>0)=");
				radius = Keyboard.readDouble();
			} while (radius <= 0);

			final QTMiner qt = new QTMiner(radius);
			try {
				int numIter = qt.compute(data);
				System.out.println("Number of clusters:" + numIter);
				System.out.println(qt.getC().toString(data));
			} catch (ClusteringRadiusException e) {
				System.out.println(data.getNumberOfExamples() + " tuples in one cluster! ");
			} catch (EmptyDatasetException e) {
				System.out.println("error 404, tuples not found");
			}
			do {
				System.out.println("New execution?(y/n) ");
				continuare = Keyboard.readChar();
			} while ((continuare != 'y') && (continuare != 'n'));
		} while (continuare != 'n');
		System.out.println("Esecuzione Terminata.");
	}
}
