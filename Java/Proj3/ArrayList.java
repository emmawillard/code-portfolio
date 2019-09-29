
/**
 * Emma Willard
 * (willa115, 5040938)
 */
public class ArrayList<T extends Comparable<T>> implements List<T> {
    private T[] array;

    //Constructor, initializes array to length 2.
    public ArrayList(){
       array = (T[]) new Comparable[2];
    }

    //Resize array when it becomes full, double original length
    private void resize(){
        T[] resizedArray = (T[]) new Comparable[array.length*2];
        for(int i = 0; i < array.length; i++){
            resizedArray[i] = array[i];
        }
        array = resizedArray;

    }
    public boolean add(T element){
        if(element == null){
            return false;
        }
        else{
            //Resize if needed
            if(size() + 1 >= array.length){
                resize();
            }

            //boolean to break out of for-loop
            boolean added = false;
            for(int i = 0; i < array.length && !added; i++){
                if(array[i] == null){
                    array[i] = element;
                    added = true;
                }
            }
            return true;
        }
    }
    public boolean add(int index, T element){
        if(element == null || index < 0 || index >= array.length){
            return false;
        }
        if(size() + 1 >= array.length){
            resize();
        }

        //Shift other elements, then add new element
        for(int i = index; i < array.length; i++){
            array[index + 1] = array[index];
        }
        array[index] = element;
        return true;

    }

    //Clear list
    public void clear(){
        array = (T[]) new Comparable[2];
    }
    public boolean contains(T element){
        for(int i = 0; i < size(); i++){
            if(array[i] == element){
                return true;
            }
        }
        return false;

    }

    //Return object at index
    public T get(int index){
        if(index < 0 || index >= array.length || array[index] == null){
            return null;
        }
        else{
            return array[index];}
    }

    //Finds index of first instance of element
    public int indexOf(T element){
        if(element == null || !contains(element)){
            return -1;
        }
        for(int i = 0; i < size(); i++){
            if(array[i] == element){
                return i;
            }
        }
        return -1;
    }
    public boolean isEmpty(){
        for(int i = 0; i < size(); i++){
            if(array[i] != null){
                return false;
            }
        }
        return true;
    }
    public int lastIndexOf(T element){
        if(element == null || !contains(element)){
            return -1;
        }

        // last is initially -1 if it doesn't contain the element (not totally necessary)
        // "Double-checking" after checking if !contains(element)
        int last = -1;
        for(int i = 0; i < size(); i++){
            if(array[i] == element){
                last = i;
            }
        }
        return last;
    }
    public T set(int index, T element){
        if(element == null || index < 0 || index >= array.length){
            return null;
        }
        else{
            array[index] = element;
            return array[index];
        }

    }
    public int size(){
        int count = 0;
        for(int i = 0; i < array.length; i++){
            if(array[i] != null){
                count++;
            }
        }
        return count;
    }

    //Code modified from Bubble.java given on Moodle
    //And changed to sort T[] array
    //Bubble sort

    public void sort(boolean order){
        int i,j;
        T temp;
        boolean swapped = true;

        //order == true, sorted in alphabetical order
        if(order == true){
            for(i = 0; i < size() && swapped; i++){
                swapped = false;
                for(j = 1; j < size() - i; j++){
                    if(array[j].compareTo(array[j-1]) < 0){
                        swapped = true;
                        temp = array[j];
                        array[j] = array[j-1];
                        array[j-1] = temp;
                    }
                }
            }


        }

        //order == false, will be sorted in reverse alphabetical order
        else{
            for(i = 0; i < size() && swapped; i++){
                swapped = false;
                for(j = 1; j < size() - i; j++){
                    if(array[j].compareTo(array[j-1]) > 0){
                        swapped = true;
                        temp = array[j];
                        array[j] = array[j-1];
                        array[j-1] = temp;
                    }
                }
            }

        }
    }

    //Removes first instance of element from list
    public boolean remove(T element){
        if(element == null || !contains(element)){
            return false;
        }
        else{

            //Shift other elements to remove element
            int idx = indexOf(element);
            int i;
            T temp;
            for(i = idx; i+1 < size(); i++){
                temp = array[i+1];
                array[i] = temp;
            }
            array[size()-1] = null;
            //array[idx] = element;
            return true;
        }

    }
    public T remove(int index){
        if(index < 0 || index >= array.length){
            return null;
        }
        else{
            T removed = get(index);
            T temp;
            int i;
            for(i = index; i+1 < size(); i++){
                temp = array[i+1];
                array[i] = temp;
            }
            array[size()-1] = null;
            return removed;
        }
    }

    public String toString(){
        String arrString = "";
        for(int i = 0; i < size(); i++){
            arrString = arrString  + " " + array[i];
        }
        return arrString;
    }
    public static void main(String[] args){

        ArrayList list = new ArrayList();

        list.add("it's");
        list.add(1, "me");
        list.add("mario");
        list.add("ahaha");
        list.add("me");
        list.add("hello");

        System.out.println("List elements: " + list.toString());
        System.out.println("List size: " + list.size() +  ", " + "Array current length: " + list.array.length);
        System.out.println("Contains 'hello'?: " + list.contains("hello"));
        System.out.println("Index 2: " + list.get(2));
        System.out.println("First instance of 'me' at index: " + list.indexOf("me"));
        System.out.println("Last instance of 'me' at index: " + list.lastIndexOf("me"));
        System.out.println();
        list.set(2, "howdy");
        System.out.println("List elements after setting index 2: " + list.toString());
        list.remove("me");
        System.out.println("List elements after removal of 1st 'me': " + list.toString() + ", " + "Size: " + list.size());
        list.remove(2);
        System.out.println("List elements after remove index 2: " + list.toString() + ", " + "Size: " + list.size());
        System.out.println();
        list.sort(true);
        System.out.println("List in order: " + list.toString());
        list.sort(false);
        System.out.println("List in reverse order: " + list.toString());
        System.out.println("List empty: " + list.isEmpty());
        System.out.println();
        list.clear();
        System.out.println("-----List cleared-----");
        System.out.println("List empty: " + list.isEmpty());
    }
}
