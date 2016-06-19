package com.castleheg.tappa.utils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;

public class XmlHandler {
  private XmlPullParserFactory xmlFactoryObject;
  private XmlPullParser xmlParser;

  private String rideName;

  public XmlHandler() {
  }

  public void initHandler(InputStream xmlStream) throws XmlPullParserException {
    xmlFactoryObject = XmlPullParserFactory.newInstance();
    xmlParser = xmlFactoryObject.newPullParser();
    xmlParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
    xmlParser.setInput(xmlStream, null);
  }

  public void parseStream() {
    int event;
    String text = null;
    setRideName(null);
    int lc=0;
    try {
      event = xmlParser.getEventType();
      while (event != XmlPullParser.END_DOCUMENT) {
        String name = xmlParser.getName();
        switch (event) {
          case XmlPullParser.START_TAG:
            System.out.println("Start tag");
            break;
          case XmlPullParser.TEXT:
            text = xmlParser.getText();
            System.out.println("TEXT=" + text);
            break;
          case XmlPullParser.END_TAG:
            System.out.println("END tag");
            if (name.equals("name")) {
              setRideName(text);
            } else {
            }
            break;
        }
        lc++;
        event = xmlParser.next();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("LC=" + lc);
  }

  private void setRideName(String rideName) {
    this.rideName = rideName;
  }

  public String getRideName() {
    return this.rideName;
  }
}
