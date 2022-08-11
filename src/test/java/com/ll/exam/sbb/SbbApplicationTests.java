package com.ll.exam.sbb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SbbApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    private QuestionRepository questionRepository;

    @BeforeEach
    void reset(){
        questionRepository.disableForeignKeyCheck();
        questionRepository.truncateMyTable();
        questionRepository.enableForeignKeyCheck();
    }

    @Test
    public void Question_저장이_잘되는지(){
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        questionRepository.save(q2);

        assertThat(q1.getId()).isGreaterThan(0);
        assertThat(q2.getId()).isGreaterThan(q1.getId());
    }

    @Test
    public void Question_삭제가_잘되는지(){
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        questionRepository.save(q2);

        questionRepository.delete(q1);

        assertEquals(1,questionRepository.count());
    }

    @Test
    public void Question_수정이_잘되는지(){
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        questionRepository.save(q1);

        Question question=questionRepository.findById(1).orElseThrow(RuntimeException::new);
        question.setSubject("changeSubject");
        questionRepository.save(question);
        Question question1=questionRepository.findBySubject("changeSubject");
        assertEquals(1,question1.getId());
    }

    @Test
    public void  findBySubject(){
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        questionRepository.save(q1);

        Question question1=questionRepository.findBySubject("sbb가 무엇인가요?");
        assertEquals(1,question1.getId());
    }

    @Test
    public void findBySubjectAndContentTest(){
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        questionRepository.save(q1);

        Question question=questionRepository.findBySubjectAndContent("sbb가 무엇인가요?","sbb에 대해서 알고 싶습니다.");
        assertEquals("sbb가 무엇인가요?",question.getSubject());
        assertEquals("sbb에 대해서 알고 싶습니다.",question.getContent());
    }

    @Test
    public void findBySubjectLikeTest(){
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        questionRepository.save(q2);

        List<Question> questions=questionRepository.findBySubjectLike("sbb%");
        assertEquals(1,questions.size());
    }




}
