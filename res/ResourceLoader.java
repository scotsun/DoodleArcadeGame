import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

public class ResourceLoader {
	
	static ResourceLoader rl = new ResourceLoader();
	
	public static Image getImage(String filename) {
		URL url = rl.getClass().getResource("images/"+filename);
		return Toolkit.getDefaultToolkit().getImage(url);
	}
	
	public static InputStream getTextsInputStream(String filename) {
		return rl.getClass().getResourceAsStream("texts/"+filename);
	}
}
 