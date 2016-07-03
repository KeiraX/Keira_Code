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
 * 功能：游戏的主区域面板,实现游戏的逻辑算法,包括方块的下落、旋转、平移、消行等
 * 
 * @author Keira
 *
 */
public class GameAreaPanel extends JPanel {
	// 一个方块的大小
	public static final int BLOCK_SIZE = 20;
	public static int BLOCK_WIDTH = 20;
	public static int BLOCK_HEIGHT = 20;
	// 方块下落区的行和列
	public static final int ROW = 30 + 1; // 多加一行边界(底部)
	public static final int COL = 20 + 2; // 多加两列边界(左右两边)
	// 下落区的宽、高
	public static final int GAMEAREA_WIDTH = COL * BLOCK_SIZE;
	public static final int GAMEAREA_HEIGHT = ROW * BLOCK_SIZE;
	//
	public static int shapeRow = 0;
	public static int shapeCol = 10;
	// 方块下落区的大方格数组
	Block[][] blockArr = new Block[ROW][COL];
	// 方块实际活动区域,即4*4的小方格
	static int[][] shapeTemp = null;
	// 定时的变量,初始值为1000
	int delay = 1000;
	// 游戏开始与否的标志
	public boolean isStart = false;
	// 游戏是否结束
	public boolean isGameOver = false;
	// 方块皮肤变量
	public String skin = "c1";
	// 壁纸皮肤的变量
	public String wallPaper = "0";
	// 设置颜色图片的临时变量
	private int tuColor = 0;
	int i = 0;
	int j = 0;
	
	public int getTuColor() {
		return tuColor;
	}
	public void setTuColor(int tuColor) {
		this.tuColor = tuColor;
	}
	
