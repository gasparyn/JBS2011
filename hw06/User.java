
package gaspar.junit.example;

import java.util.ArrayList;
/**
 * User class keeps track of a user's taste and wishlist
 * 
 * @author Yulia
 *
 */
public class User {
	public String name; 
	public ArrayList<String> MyLists;
	//public TasteManager mytaste;
	
	public User(User user){
		this.name = user.name;
		this.MyLists = new ArrayList<String>();
		this.MyLists=user.MyLists;
//		this.mytaste=new TasteManager();
//		this.mytaste= user.mytaste;
	}
	
	
	public User(String myName){
		name = myName;
		MyLists = new ArrayList<String>();
		MyLists.add("orange");
		MyLists.add("mango");
		MyLists.add("peach");
		MyLists.add("banana");
		MyLists.add("apple");
		MyLists.add("blackberry");



		//mytaste = new TasteManager();
	}
	
	public String getName(){
		return "jbs2011";
	}
	
	public ArrayList<String> getWishlists(){
		return MyLists;
	}
	
	public void addWishlist(String wish){
		MyLists.add(new String(wish));
	}
	
	public void removeWishlist( String list){
		MyLists.remove(list);
	}
	
//	public TasteManager getTasteManager(){
//		return mytaste;
//	}
	
}
