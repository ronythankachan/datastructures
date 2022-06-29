package datastructures;

class Node{
    int val;
    Node left;
    Node right;
    Node(int val){
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class Tree {

    public static void preOrder(Node root){
        if(root == null) return;
        System.out.print(root.val+" -> ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void main(String[] args) {
        Node first = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);
        Node fourth = new Node(4);
        Node fifth = new Node(5);
        Node sixth = new Node(6);
        first.left = second;
        first.right = third;
        first.left.left = fourth;
        first.left.right = fifth;
        first.left.left.left = sixth;
        Tree.preOrder(first);
    }
}
