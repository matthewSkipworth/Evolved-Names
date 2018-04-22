//Written by Matthew Skipworth
//Latest Version 18 April 2018
import java.lang.reflect.Array;
import java.util.*;
/**
 * Class Genome contains methods that construct or operate on Genome objects.
 * 
 * @author Matthew Skipworth
 * @version 18 April 2018 
 */
public class Genome /*implements Comparable<Genome> */{
	String target = "CHRISTOPHER PAUL MARRIOTT";
	public static final int charArraySize = 28;

	public static final char[] charArray = {'A','B','C','D','E','F','G','H',
											'I','J','K','L','M','N','O','P',
											'Q','R','S','T','U','V','W','X',
											'Y','Z', ' ', '-'};
	String gename;
	double mutationRate;
	int fitness;
	Random randomObject = new Random();
/**
 * Class constructor. The method initializes the Genome's mutationRate and 
 * gename attribute.
 * 
 * @param mutationRate sets the mutationRate field for the object.
 * @return returns the Genome object.
 */
	Genome(double mutationRate) {
		gename = new String("A");
		// a constructor that initializes a Genome with value 'A' and assigns
		// the internal mutation rate. The mutationRate must be between zero 
		// and one.
		
		this.mutationRate = mutationRate;
		
	}
/**
 * Class constructor. The method essentially copies the Genome being passed in.
 * 
 * @param gene represents another Genome object to be copied.
 * @return returns the copy Genome object.
 */
	Genome(Genome gene) {
		// a copy constructor that initializes a Genome with the same values as 
		// the input gene.
		gename = gene.gename;
		this.mutationRate = gene.mutationRate;
	}
/**
 * Method mutate, like it sounds, mutates the Genome's gename (name of the 
 * Genome) field. It calls three helper methods addChar, removeChar, and 
 * changeChar.	
 */
	void mutate() {
		//Random randomObject = new Random();
		StringBuilder genString = new StringBuilder(gename);		
		if (chance(mutationRate)) {
			addChar(genString);
		}
		
		if (chance(mutationRate)) {
			removeChar(genString);
		}
		if (chance(mutationRate)) {
			changeChar(genString);
		}
	}
	// mutate helpers
/**
 * Method addChar adds a random character to a random position in the
 * gename field.
 * 
 * @param genString is a StringBuilder passed in to mutate the contents of
 * the gename field.
 */
	void addChar(StringBuilder genString) {

		//StringBuilder genString = new StringBuilder(gename);
		int charOffset = randomObject.nextInt(charArraySize);
		char newChar = charArray[charOffset];
		int offset = randomObject.nextInt(gename.length() + 1);
		genString.insert(offset, newChar); 
		gename = genString.toString();
	}
/**
 * Method removeChar removes a random character from the gename field.
 * 
 * @param genString is a StringBuilder passed in to mutate the contents of
 * the gename field.
 */
	void removeChar(StringBuilder genString) {
	//	StringBuilder genString = new StringBuilder(gename);
		randomObject = new Random();
		if (gename.length() > 1) {
			genString.deleteCharAt(
					randomObject.nextInt(gename.length()));
			gename = genString.toString();
		}
	}
/**
 * Method change Char swaps a character at a random index with a randomly
 * selected character from the charArray constant.
 * 	
 * @param genString is a StringBuilder passed in to mutate the contents of
 * the gename field.
 */
	void changeChar(StringBuilder genString) {
	//	StringBuilder genString = new StringBuilder(gename);
		randomObject = new Random();
		genString.setCharAt(randomObject.nextInt(gename.length()), 
							Array.getChar(charArray, 
							randomObject.nextInt(charArray.length)));
		gename = genString.toString();
	}
	// mutate helpers end
/**
 * Method crossover takes in a randomly selected Genome object other, and has 
 * the ability to swap a randomly selected character from the current Genome
 * with a randomly selected character from the Genome other.
 * 	
 * @param other
 */
	void crossover(Genome other) {
		StringBuilder genString = new StringBuilder();
		int max = Math.max(other.gename.length(), this.gename.length());
		for (int i = 0; i < max; i++) {
			double randouble = Math.random();
			if (randouble < 0.5 && i < other.gename.length()) {
				if (other.gename.charAt(i) == ' ') {
					break;
				}
				genString.append(other.gename.charAt(i));
			} else if (i < this.gename.length()) {
				if (this.gename.charAt(i) == ' ') {
					break;
				}
				genString.append(gename.charAt(i));
			}
		}
	}
/**
 * Method fitness computes the fitness of the Genome's gename compared to 
 * TARGET.	
 * 
 * @return Integer is the Genomes fitness score (lower is more fit). 
 */
	Integer fitness() {
		int n = gename.length();
		int m = target.length();
		int l = Math.max(n, m);
		int f = Math.abs(m - n) * 2;
		int t = f / 2;
		for (int i = 0; i < l - t; i++) {
			if (gename.charAt(i) != target.charAt(i)) {
				f++;
			}
		}
		this.fitness = f;
		return f;
	}
//Levenshtein fitness.
	/*public Integer fitness() {
		int i = 0, j = 0;
		int n = gename.length();
		int m = TARGET.length();
		int[][] matrix = new int[n + 1][m + 1];
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				if (gename.charAt(i - 1) == TARGET.charAt(j - 1)) {
					matrix[i][j] = matrix[i - 1][j - 1];
				} else {
					matrix[i][j] = Math.min(matrix[i - 1][j] + 1, Math.min(matrix[i][j - 1] + 1,
								  matrix[i - 1][j - 1] + 1));
				}
			}
		}
		//return matrix[i][j] + (Math.abs(n-m) + 1) / 2;
		//System.out.println(matrix[i][j] + (Math.abs(n-m) + 1) / 2);
		
	}*/
/**
 * Method chance returns a boolean value based on the double precision
 * floating point number passed in.	
 * 
 * @param percent represents a double precion floating point number less than
 * one.
 * @return returns a boolean value.
 */
	boolean chance(double percent) {
		boolean result = false;
		double randouble = Math.random();
		if (randouble < percent) {
			result = true;
		}
		return result;
	}
/**
 * Method toString returns the gename field of the Genome.
 * 
 * @return returns a String.	
 */
	public String toString() {
		return "Gene: " + gename +  " Fitness: " + this.fitness();
	}
}
