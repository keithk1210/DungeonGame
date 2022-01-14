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
		Resources.TEXTURES.add(Resources.CONJ_BLUE_FRONT, ImageIO.read(new File("res/textures/player_textures/conjuror_blue/front")));
		Resources.TEXTURES.add(Resources.CONJ_BLUE_FRONT_LEFT,ImageIO.read(new File("res/textures/player_textures/conjuror_blue/front_left")));
		Resources.TEXTURES.add(Resources.CONJ_BLUE_FRONT_RIGHT, ImageIO.read(new File("res/textures/player_textures/conjuror_blue/front_right")));
		Resources.TEXTURES.add(Resources.CONJ_BLUE_LEFT, ImageIO.read(new File("res/textures/player_textures/conjuror_blue/left")));
		Resources.TEXTURES.add(Resources.CONJ_BLUE_LEFT_LEFT,ImageIO.read(new File("res/textures/player_textures/conjuror_blue/left_left")));
		Resources.TEXTURES.add(Resources.CONJ_BLUE_LEFT_RIGHT,ImageIO.read(new File("res/textures/player_textures/conjuror_blue/left_left")));
		Resources.TEXTURES.add(Resources.CONJ_BLUE_RIGHT, ImageIO.read(new File("res/textures/player_textures/conjuror_blue/right")));
		Resources.TEXTURES.add(Resources.CONJ_BLUE_RIGHT_LEFT,ImageIO.read(new File("res/textures/player_textures/conjuror_blue/right_left")));
		Resources.TEXTURES.add(Resources.CONJ_BLUE_RIGHT_RIGHT, ImageIO.read(new File("res/textures/player_textures/conjuror_blue/right_right")));
		Resources.TEXTURES.add(Resources.CONJ_BLUE_BACK,ImageIO.read(new File("res/textures/player_textures/conjuror_blue/back")));
		Resources.TEXTURES.add(Resources.CONJ_BLUE_BACK_LEFT,ImageIO.read(new File("res/textures/player_textures/conjuror_blue/back_left")));
		Resources.TEXTURES.add(Resources.CONJ_BLUE_BACK_RIGHT,ImageIO.read(new File("res/textures/player_textures/conjuror_blue/back_right")));
	} catch (IOException e) {
		e.printStackTrace();
	}
}
}