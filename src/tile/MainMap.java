package tile;

import entity.Player;
import main.Map;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MainMap {
    TileMap[] tileMaps;

    public MainMap(String[] layers) {
        tileMaps = new TileMap[layers.length];
        int i = 0;
        for (String layer : layers) {
            tileMaps[i++] = new TileMap(layer);
        }
    }

    public BufferedImage getImage(int rc){
        return switch (rc) {
            case 2054 -> TileManager.getTile("water").getImage();
            case 6 -> TileManager.getTile("sand").getImage();
            case 75 -> TileManager.getTile("grass01").getImage();
            case 76 -> TileManager.getTile("grass02").getImage();
            case 77 -> TileManager.getTile("grass03").getImage();
            case 78 -> TileManager.getTile("grass04").getImage();
            case 83 -> TileManager.getTile("grass05").getImage();
            case 84 -> TileManager.getTile("grass06").getImage();
            case 85 -> TileManager.getTile("grass07").getImage();
            case 86 -> TileManager.getTile("grass08").getImage();
            case 1 -> TileManager.getTile("road01").getImage();
            case 52 -> TileManager.getTile("road02").getImage();
            case 33 -> TileManager.getTile("road03").getImage();
            case 4 -> TileManager.getTile("road04").getImage();
            case 21 -> TileManager.getTile("road05").getImage();
            case 35 -> TileManager.getTile("road06").getImage();
            case 31 -> TileManager.getTile("road07").getImage();
            case 23 -> TileManager.getTile("road08").getImage();
            case 14 -> TileManager.getTile("road").getImage();
            case 89 -> TileManager.getTile("grass_corner01").getImage();
            case 80 -> TileManager.getTile("grass_corner02").getImage();
            case 73 -> TileManager.getTile("grass_corner03").getImage();
            case 82 -> TileManager.getTile("grass_corner04").getImage();
            case 88 -> TileManager.getTile("grass_corner05").getImage();
            case 72 -> TileManager.getTile("grass_corner06").getImage();
            case 74 -> TileManager.getTile("grass_corner07").getImage();
            case 90 -> TileManager.getTile("grass_corner08").getImage();
            case 104 -> TileManager.getTile("level_floor01").getImage();
            case 105 -> TileManager.getTile("level_floor02").getImage();
            case 106 -> TileManager.getTile("level_floor03").getImage();
            case 298 -> TileManager.getTile("brick01").getImage();
            case 322 -> TileManager.getTile("brick02").getImage();
            case 225 -> TileManager.getTile("port01").getImage();
            case 233 -> TileManager.getTile("port02").getImage();
            case 496 -> TileManager.getTile("player_house_wall01_1").getImage();
            case 497 -> TileManager.getTile("player_house_wall01_2").getImage();
            case 498 -> TileManager.getTile("player_house_wall01_3").getImage();
            case 504 -> TileManager.getTile("player_house_wall02_1").getImage();
            case 505 -> TileManager.getTile("player_house_wall02_2").getImage();
            case 506 -> TileManager.getTile("player_house_wall02_3").getImage();
            case 560 -> TileManager.getTile("player_house_roof01").getImage();
            case 568 -> TileManager.getTile("player_house_roof02").getImage();
            case 576 -> TileManager.getTile("player_house_roof03").getImage();
            case 584 -> TileManager.getTile("player_house_roof04").getImage();
            case 366 -> TileManager.getTile("player_house_floor01").getImage();
            case 358 -> TileManager.getTile("player_house_floor02").getImage();
            case 448 -> TileManager.getTile("stone_house_wall01_1").getImage();
            case 449 -> TileManager.getTile("stone_house_wall01_2").getImage();
            case 450 -> TileManager.getTile("stone_house_wall01_3").getImage();
            case 456 -> TileManager.getTile("stone_house_wall02_1").getImage();
            case 457 -> TileManager.getTile("stone_house_wall02_2").getImage();
            case 458 -> TileManager.getTile("stone_house_wall02_3").getImage();
            case 564 -> TileManager.getTile("stone_house_roof01").getImage();
            case 572 -> TileManager.getTile("stone_house_roof02").getImage();
            case 580 -> TileManager.getTile("stone_house_roof03").getImage();
            case 588 -> TileManager.getTile("stone_house_roof04").getImage();
            case 528 -> TileManager.getTile("shop_wall01_1").getImage();
            case 529 -> TileManager.getTile("shop_wall01_2").getImage();
            case 530 -> TileManager.getTile("shop_wall01_3").getImage();
            case 536 -> TileManager.getTile("shop_wall02_1").getImage();
            case 537 -> TileManager.getTile("shop_wall02_2").getImage();
            case 538 -> TileManager.getTile("shop_wall02_3").getImage();
            case 561 -> TileManager.getTile("shop_roof01").getImage();
            case 569 -> TileManager.getTile("shop_roof02").getImage();
            case 577 -> TileManager.getTile("shop_roof03").getImage();
            case 585 -> TileManager.getTile("shop_roof04").getImage();
            case 928 -> TileManager.getTile("pool01_1").getImage();
            case 929 -> TileManager.getTile("pool01_2").getImage();
            case 930 -> TileManager.getTile("pool01_3").getImage();
            case 936 -> TileManager.getTile("pool02_1").getImage();
            case 937 -> TileManager.getTile("pool02_2").getImage();
            case 938 -> TileManager.getTile("pool02_3").getImage();
            case 944 -> TileManager.getTile("pool03_1").getImage();
            case 945 -> TileManager.getTile("pool03_2").getImage();
            case 946 -> TileManager.getTile("pool03_3").getImage();
            case 40 -> TileManager.getTile("bush").getImage();
            case 848 -> TileManager.getTile("bench01").getImage();
            case 849 -> TileManager.getTile("bench02").getImage();
            case 850 -> TileManager.getTile("bench03").getImage();
            case 262 -> TileManager.getTile("clothes_line01_1").getImage();
            case 263 -> TileManager.getTile("clothes_line01_2").getImage();
            case 270 -> TileManager.getTile("clothes_line01_3").getImage();
            case 271 -> TileManager.getTile("clothes_line01_4").getImage();
            case 278 -> TileManager.getTile("clothes_line02_1").getImage();
            case 279 -> TileManager.getTile("clothes_line02_2").getImage();
            case 286 -> TileManager.getTile("clothes_line02_3").getImage();
            case 287 -> TileManager.getTile("clothes_line02_4").getImage();
            case 909 -> TileManager.getTile("camber01").getImage();
            case 917 -> TileManager.getTile("camber02").getImage();
            case 925 -> TileManager.getTile("camber03").getImage();
            case 933 -> TileManager.getTile("camber04").getImage();
            case 941 -> TileManager.getTile("camber05").getImage();
            case 949 -> TileManager.getTile("camber06").getImage();
            case 717 -> TileManager.getTile("table01_1").getImage();
            case 718 -> TileManager.getTile("table01_2").getImage();
            case 719 -> TileManager.getTile("table01_3").getImage();
            case 725 -> TileManager.getTile("table02_1").getImage();
            case 726 -> TileManager.getTile("table02_2").getImage();
            case 727 -> TileManager.getTile("table02_3").getImage();
            case 733 -> TileManager.getTile("table03_1").getImage();
            case 734 -> TileManager.getTile("table03_2").getImage();
            case 735 -> TileManager.getTile("table03_3").getImage();
            case 858 -> TileManager.getTile("jar01").getImage();
            case 859 -> TileManager.getTile("jar02").getImage();
            case 184 -> TileManager.getTile("fence_x").getImage();
            case 176 -> TileManager.getTile("fence_y").getImage();
            case 178 -> TileManager.getTile("fence_top_left").getImage();
            case 179 -> TileManager.getTile("fence_top_right").getImage();
            case 187 -> TileManager.getTile("fence_bot_right").getImage();
            case 186 -> TileManager.getTile("fence_bot_left").getImage();
            case 180 -> TileManager.getTile("fence_end_left").getImage();
            case 188 -> TileManager.getTile("fence_end_right").getImage();
            case 177 -> TileManager.getTile("fence_end_top").getImage();
            case 328 -> TileManager.getTile("stair_top_left").getImage();
            case 330 -> TileManager.getTile("stair_top_right").getImage();
            case 336 -> TileManager.getTile("stair_mid_left").getImage();
            case 337 -> TileManager.getTile("stair_mid_cen").getImage();
            case 338 -> TileManager.getTile("stair_mid_right").getImage();
            case 344 -> TileManager.getTile("stair_bot_left").getImage();
            case 345 -> TileManager.getTile("stair_bot_cen").getImage();
            case 346 -> TileManager.getTile("stair_bot_right").getImage();
            case 503 -> TileManager.getTile("player_house_door01").getImage();
            case 511 -> TileManager.getTile("player_house_door02").getImage();
            case 880 -> TileManager.getTile("straws01").getImage();
            case 881 -> TileManager.getTile("straws02").getImage();
            case 888 -> TileManager.getTile("straws03").getImage();
            case 889 -> TileManager.getTile("straws04").getImage();
            case 896 -> TileManager.getTile("straws05").getImage();
            case 897 -> TileManager.getTile("straws06").getImage();
            case 375 -> TileManager.getTile("shop_door01").getImage();
            case 383 -> TileManager.getTile("shop_door02").getImage();
            case 50 -> TileManager.getTile("stone_house_door01").getImage();
            case 58 -> TileManager.getTile("stone_house_door02").getImage();
            case 660 -> TileManager.getTile("stone_house_tag").getImage();
            case 884 -> TileManager.getTile("barrel01").getImage();
            case 885 -> TileManager.getTile("barrel02").getImage();
            case 892 -> TileManager.getTile("barrel03").getImage();
            case 893 -> TileManager.getTile("barrel04").getImage();
            case 900 -> TileManager.getTile("barrel05").getImage();
            case 901 -> TileManager.getTile("barrel06").getImage();
            case 597 -> TileManager.getTile("window01").getImage();
            case 605 -> TileManager.getTile("window02").getImage();
            case 230 -> TileManager.getTile("mission_board01").getImage();
            case 231 -> TileManager.getTile("mission_board02").getImage();
            case 238 -> TileManager.getTile("mission_board03").getImage();
            case 239 -> TileManager.getTile("mission_board04").getImage();
            case 235 -> TileManager.getTile("sign01").getImage();
            case 229 -> TileManager.getTile("sign02").getImage();
            case 237 -> TileManager.getTile("sign03").getImage();
            case 228 -> TileManager.getTile("post_box").getImage();
            default -> null;
        };
    }

    public void draw(Graphics2D g, int playerX, int playerY, int layer){
        for (int row = 0; row < tileMaps[layer].mapHeight; row++) {
            for (int col = 0; col < tileMaps[layer].mapWidth; col++) {
                int tile = tileMaps[layer].map[row][col];
                BufferedImage tileImage = this.getImage(tile);
                // draw tile in screen
                g.drawImage(tileImage,(col * Map.scaledTileSize) - playerX, (row * Map.scaledTileSize) - playerY, Map.scaledTileSize, Map.scaledTileSize, null);
            }
        }
    }

    public void render(Graphics2D g, Player player){
        for (int layer = 0; layer < tileMaps.length; layer++){
            // draw map in each layer
            this.draw(g, (int)player.getPixelPosX(), (int)player.getPixelPosY(), layer);
        }
    }
}
