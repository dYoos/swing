package io.robusta.fora.swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;

import io.robusta.fora.ForaDataSource;
import io.robusta.fora.domain.Comment;
import io.robusta.fora.domain.Subject;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SubjectView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Subject model;
	SubjectController controller;

	private Box subjectPanel;
	private JTextPane titlePane;
	private JTextPane contentPane;
	private JPanel commentsList;
	private JButton addComment;
	private JButton showComment;
	private JPanel addCommentPanel;
	private boolean visibleComments;

	private JTextArea entreComment;

	private JCheckBox anonymous;


	public SubjectView() {
		this.model = ForaDataSource.getInstance().getSubjects().get(0);
		initView();
	}

	public SubjectView(Subject subject) {
		super();
		this.model = subject;
		initView();
	}

	/**
	 * Create the panel.
	 */
	private void initView() {

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		subjectPanel = Box.createHorizontalBox();

		// title
		titlePane = new JTextPane();
		titlePane.setFont(new Font("Tahoma", Font.BOLD, 11));
		titlePane.setBackground(UIManager.getColor("Label.background"));
		titlePane.setEditable(false);
		titlePane.setText(model.getTitle());
		add(titlePane);

		// content
		contentPane = new JTextPane();
		// contentPane.setPreferredSize(new Dimension(250, 100));
		contentPane.setBackground(UIManager.getColor("Label.background"));
		contentPane.setEditable(false);
		contentPane.setText(model.getContent());
		subjectPanel.add(new JScrollPane(contentPane));

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

		subjectPanel.add(userPanel);

		// button and flags
		JPanel actionsPanel = new JPanel(new GridLayout(2, 3, 3, 3));
		// like button
		JButton buttonLike = new JButton("");
		buttonLike.setIcon(new ImageIcon(CommentView.class
				.getResource("/io/robusta/fora/swing/images/like.png")));

		// resize the icon
		String iconURL = "/io/robusta/fora/swing/images/like.png";
		buttonLike.setIcon(Tools.resizeIcon(iconURL, 40, 40));
		actionsPanel.add(buttonLike);

		// dislike button
		JButton buttonDislike = new JButton("");
		buttonDislike.setIcon(new ImageIcon(CommentView.class
				.getResource("/io/robusta/fora/swing/images/dislike.png")));
		actionsPanel.add(buttonDislike);

		// flag button
		JButton buttonFlag = new JButton("");
		buttonFlag.setIcon(new ImageIcon(CommentView.class
				.getResource("/io/robusta/fora/swing/images/flag.jpg")));
		actionsPanel.add(buttonFlag);

		addComment = new JButton("add comment");
//		addComment.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				int response = JOptionPane.showConfirmDialog(null, addCommentPanel, "Ajouter un commentaire",
//						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//				if (response == JOptionPane.OK_OPTION ) {
//					controller.addComment("string");
//				}
//			}
//		});

		// show/hide comments
		visibleComments = false;
		showComment = new JButton("Show comments");
		showComment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!visibleComments) {
					add((commentsList));
					showComment.setText("Hide comments");
					visibleComments = true;
				} else {
					remove(commentsList);
					showComment.setText("Show comments");
					visibleComments = false;
				}
				revalidate();
				SwingApp.resize();
			}
		});
		actionsPanel.add(addComment);
		actionsPanel.add(showComment);

		subjectPanel.add(actionsPanel);

		add(subjectPanel);

		// comments list
		commentsList = new JPanel();
		commentsList
				.setLayout(new BoxLayout(commentsList, BoxLayout.PAGE_AXIS));
		for (Comment c : model.getComments()) {
			CommentView commentView = new CommentView(c);
			CommentController controller = new CommentController(c, commentView);
			commentView.setController(controller);
			commentsList.add(commentView);
		}
		
		/*
		 *  add comment panel
		 */
		addCommentPanel =  new JPanel();
		entreComment = new JTextArea(5, 30);
		anonymous = new JCheckBox("Anonymous", true);
		
		addCommentPanel.add(entreComment);
		addCommentPanel.add(anonymous);
		
	}

}
