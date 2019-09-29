/**Emma Willard
 * (willa115, 5040938)
 */
public class Coworker extends Contact{
    private String email;
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public Coworker(String name, long phone, String address, String comments, String email){
        super.setName(name);
        super.setPhone(phone);
        super.setAddress(address);
        super.setComments(comments);
        this.email = email;
    }
    public String toString(){
        return getName() + "\n" + getPhone() + "\n" + getAddress() + "\n" + getComments() + "\n" + getEmail();
    }
}
