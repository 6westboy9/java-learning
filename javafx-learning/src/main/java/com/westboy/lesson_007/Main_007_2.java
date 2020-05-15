package com.westboy.lesson_007;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


/**
 * @author westboy
 * @since 2020/2/20
 */
public class Main_007_2 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("测试");
		stage.setWidth(800);
		stage.setHeight(400);


		HostServices hostServices = getHostServices();
		// 直接在启动的时候，打开网页
		hostServices.showDocument("www.baidu.com");

		stage.show();

	}
}
