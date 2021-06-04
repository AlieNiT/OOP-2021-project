package changes;

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
}
