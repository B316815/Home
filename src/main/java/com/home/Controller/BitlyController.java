//package com.home.Controller;
//import com.home.Service.BitlyService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class BitlyController {
//
//    @Autowired
//    private BitlyService bitlyService;
//
//    @GetMapping("/shorten")
//    public String shortenUrl(@RequestParam String longUrl) {
//        return bitlyService.shortenUrl(longUrl);
//    }
//}
//
