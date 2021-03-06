package thiagodnf.sootparser;

import java.net.URL;

import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import thiagodnf.sootparser.util.FXMLUtil;
import thiagodnf.sootparser.util.OSUtil;

@SuppressWarnings("restriction")
public class Launcher extends Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(Launcher.class);
	
	public static void main(String[] args) throws Exception {
		
		LOGGER.info("Starting SootParser");
		LOGGER.info("User Directory: {}", System.getProperty("user.dir"));
		LOGGER.info("Operational System Name: {}", System.getProperty("os.name"));
		LOGGER.info("Operational System Arch: {}", System.getProperty("os.arch"));
		LOGGER.info("Operational System Version: {}", System.getProperty("os.version"));
		
		if (OSUtil.isMac()) {
			try {
				URL iconURL = Launcher.class.getResource("/images/icon.png");
				java.awt.Image image = new ImageIcon(iconURL).getImage();
				com.apple.eawt.Application.getApplication().setDockIconImage(image);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
		
		launch(args);
	}

	public void start(Stage stage) throws Exception {

		Parent rootNode = (Parent) FXMLUtil.load("/fxml/home.fxml");

		Scene scene = new Scene(rootNode);
		scene.getStylesheets().add("/styles/styles.css");

		stage.setTitle("SootParser");
		stage.setScene(scene);
		stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/icon.png")));
		stage.show();
	}
}
