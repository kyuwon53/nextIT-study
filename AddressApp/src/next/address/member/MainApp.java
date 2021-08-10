package next.address.member;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		initDriverLoader();
		primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));
		// fxml 파일에서 상위 레이아웃을 가져온다.
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(MainApp.class.getResource("view/MemberList.fxml"));
					AnchorPane rootLayout = (AnchorPane) loader.load();
					
					// 상위 레이아웃을 포함하는 scene을 보여준다.
					Scene scene = new Scene(rootLayout);
					primaryStage.setScene(scene);
					primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
	/*
	 * 오라클 드라이버 로딩 
	 * */
	private void initDriverLoader() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패... ");
			System.out.println("프로그램 종료");
			e.printStackTrace();
			System.exit(0);
		}
	}
}
