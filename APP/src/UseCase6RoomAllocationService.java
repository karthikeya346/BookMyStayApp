import java.util.*;

// Booking Service
class BookingService {

    private Set<String> allocatedRoomIds; // to prevent duplicates
    private Map<String, Set<String>> roomAllocations; // type → room IDs

    public BookingService() {
        allocatedRoomIds = new HashSet<>();
        roomAllocations = new HashMap<>();
    }

    // Generate unique room ID
    private String generateRoomId(String roomType, int number) {
        return roomType.substring(0, 1).toUpperCase() + number;
    }

    // Process queue
    public void processBookings(Queue<Reservation> queue, RoomInventory inventory) {

        System.out.println("\n=== Processing Bookings ===");

        while (!queue.isEmpty()) {

            Reservation request = queue.poll(); // FIFO

            String roomType = request.roomType;

            int available = inventory.getAvailability(roomType);

            // Check availability
            if (available > 0) {

                String roomId = generateRoomId(roomType, available);

                // Ensure uniqueness
                if (!allocatedRoomIds.contains(roomId)) {

                    allocatedRoomIds.add(roomId);

                    // Store allocation
                    roomAllocations
                            .computeIfAbsent(roomType, k -> new HashSet<>())
                            .add(roomId);

                    // Update inventory (IMPORTANT)
                    inventory.updateAvailability(roomType, available - 1);

                    System.out.println("Booking Confirmed for " + request.guestName +
                            " → Room ID: " + roomId);
                }

            } else {
                System.out.println("Booking Failed for " + request.guestName +
                        " (No " + roomType + " rooms available)");
            }
        }
    }

    // Display allocations
    public void showAllocations() {
        System.out.println("\n=== Allocated Rooms ===");

        for (String type : roomAllocations.keySet()) {
            System.out.println(type + " → " + roomAllocations.get(type));
        }
    }
}

// Main Class
public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        // Step 1: Inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single", 2);
        inventory.addRoomType("Double", 1);
        inventory.addRoomType("Suite", 1);

        // Step 2: Queue (UC5)
        Queue<Reservation> queue = new LinkedList<>();

        queue.add(new Reservation("Karthik", "Single"));
        queue.add(new Reservation("Rahul", "Single"));
        queue.add(new Reservation("Anita", "Single")); // should fail
        queue.add(new Reservation("Priya", "Suite"));

        // Step 3: Process bookings
        BookingService service = new BookingService();
        service.processBookings(queue, inventory);

        // Step 4: Show results
        service.showAllocations();

        // Step 5: Final inventory
        inventory.displayInventory();
    }
}