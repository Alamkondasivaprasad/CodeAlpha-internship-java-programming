import java.io.*;
import java.util.*;
public class HotelReservationSystem {
    static final Scanner sc = new Scanner(System.in);
    // ==== Room Class ====
    static class Room {
        int roomNumber;
        String category;
        double price;
        boolean isBooked;
        String bookedBy;
        String bookingDate;
        Room(int roomNumber, String category, double price) {
            this.roomNumber = roomNumber;
            this.category = category;
            this.price = price;
            this.isBooked = false;
        }
        @Override
        public String toString() {
            return "Room " + roomNumber + " (" + category + ") - $" + price +
                    (isBooked ? " [BOOKED by " + bookedBy + " on " + bookingDate + "]" : " [Available]");
        }
    }
    // ==== Booking Class ====
    static class Booking {
        String bookingId;
        String customerName;
        Room room;
        String date;
        double amountPaid;
        Booking(String bookingId, String customerName, Room room, String date, double amountPaid) {
            this.bookingId = bookingId;
            this.customerName = customerName;
            this.room = room;
            this.date = date;
            this.amountPaid = amountPaid;
        }

        @Override
        public String toString() {
            return "Booking ID: " + bookingId + "\nCustomer: " + customerName + "\nRoom: " +
                    room.roomNumber + " (" + room.category + ")\nDate: " + date +
                    "\nPaid: $" + amountPaid;
        }
    }

    static List<Room> rooms = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();

    // ==== Initialize some rooms ====
    static void initRooms() {
        rooms.add(new Room(101, "Standard", 100));
        rooms.add(new Room(102, "Standard", 100));
        rooms.add(new Room(201, "Deluxe", 200));
        rooms.add(new Room(202, "Deluxe", 200));
        rooms.add(new Room(301, "Suite", 300));
    }

    // ==== Search Rooms ====
    static void searchRooms() {
        System.out.print("Enter category (Standard/Deluxe/Suite): ");
        String category = sc.nextLine();
        System.out.print("Enter booking date (YYYY-MM-DD): ");
        String date = sc.nextLine();

        boolean found = false;
        for (Room r : rooms) {
            if (r.category.equalsIgnoreCase(category) && (!r.isBooked || !r.bookingDate.equals(date))) {
                System.out.println(r);
                found = true;
            }
        }
        if (!found) System.out.println("No available rooms found for " + category + " on " + date);
    }

    // ==== Book Room ====
    static void bookRoom() {
        System.out.print("Enter room number: ");
        int roomNum = Integer.parseInt(sc.nextLine());
        System.out.print("Enter customer name: ");
        String customer = sc.nextLine();
        System.out.print("Enter booking date (YYYY-MM-DD): ");
        String date = sc.nextLine();

        for (Room r : rooms) {
            if (r.roomNumber == roomNum && (!r.isBooked || !r.bookingDate.equals(date))) {
                System.out.println("Room price: $" + r.price);
                System.out.print("Proceed to payment? (yes/no): ");
                if (sc.nextLine().equalsIgnoreCase("yes")) {
                    // Payment simulation
                    System.out.println("Processing payment...");
                    String bookingId = UUID.randomUUID().toString().substring(0, 8);
                    r.isBooked = true;
                    r.bookedBy = customer;
                    r.bookingDate = date;
                    bookings.add(new Booking(bookingId, customer, r, date, r.price));
                    System.out.println("Booking confirmed! ID: " + bookingId);
                }
                return;
            }
        }
        System.out.println("Room not available.");
    }

    // ==== Cancel Booking ====
    static void cancelBooking() {
        System.out.print("Enter booking ID to cancel: ");
        String id = sc.nextLine();
        Booking bookingToCancel = null;

        for (Booking b : bookings) {
            if (b.bookingId.equals(id)) {
                bookingToCancel = b;
                break;
            }
        }
        if (bookingToCancel != null) {
            bookingToCancel.room.isBooked = false;
            bookingToCancel.room.bookedBy = null;
            bookingToCancel.room.bookingDate = null;
            bookings.remove(bookingToCancel);
            System.out.println("Booking cancelled successfully.");
        } else {
            System.out.println("Booking ID not found.");
        }
    }

    // ==== View Booking Details ====
    static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }
        for (Booking b : bookings) {
            System.out.println(b);
            System.out.println("-------------------");
        }
    }

    // ==== Main Menu ====
    public static void main(String[] args) {
        initRooms();

        while (true) {
            System.out.println("\n==== Hotel Reservation System ====");
            System.out.println("1. Search Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View Bookings");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1": searchRooms(); break;
                case "2": bookRoom(); break;
                case "3": cancelBooking(); break;
                case "4": viewBookings(); break;
                case "5": System.exit(0);
                default: System.out.println("Invalid choice.");
            }
        }
    }
}
