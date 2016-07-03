package cn.blockgame;

import java.util.Random;

/**
 * 功能：定义一个Block类，包括三个属性：方块坐标x,方块坐标y,方块类型type
 * 
 * @author Keira
 */
public class Block {
	// 方块的x坐标
	private int x;
	// 方块的y坐标
	private int y;
	// 方块类型
	private int type;

	
	public Block() {}
	
	public Block(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;	
	}
	
	
//	/**
//	 * 功能：移动方块
//	 * 
//	 * @return
//	 */
//	public void move(int newRow, int newCol) {
//		// 移动前先判断方块是否越界
//		if (!isMoveable(newRow, newCol)) {
//			// 超出边界,不操作
//			return;
//		} else {
//			this.clear();
//			this.x = newRow;
//			this.y = newCol;
////			this.showBlock();
//			new GameAreaPanel().repaint();
//		}
//	}
//	
//	/**
//	 * 功能：实现方块的下落;即列不变，行增加
//	 * 
//	 * @return
//	 */
//	public void downMove() {
//		move(x + 1, y);
//	}
//
//	/**
//	 * 功能：实现方块的旋转
//	 * 
//	 * @return
//	 */
//	public void upMove() {
//		// TODO
//	}
//
//	/**
//	 * 功能：实现方块的左移;即行不变，列减少
//	 * 
//	 * @return
//	 */
//	public void leftMove() {
//		move(x, y - 1);
//	}
//
//	/**
//	 * 功能：实现方块的右移;即行不变，列增加
//	 * 
//	 * @return
//	 */
//	public void reghtMove() {
//		move(x, y - 1);
//	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
