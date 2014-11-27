package com.vmojing.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@ComponentScan({"com.vmojing.core"})
@Import({ CoreBeansConfiguration.class})
public class CoreRootConfiguration {

}
