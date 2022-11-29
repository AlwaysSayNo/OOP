package org.nazar.grynko;

import lombok.SneakyThrows;
import org.nazar.grynko.util.Utils;


public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        String pathToPackage = "org.nazar.grynko.clazz.test";
        String className = "TestClazz";

        Class objectClass = Utils.load(pathToPackage, className);
        String clazzString = Utils.getClassString(objectClass);

        System.out.println(clazzString);
    }

}
