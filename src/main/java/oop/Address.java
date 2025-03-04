package oop;

public class Address implements Cloneable {
    private String name;
    
    // 构造方法
    public Address(String name) {
        this.name = name;
    }

    // Getter方法
    public String getName() {
        return name;
    }

    // Setter方法
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Address clone() {
        try {
            return (Address) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

