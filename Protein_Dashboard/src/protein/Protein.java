package protein;

public class Protein {
    private int id;
    private String proteins;
    private String sequences;
    private int length;
    private String annotations;

    public Protein() {
    }

    public Protein(int id, String proteins, String sequences, int length, String annotations) {
        this.id = id;
        this.proteins = proteins;
        this.sequences = sequences;
        this.length = length;
        this.annotations = annotations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProteins() {
        return proteins;
    }

    public void setProteins(String proteins) {
        this.proteins = proteins;
    }

    public String getSequences() {
        return sequences;
    }

    public void setSequences(String sequences) {
        this.sequences = sequences;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int id) {
        this.id = length;
    }

    public String getAnnotations() {
        return annotations;
    }

    public void setAnnotations(String annotations) {
        this.annotations = annotations;
    }


    @Override
    public String toString() {
        return "Cafa{" +
                "id=" + id +
                ", proteins='" + proteins + '\'' +
                ", sequences='" + sequences + '\'' +
                ", length='" + length + '\'' +
                ", annotations='" + annotations + '\'' +
                '}';
    }
}
