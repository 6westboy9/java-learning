package com.westboy.lesson_002;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author westboy
 * @since 2020/2/20
 */
public class Main_002 extends Application {

	// 主线程...main
	// 构造方法...JavaFX Application Thread
	// 初始化...JavaFX-Launcher
	// 开始...JavaFX Application Thread
	// 停止...JavaFX Application Thread

	public static void main(String[] args) {
		System.out.println("主线程..." + Thread.currentThread().getName());
		Application.launch(Main_002.class, args);
	}

	public Main_002() {
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
		primaryStage.setTitle("测试");
		primaryStage.show();
	}
}
