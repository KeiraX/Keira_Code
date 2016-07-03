package cn.blockgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.text.StyleConstants.FontConstants;

/**
 * ���ܣ���Ϸ�����������,ʵ����Ϸ���߼��㷨,������������䡢��ת��ƽ�ơ����е�
 * 
 * @author Keira
 *
 */
public class GameAreaPanel extends JPanel {
	// һ������Ĵ�С
	public static final int BLOCK_SIZE = 20;
	public static int BLOCK_WIDTH = 20;
	public static int BLOCK_HEIGHT = 20;
	// �������������к���
	public static final int ROW = 30 + 1; // ���һ�б߽�(�ײ�)
	public static final int COL = 20 + 2; // ������б߽�(��������)
	// �������Ŀ���
	public static final int GAMEAREA_WIDTH = COL * BLOCK_SIZE;
	public static final int GAMEAREA_HEIGHT = ROW * BLOCK_SIZE;
	//
	public static int shapeRow = 0;
	public static int shapeCol = 10;
	// �����������Ĵ󷽸�����
	Block[][] blockArr = new Block[ROW][COL];
	// ����ʵ�ʻ����,��4*4��С����
	static int[][] shapeTemp = null;
	// ��ʱ�ı���,��ʼֵΪ1000
	int delay = 1000;
	// ��Ϸ��ʼ���ı�־
	public boolean isStart = false;
	// ��Ϸ�Ƿ����
	public boolean isGameOver = false;
	// ����Ƥ������
	public String skin = "c1";
	// ��ֽƤ���ı���
	public String wallPaper = "0";
	// ������ɫͼƬ����ʱ����
	private int tuColor = 0;
	int i = 0;
	int j = 0;
	
	public int getTuColor() {
		return tuColor;
	}
	public void setTuColor(int tuColor) {
		this.tuColor = tuColor;
	}
	
