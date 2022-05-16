package springcloudproducer.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;


/**
 * 服务提供者
 */
@RestController
@Component
public class HelloController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/hello")
    public String index(@RequestParam String name, HttpServletRequest request) {
        System.out.println(request.getParameterNames());
        System.out.println(request.getParameterMap());

        for (String str : request.getParameterMap().keySet()) {
            System.out.println(str + "=" + request.getParameterValues(str));
        }
        Enumeration<String> paraNames = request.getParameterNames();
        for (Enumeration e = paraNames; e.hasMoreElements(); ) {

            String thisName = e.nextElement().toString();
            String thisValue = request.getParameter(thisName);
            System.out.println(thisName + "--------------" + thisValue);

        }
        return "hello " + name + "，this is first messge  from HelloController  producer， from port " + port;
    }


    @GetMapping(value = "/echo/{string}")
    public String echo(@PathVariable String string) {

        return "from " + port + "Hello Nacos Discovery " + string;
    }


}
