package com.example.fastdfs.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@ConfigurationProperties(prefix = "ly.upload")
@Data
public class LyUploadFastDFSProperties {

    private String baseUrl;
    private List<String> allowTypes;

}

