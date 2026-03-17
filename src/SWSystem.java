import java.util.ArrayList;

public class SWSystem {

    private String name;
    private String category;
    private String version;
    private ArrayList<QualityDimension>qualityDimensions;

    public SWSystem(String name,String category,String version){

        this.name=name;
        this.category=category;
        this.version=version;
        qualityDimensions=new ArrayList<QualityDimension>();
    }

    public String getName(){
        return name;
    }

    public ArrayList<QualityDimension> getDimensions(){
        return  qualityDimensions;
    }

    public void addDimension(QualityDimension qualityDimension){
        qualityDimensions.add(qualityDimension);
    }

    public double calculateOverallScore(){

        double total=0.0;
        double totalWeight=0.0;

        for(QualityDimension qualityDimension:qualityDimensions){

            total+=qualityDimension.calculateDimensionScore()*qualityDimension.getWeight();
            totalWeight+=qualityDimension.getWeight();

        }

        if(totalWeight==0){return 0;}

        return total/totalWeight;
    }

    public QualityDimension findWeakestDimension(){

        if(qualityDimensions.isEmpty()){return null;}

        QualityDimension weakest = qualityDimensions.get(0);
        for(QualityDimension qualityDimension:qualityDimensions){

            if(qualityDimension.calculateDimensionScore()<weakest.calculateDimensionScore())
                weakest=qualityDimension;
        }
        return weakest;
    }

    public void report(){

        System.out.println("========================================");
        System.out.println("SOFTWARE QUALITY EVALUATION REPORT (ISO/IEC 25010)");
        System.out.println("System: " + name + " v" + version + " (" + category + ")");
        System.out.println("========================================");
        System.out.println();

        for (QualityDimension qualityDimension : qualityDimensions) {
            System.out.println("--- " + qualityDimension.getName() + " [" + qualityDimension.getIsoCode()
                    + "] (Weight: " + (int) qualityDimension.getWeight() + ") ---");

            for (Criterion criterion : qualityDimension.getCriteria()) {
                String s;

                if (criterion.getDirection().equalsIgnoreCase("higher")) {
                    s = "Higher is better";
                } else {
                    s = "Lower is better";
                }

                System.out.println(criterion.getName() + ": " + criterion.getMeasuredValue()
                        + " " + criterion.getUnit() + " -> Score: " + criterion.calculateScore()
                        + " (" + s + ")");
            }

            System.out.println(">> Dimension Score: " + qualityDimension.calculateDimensionScore() +
                    "/5 [" + qualityDimension.qualityLabel() + "]");

            System.out.println();
        }

        double overall = calculateOverallScore();
        overall = Math.round(overall * 10) / 10.0;

        String overallLabel;

        if (overall >= 4.5) {
            overallLabel = "Excellent Quality";
        }
        else if (overall >= 3.5) {
            overallLabel = "Good Quality";
        }
        else if (overall >= 2.5) {
            overallLabel = "Needs Improvement";
        }
        else {
            overallLabel = "Poor Quality";
        }

        System.out.println("========================================");
        System.out.println("OVERALL QUALITY SCORE: " + overall +
                "/5 [" + overallLabel + "]");
        System.out.println("========================================");
        System.out.println();

        System.out.println("GAP ANALYSIS (ISO/IEC 25010)");
        System.out.println("========================================");

        QualityDimension weakest = findWeakestDimension();

        if (weakest != null) {
            System.out.println("Weakest Characteristic : " + weakest.getName() +
                    " [" + weakest.getIsoCode() + "]");

            System.out.println("Score: " + weakest.calculateDimensionScore() +
                    "/5  |  Gap: " + weakest.qualityGap());

            System.out.println("Level: " + weakest.qualityLabel());

            System.out.println(">> This characteristic requires the most improvement.");
        }

        System.out.println("========================================");
    }






}


