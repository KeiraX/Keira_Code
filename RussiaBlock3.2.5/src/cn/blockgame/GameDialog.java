package cn.blockgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


public class GameDialog extends JDialog {

	public GameDialog() {
		super();

	}

	/**
	 * ���ܣ�ʵ��"����"�Ի���
	 */
	public void aboutDialog() {
		this.setTitle("����");
		this.setSize(300, 300);
		
		// Сͼ�����
		JPanel iImage = new JPanel();
		// �汾��Ϣ���
		JPanel vInfo = new JPanel();
		// ��Ȩ��Ϣ�ı���
		JTextField jtf_projectname = new JTextField("��Ŀ  ����˹����", 20);
		JTextField jtf_version = new JTextField("�汾  V1.0", 20);
		JTextField jtf_copyright = new JTextField("@��Ȩ����  2016 JX160306", 20);
		JTextField jtf_author= new JTextField("����  Keira", 20);
		
		// �����ı��򲻿ɱ༭
		jtf_projectname.setEditable(false);
		jtf_version.setEditable(false);
		jtf_copyright.setEditable(false);
		jtf_author.setEditable(false);
		
		// ���ı�����ӵ��汾��Ϣ���
		vInfo.add(jtf_projectname);
		vInfo.add(jtf_version);
		vInfo.add(jtf_copyright);
		vInfo.add(jtf_author);
		
		// ������ǩ
		ImageIcon icon = new ImageIcon("resources\\image\\icon.jpg");
		JLabel ilabel = new JLabel("Enjoying��", icon, SwingConstants.LEFT);
		// �ѱ�ǩ��ӵ�Сͼ�����
		iImage.add(ilabel);		
		
		// ��Сͼ�������ӵ����ڶԻ���,�����ñ���λ��
		this.add(iImage, BorderLayout.NORTH);
		// �Ѱ汾��Ϣ�����ӵ����ڶԻ���,����������λ��
		this.add(vInfo, BorderLayout.CENTER);
		
		//����һֱ�����ϲ�
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	/**
	 * ���ܣ�ʵ��"�Զ���"�Ի���
	 */
	public void CustomDialog() {
		this.setTitle("�Զ�����Ϸ����");
		this.setSize(500, 300);
		// ���öԻ��򲼾�Ϊ��
		this.setLayout(null);
		
		// ������ǩ,������λ�úʹ�С
		JLabel label = new JLabel("�����ٶ�");
 		label.setBounds(50, 50, 60, 30);
		JLabel label2 = new JLabel("�ٶ�20-100���μӿ�");
		label2.setBounds(335, 50, 130, 30);
		JLabel label3 = new JLabel("����Ƥ��");
		label3.setBounds(50, 105, 60, 30);		
		// ���ñ�ǩ�߿���ɫ
//		label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
//		label2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
//		label3.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		label.setBorder(new LineBorder(Color.BLACK, 1));
		label2.setBorder(new LineBorder(Color.BLACK, 1));
		label3.setBorder(new LineBorder(Color.BLACK, 1));
		// �ѱ�ǩ��ӵ��Ի���
		this.add(label);
		this.add(label2);
		this.add(label3);
		
		//����������,����������;��Χ��20-100,��ʼֵΪ20
		JSlider slider = new JSlider(20, 100, 20); 
		slider.setBounds(120, 60, 200, 50);
		 //���û������ͣ�ڿ̶ȴ�  
        slider.setSnapToTicks(true); 
		 //���û��ƿ̶�  
        slider.setPaintTicks(true);  
        //���������ο̶ȵļ��  
        slider.setMajorTickSpacing(20);   
        //���û��ƿ̶ȱ�ǩ��Ĭ�ϻ�����ֵ�̶ȱ�ǩ  
        slider.setPaintLabels(true);  
        //�ѻ�������ӵ��Ի���
        this.add(slider);  
		
		// ������ͬ�ȼ���3����ѡ��ť,��λ�úʹ�С;Ĭ��ѡ������Ƥ��
		JRadioButton lButton = new JRadioButton("����", true);
		lButton.setBounds(150, 110, 70, 20);
		JRadioButton zButton = new JRadioButton("����", false);
		zButton.setBounds(250, 110, 70, 20);
		JRadioButton xButton = new JRadioButton("�Ų�", false);
		xButton.setBounds(350, 110, 70, 20);
		//��3��RadioButtonͬһʱ��ֻ��һ��RadioButton��״̬����Ϊ"on";��ʵ�ֵ�ѡ��ť
		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(lButton);
		bgroup.add(zButton);
		bgroup.add(xButton);
		// �Ѱ�ť��ӵ��Ի���
		this.add(lButton);
		this.add(zButton);
		this.add(xButton);
		
		// ����2����ѡ��ť,��λ�úʹ�С
		JCheckBox upBox = new JCheckBox("�����Ƿ��Զ�����");
		upBox.setBounds(50, 160, 150, 20);
		JCheckBox playBox = new JCheckBox("��Ϸ�����Ƿ񲥷�����");
		playBox.setBounds(220, 160, 170, 20);
		// �Ѹ�ѡ��ť��ӵ��Ի���
		this.add(upBox);
		this.add(playBox);
		
		// ����ȷ����ȡ����2����ť,��λ�úʹ�С
		JButton confirm = new JButton("ȷ��(O)");
		confirm.setMnemonic('O');
		confirm.setBounds(250, 200, 85, 30);
		JButton cancel = new JButton("ȡ��(C)");
		cancel.setMnemonic('C');
		cancel.setBounds(350, 200, 85, 30);
		// �Ѱ�ť��ӵ��Ի���
		this.add(confirm);
		this.add(cancel);
		
		//����һֱ�����ϲ�
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	
	    // ���"ȷ��"��ť����
	    confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// ���û�������ֵ
				int temp = slider.getValue();
				switch(temp) {
					case 20:
						GamePanel.jp_game.timer.setDelay(1000);
						break;
					case 40:
						GamePanel.jp_game.timer.setDelay(750);
						break;
					case 60:
						GamePanel.jp_game.timer.setDelay(500);
						break;
					case 80:
						GamePanel.jp_game.timer.setDelay(200);
						break;
					case 100:
						GamePanel.jp_game.timer.setDelay(50);
						break;
					default:
						break;						
				}
				
				// TODO ���ñ������ֺ���Ϸ��Ч
				if (playBox.isSelected()) { // ���������
					try {
						AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/audio/background.WAV").getAbsoluteFile());
						Clip clip = AudioSystem.getClip();
						clip.open(audioInputStream);
						clip.start(); // ����
						clip.loop(Clip.LOOP_CONTINUOUSLY); // ѭ��	
						// BUG ???
//						for (int cTemp = 1; cTemp < 21; cTemp++) {
//							if (GamePanel.jp_game.blockArr[0][cTemp].getType() == 2) {
//								System.out.println("------------");
//								clip.stop();
//							}
//						}
					} catch (Exception ex) {
						System.out.println("Error with playing sound.");
						ex.printStackTrace();
					}

//					AudioStream backMusic;
//					AudioData backData;
//					ContinuousAudioDataStream loop = null;
//					try {
//						backMusic = new AudioStream(new FileInputStream("resources\\audio\\58.WAV"));
//						backData = backMusic.getData();
//						loop = new ContinuousAudioDataStream(backData);						
//					} catch (FileNotFoundException e1) {
//						e1.printStackTrace();
//						System.out.println("File Not Found");
//					} catch (IOException e1) {
//						System.out.println("Something Is Error");
//						e1.printStackTrace();
//					}
//					AudioPlayer.player.start(loop);
				}
				
				// ���÷���Ƥ��
				if(lButton.isSelected()) {
					GamePanel.jp_game.skin = "c1";
				}
				if(zButton.isSelected()) {
					GamePanel.jp_game.skin = "c2";
				}
				if(xButton.isSelected()) {
					GamePanel.jp_game.skin = "c3";
				}

				// TODO ���÷����Զ�����
				if(upBox.isSelected()) { // ���������
					GamePanel.jp_game.timer2.start();
				}
				// �رմ˶Ի���
				GameDialog.this.dispose();
			}
		});
	    
	    // ���"ȡ��"��ť����
	    cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// �رմ˶Ի���
				GameDialog.this.dispose();
			}
		});
	}
}
