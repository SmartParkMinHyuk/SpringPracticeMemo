package org.example.memo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.memo.dto.MemoRequestDto;

@Getter
@AllArgsConstructor
public class Memo {

    private Long id; // int보다 훨씬 더 크고, 클래스기 때문에 null 가능.
    private String title;
    private String contents;

    public void update(MemoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void updateTitle(MemoRequestDto requestDto) {
        this.title = requestDto.getTitle();
    }
}
