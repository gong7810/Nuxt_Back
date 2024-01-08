package kr.co.seoulit.erp.sys.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/*")
public class LoginController {

    @GetMapping("/loginForm/{model}")
    public String login(@PathVariable String model) {
        System.out.println("하하 "+ model);
        return "ok";
    }
}
