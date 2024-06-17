package com.highgarden.springboot_board.service;

import com.highgarden.springboot_board.dto.BoardDTO;
import com.highgarden.springboot_board.dto.BoardFileDTO;
import com.highgarden.springboot_board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void save(BoardDTO boardDTO) throws IOException {
        if(boardDTO.getBoardFile().get(0).isEmpty()){
            //파일 없음
            boardDTO.setFileAttached(0);
            boardRepository.save(boardDTO);
        }else{
            boardDTO.setFileAttached(1);//파일 존재함
            BoardDTO savedBoard = boardRepository.save(boardDTO);

            for(MultipartFile boardFile : boardDTO.getBoardFile()){
                String originalFilename = boardFile.getOriginalFilename();
                System.out.println("originalFilename = " + originalFilename);

                System.out.println(System.currentTimeMillis());
                String storedFileName = System.currentTimeMillis()+"_"+originalFilename;
                System.out.println("storedFileName = " + storedFileName);

                BoardFileDTO boardFileDTO = new BoardFileDTO();
                boardFileDTO.setOriginalFileName(originalFilename);
                boardFileDTO.setStoredFileName(storedFileName);
                boardFileDTO.setBoardId(savedBoard.getId());

                String savePath = "D:/board_tistory/springboot-board/src/main/resources/upload_files/"+storedFileName;
                boardFile.transferTo(new File(savePath));
                boardRepository.saveFile(boardFileDTO);
            }
        }
    }
    public List<BoardDTO> findAll() {
        return boardRepository.findAll();
    }
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }
    public BoardDTO findById(Long id) {
        return boardRepository.findById(id);
    }
    public void update(BoardDTO boardDTO) {
        boardRepository.update(boardDTO);
    }
    public void delete(Long id) {
        boardRepository.delete(id);
    }

    public List<BoardFileDTO> findFile(Long id) {
        return boardRepository.findFile(id);
    }
}
