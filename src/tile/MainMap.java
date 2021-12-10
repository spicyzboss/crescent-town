package tile;

import main.Map;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MainMap {
    TileMap[] tileMaps;
    int tileSize;
    int posX = 25;
    int posY = 50;
    public MainMap(String[] layers, int tileSize) {
        this.tileSize = tileSize*2;
        tileMaps = new TileMap[layers.length];
        for(int layer = 0; layer<layers.length; layer++){
            try {
                tileMaps[layer] = new TileMap(layers[layer]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
//    public BufferedImage getMainLayer1(int rc){
//        return switch (rc) {
//            case 2054 -> TileManager.getTile(0).getImage();//water
//            case 6 -> TileManager.getTile(1).getImage();//sand
//            default -> null;
//        };
//    }
//    public BufferedImage getMainLayer2(int rc){
//        return switch (rc) {
//            case 75 -> TileManager.getTile(2).getImage();//grass01
//            case 76 -> TileManager.getTile(3).getImage();//grass02
//            case 77 -> TileManager.getTile(4).getImage();//grass03
//            case 78 -> TileManager.getTile(5).getImage();//grass04
//            case 83 -> TileManager.getTile(6).getImage();//grass05
//            case 84 -> TileManager.getTile(7).getImage();//grass06
//            case 85 -> TileManager.getTile(8).getImage();//grass07
//            case 86 -> TileManager.getTile(9).getImage();//grass08
//            case 1 -> TileManager.getTile(10).getImage();//road01
//            case 52 -> TileManager.getTile(11).getImage();//road02
//            case 33 -> TileManager.getTile(12).getImage();//road03
//            case 4 -> TileManager.getTile(13).getImage();//road04
//            case 21 -> TileManager.getTile(14).getImage();//road05
//            case 35 -> TileManager.getTile(15).getImage();//road06
//            case 31 -> TileManager.getTile(16).getImage();//road07
//            case 23 -> TileManager.getTile(17).getImage();//road08
//            case 14 -> TileManager.getTile(18).getImage();//road
//            case 89 -> TileManager.getTile(19).getImage();//grass_corner01
//            case 80 -> TileManager.getTile(20).getImage();//grass_corner02
//            case 73 -> TileManager.getTile(21).getImage();//grass_corner03
//            case 82 -> TileManager.getTile(22).getImage();//grass_corner04
//            case 88 -> TileManager.getTile(23).getImage();//grass_corner05
//            case 72 -> TileManager.getTile(24).getImage();//grass_corner06
//            case 74 -> TileManager.getTile(25).getImage();//grass_corner07
//            case 90 -> TileManager.getTile(26).getImage();//grass_corner08
//            case 104 -> TileManager.getTile(27).getImage();//level_floor01
//            case 105 -> TileManager.getTile(28).getImage();//level_floor02
//            case 106 -> TileManager.getTile(29).getImage();//level_floor03
//            case 298 -> TileManager.getTile(30).getImage();//brick01
//            case 322 -> TileManager.getTile(31).getImage();//brick02
//            case 225 -> TileManager.getTile(32).getImage();//port01
//            case 233 -> TileManager.getTile(33).getImage();//port02
//            default -> null;
//        };
//    }
//    public BufferedImage getMainLayer3(int rc){
//        return switch (rc) {
//            case 496 -> TileManager.getTile(34).getImage();//player_house_wall01_1
//            case 497 -> TileManager.getTile(35).getImage();//player_house_wall01_2
//            case 498 -> TileManager.getTile(36).getImage();//player_house_wall01_3
//            case 504 -> TileManager.getTile(37).getImage();//player_house_wall02_1
//            case 505 -> TileManager.getTile(38).getImage();//player_house_wall02_2
//            case 506 -> TileManager.getTile(39).getImage();//player_house_wall02_3
//            case 560 -> TileManager.getTile(40).getImage();//player_house_roof01
//            case 568 -> TileManager.getTile(41).getImage();//player_house_roof02
//            case 576 -> TileManager.getTile(42).getImage();//player_house_roof03
//            case 584 -> TileManager.getTile(43).getImage();//player_house_roof04
//            case 366 -> TileManager.getTile(44).getImage();//player_house_floor01
//            case 358 -> TileManager.getTile(45).getImage();//player_house_floor02
//            case 448 -> TileManager.getTile(46).getImage();//stone_house_wall01_1
//            case 449 -> TileManager.getTile(47).getImage();//stone_house_wall01_2
//            case 450 -> TileManager.getTile(48).getImage();//stone_house_wall01_3
//            case 456 -> TileManager.getTile(49).getImage();//stone_house_wall02_1
//            case 457 -> TileManager.getTile(50).getImage();//stone_house_wall02_2
//            case 458 -> TileManager.getTile(51).getImage();//stone_house_wall02_3
//            case 564 -> TileManager.getTile(52).getImage();//stone_house_roof01
//            case 572 -> TileManager.getTile(53).getImage();//stone_house_roof02
//            case 580 -> TileManager.getTile(54).getImage();//stone_house_roof03
//            case 588 -> TileManager.getTile(55).getImage();//stone_house_roof04
//            case 528 -> TileManager.getTile(56).getImage();//shop_wall01_1
//            case 529 -> TileManager.getTile(57).getImage();//shop_wall01_2
//            case 530 -> TileManager.getTile(58).getImage();//shop_wall01_3
//            case 536 -> TileManager.getTile(59).getImage();//shop_wall02_1
//            case 537 -> TileManager.getTile(60).getImage();//shop_wall02_2
//            case 538 -> TileManager.getTile(61).getImage();//shop_wall02_3
//            case 561 -> TileManager.getTile(62).getImage();//shop_roof01
//            case 569 -> TileManager.getTile(63).getImage();//shop_roof02
//            case 577 -> TileManager.getTile(64).getImage();//shop_roof03
//            case 585 -> TileManager.getTile(65).getImage();//shop_roof04
//            case 928 -> TileManager.getTile(66).getImage();//pool01_1
//            case 929 -> TileManager.getTile(67).getImage();//pool01_2
//            case 930 -> TileManager.getTile(68).getImage();//pool01_3
//            case 936 -> TileManager.getTile(69).getImage();//pool02_1
//            case 937 -> TileManager.getTile(70).getImage();//pool02_2
//            case 938 -> TileManager.getTile(71).getImage();//pool02_3
//            case 944 -> TileManager.getTile(72).getImage();//pool03_1
//            case 945 -> TileManager.getTile(73).getImage();//pool03_2
//            case 946 -> TileManager.getTile(74).getImage();//pool03_3
//            case 40 -> TileManager.getTile(75).getImage();//bush
//            case 848 -> TileManager.getTile(76).getImage();//bench01
//            case 849 -> TileManager.getTile(77).getImage();//bench02
//            case 850 -> TileManager.getTile(78).getImage();//bench03
//            case 262 -> TileManager.getTile(79).getImage();//drying01_1
//            case 263 -> TileManager.getTile(80).getImage();//drying01_2
//            case 270 -> TileManager.getTile(81).getImage();//drying01_3
//            case 271 -> TileManager.getTile(82).getImage();//drying01_4
//            case 278 -> TileManager.getTile(83).getImage();//drying02_1
//            case 279 -> TileManager.getTile(84).getImage();//drying02_2
//            case 286-> TileManager.getTile(85).getImage();//drying02_3
//            case 287 -> TileManager.getTile(86).getImage();//drying02_4
//            case 909 -> TileManager.getTile(87).getImage();//camber01
//            case 917 -> TileManager.getTile(88).getImage();//camber02
//            case 925 -> TileManager.getTile(89).getImage();//camber03
//            case 933 -> TileManager.getTile(90).getImage();//camber04
//            case 941 -> TileManager.getTile(91).getImage();//camber05
//            case 949 -> TileManager.getTile(92).getImage();//camber06
//            case 717 -> TileManager.getTile(93).getImage();//table01_1
//            case 718 -> TileManager.getTile(94).getImage();//table01_2
//            case 719-> TileManager.getTile(95).getImage();//table01_3
//            case 725 -> TileManager.getTile(96).getImage();//table02_1
//            case 726 -> TileManager.getTile(97).getImage();//table02_2
//            case 727 -> TileManager.getTile(98).getImage();//table02_3
//            case 733 -> TileManager.getTile(99).getImage();//table03_1
//            case 734 -> TileManager.getTile(100).getImage();//table03_2
//            case 735 -> TileManager.getTile(101).getImage();//table03_3
//            case 858 -> TileManager.getTile(102).getImage();//jar01
//            case 859 -> TileManager.getTile(103).getImage();//jar02
//            default -> null;
//        };
//    }

    public BufferedImage getImage(int rc){
        return switch (rc) {
            case 2054 -> TileManager.getTile(0).getImage();//water
            case 6 -> TileManager.getTile(1).getImage();//sand
            case 75 -> TileManager.getTile(2).getImage();//grass01
            case 76 -> TileManager.getTile(3).getImage();//grass02
            case 77 -> TileManager.getTile(4).getImage();//grass03
            case 78 -> TileManager.getTile(5).getImage();//grass04
            case 83 -> TileManager.getTile(6).getImage();//grass05
            case 84 -> TileManager.getTile(7).getImage();//grass06
            case 85 -> TileManager.getTile(8).getImage();//grass07
            case 86 -> TileManager.getTile(9).getImage();//grass08
            case 1 -> TileManager.getTile(10).getImage();//road01
            case 52 -> TileManager.getTile(11).getImage();//road02
            case 33 -> TileManager.getTile(12).getImage();//road03
            case 4 -> TileManager.getTile(13).getImage();//road04
            case 21 -> TileManager.getTile(14).getImage();//road05
            case 35 -> TileManager.getTile(15).getImage();//road06
            case 31 -> TileManager.getTile(16).getImage();//road07
            case 23 -> TileManager.getTile(17).getImage();//road08
            case 14 -> TileManager.getTile(18).getImage();//road
            case 89 -> TileManager.getTile(19).getImage();//grass_corner01
            case 80 -> TileManager.getTile(20).getImage();//grass_corner02
            case 73 -> TileManager.getTile(21).getImage();//grass_corner03
            case 82 -> TileManager.getTile(22).getImage();//grass_corner04
            case 88 -> TileManager.getTile(23).getImage();//grass_corner05
            case 72 -> TileManager.getTile(24).getImage();//grass_corner06
            case 74 -> TileManager.getTile(25).getImage();//grass_corner07
            case 90 -> TileManager.getTile(26).getImage();//grass_corner08
            case 104 -> TileManager.getTile(27).getImage();//level_floor01
            case 105 -> TileManager.getTile(28).getImage();//level_floor02
            case 106 -> TileManager.getTile(29).getImage();//level_floor03
            case 298 -> TileManager.getTile(30).getImage();//brick01
            case 322 -> TileManager.getTile(31).getImage();//brick02
            case 225 -> TileManager.getTile(32).getImage();//port01
            case 233 -> TileManager.getTile(33).getImage();//port02
            case 496 -> TileManager.getTile(34).getImage();//player_house_wall01_1
            case 497 -> TileManager.getTile(35).getImage();//player_house_wall01_2
            case 498 -> TileManager.getTile(36).getImage();//player_house_wall01_3
            case 504 -> TileManager.getTile(37).getImage();//player_house_wall02_1
            case 505 -> TileManager.getTile(38).getImage();//player_house_wall02_2
            case 506 -> TileManager.getTile(39).getImage();//player_house_wall02_3
            case 560 -> TileManager.getTile(40).getImage();//player_house_roof01
            case 568 -> TileManager.getTile(41).getImage();//player_house_roof02
            case 576 -> TileManager.getTile(42).getImage();//player_house_roof03
            case 584 -> TileManager.getTile(43).getImage();//player_house_roof04
            case 366 -> TileManager.getTile(44).getImage();//player_house_floor01
            case 358 -> TileManager.getTile(45).getImage();//player_house_floor02
            case 448 -> TileManager.getTile(46).getImage();//stone_house_wall01_1
            case 449 -> TileManager.getTile(47).getImage();//stone_house_wall01_2
            case 450 -> TileManager.getTile(48).getImage();//stone_house_wall01_3
            case 456 -> TileManager.getTile(49).getImage();//stone_house_wall02_1
            case 457 -> TileManager.getTile(50).getImage();//stone_house_wall02_2
            case 458 -> TileManager.getTile(51).getImage();//stone_house_wall02_3
            case 564 -> TileManager.getTile(52).getImage();//stone_house_roof01
            case 572 -> TileManager.getTile(53).getImage();//stone_house_roof02
            case 580 -> TileManager.getTile(54).getImage();//stone_house_roof03
            case 588 -> TileManager.getTile(55).getImage();//stone_house_roof04
            case 528 -> TileManager.getTile(56).getImage();//shop_wall01_1
            case 529 -> TileManager.getTile(57).getImage();//shop_wall01_2
            case 530 -> TileManager.getTile(58).getImage();//shop_wall01_3
            case 536 -> TileManager.getTile(59).getImage();//shop_wall02_1
            case 537 -> TileManager.getTile(60).getImage();//shop_wall02_2
            case 538 -> TileManager.getTile(61).getImage();//shop_wall02_3
            case 561 -> TileManager.getTile(62).getImage();//shop_roof01
            case 569 -> TileManager.getTile(63).getImage();//shop_roof02
            case 577 -> TileManager.getTile(64).getImage();//shop_roof03
            case 585 -> TileManager.getTile(65).getImage();//shop_roof04
            case 928 -> TileManager.getTile(66).getImage();//pool01_1
            case 929 -> TileManager.getTile(67).getImage();//pool01_2
            case 930 -> TileManager.getTile(68).getImage();//pool01_3
            case 936 -> TileManager.getTile(69).getImage();//pool02_1
            case 937 -> TileManager.getTile(70).getImage();//pool02_2
            case 938 -> TileManager.getTile(71).getImage();//pool02_3
            case 944 -> TileManager.getTile(72).getImage();//pool03_1
            case 945 -> TileManager.getTile(73).getImage();//pool03_2
            case 946 -> TileManager.getTile(74).getImage();//pool03_3
            case 40 -> TileManager.getTile(75).getImage();//bush
            case 848 -> TileManager.getTile(76).getImage();//bench01
            case 849 -> TileManager.getTile(77).getImage();//bench02
            case 850 -> TileManager.getTile(78).getImage();//bench03
            case 262 -> TileManager.getTile(79).getImage();//drying01_1
            case 263 -> TileManager.getTile(80).getImage();//drying01_2
            case 270 -> TileManager.getTile(81).getImage();//drying01_3
            case 271 -> TileManager.getTile(82).getImage();//drying01_4
            case 278 -> TileManager.getTile(83).getImage();//drying02_1
            case 279 -> TileManager.getTile(84).getImage();//drying02_2
            case 286-> TileManager.getTile(85).getImage();//drying02_3
            case 287 -> TileManager.getTile(86).getImage();//drying02_4
            case 909 -> TileManager.getTile(87).getImage();//camber01
            case 917 -> TileManager.getTile(88).getImage();//camber02
            case 925 -> TileManager.getTile(89).getImage();//camber03
            case 933 -> TileManager.getTile(90).getImage();//camber04
            case 941 -> TileManager.getTile(91).getImage();//camber05
            case 949 -> TileManager.getTile(92).getImage();//camber06
            case 717 -> TileManager.getTile(93).getImage();//table01_1
            case 718 -> TileManager.getTile(94).getImage();//table01_2
            case 719-> TileManager.getTile(95).getImage();//table01_3
            case 725 -> TileManager.getTile(96).getImage();//table02_1
            case 726 -> TileManager.getTile(97).getImage();//table02_2
            case 727 -> TileManager.getTile(98).getImage();//table02_3
            case 733 -> TileManager.getTile(99).getImage();//table03_1
            case 734 -> TileManager.getTile(100).getImage();//table03_2
            case 735 -> TileManager.getTile(101).getImage();//table03_3
            case 858 -> TileManager.getTile(102).getImage();//jar01
            case 859 -> TileManager.getTile(103).getImage();//jar02
            case 184 -> TileManager.getTile(104).getImage();//fence_x
            case 176 -> TileManager.getTile(105).getImage();//fence_y
            case 178 -> TileManager.getTile(106).getImage();//fence_top_left
            case 179 -> TileManager.getTile(107).getImage();//fence_top_right
            case 187 -> TileManager.getTile(108).getImage();//fence_bot_right
            case 186 -> TileManager.getTile(109).getImage();//fence_bot_left
            case 180 -> TileManager.getTile(110).getImage();//fence_end_left
            case 188 -> TileManager.getTile(111).getImage();//fence_end_right
            case 177 -> TileManager.getTile(112).getImage();//fence_end_top
            case 328 -> TileManager.getTile(113).getImage();//stair_top_left
            case 330 -> TileManager.getTile(114).getImage();//stair_top_right
            case 336 -> TileManager.getTile(115).getImage();//stair_mid_left
            case 337 -> TileManager.getTile(116).getImage();//stair_mid_cen
            case 338 -> TileManager.getTile(117).getImage();//stair_mid_right
            case 344 -> TileManager.getTile(118).getImage();//stair_bot_left
            case 345 -> TileManager.getTile(119).getImage();//stair_bot_cen
            case 346 -> TileManager.getTile(120).getImage();//stair_bot_right
            case 503 -> TileManager.getTile(121).getImage();//player_house_door01
            case 511 -> TileManager.getTile(122).getImage();//player_house_door02
            case 880 -> TileManager.getTile(123).getImage();//straws01
            case 881 -> TileManager.getTile(124).getImage();//straws02
            case 888 -> TileManager.getTile(125).getImage();//straws03
            case 889 -> TileManager.getTile(126).getImage();//straws04
            case 896 -> TileManager.getTile(127).getImage();//straws05
            case 897 -> TileManager.getTile(128).getImage();//straws06
            case 375 -> TileManager.getTile(129).getImage();//shop_door01
            case 383 -> TileManager.getTile(130).getImage();//shop_door02
            case 50 -> TileManager.getTile(131).getImage();//stone_house_door01
            case 58 -> TileManager.getTile(132).getImage();//stone_house_door02
            case 660 -> TileManager.getTile(133).getImage();//stone_house_tag
            case 884 -> TileManager.getTile(134).getImage();//barrel01
            case 885 -> TileManager.getTile(135).getImage();//barrel02
            case 892 -> TileManager.getTile(136).getImage();//barrel03
            case 893 -> TileManager.getTile(137).getImage();//barrel04
            case 900 -> TileManager.getTile(138).getImage();//barrel05
            case 901 -> TileManager.getTile(139).getImage();//barrel06
            case 597 -> TileManager.getTile(140).getImage();//window01
            case 605 -> TileManager.getTile(141).getImage();//window02
            case 230 -> TileManager.getTile(142).getImage();//mission_board01
            case 231 -> TileManager.getTile(143).getImage();//mission_board02
            case 238 -> TileManager.getTile(144).getImage();//mission_board03
            case 239 -> TileManager.getTile(145).getImage();//mission_board04
            case 235 -> TileManager.getTile(146).getImage();//sign01
            case 229 -> TileManager.getTile(147).getImage();//sign02_01
            case 237 -> TileManager.getTile(148).getImage();//sign02_02
            case 228 -> TileManager.getTile(149).getImage();//post_box
            default -> null;
        };
    }

    public void draw(Graphics2D g, int moveX, int moveY, int layer){
        int x = -(posX*tileSize+moveX);
        int y = -(posY*tileSize+moveY);
        for(int row = 0; row<tileMaps[layer].mapHeight; row++) {
            for (int col = 0; col < tileMaps[layer].mapWidth; col++) {
                int rc = tileMaps[layer].map[row][col];
                BufferedImage image = this.getImage(rc);
                g.drawImage(image,x+col*tileSize, y+row*tileSize, Map.scaledTileSize, Map.scaledTileSize, null);
            }
        }

    }
    public void render(Graphics2D g, int moveX, int moveY){
        for(int layer = 0; layer<tileMaps.length; layer++){
            draw(g, moveX, moveY, layer);
        }
    }
}
