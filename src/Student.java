
public class Student extends Object {
    
    // Init
    private String title;
    private String firstName;
    private String lastName;
    private long studentId;
    private int day, month, year;
    
    
    // Contructors
    public Student() {
    }
    
    public Student(String title, String firstName, String lastName, long studentId, int day, int month, int year) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
        this.day = day;
        this.month = month;
        this.year = year;
    }
    
    // Setter and Getter
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getLastName() {
        return lastName;
    }
     
    public long getStudentId() {
        return studentId;
    }
    
    public void setDay(int day) {
        this.day = day;
    }
    
    public int getDay() {
        return day;
    }
    
    public void setMonth(int month) {
        this.month = month;
    }
    
    public int getMonth() {
        return month;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    public int getYear() {
        return year;
    }
    
    
    // Methods
    public double computeOverallMarks() {
        return 0;
    }
     
    public String computeFinalGrade() {
        return null;
    }
     
    public double getOverallMarks() {
        return 0;
    }
     
     
    public boolean equals(Student stud) {
        if (this.firstName.equals(stud.firstName)) {
            if (this.lastName.equals(stud.lastName)) {
                if (this.day == stud.day) {
                    if (this.month == stud.month) {
                        if (this.year == stud.year) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
     
    public String toString() {
       return title + " " + firstName + " " + lastName + " " + studentId + " " + day + " " + month + " " + year;
    }
}
