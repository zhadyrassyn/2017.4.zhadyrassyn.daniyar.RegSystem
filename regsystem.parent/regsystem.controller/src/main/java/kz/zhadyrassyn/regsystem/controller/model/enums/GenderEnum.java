package kz.zhadyrassyn.regsystem.controller.model.enums;

import com.google.gson.annotations.SerializedName;

public enum GenderEnum {

    @SerializedName("1")
    MALE(1),

    @SerializedName("0")
    FEMALE (0);

    private final int value;
    public int getValue() {
        return value;
    }

    private GenderEnum(int value) {
        this.value = value;

    }
}
