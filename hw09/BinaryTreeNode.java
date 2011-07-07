package gaspar.pa5;

/**Binary tree node calass that represents a BT, each tree node has a yes or no child
 * and a node will have some data stored as an answer/animal it represents
 * @author gasparobimba
 * @author martin (Gets all the credit for the algorithm and skeleton of the game
 *
 * @param <E>
 */
public class BinaryTreeNode<E>{

	/* generic data parameter*/
    private E data;
    
    private BinaryTreeNode<E> yes, no;   //the tree node's children

/*Constructor
 * */   
public BinaryTreeNode(E data, BinaryTreeNode<E> left, BinaryTreeNode<E> right){
   this.data = data;
   this.yes = left;
   this.no = right;
}       


	//getter for data
	public E getData()  {
     return data;
	}


	//getter left child
	public BinaryTreeNode<E> getLeft(){
		return yes;                                               
	} 

	//get leftmost node's data
	public E getLeftmostData(){
		if (yes == null)
			return data;
		else
			return yes.getLeftmostData();
	}
   
	//get right child
	public BinaryTreeNode<E> getRight(){
		return no;                                               
	} 


	//get rightmost node's data
	public E getRightmostData(){
		if (no == null)
			return data;
		else
			return no.getRightmostData();
	}
	//check for nodes at end of tree/leaf
	public boolean isLeaf(){
	   return (yes == null) && (no == null);                                               
	} 


	
/**
* Remove the leftmost most node of the tree with this node as its root.
*  been removed).
**/
public BinaryTreeNode<E> removeLeftmost( )
{
   if (yes == null)
      return no;
   else
   {
      yes = yes.removeLeftmost( );
      return this;
   }
}


/**
* Remove the rightmost most node of the tree with this node as its root.
* @param - none
* <dt><b>Postcondition:</b><dd>
*   The tree starting at this node has had its rightmost node removed (i.e.,
*   the deepest node that can be reached by following right links). The
*   return value is a reference to the root of the new (smaller) tree.
*   This return value could be null if the original tree had only one
*   node (since that one node has now been removed).
**/
public BinaryTreeNode<E> removeRightmost( )
{
   if (no == null)
      return yes;
   else
   {
      no = no.removeRightmost();
      return this;
   }
}
    
/**
* Modification method to set the data in this node.   
* @param <CODE>newData</CODE>
*   the new data to place in this node
* <dt><b>Postcondition:</b><dd>
*   The data of this node has been set to <CODE>newData</CODE>.
**/
public void setData(E newData)   
{
   data = newData;
}                                                               


/**
* Modification method to set the link to the left child of this node.
* 
**/
public void setLeft(BinaryTreeNode<E> newLeft)
{                    
   yes = newLeft;
}
 
 
/**
* Modification method to set the link to the right child of this node.
* 
**/
public void setRight(BinaryTreeNode<E> newRight)
{                    
   no = newRight;
}  
 

}
        
