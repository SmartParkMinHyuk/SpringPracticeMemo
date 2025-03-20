# 메모장 API - Spring Boot

이 프로젝트는 간단한 메모장 API입니다. Spring Boot를 사용해서 만들었고, 메모를 생성하고, 조회하고, 수정하고, 삭제할 수 있습니다.

## 목차
- [시작하기](#시작하기)
- [API 사용법](#api-사용법)
    - [메모 생성](#메모-생성)
    - [메모 전체 조회](#메모-전체-조회)
    - [메모 단건 조회](#메모-단건-조회)
    - [메모 수정](#메모-수정)
    - [메모 제목 수정](#메모-제목-수정)
    - [메모 삭제](#메모-삭제)
- [프로젝트 구조](#프로젝트-구조)
- [의존성](#의존성)

---

## 시작하기


서버가 실행되면 `http://localhost:8080`에서 API를 사용할 수 있습니다.

---

## API 사용법

### 메모 생성
- **URL:** `POST /memos`
- **요청 예시:**
```json
{
    "title": "제목1",
    "contents": "내용1"
}
```
- **응답 예시:**
```json
{
    "id": 1,
    "title": "제목1",
    "contents": "내용1"
}
```

### 메모 전체 조회
- **URL:** `GET /memos`
- **응답 예시:**
```json
[
    {
        "id": 1,
        "title": "제목1",
        "contents": "내용1"
    },
    {
        "id": 2,
        "title": "제목2",
        "contents": "내용2"
    }
]
```

### 메모 단건 조회
- **URL:** `GET /memos/{id}`
- **응답 예시:**
```json
{
    "id": 1,
    "title": "제목1",
    "contents": "내용1"
}
```

### 메모 수정
- **URL:** `PUT /memos/{id}`
- **요청 예시:**
```json
{
    "title": "수정된 제목",
    "contents": "수정된 내용"
}
```
- **응답 예시:**
```json
{
    "id": 1,
    "title": "수정된 제목",
    "contents": "수정된 내용"
}
```

### 메모 제목 수정
- **URL:** `PATCH /memos/{id}`
- **요청 예시:**
```json
{
    "title": "제목만 수정"
}
```
- **응답 예시:**
```json
{
    "id": 1,
    "title": "제목만 수정",
    "contents": "내용1"
}
```

### 메모 삭제
- **URL:** `DELETE /memos/{id}`
- **응답:** `200 OK` (성공) 또는 `404 Not Found` (메모 없음)

---

## 프로젝트 구조
```
src/
├── main/
│   ├── java/org/example/memo/
│   │   ├── controller/MemoController.java
│   │   ├── dto/MemoRequestDto.java
│   │   ├── dto/MemoResponseDto.java
│   │   ├── entity/Memo.java
│   ├── resources/
│       ├── application.properties
```

---

## 의존성
이 프로젝트에서 사용하는 라이브러리는 아래와 같습니다.
- Spring Boot Web
- Lombok (코드 줄이기 용도)

`pom.xml`에서 확인할 수 있습니다.
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

---
Postman을 사용해서 API를 테스트할 수 있습니다.



