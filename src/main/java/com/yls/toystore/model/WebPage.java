package com.yls.toystore.model;

import lombok.Data;

/**
 * @author yunglee on 2021/05/29
 * @package toystore
 */
@Data
public class WebPage {
    public static final String WEBROOT = "index";
    private Boolean isMainNotice;
    private Boolean isMainHeader;
    private Boolean isMainContent;
    private Boolean isMainFooter;
    private Boolean isMainCssLayout;
    private Boolean isMainScriptLayout;
    private Boolean isElevateZoom;
    private Boolean enableCDN;
    private String mainHtml5Base;
    private String mainContent;
    private String mainCssLayout;
    private String mainScriptLayout;

    public WebPage() {
        this.isMainNotice = true;
        this.isMainHeader = true;
        this.isMainContent = true;
        this.isMainFooter = true;
        this.isMainCssLayout = false;
        this.isMainScriptLayout = false;
        this.enableCDN = false;
        this.isElevateZoom = false;
        this.mainHtml5Base = "./";
    }
}
