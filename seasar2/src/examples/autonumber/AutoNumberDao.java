package examples.autonumber;

public interface AutoNumberDao {

	public int increment(int numberKey);
	
	public int getCurrentNumber(int numberKey);
}
