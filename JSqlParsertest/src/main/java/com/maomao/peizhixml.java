package com.maomao;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class peizhixml {


    public String getBooks(File file){
    StringBuffer ss = new StringBuffer();
    ss.append("\t");
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(file);
            Element bookstore = document.getRootElement();
            Iterator storeit = bookstore.elementIterator();

            while(storeit.hasNext()){
                Element bookElement = (Element) storeit.next();
                //遍历bookElement的属性
                List<Attribute> attributes = bookElement.attributes();
                for(Attribute attribute : attributes){
                    if(attribute.getName().equals("desc")){
                        ss.append(attribute.getValue());
                        ss.append("\t");
                    }
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return ss.toString();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        File file = new File("E:\\books.xml");
        String s = new peizhixml().getBooks(file);
        System.out.println(s);

    }
}
