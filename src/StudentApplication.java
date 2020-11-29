import java.util.*;
import java.io.*;

public class StudentApplication {
    public static Scanner kb = new Scanner(System.in);
    public static char userInput; // static public variable to store the user's input of student type
     

    public static char getUserInput() { // Method to get user's input
        System.out.print("Enter C (Course Work Students) or R (Research Students): "); 
        userInput = kb.next().charAt(0);
        userInput = Character.toLowerCase(userInput); // To lower case user input for consistency
        while (true) { // A while loop to make sure user enters C or R only
            if (userInput != 'c' && userInput != 'r') { 
                System.out.print("Error, please enter C or R: ");
                userInput = kb.next().charAt(0);
                userInput = Character.toLowerCase(userInput);
            } else {
                break;
            }
        }
        System.out.println(); // Aesthetic purpose
        return userInput; // Store the user's input
    }
    

    public static ArrayList<Student> readFile() { // Method to read the file of user's input, that returns an array list of the students read from the file
        ArrayList<Student> students = new ArrayList<Student>(); 
        try {
            if (userInput == 'c') { 
                Scanner reader = new Scanner(new File("courseworkstudent.txt")); // If user's input is c, read course work students' file
                System.out.println("Selected Course Work Students"); // Aesthetic purpose
                while (reader.hasNextLine()) { // There are lines that the scanner is picking up
                    String line = reader.nextLine(); // Store the entire line into the line variable
                    String[] c = line.split(" "); // Split the line up with the spaces, and store each item into an array
                    students.add(new CourseWorkStudent(c[0], c[1], c[2], Long.parseLong(c[3]), Integer.parseInt(c[4]), Integer.parseInt(c[5]), Integer.parseInt(c[6]),
                    Double.parseDouble(c[7]), Double.parseDouble(c[8]), Double.parseDouble(c[9]), Double.parseDouble(c[10])));
                    // Store the position of each array into a student arraylist, and parse the string to the correct data type.
                }
                reader.close(); // close the scanner
            } else if (userInput == 'r') {
                Scanner reader = new Scanner(new File("researchstudent.txt")); // If user's input is r, read research students' file
                System.out.println("Selected Research Students"); // Aesthetic purpose
                while (reader.hasNextLine()) { // There are lines that the scanner is picking up
                    String line = reader.nextLine(); // Store the entire line into the line variable
                    String[] c = line.split(" "); // Split the line up with the spaces, and store each item into an array
                    students.add(new ResearchStudent(c[0], c[1], c[2], Long.parseLong(c[3]), Integer.parseInt(c[4]), Integer.parseInt(c[5]), Integer.parseInt(c[6]),
                    Double.parseDouble(c[7]), Double.parseDouble(c[8]), Double.parseDouble(c[9])));
                    // Store the position of each array into a student arraylist, and parse the string to the correct data type.
                }
                reader.close(); // close the scanner
            }
            computeAllMarksGrades(students); // Compute the marks and grades for all student
            System.out.println(); // Aesthetic purpose
            return students; // Return student array list
        } catch (FileNotFoundException ex) { // If the file is not found
            System.out.println(ex.getMessage()); // Print the error
        }
        return null; // Return null if anything goes wrong
    }
    
