package com.xymiao.cms.enums;

public enum ResponseCode {
SUCCESS(0, "SUCCESS"),
ERROR(400, "ERROR"),
NEED_AUTH(401, "ERROR"),
NEED_LOGIN(10, "NEED_LOGIN"),
ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT");

private final int code;
private final String desc;

ResponseCode(int code, String desc) {
    this.code = code;
    this.desc = desc;
}

public int getCode() {
    return code;
}

public String getDesc() {
    return desc;
}
}