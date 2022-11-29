package org.nazar.grynko.util;

import org.nazar.grynko.Main;

import java.lang.reflect.*;

public class Utils {

    private static <T> String getFields(Class<T> clazz) {
        StringBuilder str = new StringBuilder();
        Field[] fields = clazz.getFields();

        for (int i = 0; i < fields.length; ++i) {
            Field f = fields[i];

            int mod = f.getModifiers();
            String type = f.getType().getTypeName();
            String name = f.getName();

            str.append("\t");
            if(mod != 0) str.append(Modifier.toString(mod)).append(" ");
            str.append(type).append(" ").append(name).append(";");

            if(i != fields.length - 1) str.append("\n");
        }

        return str.toString();
    }

    private static <T> String getCostructors(Class<T> clazz) {
        StringBuilder str = new StringBuilder();
        Constructor[] constructors = clazz.getConstructors();


        for (int i = 0; i < constructors.length; ++i) {
            Constructor c = constructors[i];

            int mMod = c.getModifiers();
            String mName = c.getName();

            str.append("\t");
            if(mMod != 0) str.append(Modifier.toString(mMod)).append(" ");
            str.append(" ").append(mName);

            str.append("(");

            Parameter[] parameters =  c.getParameters();
            str.append(getParameters(parameters));

            str.append(");");

            if(i != parameters.length - 1) str.append("\n");
        }

        return str.toString();
    }

    private static <T> String getMethods(Class<T> clazz) {
        StringBuilder str = new StringBuilder();
        Method[] methods = clazz.getMethods();

        for (int i = 0; i < methods.length; ++i) {
            Method m = methods[i];

            int mMod = m.getModifiers();
            String mType = m.getReturnType().getName();
            String mName = m.getName();

            str.append("\t");
            if(mMod != 0) str.append(Modifier.toString(mMod)).append(" ");
            str.append(mType).append(" ").append(" ").append(mName);

            str.append("(");

            Parameter[] parameters =  m.getParameters();
            str.append(getParameters(parameters));

            str.append(");");


            if(i != methods.length - 1) str.append("\n");
        }

        return str.toString();
    }

    private static String getParameters(Parameter[] parameters) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < parameters.length; ++i) {
            Parameter p = parameters[i];

            String pType = p.getType().getName();
            String pName = p.getName();

            str.append(pType).append(" ").append(pName);

            if(i != parameters.length - 1) str.append(", ");
        }

        return str.toString();
    }

    public static <T> String getClassString(Class<T> clazz) {
        StringBuilder sb = new StringBuilder();

        int mMod = clazz.getModifiers();
        String mName = clazz.getName();

        if(mMod != 0) sb.append(Modifier.toString(mMod)).append(" ");
        sb.append(mName).append(" ").append("{").append("\n\n");

        sb.append(getFields(clazz)).append("\n\n");
        sb.append(getCostructors(clazz)).append("\n\n");
        sb.append(getMethods(clazz)).append("\n\n");

        sb.append("}");

        return sb.toString();
    }

    public static Class load(String packageName, String className) throws ClassNotFoundException {
        ClassLoader loader = Main.class.getClassLoader();
        return loader.loadClass(packageName + "." + className);
    }
}
