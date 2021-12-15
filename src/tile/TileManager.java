package tile;

import java.io.File;
import java.util.*;

public class TileManager {
    public static HashMap<String, Tile> tile;

    public TileManager() {
        tile = new HashMap<>();
        loadTile();
    }

    public void loadTile(){
        File folder = new File("src/resource/tile");
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            addTile(fileEntry.getName().replace(".png", ""));
        }
    }

    private void addTile(String filename) {
        tile.put(filename, new Tile(filename));
    }

    public static Tile getTile(String name) {
        return tile.get(name);
    }

    public static Tile getTileByNumber(int tileNumber){
        return switch (tileNumber) {
            case 1 -> TileManager.getTile("road01");
            case 4 -> TileManager.getTile("road04");
            case 6 -> TileManager.getTile("sand");
            case 75 -> TileManager.getTile("grass01");
            case 76 -> TileManager.getTile("grass02");
            case 77 -> TileManager.getTile("grass03");
            case 78 -> TileManager.getTile("grass04");
            case 83 -> TileManager.getTile("grass05");
            case 84 -> TileManager.getTile("grass06");
            case 85 -> TileManager.getTile("grass07");
            case 86 -> TileManager.getTile("grass08");
            case 52 -> TileManager.getTile("road02");
            case 33 -> TileManager.getTile("road03");
            case 21 -> TileManager.getTile("road05");
            case 35 -> TileManager.getTile("road06");
            case 31 -> TileManager.getTile("road07");
            case 23 -> TileManager.getTile("road08");
            case 14 -> TileManager.getTile("road");
            case 89 -> TileManager.getTile("grass_corner01");
            case 80 -> TileManager.getTile("grass_corner02");
            case 73 -> TileManager.getTile("grass_corner03");
            case 82 -> TileManager.getTile("grass_corner04");
            case 88 -> TileManager.getTile("grass_corner05");
            case 72 -> TileManager.getTile("grass_corner06");
            case 74 -> TileManager.getTile("grass_corner07");
            case 90 -> TileManager.getTile("grass_corner08");
            case 104 -> TileManager.getTile("level_floor01");
            case 105 -> TileManager.getTile("level_floor02");
            case 106 -> TileManager.getTile("level_floor03");
            case 298 -> TileManager.getTile("brick01");
            case 322 -> TileManager.getTile("brick02");
            case 225 -> TileManager.getTile("port01");
            case 233 -> TileManager.getTile("port02");
            case 496 -> TileManager.getTile("player_house_wall01_1");
            case 497 -> TileManager.getTile("player_house_wall01_2");
            case 498 -> TileManager.getTile("player_house_wall01_3");
            case 504 -> TileManager.getTile("player_house_wall02_1");
            case 505 -> TileManager.getTile("player_house_wall02_2");
            case 506 -> TileManager.getTile("player_house_wall02_3");
            case 560 -> TileManager.getTile("player_house_roof01");
            case 568 -> TileManager.getTile("player_house_roof02");
            case 576 -> TileManager.getTile("player_house_roof03");
            case 584 -> TileManager.getTile("player_house_roof04");
            case 366 -> TileManager.getTile("player_house_floor01");
            case 358 -> TileManager.getTile("player_house_floor02");
            case 448 -> TileManager.getTile("stone_house_wall01_1");
            case 449 -> TileManager.getTile("stone_house_wall01_2");
            case 450 -> TileManager.getTile("stone_house_wall01_3");
            case 456 -> TileManager.getTile("stone_house_wall02_1");
            case 457 -> TileManager.getTile("stone_house_wall02_2");
            case 458 -> TileManager.getTile("stone_house_wall02_3");
            case 564 -> TileManager.getTile("stone_house_roof01");
            case 572 -> TileManager.getTile("stone_house_roof02");
            case 580 -> TileManager.getTile("stone_house_roof03");
            case 588 -> TileManager.getTile("stone_house_roof04");
            case 528 -> TileManager.getTile("shop_wall01_1");
            case 529 -> TileManager.getTile("shop_wall01_2");
            case 530 -> TileManager.getTile("shop_wall01_3");
            case 536 -> TileManager.getTile("shop_wall02_1");
            case 537 -> TileManager.getTile("shop_wall02_2");
            case 538 -> TileManager.getTile("shop_wall02_3");
            case 561 -> TileManager.getTile("shop_roof01");
            case 569 -> TileManager.getTile("shop_roof02");
            case 577 -> TileManager.getTile("shop_roof03");
            case 585 -> TileManager.getTile("shop_roof04");
            case 928 -> TileManager.getTile("pool01_1");
            case 929 -> TileManager.getTile("pool01_2");
            case 930 -> TileManager.getTile("pool01_3");
            case 936 -> TileManager.getTile("pool02_1");
            case 937 -> TileManager.getTile("pool02_2");
            case 938 -> TileManager.getTile("pool02_3");
            case 944 -> TileManager.getTile("pool03_1");
            case 945 -> TileManager.getTile("pool03_2");
            case 946 -> TileManager.getTile("pool03_3");
            case 40 -> TileManager.getTile("bush");
            case 848 -> TileManager.getTile("bench01");
            case 849 -> TileManager.getTile("bench02");
            case 850 -> TileManager.getTile("bench03");
            case 262 -> TileManager.getTile("clothes_line01_1");
            case 263 -> TileManager.getTile("clothes_line01_2");
            case 270 -> TileManager.getTile("clothes_line01_3");
            case 271 -> TileManager.getTile("clothes_line01_4");
            case 278 -> TileManager.getTile("clothes_line02_1");
            case 279 -> TileManager.getTile("clothes_line02_2");
            case 286 -> TileManager.getTile("clothes_line02_3");
            case 287 -> TileManager.getTile("clothes_line02_4");
            case 909 -> TileManager.getTile("camber01");
            case 917 -> TileManager.getTile("camber02");
            case 925 -> TileManager.getTile("camber03");
            case 933 -> TileManager.getTile("camber04");
            case 941 -> TileManager.getTile("camber05");
            case 949 -> TileManager.getTile("camber06");
            case 717 -> TileManager.getTile("table_type01_1");
            case 718 -> TileManager.getTile("table_type01_2");
            case 719 -> TileManager.getTile("table_type01_3");
            case 725 -> TileManager.getTile("table_type01_4");
            case 726 -> TileManager.getTile("table_type01_5");
            case 727 -> TileManager.getTile("table_type01_6");
            case 733 -> TileManager.getTile("table_type01_7");
            case 734 -> TileManager.getTile("table_type01_8");
            case 735 -> TileManager.getTile("table_type01_9");
            case 858 -> TileManager.getTile("jar01");
            case 859 -> TileManager.getTile("jar02");
            case 184 -> TileManager.getTile("fence_x");
            case 176 -> TileManager.getTile("fence_y");
            case 178 -> TileManager.getTile("fence_top_left");
            case 179 -> TileManager.getTile("fence_top_right");
            case 187 -> TileManager.getTile("fence_bot_right");
            case 186 -> TileManager.getTile("fence_bot_left");
            case 180 -> TileManager.getTile("fence_end_left");
            case 188 -> TileManager.getTile("fence_end_right");
            case 177 -> TileManager.getTile("fence_end_top");
            case 328 -> TileManager.getTile("stair_top_left");
            case 330 -> TileManager.getTile("stair_top_right");
            case 336 -> TileManager.getTile("stair_mid_left");
            case 337 -> TileManager.getTile("stair_mid_cen");
            case 338 -> TileManager.getTile("stair_mid_right");
            case 344 -> TileManager.getTile("stair_bot_left");
            case 345 -> TileManager.getTile("stair_bot_cen");
            case 346 -> TileManager.getTile("stair_bot_right");
//            case 503 -> TileManager.getTile("player_house_door01");
//            case 511 -> TileManager.getTile("player_house_door02");
            case 880 -> TileManager.getTile("straws01");
            case 881 -> TileManager.getTile("straws02");
            case 888 -> TileManager.getTile("straws03");
            case 889 -> TileManager.getTile("straws04");
            case 896 -> TileManager.getTile("straws05");
            case 897 -> TileManager.getTile("straws06");
            case 375 -> TileManager.getTile("shop_door01");
            case 383 -> TileManager.getTile("shop_door02");
            case 50 -> TileManager.getTile("stone_house_door01");
            case 58 -> TileManager.getTile("stone_house_door02");
            case 660 -> TileManager.getTile("stone_house_tag");
            case 884 -> TileManager.getTile("barrel01");
            case 885 -> TileManager.getTile("barrel02");
            case 892 -> TileManager.getTile("barrel03");
            case 893 -> TileManager.getTile("barrel04");
            case 900 -> TileManager.getTile("barrel05");
            case 901 -> TileManager.getTile("barrel06");
            case 597 -> TileManager.getTile("window01");
            case 605 -> TileManager.getTile("window02");
            case 230 -> TileManager.getTile("mission_board01");
            case 231 -> TileManager.getTile("mission_board02");
            case 238 -> TileManager.getTile("mission_board03");
            case 239 -> TileManager.getTile("mission_board04");
            case 235 -> TileManager.getTile("sign01");
            case 229 -> TileManager.getTile("sign02");
            case 237 -> TileManager.getTile("sign03");
            case 228 -> TileManager.getTile("post_box");
            case 860 -> TileManager.getTile("wooden_chest");
            case 226 -> TileManager.getTile("water_pit");
            case 227 -> TileManager.getTile("bucket");
            case 10 -> TileManager.getTile("tree01");
            case 11 -> TileManager.getTile("tree02");
            case 18 -> TileManager.getTile("tree03");
            case 19 -> TileManager.getTile("tree04");
            case 882 -> TileManager.getTile("sacks01");
            case 883 -> TileManager.getTile("sacks02");
            case 890 -> TileManager.getTile("sacks03");
            case 891 -> TileManager.getTile("sacks04");
            case 898 -> TileManager.getTile("sacks05");
            case 899 -> TileManager.getTile("sacks06");
            case 886 -> TileManager.getTile("box01");
            case 887 -> TileManager.getTile("box02");
            case 894 -> TileManager.getTile("box03");
            case 895 -> TileManager.getTile("box04");
            case 902 -> TileManager.getTile("box05");
            case 903 -> TileManager.getTile("box06");
            case 911 -> TileManager.getTile("small_tree01");
            case 919 -> TileManager.getTile("small_tree02");
            case 943 -> TileManager.getTile("flower");
            case 958 -> TileManager.getTile("shop_sign");
            case 872 -> TileManager.getTile("sack");
            case 2054 -> TileManager.getTile("water");

            case 598 -> TileManager.getTile("window_indoor01");
            case 606 -> TileManager.getTile("window_indoor02");
            case 769 -> TileManager.getTile("stove");
            case 908 -> TileManager.getTile("statue01_1");
            case 916 -> TileManager.getTile("statue01_2");
            case 923 -> TileManager.getTile("statue02_1");
            case 931 -> TileManager.getTile("statue02_2");
            case 770 -> TileManager.getTile("sink");
            case 771 -> TileManager.getTile("shelf");
            case 976 -> TileManager.getTile("map");
            case 983 -> TileManager.getTile("ham");
            case 966 -> TileManager.getTile("food");
            case 809 -> TileManager.getTile("fire_place01");
            case 816 -> TileManager.getTile("fire_place02");
            case 817 -> TileManager.getTile("fire_place03");
            case 818 -> TileManager.getTile("fire_place04");
            case 824 -> TileManager.getTile("fire_place05");
            case 825 -> TileManager.getTile("fire_place06");
            case 826 -> TileManager.getTile("fire_place07");
            case 676 -> TileManager.getTile("couter01_1");
            case 684 -> TileManager.getTile("couter01_2");
            case 692 -> TileManager.getTile("couter01_3");
            case 677 -> TileManager.getTile("couter02_1");
            case 678 -> TileManager.getTile("couter02_2");
            case 679 -> TileManager.getTile("couter02_3");
            case 685 -> TileManager.getTile("couter03_1");
            case 686 -> TileManager.getTile("couter03_2");
            case 693 -> TileManager.getTile("couter03_3");
            case 694 -> TileManager.getTile("couter03_4");
            case 810 -> TileManager.getTile("clock");
            case 724 -> TileManager.getTile("chair_type01");
            case 754 -> TileManager.getTile("chair_type02");
            case 744 -> TileManager.getTile("bookshelf01");
            case 745 -> TileManager.getTile("bookshelf02");
            case 752 -> TileManager.getTile("bookshelf03");
            case 753 -> TileManager.getTile("bookshelf04");
            case 743 -> TileManager.getTile("table_type02_3");
            case 759 -> TileManager.getTile("table_type02_9");
            case 30 -> TileManager.getTile("shadow_left");
            case 45 -> TileManager.getTile("light_top_down");
            case 46 -> TileManager.getTile("light_left_right");
            case 37 -> TileManager.getTile("light_right_left");

            case 510 -> TileManager.getTile("floor_player_room");

            case 293 -> TileManager.getTile("transparent");
            default -> null;
        };
    }
}
