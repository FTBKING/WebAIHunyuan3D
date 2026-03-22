package com.glaceon;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootTest
@Slf4j
class DemoApplicationTests {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Test
    //获取服务url
    void getServiceUrl() {
        //1.根据服务名称拉取实例列表
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("hunyuan3D2Service");
        //如果为空则返回空
        if(serviceInstances.isEmpty()){
            return;
        }
        //2.负载均衡，选择一个实例，这里采用随机算法,hutool工具包的静态方法
        ServiceInstance serviceInstance = serviceInstances.get(RandomUtil.randomInt(serviceInstances.size()));
        //3.根据实例获取url
        String url = serviceInstance.getUri().toString();
        serviceInstances.forEach(a -> log.info("url:{}",a.getUri().toString()));
    }

}
