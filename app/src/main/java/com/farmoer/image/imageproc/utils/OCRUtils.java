package com.farmoer.image.imageproc.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.googlecode.tesseract.android.TessBaseAPI;


import java.io.IOException;

public class OCRUtils {

    public static String parseImageToString(String language, Bitmap srcBitmap, String dataPath) throws IOException
    {

        Bitmap bitmap = srcBitmap;
        TessBaseAPI baseApi = new TessBaseAPI();
        baseApi.setDebug(true);
        // 使用默认语言初始化BaseApi
        baseApi.init(dataPath, language);
        baseApi.setImage(bitmap);

        // 获取返回值
        String recognizedText = baseApi.getUTF8Text();
        baseApi.end();
        return recognizedText;
    }
}
