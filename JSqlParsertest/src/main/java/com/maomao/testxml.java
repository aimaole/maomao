package com.maomao;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class testxml {

    private List<Book> bookList = null;
    private Book book = null;

    public List<Book> getBooks(File file){

        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(file);
            Element bookstore = document.getRootElement();
            Iterator storeit = bookstore.elementIterator();

            bookList = new ArrayList<Book>();
            while(storeit.hasNext()){

                book = new Book();
                Element bookElement = (Element) storeit.next();
                //遍历bookElement的属性
                List<Attribute> attributes = bookElement.attributes();
                for(Attribute attribute : attributes){
                    if(attribute.getName().equals("id")){
                        String id = attribute.getValue();//System.out.println(id);
                        book.setId(Integer.parseInt(id));
                    }if(attribute.getName().equals("name")){
                        String name = attribute.getValue();//System.out.println(id);
                        book.setName(name);
                    }
                }
                bookList.add(book);
                book = null;
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return bookList;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        File directory = new File("");//设定为当前文件夹
        System.out.println(directory.getAbsolutePath());
        File file = new File("F:\\maomaogit\\JSqlParsertest\\src\\main\\java\\com\\maomao\\books.xml");
        List<Book> bookList = new ReadXMLByDom4j().getBooks(file);
        for(Book book : bookList){
            System.out.println(book);
        }
    }
}
