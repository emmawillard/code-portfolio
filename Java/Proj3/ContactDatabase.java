
/**Emma Willard
 * (willa115, 5040938)
 */
public class ContactDatabase {
    private List<Contact> list;
    public ContactDatabase(String type){
        if(type.equals("array")){
            list = new ArrayList<Contact>();}
        else if(type.equals("linked")){
            list = new LinkedList<Contact>();}
    }
    public boolean add(Contact c){
        return list.add(c);
    }
    public Contact find(String name){
        int idx = 0;
        while(idx < list.size()){
            if(list.get(idx).getName().contains(name)){
                return list.get(idx);
            }
            idx++;
        }
        return null;
    }
    public Contact remove(int index){
        return list.remove(index);
    }
    public Contact get(int index){
        return list.get(index);
    }
    public void sort(){
        list.sort(true);
    }
    public Contact[] getContactsByType(String type){
        Contact[] arrayType;
        int typeSize = 0;
        if(type.equals("friend")){
            for(int i = 0; i < list.size(); i++){
                if(list.get(i) instanceof Friend){
                    typeSize++;
                }
            }
            arrayType = new Contact[typeSize];
            int j = 0;
            for(int i = 0; i < list.size(); i++){
                if(list.get(i) instanceof Friend){
                    arrayType[j] = list.get(i);
                    j++;
                }
            }
        }
        else if (type.equals("coworker")){
            for(int i = 0; i < list.size(); i++){
                if(list.get(i) instanceof Coworker){
                    typeSize++;
                }
            }
            arrayType = new Contact[typeSize];
            int j = 0;
            for(int i = 0; i < list.size(); i++){
                if(list.get(i) instanceof Coworker){
                    arrayType[j] = list.get(i);
                    j++;
                }
            }
        }
        //Type is restaurant
        else{
            for(int i = 0; i < list.size(); i++){
                if(list.get(i) instanceof Restaurant){
                    typeSize++;
                }
            }
            arrayType = new Contact[typeSize];
            int j = 0;
            for(int i = 0; i < list.size(); i++){
                if(list.get(i) instanceof Restaurant){
                    arrayType[j] = list.get(i);
                    j++;
                }
            }

        }
        return arrayType;
    }
    public Contact getOldestFriend(){
        Friend oldest = null;
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i) instanceof Friend){
                if(oldest == null){
                    oldest = (Friend)list.get(i);
                }
                else{
                    Friend next = (Friend) list.get(i);
                    int birth1 = oldest.getBirthday();
                    int birth2 = next.getBirthday();
                    if(birth2 <= birth1){
                        oldest = next;
                    }

                }
            }
        }
        return oldest;
    }
    public String getMailToLink(){
        Contact[] mailList = getContactsByType("coworker");
        String mail = "mailto:";
        for(int i = 0; i<mailList.length; i++){
            String email = ((Coworker)mailList[i]).getEmail();
            if(i == mailList.length-1){
                mail = mail + email;
            }
            else{
                mail = mail + email + ",";
            }
        }
        return mail;
    }
    public Contact[] getTopKRestaurants(int k){
        Contact[] allRestaurants = getContactsByType("restaurant");
        //If there are less than k restaurants, whole list is returned.
        if(allRestaurants.length<k){
            return allRestaurants;
        }
        //If no restaurants are in database
        else if(allRestaurants.length == 0){
            return null;
        }
        else{
            Contact[] top = new Contact[k];
            int topRating = 0;
            int topIndex = 0;

            //First, find highest rating, then all those restaurants will be added
            //If there is still room in top list, go to next rating down to be added
            for(int i = 0; i<allRestaurants.length; i++){
                int rate = ((Restaurant)allRestaurants[i]).getRating();
                if(rate > topRating){
                    topRating = rate;
                }
            }

            //While th last spot in top array is empty, find next top restaurant
            while(top[k-1] == null){
                for(int i = 0; i<allRestaurants.length; i++){
                    int rate = ((Restaurant)allRestaurants[i]).getRating();
                    if(rate == topRating){
                        top[topIndex] = allRestaurants[i];
                        topIndex++;
                    }
                }
                topRating--;
            }
            return top;
        }
    }

    //For entire list of Contacts
    public String toString(){
        String dataString = "---CONTACTS:---";
        for(int i = 0; i<list.size(); i++ ){
            dataString = dataString + "\n" + "\n" + list.get(i);
        }
        return dataString + "\n" + "---END OF CONTACTS---";
    }

    //For other lists to check functionality
    public String toString(Contact[] array){
        String dataString = "---CONTACTS:---";
        for(int i = 0; i<array.length; i++ ){
            dataString = dataString + "\n" + "\n" + array[i];
        }
        return dataString + "\n" + "---END OF CONTACTS---";
    }


    public static void main(String[] args){
        ContactDatabase database = new ContactDatabase("array");
        //For trying same list as linked list:
        //ContactDatabase database = new ContactDatabase("linked");
        database.add(new Restaurant("Punch", 9414444, "here", "good pizza",5));
        database.add(new Restaurant("Mesa", 9414445, "here", "OK pizza",4));
        database.add(new Restaurant("Sandwich shop", 9414443, "here", "OK sandwiches",3));
        database.add(new Restaurant("Dining hall", 9414440, "here", "don't go here",0));
        database.add(new Friend("Angela", 902100, "955 Road", "sister", 19880212));
        database.add(new Friend("Bea", 902100, "955 Road", "sister's roommate", 19880312));
        database.add(new Friend("Me", 9090912, "404 Road", "buddy", 19951224));
        database.add(new Coworker("Boss", 9999999, "405 Road", "not buddy","imsocool@mail.com"));
        database.add(new Coworker("Arin", 8889999, "901 St", "work buddy","arin@workmail.com"));
        System.out.println(database.toString());
        System.out.println();
        database.remove(2);
        System.out.println("Index 2 removed:"+ "\n" + database.toString());
        System.out.println("Get index 2:"+ database.get(2));
        System.out.println();
        System.out.println("Find 'Angela':" + "\n" + database.find("Angela"));
        System.out.println();
        System.out.println("Find 'Obama':" + "\n" + database.find("Obama"));
        database.sort();
        System.out.println("---SORTED---" + "\n" + database.toString());
        System.out.println();
        System.out.println("Oldest friend: " + "\n" + database.getOldestFriend());
        System.out.println();
        System.out.println(database.getMailToLink());
        System.out.println();
        System.out.println("TOP RESTAURANTS" + "\n" + database.toString(database.getTopKRestaurants(2)));

    }
}
