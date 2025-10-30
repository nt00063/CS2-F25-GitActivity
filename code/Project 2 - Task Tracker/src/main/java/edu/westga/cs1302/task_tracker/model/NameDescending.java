package edu.westga.cs1302.task_tracker.model;

import java.util.Comparator;

/** Compare two Tasks to identify the correct Descending
 *  ordering of the tasks by their names (Z–A).
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class NameDescending implements Comparator<Task> {

	/** Returns a value indicating ordering of the two tasks
	 *  based on descending name order (Z–A).
	 * 
	 * @precondition o1 != null && o2 != null
	 * @postcondition none
	 * 
	 * @param o1 the first task to compare
	 * @param o2 the second task to compare
	 * 
	 * @return negative if o1’s name comes after o2’s,
	 * 		   zero if the same,
	 * 		   positive if before
	 */
	@Override
	public int compare(Task o1, Task o2) {
		if (o1 == null) {
			throw new IllegalArgumentException("o1 must not be null");
		}
		if (o2 == null) {
			throw new IllegalArgumentException("o2 must not be null");
		}
		return o2.getName().compareToIgnoreCase(o1.getName());
	}

	/** Returns the name of the comparator to represent it as a String.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return a String indicating sorting order
	 */
	@Override
	public String toString() {
		return "Name (Z→A)";
	}
}
