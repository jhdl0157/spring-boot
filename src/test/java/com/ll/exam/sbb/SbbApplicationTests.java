package com.ll.exam.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SbbApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void testJpa() {
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q1);  // 첫번째 질문 저장

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q2);  // 두번째 질문 저장
    }

    @Test
    void testJpa2(){
        List<Question> all=questionRepository.findAll();
        assertEquals(2,all.size());

        Question q=all.get(0);
        assertEquals("sbb가 무엇인가요?",q.getSubject());
    }

    @Test
    void testJpa3(){
        Question q1=questionRepository.findById(1).orElseThrow(RuntimeException::new);
        System.out.println(q1.toString());
        Question q2=questionRepository.findById(1).orElseThrow(RuntimeException::new);
        System.out.println(q2.toString());
        Question question=questionRepository.findBySubject("sbb가 무엇인가요?");
        assertEquals(1,question.getId());
    }

    @Test
    void jpa_1차_캐시_테스트(){
        Question q1=questionRepository.findById(1).orElseThrow(RuntimeException::new);
        System.out.println("--------------------------");
        Question q2=questionRepository.findById(1).orElseThrow(RuntimeException::new);


        System.out.println("같은 객체인지 비교 해보자");
        System.out.println(q1.toString());
        System.out.println(q2.toString());
    }

    @Test
    @Transactional
    void JPA_변경감지_테스트(){
        Question q1=questionRepository.findById(1).orElseThrow(RuntimeException::new);
        q1.setSubject("changeSub");

    }

}
