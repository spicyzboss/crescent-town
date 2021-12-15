package entity;

import main.Game;
import tile.MainMap;

import java.awt.*;

public class AssetManage {
    public static void mapSetting(MainMap map) {
        switch (map.name){
            case "village"->{
                Object woodenChest = new Chest("wooden chest");
                woodenChest.setTilePosX(32);
                woodenChest.setTilePosY(56);
                map.objects.add(woodenChest);

                Object sign = new Object("sign");
                sign.setTilePosX(48);
                sign.setTilePosY(66);
                sign.solidArea.setSize(Game.scaledTileSize, Game.scaledTileSize*2);

                NPC mongo = new NPC("mongo", "gender", "merchant");
                mongo.setTilePosX(26);
                mongo.setTilePosY(58);
                mongo.setMoveAble(4);
                mongo.setDirection("right");
                map.addNpc(mongo);
            }
        }

    }
}
