package com.practice.functional;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 */
public class Person {

	enum Gender {
		MALE,
		FEMALE
	}

	private Integer id;
	private String firstName;
	private String lastName;
	private Integer age;
	private Gender gender;

	public Person(Integer id, String firstName, String lastName, Integer age, Gender gender) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
	}

	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Integer getAge() {
		return age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "[ " + id + " " + firstName + " " + lastName + " " + age + " " + gender + " " + "]";
	}

	public static Predicate<Person> isMale() {
		return p -> Person.Gender.MALE == p.gender;
	}

	public static Predicate<Person> greaterThan40() {
		return p -> p.age > 40;
	}


	public static List<Person> filterEmployees(List<Person> personList, Predicate<Person> predicate) {
		return personList.stream().filter(predicate).collect(Collectors.toList());
	}


}
