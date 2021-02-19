package org.recruitmentservice.avatar.storage;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("avatar-properties")
public class AvatarProperties {
    private String basePath;
    private String maxMultipartSize;
}
