package FinalProject;

import java.io.*;
import java.util.*;

public class Runner {

	public static void main(String[] args) throws IOException {
		File file = new File ("movieRatingAll.txt");
		Scanner lc = new Scanner(file);

		Movie[] movies = null;

		int size = 0;
		String row = "";

		while(lc.hasNextLine())
		{
			row = lc.nextLine().trim();
			if (!row.equals(""))
			{
				size++;
			}
		}       
		lc.close(); 

		movies = ReadInput(file, size);

		int count = movies.length;
		Movie[] arrayList = new Movie[count];

		for (int i=0; i<count; i++) {
			arrayList[i] = movies[i];
		}

		PrintFile(arrayList);
		System.out.println("\nTotal Movies on File: " + size +"\n"); 

		PercentageComparator VPComparator = new PercentageComparator();
		MyPriorityQueue<Movie> priorityQueue = new MyPriorityQueue<Movie>(10, VPComparator);

		for (int i=0; i<count; i++)  {
			priorityQueue.add(movies[i]);
		}

		Movie headVP =  priorityQueue.peek(); 
		System.out.println("Highest Viewer Percentage: " + headVP.getViewersPercentage());
		System.out.println("\nMovie With The Highest Percentage: " + headVP.getMovieName()); 

		AverageRatingComparator ARComparator = new AverageRatingComparator();
		MyPriorityQueue<Movie> priorityQueueAR = new MyPriorityQueue(10, (Comparator)ARComparator);

		for (int i=0; i<count; i++)
		{
			priorityQueueAR.add(movies[i]);
		}

		Movie headAR =  priorityQueueAR.peek(); 
		System.out.println("\nMovie With The Highest Rating: " + headAR.getMovieName() + " (Rating: "+ headAR.getAverageRating() + ")"); 


		/*
		 * Most viewed
		 *  
		 */

		StudentsComparator MWComparator = new StudentsComparator();
		PriorityQueue<Movie> priorityQueueMW = new PriorityQueue<Movie>(10, (Comparator<? super Movie>) MWComparator);

		for (int i=0; i<count; i++)
		{
			priorityQueueMW.add(movies[i]);
		}

		Movie headStudents =  priorityQueueMW.peek(); 
		System.out.println("\nMost Viewed Movie: " + headStudents.getMovieName() 
				+ " (Viewed By " + headStudents.getNumberStudents() + " Students)"); 






	}
	public static void PrintFile(Movie[] output)
	{
		int size = output.length;

		String vpcent = ""; 
		int numStudent = 0; 
		double avgRte = 0; 
		String moveiName = ""; 
		int year = 0;
		int nameLength = 0;

		printoutputHeader();

		for(int i = 0; i < size; i++)
		{       
			vpcent = ""; 
			numStudent = 0; 
			avgRte = 0; 
			moveiName = ""; 
			year = 0;

			vpcent = output[i].getViewersPercentage();
			numStudent = output[i].getNumberStudents();
			avgRte = output[i].getAverageRating();
			moveiName = output[i].getMovieName();

			nameLength = 34 - (moveiName.length());
			moveiName = output[i].getMovieName() + Space(nameLength);

			year = output[i].getMovieReleaseYear();

			System.out.println("\n" + vpcent + "\t\t"
					+ numStudent+ "\t\t"
					+ avgRte+ "\t\t\t"
					+ moveiName + "\t\t "
					+ year);

		}                            
	}
	private static String Space(int nameLength) {

		String space = "";
		for (int i=0; i < nameLength; i++)
		{           
			space = space + " ";
		}
		return space;       

	}
	private static void printoutputHeader() {
		System.out.println(""); 
		System.out.println("\t\t\t\t\tMovie Ratings\t\t") ;
		System.out.println("\t\t\t\t\t============");
		System.out.println("\nViewer Percentage\tStudents\tAverage Ratings\t\tMovie Name\t\t\t\t\tYear");
		System.out.println("===================\t=========\t===============\t\t==========\t\t\t\t\t====");  

	}
	public static Movie[] ReadInput(File file, int size) throws IOException {
		Movie [] output = new Movie[size];
		Scanner sc = new Scanner(file); 

		int count = 0;
		String vpcent = ""; 
		int numStudent = 0; 
		double avgRte = 0; 
		String StrMovieName = "";
		String moveiName = "";
		int year = 0;
		String row;
		String strYear = "";

		while(sc.hasNextLine())
		{
			row = sc.nextLine().trim();

			if (!row.equals(""))
			{
				String[] rowContent = row.split("\t");


				if (rowContent.length > 1)
				{

					vpcent = rowContent[0];
					numStudent =  Integer.parseInt(rowContent[1]);
					avgRte = Double.parseDouble(rowContent[2]);
					StrMovieName = rowContent[3].trim();              

					String[] yearMovie = StrMovieName.split("\"");

					if (yearMovie.length == 2) {
						moveiName = StrMovieName;
						year = 0;
					}

					if (yearMovie.length == 3) {

						moveiName = yearMovie[1];
						strYear = yearMovie[2];

						year = Integer.parseInt(yearMovie[2].trim());                	 

					}

					output[count] = new Movie(vpcent, numStudent, avgRte, moveiName, year);
					count++; 
				}
				else
				{
					System.out.println(); 

				}            
			}
		}       
		sc.close();        
		return output;  
	}

}
