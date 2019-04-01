import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Test {

    public static void main(String[] args) {
        String[] sd = {"hello", "world"};
        List list = Arrays.asList(sd).stream()
                .map(word -> word.split(""))

                .collect(toList());

        list  =Arrays.asList(sd).stream()
                .flatMap(word -> Arrays.asList(word.split("")).stream())
                .collect(toList());
        Optional<Student> reOp = Optional.ofNullable(null);
        List ll = new ArrayList();
        ll.add(1);
        ll.add(1);

        Student ne  =  reOp.orElse(new Student("hah",12));
        System.out.println(ne);

    }

}

class Student{
    String name;
    int age;

    @Override
    public String toString() {

        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}