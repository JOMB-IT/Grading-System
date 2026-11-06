import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeEvaluationSystem {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();

    static class Student {
        String id;
        String name;
        String[] subjects = {"Mathematics", "English", "Science", "Programming", "Computer Systems"};
        double[] grades = new double[5];
        double average;
        int failedSubjects;
        String classification;
        String eligibility;

        Student(String id, String name, double[] grades) {
            this.id = id;
            this.name = name;
            this.grades = grades;
            evaluate();
        }

        void evaluate() {
            double total = 0;
            failedSubjects = 0;

            for (double grade : grades) {
                total += grade;
                if (grade < 75) {
                    failedSubjects++;
                }
            }

            average = total / grades.length;

            if (average >= 90) {
                classification = "Excellent";
            } else if (average >= 85) {
                classification = "Very Good";
            } else if (average >= 80) {
                classification = "Good";
            } else if (average >= 75) {
                classification = "Satisfactory";
            } else {
                classification = "Failed";
            }

            if (average < 75) {
                eligibility = "NOT ELIGIBLE - FAILED";
            } else if (failedSubjects > 0) {
                eligibility = "REQUIRES ACADEMIC REVIEW";
            } else {
                eligibility = "ELIGIBLE";
            }
        }

        void display() {
            System.out.println("\\n----------------------------------------");
            System.out.println("STUDENT EVALUATION");
            System.out.println("Student ID: " + id);
            System.out.println("Full Name: " + name);

            for (int i = 0; i < grades.length; i++) {
                String result = grades[i] >= 75 ? "Passed" : "Failed";
                System.out.printf("%-20s %.2f - %s%n", subjects[i], grades[i], result);
            }

            System.out.printf("General Average: %.2f%n", average);
            System.out.println("Classification: " + classification);
            System.out.println("Failed Subjects: " + failedSubjects);
            System.out.println("Enrollment Status: " + eligibility);
            System.out.println("----------------------------------------");
        }
    }

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\\ACCESS COMPUTER COLLEGE");
            System.out.println("STUDENT GRADE EVALUATION SYSTEM");
            System.out.println("1. Register Student and Enter Grades");
            System.out.println("2. View Student Evaluation");
            System.out.println("3. Process Another Student");
            System.out.println("4. Exit");

            choice = readIntInRange("Enter choice: ", 1, 4);

            switch (choice) {
                case 1:
                case 3:
                    registerStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 4:
                    System.out.println("Thank you for using the system.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }

    static void registerStudent() {
        System.out.print("Enter Student ID: ");
        String id = readNonEmptyText();

        System.out.print("Enter Full Name: ");
        String name = readNonEmptyText();

        String[] subjects = {"Mathematics", "English", "Science", "Programming", "Computer Systems"};
        double[] grades = new double[5];

        for (int i = 0; i < grades.length; i++) {
            grades[i] = readGrade("Enter grade for " + subjects[i] + " (0-100): ");
        }

        Student student = new Student(id, name, grades);
        students.add(student);
        System.out.println("Student record successfully saved.");
        student.display();
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        for (Student student : students) {
            if (student == null) {
                continue;
            }
            student.display();
        }
    }

    static double readGrade(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = input.nextLine();

            try {
                double grade = Double.parseDouble(value);
                if (grade >= 0 && grade <= 100) {
                    return grade;
                }
                System.out.println("Grade must be from 0 to 100.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    static int readIntInRange(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String value = input.nextLine();

            try {
                int number = Integer.parseInt(value);
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println("Please choose from " + min + " to " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    static String readNonEmptyText() {
        while (true) {
            String value = input.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.print("This field cannot be blank. Please enter again: ");
        }
    }
}
