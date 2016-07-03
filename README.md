# Keira_Code
自己练手小项目的仓库
此版本介绍：
	上版本未解决问题：分数面板区的文本框控件中,如何一直循环setScoreDelay(scoreTemp)呢？？？
	完成方块的彩色设计
	
	完成方块下落区面板的背景图片添加
		思考:
			如果给整个面板添加背景,那么怎么才能使面板不覆盖窗体的背景图片呢？是不是设置面板的透明度呢？待解决！
		解决方法:
				// 设置面板透明化
				this.setOpaque(false);
				然后在GamePanel类中添加背景图片(10张,跟随定时器timer定时更换)
				还有就是更换图片后,需要把每个面板都重新repaint()重绘一次
	完成设置窗体图标
		在GameFrame类中
			// 设置图标
			Image icon_jframe = new ImageIcon("resources\\image\\icon3.jpg").getImage();
			this.setIconImage(icon_jframe);
	初步完成窗体皮肤设置;只是用系统自带的皮肤包
	窗体的皮肤更改设置
		swing的界面通常是通过UIManager.setLookAndFeel来控制的
		java 的自带几种皮肤的类名:
			javax.swing.plaf.metal.MetalLookAndFeel
			com.sun.java.swing.plaf.windows.WindowsLookAndFeel
			com.sun.java.swing.plaf.motif.MotifLookAndFeel
			com.sun.java.swing.plaf.mac.MacLookAndFeel
			com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel //java6新皮肤，玻璃透明	
	
	完成游戏的暂停和继续功能
		思路：
			timer.stop(); // 暂停
			timer.start(); // 继续
		待完善:
			执行相关操作弹出对应的图片提示
			
	完成背景音乐和游戏音效
		思考:
			如何取消播放音效？每一个？
			还有,自定义对话框中的设置生效后,关闭自定义对话框再次打开自定义对话框,发现没办法记住之前所设置的状态
		解决方法:
			因为每次监听完"确定"按钮后,关闭了自定义对话框,之后每次都是重新调用也就是重新new一个新的自定义对话框,所以无法记住状态
			可以时监听完成之后,隐藏对话框,而不是diapose()关闭它;但此想法有待考究!
			
	完成方块自动上涨	
		小BUG：
			方块触顶结束游戏的那段代码中，一直循环打印"GAME OVER",证明没有真的结束游戏;待改！
		解决方法:
			在GameAreaPanel类的paintComponent()方法中调用了gameOver()方法 -- 里面有个endGame()方法:
				//注意:不能重绘两次;在下落区面板的重绘中 -- paintComponent中又调用了一次repaint(),结果造成递归重绘
				// 这样在结束游戏后面板还在重绘
				//GameAreaPanel.this.repaint();
	
	完成亮点的设计
		亮点:
			点击功能区的ScorePanel类中的"咩~~~"按钮,可以随机消除某几行里面的方块,同时配有音效提醒
		注意:
			// 产生0-15的随机数
			int rTemp = (int)(0+Math.random()*(15-0+1));
			// 产生16-30的随机数
			int rTemp2 = (int)(16+Math.random()*(30-16+1));
			// 让下落区重新获取焦点
			GamePanel.jp_game.requestFocus();
		
