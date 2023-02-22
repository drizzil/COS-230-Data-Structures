public class QueueEmptyException extends Exception {
	public QueueEmptyException() {
		super("Error: Queue Empty.");
	}
}