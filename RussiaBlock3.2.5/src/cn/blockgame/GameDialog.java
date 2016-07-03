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
	 * 功能：实现"关于"对话框
	 */
	public void aboutDialog() {
		this.setTitle("关于");
		this.setSize(300, 300);
		
		// 小图标面板
		JPanel iImage = new JPanel();
		// 版本信息面板
		JPanel vInfo = new JPanel();
		// 版权信息文本框
		JTextField jtf_projectname = new JTextField("项目  俄罗斯方块", 20);
		JTextField jtf_version = new JTextField("版本  V1.0", 20);
		JTextField jtf_copyright = new JTextField("@版权所有  2016 JX160306", 20);
		JTextField jtf_author= new JTextField("作者  Keira", 20);
		
		// 设置文本框不可编辑
		jtf_projectname.setEditable(false);
		jtf_version.setEditable(false);
		jtf_copyright.setEditable(false);
		jtf_author.setEditable(false);
		
		// 把文本框添加到版本信息面板
		vInfo.add(jtf_projectname);
		vInfo.add(jtf_version);
		vInfo.add(jtf_copyright);
		vInfo.add(jtf_author);
		
		// 创建标签
		ImageIcon icon = new ImageIcon("resources\\image\\icon.jpg");
		JLabel ilabel = new JLabel("Enjoying！", icon, SwingConstants.LEFT);
		// 把标签添加到小图标面板
		iImage.add(ilabel);		
		
		// 把小图标面板添加到关于对话框,并设置北部位置
		this.add(iImage, BorderLayout.NORTH);
		// 把版本信息面板添加到关于对话框,并设置中央位置
		this.add(vInfo, BorderLayout.CENTER);
		
		//设置一直在最上层
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	/**
	 * 功能：实现"自定义"对话框
	 */
	public void CustomDialog() {
		this.setTitle("自定义游戏规则");
		this.setSize(500, 300);
		// 设置对话框布局为空
		this.setLayout(null);
		
		// 创建标签,并设置位置和大小
		JLabel label = new JLabel("下落速度");
 		label.setBounds(50, 50, 60, 30);
		JLabel label2 = new JLabel("速度20-100依次加快");
		label2.setBounds(335, 50, 130, 30);
		JLabel label3 = new JLabel("方块皮肤");
		label3.setBounds(50, 105, 60, 30);		
		// 设置标签边框及颜色
//		label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
//		label2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
//		label3.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		label.setBorder(new LineBorder(Color.BLACK, 1));
		label2.setBorder(new LineBorder(Color.BLACK, 1));
		label3.setBorder(new LineBorder(Color.BLACK, 1));
		// 把标签添加到对话框
		this.add(label);
		this.add(label2);
		this.add(label3);
		
		//创建滑动条,及基本属性;范围在20-100,初始值为20
		JSlider slider = new JSlider(20, 100, 20); 
		slider.setBounds(120, 60, 200, 50);
		 //设置滑块必须停在刻度处  
        slider.setSnapToTicks(true); 
		 //设置绘制刻度  
        slider.setPaintTicks(true);  
        //设置主、次刻度的间距  
        slider.setMajorTickSpacing(20);   
        //设置绘制刻度标签，默认绘制数值刻度标签  
        slider.setPaintLabels(true);  
        //把滑动条添加到对话框
        this.add(slider);  
		
		// 创建不同等级的3个单选按钮,及位置和大小;默认选中立体皮肤
		JRadioButton lButton = new JRadioButton("立体", true);
		lButton.setBounds(150, 110, 70, 20);
		JRadioButton zButton = new JRadioButton("质朴", false);
		zButton.setBounds(250, 110, 70, 20);
		JRadioButton xButton = new JRadioButton("炫彩", false);
		xButton.setBounds(350, 110, 70, 20);
		//此3个RadioButton同一时间只有一个RadioButton的状态可以为"on";即实现单选按钮
		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(lButton);
		bgroup.add(zButton);
		bgroup.add(xButton);
		// 把按钮添加到对话框
		this.add(lButton);
		this.add(zButton);
		this.add(xButton);
		
		// 创建2个复选框按钮,及位置和大小
		JCheckBox upBox = new JCheckBox("方块是否自动上涨");
		upBox.setBounds(50, 160, 150, 20);
		JCheckBox playBox = new JCheckBox("游戏过程是否播放音乐");
		playBox.setBounds(220, 160, 170, 20);
		// 把复选框按钮添加到对话框
		this.add(upBox);
		this.add(playBox);
		
		// 创建确定和取消的2个按钮,及位置和大小
		JButton confirm = new JButton("确定(O)");
		confirm.setMnemonic('O');
		confirm.setBounds(250, 200, 85, 30);
		JButton cancel = new JButton("取消(C)");
		cancel.setMnemonic('C');
		cancel.setBounds(350, 200, 85, 30);
		// 把按钮添加到对话框
		this.add(confirm);
		this.add(cancel);
		
		//设置一直在最上层
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	
	    // 添加"确定"按钮监听
	    confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 设置滑动条的值
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
				
				// TODO 设置背景音乐和游戏音效
				if (playBox.isSelected()) { // 按键被点击
					try {
						AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/audio/background.WAV").getAbsoluteFile());
						Clip clip = AudioSystem.getClip();
						clip.open(audioInputStream);
						clip.start(); // 播放
						clip.loop(Clip.LOOP_CONTINUOUSLY); // 循环	
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
				
				// 设置方块皮肤
				if(lButton.isSelected()) {
					GamePanel.jp_game.skin = "c1";
				}
				if(zButton.isSelected()) {
					GamePanel.jp_game.skin = "c2";
				}
				if(xButton.isSelected()) {
					GamePanel.jp_game.skin = "c3";
				}

				// TODO 设置方块自动上涨
				if(upBox.isSelected()) { // 按键被点击
					GamePanel.jp_game.timer2.start();
				}
				// 关闭此对话框
				GameDialog.this.dispose();
			}
		});
	    
	    // 添加"取消"按钮监听
	    cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 关闭此对话框
				GameDialog.this.dispose();
			}
		});
	}
}
