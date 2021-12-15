package main;

import object.Chest;
import entity.NPC;
import object.Door;
import object.Object;
import tile.Map;
import tile.Tile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;

public class Maps {
    public static HashMap<String, Map> maps;
    public static ArrayList<NPC> NPCs;
    public static ArrayList<Object> objects;

    public Maps(){
        maps = new HashMap<>();
        objects = new ArrayList<>();
        NPCs = new ArrayList<>();

        loadNpc();
        loadObject();
        loadMap();
    }

    public void loadMap(){
        File folder = new File("src/resource/map");
        for (File file : folder.listFiles()){
            addMap(file.getName());
        }
    }

    public void loadNpc(){
        // NPCs
        NPC mongo = new NPC("mongo", "gender", "merchant");
        mongo.setTilePosX(26);
        mongo.setTilePosY(58);
        mongo.setMoveAble(4);
        String[] mongoMap = {"village", "village2"};
        mongo.setMap(mongoMap);
        NPCs.add(mongo);
    }

    public void loadObject(){
        // objects
        Chest woodenChest = new Chest("wooden_chest");
        woodenChest.setTilePosX(32);
        woodenChest.setTilePosY(56);
        woodenChest.setMap("village");
        objects.add(woodenChest);

        Door door = new Door("door01");
        door.setTilePosX(26);
        door.setTilePosY(53);
        door.setSpawnPointX(33);
        door.setSpawnPointY(56);
        door.solidArea.setSize(Game.scaledTileSize, Game.scaledTileSize*2);
        door.setDestination("village2");
        door.setMap("village");
        objects.add(door);
    }
    private void addMap(String mapName) {
        maps.put(mapName, new Map(mapName, 100, 100));
    }
    public static Map getMap(String name){
        return maps.get(name);
    }
    public static void Setting(Map map){
        switch (map.name){
            case "village"->{
                System.out.println("Village loaded");
                for (Object obj : objects){
                    if(obj.getMap().equals("village")){
                        map.addObj(obj);
                    }
                }
                for (NPC npc : NPCs){
                    for(String mapName : npc.getMap()){
                        if(mapName.equals("village")){
                            map.addNPC(npc);
                        }
                    };
                }
            }
            case "village2" ->{
                System.out.println("Village2 loaded");
                for (Object obj : objects){
                    if(obj.getMap().equals("village2")){
                        map.addObj(obj);
                    }
                }
                for (NPC npc : NPCs){
                    for(String mapName : npc.getMap()){
                        if(mapName.equals("village2")){
                            map.addNPC(npc);
                        }
                    };
                }
            }
        }
    }
}
