package com.westboy.lesson_004;

import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author westboy
 * @since 2020/2/20
 */
public class Main_004_3 extends Application {

	public static void main(String[] args) {
		System.out.println("主线程..." + Thread.currentThread().getName());
		launch(args);
	}

	public Main_004_3() {
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

		// 不能最小化
		Stage stage1 = new Stage();
		stage1.setTitle("STAGE-1");
		// stage1.initModality(Modality.APPLICATION_MODAL);
		stage1.show();

		// 透明，不常用
		Stage stage2 = new Stage();

		stage2.setTitle("STAGE-2");
		// 在 Modality.APPLICATION_MODAL 模式下，stage2 是 stage1 拥有者，要关闭 stage1 先必须关闭 stage2，stage2 不能最小化
		// 在 Modality.NONE 模式下，stage2 是 stage1 拥有者，关闭 stage1 的同时也会关闭 stage2，但是关闭 stage2 不会关闭 stage1
		// 在 Modality.WINDOW_MODAL 模式下，跟 Modality.APPLICATION_MODAL 效果相同
		stage2.initOwner(stage1);
		// stage2.initModality(Modality.APPLICATION_MODAL);
		// stage2.initModality(Modality.NONE);
		stage2.initModality(Modality.WINDOW_MODAL);
		stage2.show();

		// 没有上面菜单栏
		Stage stage3 = new Stage();
		stage3.setTitle("STAGE-3");
		// stage3.initModality(Modality.WINDOW_MODAL);
		stage3.show();
	}
}
