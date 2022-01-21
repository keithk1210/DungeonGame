package framework.resources;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Loader {
	
	public static void load() {
		
	try {
		Resources.TEXTURES.add(TextureID.BROWN_WALL_FRONT_FACE,ImageIO.read(new File("res/textures/brown_wall/brown_wall_front_facing.png")));
		Resources.TEXTURES.add(TextureID.BROWN_WALL_ABOVE,ImageIO.read(new File("res/textures/brown_wall/brown_wall_above.png")));
		Resources.TEXTURES.add(TextureID.BROWN_FLOOR,ImageIO.read(new File("res/textures/brown_wall/brown_floor.png")));
		Resources.TEXTURES.add(TextureID.CONJ_BLUE_FRONT, ImageIO.read(new File("res/textures/entities/player/conjuror_blue/front.png")));
		Resources.TEXTURES.add(TextureID.CONJ_BLUE_FRONT_LEFT,ImageIO.read(new File("res/textures/entities/player/conjuror_blue/front_left.png")));
		Resources.TEXTURES.add(TextureID.CONJ_BLUE_FRONT_RIGHT, ImageIO.read(new File("res/textures/entities/player/conjuror_blue/front_right.png")));
		Resources.TEXTURES.add(TextureID.CONJ_BLUE_LEFT, ImageIO.read(new File("res/textures/entities/player/conjuror_blue/left.png")));
		Resources.TEXTURES.add(TextureID.CONJ_BLUE_LEFT_LEFT,ImageIO.read(new File("res/textures/entities/player/conjuror_blue/left_left.png")));
		Resources.TEXTURES.add(TextureID.CONJ_BLUE_LEFT_RIGHT,ImageIO.read(new File("res/textures/entities/player/conjuror_blue/left_right.png")));
		Resources.TEXTURES.add(TextureID.CONJ_BLUE_RIGHT, ImageIO.read(new File("res/textures/entities/player/conjuror_blue/right.png")));
		Resources.TEXTURES.add(TextureID.CONJ_BLUE_RIGHT_LEFT,ImageIO.read(new File("res/textures/entities/player/conjuror_blue/right_left.png")));
		Resources.TEXTURES.add(TextureID.CONJ_BLUE_RIGHT_RIGHT, ImageIO.read(new File("res/textures/entities/player/conjuror_blue/right_right.png")));
		Resources.TEXTURES.add(TextureID.CONJ_BLUE_BACK,ImageIO.read(new File("res/textures/entities/player/conjuror_blue/back.png")));
		Resources.TEXTURES.add(TextureID.CONJ_BLUE_BACK_LEFT,ImageIO.read(new File("res/textures/entities/player/conjuror_blue/back_left.png")));
		Resources.TEXTURES.add(TextureID.CONJ_BLUE_BACK_RIGHT,ImageIO.read(new File("res/textures/entities/player/conjuror_blue/back_right.png")));
		Resources.TEXTURES.add(TextureID.SKELETON_FRONT, ImageIO.read(new File("res/textures/entities/enemies/skeleton/front.png")));
		Resources.TEXTURES.add(TextureID.SKELETON_FRONT_LEFT,ImageIO.read(new File("res/textures/entities/enemies/skeleton/front_left.png")));
		Resources.TEXTURES.add(TextureID.SKELETON_FRONT_RIGHT, ImageIO.read(new File("res/textures/entities/enemies/skeleton/front_right.png")));
		Resources.TEXTURES.add(TextureID.SKELETON_LEFT, ImageIO.read(new File("res/textures/entities/enemies/skeleton/left.png")));
		Resources.TEXTURES.add(TextureID.SKELETON_LEFT_LEFT,ImageIO.read(new File("res/textures/entities/enemies/skeleton/left_left.png")));
		Resources.TEXTURES.add(TextureID.SKELETON_LEFT_RIGHT,ImageIO.read(new File("res/textures/entities/enemies/skeleton/left_right.png")));
		Resources.TEXTURES.add(TextureID.SKELETON_RIGHT, ImageIO.read(new File("res/textures/entities/enemies/skeleton/right.png")));
		Resources.TEXTURES.add(TextureID.SKELETON_RIGHT_LEFT,ImageIO.read(new File("res/textures/entities/enemies/skeleton/right_left.png")));
		Resources.TEXTURES.add(TextureID.SKELETON_RIGHT_RIGHT, ImageIO.read(new File("res/textures/entities/enemies/skeleton/right_right.png")));
		Resources.TEXTURES.add(TextureID.SKELETON_BACK,ImageIO.read(new File("res/textures/entities/enemies/skeleton/back.png")));
		Resources.TEXTURES.add(TextureID.SKELETON_BACK_LEFT,ImageIO.read(new File("res/textures/entities/enemies/skeleton/back_left.png")));
		Resources.TEXTURES.add(TextureID.SKELETON_BACK_RIGHT,ImageIO.read(new File("res/textures/entities/enemies/skeleton/back_right.png")));
		Resources.TEXTURES.add(TextureID.MAGIC_BULLET,ImageIO.read(new File("res/textures/entities/projectiles/magic/magic_bullet.png")));
		Resources.TEXTURES.add(TextureID.BOMB,ImageIO.read(new File("res/textures/entities/misc/bomb.png")));
	} catch (IOException e) {
		e.printStackTrace();
	}
}
}