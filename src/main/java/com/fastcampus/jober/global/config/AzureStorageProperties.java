package com.fastcampus.jober.global.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "cloud.azure.storage")
public class AzureStorageProperties {
    private String account;
    private String key;
    private String containerName;

    public String getConnectionString() {
        return "DefaultEndpointsProtocol=https;AccountName=" + account + ";AccountKey=" + key + ";EndpointSuffix=core.windows.net";
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }
}
