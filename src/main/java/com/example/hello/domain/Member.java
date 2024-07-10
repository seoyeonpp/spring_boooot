package com.example.hello.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity // JPA가 관리하는 Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 알아서 생성해주는것
    private Long id;

//    @Column(name = "username") - 컬럼명을 정해줄수있다.
    private  String name;
}
