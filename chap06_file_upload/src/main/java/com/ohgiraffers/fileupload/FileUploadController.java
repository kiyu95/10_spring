package com.ohgiraffers.fileupload;

import jakarta.annotation.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class FileUploadController {

    @Resource
    private ResourceLoader resourceLoader;

    @RequestMapping(value = {"/", "main"}) // /랑 main요청 처리
    public String index(){
        return "main";
    }

    @PostMapping("single-file") // main.html과 연결
    public String singFileUpload(@RequestParam MultipartFile singleFile, String singleFileDescription, Model model) throws IOException {
                                // post로 넘어오니까 RequestParam로 받아준다

        System.out.println("single file : " + singleFile);
        System.out.println("원본 파일 이름: " + singleFile.getOriginalFilename()); // 파일의 이름
        System.out.println("input name : " + singleFile.getName()); // input 태그의 name
//        System.out.println("원본 파일 객체 : " + singleFile.getBytes());  // 컴퓨터가 인식하고있는 실제 2진 데이터 값의 주소값 출력
        System.out.println("원본 파일 사이즈 : " + singleFile.getSize()); // 파일의 용량
        System.out.println("singleFileDescription : " + singleFileDescription);

        // 파일을 저장할 경로 설정
//        String root = "c:/upload-files";
//        String filePath = root + "/single";

        String filePath = resourceLoader.getResource("classpath:static/img/").getFile().getAbsolutePath();

        File dir = new File(filePath);
        System.out.println(dir.getAbsolutePath());

        if (!dir.exists()) { // 파일 경로가 없으면
            dir.mkdirs(); // 경로를 만들겠다
        }

        String originFileName = singleFile.getOriginalFilename();
        String ext = originFileName.substring(originFileName.lastIndexOf(".")); // JAVA.png 에서 .png(확장자) 획득
        String savedName = UUID.randomUUID().toString().replace("-", "") + ext; // UUID : 중복되지않게 난수 발생, - 삭제 + ext(확장자)

        try {
            System.out.println("filePath ========== " + filePath);
            singleFile.transferTo(new File(filePath + "/" + savedName));
            model.addAttribute("message", "파일 업로드 성공");
            model.addAttribute("img", "static/img/" + savedName);
        } catch (IOException e){
            e.printStackTrace();
            model.addAttribute("message", "파일 업로드 실패");
        }

        return "result"; // 반환받을 result.html을 생성해주어야 한다.
    }

    @PostMapping("multi-file")
    public String multiFileUpload(@RequestParam List<MultipartFile> multipartFiles,
                                  String multiFileDescription, Model model){
        System.out.println("multiFiles : " + multipartFiles);
        System.out.println("multiFileDescription : " + multiFileDescription);

        String root = "c:/upload-files";
        String filePath = root + "/multi";

        File dir = new File(filePath);
        if (!dir.exists()){
            dir.mkdirs();
        }

        List<FileDTO> files = new ArrayList<>();


            try {
                for (MultipartFile file : multipartFiles) {
                    String originFileName = file.getOriginalFilename();
                    String ext = originFileName.substring(originFileName.lastIndexOf("."));
                    String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
                    files.add(new FileDTO(originFileName, savedName, filePath, multiFileDescription));
                    file.transferTo(new File(filePath, "/" + savedName));
                }
                model.addAttribute("message", "다중 파일 업로드 성공");

            } catch (IOException e) {
              e.printStackTrace();

              for (FileDTO file : files){
                  new File(filePath + "/" + file.getSavedName()).delete();
              }
              model.addAttribute("message", "다중 파일 업로드 실패");
        }
            return "result";
    }
}
