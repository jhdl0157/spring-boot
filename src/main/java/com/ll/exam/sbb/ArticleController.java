package com.ll.exam.sbb;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final QuestionRepository questionRepository;
    List<Article> articles=new ArrayList<>();
    long id=0;

    @GetMapping("/article/{id}")
    public Article getArticle(@PathVariable long id){
        Article article=articles.stream().filter(x->x.getId()==id).findFirst().get();
        return article;
    }

    @GetMapping("/test")
    public void Test(){
        Question q1=questionRepository.findById(1).orElseThrow(RuntimeException::new);
        System.out.println("1차 캐쉬 확인해보기-------------------");
        Question q3=questionRepository.findById(1).orElseThrow(RuntimeException::new);
        System.out.println("--------------------------");
        List<Question> q2=questionRepository.findAll();
        System.out.println("같은 객체인지 비교 해보자");
        System.out.println(q1.toString());
        System.out.println(q2.get(0));
    }
}
