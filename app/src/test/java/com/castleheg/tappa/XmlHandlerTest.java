package com.castleheg.tappa;

import android.os.Build;

import com.castleheg.tappa.utils.XmlHandler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.isEmptyOrNullString;

import java.io.InputStream;

import static org.junit.Assert.*;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,
    sdk = Build.VERSION_CODES.JELLY_BEAN)
public class XmlHandlerTest {

  private static final String SMALL_STRAVA_DATA_FILE = "xml/ride_small.xml";

  @Test
  public void testXmlHandlerReadsValidXmlFile() throws Exception {
    InputStream inputStream =
        this.getClass().getClassLoader().getResourceAsStream(SMALL_STRAVA_DATA_FILE);
    assertThat(inputStream, notNullValue());

    XmlHandler xmlHandler = new XmlHandler();
    xmlHandler.initHandler(inputStream);
    xmlHandler.parseStream();
    inputStream.close();
    assertEquals("Ride 1", xmlHandler.getRideName());
  }


}
