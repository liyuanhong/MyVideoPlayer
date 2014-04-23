package com.lyh.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.lyh.bean.GlobalParameter;
import com.lyh.mouseListener.ExitListener;
import com.lyh.mouseListener.OpenfileListener;

public class MainFrame {
	private GlobalParameter globalParam = new GlobalParameter();  //transfer the useful parameter
	
	private JFrame frame = new JFrame();  //the main frame
	
	private JMenuBar menubar = new JMenuBar(); //the top menu bar
	
	private JMenu filemenu = new JMenu("file");  //file menu selection "file"
	private JMenuItem fileopen = new JMenuItem("open"); //the open item
	private JMenuItem fileexit = new JMenuItem("exit"); //the exit item
	
	
	public MainFrame() {
		init();
	}
	
	/**
	 * initialize the window
	 */
	public void init(){		
		frame.setLocation(300, 200);
		frame.add(menubar,BorderLayout.NORTH);
		menubar.add(filemenu);

		filemenu.add(fileopen);
		filemenu.add(fileexit);
		frame.setSize(300, 300);
		frame.setVisible(true);	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addMouseListeners();
	}
	
	/**
	 * add listener for all of the compernent on mouse click
	 */
	public void addMouseListeners(){
		fileopen.addActionListener(new OpenfileListener(frame,globalParam));
		fileexit.addActionListener(new ExitListener());
	}
	
	/**
	 * add listener for some of the compernent on keydown
	 */
	public void addKeyboardListener(){
		
	}
}
