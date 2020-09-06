public class LinkedLinkHash {
    public class node{
        public node next;
        public int data;

        public node(node next, int data){
            this.data = data;
            this.next = next;
        }
    }

    public node[] arr;
    private int size;
    public LinkedLinkHash(int m){
        arr = new node[m];
        this.size = m;
    }

    public int hash(int i){
        return i % size;
    }

    public void Add(int x){
        int index = hash(x);
        arr[index] = new node(arr[index], x);
    }

    public boolean contains(int x){
        int index = hash(x);
        node current = arr[index];
        while (current != null){
            if(current.data == x){
                return true;
            }
            current = current.next;
        }
        return false;
    }
}
