package tank;

import tank.utils.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Mayz
 * Date 2024/10/3 15:19
 * Description
 */
public class ResourceLoading {
    public static BufferedImage blueTankL, blueTankR, blueTankU, blueTankD;
    public static BufferedImage redTankL, redTankR, redTankU, redTankD;
    public static BufferedImage bulletL, bulletR, bulletU, bulletD;
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            blueTankU = ImageIO.read(ResourceLoading.class.getClassLoader().getResourceAsStream("images/badTank2.png"));
            blueTankL = ImageUtil.rotateImage(blueTankU, -90);
            blueTankR = ImageUtil.rotateImage(blueTankU, 90);
            blueTankD = ImageUtil.rotateImage(blueTankU, 180);
            redTankU = ImageIO.read(ResourceLoading.class.getClassLoader().getResourceAsStream("images/badTank1.png"));
            redTankL = ImageUtil.rotateImage(redTankU, -90);
            redTankR = ImageUtil.rotateImage(redTankU, 90);
            redTankD = ImageUtil.rotateImage(redTankU, 180);
            bulletU = ImageIO.read(ResourceLoading.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            bulletR = ImageUtil.rotateImage(bulletU, 90);
            bulletD = ImageUtil.rotateImage(bulletU, 180);
            for (int i = 1; i < 17; i++) {
                explodes[i - 1] = ImageIO.read(ResourceLoading.class.getClassLoader().getResourceAsStream("images/e" + i + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
