package view.menu.color;

public enum GrassShades {
    DRY(137),
    WATER1(107),
    WATER2(65),
    WATER3(106),
    WATER4(64),
    WATER5(28);

    private final int code;
    GrassShades(int code) {
        this.code = code;
    }
    public int getCode() { return code; }
}
