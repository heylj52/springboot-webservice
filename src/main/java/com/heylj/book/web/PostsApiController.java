package com.heylj.book.web;

import com.heylj.book.domain.posts.Posts;
import com.heylj.book.domain.posts.PostsService;
import com.heylj.book.web.dto.PostsResponseDto;
import com.heylj.book.web.dto.PostsSaveRequestDto;
import com.heylj.book.web.dto.PostsUpdateRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "게시물", description = "게시물 관련 api 입니다.")
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @Operation(summary = "게시물 등록", description = "게시물 등록 메서드입니다.")
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @Operation(summary = "게시물 수정", description = "게시물 수정 메서드입니다.")
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @Operation(summary = "게시물 읽기", description = "게시물 읽기 메서드입니다.")
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @Operation(summary = "게시물 삭제", description = "게시물 삭제 메서드입니다.")
    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }
}
