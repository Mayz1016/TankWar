package tank;

import tank.enums.Direction;
import tank.enums.Group;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mayz
 * Date 2024/10/1 14:10
 * Description
 */
public class TankFrame extends Frame {
    //自己的坦克
    Tank tank = new Tank(this, Direction.UP, Group.BLUE, 100, 500);
    //敌方坦克
    List<Tank> enemyTanks = new ArrayList<Tank>();
    List<Bullet> bullets = new ArrayList<Bullet>();
    static final int GAME_WIDTH = 1080, GAME_HEIGTH = 960;

    public TankFrame() {
        this.setSize(GAME_WIDTH, GAME_HEIGTH);
        this.setResizable(false);
        this.setVisible(true);
        this.setTitle("TANK WAR");

        this.addKeyListener(new MykeyListener());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
    }

    /**
     * 画图类
     *
     * @param g 画笔工具
     */
    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("界面内发射的子弹数量：" + bullets.size() + "  敌方坦克数量：" + enemyTanks.size(), 10, 50);
        g.setColor(color);
        //我的坦克
        tank.paint(g);
        //我的子弹
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.paint(g);
        }
        //敌人的子弹
        for (int i = 0; i < enemyTanks.size(); i++) {
            Tank enemyTank = enemyTanks.get(i);
            enemyTank.paint(g);
        }
        //碰撞检测
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            for (int j = 0; j < enemyTanks.size(); j++) {
                Tank enemyTank = enemyTanks.get(j);
                if (bullet.collidewith(enemyTank)) {
                    //发生碰撞加载爆炸gif
                    int ex = enemyTank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
                    int ey = enemyTank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
                    Explode explode = new Explode(ex, ey, enemyTank.tf);
                    explode.paint(g);
                }
            }
        }
    }

    //解决双缓冲问题
    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGTH);
        }
        Graphics graphics = offScreenImage.getGraphics();
        Color color = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, GAME_WIDTH, GAME_HEIGTH);
        graphics.setColor(color);
        paint(graphics);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    /**
     * 键盘监听类
     */
    class MykeyListener extends KeyAdapter {
        Boolean kl = false;
        Boolean kr = false;
        Boolean ku = false;
        Boolean kd = false;

        /**
         * 键盘按下事件监听
         *
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    kl = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    kr = true;
                    break;
                case KeyEvent.VK_UP:
                    ku = true;
                    break;
                case KeyEvent.VK_DOWN:
                    kd = true;
                    break;
                default:
                    break;
            }
            setTankDirection();
        }

        /**
         * 键盘松开事件监听
         *
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    kl = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    kr = false;
                    break;
                case KeyEvent.VK_UP:
                    ku = false;
                    break;
                case KeyEvent.VK_DOWN:
                    kd = false;
                    break;
                case KeyEvent.VK_SPACE:
                    tank.fire();
                    break;
                default:
                    break;
            }
            setTankDirection();
        }

        /**
         * 改变坦克方向方法
         */
        public void setTankDirection() {
            if (!kd && !ku && !kl && !kr)
                //未按键，则坦克静止不动
                tank.setMoving(false);
            else {
                tank.setMoving(true);
                if (kd) tank.setDirection(Direction.DOWN);
                if (ku) tank.setDirection(Direction.UP);
                if (kl) tank.setDirection(Direction.LEFT);
                if (kr) tank.setDirection(Direction.RIGHT);
            }
        }
    }
}