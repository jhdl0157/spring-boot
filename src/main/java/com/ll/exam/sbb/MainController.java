package com.ll.exam.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MainController {


    int value=0;
    @GetMapping("/sbb")
    @ResponseBody //아래 함수의 리턴값을 문자열화 해서 브라우저 응답의 바디에 담는다.
    public String index(){
        System.out.println("index");
        return "안녕asdasssssssssssssssdasd!!!!!!@####ㅁ@@@@!!";
    }

    @GetMapping("/plus")
    @ResponseBody
    public String plus(@RequestParam int a,@RequestParam int b){
        return String.valueOf(a+b);
    }
    @GetMapping("/minus")
    @ResponseBody
    public String minus(@RequestParam int a,@RequestParam int b){
        return String.valueOf(a-b);
    }
    @GetMapping("increase")
    @ResponseBody
    public String increase(){
        value++;
        return String.valueOf(value);
    }

    @GetMapping("/gugudan")
    @ResponseBody
    public String gugudan(@RequestParam int dan,@RequestParam int limit){
         final StringBuffer stringBuffer=new StringBuffer();
        for(int i=1;i<=limit;i++){
            stringBuffer.append(("%d * %d = %d<br>").formatted(dan,i,dan*i));
        }
        return stringBuffer.toString();
    }

    @GetMapping("/mbti")
    @ResponseBody
    public String mbti(@RequestParam String name){
        Map<String,String> map =new HashMap<>();
        map.put("홍길동","INFP");
        map.put("홍길순","ENFP");
        map.put("임꺽정","INFJ");
        map.put("이재호","INTJ");
        return map.get(name);
    }

    @GetMapping("/saveSessionAge/{id}")
    @ResponseBody
    public int  save(@PathVariable int id, HttpServletRequest httpRequest){
        HttpSession session = httpRequest.getSession();
            session.setAttribute("age", id);

    return id;
    }

    @GetMapping("/getSessionAge")
    @ResponseBody
    public Integer  save(HttpServletRequest httpRequest){
        HttpSession session = httpRequest.getSession();
        return (Integer) session.getAttribute("age");
    }

}



