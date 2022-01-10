package world.generator;
import utils.MathHelper.Direction;
import world.Room;
import world.World;

public class LevelGenerator {
	

	public void createRoom(World world) {
		if (world.getRooms().size() == 0) {
			world.addRoom(new Room(0,0,Direction.WEST,Direction.NORTH,Direction.EAST,Direction.SOUTH));
		}
	}
	

}
