package qct.pojo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by alex on 2014/8/23.
 */
@XmlRootElement
public class User {
    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
