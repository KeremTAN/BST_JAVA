/******************************************************
 * BST Node
 ******************************************************/
public class BSTNode{
    public int key;           /* Key */
    public int count;         /* No of times a key is inserted into the tree */
    public BSTNode left;      /* Pointer to the left child */
    public BSTNode right;     /* Pointer to the right child */
    // Constructor
    public BSTNode(int key){this.key=key; count=1; left=right=null;}
};
