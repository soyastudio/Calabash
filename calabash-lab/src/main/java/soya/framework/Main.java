package soya.framework;

import com.google.gson.Gson;
import soya.framework.bean.JsonUtils;
import soya.framework.csv.CSVDynaClass;
import soya.framework.io.ResourceService;
import soya.framework.poi.XlsxDynaClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URI;
import java.net.URLClassLoader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        File xlsx = new File("C:/Albertsons/workspace/doc/xpath-mappings.xlsx");
        File csv = new File("C:\\Albertsons\\workspace\\doc\\data.csv");

        /*new XlsxDynaClass("GroceryOrder", xlsx, "Map_OMS to Canonical_CMM",
                new String[]{
                        "Target", "DataType", "Cardinality", "Mapping", "Source", "Version"
                }).getRows().forEach(e -> {
            System.out.println(e.get("Target") + "=" + e.get("Mapping"));
        });*/

        //CSVDynaClass dynaClass = new CSVDynaClass("CSV", new FileReader(csv));

        //System.out.println(JsonUtils.toJson(dynaClass.getRows()));

        /*

        System.out.println(uri.getFragment());

        URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Arrays.stream(sysloader.getURLs()).forEach(e -> {
            System.out.println("================ " + e);
        });*/

        URI uri = URI.create("base64:file:///C:/xxx.txt#base64encode().process(soya.framework.io.fragments.Base64DecodeProcessor).jsonpath([1,2,3],x)");
        //new ResourceService.FragmentProcessChain(uri.getFragment()).process("xyz");

        System.out.println(new ResourceService.FragmentProcessChain(uri.getFragment()).process("xyz"));
    }
}
