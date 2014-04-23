package com.lyh.bean;

import java.awt.Component;
import java.io.File;

import javax.media.Player;
import javax.swing.JFrame;

public class GlobalParameter {
	private File mediaFile;  //to transfer the opened mediaFile
	private Player player; //the media player
	private Component visual;
	private Component control;
	private int isAudioOrVideoPlayer;  //0:none,1:audio,2:video

	public File getMediaFile() {
		return mediaFile;
	}
	public void setMediaFile(File mediaFile) {
		this.mediaFile = mediaFile;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Component getVisual() {
		return visual;
	}
	public void setVisual(Component visual) {
		this.visual = visual;
	}
	public Component getControl() {
		return control;
	}
	public void setControl(Component control) {
		this.control = control;
	}
	public int getIsAudioOrVideoPlayer() {
		return isAudioOrVideoPlayer;
	}
	public void setIsAudioOrVideoPlayer(int isAudioOrVideoPlayer) {
		this.isAudioOrVideoPlayer = isAudioOrVideoPlayer;
	}
}
