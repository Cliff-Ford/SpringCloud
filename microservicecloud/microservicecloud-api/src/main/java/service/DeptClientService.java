package service;

import entity.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "MICROSERVICECLOUD-DEPT")
public interface DeptClientService {
    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    boolean add(@RequestBody Dept dept);


    @GetMapping(value = "/dept/get/{id}")
    Dept get(@PathVariable("id") Long id);

    @GetMapping(value = "/dept/list")
    List<Dept> list();

}
