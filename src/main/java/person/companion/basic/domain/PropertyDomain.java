package person.companion.basic.domain;

/**
 * 功能描述：
 *
 * @author companion
 * @date 2020/6/2 10:27
 */
public class PropertyDomain {
    private int age;

    private String role;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setName(String name){

    }

    public String getName(){
        return "name";
    }
}
