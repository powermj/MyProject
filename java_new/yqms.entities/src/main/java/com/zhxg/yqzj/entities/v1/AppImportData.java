package com.zhxg.yqzj.entities.v1;

import javax.persistence.Table;

import com.zhxg.framework.base.curd.impl.BaseEntity;

@Table(name = "app_import_data")
public class AppImportData extends BaseEntity {

    /**
     * 自增长主键
     */
    private int id;
    /**
     * 文字描述
     */
    private String appWords;
    /**
     * 图片
     */
    private String appPictures;
    /**
     * 音频
     */
    private String appVoices;
    /**
     * 用户ID
     */
    private String appUserID;
    /**
     * 入库时间
     */
    private String inputDate;
    /**
     * 机型
     */
    private String model;
    /**
     * 版本
     */
    private String edition;
    /**
     * 是否有效 1--有效 2--无效
     */
    private String valid;

    /**
     * 用户账户名
     */
    private String appUserName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppWords() {
        return appWords;
    }

    public void setAppWords(String appWords) {
        this.appWords = appWords;
    }

    public String getAppPictures() {
        return appPictures;
    }

    public void setAppPictures(String appPictures) {
        this.appPictures = appPictures;
    }

    public String getAppVoices() {
        return appVoices;
    }

    public void setAppVoices(String appVoices) {
        this.appVoices = appVoices;
    }

    public String getAppUserID() {
        return appUserID;
    }

    public void setAppUserID(String appUserID) {
        this.appUserID = appUserID;
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getAppUserName() {
        return appUserName;
    }

    public void setAppUserName(String appUserName) {
        this.appUserName = appUserName;
    }

}
