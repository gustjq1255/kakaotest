## README

###프로젝트구성

- SpringBoot, H2, gradle, JPA, WebSocket, Thymeleaf, Security, Junit, lombok

```c
plugins {
	id 'org.springframework.boot' version '2.6.3'
	id 'io.spring.dependency-management' version '1.0.11.RELEASE'
	id 'java'
}

group = 'com.example'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '1.8'

configurations {
	compileOnly {
		extendsFrom annotationProcessor
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-jdbc'
	implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
	implementation 'nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.springframework.boot:spring-boot-starter-websocket'
	implementation 'org.bgee.log4jdbc-log4j2:log4jdbc-log4j2-jdbc4.1:1.16'
	implementation 'org.springframework.boot:spring-boot-starter-security'
	implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity5'
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	compileOnly 'org.projectlombok:lombok'
	developmentOnly 'org.springframework.boot:spring-boot-devtools'
	runtimeOnly 'com.h2database:h2'
	annotationProcessor 'org.springframework.boot:spring-boot-configuration-processor'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'org.springframework.security:spring-security-test'
}

tasks.named('test') {
	useJUnitPlatform()
}
```

###실행방법
1. GitHub에서 Repository를 Clone을 해온다 [https://github.com/gustjq1255/kakaotest.git](https://github.com/gustjq1255/kakaotest.git)
2. package Explorer에서 '마우스 우클릭 > import projects > Gradle > Existing Gradle Project'를 선택한다.
3. Next를 누르고 Project root Directory 경로를 '깃저장경로'/kakaotest/kakao 폴더로 지정해준 후 Finish버튼을 눌러준다.
4. lombok을 적용해준다.
5. h2 database를 설치하고 db명을 qna로 생성해준다. [다운경로](http://www.h2database.com/html/download.html)
6. application.propertis의 spring.jpa.hibernate.ddl-auto=create옵션을 확인해준다.

** 최초실행시엔 주석을풀어주고 이후엔 주석을 해준다. 테이블 CREATE, DROP **

```c
spring.jpa.hibernate.ddl-auto=create
```
** 시나리오 돌리실때 Junit부터 실행하시면 기본데이터가 등록됩니다. 예) Run As > Spring Boot App 이후  Run As > Junit TEST **

** 고객ID/PW -> testuser/testuser  **

** 관리자ID/PW -> testadmin/testadmin  **

###문제 해결 전략

1. 사용자는 고객(USER), 관리자(ADMIN)로 나뉜다.
2. 비 로그인 사용자는 메인화면 이외에 접근할 수 없습니다.
3. 고객 권한 사용자는 관리자용 화면에 접근할 수 없습니다.
4. 고객은 로그인 이후에 문의를 등록할 수 있습니다. (자신이 작성한 문의를 확인하기 위함)
5. 고객은 로그인 이후 자신의 문의목록을 확인 할 수 있고 현재 진행상태와 결과를 실시간으로 확인 할 수 있다.
6. 관리자는 관리자용 화면에서 자신이 상담사로 지정되거나 상담사가 지정되지 않은 문의 목록을 확인 할 수 있습니다.
7. 관리자용 문의목록은 websocket에 연결하여 QNA 테이블에 수정이 발생하면 즉시 화면에 반영됩니다.
8. 관리자용 문의목록, 관리자용 조회화면에서 상담사 정보가 없는 문의에 대해 자신을 상담사로 지정할 수 있습니다.
9. 관리자용 회원목록에서 사용자관리를 할 수 있습니다.



