package framework.resources;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Loader {
	
	public static void load() {
		
	try {
		Resources.TEXTURES.add(Resources.BROWN_WALL_FRONT_FACE,ImageIO.read(new File("res/textures/brown_wall/brown_wall_front_facing.png")));
		Resources.TEXTURES.add(Resources.BROWN_WALL_ABOVE,ImageIO.read(new File("res/textures/brown_wall/brown_wall_above.png")));
		Resources.TEXTURES.add(Resources.BROWN_FLOOR,ImageIO.read(new File("res/textures/brown_wall/brown_floor.png")));
		Resources.TEXTURES.add(Resources.CONJ_BLUE_FRONT, ImageIO.read(new File("res/textures/player/conjuror_blue/front.png")));
		Resources.TEXTURES.add(Resources.CONJ_BLUE_FRONT_LEFT,ImageIO.read(new File("res/textures/player/conjuror_blue/front_left.png")));
		Resources.TEXTURES.add(Resources.CONJ_BLUE_FRONT_RIGHT, ImageIO.read(new File("res/textures/player/conjuror_blue/front_right.png")));
		Resources.TEXTURES.add(Resources.CONJ_BLUE_LEFT, ImageIO.read(new File("res/textures/player/conjuror_blue/left.png")));
		Resources.TEXTURES.add(Resources.CONJ_BLUE_LEFT_LEFT,ImageIO.read(new File("res/textures/player/conjuror_blue/left_left.png")));
		Resources.TEXTURES.add(Resources.CONJ_BLUE_LEFT_RIGHT,ImageIO.read(new File("res/textures/player/conjuror_blue/left_right.png")));
		Resources.TEXTURES.add(Resources.CONJ_BLUE_RIGHT, ImageIO.read(new File("res/textures/player/conjuror_blue/right.png")));
		Resources.TEXTURES.add(Resources.CONJ_BLUE_RIGHT_LEFT,ImageIO.read(new File("res/textures/player/conjuror_blue/right_left.png")));
		Resources.TEXTURES.add(Resources.CONJ_BLUE_RIGHT_RIGHT, ImageIO.read(new File("res/textures/player/conjuror_blue/right_right.png")));
		Resources.TEXTURES.add(Resources.CONJ_BLUE_BACK,ImageIO.read(new File("res/textures/player/conjuror_blue/back.png")));
		Resources.TEXTURES.add(Resources.CONJ_BLUE_BACK_LEFT,ImageIO.read(new File("res/textures/player/conjuror_blue/back_left.png")));
		Resources.TEXTURES.add(Resources.CONJ_BLUE_BACK_RIGHT,ImageIO.read(new File("res/textures/player/conjuror_blue/back_right.png")));
		Resources.TEXTURES.add(Resources.SKELETON_FRONT, ImageIO.read(new File("res/textures/enemies/skeleton/front.png")));
		Resources.TEXTURES.add(Resources.SKELETON_FRONT_LEFT,ImageIO.read(new File("res/textures/enemies/skeleton/front_left.png")));
		Resources.TEXTURES.add(Resources.SKELETON_FRONT_RIGHT, ImageIO.read(new File("res/textures/enemies/skeleton/front_right.png")));
		Resources.TEXTURES.add(Resources.SKELETON_LEFT, ImageIO.read(new File("res/textures/enemies/skeleton/left.png")));
		Resources.TEXTURES.add(Resources.SKELETON_LEFT_LEFT,ImageIO.read(new File("res/textures/enemies/skeleton/left_left.png")));
		Resources.TEXTURES.add(Resources.SKELETON_LEFT_RIGHT,ImageIO.read(new File("res/textures/enemies/skeleton/left_right.png")));
		Resources.TEXTURES.add(Resources.SKELETON_RIGHT, ImageIO.read(new File("res/textures/enemies/skeleton/right.png")));
		Resources.TEXTURES.add(Resources.SKELETON_RIGHT_LEFT,ImageIO.read(new File("res/textures/enemies/skeleton/right_left.png")));
		Resources.TEXTURES.add(Resources.SKELETON_RIGHT_RIGHT, ImageIO.read(new File("res/textures/enemies/skeleton/right_right.png")));
		Resources.TEXTURES.add(Resources.SKELETON_BACK,ImageIO.read(new File("res/textures/enemies/skeleton/back.png")));
		Resources.TEXTURES.add(Resources.SKELETON_BACK_LEFT,ImageIO.read(new File("res/textures/enemies/skeleton/back_left.png")));
		Resources.TEXTURES.add(Resources.SKELETON_BACK_RIGHT,ImageIO.read(new File("res/textures/enemies/skeleton/back_right.png")));
		Resources.TEXTURES.add(Resources.MAGIC_BULLET,ImageIO.read(new File("res/textures/projectiles/magic/magic_bullet.png")));
	} catch (IOException e) {
		e.printStackTrace();
	}
}
}