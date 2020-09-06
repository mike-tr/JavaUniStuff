import java.util.ArrayList;
import java.util.List;

public class Ex4 {
    public static class Node{
        public Node left,right;
        public int key;

        public Node(int key){
            this.key = key;
        }
    }

    public static void main(String[] args) {
        // the whole code in this sections is mine.
        // i did give the printTree and generate tree code to others tho.
        // michael trushkin
        Node root = GenerateTree(4);
        printTree(root, 4);
        System.out.println("tree depth : " + isFull(root));

        root = GenerateTree(3);
        printTree(root, 3);
        System.out.println("tree depth : " + isFull(root));

        root = GenerateTree(false, 4, 1f);
        printTree(root, 4);

        System.out.println("tree depth : " + isFull(root));

        root = GenerateTree(false, 3, 0.6f);
        printTree(root, 3);

        System.out.println("tree depth : " + isFull(root));
    }

    public static int isFull(Node root){
        if(root == null)
            return -1;
        //List<Node> currentLevel = new ArrayList<>(); //  not sure if list get/delete is O(1)
        Node[] currentLevel = new Node[1];
        Node[] nextLevel = new Node[2];
        //currentLevel.add(root);
        //List<Node> nextLevel = new ArrayList<>();
        currentLevel[0] = root;
        int index = 0;
        int max = 1;
        int depth = 0;
        boolean run = true;
        boolean nnull = false;

        // runs up to depth times. a.k.a log(n)
        while (run){
            boolean missing = false;
            nnull = false;
            // O( 2^(depth) ) :: depth at this current point
            for (index = 0; index < max; index++) {
                Node current = currentLevel[index];
                if(missing){
                    if(current.left != null || current.right != null)
                        return -2;
                    continue;
                }

                if(current.right == null || current.left == null){
                    if(nnull)
                        return  -2;
                    if (current.right != current.left)
                        return -2;
                    missing = true;
                    continue;
                }else if(!nnull){
                    nnull = true;
                }
                nextLevel[index * 2] = current.right;
                nextLevel[index * 2 + 1] = current.left;


            }
            if(nextLevel[0] != null){
                currentLevel = nextLevel;
                max *= 2;
                nextLevel = new Node[max * 2];
                depth++;
            }else{
                run = false;
            }
        }
        return depth;
    }

    public static Node GenerateTree(int height){
        return  GenerateTree(true, height, 1);
    }

    public static Node GenerateTree(boolean full, int height, float failp){
        // all the code here is made by me michael!
        // create a random non full tree, or a full tree.

        //generate a tree with height of "height"
        if(failp > 1){
            failp = 1;
        }else if(failp < 0){
            failp = 0;
        }

        Node root = new Node(1);
        List<Node> currentLevel = new ArrayList<>();
        currentLevel.add(root);
        List<Node> nextLevel = new ArrayList<>();
        int level = 0;

        float fail = 1f;
        //System.out.println(fail + " ," + Math.random());
        while (level < height){
            while (currentLevel.size() > 0){
                Node current = currentLevel.get(0);
                currentLevel.remove(0);

                if(level + 1 == height && currentLevel.size() < 1){
                    fail = 0;
                }

                if(full || Math.random() < fail){
                    current.right = new Node(current.key * 2 + 1);
                    current.left = new Node( current.key * 2);

                    nextLevel.add(current.right);
                    nextLevel.add(current.left);
                }else{
                    if(Math.random() < fail) {
                        if (Math.random() > 0.5) {
                            current.right = new Node(current.key * 2 + 1);
                            nextLevel.add(current.right);
                        } else {
                            current.left = new Node(current.key * 2);
                            nextLevel.add(current.left);
                        }
                    }
                }
            }
            level++;
            fail *= failp;
            currentLevel = nextLevel;
            nextLevel = new ArrayList<>();
        }
        return root;
    }

    public static void printTree(Node root, int depth){
        // all the code here is made by me michael!

        // this code only prints the tree of to the depth specified, well it can be improved but nah
        String data = "";
        List<Node> currentLevel = new ArrayList<>();
        currentLevel.add(root);
        int level = 0;
        List<Node> nextLevel = new ArrayList<>();

        int spacesBetweenNodes = 16;
        int maxLength = (int)Math.pow(2, depth-1);
        int spacesBetween = maxLength * spacesBetweenNodes;
        while (level <= depth){
            data += addSpaces(spacesBetween/2);
            while (currentLevel.size() > 0){


                //data += addSpaces(spacesBetween);
                Node current = currentLevel.get(0);
                currentLevel.remove(0);

                String add = "";
                if(current != null) {
                    nextLevel.add(current.right);
                    nextLevel.add(current.left);

                    add += current.key;
                }else{
                    nextLevel.add(null);
                    nextLevel.add(null);
                }
                data += add;
                data += addSpaces(spacesBetween - add.length());
            }
            level++;
            spacesBetween /= 2;
            data += "\n\n";

            currentLevel = nextLevel;
            nextLevel = new ArrayList<>();
        }
        System.out.println(data);
    }

    public static String addSpaces(int c){
        String spaces = "";
        for (int i = 0; i < c; i++) {
            spaces += " ";
        }
        return spaces;
    }
}
