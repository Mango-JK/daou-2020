# daou-2020
## #01 API 명세서

**회원 - `영준`/ 포스팅 - `정건` / 채팅 - `민영`**

### 1. User

- 📘**POST** : /users(nickname, password) - 회원 가입

- 📘**POST** : /users/login(nickname, password) - 로그인

- 📗**GET** : /users/logout(nickname) - 로그아웃

- 📘**PUT** : /users(nickname, password, newPassword) - 비밀번호 변경

- 📕**DELETE**: /users/{id} (nickname, password) - 회원 삭제

- 📗**GET** : /users/nickname() - 닉네임 중복 검사

- 📘**PUT** : /users/nickname (nickname) - 닉네임 변경

  <hr/>

### 2. Post

- 📘**POST** : /posts(user_id, problemLink, problemType, problemTitle,

  ​															language, title, content, code) - 게시글 생성

- 📘**PUT** : /posts/{postId, language, source_link, title, content, code} - 게시글 수정

- 📕**DELETE** : /posts/{postId} - 게시글 삭제



- 📗**GET** : /posts() - 전체 게시글 조회
- 📗**GET** : /posts/{language}() - 언어별 풀이 조회
- 📗**GET** : /posts/{sourceType} - 플랫폼별 풀이 조회
- 📗**GET** : /posts/{userId} - 유저별 풀이 조회



- 📗**GET** : /posts/{sourceType}/{keyword} - 플랫폼별 풀이 검색

- 📗**GET** : /posts/{language}/{keyword} - 언어별 풀이 검색

- 📗**GET** : /posts/{sourceType}/{keyword} - 플랫폼+언어별 풀이 검색

- 📗**GET** : /posts/{keyword} - 게시글 통합검색 (언어, 타이틀, 컨텐츠)

  

<hr/>

### 3. Chat

- 📗**GET** : /chat/{postId} - 채팅방 입장

- 📘**POST** : /chat/{postId} (userId, message) - 메시지 보내기