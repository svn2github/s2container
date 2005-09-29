package examples.autonumber;

public class NumberKeyNotFoundRuntimeException extends RuntimeException {

	private int numberKey_;

	public NumberKeyNotFoundRuntimeException(int numberKey) {
		super(numberKey + " not found");
		numberKey_ = numberKey;
	}
	
	public int getNumberKey() {
		return numberKey_;
	}
}
