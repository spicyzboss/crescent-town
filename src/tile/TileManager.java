package tile;

import main.Map;

import java.util.*;

public class TileManager {
    Map map;

    public static HashMap<String, Tile> tile;

    public TileManager(Map map){
        this.map = map;

        tile = new HashMap<String, Tile>();

        loadTile();
    }

    public void loadTile(){
        addTile("water");
        addTile("sand");
        addTile("grass01");
        addTile("grass02");
        addTile("grass03");
        addTile("grass04");
        addTile("grass05");
        addTile("grass06");
        addTile("grass07");
        addTile("grass08");
        addTile("road01");
        addTile("road02");
        addTile("road03");
        addTile("road04");
        addTile("road05");
        addTile("road06");
        addTile("road07");
        addTile("road08");
        addTile("road");
        addTile("grass_corner01");
        addTile("grass_corner02");
        addTile("grass_corner03");
        addTile("grass_corner04");
        addTile("grass_corner05");
        addTile("grass_corner06");
        addTile("grass_corner07");
        addTile("grass_corner08");
        addTile("level_floor01");
        addTile("level_floor02");
        addTile("level_floor03");
        addTile("brick01");
        addTile("brick02");
        addTile("port01");
        addTile("port02");
        addTile("player_house_wall01_1");
        addTile("player_house_wall01_2");
        addTile("player_house_wall01_3");
        addTile("player_house_wall02_1");
        addTile("player_house_wall02_2");
        addTile("player_house_wall02_3");
        addTile("player_house_roof01");
        addTile("player_house_roof02");
        addTile("player_house_roof03");
        addTile("player_house_roof04");
        addTile("player_house_floor01");
        addTile("player_house_floor02");
        addTile("stone_house_wall01_1");
        addTile("stone_house_wall01_2");
        addTile("stone_house_wall01_3");
        addTile("stone_house_wall02_1");
        addTile("stone_house_wall02_2");
        addTile("stone_house_wall02_3");
        addTile("stone_house_roof01");
        addTile("stone_house_roof02");
        addTile("stone_house_roof03");
        addTile("stone_house_roof04");
        addTile("shop_wall01_1");
        addTile("shop_wall01_2");
        addTile("shop_wall01_3");
        addTile("shop_wall02_1");
        addTile("shop_wall02_2");
        addTile("shop_wall02_3");
        addTile("shop_roof01");
        addTile("shop_roof02");
        addTile("shop_roof03");
        addTile("shop_roof04");
        addTile("pool01_1");
        addTile("pool01_2");
        addTile("pool01_3");
        addTile("pool02_1");
        addTile("pool02_2");
        addTile("pool02_3");
        addTile("pool03_1");
        addTile("pool03_2");
        addTile("pool03_3");
        addTile("bush");
        addTile("bench01");
        addTile("bench02");
        addTile("bench03");
        addTile("clothes_line01_1");
        addTile("clothes_line01_2");
        addTile("clothes_line01_3");
        addTile("clothes_line01_4");
        addTile("clothes_line02_1");
        addTile("clothes_line02_2");
        addTile("clothes_line02_3");
        addTile("clothes_line02_4");
        addTile("camber01");
        addTile("camber02");
        addTile("camber03");
        addTile("camber04");
        addTile("camber05");
        addTile("camber06");
        addTile("table01_1");
        addTile("table01_2");
        addTile("table01_3");
        addTile("table02_1");
        addTile("table02_2");
        addTile("table02_3");
        addTile("table03_1");
        addTile("table03_2");
        addTile("table03_3");
        addTile("jar01");
        addTile("jar02");
        addTile("fence_x");
        addTile("fence_y");
        addTile("fence_top_left");
        addTile("fence_top_right");
        addTile("fence_bot_right");
        addTile("fence_bot_left");
        addTile("fence_end_left");
        addTile("fence_end_right");
        addTile("fence_end_top");
        addTile("stair_top_left");
        addTile("stair_top_right");
        addTile("stair_mid_left");
        addTile("stair_mid_cen");
        addTile("stair_mid_right");
        addTile("stair_bot_left");
        addTile("stair_bot_cen");
        addTile("stair_bot_right");
        addTile("player_house_door01");
        addTile("player_house_door02");
        addTile("straws01");
        addTile("straws02");
        addTile("straws03");
        addTile("straws04");
        addTile("straws05");
        addTile("straws06");
        addTile("shop_door01");
        addTile("shop_door02");
        addTile("stone_house_door01");
        addTile("stone_house_door02");
        addTile("stone_house_tag");
        addTile("barrel01");
        addTile("barrel02");
        addTile("barrel03");
        addTile("barrel04");
        addTile("barrel05");
        addTile("barrel06");
        addTile("window01");
        addTile("window02");
        addTile("mission_board01");
        addTile("mission_board02");
        addTile("mission_board03");
        addTile("mission_board04");
        addTile("sign01");
        addTile("sign02");
        addTile("sign03");
        addTile("post_box");
    }

    private void addTile(String filename) {
        tile.put(filename, new Tile(filename));
    }

    public static Tile getTile(String name) {
        return tile.get(name);
    }

}
