package com.albertsons.application.actions;

import com.albertsons.application.configuration.ServiceLocator;
import soya.framework.xmlbeans.XmlSchemaTree;

import java.io.File;

public class CmmAction implements Action {

    private String bod;

    @Override
    public String execute() throws Exception {
        File dir = new File(ServiceLocator.getInstance().getWorkspace(), "CMM/BOD");
        File xsd = new File(dir, bod + ".xsd");

        StringBuilder builder = new StringBuilder();
        new XmlSchemaTree(xsd).stream().forEach(e -> {
            builder.append(e.getPath())
                    .append("=")
                    .append("\n");
        });

        return builder.toString();
    }
}
