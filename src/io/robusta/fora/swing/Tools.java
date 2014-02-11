package io.robusta.fora.swing;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Tools {

	/**
	 * gets and resizes the icon from the image in imageURL 
	 * @param imageURL
	 * @param widht
	 * @param height
	 * @return
	 */
	public static Icon resizeIcon(String imageURL, int widht, int height) {
		ImageIcon icon = new ImageIcon(CommentView.class.getResource(imageURL));

		Image likeImage = icon.getImage();
		likeImage = likeImage
				.getScaledInstance(widht, height, Image.SCALE_FAST);
		icon.setImage(likeImage);
		return icon;
	}

}
