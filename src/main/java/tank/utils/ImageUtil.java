package tank.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Mayz
 * Date 2024/10/4 16:15
 * Description
 */
public class ImageUtil {

    /**
     * 图片旋转
     *
     * @param bufferedImage
     * @param degree
     * @return
     */
    public static BufferedImage rotateImage(final BufferedImage bufferedImage, final int degree) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int type = bufferedImage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2D = (img = new BufferedImage(width, height, type))
                .createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.rotate(Math.toRadians(degree), width / 2, height / 2);
        graphics2D.drawImage(bufferedImage, 0, 0, null);
        graphics2D.dispose();
        return img;
    }
}
