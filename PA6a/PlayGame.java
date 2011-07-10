package gaspar.pa6;

import java.util.Scanner;
/**
 * Game Loop
 * Play game method allows a user to start playing this wonderful game
 * @author gasparobimba
 *
 */
public class PlayGame {
	String name;
	boolean keepOn=true;
	int score=0;
	GameBoard board =new GameBoard();
	Scanner in =new Scanner(System.in);

	//constructor for the game, takes in player name
	public PlayGame(String name){
		this.name=name;
		// not so well implemented game loop
		while(keepOn!=false){
			/* ask user for input*/
			System.out.println("please enter first card coordinates,( x1 return then y1)");
			int x1=in.nextInt();  	//x coord
			int y1=in.nextInt();	//y coord

			System.out.println("please enter first card coordinates, x2 then y2");
			int x2=in.nextInt();	//x coord
			int y2=in.nextInt();	//y coord
			
			//check if input is within range
			if(x2<0||x2>8||y2<0||y2>8||x1<0||x1>8||x1<0||x1>8){
				System.out.println("Invalid entry");
			}
			
			//if in range of board
			else{
				String qsn=board.getBoard()[x1][y1];
				String ans=board.getBoard()[x2][y2];
				if (qsn!=null&&ans!=null){
					
					//check if the question is answered
					if (board.getMap().get(ans)==(qsn)||board.getMap().get(qsn)==(ans)){
						//if so remove the two cards from board
						board.getBoard()[x1][y1]=null;
						score++;//player wins
						System.out.println("Hooray, you are good");
						System.out.println(name+" ,Your Score: "+score);

					}
					else	//if there wasnt a match
					{
						System.out.println("oops,you lost");
						score--;	//player loses
						System.out.println(name+" ,Your Score: "+score);
						System.out.println("Keep playing? [1/0]");
						int yep=in.nextInt();
						//ask player to play again
						if(yep!=1||score<-50){
							System.out.println("Game OVER! Good luck next time!");
							keepOn=false;
						}

					}//end no match

				}//end if question not null
			}//end in range
		}
	}
}
