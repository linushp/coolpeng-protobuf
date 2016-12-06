package cn.ubibi.metadata.models;

import java.util.Arrays;
import java.util.List;


public class MetaDataAttribute {

    private String name;// 'empNumber',
    private String text;// '员工工号',
    private String dbType;// 'string',//bool,number,date,string
    private String format;//: '${value}',
    private String input;//: 'generator:CommonNumberGenerator("empNumber","YG",10000001)',
    private List<String> validator;//:[]

    public MetaDataAttribute() {
    }

    public MetaDataAttribute(String name, String text, String dbType, String format, String input, List<String> validator) {
        this.name = name;
        this.text = text;
        this.dbType = dbType;
        this.format = format;
        this.input = input;
        this.validator = validator;
    }


    public MetaDataAttribute(String name, String text, String dbType, String format, String input, String [] validator) {

        this.name = name;
        this.text = text;
        this.dbType = dbType;
        this.format = format;
        this.input = input;

        if(validator!=null){
            this.validator = Arrays.asList(validator);
        }

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public List<String> getValidator() {
        return validator;
    }

    public void setValidator(List<String> validator) {
        this.validator = validator;
    }
}
