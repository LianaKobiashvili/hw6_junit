package org.example.data;

public enum ButtonsName {
    FANFICS ("ФАНФИКИ"), FANART ("ФАНАРТ"), BLOGS ("БЛОГИ");

    private final String buttonName;
    ButtonsName (String buttonName){
        this.buttonName = buttonName;
    }
    public String getButtonName(){
        return buttonName;
    }
}
