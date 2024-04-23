package model;

public enum RoomType {
    SINGLE(1),
    DOUBLE(2);

    public final Integer label;

    private RoomType(Integer label) {
        this.label = label;
    }

    public static RoomType getValueByLabel(Integer label) {
        for (RoomType roomType : values()) {
            if (label == roomType.label) {
                return roomType;
            }
        }
        throw new IllegalArgumentException();
    }
}