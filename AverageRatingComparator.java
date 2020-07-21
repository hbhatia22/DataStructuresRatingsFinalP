package FinalProject;

public class AverageRatingComparator {
	public int compare(Movie s1, Movie s2)
    {
        return Double.compare(s2.getAverageRating(), s1.getAverageRating());
    }
}
