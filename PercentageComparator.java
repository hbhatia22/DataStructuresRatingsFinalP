package FinalProject;

public class PercentageComparator {
    public int compare(Movie s1, Movie s2)
    {
 	   long perc1 = Long.parseLong(s1.getViewersPercentage()); 
        long perc2 = Long.parseLong(s2.getViewersPercentage());
                 
        return Long.compare(perc2, perc1);
    }


}
