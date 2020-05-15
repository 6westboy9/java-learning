package com.westboy.lesson_004;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author westboy
 * @since 2020/2/20
 */
public class Main_004 extends Application {

	public static void main(String[] args) {
		System.out.println("主线程..." + Thread.currentThread().getName());
		launch(args);
	}

	public Main_004() {
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
		// 在 iOS 系统下没有显示
		// primaryStage.getIcons().add(new Image("icon/gift.png"));
		// primaryStage.setWidth(1000);
		// primaryStage.setMinWidth(800);
		// primaryStage.setMaxWidth(1200);

		// primaryStage.setHeight(500);
		// primaryStage.setMinHeight(300);
		// primaryStage.setMaxHeight(700);

		// 如果没有设置宽度和高度的时候需要在 show 方法后调用获取
		System.out.println("宽度: " + primaryStage.getWidth());
		System.out.println("高度: " + primaryStage.getHeight());

		// 动态监听窗口大小变化
		// primaryStage.widthProperty().addListener((observable, oldValue, newValue) -> System.out.println("当前宽度:" + newValue.doubleValue()));
		// primaryStage.heightProperty().addListener((observable, oldValue, newValue) -> System.out.println("当前高度:" + newValue.doubleValue()));

		// 自动进入全屏，必须设置 Scene，Scene 后续进行讲解，简单理解为在必须在窗口上放置一块桌布，在桌布上面才可以放一些花盆等等
		// primaryStage.setFullScreen(true);
		// primaryStage.setScene(new Scene(new Group()));

		// 设置背景透明度，范围：0~1，0 为完全透明，1 为完全不透明
		// primaryStage.setOpacity(0.5);

		// 设置窗口置顶
		// primaryStage.setAlwaysOnTop(true);

		// 设置样式类型
		// primaryStage.initStyle(StageStyle.DECORATED); // 默认，常用
		// primaryStage.initStyle(StageStyle.TRANSPARENT); // 透明
		// primaryStage.initStyle(StageStyle.UNDECORATED); // 没有上面菜单栏
		// primaryStage.initStyle(StageStyle.UNIFIED); // 常用
		// primaryStage.initStyle(StageStyle.UTILITY); // 常用，没有窗口最大最小化

		// 设置不可调整大小
		// primaryStage.setResizable(false);
		primaryStage.show();
		// 其他设置参考 Stage 类的 API 文档
	}
}
