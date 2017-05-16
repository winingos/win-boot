package com.comment.temp;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.Arrays;
import java.util.Vector;

/**
 * Created by Administrator on 2017/1/2 0002.
 */
public class XmlTest {
    public static void main(String[] args) throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Vector v = new Vector(10);
        for (int i = 1; i < 100; i++) {
            Integer o = i;
            v.add(o);
            v.remove(o);
        }
        System.out.println("v = " + Arrays.toString(v.toArray()));
    }
}
