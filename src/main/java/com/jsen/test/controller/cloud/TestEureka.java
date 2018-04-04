package com.jsen.test.controller.cloud;

import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/3
 */
@RestController
@CrossOrigin
@RequestMapping("/cloud")
public class TestEureka {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/echo")
    public ResponseBase echo() {
        // ServiceInstance serviceInstance = loadBalancerClient.choose("service-1");
        // String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/dom/echo/jjjc";


        String data = restTemplate.getForObject("http://service-quartz/dom/echo/jjc" , String.class);
        return ResponseBase.create().code(0).msg(data);
    }
}