	// ��ʱ����һ��
	Timer timer = new Timer(delay, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// 4���� ++
			shapeRow++;
			
			// ���ñ���ʵ��ÿ��ʱ����һ�������ͻ�һ�α���ͼƬ			
			i ++;
			while(i == 20) {
				wallPaper = "" + j;
				j ++;
				if(j == 11) {
					j = 0;
				}				
				i = 0;
			}
			
			// ���ػ湦������3������
			GamePanel.jp_fun.jp_preview.repaint();
			GamePanel.jp_fun.jp_operation.repaint();
			GamePanel.jp_fun.jp_score.repaint();
			// �ػ���������������;����repaint������Ȼ��ϵͳ��ȥ������д��paintComponent����
			GameAreaPanel.this.repaint();
		}
	});
	
	// TODO ���Ƶײ㿪ʼ�Զ����ǵķ��飬��ʱ����һ��
	Timer timer2 = new Timer(750, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			Random ranTemp = new Random();
			int[] iTemp = new int[5]; 
			gameOver();
			if(isStart) { // ��ʼ��Ϸ
				// �������һЩ�շ���
				for(int i = 0; i < iTemp.length; i ++) {
					iTemp[i] = ranTemp.nextInt((COL - 3)) + 1; 
				}
			}
			for (int r = 0; r < blockArr.length - 1; r++) {
				for (int c = 1; c < blockArr[r].length - 1; c++) {
					if (r == blockArr.length - 2) {
						if (c == iTemp[0] || c == iTemp[1] || c == iTemp[2] || c == iTemp[3] || c == iTemp[4]) {
							blockArr[r][c].setType(0);
						} else {
							blockArr[r][c].setType(2);
						}
					}else {
						// �������ݣ���r+i�и�ֵ��r�У�ʵ�ַ�����������һλ
						blockArr[r][c].setType(blockArr[r + 1][c].getType());
					}
				}
			}
			// �ػ���������������
			GameAreaPanel.this.repaint();
		}
	});
	

	public GameAreaPanel() {
		// ���󷽸������ʼ��
		for (int r = 0; r < ROW; r++) {
			for (int c = 0; c < COL; c++) {
				blockArr[r][c] = new Block();
				// ���õײ�����ߡ��ұߵ�����Χ���Ǳ߽�Ϊ2�ķ���ǽ
				if (r == ROW - 1 || c == 0 || c == COL - 1) {
					blockArr[r][c].setType(2);
				}
			}
		}

		// ������ʱ��
		//timer.start();
		
		// ����������ȡ����
		GameAreaPanel.this.setFocusable(true);
		// ����������Ӽ��̼����¼�
		GameAreaPanel.this.addKeyListener(new KeyControl(this));

		// ������������С
		this.setPreferredSize(new Dimension(GAMEAREA_WIDTH, GAMEAREA_HEIGHT));
		// �������͸����
		this.setOpaque(false);
		// �������߿���ɫ
		//this.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
	}

	/**
	 * ���ܣ���дpaintComponent����
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// ��ӱ���ͼƬ
//		Image gameBack = Toolkit.getDefaultToolkit().getImage("resources\\image\\star.jpg");
//		g.drawImage(gameBack,0,0,this);

		// ���ڵ���ʱ,����ᰴ�����Ŵ�
//		BLOCK_WIDTH = this.getWidth() / (this.COL);
//		BLOCK_HEIGHT = this.getHeight() / (this.ROW);

		if (shapeTemp == null) {
			// ������ɷ���;ʵ��Ԥ�����ͷ����������������֮��ķ������ݵĴ�������
			shapeTemp = GamePanel.jp_fun.jp_preview.ranNextShape();
		}
		
		// ������
		eliminateRow();

		// 5�������߽�
		if (isOutOfBounds(shapeTemp)) {
			// �� --;����ԭ��ȥ
			shapeRow--;
			for (int r = 0; r < shapeTemp.length; r++) {
				for (int c = 0; c < shapeTemp[r].length; c++) {
					// ���ñ߽�Ϊ2
					if (shapeTemp[r][c] == 1) {
						blockArr[r + shapeRow][c + shapeCol].setType(2);
					}
				}
			}

			// ��ԭ�к���,�����ڶ��������µķ���
			shapeRow = 0;
			shapeCol = 10;
			// �ٴ���������µķ���
			// shapeTemp = null;
			shapeTemp = GamePanel.jp_fun.jp_preview.ranNextShape();
		}

		// 1����ֵ����С����ֵ��������Ϸ���Ĵ󷽸�
		for (int r = 0; r < shapeTemp.length; r++) {
			for (int c = 0; c < shapeTemp[r].length; c++) {
				if (shapeTemp[r][c] == 1) {
					blockArr[r + shapeRow][c + shapeCol].setType(1);
				}
			}
		}
		
		// 2������
		Image iDraw = setColorBlock();
		for (int r = 0; r < ROW; r++) {
			for (int c = 0; c < COL; c++) {
				// ����������������
//				g.setColor(Color.LIGHT_GRAY);
//				g.drawRect(c * BLOCK_SIZE, r * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);

				if (blockArr[r][c].getType() == 1) {
					// ���Ʒ���
//					g.setColor(Color.GREEN);
//					g.fill3DRect(c * BLOCK_SIZE, r * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, true);					
					
					g.drawImage(iDraw, c * BLOCK_SIZE, r * BLOCK_SIZE, null);
					
				} else if (blockArr[r][c].getType() == 2) {
					// ����ǽ
//					g.setColor(Color.GRAY);
//					g.fill3DRect(c * BLOCK_SIZE, r * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, true);
					
					Image icon_wall = new ImageIcon("resources\\image\\" + skin + "\\6.png").getImage();
					g.drawImage(icon_wall, c * BLOCK_SIZE, r * BLOCK_SIZE, null);
				
				}
			}
		}

		// ��ӡ
		// printBlockArr();

		// 3����������������Ϸ���Ĵ󷽸��е�ԭλ��4*4С���������
		for (int r = 0; r < shapeTemp.length; r++) {
			for (int c = 0; c < shapeTemp[r].length; c++) {
				if (shapeTemp[r][c] == 1) {
					blockArr[r + shapeRow][c + shapeCol].setType(0);
				}
			}
		}
		// 4���� ++ --> �Ѿ��ƶ����˶�ʱ������������������������һ������
		// shapeRow++;
		
		// ���鴥��,��Ϸ����
		gameOver();
		if(getTuColor() == 1) { // ����"GAMEOVER"����
			// g.setColor(Color.MAGENTA);
			// g.setFont(new Font("BrushScriptStd", Font.BOLD&Font.ITALIC, 60));
			// g.drawString("GAME OVER", 45, 300);
			isGameOver = true;
			Image icon_gameover = new ImageIcon("resources\\image\\GAMEOVER.gif").getImage();
			g.drawImage(icon_gameover, 0, 135, null);
		}
		
		// ��Ϸ��ͣ		
		if(getTuColor() == 2) { // ����"PAUSE"����
			Image icon_gameover = new ImageIcon("resources\\image\\PAUSE.gif").getImage();
			g.drawImage(icon_gameover, 0, 135, null);
		}
		// ��Ϸ����
		if(getTuColor() == 3) { // ����"PAUSE"����
			Image icon_gameover = new ImageIcon("resources\\image\\CONTINUE.gif").getImage();
			g.drawImage(icon_gameover, 0, 135, null);
			setTuColor(0); // ����Ϊ0,ʵ��
		}
		
	}
	
	
	/**
	 * ���ܣ��ж��Ƿ񳬳�"�߽�"
	 * 
	 * @param shape
	 * @return
	 */
	boolean isOutOfBounds(int[][] shapeTemp) {
		for (int r = 0; r < shapeTemp.length; r++) {
			for (int c = 0; c < shapeTemp[r].length; c++) {
				// ��4*4��С�����������1�ʹ󷽸��������2�Ƚϣ���Ⱦ���Խ��
				if (shapeTemp[r][c] == 1) {
					if (blockArr[r + shapeRow][c + shapeCol].getType() == 2) {
						return true;
					}
				}
			}
		}
		return false;
	}

	// ��ӡ������Ϸ���Ĵ󷽸������洢��ֵ
	public void printBlockArr() {
		for (int r = 0; r < blockArr.length; r++) {
			for (int c = 0; c < blockArr[r].length; c++) {
				System.out.print(blockArr[r][c].getType() + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}

	/**
	 * ���ܣ����ҿ�����������,���������в���
	 * 
	 * @return
	 */
	public void eliminateRow() {
		for (int r = 0; r < 30; r++) {
			int temp = 0;
			for (int c = 1; c < 21; c++) {
				if (blockArr[r][c].getType() == 2) {
					temp++;
					if (temp == 20) { // 20
						// ����20�Σ���ÿ����������к�
						int num = r;
						System.out.println("DelRow:" + r); // ��ӡ�к�
						System.out.println("Deleteing...");

						// �������ݣ���r-i�и�ֵ��r�У�ʵ�ַ�����������һλ
						for (int rDel = num; rDel > 1; rDel--) {
							for (int cTmep = 1; cTmep < 21; cTmep++) {
								// ע�⣺blockArr���鶨��ΪBlock����,������int����;����ֱ�ӿ�������;
								blockArr[rDel][cTmep].setType(blockArr[rDel - 1][cTmep].getType());
								// ����: blockArr[rDel][cTmep] = blockArr[rDel - 1][cTmep];
							}
						}
						
						// TODO ����������
					    try {
					        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/audio/clear.WAV").getAbsoluteFile());
					        Clip clip = AudioSystem.getClip();
					        clip.open(audioInputStream);
					        clip.start(); // ����
					    } catch(Exception ex) {
					        System.out.println("Error with playing sound.");
					        ex.printStackTrace();
					    }

						// ÿ��һ��,��������1,������10,�ٶȼ�200
						FunctionPanel.lineNum++;
						System.out.println("DelLineNum:" + GamePanel.jp_fun.lineNum);
						
						FunctionPanel.score += 10;
						System.out.println("Score:" + GamePanel.jp_fun.score);
						// �ػ�������
						GamePanel.jp_fun.jp_score.repaint();
						// ���ݷ������ö�Ӧ�������ٶ�
						setScoreDelay(FunctionPanel.score);
					}
				}
			}
			temp = 0;
		}
	}

	/**
	 * ���ܣ����ݷ������ö�Ӧ�������ٶ�
	 * 
	 * @param score
	 * 
	 */
	public void setScoreDelay(int scoreTemp) {
		// �ٶ�Ϊ0ʱ,�����ٶ�
		if (delay <= 0) {
			delay = 1000;
		}
		// �÷ִﵽ100�֣��ٶ�����200
			if(scoreTemp == 100) {
				delay -= 200;
//				scoreTemp = 0;
			}
		// TODO ���һֱѭ�������if����أ�
		System.out.println("Delay:" + delay);
		timer.setDelay(delay);
	}

	/**
	 * ���ܣ���ʼ��Ϸ
	 * 
	 */
	public void startGame() {
		//endGame();

		// ������ʱ��
		timer.start();
		// ��շ�����������
		FunctionPanel.score = 0;
		FunctionPanel.lineNum = 0;
		// ��ʼ��Ϸ
		isStart = true;

	}

	/**
	 * ���ܣ�������Ϸ
	 * 
	 */
	public void endGame() {
		// ������Ϸ
		isStart = false;
		// �رն�ʱ��
		timer.stop();
		timer2.stop();
		// �ص���������
		// setScoreDelay(0);
		delay = 1000;
		FunctionPanel.score = 0;
		FunctionPanel.lineNum = 0;
		// ע��:�����ػ�����;�������������ػ��� -- paintComponent���ֵ�����һ��repaint(),�����ɵݹ��ػ�
		// �����ڽ�����Ϸ����廹���ػ�
//		GameAreaPanel.this.repaint();
	}
	
	/**
	 * ����:ʵ��������ֲ�ɫ����
	 * 
	 */
	public Image setColorBlock() {
		Random ran = new Random();
		int ColorTemp = ran.nextInt(6);
		Image icon_block = null;
		switch (ColorTemp) {
		case 0:
			icon_block = new ImageIcon("resources\\image\\" + skin +"\\0.png").getImage();
			break;
		case 1:
			icon_block = new ImageIcon("resources\\image\\" + skin +"\\1.png").getImage();
			break;
		case 2:
			icon_block = new ImageIcon("resources\\image\\" + skin +"\\2.png").getImage();
			break;
		case 3:
			icon_block = new ImageIcon("resources\\image\\" + skin +"\\3.png").getImage();
			break;
		case 4:
			icon_block = new ImageIcon("resources\\image\\" + skin +"\\4.png").getImage();
			break;
		case 5:
			icon_block = new ImageIcon("resources\\image\\" + skin +"\\5.png").getImage();
			break;
		default:
			break;
		}
		return icon_block;
	}
	
	/**
	 * ����:ʵ�ַ��鴥��������Ϸ
	 * 
	 */
	public void gameOver() {
		for (int cTemp = 1; cTemp < 21; cTemp++) {
			if (blockArr[0][cTemp].getType() == 2) {
				System.out.println("---------GAME OVER--------");
				isGameOver = true;
				if (isGameOver) {
					setTuColor(1);
					 // ��Ϸ��������Ч
					 try {
						 AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/audio/xu.WAV").getAbsoluteFile());
						 Clip clip = AudioSystem.getClip();
						 clip.open(audioInputStream);
						 clip.start(); // ����
					 } catch(Exception ex) {
						 System.out.println("Error with playing sound");
						 ex.printStackTrace();
					 }
					 isGameOver = false;
				}
				endGame();
			}
		}
	}

}
