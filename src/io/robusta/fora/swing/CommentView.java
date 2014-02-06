package io.robusta.fora.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import io.robusta.fora.ForaDataSource;
import io.robusta.fora.domain.Comment;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.UIManager;

public class CommentView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @wbp.nonvisual location=130,379
	 */
	Comment model;
	CommentController controller;

	public CommentView() {
		this.model = ForaDataSource.getInstance().getComments().get(1);
		initView();
	}

	public CommentView(Comment comment) {
		this.model = comment;
		initView();
	}

	private JTextPane commentPane;
	private BoxLayout hBox;

	/**
	 * Create the panel.
	 */
	public void initView() {

		// layout
		hBox = new BoxLayout(this, BoxLayout.LINE_AXIS);
		setLayout(hBox);

		// comment
		commentPane = new JTextPane();
		commentPane.setPreferredSize(new Dimension(250, 100));
		commentPane.setBackground(UIManager.getColor("Label.background"));
		commentPane.setEditable(false);
		commentPane.setText(model.getContent());
		add(new JScrollPane(commentPane));

		// user
		// display user name under the user's avatar using GridLayout 2x1
		JPanel userPanel = new JPanel(new GridLayout(2, 1, 0, 0));
		userPanel.setPreferredSize(new Dimension(80, 80));

		JLabel lblUserAvatar = new JLabel();
		lblUserAvatar.setIcon(new ImageIcon(CommentView.class
				.getResource("/io/robusta/fora/swing/images/user.png")));
		userPanel.add(lblUserAvatar);

		JLabel lblUserName = new JLabel(String.valueOf(model.getUser()));
		userPanel.add(lblUserName);
		add(userPanel);

		// like button
		JButton buttonLike = new JButton("");
		buttonLike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.like();
				updateContentColor();
			}
		});
		buttonLike.setIcon(new ImageIcon(CommentView.class
				.getResource("/io/robusta/fora/swing/images/like.png")));

		// resize the icon
		String iconURL = "/io/robusta/fora/swing/images/like.png";
		buttonLike.setIcon(resizeIcon(iconURL, 40, 40));
		add(buttonLike);

		// dislike button
		JButton buttonDislike = new JButton("");
		buttonDislike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.dislike();
				updateContentColor();
			}
		});
		buttonDislike.setIcon(new ImageIcon(CommentView.class
				.getResource("/io/robusta/fora/swing/images/dislike.png")));
		add(buttonDislike);

		// flag button
		JButton buttonFlag = new JButton("");
		buttonFlag.setIcon(new ImageIcon(CommentView.class
				.getResource("/io/robusta/fora/swing/images/flag.jpg")));
		add(buttonFlag);

	}

	private Icon resizeIcon(String iconURL, int widht, int height) {
		ImageIcon icon = new ImageIcon(CommentView.class.getResource(iconURL));

		Image likeImage = icon.getImage();
		likeImage = likeImage
				.getScaledInstance(widht, height, Image.SCALE_FAST);
		icon.setImage(likeImage);
		return icon;
	}

	public void setController(CommentController controller) {
		this.controller = controller;
	}

	private void updateContentColor() {
		if (this.model.getScore() > 0) {
			commentPane.setForeground(Color.GREEN);
		} else if (this.model.getScore() < 0) {
			commentPane.setForeground(Color.RED);
		} else {
			commentPane.setForeground(Color.BLACK);
		}
	}
}
