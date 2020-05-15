package com.westboy.tmp;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * @author westboy
 * @since 2019/11/14
 */
public class Demo {

	public static void main(String[] args) throws Exception {
		Class<Person> personClass = Person.class;
		Field[] fields = personClass.getDeclaredFields();

		System.out.println(Arrays.toString(fields));

		// for (Field field : fields) {
		// 	field.setAccessible(true);
		//
		// }

		Person person1 = new Person("", 1);
		Person person2 = new Person("", 1);

		notEqualFields(person1, person2, Person.class);


	}

	public static List<String> notEqualFields(Person person1, Person person2, Class<Person> clazz) throws NoSuchFieldException, IllegalAccessException {
		// Field[] fields = clazz.getDeclaredFields();
		// for (Field field : fields) {
		// 	field.setAccessible(true);
		// 	System.out.println(field.getName());
		// 	System.out.println(field.getType());
		// 	System.out.println(field.get(field.getName()));;
		// }

		return null;
	}

}


class Person{
	private String name;
	int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
}
