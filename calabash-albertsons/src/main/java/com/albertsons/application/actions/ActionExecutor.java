package com.albertsons.application.actions;

import soya.framework.bean.BasicDynaClass;
import soya.framework.bean.DynaBean;
import soya.framework.bean.DynaProperty;
import soya.framework.reflect.ReflectUtils;

import java.lang.reflect.Field;

public class ActionExecutor implements DynaBean<BasicDynaClass> {
    private Action action;
    private DynaBean dynaBean;

    private ActionExecutor(Class<? extends Action> actionType) throws InstantiationException, IllegalAccessException {
        this.action = actionType.newInstance();
        this.dynaBean = BasicDynaClass.builder().copyFromFields(actionType).create().newInstance();
    }

    @Override
    public BasicDynaClass getDynaClass() {
        return (BasicDynaClass) dynaBean.getDynaClass();
    }

    @Override
    public Object get(String name) {
        return dynaBean.get(name);
    }

    @Override
    public void set(String name, Object value) {
        dynaBean.set(name, value);
    }

    public DynaProperty[] properties() {
        return dynaBean.getDynaClass().getDynaProperties();
    }

    public ActionExecutor setProperty(String name, Object value) {
        set(name, value);
        return this;
    }

    public String execute() throws Exception {
        DynaProperty[] properties = dynaBean.getDynaClass().getDynaProperties();
        for (DynaProperty property : properties) {
            Field field = ReflectUtils.findField(action.getClass(), property.getName());
            field.setAccessible(true);
            field.set(action, dynaBean.get(property.getName()));
        }

        return action.execute();
    }

    public static ActionExecutor newInstance(Class<? extends Action> actionType) {
        try {
            return new ActionExecutor(actionType);

        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        String result = ActionExecutor.newInstance(CmmAction.class)
                .setProperty("uri", "xxx")
                .execute();

        System.out.println(result);

    }
}
