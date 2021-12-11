package tile;

import main.Map;

import java.awt.*;

public class TileManager {
    Map map;
    public static Tile[] tile;
    public boolean walkable;
    public TileManager(Map map){

        this.map = map;
        tile = new Tile[200];

        loadTexture();
    }

    public void loadTexture(){
        tile[0] =new Tile("src/resource/tile/water/water.png");

        tile[1] =new Tile("src/resource/tile/sand/sand.png", false);

        tile[2] =new Tile("src/resource/tile/grass/grass01.png", false);

        tile[3] =new Tile("src/resource/tile/grass/grass02.png", false);

        tile[4] =new Tile("src/resource/tile/grass/grass03.png", false);

        tile[5] =new Tile("src/resource/tile/grass/grass04.png", false);

        tile[6] =new Tile("src/resource/tile/grass/grass05.png", false);

        tile[7] =new Tile("src/resource/tile/grass/grass06.png", false);

        tile[8] =new Tile("src/resource/tile/grass/grass07.png", false);

        tile[9] =new Tile("src/resource/tile/grass/grass08.png", false);

        tile[10] =new Tile("src/resource/tile/road/road01.png", false);

        tile[11] =new Tile("src/resource/tile/road/road02.png", false);

        tile[12] =new Tile("src/resource/tile/road/road03.png", false);

        tile[13] =new Tile("src/resource/tile/road/road04.png", false);

        tile[14] =new Tile("src/resource/tile/road/road05.png", false);

        tile[15] =new Tile("src/resource/tile/road/road06.png", false);

        tile[16] =new Tile("src/resource/tile/road/road07.png", false);

        tile[17] =new Tile("src/resource/tile/road/road08.png", false);

        tile[18] =new Tile("src/resource/tile/road/road.png", false);

        tile[19] =new Tile("src/resource/tile/grass/grass_corner01.png", false);

        tile[20] =new Tile("src/resource/tile/grass/grass_corner02.png", false);

        tile[21] =new Tile("src/resource/tile/grass/grass_corner03.png", false);

        tile[22] =new Tile("src/resource/tile/grass/grass_corner04.png", false);

        tile[23] =new Tile("src/resource/tile/grass/grass_corner05.png", false);

        tile[24] =new Tile("src/resource/tile/grass/grass_corner06.png", false);

        tile[25] =new Tile("src/resource/tile/grass/grass_corner07.png", false);

        tile[26] =new Tile("src/resource/tile/grass/grass_corner08.png", false);

        tile[27] =new Tile("src/resource/tile/level_floor/level_floor01.png");

        tile[28] =new Tile("src/resource/tile/level_floor/level_floor02.png");

        tile[29] =new Tile("src/resource/tile/level_floor/level_floor03.png");

        tile[30] =new Tile("src/resource/tile/brick/brick01.png", false);

        tile[31] =new Tile("src/resource/tile/brick/brick02.png", false);

        tile[32] =new Tile("src/resource/tile/port/port01.png", false);

        tile[33] =new Tile("src/resource/tile/port/port02.png", false);

        tile[34] =new Tile("src/resource/tile/house/player_house/wall01_1.png");

        tile[35] =new Tile("src/resource/tile/house/player_house/wall01_2.png");

        tile[36] =new Tile("src/resource/tile/house/player_house/wall01_3.png");

        tile[37] =new Tile("src/resource/tile/house/player_house/wall02_1.png");

        tile[38] =new Tile("src/resource/tile/house/player_house/wall02_2.png");

        tile[39] =new Tile("src/resource/tile/house/player_house/wall02_3.png");

        tile[40] =new Tile("src/resource/tile/house/player_house/roof01.png");

        tile[41] =new Tile("src/resource/tile/house/player_house/roof02.png");

        tile[42] =new Tile("src/resource/tile/house/player_house/roof03.png");

        tile[43] =new Tile("src/resource/tile/house/player_house/roof04.png");

        tile[44] =new Tile("src/resource/tile/house/player_house/floor01.png", false);

        tile[45] =new Tile("src/resource/tile/house/player_house/floor02.png", false);

        tile[46] =new Tile("src/resource/tile/house/stone_house/wall01_1.png");

        tile[47] =new Tile("src/resource/tile/house/stone_house/wall01_2.png");

        tile[48] =new Tile("src/resource/tile/house/stone_house/wall01_3.png");

        tile[49] =new Tile("src/resource/tile/house/stone_house/wall02_1.png");

        tile[50] =new Tile("src/resource/tile/house/stone_house/wall02_2.png");

        tile[51] =new Tile("src/resource/tile/house/stone_house/wall02_3.png");

        tile[52] =new Tile("src/resource/tile/house/stone_house/roof01.png");

        tile[53] =new Tile("src/resource/tile/house/stone_house/roof02.png");

        tile[54] =new Tile("src/resource/tile/house/stone_house/roof03.png");

        tile[55] =new Tile("src/resource/tile/house/stone_house/roof04.png");

        tile[56] =new Tile("src/resource/tile/house/shop/wall01_1.png");

        tile[57] =new Tile("src/resource/tile/house/shop/wall01_2.png");

        tile[58] =new Tile("src/resource/tile/house/shop/wall01_3.png");

        tile[59] =new Tile("src/resource/tile/house/shop/wall02_1.png");

        tile[60] =new Tile("src/resource/tile/house/shop/wall02_2.png");

        tile[61] =new Tile("src/resource/tile/house/shop/wall02_3.png");

        tile[62] =new Tile("src/resource/tile/house/shop/roof01.png");

        tile[63] =new Tile("src/resource/tile/house/shop/roof02.png");

        tile[64] =new Tile("src/resource/tile/house/shop/roof03.png");

        tile[65] =new Tile("src/resource/tile/house/shop/roof04.png");

        tile[66] =new Tile("src/resource/tile/pool/pool01_1.png");

        tile[67] =new Tile("src/resource/tile/pool/pool01_2.png");

        tile[68] =new Tile("src/resource/tile/pool/pool01_3.png");

        tile[69] =new Tile("src/resource/tile/pool/pool02_1.png");

        tile[70] =new Tile("src/resource/tile/pool/pool02_2.png");

        tile[71] =new Tile("src/resource/tile/pool/pool02_3.png");

        tile[72] =new Tile("src/resource/tile/pool/pool03_1.png");

        tile[73] =new Tile("src/resource/tile/pool/pool03_2.png");

        tile[74] =new Tile("src/resource/tile/pool/pool03_3.png");

        tile[75] =new Tile("src/resource/tile/tree/bush.png");

        tile[76] =new Tile("src/resource/tile/chair/bench01.png");

        tile[77] =new Tile("src/resource/tile/chair/bench02.png");

        tile[78] =new Tile("src/resource/tile/chair/bench03.png");

        tile[79] =new Tile("src/resource/tile/drying/drying01_1.png");

        tile[80] =new Tile("src/resource/tile/drying/drying01_2.png");

        tile[81] =new Tile("src/resource/tile/drying/drying01_3.png");

        tile[82] =new Tile("src/resource/tile/drying/drying01_4.png");

        tile[83] =new Tile("src/resource/tile/drying/drying02_1.png");

        tile[84] =new Tile("src/resource/tile/drying/drying02_2.png");

        tile[85] =new Tile("src/resource/tile/drying/drying02_3.png");

        tile[86] =new Tile("src/resource/tile/drying/drying02_4.png");

        tile[87] =new Tile("src/resource/tile/camber/camber01.png");

        tile[88] =new Tile("src/resource/tile/camber/camber02.png");

        tile[89] =new Tile("src/resource/tile/camber/camber03.png");

        tile[90] =new Tile("src/resource/tile/camber/camber04.png");

        tile[91] =new Tile("src/resource/tile/camber/camber05.png");

        tile[92] =new Tile("src/resource/tile/camber/camber06.png");

        tile[93] =new Tile("src/resource/tile/table/table01_1.png");

        tile[94] =new Tile("src/resource/tile/table/table01_2.png");

        tile[95] =new Tile("src/resource/tile/table/table01_3.png");

        tile[96] =new Tile("src/resource/tile/table/table02_1.png");

        tile[97] =new Tile("src/resource/tile/table/table02_2.png");

        tile[98] =new Tile("src/resource/tile/table/table02_3.png");

        tile[99] =new Tile("src/resource/tile/table/table03_1.png");

        tile[100] =new Tile("src/resource/tile/table/table03_2.png");

        tile[101] =new Tile("src/resource/tile/table/table03_3.png");

        tile[102] =new Tile("src/resource/tile/prop/jar01.png");

        tile[103] =new Tile("src/resource/tile/prop/jar02.png");

        tile[104] =new Tile("src/resource/tile/fence/fence_x.png");

        tile[105] =new Tile("src/resource/tile/fence/fence_y.png");

        tile[106] =new Tile("src/resource/tile/fence/fence_top_left.png");

        tile[107] =new Tile("src/resource/tile/fence/fence_top_right.png");

        tile[108] =new Tile("src/resource/tile/fence/fence_bot_right.png");

        tile[109] =new Tile("src/resource/tile/fence/fence_bot_left.png");

        tile[110] =new Tile("src/resource/tile/fence/fence_end_left.png");

        tile[111] =new Tile("src/resource/tile/fence/fence_end_right.png");

        tile[112] =new Tile("src/resource/tile/fence/fence_end_top.png");

        tile[113] =new Tile("src/resource/tile/stair/stair01_1.png");

        tile[114] =new Tile("src/resource/tile/stair/stair01_2.png", false);

        tile[115] =new Tile("src/resource/tile/stair/stair02_1.png");

        tile[116] =new Tile("src/resource/tile/stair/stair02_2.png", false);

        tile[117] =new Tile("src/resource/tile/stair/stair02_3.png");

        tile[118] =new Tile("src/resource/tile/stair/stair03_1.png");

        tile[119] =new Tile("src/resource/tile/stair/stair03_2.png", false);

        tile[120] =new Tile("src/resource/tile/stair/stair03_3.png");

        tile[121] =new Tile("src/resource/tile/house/player_house/door01.png");

        tile[122] =new Tile("src/resource/tile/house/player_house/door02.png");

        tile[123] =new Tile("src/resource/tile/straw/straws01.png");

        tile[124] =new Tile("src/resource/tile/straw/straws02.png");

        tile[125] =new Tile("src/resource/tile/straw/straws03.png");

        tile[126] =new Tile("src/resource/tile/straw/straws04.png");

        tile[127] =new Tile("src/resource/tile/straw/straws05.png");

        tile[128] =new Tile("src/resource/tile/straw/straws06.png");

        tile[129] =new Tile("src/resource/tile/house/shop/door01.png");

        tile[130] =new Tile("src/resource/tile/house/shop/door02.png");

        tile[131] =new Tile("src/resource/tile/house/stone_house/door01.png");

        tile[132] =new Tile("src/resource/tile/house/stone_house/door02.png");

        tile[133] =new Tile("src/resource/tile/house/stone_house/tag.png");

        tile[134] =new Tile("src/resource/tile/barrel/barrel01.png");

        tile[135] =new Tile("src/resource/tile/barrel/barrel02.png");

        tile[136] =new Tile("src/resource/tile/barrel/barrel03.png");

        tile[137] =new Tile("src/resource/tile/barrel/barrel04.png");

        tile[138] =new Tile("src/resource/tile/barrel/barrel05.png");

        tile[139] =new Tile("src/resource/tile/barrel/barrel06.png");

        tile[140] =new Tile("src/resource/tile/house/window/window01_1.png");

        tile[141] =new Tile("src/resource/tile/house/window/window01_2.png");

        tile[142] =new Tile("src/resource/tile/mission_board/mb01.png");

        tile[143] =new Tile("src/resource/tile/mission_board/mb02.png");

        tile[144] =new Tile("src/resource/tile/mission_board/mb03.png");

        tile[145] =new Tile("src/resource/tile/mission_board/mb04.png");

        tile[146] =new Tile("src/resource/tile/road/sign01.png");

        tile[147] =new Tile("src/resource/tile/road/sign02_1.png");

        tile[148] =new Tile("src/resource/tile/road/sign02_2.png");

        tile[149] =new Tile("src/resource/tile/house/post_box.png");

        tile[150] =new Tile("src/resource/tile/not_use.png");

        tile[151] =new Tile("src/resource/tile/pass.png");

    }



