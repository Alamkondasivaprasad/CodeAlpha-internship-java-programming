import java.util.ArrayList;
import java.util.Scanner;
public class StudentGradeTracker {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> studentNames = new ArrayList<>();
        ArrayList<Double> studentGrades = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter student name: ");
            studentNames.add(sc.nextLine());

            System.out.print("Enter " + studentNames.get(i) + "'s grade: ");
            studentGrades.add(sc.nextDouble());
            sc.nextLine(); 
        }

        // Calculate average, highest, and lowest scores
        double sum = 0;
        double highest = studentGrades.get(0);
        double lowest = studentGrades.get(0);
        int highestIndex = 0, lowestIndex = 0;

        for (int i = 0; i < studentGrades.size(); i++) {
            double grade = studentGrades.get(i);
            sum += grade;
            if (grade > highest) {
                highest = grade;
                highestIndex = i;
            }
            if (grade < lowest) {
                lowest = grade;
                lowestIndex = i;
            }
        }

        double average = sum / studentGrades.size();

        // Display summary report
        System.out.println("\n--- Student Grades Summary ---");
        for (int i = 0; i < studentNames.size(); i++) {
            System.out.println(studentNames.get(i) + " : " + studentGrades.get(i));
        }
        System.out.println("Average Score: " + average);
        System.out.println("Highest Score: " + highest + " (" + studentNames.get(highestIndex) + ")");
        System.out.println("Lowest Score: " + lowest + " (" + studentNames.get(lowestIndex) + ")");

        sc.close();
    }
}
