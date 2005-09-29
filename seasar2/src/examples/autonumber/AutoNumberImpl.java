package examples.autonumber;


public class AutoNumberImpl implements AutoNumber {

	private AutoNumberDao autoNumberDao_;

	public AutoNumberImpl(AutoNumberDao autoNumberDao) {
		autoNumberDao_ = autoNumberDao;
	}

	public int next(int numberKey) throws NumberKeyNotFoundRuntimeException {
		int updateCount = autoNumberDao_.increment(numberKey);
		if (updateCount == 1) {
			return autoNumberDao_.getCurrentNumber(numberKey);
		}
		throw new NumberKeyNotFoundRuntimeException(numberKey);
	}
}
