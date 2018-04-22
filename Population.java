//Written by Matthew Skipworth
//Latest version: 18 April 2018
import java.util.*;
/**
 * class Population contains the state and behavior of the Population
 * object. In other words the methods in this class either construct a new 
 * Population object or mutate the Population object.
 * 
 * @author Matthew Skipworth
 * @version 18 April 2018
 */
public class Population {
	ArrayList<Genome> thePopulation;
	Genome mostFit; 
	int numGenomes;
/**
 * class Constructor.
 * 	
 * @param numGenomes is the number of Genomes contained in the Population 
 * object.
 * @param mutationRate represents the probability of which each Genome 
 * mutation occurs. 
 * @return returns the Population object.
 */
	Population(int numGenomes, double mutationRate) {
		this.numGenomes = numGenomes;
		thePopulation = new ArrayList<Genome>(numGenomes);
		for (int i = 0; i < numGenomes; i++) {
			Genome newGenome = new Genome(mutationRate);
			thePopulation.add(newGenome);
		}
		ArrayList<Genome> thePopulation = new ArrayList<Genome>(numGenomes);		
	}
/**
 * Method day removes the least fit half of the population and restores the
 * lost Genomes with new Genomes based on the remaining half.	
 */
	void day() {
		mergeSort(thePopulation);
		mostFit = thePopulation.get(0);
		removeLeastFit(thePopulation);
		restoreGenomes(thePopulation);
	}
/**
 * Method removeLeastFit removes the least fit half of the population.	
 * @param genomeList is the Population object from which the least fit Genomes
 * are being removed.
 */
	void removeLeastFit(ArrayList<Genome> genomeList) {
// idea from stack overflow: https://stackoverflow.com/questions/10797663/removing-range-tail-from-a-list
		genomeList.subList(genomeList.size() - genomeList.size() / 2, genomeList.size()).clear();
	}
// day helper methods
/**
 * Method restoreGenomes restores the Population back to the original Population
 * size by either cloning Genomes from the remaining Population or cloning a 
 * remaining Genome and performing a Genome crossover() and then performing a
 * Genome mutate(). Either of these have equal probability of occurring.
 * 
 * @param genomeList represents the Population of which the number of Genomes 
 * is to be restored.
 */
	void restoreGenomes(ArrayList<Genome> genomeList) {
		while(genomeList.size() < numGenomes) {
			double randouble = Math.random();
			if (randouble < 0.5) {
				Genome newGene = new Genome(randomGenome());
				newGene.mutate();
				this.thePopulation.add(newGene);
			} else {
				Genome newGene = new Genome(randomGenome());
				newGene.crossover(randomGenome());
				newGene.mutate();
				this.thePopulation.add(newGene);
			} 
		} 
	}
/**
 * Method randomGenome picks a random Genome out of the Population.
 * 	
 * @return returns a random Genome from the Population.
 */
	Genome randomGenome() {
		Random newRand = new Random();
		return this.thePopulation.get(newRand.nextInt(this.thePopulation.size()));
	}
/**
 * Method toString returns the Population's Genome's genames.	
 */
	public String toString() {
		return thePopulation.toString();
	}
// end day helper methods
	
// Merge Sort Algorithm taken from Christopher Marriott's posted Canvas Files. 
/**
 * Method merge which I borrowed from Christopher Marriott's uploaded Canvas
 * files, merges the Population back together after mergeSort is performed.
 * 	
 * @param list represents the Population.
 * @param start represents the first Genome in the Population.
 * @param finish represents the last Genome in the Population.
 */
	public static void merge(List<Genome> list, int start, int finish){
		int mid = (start+finish)/2;
		int i = start;
		int j = mid + 1;
		List<Genome> temp = new ArrayList<Genome>();
		while(i < mid + 1 && j < finish + 1) {
			temp.add(list.get(i).fitness() < list.get(j).fitness() ? list.get(i++) : list.get(j++));
		}
		while(i<mid +1){
			temp.add(list.get(i++));
		}
		for(i = 0;i<temp.size();i++){
			list.set(start+i, temp.get(i));
		}
	}
/**
 * Method mergeSort is a helper method for the other mergeSort method.
 * The purpose of this method is to pick the first and last Genomes out of the
 * Population. This makes calling the method a cleaner process since all you'd 
 * have to pass in is the name of the Population itself.
 *  	
 * @param list represents the Population.
 */
	public static void mergeSort(List<Genome> list) {
		mergeSort(list,0,list.size()-1);
	}
/**
 * Method mergeSort Splits the Population up into smaller chunks to be sorted.
 * 	
 * @param list represents the Population.
 * @param start represents the first Genome in the Population.
 * @param finish represents the last Genome in the Population.
 */
	public static void mergeSort(List<Genome> list, int start, int finish){
		if(start < finish){
//			System.out.println(start + " " + finish + " " + list);
			int mid = (start + finish)/2;
			mergeSort(list, start, mid);
			mergeSort(list, mid+1, finish);
			merge(list, start,finish);
//			System.out.println(start + " " + finish + " " + list);
		}
	}
/**
 * method getNumGenomes returns the number of Genomes in the Population.	
 * 
 * @return returns the number of Genomes in the Population.
 */
	public int getNumGenomes() {
		return numGenomes;
	}
}