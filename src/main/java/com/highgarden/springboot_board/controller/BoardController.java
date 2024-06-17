package com.highgarden.springboot_board.controller;

import com.highgarden.springboot_board.dto.BoardDTO;
import com.highgarden.springboot_board.dto.BoardFileDTO;
import com.highgarden.springboot_board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/save")
    public String save(){
        return "save";
    }

    @PostMapping("/save")
    public String save(BoardDTO boardDTO) throws IOException {
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "redirect:/list";
    }

    /*
     * 글의 목록을 불러온다. 목록화면에서는 글의 상세내용이 필요 없기 때문에 실제로 보여지는 데이터만 가져오면 된다.
     * 떄문에 sql문도 필요없는 데이터는 작성하지 않도록 한다.
     * */
    @GetMapping("/list")
    public String findAll(Model model){
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        System.out.println("boardDTOList = " + boardDTOList);
        return "list";
    }

    /*
     * @PathVariable을 통해 url에 id만 주어졌을 때 parameter를 읽어올 수 있도록 함
     *
     * */
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        //조회수 처리
        boardService.updateHits(id);
        //상세내용 가져오기
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        System.out.println("boardDTO = " + boardDTO);
        if(boardDTO.getFileAttached() == 1){
            List<BoardFileDTO> boardFileDTOList = boardService.findFile(id);
            model.addAttribute("boardFileDTOList", boardFileDTOList);
        }
        return "detail";
    }

    /*
     * update요청이 들어오면 상세화면으로 이동시킨 다음 수정에 된 데이터를 서버로 보내는 작업이 순차적으로 진행되어야 함.
     * 때문에 같은 url요청이더라고 get이냐 post냐에 따라서 하는 역할이 달라지도록 구현할 수 있음.
     * get요청인 경우 선택된 게시글의 상세정보를 db에서 받아오고
     * post요청인 경우 수정된 값을 db에 update 요청하도록 함.
     * 글목록 조회 -> 상세조회 -> 수정버튼 -> 상세글정보 db요청(상세조회) -> 수정화면으로 이동 -> 수정요청 -> 상세조회(수정된값 반영)
     * 수정하는 데에 상세조회요청이 3번 일어남
     * */
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model){
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "update";
    }

    @PostMapping("/update/{id}")
    public String update(BoardDTO boardDTO, Model model){
        boardService.update(boardDTO);
        BoardDTO dto = boardService.findById(boardDTO.getId());
        model.addAttribute("board", dto);
        return "detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        boardService.delete(id);
        return "redirect:/list";
    }
}
