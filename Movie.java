package FinalProject;

public class Movie implements Comparable<Movie> {
	private String viewersPercentage;
	private int numberStudents;
	private double averageRating;
	private String movieName;
	private int movieReleaseYear;

	public Movie() {
		viewersPercentage = "";
		numberStudents = 0;
		averageRating = 0;
		movieName = "";
		movieReleaseYear = 0;
	}

	public Movie(String viewersPercentage, int numStudent,  double avgRte, String movieName, int year)
	{
		this.viewersPercentage = viewersPercentage;
		this.numberStudents = numStudent;
		this.averageRating = avgRte;
		this.movieName = movieName;
		this.movieReleaseYear = year;    
	}
	//Get Viewer Pecentage
	public String getViewersPercentage() {
		return viewersPercentage;
	}

	public void setViewersPercentage(String viewersPercentage) {
		this.viewersPercentage = viewersPercentage;
	}
	//Get Number of students
	public int getNumberStudents() {
		return numberStudents;
	}

	public void setNumberStudents(int numberStudents) {
		this.numberStudents = numberStudents;
	}
	//Get Average Rating
	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}
	// Get Movie Name
	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	//Get Movie Release year
	public int getMovieReleaseYear() {
		return movieReleaseYear;
	}

	public void setMovieReleaseYear(int movieReleaseYear) {
		this.movieReleaseYear = movieReleaseYear;
	}

	public int compareTo(Movie1 o) {
		return (this.movieName.compareTo(o.movieName));
	}

	public String toString() {
		return "" + viewersPercentage + "\t" + numberStudents + "\t" 
				+ averageRating + "\t" + movieName + "\t" + movieReleaseYear;
	}

	@Override
	public int compareTo(Movie o) {
		// TODO Auto-generated method stub
		return (this.movieName.compareTo(o.movieName));
	}

	


}
