package tank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

/**
 * Created by Mayz
 * Date 2024/10/4 15:19
 * Description 爆炸
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Explode {
    private int x, y;
    TankFrame tf = null;
    static int WIDTH = ResourceLoading.explodes[0].getWidth();
    static int HEIGHT = ResourceLoading.explodes[0].getHeight();
    private int step = 0;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    public void paint(Graphics graphics) {
        while (step < ResourceLoading.explodes.length) {
            graphics.drawImage(ResourceLoading.explodes[step++], x, y, null);
        }
    }

}
