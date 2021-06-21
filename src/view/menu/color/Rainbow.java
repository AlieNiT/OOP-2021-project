package view.menu.color;

import static model.game.missionmodel.MissionMap.MAP_SIZE;

public enum Rainbow {
    RED1(125),
    RED2(160),
    ORANGE1(202),
    ORANGE2(214),
    YELLOW1(11),
    YELLOW2(148),
    GREEN1(28),
    GREEN2(29),
    BLUE1(37),
    BLUE2(33),
    PURPLE1(63),
    PURPLE2(93),
    PINK1(165),
    PINK2(201),
    PINK3(199);

    private final int code;

    Rainbow(int code) {
        this.code = code;
    }

    public int getCode() { return code; }

    public static String mapRainbow(int i) {
        double ratio = (double) i/MAP_SIZE;
        int normalized = (int) (ratio * 256 * 6);
        int x = normalized % 256;
        int red = 0, green = 0, blue = 0;
        switch (normalized / 256) {
            case 0 -> { red = 255; green = x; blue = 0; }
            case 1 -> { red = 255 - x; green = 255; blue = 0; }
            case 2 -> { red = 0; green = 255; blue = x; }
            case 3 -> { red = 0; green = 255 - x; blue = 255; }
            case 4 -> { red = x; green = 0; blue = 255; }
            case 5 -> { red = 255; green = 0; blue = 255 - x; }
        }
        return "\u001b[38;2;" + red + ";" + green + ";" + blue +"m";
    }
}
