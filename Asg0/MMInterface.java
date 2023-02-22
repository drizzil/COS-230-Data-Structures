public interface MMInterface {
	void init(int size);
	void malloc(int id, int size) throws MyOutOfMemoryException;
	void free(int id) throws MyInvalidMemoryException;
	void print();
}
