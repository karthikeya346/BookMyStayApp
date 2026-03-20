import java.util.HashMap;
import java.util.Map;

class SearchService {

    public void searchAvailableRooms(RoomInventory inventory, Map<String, Room> rooms) {

        System.out.println("\n=== Available Rooms ===");

        for (String type : inventory.getInventory().keySet()) {

            int count = inventory.getAvailability(type);

            if (count > 0) {
                Room room = rooms.get(type);

                if (room != null) {
                    room.displayRoomDetails(); // your existing method
                    System.out.println("Available: " + count + "\n");
                }
            }
        }
    }
}

public class UseCase4RoomSearch {

    public static void main(String[] args) {

        // Inventory (UC3)
        RoomInventory inventory = new RoomInventory();

        inventory.addRoomType("Single", 10);
        inventory.addRoomType("Double", 0);
        inventory.addRoomType("Suite", 2);

        // Use EXISTING room classes (UC2)
        HashMap<String, Room> rooms = new HashMap<>();

        rooms.put("Single", new SingleRoom());
        rooms.put("Double", new DoubleRoom());
        rooms.put("Suite", new SuiteRoom());

        // Search
        SearchService search = new SearchService();
        search.searchAvailableRooms(inventory, rooms);
    }
}