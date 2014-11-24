package programs.weighttraining;

/*
    Structure used by Exercise to log the individual
    exercise results
 */
public class Set {
    private short weight;
    private byte repitions;

    public Set(){
        repitions = 10;
    }

    public void setRepitions(byte repitions) {
        this.repitions = repitions;
    }

    public void setWeight(short weight) {
        this.weight = weight;
    }

    public int getRepitions() {
        return repitions;
    }

    public int getWeight() {
        return weight;
    }
}
