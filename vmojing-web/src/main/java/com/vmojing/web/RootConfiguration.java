package com.vmojing.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({WebAppConfiguration.class})
@ComponentScan("com.vmojing.web")
public class RootConfiguration {

}
