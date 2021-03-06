package com.westboy.atomic;


import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<Person> reference = new AtomicReference<>();

        AtomicReference<Person> person = new AtomicReference<>(new Person("xiaowang", 18));


        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Thread(() -> {
                Person person1 = new Person();
                person1.setName("xiaowang" + finalI);
                person1.setAge(20 + finalI);
                person.compareAndSet(null, person1);
            }).start();
        }


    }

    static class Person {
        private String name;
        private Integer age;

        public Person() {
        }

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
