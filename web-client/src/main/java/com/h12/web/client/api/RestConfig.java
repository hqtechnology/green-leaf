package com.h12.web.client.api;

import lombok.Data;

@Data
public class RestConfig {
    private Integer connectionTimeout;
    private String host;
    private String uri;

}
