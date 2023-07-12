package com.raintest.pojo;

import org.bson.BsonType;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.Conventions;
import org.bson.codecs.pojo.annotations.*;

import java.util.Arrays;
import java.util.List;

@BsonDiscriminator(value = "AnnotatedProduct", key = "_cls")
public class Product {
    @BsonProperty("modelName")
    private String name;

    @BsonId()
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String serialNumber;
    @BsonIgnore
    private List<Product> relatedItems;

    @BsonCreator
    public Product(@BsonProperty("modelName") String name) {
        this.name = name;
    }


    public static void main(String[] args) {
        ClassModel<Product> classModel = ClassModel.builder(Product.class).
                conventions(Arrays.asList(Conventions.ANNOTATION_CONVENTION)).build();

        System.out.println(classModel);

    }
}
