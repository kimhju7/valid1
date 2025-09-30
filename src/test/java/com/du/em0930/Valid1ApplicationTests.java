package com.du.em0930;

import com.du.em0930.entity.MyUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class Valid1ApplicationTests {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    void testPersist() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transection = em.getTransaction();

        transection.begin();  // 트랜젝션 시작

        MyUser user = MyUser.builder()      // 객체 생성
                .name("홍길동")
                .email("hong@korea.com")
                .password("567890")
                .build();

        em.persist(user);   // 영속성

        user.setName("테스트");

        em.flush();    // DB에 즉시 반영
        transection.commit();   // 트랜젝션 커밋

        em.close();   // 엔티티 매니저 닫기

    }


    @Test
    void testTemplate() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transection = em.getTransaction();

        transection.begin();  // 트랜젝션 시작



        em.flush();    // DB에 즉시 반영
        transection.commit();   // 트랜젝션 커밋

        em.close();   // 엔티티 매니저 닫기

    }


    @Test
    void testFind() {            // 자료 가져오는 거
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transection = em.getTransaction();

        transection.begin();  // 트랜젝션 시작

        MyUser user = em.find(MyUser.class, 1L);
        System.out.println(user);

        user.setName("최하나");

        em.flush();    // DB에 즉시 반영
        transection.commit();   // 트랜젝션 커밋

        em.close();   // 엔티티 매니저 닫기

    }


    @Test
    void testMerge() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transection = em.getTransaction();

        transection.begin();  // 트랜젝션 시작

        MyUser user = MyUser.builder()      // 객체 생성
                .id(1L)
                .name("관리자")
                .email("admin@korea.com")
                .password("999999")
                .build();

        em.merge(user);   // 영속성

        em.flush();    // DB에 즉시 반영
        transection.commit();   // 트랜젝션 커밋

        em.close();   // 엔티티 매니저 닫기

    }

    @Test
    void testRemove() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transection = em.getTransaction();

        transection.begin();  // 트랜젝션 시작

        MyUser user = em.find(MyUser.class, 2L);

        em.remove(user);


        em.flush();    // DB에 즉시 반영
        transection.commit();   // 트랜젝션 커밋

        em.close();   // 엔티티 매니저 닫기

    }

    @Test
    void testSelectAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transection = em.getTransaction();

        transection.begin();  // 트랜젝션 시작

        List<MyUser> list = em.createQuery("select e from MyUser e", MyUser.class).getResultList();
        for (MyUser user : list) {
            System.out.println(user);
            user.setName("가나다");
        }

        em.flush();    // DB에 즉시 반영
        transection.commit();   // 트랜젝션 커밋

        em.close();   // 엔티티 매니저 닫기

    }

}
