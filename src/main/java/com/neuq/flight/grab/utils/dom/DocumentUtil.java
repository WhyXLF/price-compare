package com.neuq.flight.grab.utils.dom;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * author: xiaoliufu
 * date:   2017/3/18.
 * description: 创建Document
 */
public class DocumentUtil {
    private static final Logger logger = LoggerFactory.getLogger(DocumentUtil.class);

    public static Document getDocument(String html) {
        return Jsoup.parse(html);
    }
}