    public static void optionTwo(ArrayList<Student> students) { // Option two, to read a file, and store the file into student array list
        System.out.println(); // For aesthetic purpose
        boolean added = false; // flag to check if any student records were added in
        try { 
            if (userInput == 'c') { 
                Scanner reader = new Scanner(new File("additionalcourworkstudent.txt")); // If user chose course work student earlier, read the additional course work students' file
                while (reader.hasNextLine()) { // There are lines that the scanner is picking up
                    String line = reader.nextLine(); // Store the entire line into the line variable
                    String[] c = line.split(" "); // Split the line up with the spaces, and store each item into an array
                    long studentId = Long.parseLong(c[3]);
                    if (search(students, studentId) == null) { // Search if student id already exist or not, if not
                        students.add(new CourseWorkStudent(c[0], c[1], c[2], Long.parseLong(c[3]), Integer.parseInt(c[4]), Integer.parseInt(c[5]), Integer.parseInt(c[6]),
                        Double.parseDouble(c[7]), Double.parseDouble(c[8]), Double.parseDouble(c[9]), Double.parseDouble(c[10])));
                        // Store the position of each array into a student arraylist, and parse the string to the correct data type.
                        added = true; // student is added in
                    } else {
                        System.out.println("Student with this ID already exist!");
                    }
                }
                reader.close(); // close the reader
            } else {
                Scanner reader = new Scanner(new File("additionalresearchstudent.txt")); // If user chose research student earlier, read the additional research students' file
                while (reader.hasNextLine()) { // There are lines that the scanner is picking up
                    String line = reader.nextLine(); // Store the entire line into the line variable
                    String[] c = line.split(" "); // Split the line up with the spaces, and store each item into an array
                    long studentId = Long.parseLong(c[3]);
                    if (search(students, studentId) == null) { // Search if student id already exist or not, if not                    
                        students.add(new ResearchStudent(c[0], c[1], c[2], Long.parseLong(c[3]), Integer.parseInt(c[4]), Integer.parseInt(c[5]), Integer.parseInt(c[6]),
                        Double.parseDouble(c[7]), Double.parseDouble(c[8]), Double.parseDouble(c[9])));
                        // Store the position of each array into a student arraylist, and parse the string to the correct data type.
                        added = true; // student was added in 
                    } else {
                        System.out.println("Student with this ID already exist!");
                    }
                }
                reader.close(); // close the reader
            }
            if (added) { // if tehre are any student records added
                System.out.println("Student details added!"); 
            }
            computeAllMarksGrades(students); // Compute the marks and grades for all student
            System.out.println(); // For aesthetic purpose
        } catch (FileNotFoundException ex) { // If the file is not found
            System.out.println(ex.getMessage()); // Print the error
        }
    }
    
    private static void computeAllMarksGrades(ArrayList<Student> students) { // compute all the student from the student array list
        for (Student s : students) { // for all the student
            s.computeOverallMarks(); // compute the overall marks
            s.computeFinalGrade(); // compute the final grade
        }
    }
    
    public static void optionThree(ArrayList<Student> students) { // option three, display all info about all students
        System.out.println(); // For aesthetic purpose
        for (Student s : students) { // for all the student
            System.out.println(s); // print out all student info
        }
        System.out.println(); // For aesthetic purpose   
    }
    
    private static double calculateAverage(ArrayList<Student> students) { // Seperate method to calculate average (as 2 methods are using it)
        double total = 0; // Set total marks as 0
        for (Student s : students) { // for every student
            total += s.getOverallMarks(); // add overall marks into total
        }
        return total/students.size(); // return total/ no of student 
    }
    
    public static void optionFour(ArrayList<Student> students) {
        System.out.println(); // aesthetic purpose
        double average = calculateAverage(students); // calculate average
        
        System.out.println("Average marks: " + average); // Print out average marks
        System.out.println(); // aesthetic purpose
    }
    
    public static void optionFive(ArrayList<Student> students) { // option five, print the number of student above or below average
        System.out.println(); // aesthetic purpose
        double average = calculateAverage(students); // calculate average
        
        int above = 0; // number of student = or above average
        int below = 0; // number of student below average
        for (Student s : students) { // for every student
            if (s.getOverallMarks() >= average) { // if overall marks is more than or equals to average
                above++; // number of above ++
            } else {
                below++; // else below ++
            }
        }
        System.out.println("Equals, or above average marks: " + above); // print out number of above
        System.out.println("Below average marks: " + below); // print out number of below
        System.out.println(); // aesthetic purpose
    }
    
    private static Student search(ArrayList<Student> students, long studentId) { // search method , check if the student id belongs to anyone
        for (Student s : students) { // for every student
            if (s.getStudentId() == studentId) { // if student id matches with the user's input 
                return s; // return that individual student
            }
        } 
        return null; // else return null
    }
    
    public static void optionSix(ArrayList<Student> students) { // option 6, search student by id
        System.out.println(); // aesthetic purpose
        System.out.print("Please enter student ID: "); // prompt for id to search
        long studentId = kb.nextLong();
        Student s = search(students, studentId); // search the arraylis
        System.out.println(); // aesthetic purpose
        if (s == null) { // if result is null
            System.out.println("No ID matched"); // print no match
        } else {
            System.out.println("Found!"); // if matched
            System.out.println(s); // print all info of the student
        }
        System.out.println(); // aesthetic purpose
    }
    
