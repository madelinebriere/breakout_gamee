import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * General class for any piece in game 
 * Based on Sprite class by Carl Dea (cdea)
 * Most of the Objects used in BreakoutWorld are instances of GamePiece. 
 * Instances of GamePiece have an ImageView which can be updated by position to
 * simulate animation. 
 * 
 * All GamePieces have a collide method that registers when two ImageViews overlap.
 * Likewise, the update method is important because every GamePiece updates in a
 * unique way -- producing different actors/actions.
 * 
 * @author maddiebriere
 *
 */
public abstract class GamePiece {
	private ImageView myImage; // Each GamePiece has an image that represents it
	private String myType;

	public GamePiece(double x, double y, String type) {
		myImage = buildImage(type, x, y);
		myType = type;
	}

	/**
	 * All GamePieces will have to be updated in their own ways
	 */
	public abstract void update(int size, int level);

	/**
	 * Check if this GamePiece has collided with another GamePiece
	 */
	public boolean collide(GamePiece other) {
		return this.myImage.intersects(other.myImage.getBoundsInLocal());
	}

	/** 
	 * @param pieces,
	 *            a List of pieces Add all pieces to a Group in some scene
	 */
	public static <T extends GamePiece> void addImages(ArrayList<T> pieces, Group root) {
		for (GamePiece g : pieces) {
			root.getChildren().add(g.myImage);
		}
	}

	public static <T extends GamePiece> void addImage(GamePiece piece, Group root) {
		root.getChildren().add(piece.myImage);
	}

	/**
	 * Use when variables change or image must be created in the first place
	 * 
	 * @return ImageView with GamePiece variables
	 */
	public ImageView buildImage(String type, double x, double y) {
		Image im3 = new Image(getClass().getClassLoader().getResourceAsStream(type));
		ImageView toRet = new ImageView(im3);
		toRet.setX(x - toRet.getBoundsInLocal().getWidth() / 2);
		toRet.setY(y - toRet.getBoundsInLocal().getHeight() / 2);
		return toRet;
	}

	/**
	 * @return x position of center of GamePiece
	 */
	public double getX() {
		return myImage.getX();
	}

	/**
	 * @return y position of center of GamePiece
	 */
	public double getY() {
		return myImage.getY();
	}

	public void setCenterX(double x) {
		myImage.setX(x - this.getWidth() / 2);
	}

	public void setCenterY(double y) {
		myImage.setY(y - this.getHeight() / 2);
	}

	public double getCenterX() {
		return getX() + this.getWidth() / 2;
	}

	public double getCenterY() {
		return getY() + this.getHeight() / 2;
	}

	public void setX(double x) {
		myImage.setX(x);
	}

	public void setY(double y) {
		myImage.setY(y);
	}

	public ImageView getMyImage() {
		return myImage;
	}

	public void setMyImage(ImageView myImage) {
		this.myImage = myImage;
	}

	public double getHeight() {
		return myImage.getBoundsInLocal().getHeight();
	}

	public double getWidth() {
		return myImage.getBoundsInLocal().getWidth();
	}

	public String getMyType() {
		return myType;
	}

	public void setMyType(String myType) {
		this.myType = myType;
	}

}
