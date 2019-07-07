package cliff.ford.controller;

import cliff.ford.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept){
        return deptService.add(dept);
    }


    @GetMapping(value = "/dept/get/{id}")
    @HystrixCommand(fallbackMethod = "wrongId")
    public Dept get(@PathVariable("id") Long id){
        Dept dept = deptService.get(id);
        if(id == null || dept == null){
            throw  new RuntimeException();
        }
        return dept;
    }

    private Dept wrongId(@PathVariable("id") Long id){
        return new Dept().setDeptno(id).setDname("该Id:"+id+"没有对应信息--Hystrix")
                .setDb_source("no database");
    }

    @GetMapping(value = "/dept/list")
    public List<Dept> list(){
        return deptService.list();
    }

    @GetMapping(value = "/dept/discovery")
    public Object discovery(){
        List<String> list = discoveryClient.getServices();
        System.out.println(list);

        List<ServiceInstance> instances = discoveryClient.getInstances("MICROSERVICECLOUD-DEPT");
        for(ServiceInstance instance : instances){
            System.out.println(instance.getServiceId()+"\t"+
                    instance.getHost()+"\t"+
                    instance.getPort()+"\t"+
                    instance.getUri());
        }
        return this.discoveryClient;
    }
}
