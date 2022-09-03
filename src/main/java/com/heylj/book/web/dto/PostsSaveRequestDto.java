package com.heylj.book.web.dto;

import com.heylj.book.domain.posts.Posts;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    @Schema(description = "제목", defaultValue = "title")
    private String title;
    @Schema(description = "내용", defaultValue = "content")
    private String content;
    @Schema(description = "작성자", defaultValue = "author")
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
