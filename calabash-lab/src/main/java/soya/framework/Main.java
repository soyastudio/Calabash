package soya.framework;

import soya.framework.bean.JsonUtils;
import soya.framework.csv.CSVDynaClass;
import soya.framework.poi.XlsxDynaClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URI;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        File xlsx = new File("C:\\Albertsons\\workspace\\doc\\xpath-mappings.xlsx");
        File csv = new File("C:\\Albertsons\\workspace\\doc\\data.csv");

        /*new XlsxDynaClass("GroceryOrder", xlsx, "Map_OMS to Canonical_CMM",
                new String[]{
                        "Target", "DataType", "Cardinality", "Mapping", "Source", "Version"
                }).getRows().forEach(e -> {
            System.out.println(e.get("Target") + "=" + e.get("Mapping"));
        });*/

        CSVDynaClass dynaClass = new CSVDynaClass("CSV", new FileReader(csv));

        System.out.println(JsonUtils.toJson(dynaClass.getRows()));

        //URI uri = URI.create("base64:encode:file:///C:/xxx.txt");

    }
}
