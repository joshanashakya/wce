package org.onez.kmeans;

import org.hamcrest.core.IsNot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.onez.cluster.ClusterContext;
import org.onez.io.DataHandler;
import org.onez.iris.Iris;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.xml.crypto.Data;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.anyString;

/**
 * Created by joshanashakya on 8/10/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DataHandler.class)
public class KMeansServiceImplTest {

    private KMeansServiceImpl service;

    @Before
    public void setUp() {
        PowerMockito.mockStatic (DataHandler.class);
        service = new KMeansServiceImpl ();
    }

    @Test
    public void testWhenDataIsEmptyAndExpectEmptyClusterContext() {
        Mockito.when (DataHandler.loadData (anyString ())).thenReturn ("");
        ClusterContext context = service.initialize ("");
        assertThat (context.getNumberOfCluster (), is (0));
        assertThat (context.getIrisList ().size (), is (0));
    }

    @Test
    public void testWhenDataIsProvidedAndExpectClusterContextWithDifferentData () {
        Mockito.when (DataHandler.loadData (anyString ())).thenReturn (data);
        ClusterContext context = service.initialize (anyString ());
        assertThat (context.getClusters ().size (), IsNot.not (0));
    }

    @Test
    public void testWhenIrisAndCentroidIsSameAndExpectZero() {
        Iris iris = new Iris (5.1,3.5,1.4,0.2);
        Iris centroid = new Iris (5.1,3.5,1.4,0.2);
        double distance = service.calculateEuclideanDistance (iris, centroid);
        assertThat (distance, is (0.0));
    }

    @Test
    public void testWhenIrisAndCentroidIsDifferentAndExpectResult() {
        Iris iris = new Iris (5.1,3.5,1.4,0.2);
        Iris centroid = new Iris (4.9,3.0,1.4,0.2);
//        double value = 0.0;
        double calculatedValue = service.calculateEuclideanDistance (iris, centroid);
        System.out.println (calculatedValue);
//        assertThat (calculatedValue, is (value));
    }

    @Test
    public void testWhen() {

    }

    private final String data = "3::5.1,3.5,1.4,0.2::4.9,3.0,1.4,0.2::4.7,3.2,1.3,0.2::4.6,3.1,1.5,0.2::5.0,3.6,1.4,0.2::5.4,3.9,1.7,0.4::4.6,3.4,1.4,0.3::5.0,3.4,1.5,0.2::4.4,2.9,1.4,0.2::4.9,3.1,1.5,0.1::5.4,3.7,1.5,0.2::4.8,3.4,1.6,0.2::4.8,3.0,1.4,0.1::4.3,3.0,1.1,0.1::5.8,4.0,1.2,0.2::5.7,4.4,1.5,0.4::5.4,3.9,1.3,0.4::5.1,3.5,1.4,0.3::5.7,3.8,1.7,0.3::5.1,3.8,1.5,0.3::5.4,3.4,1.7,0.2::5.1,3.7,1.5,0.4::4.6,3.6,1.0,0.2::5.1,3.3,1.7,0.5::4.8,3.4,1.9,0.2::5.0,3.0,1.6,0.2::5.0,3.4,1.6,0.4::5.2,3.5,1.5,0.2::5.2,3.4,1.4,0.2::4.7,3.2,1.6,0.2::4.8,3.1,1.6,0.2::5.4,3.4,1.5,0.4::5.2,4.1,1.5,0.1::5.5,4.2,1.4,0.2::4.9,3.1,1.5,0.1::5.0,3.2,1.2,0.2::5.5,3.5,1.3,0.2::4.9,3.1,1.5,0.1::4.4,3.0,1.3,0.2::5.1,3.4,1.5,0.2::5.0,3.5,1.3,0.3::4.5,2.3,1.3,0.3::4.4,3.2,1.3,0.2::5.0,3.5,1.6,0.6::5.1,3.8,1.9,0.4::4.8,3.0,1.4,0.3::5.1,3.8,1.6,0.2::4.6,3.2,1.4,0.2::5.3,3.7,1.5,0.2::5.0,3.3,1.4,0.2::7.0,3.2,4.7,1.4::6.4,3.2,4.5,1.5::6.9,3.1,4.9,1.5::5.5,2.3,4.0,1.3::6.5,2.8,4.6,1.5::5.7,2.8,4.5,1.3::6.3,3.3,4.7,1.6::4.9,2.4,3.3,1.0::6.6,2.9,4.6,1.3::5.2,2.7,3.9,1.4::5.0,2.0,3.5,1.0::5.9,3.0,4.2,1.5::6.0,2.2,4.0,1.0::6.1,2.9,4.7,1.4::5.6,2.9,3.6,1.3::6.7,3.1,4.4,1.4::5.6,3.0,4.5,1.5::5.8,2.7,4.1,1.0::6.2,2.2,4.5,1.5::5.6,2.5,3.9,1.1::5.9,3.2,4.8,1.8::6.1,2.8,4.0,1.3::6.3,2.5,4.9,1.5::6.1,2.8,4.7,1.2::6.4,2.9,4.3,1.3::6.6,3.0,4.4,1.4::6.8,2.8,4.8,1.4::6.7,3.0,5.0,1.7::6.0,2.9,4.5,1.5::5.7,2.6,3.5,1.0::5.5,2.4,3.8,1.1::5.5,2.4,3.7,1.0::5.8,2.7,3.9,1.2::6.0,2.7,5.1,1.6::5.4,3.0,4.5,1.5::6.0,3.4,4.5,1.6::6.7,3.1,4.7,1.5::6.3,2.3,4.4,1.3::5.6,3.0,4.1,1.3::5.5,2.5,4.0,1.3::5.5,2.6,4.4,1.2::6.1,3.0,4.6,1.4::5.8,2.6,4.0,1.2::5.0,2.3,3.3,1.0::5.6,2.7,4.2,1.3::5.7,3.0,4.2,1.2::5.7,2.9,4.2,1.3::6.2,2.9,4.3,1.3::5.1,2.5,3.0,1.1::5.7,2.8,4.1,1.3::6.3,3.3,6.0,2.5::5.8,2.7,5.1,1.9::7.1,3.0,5.9,2.1::6.3,2.9,5.6,1.8::6.5,3.0,5.8,2.2::7.6,3.0,6.6,2.1::4.9,2.5,4.5,1.7::7.3,2.9,6.3,1.8::6.7,2.5,5.8,1.8::7.2,3.6,6.1,2.5::6.5,3.2,5.1,2.0::6.4,2.7,5.3,1.9::6.8,3.0,5.5,2.1::5.7,2.5,5.0,2.0::5.8,2.8,5.1,2.4::6.4,3.2,5.3,2.3::6.5,3.0,5.5,1.8::7.7,3.8,6.7,2.2::7.7,2.6,6.9,2.3::6.0,2.2,5.0,1.5::6.9,3.2,5.7,2.3::5.6,2.8,4.9,2.0::7.7,2.8,6.7,2.0::6.3,2.7,4.9,1.8::6.7,3.3,5.7,2.1::7.2,3.2,6.0,1.8::6.2,2.8,4.8,1.8::6.1,3.0,4.9,1.8::6.4,2.8,5.6,2.1::7.2,3.0,5.8,1.6::7.4,2.8,6.1,1.9::7.9,3.8,6.4,2.0::6.4,2.8,5.6,2.2::6.3,2.8,5.1,1.5::6.1,2.6,5.6,1.4::7.7,3.0,6.1,2.3::6.3,3.4,5.6,2.4::6.4,3.1,5.5,1.8::6.0,3.0,4.8,1.8::6.9,3.1,5.4,2.1::6.7,3.1,5.6,2.4::6.9,3.1,5.1,2.3::5.8,2.7,5.1,1.9::6.8,3.2,5.9,2.3::6.7,3.3,5.7,2.5::6.7,3.0,5.2,2.3::6.3,2.5,5.0,1.9::6.5,3.0,5.2,2.0::6.2,3.4,5.4,2.3::5.9,3.0,5.1,1.8::";
}
