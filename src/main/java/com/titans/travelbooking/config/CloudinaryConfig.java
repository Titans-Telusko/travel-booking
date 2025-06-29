package com.titans.travelbooking.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary getCloudinary() {
        Map cloudMap = new HashMap();
        cloudMap.put("cloud_name", ""); // Add value from your account
        cloudMap.put("api_key", ""); // Add value from your account
        cloudMap.put("api_secret", ""); // Add value from your account
        cloudMap.put("secure", true);

        return new Cloudinary(cloudMap);
    }

}
