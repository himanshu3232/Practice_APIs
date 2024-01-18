package com.demo.mywebapp;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class Controller{
    private final List<POJO> pojoList = new ArrayList<>();
    @GetMapping("/getAll")
    public String getAllPojo(){
        StringBuilder sb = new StringBuilder();
        pojoList.forEach(item -> sb.append(item).append((char)10));
        return sb.toString();
    }
    @GetMapping("/{book}")
    public List<POJO> getPojoByName(@PathVariable String book){
        return pojoList.stream().filter(pojo -> pojo.getBookName().equals(book)).toList();
    }
    @PostMapping
    public boolean addPojo(@RequestBody POJO pojo){
        return pojoList.add(pojo);
    }
    @DeleteMapping("/{book}")
    public boolean deletePojoByName(@PathVariable String book){
        return pojoList.removeIf(pojo -> pojo.getBookName().equals(book));
    }
    @PutMapping("/{book}")
    public boolean updatePojoByName(@PathVariable String book, @RequestBody POJO pojo){
       if(pojoList.removeIf(item -> item.getBookName().equals(book))) return pojoList.add(pojo);
       return false;
    }
}