package cn.ubibi.metadata.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "md_MetaDataEntity")
public class MetaDataEntity {
    @Id
    public String id;

    private String name;
    private String collection;
    private String text;
    private String group;
    private List<MetaDataAttribute> attributes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<MetaDataAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<MetaDataAttribute> attributes) {
        this.attributes = attributes;
    }
}
