package com.shine.eacodingchallenge.service.renderer;

import com.shine.eacodingchallenge.domain.ModelData;
import com.shine.eacodingchallenge.domain.MakeData;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HtmlCarDataRendererServiceTest {

    @Test
    public void testRenderNoData(){
        test(null, HtmlCarDataRendererService.NO_DATA);
        test(new HashMap<>(), HtmlCarDataRendererService.NO_DATA);
    }

    @Test
    public void testRenderMake(){
        Map<String, MakeData> vendorData = new HashMap<>();
        vendorData.put("make1", null);
        test(vendorData, HtmlCarDataRendererService.NO_DATA);

        vendorData = new HashMap<>();
        vendorData.put("make1", new MakeData("make1"));
        test(vendorData, "<span>make1</span><br/>");
    }

    @Test
    public void testRenderModel() {
        Map<String, MakeData> vendorData = new HashMap<>();
        MakeData make1 = new MakeData("make1");
        make1.getModels().put("model1", null);
        vendorData.put("make1", make1);
        test(vendorData, "<span>make1</span><br/>");

        vendorData = new HashMap<>();
        make1 = new MakeData("make1");
        make1.getModels().put("model1", new ModelData(null));
        vendorData.put("make1", make1);
        test(vendorData, "<span>make1</span><br/>");
    }

    @Test
    public void testRenderInvalidShows() {
        Map<String, MakeData> vendorData = new HashMap<>();
        MakeData make1 = new MakeData("make1");
        ModelData model1 = new ModelData("model1");
        model1.getAttendedCarShows().add(null);
        make1.getModels().put("model1", model1);
        vendorData.put("make1", make1);
        test(vendorData, "<span>make1</span><br/><span style=\"margin-left:2em\">model1</span><br/>");

        vendorData = new HashMap<>();
        make1 = new MakeData("make1");
        model1 = new ModelData("model1");
        model1.getAttendedCarShows().add(" ");
        make1.getModels().put("model1", model1);
        vendorData.put("make1", make1);
        test(vendorData, "<span>make1</span><br/><span style=\"margin-left:2em\">model1</span><br/>");
    }

    @Test
    public void testRenderValidData() {
        Map<String, MakeData> vendorData = new HashMap<>();
        MakeData make1 = new MakeData("make1");
        ModelData model1 = new ModelData("model1");
        model1.getAttendedCarShows().add("show1");
        make1.getModels().put("model1", model1);
        vendorData.put("make1", make1);
        test(vendorData, "<span>make1</span><br/>" +
                            "<span style=\"margin-left:2em\">model1</span><br/>" +
                                "<span style=\"margin-left:4em\">show1</span><br/>");


    }

    private void test(Map<String, MakeData> vendorData, String expectedResult) {
        HtmlCarDataRendererService Renderer = new HtmlCarDataRendererService();
        try {
            String string = Renderer.render(vendorData);
            Assert.assertNotNull(string);
            Assert.assertEquals(expectedResult, string);
        }
        catch (Throwable t){
            t.printStackTrace();
            Assert.fail();
        }
    }
}
