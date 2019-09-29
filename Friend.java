/**Emma Willard
 * (willa115, 5040938)
 */
public class Friend extends Contact{
    // Birthday is YYYYMMDD
    private int birthday;
    public int getBirthday(){
        return birthday;
    }
    public void setBirthday(int birthday){
        this.birthday = birthday;
    }

    public Friend(String name, long phone, String address, String comments, int birthday){
        super.setName(name);
        super.setPhone(phone);
        super.setAddress(address);
        super.setComments(comments);
        this.birthday = birthday;
    }
    public String toString(){
        return getName() + "\n" + getPhone() + "\n" + getAddress() + "\n" + getComments() + "\n" + getBirthday();
    }
}
