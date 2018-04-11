
public class Genome {
	public static final String TARGET = "CHRISTOPHER PAUL MARRIOTT";
	String current;
	double mutationRate;
	
	Genome(double mutationRate) {
		if (mutationRate > 1 || mutationRate < 0) {
			System.out.println("incorrect mutation rate.");
			return;
		}
		current = "A";		
		this.mutationRate = mutationRate;
	}
	Genome(Genome gene) {
		current = gene.current;
		this.mutationRate = gene.mutationRate;
	}
	void mutate() {
		double randouble = Math.random();
		if (randouble < this.mutationRate) {
			//add a randomly selected char to a randomly
			//selected position in the string target
		}
	}
	void crossover(Genome other) {
		
	}
	Integer fitness() {
		
	}
	String toString() {
		
	}
} 
