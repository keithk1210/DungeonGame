package resources;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Loader {
	
	public static void load() {
		
	try {
		Resources.TEXTURES.add(Resources.BROWN_WALL_FRONT_FACE,ImageIO.read(new File("res/textures/brown_wall_textures/brown_wall_front_facing.png")));
		Resources.TEXTURES.add(Resources.BROWN_WALL_ABOVE,ImageIO.read(new File("res/textures/brown_wall_textures/brown_wall_above.png")));
		Resources.TEXTURES.add(Resources.BROWN_FLOOR,ImageIO.read(new File("res/textures/brown_wall_textures/brown_floor.png")));
	} catch (IOException e) {
		e.printStackTrace();
	}
}
}