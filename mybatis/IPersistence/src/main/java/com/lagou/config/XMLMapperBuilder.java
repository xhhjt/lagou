package com.lagou.config;

import com.lagou.pojo.Configuration;
import com.lagou.pojo.MappedStatement;
import com.lagou.pojo.SqlHandleType;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class XMLMapperBuilder {

    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(InputStream inputStream) throws DocumentException {

        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();

        String namespace = rootElement.attributeValue("namespace");
        pareseNode(rootElement,namespace,SqlHandleType.INSERT);
        pareseNode(rootElement,namespace,SqlHandleType.SElECT);
        pareseNode(rootElement,namespace,SqlHandleType.UPDATE);
        pareseNode(rootElement,namespace,SqlHandleType.DELETE);
    }

    private void pareseNode(Element rootElement, String namespace, SqlHandleType nodeName) {
        List<Element> list = rootElement.selectNodes("//" + nodeName.getName());
        for (Element element : list) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String paramterType = element.attributeValue("paramterType");
            String sqlText = element.getTextTrim();
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParamterType(paramterType);
            mappedStatement.setSql(sqlText);
            mappedStatement.setHandleType(nodeName);
            String key = namespace + "." + id;
            configuration.getMappedStatementMap().put(key, mappedStatement);
        }
    }


}
