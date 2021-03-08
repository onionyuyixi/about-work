package com.example.aboutwork.xmlbean;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Data
@JacksonXmlRootElement(localName = "Invoice XSDVersion='2.8'")
public class SomeModel {
    private String name;
    private int age;
    private String addr;
    @JacksonXmlElementWrapper(localName = "Myfriends")
    @JacksonXmlProperty(localName = "string") //注意：即使换成int程序也不会出异常
    private List<String> friends;


    @SneakyThrows
    public static void main(String[] args) {
        SomeModel someModel = new SomeModel();
        someModel.setName("zs");
        someModel.setAge(10);
        someModel.setAddr("road13");
        String[] fs = new String[]{"abc", "efg", "hij"};
        someModel.setFriends(Arrays.asList(fs));

        XmlMapper xmlMapper = new XmlMapper();
        ObjectWriter writer = xmlMapper.writerFor(SomeModel.class);
        System.err.println(writer.writeValueAsString(someModel));
        int i = "123|1234|0".lastIndexOf("|");
        System.err.println(i);
    }
}
