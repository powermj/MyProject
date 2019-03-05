package com.zhxg.framework.base.utils;

import com.lowagie.text.Document;
import com.lowagie.text.Image;

public class ITextUtil {

    public static float getContextWidth(Document document) {
        float width = document.getPageSize().getWidth();
        float left = document.leftMargin();
        float right = document.rightMargin();
        float contextWidth = width - left - right;
        return contextWidth;
    }

    public static float getPictureHightByWidth(Image image, float contextWidth) {

        float plainHeight = image.getPlainHeight();
        float plainWidth = image.getPlainWidth();
        float height = (contextWidth / plainWidth) * plainHeight;

        return height;
    }

    private static final float DEFAULT_PERCENT = 1f;

    public static void setScaleAbsolute(Image image, float contextWidth) {
        setScaleAbsolute(image, contextWidth, DEFAULT_PERCENT);
    }

    public static void setScaleAbsolute(Image image, float contextWidth, float percent) {
        contextWidth *= percent;
        float contextHight = getPictureHightByWidth(image, contextWidth);
        image.scaleAbsolute(contextWidth, contextHight);
    }

}
