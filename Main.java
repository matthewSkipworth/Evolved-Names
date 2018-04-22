//Written by Matthew Skipworth
//Latest Version 18 April 2018
/**
 * Class Main drives the program.
 * 
 * @author Matthew Skipworth
 * @version 18 April 2018
 */
public class Main {
/**
 * method main runs the program.	
 * @param args isn't used in this program however it would normally 
 * represent space separated text passed into the terminal. 
 */
	public static void main(String[] args) { 
		int count = 0;
		Population newPop = new Population(100, 0.05);
		do {
			count++;
			System.out.println(newPop.mostFit);
			newPop.day();
		} while (newPop.mostFit.fitness() != 0);
		System.out.println(newPop.mostFit);
		System.out.println("\n" + count);
		//testGenome();
		//testPopulation();
	}
/**
 * Method testGenome tests all methods of the Genome class.	
 */
	static void testGenome() {
		Genome testGene = new Genome(0.5);
		Genome testGene2 = new Genome(0.5);
		testGene2.gename.replaceAll(testGene2.gename, "MATTHEW FRANCIS SKIPWORTH");
		StringBuilder testSBuilder = new StringBuilder(testGene.gename);
		System.out.println(testGene);
		for (int i = 0; i < 30; i ++) {
			testGene.addChar(testSBuilder);
			System.out.println(testGene);
		}
		for (int i = 0; i < 30; i ++) {
			testGene.changeChar(testSBuilder);
			System.out.println(testGene);
		}
		for (int i = 0; i < 30; i ++) {
			testGene.crossover(testGene2);
			System.out.println(testGene);
		}
		for (int i = 0; i < 30; i ++) {
			testGene.removeChar(testSBuilder);
			System.out.println(testGene);
		}
		
		System.out.println(testGene.target.length());
		for (int i = 0; i < 10; i++) {
			System.out.println(testGene.chance(testGene.mutationRate));
		}
	}
/**
 * Method testPopulation tests all methods of the Population class.	
 */
	static void testPopulation() {
		Population testPop = new Population(10, .5);
		for (int i = 0; i < 20; i++) {
			testPop.day();
		}	
		System.out.println(testPop.toString());
		Population.mergeSort(testPop.thePopulation);
		System.out.println(testPop.toString());
		System.out.println(testPop.thePopulation.size());
		testPop.removeLeastFit(testPop.thePopulation );
		System.out.println(testPop.toString());
		System.out.println(testPop.thePopulation.size());
		testPop.restoreGenomes(testPop.thePopulation);
		System.out.println(testPop.toString());
		System.out.println(testPop.thePopulation.size());		
		System.out.println(testPop.thePopulation.indexOf(testPop.randomGenome()));
		
	}
}
