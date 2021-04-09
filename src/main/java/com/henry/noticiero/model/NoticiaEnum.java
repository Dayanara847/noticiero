package com.henry.noticiero.model;

public enum NoticiaEnum {

    VIDEO("video"),
    IMAGE("image"),
    TEXT("text");

    private String description;

    NoticiaEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static NoticiaEnum find(String value) {
        for(NoticiaEnum noticiaEnum : values()) {
            if(noticiaEnum.toString().equalsIgnoreCase(value)) {
                return noticiaEnum;
            }
        }
        throw new IllegalArgumentException(String.format("Invalid NoticiaType: %s", value));
    }

}
