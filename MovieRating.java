package FinalProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;


public class MovieRating implements Rating<Movie> {
	String ratingFile;
	ArrayList<Movie> movies = null;
	MovieRating(String ratingFile){
		this.ratingFile = ratingFile;
		movies = readInputFile();
	}
	ArrayList<Movie> getMovies(){
		return movies;
	}
	
	void setMovies(ArrayList<Movie> movies){
		this.movies = movies;
	}
	
	private ArrayList<Movie> readInputFile(){
		ArrayList<Movie> lmovies = new ArrayList<>();
		if(this.ratingFile==null) return null;
		Scanner input;
		try{
			input = new Scanner(new File(ratingFile));
			while(input.hasNextLine())
			{
				String line = input.nextLine();
				String[] elements = line.split("\t");
				if(elements.length != 5) continue;
				String tmp = (new StringBuilder(elements[0])).reverse().toString();
				long prating = Long.parseLong(tmp.trim(),10);
				int popularity = Integer.parseInt(elements[1].trim());
				float rateScore = Float.parseFloat(elements[2].trim());
				int year = Integer.parseInt(elements[4].trim());
				Movie m = new Movie(prating, popularity, rateScore, elements[3], year);
				lmovies.add(m);
			}
			input.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		return (lmovies);
	}
		
	protected void produceOutput(ArrayList<Movie> movies, String query){
		Comparator<Movie> cmp = null;
		switch(query){
		case "percentageRating":
			cmp = new Comparator<Movie>(){
				public int compare(Movie m1, Movie m2){
					return (int) (m2.percentRating - m1.percentRating);
				}
			};
			break;
		case "ratingScores":
			cmp = new Comparator<Movie>(){
				public int compare(Movie m1, Movie m2){
					return (int) (m2.ratingScore - m1.ratingScore);
				}
			};
			break;
		case "seeingNumber":
			cmp = new Comparator<Movie>(){
				public int compare(Movie m1, Movie m2){
					return (int) (m2.numberWatched - m1.numberWatched);
				}
			};
			break;
		case "movieName":
			cmp = null;
			break;
		case "latestRelease":
			cmp = new Comparator<Movie>(){
				public int compare(Movie m1, Movie m2){
					return (int) (m2.year - m1.year);
				}
			};
			break;
		default:
			System.out.println("Wrong input type");
			return;
		}
		pickMax(cmp);
		pickMin(cmp);
	}

	@Override
	public Movie pickMax(Comparator<Movie> c) {
		MyPriorityQueue<Movie> pq = new MyPriorityQueue<>(movies, c);
		Movie m = pq.poll();
		System.out.println("The lowest is: " + m); 
		return m;
	}

	@Override
	public Movie pickMin(Comparator<Movie> c) {
		if(c==null){
			System.out.println("Reversing default comparable is not implemented");
			return pickMax(c);
		}
		else{
			Comparator<Movie> cc = new Comparator<Movie>(){
			public int compare(Movie m1, Movie m2){
				return (c.compare(m2, m1)); 
			}
		};
		MyPriorityQueue<Movie> pq = new MyPriorityQueue<>(movies, cc);
		Movie m = pq.poll();
		System.out.println("The highest is: " + m);
		return m;
		}
	}

	@Override
	public void add(Movie e) {
		movies.add(e);
	}

	@Override
	public boolean isEmpty() {
		if((movies == null) || (movies.size()==0))
		return true;
		else return false;
	}

	@Override
	public int size() {
		if((movies == null) || (movies.size()==0))
			return 0;
		else return movies.size();
	}
}
	
class Movie implements Comparable<Movie> {
	long percentRating;
	int numberWatched;
	float ratingScore;
	String name;
	int year;

	public Movie(long prating, int popularity, float rateScore, String string, int year){
		this.percentRating = prating;
		this.numberWatched = popularity;
		this.ratingScore = rateScore;
		this.name = string;
		this.year = year;
	}

	@Override
	public int compareTo(Movie o) {
		return (this.name.compareTo(o.name));
	}

	public String toString(){
		return "" + percentRating + "\t" + numberWatched + "\t" + ratingScore + "\t" + name + "\t" + year;
	}
}