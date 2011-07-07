
package gaspar.pa5;
import java.util.Scanner;


public class MainGame{
	/* read input from keyboard*/
	private static Scanner input = new Scanner(System.in);

	/**
	 * The main method prints instructions and lets the user play the game
	 * The tree grows as the user keeps playing
	 * 
	 **/
	public static void main(String[ ] args){
		BinaryTreeNode<String> root;
		/*Now ask the player some questions*/
		System.out.println("Please think of an animal......");
		System.out.println(" I will guess what animal you thinking of");

		/*set up a default root with two children
		 *1. set up the root question
		 */
		root = beginningTree( );
		do
			playGame(root);
		while (ask("Repeat Play?"));

		System.out.println("Great, You Taught me a lot");
	}



	public static void playGame(BinaryTreeNode<String> current){
		while (!current.isLeaf( ))
		{
			if (ask(current.getData( )))
				current = current.getLeft( );
			else
				current = current.getRight( );
		}

		System.out.print("you are thinking of " + current.getData( ) + ". ");
		if (!ask("true?"))
			buildTree(current);
		else
			System.out.println("hahaha, I can actually read your mind!");
	}


	/**
	 * Construct a small taxonomy tree with four animals.
	 * @author - Martin
	 * @author -modified by gaspar obimba to suit needs for PA5
	 **/
	public static BinaryTreeNode<String> beginningTree( )   
	{
		BinaryTreeNode<String> root;
		BinaryTreeNode<String> child;


		// Create the root node with the question “Are you a mammal?”
		root = new BinaryTreeNode<String>("are you a mammal", null, null);

		// Create and attach the left subtree.
		child = new BinaryTreeNode<String>("are you bigger than a cat?", null, null);
		child.setLeft(new BinaryTreeNode<String>("kangaroo", null, null));
		child.setRight(new BinaryTreeNode<String>("mouse", null, null));
		root.setLeft(child);

		// Create and attach the right subtree.
		child = new BinaryTreeNode<String>("do you live under water?", null, null);
		child.setLeft(new BinaryTreeNode<String>("trout", null, null));
		child.setRight(new BinaryTreeNode<String>("robin", null, null));
		root.setRight(child);

		return root;
	}


	/**
	 * 
	 * @param current
	 */
	public static void buildTree(BinaryTreeNode<String> current)

	//So the user asks a question about an animal and then after the game, if the answer guess was wrong, the tree is updated
	//else the user wins

	{
		String guess;   // The animal that was just guessed
		String actual; // The animal that the user was thinking of
		String NewQuestionToDistinguish;   // A question to distinguish the two animals

		// Set Strings for the guessed animal, correct animal and a new question.
		guess = current.getData( );
		System.out.println("You WON. What animal were you? ");
		actual = input.nextLine( );
		System.out.println("Please type a simple yes/no question that will distinguish a\n"+actual + " from a " + guess + ".");
		NewQuestionToDistinguish = input.nextLine( );

		// Put the new question in the current node, and add two new children.
		current.setData(NewQuestionToDistinguish);
		System.out.println("As a " + actual + ", " + NewQuestionToDistinguish);
		if (ask("Please answer"))
		{
			current.setLeft(new BinaryTreeNode<String>(actual, null, null));
			current.setRight(new BinaryTreeNode<String>(guess, null, null));
		}
		else
		{
			current.setLeft(new BinaryTreeNode<String>(guess, null, null));
			current.setRight(new BinaryTreeNode<String>(actual, null, null));
		}         
	}

	public static boolean ask(String prompt){
		String answer;
		System.out.print(prompt + " yes/no: ");
		answer = input.nextLine( ).toUpperCase( );
		while (!answer.startsWith("Y") && !answer.startsWith("N")){
			System.out.print("Invalid response. Please type Y or N: ");
			answer = input.nextLine( ).toUpperCase( );
		}
		return answer.startsWith("Y");
	}

}




