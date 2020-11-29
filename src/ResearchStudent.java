
public class ResearchStudent extends Student {
    
    private double proposal;
    private double oral;
    private double thesis;
    private double overallMarks;
    private String finalGrade;

    public ResearchStudent(String title, String firstName, String lastName, long studentId, int day, int month, int year, double proposal, double oral, double thesis) {
        super(title, firstName, lastName, studentId, day, month, year);
        this.proposal = proposal;
        this.oral = oral;
        this.thesis = thesis;        
    }
    
    public void setProposal(double proposal) {
        this.proposal = proposal;
    }
    
    public double getProposal() {
        return proposal;
    }
    
    public void setOral(double oral) {
        this.oral = oral;
    }
    
    public double getOral() {
        return oral;
    }
    
    public void setThesis(double thesis) {
        this.thesis = thesis;
    }
    
    public double getThesis() {
        return thesis;
    }

    @Override
    public double computeOverallMarks() {
        overallMarks = (proposal/100 * 35) + (oral/100 * 15) + (thesis/100 * 50);
        return overallMarks;
    }
    
    @Override
    public String computeFinalGrade() {
         if (overallMarks >= 80) {
             finalGrade = "HD";
         } else if (overallMarks >= 70) {
             finalGrade = "D";
         } else if (overallMarks >= 60) {
             finalGrade = "C";
         } else if (overallMarks >= 50) {
             finalGrade = "P";
         } else {
             finalGrade = "N";
         }
         return finalGrade;
     }
    
    @Override
    public double getOverallMarks() {
         return overallMarks;
    }
     
    @Override
    public String toString() {
        return super.toString() + " " + proposal + " " + oral + " " + thesis + " " + overallMarks + " " + finalGrade;
    }
}