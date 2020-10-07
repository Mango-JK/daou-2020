package com.daou.ssjd.service;

import com.daou.ssjd.domain.entity.Posts;
import com.daou.ssjd.domain.entity.Problems;
import com.daou.ssjd.domain.repository.PostsRepository;
import com.daou.ssjd.domain.repository.ProblemsRepository;
import com.daou.ssjd.dto.PostsSaveRequestDto;
import com.daou.ssjd.dto.PostsUpdateRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class PostsServiceTest {

    @Autowired
    private PostsService postsService;

    @Autowired
    private ProblemsService problemsService;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private ProblemsRepository problemsRepository;

    /**
     * 1. 게시글 생성 / postService.savePost()
     */
    @Test
    @Transactional
    public void 게시글_등록() {
        // given
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .userId(1)
                .problemLink("https://javacoding.tistory.com/71?category=354715")
                .problemSite("백준")
                .problemTitle("뱀")
                .language("JAVA")
                .title("뱀 문제 풀어보세요")
                .content("구현 코드가 너무 길어요")
                .code("import java.io.BufferedReader;\n" +
                        "import java.io.IOException;\n" +
                        "import java.io.InputStreamReader;\n" +
                        "import java.util.ArrayList;\n" +
                        "import java.util.LinkedList;\n" +
                        "import java.util.Queue;\n" +
                        "import java.util.StringTokenizer;\n" +
                        "\n" +
                        "public class Main {\n" +
                        "\tstatic int N, K, L, length, next_i, next_j, Point, d;\n" +
                        "\tstatic int[][] map;\n" +
                        "\tstatic StringTokenizer st;\n" +
                        "\tstatic Queue<Curve> pq;\n" +
                        "\tstatic ArrayList<Pos> list;\n" +
                        "\tstatic int[] di = { 0, 1, 0, -1 };\n" +
                        "\tstatic int[] dj = { 1, 0, -1, 0 };\n" +
                        "\n" +
                        "\tpublic static void main(String[] args) throws IOException {\n" +
                        "\n" +
                        "\t\tInput(); // 입력 받기 및 초기화\n" +
                        "\n" +
                        "\t\tSnake(new Pos(0, 0, 1, 0)); // 시작 (0, 0) 좌표와, 길이 1 , 방향 0\n" +
                        "\n" +
                        "\t\tPoint++; // 다음 번째에서 종료되므로 끝난 이후 +1 한번 해주기\n" +
                        "\t\tSystem.out.println(Point);\n" +
                        "\n" +
                        "\t}\n" +
                        "\n" +
                        "\tstatic void Snake(Pos tmp) {\n" +
                        "\t\twhile (list.size() > length) { // 꼬리 잘라주기\n" +
                        "\t\t\tint tail_i = list.get(0).i;\n" +
                        "\t\t\tint tail_j = list.get(0).j;\n" +
                        "\t\t\tmap[tail_i][tail_j] = 0;\n" +
                        "\t\t\tlist.remove(0);\n" +
                        "\t\t}\n" +
                        "\n" +
                        "\t\tif (pq.size() > 0) {\n" +
                        "\t\t\tif (Point == pq.peek().idx) {\n" +
                        "\t\t\t\tif (pq.peek().ch == 'D')\n" +
                        "\t\t\t\t\td = (d + 1) % 4;\n" +
                        "\t\t\t\telse if (pq.peek().ch == 'L')\n" +
                        "\t\t\t\t\td = ((d - 1) + 4) % 4;\n" +
                        "\t\t\t\tpq.poll();\n" +
                        "\t\t\t}\n" +
                        "\t\t}\n" +
                        "\n" +
                        "\t\tnext_i = tmp.i + di[d];\n" +
                        "\t\tnext_j = tmp.j + dj[d];\n" +
                        "\n" +
                        "\t\tif (next_i >= 0 && next_j >= 0 && next_i < N && next_j < N && map[next_i][next_j] != 1) {\n" +
                        "\t\t\tif (map[next_i][next_j] == 4) { // 사과가 있다면 길이 ++\n" +
                        "\t\t\t\tlength++;\n" +
                        "\t\t\t}\n" +
                        "\t\t\tPoint++;\t// 다음 단계로 진행할 수 있으므로 Point +1 해주기\n" +
                        "\t\t\tlist.add(new Pos(next_i, next_j));\t// 다음 위치 list에 기록해주기\n" +
                        "\t\t\tmap[next_i][next_j] = 1;\t// 다음 위치 map에 1로 기록해두기\n" +
                        "\t\t\tSnake(new Pos(next_i, next_j, length, d));\n" +
                        "\t\t}\n" +
                        "\t\treturn;\n" +
                        "\t}\n" +
                        "\n" +
                        "\tstatic void Input() throws IOException {\n" +
                        "\t\tBufferedReader br = new BufferedReader(new InputStreamReader(System.in));\n" +
                        "\t\tN = Integer.parseInt(br.readLine());\n" +
                        "\t\tK = Integer.parseInt(br.readLine());\n" +
                        "\t\tpq = new LinkedList<Curve>(); // 방향전환 위치와 방향을 저장할 큐\n" +
                        "\t\tlist = new ArrayList<Pos>(); // 뱀의 몸체 위치를 기록하는 리스트\n" +
                        "\t\tmap = new int[N][N];\n" +
                        "\n" +
                        "\t\tfor (int k = 0; k < K; k++) { // 사과 위치 입력받기\n" +
                        "\t\t\tst = new StringTokenizer(br.readLine(), \" \");\n" +
                        "\t\t\tint x = Integer.parseInt(st.nextToken());\n" +
                        "\t\t\tint y = Integer.parseInt(st.nextToken());\n" +
                        "\t\t\tmap[x - 1][y - 1] = 4;\n" +
                        "\t\t}\n" +
                        "\n" +
                        "\t\tL = Integer.parseInt(br.readLine());\n" +
                        "\t\tfor (int l = 0; l < L; l++) { // 방향 전환 위치와 방향 큐에 기록해두기\n" +
                        "\t\t\tst = new StringTokenizer(br.readLine(), \" \");\n" +
                        "\t\t\tint i = Integer.parseInt(st.nextToken());\n" +
                        "\t\t\tchar c = st.nextToken().charAt(0);\n" +
                        "\t\t\tpq.offer(new Curve(i, c));\n" +
                        "\t\t}\n" +
                        "\n" +
                        "\t\tlength = 1;\n" +
                        "\t\tPoint = 0;\n" +
                        "\t\tmap[0][0] = 1;\n" +
                        "\t\tlist.add(new Pos(0, 0));\n" +
                        "\n" +
                        "\t}\n" +
                        "\n" +
                        "\tstatic class Curve implements Comparable<Curve> {\n" +
                        "\t\tint idx;\n" +
                        "\t\tchar ch;\n" +
                        "\n" +
                        "\t\tpublic Curve(int idx, char ch) {\n" +
                        "\t\t\tsuper();\n" +
                        "\t\t\tthis.idx = idx;\n" +
                        "\t\t\tthis.ch = ch;\n" +
                        "\t\t}\n" +
                        "\n" +
                        "\t\t@Override\n" +
                        "\t\tpublic int compareTo(Curve o) {\n" +
                        "\t\t\treturn this.idx - o.idx;\n" +
                        "\t\t}\n" +
                        "\t}\n" +
                        "\n" +
                        "\tstatic class Pos {\n" +
                        "\t\tint i, j, length, direction;\n" +
                        "\n" +
                        "\t\tpublic Pos(int i, int j, int length, int direction) {\n" +
                        "\t\t\tsuper();\n" +
                        "\t\t\tthis.i = i;\n" +
                        "\t\t\tthis.j = j;\n" +
                        "\t\t\tthis.length = length;\n" +
                        "\t\t\tthis.direction = direction;\n" +
                        "\t\t}\n" +
                        "\n" +
                        "\t\tpublic Pos(int k, int l) {\n" +
                        "\t\t\tthis.i = k;\n" +
                        "\t\t\tthis.j = l;\n" +
                        "\t\t}\n" +
                        "\t}\n" +
                        "\n" +
                        "}")
                .build();

        // when
        Posts result = postsService.savePost(dto);

        // then
        Posts posts = postsRepository.findByPostId(result.getPostId()).get();
        Assertions.assertThat(posts.getLanguage()).isEqualTo("JAVA");
        Assertions.assertThat(posts.getUser().getUserId()).isEqualTo(1);
        Assertions.assertThat(problemsService.findByProblemId(posts.getProblem().getProblemId()).getProblemTitle()).isEqualTo("뱀");

    }

    /**
     * 2. 게시글 수정 / postService.updatePost()
     */
    @Test
    @Transactional
    public void 게시글_수정() {
        // given
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .userId(1)
                .problemLink("https://www.naver.com")
                .problemSite("업데이트 테스트용")
                .problemTitle("업데이트 테스트")
                .language("TEST")
                .title("게시글 업데이트 풀어보세요")
                .content("UPDATE CODE")
                .code("THIS IS UPDATED")
                .build();
        Posts savePost = postsService.savePost(dto);
        int updatingPostId = savePost.getPostId();

        // when
        PostsUpdateRequestDto updateDto = new PostsUpdateRequestDto(savePost.getUser().getUserId(),
                "SUCCESS LINK", "SUCCESS TYPE", "SUCCESS TITLE","SUCCESS!", "TITLE !", "CONTENT ! ", "CODE SUCCESS!");
        postsService.updatePost(updatingPostId, updateDto);

        // then
        Posts afterPost = postsService.findByPostId(updatingPostId).get();
        Assertions.assertThat(afterPost.getPostId()).isEqualTo(savePost.getPostId());
        Assertions.assertThat(afterPost.getTitle()).isEqualTo("TITLE !");
    }

    /**
     * 3. 게시글 삭제 / postService.deletePost()
     */
    @Test
    @Transactional
    public void 게시글_삭제() {
        // given
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .userId(1)
                .problemLink("https://www.naver.com2")
                .problemSite("삭제 테스트용")
                .problemTitle("삭제 테스트")
                .language("TEST")
                .title("게시글 삭제 풀어보세요")
                .content("삭제 CODE")
                .code("THIS IS DELETED")
                .build();
        Posts savePost = postsService.savePost(dto);

        // when
        Optional<Posts> post = postsRepository.findById(savePost.getPostId());
        Optional<Problems> problem = problemsRepository.findById(savePost.getProblem().getProblemId());

        // then
        Assertions.assertThat(post).isNotNull();
        Assertions.assertThat(problem).isNotNull();
        postsService.deletePost(savePost.getPostId());
        Assertions.assertThat(postsRepository.findByPostId(savePost.getPostId())).isEmpty();
    }

    /**
     * 4. 게시글 상세 조회 / postService.findByPostId()
     */
    @Test
    @Transactional(readOnly = true)
    public void 게시글_상세조회() {
        // given
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .userId(3)
                .problemLink("https://www.daou.co.kr")
                .problemSite("조회 테스트용")
                .problemTitle("조회 테스트")
                .language("Vue.js")
                .title("게시글 조회입니다.")
                .content("findByPostId")
                .code("THIS IS GET One")
                .build();
        Posts savePost = postsService.savePost(dto);

        // when
        int postId = savePost.getPostId();
        Posts findOnePosts = postsService.findByPostId(postId).get();

        // then
        Assertions.assertThat(dto.getProblemTitle()).isEqualTo(findOnePosts.getProblem().getProblemTitle());
        Assertions.assertThat(dto.getCode()).isEqualTo(findOnePosts.getCode());
        Assertions.assertThat(dto.getUserId()).isEqualTo(findOnePosts.getUser().getUserId());
    }

    /**
     * 5. 전체 게시글 조회
     */
    @Test
    @Transactional(readOnly = true)
    public void 게시글_전체조회_페이징() {
        // given
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .userId(3)
                .problemLink("https://www.daou.co.kr")
                .problemSite("전체조회_페이징")
                .problemTitle("전체조회_페이징")
                .language("Vue.js")
                .title("전체조회_페이징.")
                .content("findAll")
                .code("THIS IS findAll Post Code")
                .build();

        Posts savePost = postsService.savePost(requestDto);

        // when
        PageRequest pageRequest = PageRequest.of(0, 6, Sort.by("modifiedDate").descending());
        Page<Posts> findByRecentPost = postsService.findAllPosts(pageRequest);
        int totalPage = findByRecentPost.getTotalPages();
        int totalSize = findByRecentPost.getSize();
        Posts recentPost = findByRecentPost.getContent().get(0);

        // then
        Assertions.assertThat(totalPage).isGreaterThan(0);
        Assertions.assertThat(totalSize).isGreaterThan(0);
        Assertions.assertThat(recentPost.getTitle()).isEqualTo(savePost.getTitle());
        Assertions.assertThat(recentPost.getUser().getUserId()).isEqualTo(3);
    }

    /**
     * 6. 언어별 풀이 조회
     */
    @Test
    @Transactional(readOnly = true)
    public void 언어별_전체조회() {
        // given
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .userId(3)
                .problemLink("https://www.daou.co.kr")
                .problemSite("전체조회_페이징")
                .problemTitle("전체조회_페이징")
                .language("TEST_JAVA")
                .title("언어별 조회 테스트 1번 객체")
                .content("findAllByLanguage")
                .code("findAllByLanguage")
                .build();

        PostsSaveRequestDto requestDto2 = PostsSaveRequestDto.builder()
                .userId(3)
                .problemLink("https://www.daou.co.kr")
                .problemSite("전체조회_페이징")
                .problemTitle("전체조회_페이징")
                .language("TEST_JAVA2")
                .title("언어별 조회 테스트 2번 객체")
                .content("findAllByLanguage")
                .code("THIS IS findAllByLanguage Code")
                .build();

        Posts savePost = postsService.savePost(requestDto);
        Posts savePost2 = postsService.savePost(requestDto2);

        // when
        PageRequest pageRequest = PageRequest.of(0, 6, Sort.by("modifiedDate").descending());
        Page<Posts> findByRecentPost = postsService.findAllPostsByLanguage("TEST_JAVA", pageRequest);
        Posts firstRequestPost = findByRecentPost.getContent().get(0);

        // then
        Assertions.assertThat(firstRequestPost.getTitle()).isEqualTo("언어별 조회 테스트 1번 객체");
        Assertions.assertThat(firstRequestPost.getLanguage()).isEqualTo("TEST_JAVA");
    }

    /**
     * 7. 플랫폼별 풀이 조회
     */
    @Transactional(readOnly = true)
    @Test
    public void 플랫폼별_조회() {
        // given
        PostsSaveRequestDto firstOne = PostsSaveRequestDto.builder()
                .userId(3)
                .problemLink("https://www.daou.co.kr")
                .problemSite("프로그래머스")
                .problemTitle("플랫폼별 조회")
                .language("TEST_JAVA")
                .title("플랫폼별 조회 테스트 1번 객체(프로그래머스)")
                .content("findAllByPlatform")
                .code("findAllByPlatform")
                .build();

        PostsSaveRequestDto secondOne = PostsSaveRequestDto.builder()
                .userId(3)
                .problemLink("https://www.daou.co.kr")
                .problemSite("백준")
                .problemTitle("플랫폼별 조회")
                .language("TEST_JAVA")
                .title("플랫폼별 조회 테스트 2번 객체(백준)")
                .content("findAllByPlatform")
                .code("findAllByPlatform")
                .build();

        postsService.savePost(firstOne);
        postsService.savePost(secondOne);

        // when
        PageRequest pageRequest = PageRequest.of(0, 6, Sort.by("modifiedDate").descending());
        Page<Posts> findByPlatform = postsService.findAllPostsByProblemSite("백준", pageRequest);
        Posts firstOneByPlatform = findByPlatform.getContent().get(0);

        // then
        Assertions.assertThat(firstOneByPlatform.getProblem().getProblemSite()).isEqualTo("백준");
        Assertions.assertThat(firstOneByPlatform.getTitle()).isEqualTo("플랫폼별 조회 테스트 2번 객체(백준)");
    }

    /**
     * 8. 언어 + 플랫폼별 조회
     */
    @Transactional(readOnly = true)
    @Test
    public void 언어_플랫폼으로_조회() {
        // given
        PostsSaveRequestDto firstOne = PostsSaveRequestDto.builder()
                .userId(3)
                .problemLink("https://www.daou.co.kr")
                .problemSite("FirstOne_Type")
                .problemTitle("언어와 플랫폼으로 조회하기")
                .language("FirstOne_Language")
                .title("언어+플랫폼 테스트 1번 객체")
                .content("findAllPostsByLanguageAndPlatform")
                .code("findAllPostsByLanguageAndPlatform")
                .build();

        PostsSaveRequestDto secondOne = PostsSaveRequestDto.builder()
                .userId(3)
                .problemLink("https://www.daou.co.kr")
                .problemSite("백준")
                .problemTitle("플랫폼별 조회")
                .language("TEST_JAVA")
                .title("언어+플랫폼 테스트 2번 객체")
                .content("findAllByPlatform")
                .code("findAllByPlatform")
                .build();

        PostsSaveRequestDto thirdOne = PostsSaveRequestDto.builder()
                .userId(3)
                .problemLink("https://www.daou.co.kr")
                .problemSite("FirstOne_Type")
                .problemTitle("언어와 플랫폼으로 조회하기 2번째 객체!")
                .language("FirstOne_Language")
                .title("언어+플랫폼 테스트 3번 객체")
                .content("findAllPostsByLanguageAndPlatform")
                .code("findAllPostsByLanguageAndPlatform")
                .build();

        postsService.savePost(firstOne);
        postsService.savePost(secondOne);
        postsService.savePost(thirdOne);

        // when
        PageRequest pageRequest = PageRequest.of(0, 6, Sort.by("modifiedDate").descending());
        Page<Posts> resultPosts = postsService.findAllPostsByLanguageAndProblemSite("FirstOne_Language", "FirstOne_Type", pageRequest);
        Posts firstOneByPlatform = resultPosts.getContent().get(0);
        Posts secondOneByPlatform = resultPosts.getContent().get(1);

        // then
        Assertions.assertThat(firstOneByPlatform.getLanguage()).isEqualTo(secondOneByPlatform.getLanguage());
        Assertions.assertThat(firstOneByPlatform.getProblem().getProblemSite()).isEqualTo(secondOneByPlatform.getProblem().getProblemSite());
        Assertions.assertThat(firstOneByPlatform.getTitle()).isEqualTo("언어+플랫폼 테스트 1번 객체");
        Assertions.assertThat(secondOneByPlatform.getTitle()).isEqualTo("언어+플랫폼 테스트 3번 객체");
    }

    /**
     * 9. 유저별 풀이 조회
     */
    @Transactional(readOnly = true)
    @Test
    public void 유저별_게시글_조회() {
        // given
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .userId(3)
                .problemLink("https://www.daou.co.kr")
                .problemSite("FirstOne_Type")
                .problemTitle("언어와 플랫폼으로 조회하기")
                .language("FirstOne_Language")
                .title("언어+플랫폼 테스트 1번 객체")
                .content("findAllPostsByLanguageAndPlatform")
                .code("findAllPostsByLanguageAndPlatform")
                .build();
        postsService.savePost(requestDto);

        // when
        PageRequest pageRequest = PageRequest.of(0, 6, Sort.by("modifiedDate").descending());
        Page<Posts> resultPosts = postsService.findAllPostsByUser(3, pageRequest);

        // then
        for (Posts post : resultPosts) {
            Assertions.assertThat(post.getUser().getUserId()).isEqualTo(3);
        }
    }

    /**
     * 10. 게시글 통합 검색
     */
    @Transactional
    @Test
    public void 게시글_통합검색() {
        // given
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .userId(3)
                .problemLink("https://www.daou.co.kr")
                .problemSite("SearchTest_Site")
                .problemTitle("통합 검색")
                .language("FirstOne_Language")
                .title("전체 검색을 실행합니다.")
                .content("search")
                .code("search")
                .build();
        postsService.savePost(requestDto);

        // when
        PageRequest pageRequest = PageRequest.of(0, 6, Sort.by("modifiedDate").descending());
        Page<Posts> resultPosts = postsService.searchAllByKeyword("통합 검색", pageRequest);

        // then
        List<Posts> results = resultPosts.getContent();
        Posts resultOne = results.get(0);
        Assertions.assertThat(results.get(0).getTitle()).isEqualTo(requestDto.getTitle());
    }

    /**
     * 11. 플랫폼별 검색
     */
    @Transactional
    @Test
    public void 게시글_플랫폼별_검색() {
        // given
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .userId(3)
                .problemLink("https://www.daou.co.kr")
                .problemSite("SearchTest_Site")
                .problemTitle("플랫폼별 검색")
                .language("SearchScript")
                .title("플랫폼별 검색 테스트")
                .content("컨텐츠 내용입니다")
                .code("search")
                .build();
        postsService.savePost(requestDto);

        // when
        PageRequest pageRequest = PageRequest.of(0, 6, Sort.by("modifiedDate").descending());
        Page<Posts> resultByLanguage = postsService.searchAllByPlatform("SearchScript", null, "컨텐츠", pageRequest);
        Page<Posts> resultBySite = postsService.searchAllByPlatform(null, "SearchTest_Site", "컨텐츠", pageRequest);

        // then
        Posts languageSearchTestResult = resultByLanguage.getContent().get(0);
        Posts siteSearchTestResult = resultBySite.getContent().get(0);
        Assertions.assertThat(languageSearchTestResult.getTitle()).isEqualTo("플랫폼별 검색 테스트");
        Assertions.assertThat(siteSearchTestResult.getTitle()).isEqualTo("플랫폼별 검색 테스트");
    }
}