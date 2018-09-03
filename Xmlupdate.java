    package com.webdriver;    
    
    import java.io.File;
    import javax.xml.parsers.DocumentBuilder;
    import javax.xml.parsers.DocumentBuilderFactory;
    import javax.xml.parsers.ParserConfigurationException;
    import javax.xml.transform.Transformer;
    import javax.xml.transform.TransformerException;
    import javax.xml.transform.TransformerFactory;
    import javax.xml.transform.dom.DOMSource;
    import javax.xml.transform.stream.StreamResult;

    import org.w3c.dom.Attr;
    import org.w3c.dom.Document;
    import org.w3c.dom.Element;

    public class Xmlupdate {


        public static void main(String argv[]) {

          try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("framework");
            doc.appendChild(rootElement);

            // staff elements
            Element staff = doc.createElement("testcase");
            rootElement.appendChild(staff);

//            // set attribute to staff element
//            Attr attr = doc.createAttribute("id");
//            attr.setValue("1");
//            staff.setAttributeNode(attr);

            // shorten way
            // staff.setAttribute("id", "1");

            // first elements
            Element keyword = doc.createElement("keyword");
            keyword.appendChild(doc.createTextNode("open"));
            staff.appendChild(keyword);

            // last elements
            Element value = doc.createElement("value");
            value.appendChild(doc.createTextNode("http://en.wordpress.com/wp-login.php"));
            staff.appendChild(value);

            // nickname elements
            Element object = doc.createElement("object");
            object.appendChild(doc.createTextNode("null"));
            staff.appendChild(object);

         // first elements
            Element keyword1 = doc.createElement("keyword1");
            keyword1.appendChild(doc.createTextNode("Type"));
            staff.appendChild(keyword1);

            // last elements
            Element value1 = doc.createElement("value1");
            value1.appendChild(doc.createTextNode("User_login"));
            staff.appendChild(value1);

            // nickname elements
            Element object1 = doc.createElement("object1");
            object1.appendChild(doc.createTextNode("admin"));
            staff.appendChild(object1);
            Element value2 = doc.createElement("value2");
            value2.appendChild(doc.createTextNode("user_pass"));
            staff.appendChild(value2);
            Element object2 = doc.createElement("object2");
            object2.appendChild(doc.createTextNode("password"));
            staff.appendChild(object2);
//            // salary elements
//            Element salary = doc.createElement("salary");
//            salary.appendChild(doc.createTextNode("100000"));
//            staff.appendChild(salary);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:\file3.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

          } catch (ParserConfigurationException pce) {
        	  
            pce.printStackTrace();
          } catch (TransformerException tfe) {
            tfe.printStackTrace();
          }
        }
    }
