import java.util.Iterator;

public class HashTableIterator<K, V> implements Iterator<K>{
	
	HashTable<K, V> ttbl;
	SortedList<K, V> srtd;
	int idx; 
	
	public HashTableIterator(HashTable<K, V> ttbl) {
		this.ttbl = ttbl; 
		idx = 0; 
	} 
	  
	public HashTableIterator(SortedList<K, V> sortedList) {
		//this.ttbl = ttbl; 
		idx = 0; 
	}

	public boolean hasNext(){
		boolean rtn = false; 
		int cur = idx; 
		while(cur < ttbl.tbl.length && ttbl.tbl[idx]==null) {
			cur++; 
		}
		if(cur < ttbl.tbl.length) {
			rtn = true; 
		}
		
		return rtn; 
	} 
	 
	  
	public K next() { 
		K tmp = null; 
		System.out.println(idx);
		System.out.println(ttbl.tbl[idx].key);
		while(idx < ttbl.tbl.length && ttbl.tbl[idx]==null) {
			idx++; 
		}
		tmp = ttbl.tbl[idx].key; 
		idx++; //move past the item we are returning 
		   
		return tmp; 
	} 
	 
}