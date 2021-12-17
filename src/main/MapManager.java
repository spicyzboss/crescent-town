package main;

import entity.NPCMerchant;
import object.*;
import entity.NPC;
import object.Object;
import tile.Map;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MapManager implements Serializable {
    public static HashMap<String, Map> maps;
    public static ArrayList<NPC> NPCs;
    public static ArrayList<Object> objects;

    public MapManager() {
        maps = new HashMap<>();
        objects = new ArrayList<>();
        NPCs = new ArrayList<>();

        loadNpc();
        loadObject();
        loadMap();
    }

    public void loadMap() {
        File folder = new File("res/map");
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            addMap(file.getName());
        }
    }

    public void loadNpc() {
        // NPCs
        NPCMerchant mongo = new NPCMerchant("mongo", "male", "buy");
        mongo.setTilePosX(19);
        mongo.setTilePosY(7);
        mongo.addDialog("สวัสดีชั้นชื่อ Mongo เป็นพ่อค้า");
        mongo.addDialog("ว่าไงนะแกว่าชั้นว่าหน้าโฉดเกินจะเป็นพ่อค้างั้นหรอ");
        mongo.addDialog("บังอาจ!!!!");
        mongo.addDialog("...");
        mongo.addSaleDialog("อยากซื้ออะไรล่ะ?");
        mongo.setMap("shop1");
        NPCs.add(mongo);

        NPCMerchant andru = new NPCMerchant("andru", "male", "sell");
        andru.setTilePosX(19);
        andru.setTilePosY(7);
        andru.addDialog("สวัสดีชั้นชื่อ Andru เป็นพ่อค้า");
        andru.addDialog("โอ้นายมาใหม่งั้นหรอ ไม่ต้องตกใจไปทุกคนที่นี่ก็เหมือนกันนั่นแหละ");
        andru.addDialog("หากนายต้องการข้อมูลเพิ่มเติมไปบ้านด้านขวาของร้านนี้ดูสิ");
        andru.addDialog("...");
        andru.addSaleDialog("อยากขายอะไรล่ะ?");
        andru.setMap("shop2");
        NPCs.add(andru);

        NPC naina = new NPC("naina", "female", "none");
        naina.setTilePosX(7);
        naina.setTilePosY(8);
        naina.setPattern("line-x");
        naina.addDialog("สวัสดีชั้น naina ตอนนี้แม่ไม่อยู่ไว้มาวันหลังนะ");
        naina.setMap("house");
        naina.setMoving(true);
        naina.setMoveAble(4);
        NPCs.add(naina);

        NPC mika = new NPC("mika", "female", "blacksmith");
        mika.setTilePosX(10);
        mika.setTilePosY(12);
        mika.setPattern("rectangle");
        mika.addDialog("ตอนนี้ร้านเราปิดปรับปรุงอยู่ ยังไม้พร้อมให้บริการนะ");
        mika.addDialog("อืม ถ้าจะให้บอกเวลาที่แน่นอนละก็คงปีหน้าละมั้ง");
        mika.setMap("stone_house");
        mika.setMoving(true);
        mika.setMoveAble(4);
        NPCs.add(mika);

        NPC fiona = new NPC("fiona", "female", "blacksmith");
        fiona.setTilePosX(20);
        fiona.setTilePosY(43);
        fiona.addDialog("เอ luka ไปอยู่ไหนกันนะ");
        fiona.addDialog("เด็กใหม่นายช่วยไปตามหา luka ให้หน่อยได้มั้ยชั้นคิดว่านะอยู่แถวนี้แหละ");
        fiona.setMap("village");
        fiona.setMoveAble(4);
        NPCs.add(fiona);

        NPC luka = new NPC("luka", "female", "blacksmith");
        luka.setTilePosX(37);
        luka.setTilePosY(29);
        luka.setPattern("find direction");
        luka.addDialog("เอ ตรงนี้มันตรงไหนของเกาะกันน๊าาา");
        luka.addDialog("ชั้นต้องไปทางไหนละเนี่ย ต้องโดน Fiona สวดอีกแน่เลย");
        luka.setMap("village");
        luka.setMoveAble(4);
        luka.setMoving(true);
        NPCs.add(luka);

        NPC mana = new NPC("Mana", "female", "unemployed");
        mana.setTilePosX(14);
        mana.setTilePosY(10);
        mana.addDialog("สวัสดีนายเด็กใหม่ที่เค้าล่ำลือกันสินะ");
        mana.addDialog("ที่นี่คือโรงพยาบาล Crescent ถ้าเกิดป่วยขึ้นมาก็มาปรึกษาได้นะ");
        mana.addDialog("เอาละแนะนำตัวเสร็จแล้ว นายเองก็ไปได้แล้วละ");
        mana.setMap("village");
        NPCs.add(mana);
    }

    public void loadObject() {
        // village
        Chest woodenChest = new Chest("wooden_chest", true);
        woodenChest.setTilePosX(32);
        woodenChest.setTilePosY(56);
        woodenChest.setMap("village");
        objects.add(woodenChest);

        MissionBoard missionBoard = new MissionBoard("mission_board", false);
        missionBoard.setTilePosX(42);
        missionBoard.setTilePosY(42);
        missionBoard.setMap("village");
        missionBoard.solidArea.setSize(Game.scaledTileSize*2, Game.scaledTileSize*2);
        objects.add(missionBoard);

        Sign sign = new Sign("sign", false);
        sign.setTilePosX(41);
        sign.setTilePosY(42);
        sign.setMessage("Up:: shop1, shop2\n Left:: stone hoouse");
        sign.setMap("village");
        sign.solidArea.setSize(Game.scaledTileSize, Game.scaledTileSize*2);
        objects.add(sign);

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

        Bed bed = new Bed("green_bed");
        bed.setTilePosX(21);
        bed.setTilePosY(13);
        bed.solidArea.setSize(Game.scaledTileSize, Game.scaledTileSize * 2);
        bed.setMap("player_room");
        objects.add(bed);

        //storage
        Door doorStorage = new Door("door01");
        doorStorage.setTilePosX(33);
        doorStorage.setTilePosY(52);
        doorStorage.setSpawnPointX(13.5);
        doorStorage.setSpawnPointY(16);
        doorStorage.solidArea.setSize(Game.scaledTileSize, Game.scaledTileSize * 2);
        doorStorage.setDestination("storage");
        doorStorage.setMap("village");
        objects.add(doorStorage);

        Door leaveMarkStorage = new Door("leave_mark");
        leaveMarkStorage.setTilePosX(13);
        leaveMarkStorage.setTilePosY(17);
        leaveMarkStorage.setSpawnPointX(33);
        leaveMarkStorage.setSpawnPointY(54);
        leaveMarkStorage.solidArea.setSize(Game.scaledTileSize*2, Game.scaledTileSize);
        leaveMarkStorage.setDestination("village");
        leaveMarkStorage.setMap("storage");
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

        // house
        Door doorHouse = new Door("door02");
        doorHouse.setTilePosX(55);
        doorHouse.setTilePosY(26);
        doorHouse.setSpawnPointX(6.5);
        doorHouse.setSpawnPointY(15);
        doorHouse.solidArea.setSize(Game.scaledTileSize, Game.scaledTileSize * 2);
        doorHouse.setDestination("house");
        doorHouse.setMap("village");
        objects.add(doorHouse);

        Door leaveMarkHouse = new Door("leave_mark");
        leaveMarkHouse.setTilePosX(6);
        leaveMarkHouse.setTilePosY(16);
        leaveMarkHouse.setSpawnPointX(55);
        leaveMarkHouse.setSpawnPointY(27);
        leaveMarkHouse.solidArea.setSize(Game.scaledTileSize*2, Game.scaledTileSize);
        leaveMarkHouse.setDestination("village");
        leaveMarkHouse.setMap("house");
        objects.add(leaveMarkHouse);

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

    public static void update(Map map){
        ArrayList<Object> objs = new ArrayList<>();
        for (Object obj : objects) {
            if (obj.getMap().equals(map.name)) {
                objs.add(obj);
            }
        }
        map.objects = objs;

    }

    public static void setting(Map map) {
        for (Object obj : objects) {
            if (obj.getMap().equals(map.name)) {
                map.addObj(obj);
            }
        }
        for (NPC npc : NPCs) {
            if (npc.getMap().equals(map.name)) {
                map.addNPC(npc);
            }
        }
    }

}
