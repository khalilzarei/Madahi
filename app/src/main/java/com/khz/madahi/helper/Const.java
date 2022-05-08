package com.khz.madahi.helper;


final public class Const {

    public enum ContentType {
        NOHEH,
        ROZEH
    }

    public static final String DB_NAME    = "Madahi.db";
    public static final String CURTAIN_IN = "Curtain/In";
    public static final String DALI_IN    = "Dali/In";


    public static final String TABLE_NAME_CONTENT      = "table_name_content";
    public static final String TABLE_NAME_CONTENT_TYPE = "table_name_content_type";
    public static final String TABLE_NAME_CATEGORIES   = "table_name_categories";
    public static final String TABLE_NAME_ROOM         = "table_name_room";
    public static final String TABLE_NAME_FAVORITE     = "table_name_favorite";

    public static final String SERVER_URL     = "http://192.168.1.104/";
    public static final String BASE_URL       = SERVER_URL + "Madahi/api/";
    public static final String IMAGE_BASE_URL = SERVER_URL + "image/";

    // values have to be globally unique
    public static final String INTENT_ACTION_DISCONNECT   = "com.smart.home.Disconnect";
    public static final String NOTIFICATION_CHANNEL       = "com.smart.home.Channel";
    public static final String INTENT_CLASS_MAIN_ACTIVITY = "com.smart.home.TerminalActivity";

}
