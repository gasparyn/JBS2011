package gaspar.junit.example;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;;

public class MyClassTest {

	@Test
	public void testMultiply() {
		MyClass tester = new MyClass();
		assertEquals("Result", 50, tester.multiply(25, 2));
	}
	@Test
	public void testDivision() {
		MyClass tester = new MyClass();
		assertEquals("Result",true , tester.equals("Mama", "Mum"));
	}
	
	/*============================vogueable tests===============*/
	  @Test
	 
	public void testName() {
		User tester = new User("gaspar");
		assertEquals("Result",false , tester.getName().equals("gaspar"));
	}
	@Test
	public void testArrayList() {
		User tester = new User("gaspar");
		assertEquals("Result",false , tester.getWishlists().size()==0);
	}
	@Test
	public void testAddWishlists() {
		User tester = new User("gaspar");
		tester.addWishlist("pawpaw");
		assertEquals("Result", true , tester.getWishlists().contains("pawpaw"));
	}
	@Test
	public void testRemoveWishlists() {
		User tester = new User("gaspar");
		tester.removeWishlist("blackberry");
		assertFalse("Passed", tester.getWishlists().contains("blackberry"));
	}
	
}
				
