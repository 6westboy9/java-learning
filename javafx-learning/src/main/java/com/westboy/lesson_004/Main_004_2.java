package com.westboy.lesson_004;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author westboy
 * @since 2020/2/20
 */
public class Main_004_2 extends Application {

	public static void main(String[] args) {
		System.out.println("主线程..." + Thread.currentThread().getName());
		launch(args);
	}

	public Main_004_2() {
		super();
		System.out.println("构造方法..." + Thread.currentThread().getName());
	}

	@Override
	public void init() throws Exception {
		super.init();
		System.out.println("初始化..." + Thread.currentThread().getName());
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		// 点击关闭按钮 × 时，会执行
		System.out.println("停止..." + Thread.currentThread().getName());
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("开始..." + Thread.currentThread().getName());

		// 设置样式类型
		// 默认，常用
		Stage stage1 = new Stage();
		stage1.setTitle("StageStyle.DECORATED");
		stage1.initStyle(StageStyle.DECORATED);
		stage1.show();

		// 透明，不常用
		Stage stage2 = new Stage();
		stage2.setTitle("StageStyle.TRANSPARENT");
		stage2.initStyle(StageStyle.TRANSPARENT);
		stage2.show();

		// 没有上面菜单栏
		Stage stage3 = new Stage();
		stage3.setTitle("StageStyle.UNDECORATED");
		stage3.initStyle(StageStyle.UNDECORATED);
		stage3.show();

		// 常用
		Stage stage4 = new Stage();
		stage4.setTitle("StageStyle.UNIFIED");
		stage4.initStyle(StageStyle.UNIFIED);
		stage4.show();

		// 常用，没有窗口最大最小化
		Stage stage5 = new Stage();
		stage5.setTitle("StageStyle.UTILITY");
		stage5.initStyle(StageStyle.UTILITY);
		stage5.show();
	}
}
