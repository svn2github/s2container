package examples.autonumber;

public interface AutoNumber {

	public int next(int numberKey) throws NumberKeyNotFoundRuntimeException;
}
