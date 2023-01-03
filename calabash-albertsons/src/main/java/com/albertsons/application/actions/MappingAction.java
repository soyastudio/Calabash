package com.albertsons.application.actions;

import com.albertsons.application.configuration.ServiceLocator;
import soya.framework.poi.XlsxDynaClass;

import java.io.File;

public class MappingAction implements Action {

    private String file;

    private String sheet;

    @Override
    public String execute() throws Exception {
        StringBuilder builder = new StringBuilder();
        File xlsx = new File(ServiceLocator.getInstance().getWorkspace(), file);
        new XlsxDynaClass(xlsx.getName(), xlsx, sheet, new String[]{
                "Target", "DataType", "Cardinality", "Mapping", "Source", "Version"})
                .getRows()
                .forEach(e -> {
                    builder.append(e.get("Target")).append("=").append("\n");
                });

        return builder.toString();
    }
}