    public static Tile getTile(int index){
        return tile[index];
    }

    public static Tile getTileByRc(int rc){
        return switch (rc) {
            case 2054 -> TileManager.getTile(0);//water
            case 6 -> TileManager.getTile(1);//sand
            case 75 -> TileManager.getTile(2);//grass01
            case 76 -> TileManager.getTile(3);//grass02
            case 77 -> TileManager.getTile(4);//grass03
            case 78 -> TileManager.getTile(5);//grass04
            case 83 -> TileManager.getTile(6);//grass05
            case 84 -> TileManager.getTile(7);//grass06
            case 85 -> TileManager.getTile(8);//grass07
            case 86 -> TileManager.getTile(9);//grass08
            case 1 -> TileManager.getTile(10);//road01
            case 52 -> TileManager.getTile(11);//road02
            case 33 -> TileManager.getTile(12);//road03
            case 4 -> TileManager.getTile(13);//road04
            case 21 -> TileManager.getTile(14);//road05
            case 35 -> TileManager.getTile(15);//road06
            case 31 -> TileManager.getTile(16);//road07
            case 23 -> TileManager.getTile(17);//road08
            case 14 -> TileManager.getTile(18);//road
            case 89 -> TileManager.getTile(19);//grass_corner01
            case 80 -> TileManager.getTile(20);//grass_corner02
            case 73 -> TileManager.getTile(21);//grass_corner03
            case 82 -> TileManager.getTile(22);//grass_corner04
            case 88 -> TileManager.getTile(23);//grass_corner05
            case 72 -> TileManager.getTile(24);//grass_corner06
            case 74 -> TileManager.getTile(25);//grass_corner07
            case 90 -> TileManager.getTile(26);//grass_corner08
            case 104 -> TileManager.getTile(27);//level_floor01
            case 105 -> TileManager.getTile(28);//level_floor02
            case 106 -> TileManager.getTile(29);//level_floor03
            case 298 -> TileManager.getTile(30);//brick01
            case 322 -> TileManager.getTile(31);//brick02
            case 225 -> TileManager.getTile(32);//port01
            case 233 -> TileManager.getTile(33);//port02
            case 496 -> TileManager.getTile(34);//player_house_wall01_1
            case 497 -> TileManager.getTile(35);//player_house_wall01_2
            case 498 -> TileManager.getTile(36);//player_house_wall01_3
            case 504 -> TileManager.getTile(37);//player_house_wall02_1
            case 505 -> TileManager.getTile(38);//player_house_wall02_2
            case 506 -> TileManager.getTile(39);//player_house_wall02_3
            case 560 -> TileManager.getTile(40);//player_house_roof01
            case 568 -> TileManager.getTile(41);//player_house_roof02
            case 576 -> TileManager.getTile(42);//player_house_roof03
            case 584 -> TileManager.getTile(43);//player_house_roof04
            case 366 -> TileManager.getTile(44);//player_house_floor01
            case 358 -> TileManager.getTile(45);//player_house_floor02
            case 448 -> TileManager.getTile(46);//stone_house_wall01_1
            case 449 -> TileManager.getTile(47);//stone_house_wall01_2
            case 450 -> TileManager.getTile(48);//stone_house_wall01_3
            case 456 -> TileManager.getTile(49);//stone_house_wall02_1
            case 457 -> TileManager.getTile(50);//stone_house_wall02_2
            case 458 -> TileManager.getTile(51);//stone_house_wall02_3
            case 564 -> TileManager.getTile(52);//stone_house_roof01
            case 572 -> TileManager.getTile(53);//stone_house_roof02
            case 580 -> TileManager.getTile(54);//stone_house_roof03
            case 588 -> TileManager.getTile(55);//stone_house_roof04
            case 528 -> TileManager.getTile(56);//shop_wall01_1
            case 529 -> TileManager.getTile(57);//shop_wall01_2
            case 530 -> TileManager.getTile(58);//shop_wall01_3
            case 536 -> TileManager.getTile(59);//shop_wall02_1
            case 537 -> TileManager.getTile(60);//shop_wall02_2
            case 538 -> TileManager.getTile(61);//shop_wall02_3
            case 561 -> TileManager.getTile(62);//shop_roof01
            case 569 -> TileManager.getTile(63);//shop_roof02
            case 577 -> TileManager.getTile(64);//shop_roof03
            case 585 -> TileManager.getTile(65);//shop_roof04
            case 928 -> TileManager.getTile(66);//pool01_1
            case 929 -> TileManager.getTile(67);//pool01_2
            case 930 -> TileManager.getTile(68);//pool01_3
            case 936 -> TileManager.getTile(69);//pool02_1
            case 937 -> TileManager.getTile(70);//pool02_2
            case 938 -> TileManager.getTile(71);//pool02_3
            case 944 -> TileManager.getTile(72);//pool03_1
            case 945 -> TileManager.getTile(73);//pool03_2
            case 946 -> TileManager.getTile(74);//pool03_3
            case 40 -> TileManager.getTile(75);//bush
            case 848 -> TileManager.getTile(76);//bench01
            case 849 -> TileManager.getTile(77);//bench02
            case 850 -> TileManager.getTile(78);//bench03
            case 262 -> TileManager.getTile(79);//drying01_1
            case 263 -> TileManager.getTile(80);//drying01_2
            case 270 -> TileManager.getTile(81);//drying01_3
            case 271 -> TileManager.getTile(82);//drying01_4
            case 278 -> TileManager.getTile(83);//drying02_1
            case 279 -> TileManager.getTile(84);//drying02_2
            case 286-> TileManager.getTile(85);//drying02_3
            case 287 -> TileManager.getTile(86);//drying02_4
            case 909 -> TileManager.getTile(87);//camber01
            case 917 -> TileManager.getTile(88);//camber02
            case 925 -> TileManager.getTile(89);//camber03
            case 933 -> TileManager.getTile(90);//camber04
            case 941 -> TileManager.getTile(91);//camber05
            case 949 -> TileManager.getTile(92);//camber06
            case 717 -> TileManager.getTile(93);//table01_1
            case 718 -> TileManager.getTile(94);//table01_2
            case 719-> TileManager.getTile(95);//table01_3
            case 725 -> TileManager.getTile(96);//table02_1
            case 726 -> TileManager.getTile(97);//table02_2
            case 727 -> TileManager.getTile(98);//table02_3
            case 733 -> TileManager.getTile(99);//table03_1
            case 734 -> TileManager.getTile(100);//table03_2
            case 735 -> TileManager.getTile(101);//table03_3
            case 858 -> TileManager.getTile(102);//jar01
            case 859 -> TileManager.getTile(103);//jar02
            case 184 -> TileManager.getTile(104);//fence_x
            case 176 -> TileManager.getTile(105);//fence_y
            case 178 -> TileManager.getTile(106);//fence_top_left
            case 179 -> TileManager.getTile(107);//fence_top_right
            case 187 -> TileManager.getTile(108);//fence_bot_right
            case 186 -> TileManager.getTile(109);//fence_bot_left
            case 180 -> TileManager.getTile(110);//fence_end_left
            case 188 -> TileManager.getTile(111);//fence_end_right
            case 177 -> TileManager.getTile(112);//fence_end_top
            case 328 -> TileManager.getTile(113);//stair_top_left
            case 330 -> TileManager.getTile(114);//stair_top_right
            case 336 -> TileManager.getTile(115);//stair_mid_left
            case 337 -> TileManager.getTile(116);//stair_mid_cen
            case 338 -> TileManager.getTile(117);//stair_mid_right
            case 344 -> TileManager.getTile(118);//stair_bot_left
            case 345 -> TileManager.getTile(119);//stair_bot_cen
            case 346 -> TileManager.getTile(120);//stair_bot_right
            case 503 -> TileManager.getTile(121);//player_house_door01
            case 511 -> TileManager.getTile(122);//player_house_door02
            case 880 -> TileManager.getTile(123);//straws01
            case 881 -> TileManager.getTile(124);//straws02
            case 888 -> TileManager.getTile(125);//straws03
            case 889 -> TileManager.getTile(126);//straws04
            case 896 -> TileManager.getTile(127);//straws05
            case 897 -> TileManager.getTile(128);//straws06
            case 375 -> TileManager.getTile(129);//shop_door01
            case 383 -> TileManager.getTile(130);//shop_door02
            case 50 -> TileManager.getTile(131);//stone_house_door01
            case 58 -> TileManager.getTile(132);//stone_house_door02
            case 660 -> TileManager.getTile(133);//stone_house_tag
            case 884 -> TileManager.getTile(134);//barrel01
            case 885 -> TileManager.getTile(135);//barrel02
            case 892 -> TileManager.getTile(136);//barrel03
            case 893 -> TileManager.getTile(137);//barrel04
            case 900 -> TileManager.getTile(138);//barrel05
            case 901 -> TileManager.getTile(139);//barrel06
            case 597 -> TileManager.getTile(140);//window01
            case 605 -> TileManager.getTile(141);//window02
            case 230 -> TileManager.getTile(142);//mission_board01
            case 231 -> TileManager.getTile(143);//mission_board02
            case 238 -> TileManager.getTile(144);//mission_board03
            case 239 -> TileManager.getTile(145);//mission_board04
            case 235 -> TileManager.getTile(146);//sign01
            case 229 -> TileManager.getTile(147);//sign02_01
            case 237 -> TileManager.getTile(148);//sign02_02
            case 228 -> TileManager.getTile(149);//post_box
            default -> null;
        };
    }

//    public static boolean checkCollsion(TileMap[] tileMaps, int row, int col){
//        boolean state = false;
//        for(int layer = 0; layer<tileMaps.length; layer++){
//            Tile tile = TileManager.getTileByRc(tileMaps[layer].map[row][col]);
//            if(tile != null){
//                if(tile.isCollision()){
//                    state = true;
//
//                }
//                else if((state) && (!tile.isCollision())){
//                    state = false;
//
//                }
//            }
//
//        }
//        System.out.println("\u001B[32m checked isCollision: "+state+"\u001B[0m");
//
//        return state;
//    }

}
