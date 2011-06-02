public class mazeTester {

	static String test1="111111111100010001111010101100010101101110101100000101111011101100000101111111111",
	test2="11111111110001001111010101100110101101110101100000101111011101100000101111111111",
	test3="111111111100010101111010101100010101101110101100000101111011101100000101111111111";

	public static void main(String[] args) {
		maze t= new maze(9,9);
		t.load(test1);
		t.display();
		if(t.solve(1, 1, 7, 7) ){
			System.out.println("Path traced successfully");
			t.trace(1, 1, 7, 7);
		}
		else
			System.out.println("Did not find any path");

	}
}

