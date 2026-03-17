import java.util.ArrayList;

public class QualityDimension {

    private String name;
    private String isoCode;
    private double weight;
    private ArrayList<Criterion> criteria;


    public QualityDimension(String name,String isoCode,double weight){
        this.name=name;
        this.isoCode=isoCode;
        this.weight=weight;
        this.criteria=new ArrayList<Criterion>();
    }

    public String getName() {
        return name;
    }

    public String getIsoCode(){
        return isoCode;
    }

    public ArrayList<Criterion> getCriteria() {
        return criteria;
    }

    public void addCriterion(Criterion c){
        criteria.add(c);
    }

    public double getWeight(){
        return weight;
    }

    public double calculateDimensionScore(){
        double total=0.0;
        double totalWeight=0.0;

        for(Criterion criterion:criteria){
            total+=criterion.calculateScore() * criterion.getWeight();
            totalWeight+=criterion.getWeight();
        }

        if(totalWeight==0){return 0;}

        return total/totalWeight;
    }

    public String qualityLabel(){
        double score=calculateDimensionScore();

        if(score<=5.0 && score >=4.5)
            return "Excellent Quality";

        if(score<=4.4 && score >=3.5)
            return "Good Quality";

        if(score<=3.4 && score >=2.5)
            return "Needs Improvement";

        else
            return "Poor Quality";

    }

    public double qualityGap(){
        return 5.0-calculateDimensionScore();
    }



}

