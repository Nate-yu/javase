package oop;

public class Person implements Cloneable {
    private Address address;
    
    // 构造方法
    public Person(Address address) {
        this.address = address;
    }

    // Getter方法
    public Address getAddress() {
        return address;
    }

    // Setter方法
    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public Person clone() {
        try {
            Person person = (Person) super.clone();
//            return person;  // 浅拷贝，不对address进行clone
            person.setAddress(person.getAddress().clone()); // 深拷贝
            return person;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}