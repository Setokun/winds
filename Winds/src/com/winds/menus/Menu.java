package com.winds.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.winds.window.Game;

public class Menu {

	public enum typeMenu{MAIN, PLAY, SCORES, CONFIGURATION, SHOP, EDITOR, PROFILE};
	
	private int leftGap = 72;
	private int buttonWidth = 150;
	public typeMenu type;
	
	private Rectangle playButton = new Rectangle((Game.WIDTH / 2 - leftGap) * Game.SCALE, 50 * Game.SCALE, buttonWidth*Game.SCALE, 25*Game.SCALE);
	private Rectangle scoreButton = new Rectangle((Game.WIDTH / 2 - leftGap) * Game.SCALE, 85 * Game.SCALE, buttonWidth*Game.SCALE, 25*Game.SCALE);
	private Rectangle configButton = new Rectangle((Game.WIDTH / 2 - leftGap) * Game.SCALE, 120 * Game.SCALE, buttonWidth*Game.SCALE, 25*Game.SCALE);
	private Rectangle shopButton = new Rectangle((Game.WIDTH / 2 - leftGap) * Game.SCALE, 155 * Game.SCALE, buttonWidth*Game.SCALE, 25*Game.SCALE);
	private Rectangle editorButton = new Rectangle((Game.WIDTH / 2 - leftGap) * Game.SCALE, 190 * Game.SCALE, buttonWidth*Game.SCALE, 25*Game.SCALE);
	private Rectangle changeProfileButton = new Rectangle((Game.WIDTH / 2 - leftGap) * Game.SCALE, 225 * Game.SCALE, buttonWidth*Game.SCALE, 25*Game.SCALE);
	private Rectangle quitButton = new Rectangle((Game.WIDTH / 2 - leftGap) * Game.SCALE, 260 * Game.SCALE, buttonWidth*Game.SCALE, 25*Game.SCALE);
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		if(type == typeMenu.MAIN){
			Font fnt0 = new Font("arial", Font.BOLD, 25*Game.SCALE);
			g.setFont(fnt0);
			g.setColor(Color.WHITE);
			g.drawString("Winds", (Game.WIDTH / 2 - 35) * Game.SCALE, 35 * Game.SCALE);
			
			Font fnt1 = new Font("arial", Font.BOLD, 15*Game.SCALE);
			g.setFont(fnt1);
			g2d.draw(playButton);
			g.drawString("Play", (Game.WIDTH / 2 - 12) * Game.SCALE, 68 * Game.SCALE);
			g2d.draw(scoreButton);
			g.drawString("Scores", (Game.WIDTH / 2 - 22) * Game.SCALE, 103 * Game.SCALE);
			g2d.draw(configButton);
			g.drawString("Configuration", (Game.WIDTH / 2 - 42) * Game.SCALE, 138 * Game.SCALE);
			g2d.draw(shopButton);
			g.drawString("Shop", (Game.WIDTH / 2 - 15) * Game.SCALE, 173 * Game.SCALE);
			g2d.draw(editorButton);
			g.drawString("Level Editor", (Game.WIDTH / 2 - 38) * Game.SCALE, 208 * Game.SCALE);
			g2d.draw(changeProfileButton);
			g.drawString("ChangeProfile", (Game.WIDTH / 2 - 46) * Game.SCALE, 243 * Game.SCALE);
			g2d.draw(quitButton);
			g.drawString("Quit", (Game.WIDTH / 2 - 13) * Game.SCALE, 278 * Game.SCALE);
		}
		
		if(type == typeMenu.PLAY){
			Font fnt0 = new Font("arial", Font.BOLD, 25*Game.SCALE);
			g.setFont(fnt0);
			g.setColor(Color.WHITE);
			g.drawString("Play menu", (Game.WIDTH / 2 - 35) * Game.SCALE, 35 * Game.SCALE);
		}
	}
}
