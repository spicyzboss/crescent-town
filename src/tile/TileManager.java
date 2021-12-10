package tile;

import main.Map;

public class TileManager {
    Map map;
    private static Tile[] tile;

    public TileManager(Map map){
        this.map = map;

        tile = new Tile[200];

        addTileImage();
    }
    public void addTileImage(){
        tile[0] =new Tile("src/resource/tile/water/water.png");

        tile[1] =new Tile("src/resource/tile/sand/sand.png");

        tile[2] =new Tile("src/resource/tile/grass/grass01.png");

        tile[3] =new Tile("src/resource/tile/grass/grass02.png");

        tile[4] =new Tile("src/resource/tile/grass/grass03.png");

        tile[5] =new Tile("src/resource/tile/grass/grass04.png");

        tile[6] =new Tile("src/resource/tile/grass/grass05.png");

        tile[7] =new Tile("src/resource/tile/grass/grass06.png");

        tile[8] =new Tile("src/resource/tile/grass/grass07.png");

        tile[9] =new Tile("src/resource/tile/grass/grass08.png");

        tile[10] =new Tile("src/resource/tile/road/road01.png");

        tile[11] =new Tile("src/resource/tile/road/road02.png");

        tile[12] =new Tile("src/resource/tile/road/road03.png");

        tile[13] =new Tile("src/resource/tile/road/road04.png");

        tile[14] =new Tile("src/resource/tile/road/road05.png");

        tile[15] =new Tile("src/resource/tile/road/road06.png");

        tile[16] =new Tile("src/resource/tile/road/road07.png");

        tile[17] =new Tile("src/resource/tile/road/road08.png");

        tile[18] =new Tile("src/resource/tile/road/road.png");

        tile[19] =new Tile("src/resource/tile/grass/grass_corner01.png");

        tile[20] =new Tile("src/resource/tile/grass/grass_corner02.png");

        tile[21] =new Tile("src/resource/tile/grass/grass_corner03.png");

        tile[22] =new Tile("src/resource/tile/grass/grass_corner04.png");

        tile[23] =new Tile("src/resource/tile/grass/grass_corner05.png");

        tile[24] =new Tile("src/resource/tile/grass/grass_corner06.png");

        tile[25] =new Tile("src/resource/tile/grass/grass_corner07.png");

        tile[26] =new Tile("src/resource/tile/grass/grass_corner08.png");

        tile[27] =new Tile("src/resource/tile/level_floor/level_floor01.png");

        tile[28] =new Tile("src/resource/tile/level_floor/level_floor02.png");

        tile[29] =new Tile("src/resource/tile/level_floor/level_floor03.png");

        tile[30] =new Tile("src/resource/tile/brick/brick01.png");

        tile[31] =new Tile("src/resource/tile/brick/brick02.png");

        tile[32] =new Tile("src/resource/tile/port/port01.png");

        tile[33] =new Tile("src/resource/tile/port/port02.png");

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

        tile[44] =new Tile("src/resource/tile/house/player_house/floor01.png");

        tile[45] =new Tile("src/resource/tile/house/player_house/floor02.png");

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

        tile[114] =new Tile("src/resource/tile/stair/stair01_2.png");

        tile[115] =new Tile("src/resource/tile/stair/stair02_1.png");

        tile[116] =new Tile("src/resource/tile/stair/stair02_2.png");

        tile[117] =new Tile("src/resource/tile/stair/stair02_3.png");

        tile[118] =new Tile("src/resource/tile/stair/stair03_1.png");

        tile[119] =new Tile("src/resource/tile/stair/stair03_2.png");

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
    }
    public static Tile getTile(int index){
        return tile[index];
    }

}
