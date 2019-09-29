/**Emma Willard
 * (willa115, 5040938)*/

public class LinkedList<T extends Comparable<T>> implements List<T>{

    private Node head;
    //Constructor
    public LinkedList(){
        head = null;

    }
    public boolean add(T element){
        Node nextNode = new Node(element);
        if(element == null){
            return false;
        }
        else if(head == null){
            head = nextNode;
            return true;
        }
        else{
            Node current = head;
            while(current.getNext() != null){
                current = current.getNext();
            }
            current.setNext(nextNode);
            return true;
        }

    }
    public boolean add(int index, T element){
        Node nextNode = new Node(element);
        Node current = head;
        if(element == null || index < -1 || index > size()){
            return false;
        }

        // "Base case" if element is being adding to the front.
        else if (index == 0) {
            nextNode.setNext(current);
            head = nextNode;
            return true;}
        else if(index == size()-1){
            add(element);
            return true;}
        else{
            for(int i = 0; i < index - 1; i++){
                current = current.getNext();
            }
            //Node temp = current.getNext();
            //nextNode.setNext(temp);
            //current.setNext(nextNode);
            nextNode.setNext(current.getNext());
            current.setNext(nextNode);

            return true;
        }

    }
    public void clear(){
        head = null;
    }
    public boolean contains(T element){
        Node current = head;
        if(element == null){
            return false;
        }
        for(int i = 0; i < size(); i++){
            if(current.getData() == null){
                return false;
            }
            else if(current.getData() == element){
                return true;
            }
            else{current = current.getNext();}
        }
        return false;
    }
    public T get(int index){
        Node current = head;
        if (index < 0 || index > size()){
            return null;
        }
        else{
            for(int i = 0; i < index; i++){
                if(current.getNext() == null){
                    return null;}
                current = current.getNext();
            }
            return (T) current.getData();
            }


        }


    public int indexOf(T element){
        Node current = head;
        if(element == null || !contains((T)element)){
            return -1;
        }
        else{
            for(int i = 0; i < size(); i++){
                if(current.getData() == element){
                    return i;
                }
                else{current = current.getNext();}
            }
            return -1;
        }
    }
    public boolean isEmpty(){
        if(head == null){
            return true;
        }
        else{
            return false;}
    }
    public int lastIndexOf(T element){
        //Node current = head;
        int temp = -1;
        int i = 0;
        if(element == null || !contains(element)){
            return -1;
        }
        else{
            for(i = 0; i < size(); i++){
                if(get(i) == element){
                    temp = i;
                }}
            }
            return temp;
        }

