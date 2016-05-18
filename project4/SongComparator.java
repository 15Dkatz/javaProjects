import java.util.Comparator;

class SongComparator implements Comparator<Object> {
	int columnToSort;
	SongComparator() {

	}
	//overriding compare method
	public int compare(Object o1, Object o2) {
		String[] row1 = (String[]) o1;
		String[] row2 = (String[]) o2;
		//compare the songs by title: row 0 
		return row1[0].compareTo(row2[0]);
	}
	
	
	
}