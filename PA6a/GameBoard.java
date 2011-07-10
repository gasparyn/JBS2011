package gaspar.pa6;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/**
 * initialise the game board
 * @author gasparobimba
 *
 */
public class GameBoard {
	private Map<String,String> map=new HashMap<String,String>();
	int row=8,col=9,count=0;
	//constructor
	public GameBoard(){
		System.out.println("DISPLAY BOARD");	
	}			
/**
 * method creates a map of countries to their capital cities
 * This case has countries from Africa and the Americas
 * @return -hashmap of country=>capital
 */
  public Map<String, String> getMap(){
		map.put("Algeria", "Algiers");
		map.put("Angola","Luanda");
		map.put("Benin","Porto Novo, Cotonou");
		map.put("Botswana","Gaborone");
		map.put("Burkina Faso","Ouagadougou");
		map.put("Burundi","Bujumbura");
		map.put("Cameroon (also spelled Cameroun)","Yaoundé");
		map.put("Central African Republic (Centrafrique)","Bangui");
		map.put("Chad (Tchad)","N'Djamena");
		map.put("Comoros","Moroni");
		map.put("Republic of the Congo","Brazzaville");
		map.put("Democratic Republic of the Congo","Kinshasa");
		map.put("Côte d'Ivoire (Ivory Coast)","Yamoussoukro");
		map.put("Djibouti","Djibouti");
		map.put("Egypt (Misr)","Cairo");
		map.put("Ethiopia (Itiopia)","Addis Ababa");
		map.put("Gabon","Libreville");
		map.put("The Gambia","Banjul");
		map.put("Ghana","Accra");
		map.put("Guinea","Conakry");
		map.put("Guinea-Bissau","Bissau");
		map.put("Kenya","Nairobi");
		map.put("Lesotho","Maseru");
		map.put("Liberia","Monrovia");
		map.put("Libya","Tripoli");
		map.put("Madagascar","Antananarivo");
		map.put("Malawi","Lilongwe");
		map.put("Mali","Bamako");
		map.put("Mauritania","Nouakchott");
		map.put("Mauritius","Port Louis");
		map.put("Morocco (Al Maghrib)","Rabat");
		map.put("Mozambique","Maputo");
		map.put("Namibia","Windhoek");
		map.put("Niger","Niamey");
		map.put("Nigeria","Abuja");
		map.put("Rwanda","Kigali");
		map.put("Senegal","Dakar");
		map.put("Sierra Leone","Freetown");
		map.put("Somalia","Mogadishu");
		map.put("South Africa","Cape Town, Pretoria, Bloemfontein");
		map.put("Sudan","Khartoum");
		map.put("Swaziland","Mbabane");
		map.put("Tanzania","Dar es Salaam, Dodoma");
		map.put("Togo","Lome");
		map.put("Tunisia","Tunis");
		map.put("Uganda","Kampala");
		map.put("Western Sahara *DISPUTED*","La'youn");
		map.put("Zambia","Lusaka");
		map.put("Zimbabwe","Harare");
		map.put("Antigua and Barbuda","St. Johns");
		map.put("Bahamas","Nassau");
		map.put("Barbados","Bridgetown");
		map.put("Belize","Belmopan");
		map.put("Canada","Ottawa");
		map.put("Costa Rica","San José");
		map.put("Cuba","Havana");
		map.put("Dominica","Roseau");
		map.put("Dominican Republic (Republica Dominicana)","Santo Domingo");
		map.put("El Salvador","San Salvador");
		map.put("Greenland","Nuuk");
		map.put("Grenada","St Georges");
		map.put("Guatemala","Guatemala");
		map.put("Haiti - Port-au","Prince");
		map.put("Honduras","Tegucigalpa");
		map.put("Jamaica","Kingston, Jamaica");
		map.put("Mexico (México)","Mexico City");
		map.put("Nicaragua","Managua");
		map.put("Panama (Panamá)","Panama City");
		map.put("Saint Kitts and Nevis","Basseterre");
		map.put("Saint Vincent and the Grenadines","Kingstown");
		map.put("Trinidad and Tobago","Port of Spain");
		map.put("United States","Washington DC");
		
	return map;
        
  }
  /**
   * method that draws the board of the game
   * @return - array/matrix containing the board
   */
  public String[][] getBoard(){
		ArrayList<String> country=new ArrayList<String>(getMap().keySet());
		ArrayList<String> capital=new ArrayList<String>(getMap().values());
	
		//draw board
		
		String A[][]=new String[1000][1000];
		Collections.shuffle(capital);
		Collections.shuffle(country);
		count=country.size()-1;
		

		for (int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				if(i==j)
					A[i][j]=country.get(count);
				else if(i+j==9)
					A[i][j]=capital.get(count);
				else if(i%2==0)
					A[i][j]=country.get(count);
				else
					A[i][j]=capital.get(count); 
				//System.out.print(A[i][j].toString()+"\t");
			}
			count--;
		}
		return A;	
	}
  /**
   * display the board to the screen
   */
public void display() {
	// TODO Auto-generated method stub
	for (int i=0;i<row;i++){
		for(int j=0;j<col;j++){
			System.out.print(this.getBoard()[i][j]+"\t\t");
		} System.out.println();
	}
}
}

