package hadoop;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class testmm {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("E:\\books.xml");
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(file);
			Element bookstore = document.getRootElement();
			List<Element> elements = bookstore.elements();
			for(Element ee:elements) {
				System.out.println(ee.attributeValue("name"));
			}
		
			
		} catch (DocumentException e) {

			e.printStackTrace();
		}
		
	}

}
