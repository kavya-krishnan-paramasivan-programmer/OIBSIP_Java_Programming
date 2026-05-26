import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class User {

    String username = "admin";
    String password = "1234";
    String profileName = "Kavya";

    boolean login() {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== LOGIN SYSTEM =====");

        System.out.print("Enter Username: ");
        String user = sc.nextLine();

        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        if (user.equals(username) && pass.equals(password)) {

            System.out.println("Login Successful!");
            return true;
        }

        System.out.println("Invalid Credentials!");
        return false;
    }

    void updateProfile() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter New Profile Name: ");
        profileName = sc.nextLine();

        System.out.println("Profile Updated Successfully!");
    }

    void updatePassword() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Old Password: ");
        String oldPass = sc.nextLine();

        if (oldPass.equals(password)) {

            System.out.print("Enter New Password: ");
            password = sc.nextLine();

            System.out.println("Password Updated Successfully!");

        } else {

            System.out.println("Incorrect Old Password!");
        }
    }
}

class Question {

    String question;
    String option1;
    String option2;
    String option3;
    String option4;
    int correctAnswer;

    Question(String question,
             String option1,
             String option2,
             String option3,
             String option4,
             int correctAnswer) {

        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctAnswer = correctAnswer;
    }

    void displayQuestion() {

        System.out.println("\n" + question);
        System.out.println("1. " + option1);
        System.out.println("2. " + option2);
        System.out.println("3. " + option3);
        System.out.println("4. " + option4);
    }
}

class Exam {

    static int score = 0;
    static boolean timeUp = false;

    static void startExam() {

        Scanner sc = new Scanner(System.in);

        Question[] questions = {

            new Question(
                    "Which language is platform independent?",
                    "C",
                    "C++",
                    "Java",
                    "Python",
                    3
            ),

            new Question(
                    "Which keyword is used for inheritance in Java?",
                    "this",
                    "super",
                    "extends",
                    "implements",
                    3
            ),

            new Question(
                    "Which company developed Java?",
                    "Microsoft",
                    "Sun Microsystems",
                    "Google",
                    "Apple",
                    2
            )
        };

        Timer timer = new Timer();

        TimerTask task = new TimerTask() {

            public void run() {

                timeUp = true;

                System.out.println("\n\nTIME UP!");
                System.out.println("Exam Auto Submitted!");
            }
        };

        timer.schedule(task, 60000);

        System.out.println("\n===== ONLINE EXAMINATION =====");
        System.out.println("You have 60 seconds.");

        for (Question q : questions) {

            if (timeUp) {
                break;
            }

            q.displayQuestion();

            System.out.print("Enter Answer: ");
            int answer = sc.nextInt();

            if (answer == q.correctAnswer) {
                score++;
            }
        }

        timer.cancel();

        System.out.println("\n===== EXAM COMPLETED =====");
        System.out.println("Your Score: " + score + "/" + questions.length);
    }
}

public class OnlineExaminationSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        User user = new User();

        if (!user.login()) {
            return;
        }

        int choice;

        do {

            System.out.println("\n===== ONLINE EXAMINATION SYSTEM =====");

            System.out.println("1. Update Profile");
            System.out.println("2. Change Password");
            System.out.println("3. Start Exam");
            System.out.println("4. Logout");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            sc.nextLine();

            switch (choice) {

                case 1:
                    user.updateProfile();
                    break;

                case 2:
                    user.updatePassword();
                    break;

                case 3:
                    Exam.startExam();
                    break;

                case 4:
                    System.out.println("Logged Out Successfully!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 4);

        sc.close();
    }
}