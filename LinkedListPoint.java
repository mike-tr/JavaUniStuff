public class LinkedListPoint {
    Node head;

    public LinkedListPoint() {

    }
    public LinkedListPoint(Node next, Point p) {
        if(next == null){
            head = new Node(null, p);
            return;
        }
        head = next;
        add(p);
    }

    public void add(Point p) {
        Point zero = new Point();
        double distance = p.distance(zero);


        Node prev = head;
        if(prev == null || prev.getData().distance(zero) > distance){
            head = new Node(head, p);
            return;
        }
        Node current = prev.getNext();
        while (current != null && current.getData().distance(zero) < distance){
            prev = current;
            current = prev.getNext();
        }
        Node node = new Node(current, p);
        prev.setNext(node);
    }

    public void remove(Point p) {
        Node prev = head;
        if(prev != null){
            if(prev.getData().equals(p)){
                head = prev.getNext();
                // well maybe the first 3 points are what we want to delete.
                // so we can do it by doing recursion
                remove(p);
                return;
            }
        }
        // if the head isn't the one we want to delete.
        Node current = prev.getNext();
        Point zero = new Point();

        double distance = p.distance(zero);
        while (current != null){
            // the list is sorted so every point from here on, isn't what we are looking for.
            if(current.getData().distance(zero) > distance)
                break;
            if(current.getData().equals(p)){
                prev.setNext(current.getNext());
                current = prev.getNext();
            }else{
                prev = current;
                current = prev.getNext();
            }
        }
    }

    public int size() {
        int size = 0;
        Node current = head;
        while (current != null){
            current = current.getNext();
            size++;
        }
        return size;
    }


    public boolean contains(Point p) {
        Point zero = new Point();
        double distance = p.distance(zero);
        Node current = head;
        while (current != null){
            if(current.getData().distance(zero) > distance){
                return false;
            }
            if(current.getData().equals(p)){
                return true;
            }
            current = current.getNext();
        }
        return false;
    }


    @Override
    public String toString() {
        String s="";
        Node t=head;
        while(t!=null) {
            s+=" "+t.toString();
            t=t.getNext();
        }
        return "LinkedListPoint ["+s+"]";
    }


    public static void main(String[] args) {
        Point p1= new Point(100,0);
        Point p2= new Point(20,0);
        Point p3= new Point(3,0);
        Point p4= new Point(4,400);
        Point p5= new Point(5,0);
        Point p6= new Point(5,0);
        Point p7= new Point(100,0);
        LinkedListPoint ll= new LinkedListPoint();
        ll.add(p2);
        ll.add(p1);
        ll.add(p3);
        ll.add(p5);
        ll.add(p4);
        ll.add(p6);
        ll.add(p7);
        System.out.println("after adding 5 points: "+ ll);
        System.out.println("does the list contains p1? "+ ll.contains(p1));
        ll.remove(p1);
        ll.remove(p5);
        System.out.println("does the list contains p1, after removing it? "+ ll.contains(p1));
        System.out.println("after removing  2 points: "+ ll);
        System.out.println("list size:" +ll.size());
    }

}