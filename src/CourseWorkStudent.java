
public class CourseWorkStudent extends Student {
    
    private double assignmentOne;
    private double assignmentTwo;
    private double weeklyPractical;
    private double finalExam;
    private double overallMarks;
    private String finalGrade;
    
    public CourseWorkStudent(String title, String firstName, String lastName, long studentId, int day, int month, int year, double assignmentOne, double assignmentTwo, double weeklyPractical, double finalExam) {
        super(title, firstName, lastName, studentId, day, month, year);
        this.assignmentOne = assignmentOne;
        this.assignmentTwo = assignmentTwo;
        this.weeklyPractical = weeklyPractical;
        this.finalExam = finalExam;
    }
    
    public void setAssignmentOne(double assignmentOne) {
        this.assignmentOne = assignmentOne;
    }
    
    public double getAssignmentOne() {
        return assignmentOne;
    }
    
    public void setAssignmentTwo(double assignmentTwo) {
        this.assignmentTwo = assignmentTwo;
    }
    
    public double getAssignmentTwo() {
        return assignmentTwo;
    }
    
    public void setWeeklyPractical(double weeklyPractical) {
        this.weeklyPractical = weeklyPractical;
    }
    
    public double getWeeklyPractical() {
        return weeklyPractical;
    }
    
    @Override
    public double computeOverallMarks() {
        overallMarks = (assignmentOne/100 * 20) + (assignmentTwo/100 * 20) + (weeklyPractical/100 * 15) + (finalExam/100 * 45);
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
        return super.toString() + " " + assignmentOne + " " + assignmentTwo + " " + weeklyPractical + " " + finalExam + " " + overallMarks + " " + finalGrade;
    }

    
}