    public static void optionSeven(ArrayList<Student> students) { // option 7, search student by first and last name
        System.out.println(); // aesthetic purpose
        kb.nextLine(); // to capture enter from menu
        System.out.print("Please enter first name: "); // prompt user for first name
        String firstName = kb.nextLine();
        System.out.print("Please enter last name: "); // prompt user for last name
        String lastName = kb.nextLine();
        boolean found = false; // flag to check if the name has been found
        System.out.println(); // aesthetic purpose
         
        for (Student s : students) { // for every student
            if (s.getFirstName().equalsIgnoreCase(firstName) && s.getLastName().equalsIgnoreCase(lastName)) { // check each student name with user input, ignoring case
                System.out.println("Found!"); // if found, print found
                System.out.println(s); // print out the student info
                found = true; // set flag to true
                break; // break from loop
            }
        }
        
        if (!found) { // if after for loop, still not found
            System.out.println("No first name and last name matched"); // print no match
        }
        
        System.out.println(); // aesthetic purpose
    }
    
    public static void optionEight(ArrayList<Student> students) { // option 8, sort the array
        System.out.println(); // aesthetic purpose
        int n = students.size(); // create int n, the number of student
        for (int j = 1; j < n; j++) { // for loop of int j, the second student till the last student
            Student temp = students.get(j); // set a temp student, which is the student at j
            int i = j-1; // create an int i, which is the jth student - 1
            while ((i > -1) && students.get(i).getStudentId() > temp.getStudentId()) { // while i is not out of bounds from j, and the student id of i is more than the student idf of j)
                students.set(i+1, students.get(i)); // set the student at position i+1, to the student of i
                i--; // position i --
            } 
            students.set(i+1, temp); // set the student at position of i+1 to the student to the temp student stored earlier
        }
        System.out.println("Sorted"); // print out sorted
        System.out.println(); // aesthetic purpose
        for (Student s : students) { // for every student
            System.out.println(s); // display all info of the student, sorted
        }
        System.out.println(); // aesthetic purpose
    }
    
    public static void optionNine(ArrayList<Student> students) { // option 9, output the array into a csv file
        System.out.println(); // aesthetic purpose
        try {
            PrintWriter writer = new PrintWriter("output.csv"); // write the output into this file
            for (Student s : students) { // for every student
                 String[] c = s.toString().split(" "); // create a string [] that will split all the student information
                 for (int i = 0; i < c.length; i++) { // to output each item of the string array
                     if (i != c.length-1) { // for every information that is not the last position
                        writer.write(c[i] + ","); // separate with a comma
                     } else { // if last position
                        writer.write(c[i] + "\n"); // break line
                     }
                 }
            }
            writer.close(); // close the write
            System.out.println("File saved into \'output.csv\'"); // print that the file has been output
        } catch(FileNotFoundException ex) { // if the found is not found
            System.out.println(ex.getMessage()); // print the message
        }
        System.out.println(); // aethetic reason
    }

    public static void main(String[] args) {
        getUserInput(); // prompt the user for which type of student
        ArrayList<Student> students = readFile(); // read the file of type of student the user chose, and return it into an arraylist
       
        int option = 1; // default option 1, to quit
        do {
            System.out.println("Menu:"); // printout menu
            System.out.println("1. Quit (Exit the program)");
            System.out.println("2. Add in more students");
            System.out.println("3. Display information of all students");
            System.out.println("4. Display average overall marks");
            System.out.println("5. Display number of students equals to, or above average marks, and below average marks");
            System.out.println("6. Display information of a student using student number");
            System.out.println("7. Display information of a student using first and last name");
            System.out.println("8. Sort the student array, and display the sorted array");
            System.out.println("9. Display the sorted array into a CSV file");
            System.out.print("Input: ");
            option = kb.nextInt();
            
            switch (option) { // run the method corresponding to the user's choice
                case 2: optionTwo(students);
                    break;
                case 3: optionThree(students);
                    break;
                case 4: optionFour(students);
                    break;
                case 5: optionFive(students);
                    break;
                case 6: optionSix(students);
                    break;
                case 7: optionSeven(students);
                    break;
                case 8: optionEight(students);
                    break;
                case 9: optionNine(students);
                    break;
                default: break;
            }
            
        } while (option != 1); // if user quit, dont run the do loop
        
        
    }
}
