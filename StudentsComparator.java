package FinalProject;

public class StudentsComparator {
	 public int compare(Movie s1, Movie s2)
     {
      //Highest
      return Integer.compare(s2.getNumberStudents(), s1.getNumberStudents());
     }
}
