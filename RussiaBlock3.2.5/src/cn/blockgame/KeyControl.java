package cn.blockgame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * 功能：实现键盘按下控制对应监听事件
 * 
 * @author Keira
 *
 */
public class KeyControl extends KeyAdapter {
	private GameAreaPanel jp_gameArea;
	
	public KeyControl(GameAreaPanel jp_gameArea) {
		super();
		// 将整个游戏下落区面板当成参数传进来
		this.jp_gameArea = jp_gameArea;
	}

	/**
	 * 功能：键盘按下事件
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		
		// 判断是否按下"开局"
		if(!jp_gameArea.isStart) { // 尚未开始游戏，键盘监听设置为不可用
			return;
		}
		
		//开始游戏,开启键盘监听
		int keyCode = e.getKeyCode();
		// 键盘向上箭头
		if (keyCode == KeyEvent.VK_UP) {
			System.out.println("UP");
			int old;
			int[][] temp;
			
			old = PreviewJPanel.curState;			
			if (PreviewJPanel.curState == 3) {
				PreviewJPanel.curState = 0;
			} else {
				PreviewJPanel.curState++;
			}
			
			switch (PreviewJPanel.curType) {
			case 0:
				GameAreaPanel.shapeTemp = BlockModel.L_SHAPE[PreviewJPanel.curState]; // 方块的形状				
				
				temp = BlockModel.L_SHAPE[PreviewJPanel.curState];
				if (jp_gameArea.isOutOfBounds(temp)) { // 超出边界
					GameAreaPanel.shapeTemp = BlockModel.L_SHAPE[old]; // 回到旧的旋转状态
					PreviewJPanel.curState=old; // 防止"L"型的旋转状态跳格
				}
				// 重绘
				jp_gameArea.repaint();
				break;
			case 1:
				GameAreaPanel.shapeTemp = BlockModel.J_SHAPE[PreviewJPanel.curState];
				
				temp = BlockModel.J_SHAPE[PreviewJPanel.curState];
				if (jp_gameArea.isOutOfBounds(temp)) { // 超出边界
					GameAreaPanel.shapeTemp = BlockModel.J_SHAPE[old]; // 回到旧的旋转状态
					PreviewJPanel.curState=old; // 防止"J"型的旋转状态跳格
				}
				jp_gameArea.repaint();
				break;
			case 2:
				GameAreaPanel.shapeTemp = BlockModel.X_SHAPE[PreviewJPanel.curState];
				
				temp = BlockModel.X_SHAPE[PreviewJPanel.curState];
				if (jp_gameArea.isOutOfBounds(temp)) { // 超出边界
					GameAreaPanel.shapeTemp = BlockModel.X_SHAPE[old]; // 回到旧的旋转状态
				}
				jp_gameArea.repaint();
				break;
			case 3:
				GameAreaPanel.shapeTemp = BlockModel.I_SHAPE[PreviewJPanel.curState];
				
				temp = BlockModel.I_SHAPE[PreviewJPanel.curState];
				if (jp_gameArea.isOutOfBounds(temp)) { // 超出边界
					GameAreaPanel.shapeTemp = BlockModel.I_SHAPE[old]; // 回到旧的旋转状态
				}
				jp_gameArea.repaint();
				break;
			case 4:
				GameAreaPanel.shapeTemp = BlockModel.T_SHAPE[PreviewJPanel.curState];
				
				temp = BlockModel.T_SHAPE[PreviewJPanel.curState];
				if (jp_gameArea.isOutOfBounds(temp)) { // 超出边界
					GameAreaPanel.shapeTemp = BlockModel.T_SHAPE[old]; // 回到旧的旋转状态
					PreviewJPanel.curState=old; // 防止"T"型的旋转状态跳格
				}
				jp_gameArea.repaint();
				break;
			case 5:
				GameAreaPanel.shapeTemp = BlockModel.S_SHAPE[PreviewJPanel.curState];
				
				temp = BlockModel.S_SHAPE[PreviewJPanel.curState];
				if (jp_gameArea.isOutOfBounds(temp)) { // 超出边界
					GameAreaPanel.shapeTemp = BlockModel.S_SHAPE[old]; // 回到旧的旋转状态
				}
				jp_gameArea.repaint();
				break;
			case 6:
				GameAreaPanel.shapeTemp = BlockModel.Z_SHAPE[PreviewJPanel.curState];
				
				temp = BlockModel.Z_SHAPE[PreviewJPanel.curState];
				if (jp_gameArea.isOutOfBounds(temp)) { // 超出边界
					GameAreaPanel.shapeTemp = BlockModel.Z_SHAPE[old]; // 回到旧的旋转状态
				}
				jp_gameArea.repaint();
				break;
			default:
				break;
			}
		}

		// // 打印
		// for (int r = 0; r < GameAreaPanel.shapeTemp.length; r++) {
		// for (int c = 0; c < GameAreaPanel.shapeTemp[r].length; c++) {
		// System.out.print(GameAreaPanel.shapeTemp[r][c] + "");
		// }
		// System.out.println();
		// }

		// 键盘向下箭头 清除+存储 快速下落速度超出定时器的速度!!
		if (keyCode == KeyEvent.VK_DOWN) {
			System.out.println("DOWN");
			// 行 ++,列不变
			GameAreaPanel.shapeRow++;
			// 判断边界范围
			if (jp_gameArea.isOutOfBounds(GameAreaPanel.shapeTemp)) {
				// 还原行
				GameAreaPanel.shapeRow--;
			} else {
				// 重绘:解决方块快速下陷被吃掉的情况
				jp_gameArea.repaint();
			}
			// 方块移动播放声音
			playVoice();
		}
		// 键盘向左箭头
		if (keyCode == KeyEvent.VK_LEFT) {
			System.out.println("LEFT");
			// 行不变，列减一
			GameAreaPanel.shapeCol--;

			// 判断边界范围
			if (jp_gameArea.isOutOfBounds(GameAreaPanel.shapeTemp)) {
				// 还原列
				GameAreaPanel.shapeCol++;
			} else {
				// 重绘
				jp_gameArea.repaint();
			}
			// 方块移动播放声音
			playVoice();
		}
		// 键盘向右箭头
		if (keyCode == KeyEvent.VK_RIGHT) {
			System.out.println("RIGHT");
			// 行不变，列减一
			GameAreaPanel.shapeCol++;
			// 判断边界范围
			if (jp_gameArea.isOutOfBounds(GameAreaPanel.shapeTemp)) {
				// 还原列
				GameAreaPanel.shapeCol--;
			} else {
				// 重绘
				jp_gameArea.repaint();
			}
			// 方块移动播放声音
			playVoice();
		}
		// 键盘空格键 -- 暂停
		if(keyCode == KeyEvent.VK_SPACE) {
			System.out.println("暂停");
			// TODO
			//jp_gameArea.isStart = false;
			GamePanel.jp_game.setTuColor(2);
			jp_gameArea.timer.stop();
			GamePanel.jp_game.repaint();
		}
		// 键盘"C"键 -- 继续
		if(keyCode == KeyEvent.VK_ENTER) {
			System.out.println("继续");
			// TODO
			//jp_gameArea.isStart = true;
			GamePanel.jp_game.setTuColor(3);
			jp_gameArea.timer.start();
		}
	}
	/**
	 * 功能:实现移动方块时的声音控制
	 * 
	 */
   public void playVoice() {
	   try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/audio/down.WAV").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start(); // 播放
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
   }
	
}
