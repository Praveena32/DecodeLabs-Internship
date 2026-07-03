import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Student Grade Calculator ===\n");
        
        // 1. Ask for the number of subjects
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();
        
        // Validation for number of subjects
        if (numSubjects <= 0) {
            System.out.println("Invalid number of subjects. Exiting program.");
            return;
        }

        double[] marks = new double[numSubjects];
        double totalMarks = 0;

        // 2. Loop to take marks for each subject
        for (int i = 0; i < numSubjects; i++) {
            while (true) {
                System.out.print("Enter marks for Subject " + (i + 1) + " (out of 100): ");
                double inputMark = scanner.nextDouble();

                // Input validation: Marks must be between 0 and 100
                if (inputMark >= 0 && inputMark <= 100) {
                    marks[i] = inputMark;
                    totalMarks += inputMark;
                    break; // Exit the validation loop if input is valid
                } else {
                    System.out.println("Invalid input! Marks must be between 0 and 100. Try again.");
                }
            }
        }

        // 3. Calculate average percentage
        // Since each subject is out of 100, the average percentage is totalMarks / numSubjects
        double averagePercentage = totalMarks / numSubjects;

        // 4. Assign grades based on conditional statements
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else if (averagePercentage >= 50) {
            grade = 'E';
        } else {
            grade = 'F';
        }

        // 5. Display results clearly
        System.out.println("\n============================= ");
        System.out.println("           RESULTS            ");
        System.out.println("============================= ");
        System.out.printf("Total Marks Obtained : %.2f / %d\n", totalMarks, (numSubjects * 100));
        System.out.printf("Average Percentage   : %.2f%%\n", averagePercentage);
        System.out.println("Final Grade          : " + grade);
        System.out.println("============================= ");

        scanner.close();
    }
}
