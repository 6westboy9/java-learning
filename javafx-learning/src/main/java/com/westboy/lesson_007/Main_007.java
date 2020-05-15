package com.westboy.lesson_007;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


/**
 * @author westboy
 * @since 2020/2/20
 */
public class Main_007 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		// Stage --> Scene --> Node
		// Scene 必须放在 Stage 上，而 Node 必须放在 Scene 上
		stage.setTitle("测试");
		stage.setWidth(800);
		stage.setHeight(400);

		// Button 属于 Node
		Button button = new Button("确认");
		// 鼠标进入窗口之后图标行为
		button.setCursor(Cursor.HAND);
		button.setPrefWidth(80);
		button.setPrefHeight(30);
		// 只设置以上两个属性并没有生效


		Group group = new Group();
		group.getChildren().add(button);

		Scene scene = new Scene(group);
		// 鼠标进入窗口之后图标行为
		// scene.setCursor(Cursor.NONE);
		stage.setScene(scene);

		stage.show();
	}
}
