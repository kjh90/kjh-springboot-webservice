package com.kjh.web.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass//Entity클래스들이 BaseTimeEntity을 상속할경우 createdDate,modifiedDate 도 컬럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class)//BaseTimeEntity 클래스에 Auditing 기능을 포함시킨다.
public class BaseTimeEntity {
    //BaseTimeEntity클래스는 모든 Entity의 상위클래스가 되어 Entity들의 createdDate, modifiedDate를 자동으로 관리 하는 역할
    //JPA Auditing으로 생성시간/수정시간 자동화한다.
    //매번 DB INSERT, UPDATE 하기 전 날짜 데이터를 등록 수정하는 코드가 들어가는데 반복적인 코드이며, 코드가 지저분해지므로 자동화한다.
    //LocalDate,LocalDateTime

    @CreatedDate//Entity가 생성되어 저장될때(등록) 시간이 자동 저장된다.
    private LocalDateTime createdDate;

    @LastModifiedDate//조회한 Entity의 값을 변경할 때(수정) 시간이 자동 저장된다.
    private LocalDateTime modifiedDate;


}
