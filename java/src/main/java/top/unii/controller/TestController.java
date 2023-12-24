package top.unii.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.unii.bean.*;
import top.unii.service.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/test")
public class TestController {
    
    @Autowired
    private TestService service;
    
    @PostMapping(value = {"/request"})
    public RelayResponseBody relayRequest(@RequestBody RelayRequestBody body, HttpServletRequest request){
        
        return service.relayRequest(body, request);
        
    }
    
    /**
     * 针对接入到第三方公众号中使用的示例
     * 第三方公众号中仅支持返回文本消息，不支持返回红包消息和收费消息以及异步消息。
     */
    @PostMapping(value = {"/third"})
    public RelayResponseBody third(@RequestBody RelayRequestBody body, HttpServletRequest request){
        
        return service.third(body, request);
        
    }


}





































