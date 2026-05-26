import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Reservation {

    int pnrNumber;
    String passengerName;
    int trainNumber;
    String trainName;
    String classType;
    String dateOfJourney;
    String from;
    String destination;

    Reservation(int pnrNumber,
                String passengerName,
                int trainNumber,
                String trainName,
                String classType,
                String dateOfJourney,
                String from,
                String destination) {

        this.pnrNumber = pnrNumber;
        this.passengerName = passengerName;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.from = from;
        this.destination = destination;
    }

    void displayTicket() {

        System.out.println("\n===== TICKET DETAILS =====");
        System.out.println("PNR Number      : " + pnrNumber);
        System.out.println("Passenger Name  : " + passengerName);
        System.out.println("Train Number    : " + trainNumber);
        System.out.println("Train Name      : " + trainName);
        System.out.println("Class Type      : " + classType);
        System.out.println("Journey Date    : " + dateOfJourney);
        System.out.println("From            : " + from);
        System.out.println("Destination     : " + destination);
    }
}

class TicketDatabase {

    static ArrayList<Reservation> tickets = new ArrayList<>();

    static void addTicket(Reservation reservation) {
        tickets.add(reservation);
    }

    static Reservation findTicket(int pnr) {

        for (Reservation r : tickets) {

            if (r.pnrNumber == pnr) {
                return r;
            }
        }

        return null;
    }

    static void removeTicket(Reservation reservation) {
        tickets.remove(reservation);
    }

    static void displayAllTickets() {

        if (tickets.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }

        for (Reservation r : tickets) {
            r.displayTicket();
        }
    }
}

class Login {

    private final String username = "admin";
    private final String password = "1234";

    boolean authenticate() {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== LOGIN FORM =====");

        System.out.print("Enter Username: ");
        String user = sc.nextLine();

        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        if (user.equals(username) && pass.equals(password)) {
            System.out.println("Login Successful!");
            return true;
        }

        return false;
    }
}

class ReservationSystem {

    static void reserveTicket() {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int pnr = 100000 + random.nextInt(900000);

        System.out.println("\n===== RESERVATION FORM =====");

        System.out.print("Enter Passenger Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Train Number: ");
        int trainNo = sc.nextInt();
        sc.nextLine();

        String trainName;

        if (trainNo == 12627) {
            trainName = "Karnataka Express";
        }
        else if (trainNo == 16345) {
            trainName = "Netravathi Express";
        }
        else if (trainNo == 12637) {
            trainName = "Pandian Express";
        }
        else {
            trainName = "Unknown Train";
        }

        System.out.println("Train Name: " + trainName);

        System.out.print("Enter Class Type: ");
        String classType = sc.nextLine();

        System.out.print("Enter Journey Date: ");
        String date = sc.nextLine();

        System.out.print("From: ");
        String from = sc.nextLine();

        System.out.print("Destination: ");
        String destination = sc.nextLine();

        Reservation reservation = new Reservation(
                pnr,
                name,
                trainNo,
                trainName,
                classType,
                date,
                from,
                destination
        );

        TicketDatabase.addTicket(reservation);

        System.out.println("\nReservation Successful!");
        System.out.println("Generated PNR Number: " + pnr);
    }
}

class Cancellation {

    static void cancelTicket() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n===== CANCELLATION FORM =====");

        System.out.print("Enter PNR Number: ");
        int pnr = sc.nextInt();

        Reservation reservation = TicketDatabase.findTicket(pnr);

        if (reservation == null) {

            System.out.println("Ticket not found!");

        } else {

            reservation.displayTicket();

            sc.nextLine();

            System.out.print("\nConfirm Cancellation (yes/no): ");
            String confirm = sc.nextLine();

            if (confirm.equalsIgnoreCase("yes")) {

                TicketDatabase.removeTicket(reservation);

                System.out.println("Ticket Cancelled Successfully!");

            } else {

                System.out.println("Cancellation Aborted!");
            }
        }
    }
}

public class OnlineReservationSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Login login = new Login();

        if (!login.authenticate()) {

            System.out.println("Invalid Username or Password!");
            return;
        }

        int choice;

        do {

            System.out.println("\n===== ONLINE RESERVATION SYSTEM =====");
            System.out.println("1. Reserve Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. View All Reservations");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    sc.nextLine();
                    ReservationSystem.reserveTicket();
                    break;

                case 2:
                    Cancellation.cancelTicket();
                    break;

                case 3:
                    TicketDatabase.displayAllTickets();
                    break;

                case 4:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 4);

        sc.close();
    }
}