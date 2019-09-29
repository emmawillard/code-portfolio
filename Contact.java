/**Emma Willard
 * (willa115, 5040938)
 From Moodle for Project 3*/
public class Contact implements Comparable<Contact> {
    private String name;
    private long phone;
    private String address;
    private String comments;

    public Contact(){}

    public Contact(String name, long phone, String address, String comments) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean equals(Contact c) {
        return name.equals(c.getName()) && phone == c.getPhone() && address.equals(c.getAddress()) && comments.equals(c.getComments());
    }

    public int compareTo(Contact c) {
        return name.compareTo(c.name);
    }
}
