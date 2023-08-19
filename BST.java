import java.util.ArrayList;

public class BST {

    static class Node{

        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
        }
    }

    public static Node insert(Node root, int val){
        if(root == null){
            return new Node(val);
        }

        if(val < root.data){
            root.left = insert(root.left, val);
        }
        else if(val > root.data){
            root.right = insert(root.right, val);
        }

        return root;
    }

    public static Node createBST(int[] arr){
        Node root = null;
        for(int i=0; i<arr.length; i++){
            root = insert(root, arr[i]);
        }
        return root;
    }

    public static void inorder(Node root){
        // left
        // root
        // right
        if(root == null){
            return;
        }

        inorder(root.left);
        System.out.print(root.data+" ");
        inorder(root.right);
    }

    public static boolean search(Node root, int val){
        if(root == null){
            return false;
        }

        if(val < root.data){
            return search(root.left, val);
        }
        else if(val > root.data){
            return search(root.right, val);
        }
        else {
            return true;
        }
    }

    public static Node delete(Node root, int val){

        if(val < root.data){
            root.left = delete(root.left, val);
        }
        else if(val > root.data){
            root.right = delete(root.right, val);
        }
        else{
            // case 1: root is leaf node
            if(root.left == null && root.right == null){
                return null;
            }

            // case 2: root having either left or right single child
            if(root.left == null){
                return root.right;
            }
            else if(root.right == null){                
                return root.left;
            }

            // case 3: root having both left and right child
            // get left-most node in right
            Node leftmostNode = getLeftmost(root.right);
            root.data = leftmostNode.data;
            root.right = delete(root.right, leftmostNode.data);

        }
        return root;
    }

    public static Node getLeftmost(Node root) {
        // return inorder successor basically in leftmost node in right
        while(root.left != null){
            root = root.left;
        }
        return root;
    }

    public static void printInRange(Node root, int start, int end){
        if(root == null){
            return;
        }

        printInRange(root.left, start, end);
        if(root.data >= start && root.data <= end){
            System.out.print(root.data+" ");
        }
        printInRange(root.right, start, end);
    }

    public static void rootToLeaf(Node root, ArrayList<Integer> path){

        if(root == null){
            return;
        }

        path.add(root.data);

        if(root.left == null && root.right == null){
            System.out.println(path);
        }
        else{
            rootToLeaf(root.left, path);
            rootToLeaf(root.right, path);
        }
        
        path.remove(path.size()-1);
    }

    public static void main(String args[]){

        int[] arr = new int[]{5,3,7,2,4,8};
        
        // create bst
        Node root = createBST(arr);

        // print inorder - (sorted results)
        inorder(root);
        
        System.out.println();

        int val = 7; // searching in BST O(h) time complexity , where h is height
        if(search(root, val)){
            System.out.println("value "+val+" found in BST");
        }
        else{
            System.out.println("No match found for value "+val);
        }

        System.out.println("Before deleting: ");
        inorder(root);
        System.out.println();
        root = delete(root, 3);
        System.out.println("After deleting node with value 3: ");
        inorder(root);

        System.out.println("\nprint in range from 3 to 8");
        printInRange(root, 3, 8);

        System.out.println("\nRoot to leaf paths:");
        rootToLeaf(root, new ArrayList<>());

    }

}


