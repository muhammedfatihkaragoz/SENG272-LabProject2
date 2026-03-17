public class Criterion {

    private String name;
    private double weight;
    private String direction;
    private double minValue;
    private double maxValue;
    private String unit;
    private double measuredValue;

    public Criterion(String name,double weight,String direction,
                     double minValue,double maxValue,String unit){

        this.name=name;
        this.weight=weight;
        this.direction=direction;
        this.minValue=minValue;
        this.maxValue=maxValue;
        this.unit=unit;
        this.measuredValue=0;

    }

    public String getName(){
        return name;
    }

    public String getUnit(){
        return unit;
    }

    public String getDirection(){
        return direction;
    }

    public double getMeasuredValue() {
        return measuredValue;
    }

    public void setMeasuredValue(double measuredValue) {
        this.measuredValue = measuredValue;
    }

    public double getWeight() {
        return weight;
    }

    public double calculateScore(){

        if (maxValue == minValue) {
            return 1;
        }

        double score;

        if(direction.equalsIgnoreCase("higher")){
            score = 1 + 4 * (measuredValue - minValue) / (maxValue - minValue);
        }
        else if(direction.equalsIgnoreCase("lower")){
            score = 1 + 4 * (maxValue - measuredValue) / (maxValue - minValue);
        }
        else{return 1;}

        if(score<1){ score=1;}

        if(score>5){score=5;}

        score = Math.round(score * 2) / 2.0;
        return score;

    }
}
