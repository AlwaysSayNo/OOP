package org.nazar.grynko.clazz.test;

public class TestClazz {
    public String paramString;
    public int paramInt;

    public TestClazz(String paramString, int paramInt) {
        this.paramString = paramString;
        this.paramInt = paramInt;
    }

    public String getParamString() {
        return paramString;
    }

    public void setParamString(String paramString) {
        this.paramString = paramString;
    }

    public int getParamInt() {
        return paramInt;
    }

    private void privateMethod() {

    }

}