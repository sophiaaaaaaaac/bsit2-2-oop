import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Enrollment {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        HashMap<String, ArrayList<String>> enrollments = new HashMap<>();
        String[] validPrograms = {"BSIT", "BSCS"};

        int choice = -1;
        while (choice != 0) {
            printMenu();
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                choice = -1;
            }

            switch (choice) {
                case 1:
                    System.out.print("Student ID (e.g. 2026-0001): ");
                    String id = sc.nextLine();

                    if (id.length() < 5) {
                        System.out.println("[ERROR] Invalid Student ID format.\n");
                        break;
                    }

                    System.out.print("Full Name: ");
                    String name = sc.nextLine();

                    System.out.print("Program (BSIT/BSCS): ");
                    String prog = sc.nextLine().toUpperCase();
                    boolean isValidProg = false;
                    for (String p : validPrograms) {
                        if (p.equals(prog)) {
                            isValidProg = true;
                            break;
                        }
                    }
                    if (!isValidProg) {
                        System.out.println("[ERROR] Invalid program. Only BSIT and BSCS are allowed.\n");
                        break;
                    }

                    System.out.print("Year Level (1-4): ");
                    int yr;
                    try {
                        yr = Integer.parseInt(sc.nextLine());
                        if (yr < 1 || yr > 4) {
                            System.out.println("[ERROR] Year level must be between 1 and 4.\n");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("[ERROR] Year level must be a valid number.\n");
                        break;
                    }

                    students.add(new Student(id, name, prog, yr));
                    System.out.println("[OK] Student registered successfully!\n");
                    break;

                case 2:
                    System.out.print("Course Code: ");
                    String code = sc.nextLine();
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Units: ");
                    int units = Integer.parseInt(sc.nextLine());
                    System.out.print("Capacity: ");
                    int capacity = Integer.parseInt(sc.nextLine());

                    courses.add(new Course(code, title, units, capacity));
                    System.out.println("[OK] Course added!\n");
                    break;

                case 3:
                    System.out.print("Student ID: ");
                    String sId = sc.nextLine();
                    System.out.print("Course Code: ");
                    String cCode = sc.nextLine();

                    Student s = findStudent(students, sId);
                    Course c = findCourse(courses, cCode);

                    if (s == null) {
                        System.out.println("[ERROR] Student does not exist.\n");
                        break;
                    }
                    if (c == null) {
                        System.out.println("[ERROR] Course does not exist.\n");
                        break;
                    }
                    if (c.isFull()) {
                        System.out.println("[ERROR] Course is full.\n");
                        break;
                    }

                    ArrayList<String> scList = enrollments.get(sId);
                    if (scList != null && scList.contains(cCode)) {
                        System.out.println("[ERROR] Student is already enrolled in " + cCode + ".\n");
                        break;
                    }

                    if (scList == null) {
                        scList = new ArrayList<>();
                        enrollments.put(sId, scList);
                    }
                    scList.add(cCode);
                    c.addOneEnrollee();
                    System.out.println("[OK] Enrolled successfully!\n");
                    break;

                case 4:
                    if (students.isEmpty()) System.out.println("No students yet.");
                    for (Student student : students) System.out.println(student.describe());
                    System.out.println();
                    break;

                case 5:
                    if (courses.isEmpty()) System.out.println("No courses yet.");
                    for (Course course : courses) {
                        System.out.println(course.getCourseCode() + " | " + course.getTitle() + " | " + course.getEnrolledCount() + "/" + course.getCapacity());
                    }
                    System.out.println();
                    break;

                case 6:
                    System.out.print("Student ID: ");
                    String searchId = sc.nextLine();
                    Student target = findStudent(students, searchId);
                    if (target == null) {
                        System.out.println("[ERROR] Student not found.\n");
                        break;
                    }
                    System.out.println("--- STUDENT LOAD: " + target.getFullName() + " ---");
                    ArrayList<String> myCourses = enrollments.get(searchId);
                    int totalUnits = 0;
                    if (myCourses != null) {
                        for (String cc : myCourses) {
                            Course crs = findCourse(courses, cc);
                            if (crs != null) {
                                System.out.println(crs.getCourseCode() + " - " + crs.getUnits() + " units");
                                totalUnits += crs.getUnits();
                            }
                        }
                    }
                    System.out.println("Total Units: " + totalUnits + "\n");
                    break;

                case 0:
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice.\n");
            }
        }
    }

    static void printMenu() {
        System.out.println("========================================");
        System.out.println("      LICEO ENROLLMENT SYSTEM (CLI)     ");
        System.out.println("========================================");
        System.out.println("[1] Register Student");
        System.out.println("[2] Add Course Offering");
        System.out.println("[3] Enroll Student to Course");
        System.out.println("[4] View All Students");
        System.out.println("[5] View All Courses");
        System.out.println("[6] View Student Load");
        System.out.println("[0] Exit");
        System.out.print("Enter choice: ");
    }

    static Student findStudent(ArrayList<Student> list, String id) {
        for (Student s : list) if (s.getStudentId().equals(id)) return s;
        return null;
    }

    static Course findCourse(ArrayList<Course> list, String code) {
        for (Course c : list) if (c.getCourseCode().equals(code)) return c;
        return null;
    }
}