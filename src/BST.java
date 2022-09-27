/******************************************************
 * BST ADT.
 * Supported operations:
 * Insert
 * Delete
 * Find
 * Min
 * Max
 * Depth
 * Print
 ******************************************************/
public class BST{
    private BSTNode root;      /* Pointer to the root of the tree */
    private int noOfNodes;     /* No of nodes in the tree */

    /*******************************************************
     * Constructor: Initializes the BST
     *******************************************************/
    public BST(){root=null; noOfNodes=0;}

    /*******************************************************
     * Returns a pointer to the root of the tree
     *******************************************************/
    public BSTNode Root(){return root;}

    /*******************************************************
     * Returns the number of nodes in the tree
     *******************************************************/
    public int NoOfNodes(){return noOfNodes;}

    /*******************************************************
     * Inserts the key into the BST. Returns a pointer to
     * the inserted node
     *******************************************************/
    public BSTNode Insert(int key){
        BSTNode parent = null;
        BSTNode place =root;
        BSTNode newGuy = new BSTNode(key);
        while (place != null){
            parent = place;
            if (key == place.key){
                place.count++;
                return newGuy;
            }
            else if (key < place.key)
                place = place.left;
            else
                place = place.right;
        }
        newGuy.left = newGuy.right = null;
        if (root == null) {
            root = newGuy;
            noOfNodes++;
            root.count=1;
        }
        else if (key < parent.key){
            noOfNodes++;
            parent.left = newGuy;
            parent.left.count = 1;
        }

        else{
            noOfNodes++;
            parent.right = newGuy;
            parent.right.count=1;
        }
        return newGuy;
    } //end-Insert

    /*******************************************************
     * Deletes the key from the tree (if found). Returns
     * 0 if deletion succeeds, -1 if it fails
     *******************************************************/
    public int Delete(int key){
        BSTNode guyOfDelete = root;
        BSTNode parentOfDelete = null;
        BSTNode tmp;
        BSTNode child_left;
        BSTNode child_right;
        int ctrl=0;
        if (root == null)  return -1;
        while (guyOfDelete != null){
            if (key == guyOfDelete.key) {
                ctrl++;
                break;
            }
            else if (key < guyOfDelete.key){
                parentOfDelete = guyOfDelete;
                guyOfDelete = guyOfDelete.left;
            }
            else{
                parentOfDelete = guyOfDelete;
                guyOfDelete = guyOfDelete.right;
            }
        }
        if(ctrl!=0) {
            if (guyOfDelete.left == null && guyOfDelete.right == null){
                if (parentOfDelete!=null) {
                    if (guyOfDelete.key < parentOfDelete.key)
                        parentOfDelete.left = null;
                    else
                        parentOfDelete.right = null;
                }
                else root=null;
                }
                // If we delete that have only right child
                else if(guyOfDelete.left == null){
                    child_left =guyOfDelete.right.left;
                    child_right =guyOfDelete.right.right;

                    guyOfDelete=guyOfDelete.right;
                    if(parentOfDelete!=null) {
                        if (guyOfDelete.key < parentOfDelete.key)
                            parentOfDelete.left = guyOfDelete;
                        else
                            parentOfDelete.right = guyOfDelete;
                        guyOfDelete.right = child_right;
                        guyOfDelete.left = child_left;
                    }
                    else{
                        root=guyOfDelete;
                        root.left=child_left;
                        root.right=child_right;
                    }
                }
                // If we delete that have only left child
                else if(guyOfDelete.right == null){
                    child_left =guyOfDelete.left.left;
                    child_right =guyOfDelete.left.right;

                    guyOfDelete=guyOfDelete.left;

                    if(parentOfDelete!=null) {
                        if (guyOfDelete.key < parentOfDelete.key)
                            parentOfDelete.left = guyOfDelete;
                        else
                            parentOfDelete.right = guyOfDelete;

                        guyOfDelete.right = child_right;
                        guyOfDelete.left = child_left;
                    }
                    else{
                        root=guyOfDelete;
                        root.left=child_left;
                        root.right=child_right;
                    }
                }
                // If we delete that have two child
                else {
                    BSTNode parentOfNewGuy2 = null;
                    tmp = guyOfDelete.left;
                    while (tmp.right != null) {
                        parentOfNewGuy2 = tmp;
                        tmp = tmp.right;
                    }
                    child_left =guyOfDelete.left;
                    child_right=guyOfDelete.right;
                    guyOfDelete = tmp;
                    if(parentOfDelete!=null) {
                        if (guyOfDelete.key < parentOfDelete.key)
                            parentOfDelete.left = guyOfDelete;
                        else
                            parentOfDelete.right = guyOfDelete;

                        guyOfDelete.left = child_left;
                        guyOfDelete.right = child_right;
                    }
                    else{
                        root=guyOfDelete;
                        root.left=child_left;
                        root.right=child_right;
                    }
                    assert parentOfNewGuy2 != null;
                    if(tmp.key<parentOfNewGuy2.key)
                        parentOfNewGuy2.left=null;
                    else
                        parentOfNewGuy2.right=null;
                }

        } //Delete proccess
        else return -1; //There is no value
        noOfNodes--;
        return 0;
    } //end-Delete

    /*******************************************************
     * Searches the BST for a key. Returns a pointer to the
     * node that contains the key (if found) or NULL if unsuccessful
     *******************************************************/
    public BSTNode Find(int key){
        BSTNode place = root;
        while (place != null){
            if (key == place.key)
                return place;
            else if (key < place.key)
                place = place.left;
            else
                place = place.right;
        }
        return null;
    } //end-Find

    /*******************************************************
     * Returns a pointer to the node that contains the minimum key
     *******************************************************/
    public BSTNode Min(){
        if (root == null)
            return null;
        BSTNode place = root;
        while (place.left != null){
            place = place.left;
        }
        return place;
    } //end-Min

    /*******************************************************
     * Returns a pointer to the node that contains the maximum key
     *******************************************************/
    public BSTNode Max(){
        if (root == null)
            return null;
        BSTNode place = root;
        while (place.right != null){
            place = place.right;
        }
        return place;
    } //end-Max

    /*******************************************************
     * Returns the depth of tree. Depth of a tree is defined as
     * the depth of the deepest leaf node. Root is at depth 0
     *******************************************************/
    public int Depth(){
        int m =DepthWithRoot(root);
        return m-1;
    } //end-Depth

    /*******************************************************
     * Performs an inorder traversal of the tree and prints [key, count]
     * pairs in sorted order
     *******************************************************/
    public void Print(){
        PrintWithNode(root);
        System.out.println();
    } //end-Print
    public int DepthWithRoot(BSTNode root){
        int nodeLeft,nodeRight;
        if (root==null) return 0;
        else {
             nodeLeft = DepthWithRoot(root.left);
             nodeRight = DepthWithRoot(root.right);
        }
      return Math.max(nodeLeft,nodeRight)+1;
    }
    public void PrintWithNode(BSTNode root){
        if(root!=null) {
            System.out.print(root.key + " ");
            PrintWithNode(root.left);
            PrintWithNode(root.right);
        }
    }


}
