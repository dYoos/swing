package io.robusta.fora.swing;

import java.awt.Color;
import java.awt.Container;

import io.robusta.fora.ForaDataSource;
import io.robusta.fora.domain.Subject;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateCommentView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Subject model;
	SubjectController controller;
	
	private JTextArea enterComment;
	private JCheckBox anonymous;
	private JButton cancelButton;
	private JButton okButton;
	private JPanel buttonsPanel;
	private Box creatCommentBox;
	
	

	/**
	 * Create the panel.
	 */
	public CreateCommentView() {
//		this.model = new CreateCommentModel();
		initView();
	}

	public CreateCommentView(Subject subject) {
		this.model = subject;
		initView();
	}


	private void initView() {
		
		creatCommentBox = Box.createVerticalBox();
		
		enterComment = new JTextArea();
		anonymous = new JCheckBox("Anonymous", true);
		
		creatCommentBox.add(enterComment);
		creatCommentBox.add(anonymous);

		buttonsPanel = new JPanel();
		okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cancelButton = new JButton("Annuler");
		
		buttonsPanel.add(okButton);
		buttonsPanel.add(cancelButton);
		creatCommentBox.add(buttonsPanel);
		
		
	}

	public SubjectController getController() {
		return controller;
	}

	public void setController(SubjectController controller) {
		this.controller = controller;
	}

	public void showError(String string) {
		// TODO Auto-generated method stub
		
	}

}
