package tank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tank.enums.Direction;
import tank.enums.Group;

import java.awt.*;
import java.util.Random;

/**
 * Created by Mayz
 * Date 2024/10/1 18:10
 * Description 坦克类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tank {
    private static final int STEP = 3;
    private Boolean moving = true;
    static int WIDTH = ResourceLoading.blueTankL.getWidth();
    static int HEIGHT = ResourceLoading.blueTankL.getHeight();
    Direction direction = Direction.UP;
    private Boolean isLive = true;
    private Random random = new Random();
    private Group group;
    TankFrame tf = null;
    Rectangle rectangle = new Rectangle();

    private int x, y;

    public Tank(TankFrame tf, Direction direction, Group group, int x, int y) {
        this.tf = tf;
        this.direction = direction;
        this.group = group;
        this.x = x;
        this.y = y;
        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = Tank.WIDTH;
        rectangle.height = Tank.HEIGHT;
    }

    /**
     * 边界检测
     */
    public void boundsCheck() {
        if (this.x < 0) x = 0;
        if (this.y < 0) y = 30;
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH) x = TankFrame.GAME_WIDTH - Tank.WIDTH;
        if (this.y > TankFrame.GAME_HEIGTH - Tank.HEIGHT) y = TankFrame.GAME_HEIGTH - Tank.HEIGHT;
    }

    /**
     * 画图方法
     *
     * @param g
     */
    public void paint(Graphics g) {
        if (!isLive) tf.enemyTanks.remove(this);
        switch (direction) {
            case LEFT:
                g.drawImage(group == Group.BLUE ? ResourceLoading.blueTankL : ResourceLoading.redTankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(group == Group.BLUE ? ResourceLoading.blueTankR : ResourceLoading.redTankR, x, y, null);
                break;
            case UP:
                g.drawImage(group == Group.BLUE ? ResourceLoading.blueTankU : ResourceLoading.redTankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(group == Group.BLUE ? ResourceLoading.blueTankD : ResourceLoading.redTankD, x, y, null);
                break;
            default:
                break;
        }
        move();
    }

    private void move() {
        if (!moving) return;
        switch (direction) {
            case LEFT:
                x -= STEP;
                break;
            case RIGHT:
                x += STEP;
                break;
            case UP:
                y -= STEP;
                break;
            case DOWN:
                y += STEP;
                break;
            default:
                break;
        }
        //红色坦克设置
        if (group == Group.RED && random.nextInt(100) > 95) {
            //随机开火
            fire();
            //随机方向
            randomdirection();
        }
        //边界检测
        boundsCheck();
        //update rectangle
        rectangle.x = this.x;
        rectangle.y = this.y;
    }

    private void randomdirection() {
        this.direction = Direction.values()[random.nextInt(4)];
    }

    /**
     * 打子弹
     */
    public void fire() {
        //计算子弹发射的坐标点
        int bulletX = x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bulletY = y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        tf.bullets.add(new Bullet(tf, bulletX, bulletY, this.direction, this.group));
    }

    //坦克击毁
    public void die() {
        isLive = false;
    }
}
