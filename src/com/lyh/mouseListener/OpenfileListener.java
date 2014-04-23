package com.lyh.mouseListener;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.media.CannotRealizeException;
import javax.media.IncompatibleSourceException;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.Time;
import javax.media.protocol.DataSource;
import javax.swing.JFrame;

import com.lyh.bean.GlobalParameter;

public class OpenfileListener implements ActionListener{
	private JFrame frame;
	private GlobalParameter globalParam;
	private Player player;
	private int width;
	private int height;
	private int sta = 0;
	
	public OpenfileListener(JFrame frame,GlobalParameter globalParam) {
		this.frame = frame;
		this.globalParam = globalParam;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		FileDialog dialog = new FileDialog(frame);
		dialog.setVisible(true);
		String filePath = dialog.getDirectory();
		String fileName = dialog.getFile();
		String OpenFile = "file://////" + filePath + fileName;
		
		File file = new File(OpenFile);		
		
		globalParam.setMediaFile(file);
		
		MediaLocator loc = new MediaLocator(globalParam.getMediaFile().getPath());
		if(player == null){
			player = globalParam.getPlayer();
		}else{
			player.close();
			if(globalParam.getIsAudioOrVideoPlayer() == 0){
				
			}else if(globalParam.getIsAudioOrVideoPlayer() == 1){
		
			}else if(globalParam.getIsAudioOrVideoPlayer() == 2){
				frame.remove(globalParam.getVisual());
				frame.remove(globalParam.getControl());
			}
			player = globalParam.getPlayer();
		}
		
		try {
			player = Manager.createRealizedPlayer(loc);  
			playVideo();
		} catch (Exception e1) {
			try {
				System.out.println("11111");
				player = Manager.createPlayer(loc);
				System.out.println("222222");
				playAudio();
			} catch (Exception e2) {
				if(globalParam.getIsAudioOrVideoPlayer() == 0){
					
				}else if(globalParam.getIsAudioOrVideoPlayer() == 1){
					globalParam.setIsAudioOrVideoPlayer(0);
				}else if(globalParam.getIsAudioOrVideoPlayer() == 2){
					frame.remove(globalParam.getVisual());
					frame.remove(globalParam.getControl());
					globalParam.setIsAudioOrVideoPlayer(0);
				}
			}
		} 
	}
	
	public void playVideo(){	
		globalParam.setIsAudioOrVideoPlayer(2);
		globalParam.setPlayer(player);
		Component visual = null;
		Component control = null;
		Dimension size = null;
		try{			
			visual = player.getVisualComponent();
			control = player.getControlPanelComponent();
			size = visual.getPreferredSize();
		}catch(Exception ex){		
		}
		
		frame.add(visual,BorderLayout.CENTER);
		frame.add(control,BorderLayout.SOUTH);
		
		globalParam.setControl(control);
		globalParam.setVisual(visual);
		
		if(sta == 0){
			width = (int) (size.getWidth()*(300/(size.getHeight())));
			height = (int) (300 + control.getPreferredSize().getHeight());
			sta = 1;
		}else{
			width = frame.getWidth();
			height = frame.getHeight();
		}		
		frame.setSize(width, height);
		frame.repaint();
		frame.show();
		globalParam.getPlayer().start();
	}
	
	public void playAudio(){
		globalParam.setIsAudioOrVideoPlayer(1);
		player.realize(); 
	}
}
