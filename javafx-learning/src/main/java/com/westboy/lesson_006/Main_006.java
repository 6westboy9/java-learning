package com.westboy.lesson_006;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * @author westboy
 * @since 2020/2/20
 */
public class Main_006 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		stage.setTitle("测试");
		Screen screen = Screen.getPrimary();

		System.out.println("dpi: " + screen.getDpi());


		Rectangle2D bounds = screen.getBounds();
		System.out.println(bounds);
		// 可视化边界
		Rectangle2D visualBounds =  screen.getVisualBounds();

		System.out.println(visualBounds);

		stage.show();
	}
}
