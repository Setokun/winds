package core;

import java.awt.image.BufferedImage;

import display.BufferedImageLoader;

public class Texture {

	SpriteSheet bs, ps, bu, lj11, lj13, lj15, bib1, bib2, bib3, bib4, ronces, brambles;
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	private BufferedImage bulle_sheet = null;
	private BufferedImage lockjaw11_sheet = null;
	private BufferedImage lockjaw13_sheet = null;
	private BufferedImage lockjaw15_sheet = null;
	private BufferedImage bib_001_sheet = null;
	private BufferedImage bib_002_sheet = null;
	private BufferedImage bib_003_sheet = null;
	private BufferedImage bib_004_sheet = null;
	private BufferedImage ronces_sheet = null;
	private BufferedImage brambles_sheet = null;
	
	public BufferedImage[] block = new BufferedImage[2];
	public BufferedImage[] player = new BufferedImage[4];
	public BufferedImage[] bulle = new BufferedImage[1];
	public BufferedImage[] lvl_lockjaw11 = new BufferedImage[64];
	public BufferedImage[] lvl_lockjaw13 = new BufferedImage[64];
	public BufferedImage[] lvl_lockjaw15 = new BufferedImage[64];
	public BufferedImage[] lvl_bib1 = new BufferedImage[64];
	public BufferedImage[] lvl_bib2 = new BufferedImage[64];
	public BufferedImage[] lvl_bib3 = new BufferedImage[64];
	public BufferedImage[] lvl_bib4 = new BufferedImage[64];
	public BufferedImage[] lvl_ronces = new BufferedImage[384];
	public BufferedImage[] lvl_brambles = new BufferedImage[20];
	
	public Texture(){
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try{
			block_sheet = loader.loadImage("/block_sheet.png");
			player_sheet = loader.loadImage("/player_sheet.png");
			bulle_sheet = loader.loadImage("/bulle2.png");
			lockjaw11_sheet = loader.loadImage("/Lockjaw_11.png");
			lockjaw13_sheet = loader.loadImage("/Lockjaw_13.png");
			lockjaw15_sheet = loader.loadImage("/Lockjaw_15.png");
			bib_001_sheet = loader.loadImage("/Black-Ice-Battle_001.png");
			bib_002_sheet = loader.loadImage("/Black-Ice-Battle_002.png");
			bib_003_sheet = loader.loadImage("/Black-Ice-Battle_003.png");
			bib_004_sheet = loader.loadImage("/Black-Ice-Battle_004.png");
			ronces_sheet = loader.loadImage("/ronces.png");
			brambles_sheet = loader.loadImage("/themes/brambles_20.png");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);
		bu = new SpriteSheet(bulle_sheet);
		lj11 = new SpriteSheet(lockjaw11_sheet);
		lj13 = new SpriteSheet(lockjaw13_sheet);
		lj15 = new SpriteSheet(lockjaw15_sheet);
		bib1 = new SpriteSheet(bib_001_sheet);
		bib2 = new SpriteSheet(bib_002_sheet);
		bib3 = new SpriteSheet(bib_003_sheet);
		bib4 = new SpriteSheet(bib_004_sheet);
		ronces = new SpriteSheet(ronces_sheet);
		brambles = new SpriteSheet(brambles_sheet);
		
		getTextures();
		getTextures128();
		
	}

	private void getTextures() {
		block[0] = bs.grabImage(1, 1, 32, 32);
		block[1] = bs.grabImage(2, 1, 32, 32);
		
		//player[0] = ps.grabImage(1, 1, 32, 54); // idle
		//player[1] = ps.grabImage(2, 1, 32, 54); // walking 1
		//player[2] = ps.grabImage(3, 1, 32, 54); // walking 2
		//player[3] = ps.grabImage(4, 1, 32, 54); // walking 3
		
		bulle[0] = bu.grabImage(1, 1, 64, 64); // idle bubble

		// chargement du spriteSheet lockjaw_11
		for(int i=1; i<=8; i++){
			for(int j=1; j<=8; j++){
				int index = (i-1)*8 + j-1;
				lvl_lockjaw11[index] = lj11.grabImage(j, i, 32, 32);
			}
		}
		
		// chargement du spriteSheet lockjaw_13
		for(int i=1; i<=8; i++){
			for(int j=1; j<=8; j++){
				int index = (i-1)*8 + j-1;
				lvl_lockjaw13[index] = lj13.grabImage(j, i, 32, 32);
			}
		}
		
		// chargement du spriteSheet lockjaw_15
		for(int i=1; i<=8; i++){
			for(int j=1; j<=8; j++){
				int index = (i-1)*8 + j-1;
				lvl_lockjaw15[index] = lj15.grabImage(j, i, 32, 32);
			}
		}
		
		// chargement du spriteSheet Black-Ice-Battle_001
		for(int i=1; i<=8; i++){
			for(int j=1; j<=8; j++){
				int index = (i-1)*8 + j-1;
				lvl_bib1[index] = bib1.grabImage(j, i, 32, 32);
			}
		}
		
		// chargement du spriteSheet Black-Ice-Battle_002
		for(int i=1; i<=8; i++){
			for(int j=1; j<=8; j++){
				int index = (i-1)*8 + j-1;
				lvl_bib2[index] = bib2.grabImage(j, i, 32, 32);
			}
		}
		
		// chargement du spriteSheet Black-Ice-Battle_003
		for(int i=1; i<=8; i++){
			for(int j=1; j<=8; j++){
				int index = (i-1)*8 + j-1;
				lvl_bib3[index] = bib3.grabImage(j, i, 32, 32);
			}
		}
		
		// chargement du spriteSheet Black-Ice-Battle_004
		for(int i=1; i<=8; i++){
			for(int j=1; j<=8; j++){
				int index = (i-1)*8 + j-1;
				lvl_bib4[index] = bib4.grabImage(j, i, 32, 32);
			}
		}
		
		// chargement du spriteSheet Ronces
		for(int i=1; i<=16; i++){
			for(int j=1; j<=24; j++){
				int index = (i-1)*24 + j-1;
				lvl_ronces[index] = ronces.grabImage(j, i, 32, 32);
			}
		}
		
		
	}
	
	private void getTextures128(){
		// chargement du spriteSheet themes/brambles20
		for(int i=1; i<=5; i++){
			for(int j=1; j<=4; j++){
				int index = (i-1)*4 + j-1;
				lvl_brambles[index] = brambles.grabImage(j, i, 128, 128);
			}
		}
	}
	
	
}
