# springboot-board
스프링부트 + MySQL + 타임리프 게시판

# 프로젝트 디렉토리
```
src
├─main
│  ├─java
│  │  └─com
│  │      └─highgarden
│  │          └─springboot_board
│  │              │  SpringbootBoardApplication.java
│  │              │  
│  │              ├─controller
│  │              │      BoardController.java
│  │              │      IndexController.java
│  │              │      
│  │              ├─dto
│  │              │      BoardDTO.java
│  │              │      
│  │              ├─repository
│  │              │      BoardRepository.java
│  │              │      
│  │              └─service
│  │                      BoardService.java
│  │                      
│  └─resources
│      │  application.yml
│      │  mybatis-config.xml
│      │  
│      ├─mapper
│      │      board-mapper.xml
│      │      
│      ├─static
│      ├─templates
│      │      detail.html
│      │      index.html
│      │      list.html
│      │      save.html
│      │      update.html
│      │      
│      └─upload_files
└─test
```
