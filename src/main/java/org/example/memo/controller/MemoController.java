package org.example.memo.controller;

import org.example.memo.dto.MemoRequestDto;
import org.example.memo.dto.MemoResponseDto;
import org.example.memo.entity.Memo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController // 우리는 항상 Json 형태로 주고 받는다.
@RequestMapping("/memos")
public class MemoController {

    private final Map<Long, Memo> memoList = new HashMap<>();

    @PostMapping
    public ResponseEntity<MemoResponseDto> createMemo(@RequestBody MemoRequestDto dto) {
        //ResponseEntity 상태코드를 반환하기 위함

        // 식별자가 1씩 증가 하도록 만듦
        Long memoId = memoList.isEmpty() ? 1 : Collections.max(memoList.keySet()) + 1;

        // 요청받은 데이터로 Momo 객체 생성
        Memo memo = new Memo(memoId, dto.getTitle(), dto.getContents());

        // Inmemory DB에 Memo 메모
        memoList.put(memoId, memo);

        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.CREATED);
    }

    @GetMapping
    public List<MemoResponseDto> findAllMemos() {

        // init List
        List<MemoResponseDto> responseList = new ArrayList<>();

        // HashMap<Memo> -> List<MemoResponseDto>
        for (Memo memo : memoList.values()) {
            MemoResponseDto responseDto = new MemoResponseDto(memo);
            responseList.add(responseDto);
        }

//        // Map To ListStream 사용하여 짧게 코딩 가능
//        responseList = memoList.values().stream().map(MemoResponseDto::new).toList();

        return responseList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemoResponseDto> findMemoById(@PathVariable Long id) {

        Memo memo = memoList.get(id);

        if (memo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemoResponseDto> updateMemoById(@PathVariable Long id, @RequestBody MemoRequestDto dto) {

        Memo memo = memoList.get(id);

        if (memo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (dto.getTitle() == null || dto.getContents() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        memo.update(dto);

        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MemoResponseDto> updateTitle(
            @PathVariable Long id, @RequestBody MemoRequestDto dto) {

        Memo memo = memoList.get(id); //저장된 메모리스트에서 메모를 꺼내온다.

        // NPE방지 만약 없을 경우를 대비해서,
        if (memo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (dto.getTitle() == null || dto.getContents() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        memo.updateTitle(dto);

        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemo(@PathVariable Long id) {

        // memoList의 Key값에 id를 포함하고 있다면,
        if (memoList.containsKey(id)) {
            memoList.remove(id);

            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
