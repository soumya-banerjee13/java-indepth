package edu.soumya.java.indepth.example;

import java.util.HashSet;

/**
 * @author Soumya 
 * 
 * Classes implementing hashCode for inserting objects of that
 *         class in some HashMap or HashSet should also implement equals method
 *         moreover hashCode and equals should be consistent i.e. if two objects
 *         are equal by equal method should return same hashCode although
 *         reverse might not be true i.e. if two objects are not equal by equals
 *         method they can return same hashCode Below code is an example of
 *         violation of this rule. Run this code to understand it better. Play
 *         with the code to understand in depth
 */
public class ConflictingEqualHashTest {

	public static void main(String args[]) {
		Student s1 = new Student(1, "uvw");
		Student s2 = new Student(1, "xyz");
		HashSet<Student> set = new HashSet<>();
		set.add(s1);
		set.add(s2);
		System.out.println(set);

		Student s3 = new Student(3, "uvw");

		// s1 not equals s3 as id are different
		// s1 equals s2 as id are same
		System.out.println("s3 equals s1 ? " + s1.equals(s3));
		System.out.println("s2 equals s1 ? " + s1.equals(s2));
		// hashCode of s1 and s3 are same
		System.out.println("s1 hash = " + s1.hashCode());
		System.out.println("s2 hash = " + s2.hashCode());
		System.out.println("s3 hash = " + s3.hashCode());

		/*
		 * Although same hashCode of s3 is there in the set, contains method
		 * check for equals method also set contains s3 returns false.
		 */
		System.out.println("set contains:s3? " + set.contains(s3));
		System.out.println("set contains:s2? " + set.contains(s2));
		System.out.println("set contains:s1? " + set.contains(s1));
	}
}

class Student {

	String name;
	int id;

	public Student(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/*
	 * hashCode depends on name only
	 */
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	/*
	 * equals depends on id only
	 */
	@Override
	public boolean equals(Object obj) {
		if (id == ((Student) obj).id)
			return true;
		return false;
	}

	@Override
	public String toString() {
		return "Student{" + "name='" + name + '\'' + '}';
	}

}
