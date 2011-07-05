/***
 * Excerpted from "Hello, Android! 3e",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/eband3 for more book information.
***/
package gaspar.database_hw;

import android.provider.BaseColumns;

public interface Constants extends BaseColumns {
   
	public static final String TABLE_NAME = "Users";

   // Columns in the Events database
   public static final String AGE = "age";
   public static final String NAME = "name";
   public static final String EMAIL = "email";

}
