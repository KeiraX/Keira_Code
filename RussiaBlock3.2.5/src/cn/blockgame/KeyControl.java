package cn.blockgame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * ���ܣ�ʵ�ּ��̰��¿��ƶ�Ӧ�����¼�
 * 
 * @author Keira
 *
 */
public class KeyControl extends KeyAdapter {
	private GameAreaPanel jp_gameArea;
	
	public KeyControl(GameAreaPanel jp_gameArea) {
		super();
		// ��������Ϸ��������嵱�ɲ���������
		this.jp_gameArea = jp_gameArea;
	}

	/**
	 * ���ܣ����̰����¼�
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		
		// �ж��Ƿ���"����"
		if(!jp_gameArea.isStart) { // ��δ��ʼ��Ϸ�����̼�������Ϊ������
			return;
		}
		
		//��ʼ��Ϸ,�������̼���
		int keyCode = e.getKeyCode();
		// �������ϼ�ͷ
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
				GameAreaPanel.shapeTemp = BlockModel.L_SHAPE[PreviewJPanel.curState]; // �������״				
				
				temp = BlockModel.L_SHAPE[PreviewJPanel.curState];
				if (jp_gameArea.isOutOfBounds(temp)) { // �����߽�
					GameAreaPanel.shapeTemp = BlockModel.L_SHAPE[old]; // �ص��ɵ���ת״̬
					PreviewJPanel.curState=old; // ��ֹ"L"�͵���ת״̬����
				}
				// �ػ�
				jp_gameArea.repaint();
				break;
			case 1:
				GameAreaPanel.shapeTemp = BlockModel.J_SHAPE[PreviewJPanel.curState];
				
				temp = BlockModel.J_SHAPE[PreviewJPanel.curState];
				if (jp_gameArea.isOutOfBounds(temp)) { // �����߽�
					GameAreaPanel.shapeTemp = BlockModel.J_SHAPE[old]; // �ص��ɵ���ת״̬
					PreviewJPanel.curState=old; // ��ֹ"J"�͵���ת״̬����
				}
				jp_gameArea.repaint();
				break;
			case 2:
				GameAreaPanel.shapeTemp = BlockModel.X_SHAPE[PreviewJPanel.curState];
				
				temp = BlockModel.X_SHAPE[PreviewJPanel.curState];
				if (jp_gameArea.isOutOfBounds(temp)) { // �����߽�
					GameAreaPanel.shapeTemp = BlockModel.X_SHAPE[old]; // �ص��ɵ���ת״̬
				}
				jp_gameArea.repaint();
				break;
			case 3:
				GameAreaPanel.shapeTemp = BlockModel.I_SHAPE[PreviewJPanel.curState];
				
				temp = BlockModel.I_SHAPE[PreviewJPanel.curState];
				if (jp_gameArea.isOutOfBounds(temp)) { // �����߽�
					GameAreaPanel.shapeTemp = BlockModel.I_SHAPE[old]; // �ص��ɵ���ת״̬
				}
				jp_gameArea.repaint();
				break;
			case 4:
				GameAreaPanel.shapeTemp = BlockModel.T_SHAPE[PreviewJPanel.curState];
				
				temp = BlockModel.T_SHAPE[PreviewJPanel.curState];
				if (jp_gameArea.isOutOfBounds(temp)) { // �����߽�
					GameAreaPanel.shapeTemp = BlockModel.T_SHAPE[old]; // �ص��ɵ���ת״̬
					PreviewJPanel.curState=old; // ��ֹ"T"�͵���ת״̬����
				}
				jp_gameArea.repaint();
				break;
			case 5:
				GameAreaPanel.shapeTemp = BlockModel.S_SHAPE[PreviewJPanel.curState];
				
				temp = BlockModel.S_SHAPE[PreviewJPanel.curState];
				if (jp_gameArea.isOutOfBounds(temp)) { // �����߽�
					GameAreaPanel.shapeTemp = BlockModel.S_SHAPE[old]; // �ص��ɵ���ת״̬
				}
				jp_gameArea.repaint();
				break;
			case 6:
				GameAreaPanel.shapeTemp = BlockModel.Z_SHAPE[PreviewJPanel.curState];
				
				temp = BlockModel.Z_SHAPE[PreviewJPanel.curState];
				if (jp_gameArea.isOutOfBounds(temp)) { // �����߽�
					GameAreaPanel.shapeTemp = BlockModel.Z_SHAPE[old]; // �ص��ɵ���ת״̬
				}
				jp_gameArea.repaint();
				break;
			default:
				break;
			}
		}

		// // ��ӡ
		// for (int r = 0; r < GameAreaPanel.shapeTemp.length; r++) {
		// for (int c = 0; c < GameAreaPanel.shapeTemp[r].length; c++) {
		// System.out.print(GameAreaPanel.shapeTemp[r][c] + "");
		// }
		// System.out.println();
		// }

		// �������¼�ͷ ���+�洢 ���������ٶȳ�����ʱ�����ٶ�!!
		if (keyCode == KeyEvent.VK_DOWN) {
			System.out.println("DOWN");
			// �� ++,�в���
			GameAreaPanel.shapeRow++;
			// �жϱ߽緶Χ
			if (jp_gameArea.isOutOfBounds(GameAreaPanel.shapeTemp)) {
				// ��ԭ��
				GameAreaPanel.shapeRow--;
			} else {
				// �ػ�:�������������ݱ��Ե������
				jp_gameArea.repaint();
			}
			// �����ƶ���������
			playVoice();
		}
		// ���������ͷ
		if (keyCode == KeyEvent.VK_LEFT) {
			System.out.println("LEFT");
			// �в��䣬�м�һ
			GameAreaPanel.shapeCol--;

			// �жϱ߽緶Χ
			if (jp_gameArea.isOutOfBounds(GameAreaPanel.shapeTemp)) {
				// ��ԭ��
				GameAreaPanel.shapeCol++;
			} else {
				// �ػ�
				jp_gameArea.repaint();
			}
			// �����ƶ���������
			playVoice();
		}
		// �������Ҽ�ͷ
		if (keyCode == KeyEvent.VK_RIGHT) {
			System.out.println("RIGHT");
			// �в��䣬�м�һ
			GameAreaPanel.shapeCol++;
			// �жϱ߽緶Χ
			if (jp_gameArea.isOutOfBounds(GameAreaPanel.shapeTemp)) {
				// ��ԭ��
				GameAreaPanel.shapeCol--;
			} else {
				// �ػ�
				jp_gameArea.repaint();
			}
			// �����ƶ���������
			playVoice();
		}
		// ���̿ո�� -- ��ͣ
		if(keyCode == KeyEvent.VK_SPACE) {
			System.out.println("��ͣ");
			// TODO
			//jp_gameArea.isStart = false;
			GamePanel.jp_game.setTuColor(2);
			jp_gameArea.timer.stop();
			GamePanel.jp_game.repaint();
		}
		// ����"C"�� -- ����
		if(keyCode == KeyEvent.VK_ENTER) {
			System.out.println("����");
			// TODO
			//jp_gameArea.isStart = true;
			GamePanel.jp_game.setTuColor(3);
			jp_gameArea.timer.start();
		}
	}
	/**
	 * ����:ʵ���ƶ�����ʱ����������
	 * 
	 */
   public void playVoice() {
	   try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/audio/down.WAV").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start(); // ����
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
   }
	
}
