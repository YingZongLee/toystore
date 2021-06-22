package com.yls.toystore.controller;

import com.yls.toystore.model.WebPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Arrays;

import static com.yls.toystore.model.WebPage.WEBROOT;

/**
 * @author yunglee on 2021/05/18
 * @project toystore
 */
@Slf4j
@Controller
public class ProductController {

    @GetMapping("/products")
    public String index(Model model) {
        WebPage webPage = new WebPage();
        webPage.setMainContent("products");
        model.addAttribute(webPage);
        return WEBROOT;
    }

    @GetMapping("/product/{id}")
    public String getProduct(Model model, @PathVariable String id) {
        log.info("product id {}", id);
        WebPage webPage = new WebPage();
        webPage.setMainHtml5Base("./../");
        webPage.setIsElevateZoom(true);
        webPage.setIsMainScriptLayout(true);
        webPage.setMainScriptLayout("js/examples.gallery.js");
        webPage.setMainContent("product");
        model.addAttribute(webPage);
        return WEBROOT;
    }

    @GetMapping("/admin/prods")
    public String showProducts(Model model) {
        WebPage webPage = new WebPage();
        webPage.setIsMainNotice(false);
        webPage.setIsMainHeader(false);
        webPage.setIsMainFooter(false);
        webPage.setMainHtml5Base("./../");
        webPage.setMainContent("admin/product.list");
        model.addAttribute(webPage);
        return WEBROOT;
    }

    @GetMapping("/admin/prod/{id}")
    public String setProducts(Model model, @PathVariable String id) {
        log.info("id {}", id);
        WebPage webPage = new WebPage();
        webPage.setIsMainNotice(false);
        webPage.setIsMainHeader(false);
        webPage.setIsMainFooter(false);
        webPage.setEnableCDN(true);
        webPage.setMainHtml5Base("./../../");
        webPage.setMainContent("admin/product.detail");
        model.addAttribute(webPage);
        return WEBROOT;
    }

    @ResponseBody
    @PostMapping("/admin/prod/upload")
    public ResponseEntity<String> uploadImage(MultipartHttpServletRequest request) {
        request.getFileMap().entrySet().forEach(entry -> {
            log.info("{},  {}", entry.getKey(), entry.getValue());
        });
        return ResponseEntity.ok("upload successful.");
    }
}
