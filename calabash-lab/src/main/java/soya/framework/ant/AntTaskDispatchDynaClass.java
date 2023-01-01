package soya.framework.ant;

import org.apache.tools.ant.taskdefs.Echo;
import soya.framework.bean.BasicDynaClass;
import soya.framework.bean.CommandDispatchDynaClass;
import soya.framework.bean.DynaClass;
import soya.framework.bean.DynaProperty;

public class AntTaskDispatchDynaClass extends CommandDispatchDynaClass {

    public AntTaskDispatchDynaClass(String name, Class<?> commandType, String[] propNames) {
        super(name, commandType, propNames, "execute");
    }

    public static void main(String[] args) throws Exception {
        DynaClass dynaClass = BasicDynaClass.builder().copyFromDeclaredFields(Echo.class).create();
        for(DynaProperty property : dynaClass.getDynaProperties()) {
            System.out.println(property.getName() + ": " + property.getType().getName());
        }
    }
}
