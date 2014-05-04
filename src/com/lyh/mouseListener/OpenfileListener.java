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
import javax.media.Control;
import javax.media.IncompatibleSourceException;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.Time;
import javax.media.protocol.DataSource;
import javax.swing.JFrame;

import com.lyh.bean.GlobalParameter;
import com.sun.media.PlaybackEngine;

public class OpenfileListener implements ActionListener{
	private JFrame frame;
	private GlobalParameter globalParam;
	private Player player;
	private int width = 300;
	private int height = 300;
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
//			player = globalParam.getPlayer();
		}else{
			player.close();
			if(globalParam.getIsAudioOrVideoPlayer() == 0){
				
			}else if(globalParam.getIsAudioOrVideoPlayer() == 1){
				frame.remove(globalParam.getControl());
			}else if(globalParam.getIsAudioOrVideoPlayer() == 2){
				frame.remove(globalParam.getVisual());
				frame.remove(globalParam.getControl());
			}
//			player = globalParam.getPlayer();
		}
		
		try {
			player = Manager.createRealizedPlayer(loc);  
			playVideo();
		} catch (Exception e1) {
			try {
				player = Manager.createRealizedPlayer(loc);
				playAudio();
			} catch (Exception e2) {
				if(globalParam.getIsAudioOrVideoPlayer() == 0){
					
				}else if(globalParam.getIsAudioOrVideoPlayer() == 1){
					frame.remove(globalParam.getControl());
					globalParam.setIsAudioOrVideoPlayer(0);
				}else if(globalParam.getIsAudioOrVideoPlayer() == 2){
					frame.remove(globalParam.getVisual());
					frame.remove(globalParam.getControl());
					globalParam.setIsAudioOrVideoPlayer(0);
				}
			}
		} 
	}
	
	
	//播放视频方法
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
	
	//播放音频
	public void playAudio(){
		globalParam.setIsAudioOrVideoPlayer(1);
		player.realize();
		Component control = player.getControlPanelComponent();
		frame.add(control);
		frame.setSize(width, 100);
		globalParam.setControl(control);
		player.start(); 
		
		sta = 0;  //调整为视频播放状态，就可以改变窗口状态
	}
}
