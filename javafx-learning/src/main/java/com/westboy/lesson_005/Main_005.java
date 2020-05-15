package com.westboy.lesson_005;

import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * @author westboy
 * @since 2020/2/20
 */
public class Main_005 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		System.out.println("1-start 方法里面的线程: " + Thread.currentThread().getName());

		// 作用：在空闲的时候更新 UI 界面，底层实现逻辑是一个队列
		// 不要搞下载任务，下载任务有专门的类去实现
		// Platform.runLater(() -> System.out.println("run 方法里面的线程: " + Thread.currentThread().getName()));


		// 设置为 false 之后，即使点击关闭页面窗口，但是后台程序并没有关闭，需要执行 Platform.exit(); 才能关闭
		// Platform.setImplicitExit(false);

		System.out.println("2-start 方法里面的线程: " + Thread.currentThread().getName());

		System.out.println("是否支持 3D 效果: " + Platform.isSupported(ConditionalFeature.SCENE3D));

		stage.show();

		// 窗口一闪而过，一般不怎么用
		// Platform.exit();
	}

	@Override
	public void init() throws Exception {
		super.init();
		System.out.println("初始化...");
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		System.out.println("结束...");
	}
}
