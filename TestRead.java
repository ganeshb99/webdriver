package com.webdriver;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

//import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TestRead {

    WebDriver driver = new FirefoxDriver();

    @Test
    public void test() throws Exception {
        Element fstElmnt, fstNmElmnt;
        NodeList fstNmElmntLst, fstNm;
        try {
            File file = new File("C:\\NewFile.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);

            NodeList nodeLst = doc.getElementsByTagName("teststep");

            for (int s = 0; s < nodeLst.getLength(); s++) {

                Node fstNode = nodeLst.item(s);

                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

                    fstElmnt = (Element) fstNode;
                    fstNmElmntLst = fstElmnt.getElementsByTagName("keyword");
                    fstNmElmnt = (Element) fstNmElmntLst.item(0);
                    fstNm = fstNmElmnt.getChildNodes();
                    String x = ((Node) fstNm.item(0)).getNodeValue();
                    System.out.println(x);
                    if (x.equals("open")) {
                        NodeList lstNmElmntLst = fstElmnt
                                .getElementsByTagName("value");
                        Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
                        NodeList lstNm = lstNmElmnt.getChildNodes();
                        String z = ((Node) lstNm.item(0)).getNodeValue();
                        driver.get(z);
                    }
                    if (x.equals("type")) {
                        NodeList lstNmElmntLst = fstElmnt
                                .getElementsByTagName("value");
                        Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
                        String z = lstNmElmnt.getTextContent();
                        System.out.println(z);
                        NodeList lstNmElmntLst1 = fstElmnt
                                .getElementsByTagName("object");
                        Element lstNmElmnt1 = (Element) lstNmElmntLst1.item(0);
                        String z1 = lstNmElmnt1.getTextContent();
                        System.out.println(z1);
                        driver.findElement(By.id(z)).sendKeys(z1);
                                          
                       
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
