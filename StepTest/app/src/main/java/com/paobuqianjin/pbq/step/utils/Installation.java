package com.paobuqianjin.pbq.step.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.model.FlagPreference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

/**
 * Created by pbq on 2018/7/31.
 */

public class Installation {
    private static String sID = null;
    private static final String INSTALLATION = "INSTALLATION";

    public synchronized static String id(Context context) {
        sID = readInstallationId(context);
        return sID;
    }

    public synchronized static String makeUUid(Context context) {
        String id = UUID.randomUUID().toString();
        return id;
    }

    public static String readInstallationId(Context context) {
        return FlagPreference.getUUID(context);
    }

    public static void writeInstallationId(Context context, String uuid) {
        FlagPreference.setUUID(context, uuid);
    }
}
