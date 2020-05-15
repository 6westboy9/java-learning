package com.westboy.lesson_001;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author westboy
 * @since 2020/2/20
 */
public class HelloWorld extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("测试");
		stage.show();
	}
}
