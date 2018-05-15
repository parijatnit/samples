package com.practice.functional;

import com.practice.functional.comparator.ChainedComparator;
import org.junit.Test;

import java.util.*;

/**
 *
 * Unit test class for {@link Person}
 * Created by pbhattacharya on 3/27/17.
 */
public class PersonTest {

	@Test
	public void testFilterByGender() {

		List<Person> personList = new ArrayList<>();

		personList.addAll(Arrays.asList(
						new Person(1, "Ram", "Das", 43, Person.Gender.MALE),
						new Person(2, "Mahima", "Das", 20, Person.Gender.FEMALE),
						new Person(3, "Sandra", "Lawson", 26, Person.Gender.FEMALE),
						new Person(4, "Shankar", "Mukherjee", 54, Person.Gender.MALE),
						new Person(5, "Tijuan", "Sanki", 40, Person.Gender.MALE),
						new Person(6, "Martin", "Ramados", 40, Person.Gender.MALE),
						new Person(7, "Juan", "Delgado", 40, Person.Gender.MALE),
						new Person(8, "Anky", "Banerjee", 30, Person.Gender.FEMALE),
						new Person(9, "Raima", "Sen", 35, Person.Gender.FEMALE),
						new Person(10, "Moonmoon", "Sen", 65, Person.Gender.FEMALE),
						new Person(11, "Rahim", "Sheikh", 21, Person.Gender.MALE),
						new Person(12, "Parijat", "Bhattacharya", 31, Person.Gender.MALE),
						new Person(13, "Woland", "Fans", 39, Person.Gender.MALE)));


		// Print all the Male persons
		System.out.println("****** Male Employees *****");
		List<Person> list = Person.filterEmployees(personList, Person.isMale());
		list.forEach(System.out::println);
		System.out.println("************************");
		System.out.println("                        ");
		System.out.println("                        ");

		// Print all the Female persons
		System.out.println("***** Female Employees *****");
		list = Person.filterEmployees(personList, p -> p.getGender() == Person.Gender.FEMALE);
		list.forEach(System.out::println);
		System.out.println("************************");
		System.out.println("                        ");
		System.out.println("                        ");

		// Print age > 40
		System.out.println("************ Greater than 40 ************");
		list = Person.filterEmployees(personList, Person.greaterThan40());
		list.forEach(System.out::println);
		System.out.println("************************");
		System.out.println("                        ");
		System.out.println("                        ");

	}

	@Test
	public void testOrderByAgeAndFirstName() {
		List<Person> personList = new ArrayList<>();

		personList.addAll(Arrays.asList(
				new Person(1, "Ram", "Das", 43, Person.Gender.MALE),
				new Person(2, "Mahima", "Das", 20, Person.Gender.FEMALE),
				new Person(3, "Sandra", "Lawson", 26, Person.Gender.FEMALE),
				new Person(4, "Shankar", "Mukherjee", 54, Person.Gender.MALE),
				new Person(5, "Tijuan", "Sanki", 40, Person.Gender.MALE),
				new Person(6, "Martin", "Ramados", 40, Person.Gender.MALE),
				new Person(7, "Juan", "Delgado", 40, Person.Gender.MALE),
				new Person(8, "Anky", "Banerjee", 30, Person.Gender.FEMALE),
				new Person(9, "Raima", "Sen", 35, Person.Gender.FEMALE),
				new Person(10, "Moonmoon", "Sen", 65, Person.Gender.FEMALE),
				new Person(11, "Rahim", "Sheikh", 21, Person.Gender.MALE),
				new Person(12, "Parijat", "Bhattacharya", 31, Person.Gender.MALE),
				new Person(13, "Woland", "Fans", 40, Person.Gender.MALE)));


		final Comparator<Person> ageComparator = (p1, p2) -> p1.getAge() - p2.getAge();
		final Comparator<Person> firstNameComparator = (p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName());
		Collections.sort(personList, new ChainedComparator<>(ageComparator, firstNameComparator));
		personList.forEach(System.out::println);
	}

	protected static ThreadLocal<String> companyThreadLocal = new ThreadLocal<String>();

	@Test
	public void testStringTrim() {
		String val = companyThreadLocal.get();

		System.out.println("Printing "+val+"now");
	}

	@Test
	public void testIntegerAutoBoxing() {
		int i = 10;




	}







}
