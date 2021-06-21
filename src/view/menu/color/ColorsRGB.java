package view.menu.color;

public enum ColorsRGB {
    HEALTH5(0, 255, 127),
    HEALTH4(59, 202, 109),
    HEALTH3(119, 148, 92),
    HEALTH2(178, 95, 74),
    HEALTH1(237, 41, 56),
    FOUR_CAGES_LEFT(254, 67, 60),
    THREE_CAGES_LEFT(243, 29, 100),
    TWO_CAGES_LEFT(162, 36, 173),
    ONE_CAGE_LEFT(106, 56, 179);

    private final int R;
    private final int G;
    private final int B;

    ColorsRGB(int R, int G, int B) {
        this.R = R;
        this.G = G;
        this.B = B;
    }

    public String getRGB() { return R + ";" + G + ";" + B; }
}
