package com.ivan.vts.mapper.extended.entities;

import java.io.Serializable;

/**
 * Created by Chiefster on 15/8/2017.
 */

public class Setting implements Serializable {

    private Integer themeNo;
    private Integer languageNo;

    public Setting(Integer themeNo, Integer languageNo) {
        this.languageNo = languageNo;
        this.themeNo = themeNo;
    }

    public void setLanguageNo(Integer languageNo) {
        this.languageNo = languageNo;
    }

    public void setThemeNo(Integer themeNo) {
        this.themeNo = themeNo;
    }

    public Integer getLanguageNo() {
        return languageNo;
    }

    public Integer getThemeNo() {
        return themeNo;
    }
}
