
public class TestKlasse {
	public static void main(String[] args){
		String test = "<freigabe>Test</freigabe>";
		String[] testArray = test.split("<freigabe>|</freigabe>");
		for(String s : testArray)
			System.out.println("Element:" + s);
	}
}