	// 定时下落一格
	Timer timer = new Timer(delay, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// 4、行 ++
			shapeRow++;
			
			// 设置变量实现每定时下落一定行数就换一次背景图片			
			i ++;
			while(i == 20) {
				wallPaper = "" + j;
				j ++;
				if(j == 11) {
					j = 0;
				}				
				i = 0;
			}
			
			// 先重绘功能区的3个画布
			GamePanel.jp_fun.jp_preview.repaint();
			GamePanel.jp_fun.jp_operation.repaint();
			GamePanel.jp_fun.jp_score.repaint();
			// 重绘整个下落区画布;调用repaint方法，然后系统回去调用重写的paintComponent方法
			GameAreaPanel.this.repaint();
		}
	});
	
	// TODO 绘制底层开始自动上涨的方块，定时上涨一格
	Timer timer2 = new Timer(750, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			Random ranTemp = new Random();
			int[] iTemp = new int[5]; 
			gameOver();
			if(isStart) { // 开始游戏
				// 随机产生一些空方块
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
						// 拷贝数据：将r+i行赋值给r行；实现方块整体上涨一位
						blockArr[r][c].setType(blockArr[r + 1][c].getType());
					}
				}
			}
			// 重绘整个下落区画布
			GameAreaPanel.this.repaint();
		}
	});
	

	public GameAreaPanel() {
		// 给大方格数组初始化
		for (int r = 0; r < ROW; r++) {
			for (int c = 0; c < COL; c++) {
				blockArr[r][c] = new Block();
				// 设置底部、左边、右边的最外围都是边界为2的方块墙
				if (r == ROW - 1 || c == 0 || c == COL - 1) {
					blockArr[r][c].setType(2);
				}
			}
		}

		// 启动定时器
		//timer.start();
		
		// 给下落区获取焦点
		GameAreaPanel.this.setFocusable(true);
		// 给下落区添加键盘监听事件
		GameAreaPanel.this.addKeyListener(new KeyControl(this));

		// 设置下落区大小
		this.setPreferredSize(new Dimension(GAMEAREA_WIDTH, GAMEAREA_HEIGHT));
		// 设置面板透明化
		this.setOpaque(false);
		// 设置面板边框及颜色
		//this.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
	}

	/**
	 * 功能：重写paintComponent方法
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// 添加背景图片
//		Image gameBack = Toolkit.getDefaultToolkit().getImage("resources\\image\\star.jpg");
//		g.drawImage(gameBack,0,0,this);

		// 窗口调整时,方块会按比例放大
//		BLOCK_WIDTH = this.getWidth() / (this.COL);
//		BLOCK_HEIGHT = this.getHeight() / (this.ROW);

		if (shapeTemp == null) {
			// 随机生成方块;实现预览区和方块下落区两个面板之间的方块数据的传递问题
			shapeTemp = GamePanel.jp_fun.jp_preview.ranNextShape();
		}
		
		// 消除行
		eliminateRow();

		// 5、超出边界
		if (isOutOfBounds(shapeTemp)) {
			// 行 --;即还原回去
			shapeRow--;
			for (int r = 0; r < shapeTemp.length; r++) {
				for (int c = 0; c < shapeTemp[r].length; c++) {
					// 设置边界为2
					if (shapeTemp[r][c] == 1) {
						blockArr[r + shapeRow][c + shapeCol].setType(2);
					}
				}
			}

			// 还原行和列,重新在顶部生成新的方块
			shapeRow = 0;
			shapeCol = 10;
			// 再次随机生成新的方块
			// shapeTemp = null;
			shapeTemp = GamePanel.jp_fun.jp_preview.ranNextShape();
		}

		// 1、赋值：将小方格赋值给整个游戏区的大方格
		for (int r = 0; r < shapeTemp.length; r++) {
			for (int c = 0; c < shapeTemp[r].length; c++) {
				if (shapeTemp[r][c] == 1) {
					blockArr[r + shapeRow][c + shapeCol].setType(1);
				}
			}
		}
		
		// 2、绘制
		Image iDraw = setColorBlock();
		for (int r = 0; r < ROW; r++) {
			for (int c = 0; c < COL; c++) {
				// 给画布绘制网格线
//				g.setColor(Color.LIGHT_GRAY);
//				g.drawRect(c * BLOCK_SIZE, r * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);

				if (blockArr[r][c].getType() == 1) {
					// 绘制方块
//					g.setColor(Color.GREEN);
//					g.fill3DRect(c * BLOCK_SIZE, r * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, true);					
					
					g.drawImage(iDraw, c * BLOCK_SIZE, r * BLOCK_SIZE, null);
					
				} else if (blockArr[r][c].getType() == 2) {
					// 绘制墙
//					g.setColor(Color.GRAY);
//					g.fill3DRect(c * BLOCK_SIZE, r * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, true);
					
					Image icon_wall = new ImageIcon("resources\\image\\" + skin + "\\6.png").getImage();
					g.drawImage(icon_wall, c * BLOCK_SIZE, r * BLOCK_SIZE, null);
				
				}
			}
		}

		// 打印
		// printBlockArr();

		// 3、清除：清除整个游戏区的大方格中的原位置4*4小方格的数据
		for (int r = 0; r < shapeTemp.length; r++) {
			for (int c = 0; c < shapeTemp[r].length; c++) {
				if (shapeTemp[r][c] == 1) {
					blockArr[r + shapeRow][c + shapeCol].setType(0);
				}
			}
		}
		// 4、行 ++ --> 已经移动到了定时器匿名类里，解决方块快速下落的一下问题
		// shapeRow++;
		
		// 方块触顶,游戏结束
		gameOver();
		if(getTuColor() == 1) { // 绘制"GAMEOVER"字样
			// g.setColor(Color.MAGENTA);
			// g.setFont(new Font("BrushScriptStd", Font.BOLD&Font.ITALIC, 60));
			// g.drawString("GAME OVER", 45, 300);
			isGameOver = true;
			Image icon_gameover = new ImageIcon("resources\\image\\GAMEOVER.gif").getImage();
			g.drawImage(icon_gameover, 0, 135, null);
		}
		
		// 游戏暂停		
		if(getTuColor() == 2) { // 绘制"PAUSE"字样
			Image icon_gameover = new ImageIcon("resources\\image\\PAUSE.gif").getImage();
			g.drawImage(icon_gameover, 0, 135, null);
		}
		// 游戏继续
		if(getTuColor() == 3) { // 绘制"PAUSE"字样
			Image icon_gameover = new ImageIcon("resources\\image\\CONTINUE.gif").getImage();
			g.drawImage(icon_gameover, 0, 135, null);
			setTuColor(0); // 设置为0,实现
		}
		
	}
	
	
	/**
	 * 功能：判断是否超出"边界"
	 * 
	 * @param shape
	 * @return
	 */
	boolean isOutOfBounds(int[][] shapeTemp) {
		for (int r = 0; r < shapeTemp.length; r++) {
			for (int c = 0; c < shapeTemp[r].length; c++) {
				// 拿4*4的小方格数组里的1和大方格数组里的2比较，相等就是越界
				if (shapeTemp[r][c] == 1) {
					if (blockArr[r + shapeRow][c + shapeCol].getType() == 2) {
						return true;
					}
				}
			}
		}
		return false;
	}

	// 打印整个游戏区的大方格的数组存储的值
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
	 * 功能：查找可以消除的行,并进行消行操作
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
						// 加满20次，获得可以消除的行号
						int num = r;
						System.out.println("DelRow:" + r); // 打印行号
						System.out.println("Deleteing...");

						// 拷贝数据：将r-i行赋值给r行；实现方块整体下移一位
						for (int rDel = num; rDel > 1; rDel--) {
							for (int cTmep = 1; cTmep < 21; cTmep++) {
								// 注意：blockArr数组定义为Block类型,而不是int类型;不能直接拷贝复制;
								blockArr[rDel][cTmep].setType(blockArr[rDel - 1][cTmep].getType());
								// 错误: blockArr[rDel][cTmep] = blockArr[rDel - 1][cTmep];
							}
						}
						
						// TODO 消除的声音
					    try {
					        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/audio/clear.WAV").getAbsoluteFile());
					        Clip clip = AudioSystem.getClip();
					        clip.open(audioInputStream);
					        clip.start(); // 播放
					    } catch(Exception ex) {
					        System.out.println("Error with playing sound.");
					        ex.printStackTrace();
					    }

						// 每消一行,消行数加1,分数加10,速度加200
						FunctionPanel.lineNum++;
						System.out.println("DelLineNum:" + GamePanel.jp_fun.lineNum);
						
						FunctionPanel.score += 10;
						System.out.println("Score:" + GamePanel.jp_fun.score);
						// 重绘分数面板
						GamePanel.jp_fun.jp_score.repaint();
						// 根据分数设置对应的下落速度
						setScoreDelay(FunctionPanel.score);
					}
				}
			}
			temp = 0;
		}
	}

	/**
	 * 功能：根据分数设置对应的下落速度
	 * 
	 * @param score
	 * 
	 */
	public void setScoreDelay(int scoreTemp) {
		// 速度为0时,重置速度
		if (delay <= 0) {
			delay = 1000;
		}
		// 得分达到100分，速度提升200
			if(scoreTemp == 100) {
				delay -= 200;
//				scoreTemp = 0;
			}
		// TODO 如何一直循环上面的if语句呢？
		System.out.println("Delay:" + delay);
		timer.setDelay(delay);
	}

	/**
	 * 功能：开始游戏
	 * 
	 */
	public void startGame() {
		//endGame();

		// 启动定时器
		timer.start();
		// 清空分数和消行数
		FunctionPanel.score = 0;
		FunctionPanel.lineNum = 0;
		// 开始游戏
		isStart = true;

	}

	/**
	 * 功能：结束游戏
	 * 
	 */
	public void endGame() {
		// 结束游戏
		isStart = false;
		// 关闭定时器
		timer.stop();
		timer2.stop();
		// 回到初级设置
		// setScoreDelay(0);
		delay = 1000;
		FunctionPanel.score = 0;
		FunctionPanel.lineNum = 0;
		// 注意:不能重绘两次;在下落区面板的重绘中 -- paintComponent中又调用了一次repaint(),结果造成递归重绘
		// 这样在结束游戏后面板还在重绘
//		GameAreaPanel.this.repaint();
	}
	
	/**
	 * 功能:实现随机出现彩色方块
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
	 * 功能:实现方块触顶结束游戏
	 * 
	 */
	public void gameOver() {
		for (int cTemp = 1; cTemp < 21; cTemp++) {
			if (blockArr[0][cTemp].getType() == 2) {
				System.out.println("---------GAME OVER--------");
				isGameOver = true;
				if (isGameOver) {
					setTuColor(1);
					 // 游戏结束的音效
					 try {
						 AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/audio/xu.WAV").getAbsoluteFile());
						 Clip clip = AudioSystem.getClip();
						 clip.open(audioInputStream);
						 clip.start(); // 播放
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
