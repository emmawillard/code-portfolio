/**Emma Willard
 * (willa115, 5040938)
 */
public class Restaurant extends Contact{
    private int rating;
    public int getRating(){
        return rating;
    }
    public Restaurant(String name, long phone, String address, String comments, int rating){
        super.setName(name);
        super.setPhone(phone);
        super.setAddress(address);
        super.setComments(comments);
        this.rating = rating;
    }
    public String toString(){
        return getName() + "\n" + getPhone() + "\n" + getAddress() + "\n" + getComments() + "\n" + getRating();
    }
}
