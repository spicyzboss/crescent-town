package main;

import object.Chest;
import entity.NPC;
import object.Door;
import object.Object;
import tile.Map;
import tile.Tile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;

public class Maps implements Serializable {
    public static HashMap<String, Map> maps;
    public static ArrayList<NPC> NPCs;
    public static ArrayList<Object> objects;

    public Maps() {
        maps = new HashMap<>();
        objects = new ArrayList<>();
        NPCs = new ArrayList<>();

        loadNpc();
        loadObject();
        loadMap();
    }

    public void loadMap() {
        File folder = new File("src/resource/map");
        for (File file : folder.listFiles()) {
            addMap(file.getName());
        }
    }

    public void loadNpc() {
        // NPCs
        NPC mongo = new NPC("mongo", "gender", "merchant");
        mongo.setTilePosX(26);
        mongo.setTilePosY(58);
        mongo.setMoveAble(4);
        String[] mongoMap = {"village", "village2"};
        mongo.setMap(mongoMap);
        NPCs.add(mongo);
    }

    public void loadObject() {
        // objects
        Chest woodenChest = new Chest("wooden_chest");
        woodenChest.setTilePosX(32);
        woodenChest.setTilePosY(56);
        woodenChest.setMap("village");
        objects.add(woodenChest);

        // player room
        Door door = new Door("door01");
        door.setTilePosX(26);
        door.setTilePosY(53);
        door.setSpawnPointX(10.5);
        door.setSpawnPointY(18);
        door.solidArea.setSize(Game.scaledTileSize, Game.scaledTileSize * 2);
        door.setDestination("player_room");
        door.setMap("village");
        objects.add(door);

        Door leaveMark = new Door("leave_mark");
        leaveMark.setTilePosX(10);
        leaveMark.setTilePosY(19);
        leaveMark.setSpawnPointX(26);
        leaveMark.setSpawnPointY(54);
        leaveMark.solidArea.setSize(Game.scaledTileSize*2, Game.scaledTileSize);
        leaveMark.setDestination("village");
        leaveMark.setMap("player_room");
        objects.add(leaveMark);

        Door doorStorage = new Door("door01");
        doorStorage.setTilePosX(32);
        doorStorage.setTilePosY(52);
        doorStorage.setSpawnPointX(33.5);
        doorStorage.setSpawnPointY(14);
        doorStorage.solidArea.setSize(Game.scaledTileSize, Game.scaledTileSize * 2);
        doorStorage.setDestination("player_room");
        doorStorage.setMap("village");
        objects.add(doorStorage);

        Door leaveMarkStorage = new Door("leave_mark");
        leaveMarkStorage.setTilePosX(33);
        leaveMarkStorage.setTilePosY(15);
        leaveMarkStorage.setSpawnPointX(32);
        leaveMarkStorage.setSpawnPointY(54);
        leaveMarkStorage.solidArea.setSize(Game.scaledTileSize*2, Game.scaledTileSize);
        leaveMarkStorage.setDestination("village");
        leaveMarkStorage.setMap("player_room");
        objects.add(leaveMarkStorage);


        // shop1
        Door doorShop1 = new Door("door02");
        doorShop1.setTilePosX(46);
        doorShop1.setTilePosY(17);
        doorShop1.setSpawnPointX(11);
        doorShop1.setSpawnPointY(15);
        doorShop1.solidArea.setSize(Game.scaledTileSize, Game.scaledTileSize * 2);
        doorShop1.setDestination("shop1");
        doorShop1.setMap("village");
        objects.add(doorShop1);

        Door leaveMarkShop1 = new Door("leave_mark_3");
        leaveMarkShop1.setTilePosX(10);
        leaveMarkShop1.setTilePosY(16);
        leaveMarkShop1.setSpawnPointX(46);
        leaveMarkShop1.setSpawnPointY(18);
        leaveMarkShop1.solidArea.setSize(Game.scaledTileSize*3, Game.scaledTileSize);
        leaveMarkShop1.setDestination("village");
        leaveMarkShop1.setMap("shop1");
        objects.add(leaveMarkShop1);

        // shop2
        Door doorShop2 = new Door("door02");
        doorShop2.setTilePosX(47);
        doorShop2.setTilePosY(26);
        doorShop2.setSpawnPointX(12.5);
        doorShop2.setSpawnPointY(15);
        doorShop2.solidArea.setSize(Game.scaledTileSize, Game.scaledTileSize * 2);
        doorShop2.setDestination("shop2");
        doorShop2.setMap("village");
        objects.add(doorShop2);

        Door leaveMarkShop2 = new Door("leave_mark");
        leaveMarkShop2.setTilePosX(12);
        leaveMarkShop2.setTilePosY(16);
        leaveMarkShop2.setSpawnPointX(47);
        leaveMarkShop2.setSpawnPointY(28);
        leaveMarkShop2.solidArea.setSize(Game.scaledTileSize*2, Game.scaledTileSize);
        leaveMarkShop2.setDestination("village");
        leaveMarkShop2.setMap("shop2");
        objects.add(leaveMarkShop2);

        Door house2 = new Door("door02");
        house2.setTilePosX(55);
        house2.setTilePosY(26);
        house2.setSpawnPointX(12.5);
        house2.setSpawnPointY(15);
        house2.solidArea.setSize(Game.scaledTileSize, Game.scaledTileSize * 2);
        house2.setDestination("shop2");
        house2.setMap("village");
        objects.add(house2);

        // stone house
        Door doorStone = new Door("door03");
        doorStone.setTilePosX(28);
        doorStone.setTilePosY(41);
        doorStone.setSpawnPointX(12.5);
        doorStone.setSpawnPointY(16);
        doorStone.solidArea.setSize(Game.scaledTileSize, Game.scaledTileSize * 2);
        doorStone.setDestination("stone_house");
        doorStone.setMap("village");
        objects.add(doorStone);

        Door leaveMarkStone = new Door("leave_mark");
        leaveMarkStone.setTilePosX(12);
        leaveMarkStone.setTilePosY(18);
        leaveMarkStone.setSpawnPointX(28);
        leaveMarkStone.setSpawnPointY(42);
        leaveMarkStone.solidArea.setSize(Game.scaledTileSize*2, Game.scaledTileSize);
        leaveMarkStone.setDestination("village");
        leaveMarkStone.setMap("stone_house");
        objects.add(leaveMarkStone);

        // hospital
        Door doorHospital = new Door("door01");
        doorHospital.setTilePosX(49);
        doorHospital.setTilePosY(65);
        doorHospital.setSpawnPointX(10);
        doorHospital.setSpawnPointY(15);
        doorHospital.solidArea.setSize(Game.scaledTileSize, Game.scaledTileSize * 2);
        doorHospital.setDestination("hospital");
        doorHospital.setMap("village");
        objects.add(doorHospital);

        Door leaveHospital = new Door("leave_mark_3");
        leaveHospital.setTilePosX(9);
        leaveHospital.setTilePosY(16);
        leaveHospital.setSpawnPointX(49);
        leaveHospital.setSpawnPointY(66);
        leaveHospital.solidArea.setSize(Game.scaledTileSize*3, Game.scaledTileSize);
        leaveHospital.setDestination("village");
        leaveHospital.setMap("hospital");
        objects.add(leaveHospital);

    }

    private void addMap(String mapName) {
        maps.put(mapName, new Map(mapName, 100, 100));
    }

    public static Map getMap(String name) {
        return maps.get(name);
    }

    public static void Setting(Map map) {
        System.out.println(objects.isEmpty());

        System.out.println(map.name + " loaded");
        for (Object obj : objects) {
            if (obj.getMap().equals(map.name)) {
                System.out.println(obj.toString());

                map.addObj(obj);
            }
        }
        for (NPC npc : NPCs) {
            for (String mapName : npc.getMap()) {
                if (mapName.equals(map.name)) {
                    map.addNPC(npc);
                }
            }
        }
    }

}
