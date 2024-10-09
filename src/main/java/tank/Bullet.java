package tank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tank.enums.Direction;
import tank.enums.Group;

import java.awt.*;

/**
 * Created by Mayz
 * Date 2024/10/3 13:49
 * Description 子弹类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bullet {
    private static final int SPEED = 6;
    static int WIDTH = ResourceLoading.bulletL.getWidth();
    static int HEIGHT = ResourceLoading.bulletL.getHeight();
    //是否存活
    private Boolean isLive = true;
    private Group group = Group.RED;
    TankFrame tf = null;

    private int x, y;
    private Direction direction;
    Rectangle rectangle = new Rectangle();

    public Bullet(TankFrame tf, int x, int y, Direction direction, Group group) {
        this.tf = tf;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.group = group;
        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = Tank.WIDTH;
        rectangle.height = Tank.HEIGHT;
    }

    public void paint(Graphics graphics) {
        if (!isLive) tf.bullets.remove(this);
        switch (direction) {
            case LEFT:
                graphics.drawImage(ResourceLoading.bulletL, x, y, null);
                break;
            case RIGHT:
                graphics.drawImage(ResourceLoading.bulletR, x, y, null);
                break;
            case UP:
                graphics.drawImage(ResourceLoading.bulletU, x, y, null);
                break;
            case DOWN:
                graphics.drawImage(ResourceLoading.bulletD, x, y, null);
                break;
            default:
                break;
        }

        move();
    }

    private void move() {
        switch (direction) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }
        //update rectangle
        rectangle.x = this.x;
        rectangle.y = this.y;
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGTH) {
            isLive = false;
        }
    }

    /**
     * 碰撞检测
     *
     * @param tank
     */
    public Boolean collidewith(Tank tank) {
        Boolean isCollide = false;
        if (this.group == tank.getGroup()) return isCollide;
        if (this.rectangle.intersects(tank.getRectangle())) {
            //坦克击毁
            tank.die();
            //子弹销毁
            this.die();
            isCollide = true;
        }
        return isCollide;
    }

    //子弹销毁
    private void die() {
        this.isLive = false;
    }
}
