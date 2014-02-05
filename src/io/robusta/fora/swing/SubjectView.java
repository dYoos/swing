package io.robusta.fora.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;

import io.robusta.fora.ForaDataSource;
import io.robusta.fora.domain.Comment;
import io.robusta.fora.domain.Subject;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JTextPane;

/*
 * TODO Récupérer et afficher le sujet « Pirelli »
 Récupérer et afficher la liste de ses commentaires
 */

public class SubjectView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Subject model;
	SubjectController controller;

	private JTextPane titlePane;
	private JTextPane contentPane;

	public SubjectView() {
		this.model = ForaDataSource.getInstance().getSubjects().get(0);
		initView();
	}
	
	public SubjectView(Subject subject) {
		this.model = subject;
		initView();
	}

	/**
	 * Create the panel.
	 */
	private void initView() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// add title
		titlePane = new JTextPane();
		titlePane.setEditable(false);
		titlePane.setText(model.getTitle());
		titlePane.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(titlePane);

		// add content
		contentPane = new JTextPane();
		contentPane.setEditable(false);
		contentPane.setText(model.getContent());
		contentPane.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(contentPane);
		
		// add comments
		for (Comment c : model.getComments()) {
			CommentView commentView = new CommentView(c);
			CommentController controller = new CommentController(c, commentView);
			commentView.setController(controller);
			add(commentView);
		}
	}

}
