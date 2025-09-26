import Entity.Student;
import Entity.Course;
import Entity.Enrollment;
import java.util.Scanner

var students = mutableListOf<Student>();
var courses = mutableListOf<Course>();
var enrollments = mutableListOf<Enrollment>();
val obj = Scanner(System. `in`);

fun main(){

    val yellow = "\u001B[33m"
    val red = "\u001B[31m"
    val orange = "\u001B[38;5;208m"
    val reset = "\u001B[0m"

    while(true){

        println("==============================Hello Wo rld===================================");
        println("1. Register ${red}New${reset} Entity.Student")
        println("2. Create ${red}New${reset} course")
        println("3. Enroll student in course")
        println("4. View each course Students")
        println("5. View all students")
        println("6. View all courses")
        println("7. Exists")
        println(" ")
        println(" ")
        println("How to use:")
        println(". Type a ${orange}number${reset}  (1-7) to select an option")
        println(". Press Enter to confirm your choice")
        println("Type 7 or ${yellow}'exit'${reset} to quit the application")
        println("============================================================================");

        print("Your Selection: ");
        val userChoice: Int = readLine()!!.toInt();
        when(userChoice){
            1 -> registerNewStudent();
            2 -> createCourse();
            3 -> enroll();
            4 -> viewEachCourseStudent();
            5 -> viewAllStudent();
            6 -> viewALlCourse();
            7 -> System.exit(0);
            else -> println("Number Not Exist!!")
        }
    }
}

fun viewALlCourse() {
    if (courses.isEmpty()) {
        println("No courses available.")
        return
    }

    println("=== Courses ===")
    // Table header
    println(String.format("%-8s | %-25s | %-7s | %-15s", "ID", "Course Name", "Credits", "Enrolled"))
    println("-".repeat(65))

    // Table rows
    courses.forEach { c ->
        val enrollmentCount = enrollments.count { it.courseId == c.courseId }
        println(String.format("%-8s | %-25s | %-7d | %-15d",
            c.courseId,
            c.courseName,
            c.credits,
            enrollmentCount
        ))
    }
}


fun viewAllStudent() {
    if (students.isEmpty()) {
        println("No students registered yet.")
        return
    }

    println("=== Registered Students ===")
    // Table header
    println(String.format("%-6s | %-20s | %-25s | %-15s", "ID", "Name", "Email", "Major"))
    println("-".repeat(75)) // separator line

    // Table rows
    students.forEach { s ->
        println(String.format("%-6s | %-20s | %-25s | %-15s",
            s.id,
            s.name,
            s.email ?: "N/A",
            s.major
        ))
    }
}


fun viewEachCourseStudent() {
    print("Enter Course ID to view students: ")
    val courseId = obj.nextLine().trim()
    val course = courses.find { it.courseId == courseId }
    if (course == null) {
        println("Course ID $courseId not found.")
        return
    }

    val enrolledStudents = enrollments
        .filter { it.courseId == courseId }
        .mapNotNull { enrollment -> students.find { it.id == enrollment.studentId } }

    if (enrolledStudents.isEmpty()) {
        println("No students enrolled in ${course.courseName}.")
        return
    }

    println("=== Students in ${course.courseName} ===")
    println(String.format("%-6s | %-20s | %-25s | %-15s", "ID", "Name", "Email", "Major"))
    println("-".repeat(75))

    enrolledStudents.forEach { s ->
        println(String.format("%-6s | %-20s | %-25s | %-15s",
            s.id,
            s.name,
            s.email ?: "N/A",
            s.major
        ))
    }
}


fun enroll() {
    print("Enter Student ID: ")
    val studentId = obj.nextLine().trim()
    val student = students.find { it.id == studentId }
    if (student == null) {
        println("Student ID $studentId not found.")
        return
    }

    print("Enter Course ID: ")
    val courseId = obj.nextLine().trim()
    val course = courses.find { it.courseId == courseId }
    if (course == null) {
        println("Course ID $courseId not found.")
        return
    }

    // Check if already enrolled
    if (enrollments.any { it.studentId == studentId && it.courseId == courseId }) {
        println("Student ${student.name} is already enrolled in ${course.courseName}.")
        return
    }

    val enrollment = Enrollment(studentId, courseId)
    enrollments.add(enrollment)
    println("Student ${student.name} enrolled in ${course.courseName} successfully!")
}

fun createCourse() {
    print("Enter Course ID (e.g., CS101): ")
    val courseId = obj.nextLine().trim()

    print("Enter Course Name: ")
    val courseName = obj.nextLine().trim()

    print("Enter Number of Credits: ")
    val credits = obj.nextLine().trim().toIntOrNull() ?: 0

    val course = Course(courseId, courseName, credits)
    courses.add(course)
    println("Course $courseName has been created successfully!")
}

fun registerNewStudent() {
//    ask user for student detail step by step
    println("Enter Student Information!")

    print("Id : ")
    val id = obj.nextLine().trim();

    print("Name : ")
    val name = obj.nextLine().trim();

    print("Email : ")
    val email = obj.nextLine().trim();

    print("Major : ")
    val major = obj.nextLine().trim();

    val student = Student(id, name, email, major);

    students.add(student);

    println("$name has been create successfully!")
}


