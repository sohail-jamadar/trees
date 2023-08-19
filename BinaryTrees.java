import java.util.LinkedList;
import java.util.Queue;

class Node{
    int data;
    Node left;
    Node right;

    Node(int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }
    Node(int data, Node left, Node right){
        this.data = data;
        this.left = left;
        this.right = right;
    }
}


public class BinaryTrees{

    static int idx = -1;

    public static Node createTree(int[] arr){
        idx++;

        if(arr[idx] == -1){
            return null;
        }

        Node root = new Node(arr[idx]);

        root.left = createTree(arr);
        root.right = createTree(arr);

        return root;
    }

    public static void preorder(Node root){
        if(root == null){
            return;
        }

        System.out.print(root.data+ " ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void inorder(Node root){
        if(root == null){
            return;
        }

        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public static void postorder(Node root){
        if(root == null){
            return;
        }

        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }

    public static void levelOrder(Node root){

        Queue<Node> q = new LinkedList<>();

        q.add(root);
        q.add(null);

        while(!q.isEmpty()){

            Node curr = q.remove();

            if(curr == null){
                System.out.println();
                if(q.isEmpty()){
                    break;
                }
                else{
                    q.add(null);
                }
            }
            else{
                System.out.print(curr.data+" ");
                if(curr.left != null){
                    q.add(curr.left);
                }
                if(curr.right != null){
                    q.add(curr.right);
                }
            }
        }
    }

    public static int height(Node root){
        if(root == null){
            return 0;
        }

        int left = height(root.left);
        int right = height(root.right);

        return Math.max(left, right) + 1;
    }

    public static int countNodes(Node root){
        if(root == null){
            return 0;
        }

        int left = countNodes(root.left);
        int right = countNodes(root.right);


        return left + right + 1;
    }

    public static int sumOfNodes(Node root){
        if(root == null){
            return 0;
        }

        int left = sumOfNodes(root.left);
        int right = sumOfNodes(root.right);


        return left + right + root.data;
    }

    public static int calcDiameter(Node root){
        
        // takes O(n^2) time complexity n - for calc diameter and another n - for calc height

        if(root == null){
            return 0;
        }

        int base1 = calcDiameter(root.left);
        int base2 = calcDiameter(root.right);
        int base3 = height(root.left) + height(root.right) + 1;

        return Math.max(Math.max(base1, base2), base3);
    }

    public static TreeInfo calcDiameter2(Node root){

        // takes O(n) time complexity n - for calc diameter

        if(root == null){
            return new TreeInfo(0, 0);
        }

        TreeInfo left = calcDiameter2(root.left);
        TreeInfo right = calcDiameter2(root.right);

        int myHeight = Math.max(left.height,right.height) + 1;

        int base1 = left.diameter;
        int base2 = right.diameter;
        int base3 = left.height + right.height + 1;

        int myDiameter = Math.max(Math.max(base1, base2), base3);

        return new TreeInfo(myHeight, myDiameter);
    }

    static class TreeInfo{
        private int height;
        private int diameter;

        TreeInfo(int height, int diameter){
            this.height = height;
            this.diameter = diameter;
        }
    }

    private static boolean isIdentical(Node root, Node subroot) {
        if(root == null && subroot == null){
            // match found
            return true;
        }
        if(root == null || subroot == null){
            // mismatch
            return false;
        }

        if(root.data == subroot.data){
            return isIdentical(root.left, subroot.left) && isIdentical(root.right, subroot.right);
        }

        return false;
    }

    public static boolean isSubtree(Node root, Node subroot){
        if(subroot == null){
            return true;
        }
        if(root == null){
            return false;
        }


        if(root.data == subroot.data){
            if(isIdentical(root, subroot)){
                return true;
            }
        }

        return isSubtree(root.left, subroot) || isSubtree(root.right, subroot);

    }

    public static void main(String args[]){

        int[] arr = new int[]{1, 2, 3, -1, -1, 4, -1, -1, 5, -1, 6, -1, -1};

        Node root = createTree(arr);
        // preorder(root);
        // inorder(root);
        // postorder(root);
        levelOrder(root);
        System.out.println("Height of the tree: "+height(root));
        System.out.println("Count of nodes: "+countNodes(root));
        System.out.println("Sum of nodes: "+sumOfNodes(root));
        System.out.println("Diameter O(n^2) of given tree: "+calcDiameter(root));   
        System.out.println("Diameter2 O(n) of given tree: "+calcDiameter2(root).diameter);
        

        int[] patternArr = new int[]{3, -1, 6, -1, -1};
        Node subRoot = createTree(patternArr);
        System.out.println("is subroot Subtree of root? "+isSubtree(root, subRoot));
        
    }





}