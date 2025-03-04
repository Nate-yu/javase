package oop;

public class testMain {
    public static void main(String[] args) {
        Person person1 = new Person(new Address("武汉"));
        Person person1Copy = (Person) person1.clone();
        System.out.println(person1.getAddress() == person1Copy.getAddress());
    }
}
