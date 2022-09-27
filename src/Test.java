public class Test {
    /*******************************************************
     * Test1
     *******************************************************/
    public static int Test1(){
        BST bst = new BST();
        BSTNode node;
        int depth;

        // Insert keys
        bst.Insert(10);
        bst.Insert(5);
        bst.Insert(15);
        bst.Insert(15);
        bst.Insert(5);
        bst.Insert(5);

        //Print the BST on the screen
        depth = bst.Depth();
        System.out.print("Depth of the tree is: " + depth + "\n");
        bst.Print();
        node = bst.Root();
        if (node.key != 10 || node.count != 1) return 0;

        node = bst.Root().left;
        if (node.key != 5 || node.count != 3) return 0;

        node = bst.Root().right;
        if (node.key != 15 || node.count != 2) return 0;

        return 20;
    } //end-Test1

    /*******************************************************
     * Test2
     *******************************************************/
    public static int Test2(){
        BST bst = new BST();
        BSTNode node;
        int depth, noOfNodes;
        // Insert keys
        bst.Insert(5);
        bst.Insert(5);
        bst.Insert(10);
        bst.Insert(10);
        bst.Insert(1);
        bst.Insert(3);
        bst.Insert(2);
        bst.Insert(4);
        bst.Insert(20);
        bst.Insert(15);
        bst.Insert(30);
        bst.Insert(40);

        // Print the BST on the screen
        depth = bst.Depth();
        noOfNodes = bst.NoOfNodes();
        if (depth != 4) return 0;
        if (noOfNodes != 10) return 0;

        System.out.print("Tree Depth: " + depth + ", NoOfNodes: " + noOfNodes + "\n");
        bst.Print();

        node = bst.Min();
        if (node.key != 1 || node.left != null) return 0;

        node = bst.Max();
        if (node.key != 40 || node.right != null) return 0;

        // Check the validity of the tree
        node = bst.Root();
       if (node.key != 5 || node.count != 2) return 0;

       node = bst.Root().left;
        if (node.key != 1 || node.count != 1) return 0;

        node = bst.Root().left.right;
        if (node.key != 3 || node.count != 1) return 0;

        node = bst.Root().left.right.left;
        if (node.key != 2 || node.count != 1) return 0;

        node = bst.Root().left.right.right;
        if (node.key != 4 || node.count != 1) return 0;

        node = bst.Root().right;
        if (node.key != 10 || node.count != 2) return 0;

        node = bst.Root().right.right;
        if (node.key != 20 || node.count != 1) return 0;

        node = bst.Root().right.right.left;
        if (node.key != 15 || node.count != 1) return 0;

        node = bst.Root().right.right.right;
        if (node.key != 30 || node.count != 1) return 0;

        node = bst.Root().right.right.right.right;
        if (node.key != 40 || node.count != 1) return 0;

        // Test BSTFind
        node = bst.Find(17);
        if (node != null) return 0;

        node = bst.Find(15);
        if (node.key != 15 || node.count != 1) return 0;

        return 20;
    } //end-Test2

    /*******************************************************
     * Test3
     *******************************************************/
    public static int Test3(){
        BST bst = new BST();
        BSTNode node;

        // Insert keys
        bst.Insert(5);
        bst.Insert(5);
        bst.Insert(10);
        bst.Insert(10);
        bst.Insert(1);
        bst.Insert(3);
        bst.Insert(2);
        bst.Insert(4);
        bst.Insert(20);
        bst.Insert(15);
        bst.Insert(12);
        bst.Insert(11);
        bst.Insert(13);
        bst.Insert(17);
        bst.Insert(16);
        bst.Insert(18);
        bst.Insert(30);

        // Test delete
        if (bst.Delete(35) == 0) return 0;

        // Delete leaf node
        if (bst.Delete(30)!=0) return 0;
        node = bst.Find(20);
        if (node.right!=null && node.left.key!=15) return 0;

        // Delete internal node with only a right subtree
        bst.Delete(10);
        node = bst.Find(5);
        if (node.right.key != 20) return 0;

        // Delete internal node with only a left subtree
        bst.Delete(20);
        node = bst.Find(5);
        if (node.right.key != 15) return 0;

        // Delete an internal node with 2 children
        bst.Delete(15);
        node = bst.Find(5);
        if (node.right.key != 13 && node.right.key != 16) return 0;

        // Delete an internal node with 2 children (root)
        bst.Delete(5);
        node = bst.Root();
        if (node.key != 4 && node.key != 11) return 0;
        if (bst.NoOfNodes()!=10) return 0;

        bst.Print();

        return 30;
    } //end-Test3

    /*******************************************************
     * Test4
     *******************************************************/
    public static int Test4(){
        BST bst = new BST();
        BSTNode node;
        int min, max;

        int N = 10000;
        int[] numbers = new int[N];
        int i;

        for (i=0; i<N; i++) numbers[i] = 0;

        min = N; max = -1;
        for (i=0; i<100000; i++){
            int key = (int)(Math.random()*(N-1));
            numbers[key]++;

            if (key < min) min = key;
            if (key > max) max = key;

            // Insert into BST
            bst.Insert(key);
        } //end-for

        node = bst.Min();
        if (node == null || node.key != min || node.count != numbers[min]) return 0;

        node = bst.Max();
        if (node == null || node.key != max || node.count != numbers[max]) return 0;

        // Search all numbers and check if they are corrently inserted
        for (i=0; i<N; i++){
            node = bst.Find(i);

            if (numbers[i] == 0){
                if (node != null) return 0;
            } else {
                if (node.key != i || node.count != numbers[i]) return 0;
                if (node.left != null && node.left.key >= node.key) return 0;
                if (node.right != null && node.right.key <= node.key) return 0;
            } //end-else
        } //end-for

        // Now delete all keys from the tree until the tree is empty
        for (i=0; i<N; i++){
            int ret = bst.Delete(i);

            if (numbers[i] == 0){
                if (ret != -1) return 0;
            } else if (ret != 0) return 0;
        } //end-for

        if (bst.Root() != null) return 0;
        if (bst.NoOfNodes() != 0) return 0;

        return 30;
    } //end-Test4

    /*******************************************************
     * Main Function
     *******************************************************/
    public static void main(String[] args){
        int grade = 0;

        System.out.print("---------------------------------- TEST1 ---------------------------\n");
        grade += Test1();
        System.out.print("Your grade after Test1: " + grade + "\n");

        System.out.print("---------------------------------- TEST2 ---------------------------\n");
        grade += Test2();
        System.out.print("Your grade after Test2: " + grade + "\n");

        System.out.print("---------------------------------- TEST3 ---------------------------\n");
        grade += Test3();
        System.out.print("Your grade after Test3: " + grade + "\n");

        System.out.print("---------------------------------- TEST4 ---------------------------\n");
        grade += Test4();
        System.out.print("Your grade after Test4: " + grade + "\n");

        System.out.print("--------------------------------------------------------------------\n");
        System.out.print("!=!=!=!=!=!=> Your project grade is: " + grade + "\n");
    } /* end-main */
}
