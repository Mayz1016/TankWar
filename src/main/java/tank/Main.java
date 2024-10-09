package tank;

import tank.config.ConfigManager;
import tank.enums.Direction;
import tank.enums.Group;

/**
 * Created by Mayz
 * Date 2024/10/1 13:59
 * Description
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        ConfigManager configManager = new ConfigManager();
//        Integer tankNum = Integer.parseInt((String) ConfigManager.get("tank.num"));
        Integer tankNum = (Integer) configManager.get("tank.num");
        //初始化敌方坦克
        for (int i = 0; i < tankNum; i++) {
            tankFrame.enemyTanks.add(new Tank(tankFrame, Direction.DOWN, Group.RED, 50 + i * 100, 100 + i));
        }
        while (true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
