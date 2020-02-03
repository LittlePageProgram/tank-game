package cn.steveyu.strategy.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageTest {
    @Test
    public void testLoadImage() {
        try {
            File file = new File("");
            System.out.println(ImageTest.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            System.out.println(file.getAbsolutePath());
            BufferedImage image = ImageIO.read(new File("/Users/steveyu/IdeaProjects/tank/src/main/java/images/BadTank1.png"));
//            BufferedImage image2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("/src/main/java/images/BadTank1.png"));
            assertNotNull(image);
//            assertNotNull(image2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