    public T set(int index, T element){
        Node nextNode = new Node(element);
        Node current = head;
        if(element == null || index < 0 || index >= size()){
            return null;
        }

        // "Base case" if element is being adding to the front.
        else if (index == 0) {
            nextNode.setNext(current.getNext());
            head = nextNode;
            return (T)nextNode.getData();
            }
        /** If the index is at the end of the list
        else if(index == size()-1){
            add(element);
            return element;
        }*/
        else{
            int i;
            for(i = 0; i < index-1; i++){
                current = current.getNext();
            }
            //if statement if the index is at the end of the list
            Node temp = current.getNext();
            temp.setData(element);
            //current = current.getNext();
            current.setNext(temp);
            //current.setData(element);
            return (T)current.getData();
        }
    }
    public int size(){
        int counter = 0;
        Node current = head;
        while(current != null){
            current = current.getNext();
            counter++;
        }
        return counter;
    }
    public void sort(boolean order){
        LinkedList ordered = new LinkedList();
        //Sort in alphabetical order.
        int length = size();
        if(order == true){
            //System.out.println("Try to sort");
            int j;
            T smallest = get(0);
            while(length > 0){
                for(j = 0; j < length; j++){
                    if(length == 1){
                        smallest = get(j);
                    }
                    else if(smallest.compareTo(get(j)) >= 0){
                        smallest = get(j);
                    }
                }
                ordered.add(smallest);
                remove(smallest);
                //j = 1;
                smallest = get(0);
                length--;
                //System.out.println("Try to sort");
            }

            head = ordered.head;
            //System.out.println(toString());
        }
        //Sort in reverse alphabetical order.
        else{
            //System.out.println("Try to sort");
            int j;
            T smallest = get(0);
            while(length > 0){
                for(j = 0; j < length; j++){
                    if(length == 1){
                        smallest = get(j);
                    }
                    else if(smallest.compareTo(get(j)) <= 0){
                        smallest = get(j);
                    }
                }
                ordered.add(smallest);
                remove(smallest);

                smallest = get(0);
                length--;
            }

            head = ordered.head;
            }
    }
    public boolean remove(T element){
        Node current = head;
        if(element == null || !contains(element)){
            return false;
        }
        else{
            int index = indexOf(element);
            int i;
            for(i = 0; i < index - 1; i++){
                current = current.getNext();
            }
            //if the index is at the end of the list
            if(index == size()-1){
                current.setNext(null);
                return true;
            }
            if(index == 0){
                head = head.getNext();
                return true;
            }
            Node temp = current.getNext();
            current.setNext(temp.getNext());
            return true;
        }

    }
    public T remove(int index){
        //Node nextNode = new Node(element);
        Node current = head;
        if(get(index) == null || index < -1 || index >= size()){
            return null;
        }

        // "Base case" if element is being adding to the front.
        else if (index == 0) {
            head = head.getNext();
            return (T)current.getData();
        }

        else{
            int i;
            for(i = 0; i < index - 1; i++){
                current = current.getNext();
            }
            //If at end of list
            if(index == size()-1){
                T removed = (T)current.getNext().getData();
                //current = current.getNext();
                current.setNext(null);
                return removed;
            }
            T removed = (T)current.getNext().getData();
            Node temp = current.getNext().getNext();
            //temp.setData(element);
            //current = current.getNext();
            current.setNext(temp);
            //current.setData(element);

            return removed;

    }}
    public String toString(){
        String listString = "";
        if(head == null) {
            return "";
        }
        else{
            Node current = head;
            while(current != null) {
                listString = listString + " " + current.getData();
                current = current.getNext();
            }

        }
        return listString;
    }
    public static void main(String[] args){
        LinkedList list2 = new LinkedList();

        list2.add("it's");
        list2.add("me");
        list2.add("mario");
        list2.add("ahaha");
        list2.add(0,"hello");
        list2.add(3,"me");
        System.out.println("List elements: " + list2.toString());
        System.out.println("List size: " + list2.size());
        System.out.println("Contains 'hello'?: " + list2.contains("hello"));
        System.out.println("Index 2: " + list2.get(2));
        System.out.println("First instance of 'me' at index: " + list2.indexOf("me"));
        System.out.println("Last instance of 'me' at index: " + list2.lastIndexOf("me"));
        System.out.println();
        list2.set(4, "howdy");
        System.out.println("List elements after setting index 4: " + list2.toString());
        list2.remove("me");
        System.out.println("List elements after removal of 1st 'me': " + list2.toString() + ", " + "Size: " + list2.size());
        list2.remove(3);
        System.out.println(list2.get(0));
        System.out.println(list2.get(3));
        System.out.println(list2.get(1));
        //list2.remove(list2.size()-1);
        //System.out.println("List elements after remove index 3: " + list2.toString() + ", " + "Size: " + list2.size());
        System.out.println();
        System.out.println("List elements: " + list2.toString() + ", Size: " + list2.size());
        list2.sort(true);
        System.out.println("List in order: " + list2.toString());
        list2.sort(false);
        System.out.println("List in reverse order: " + list2.toString());
        System.out.println("List empty: " + list2.isEmpty());
        System.out.println();
        list2.clear();
        System.out.println("-----List cleared-----");
        System.out.println("List empty: " + list2.isEmpty());

    }
}
