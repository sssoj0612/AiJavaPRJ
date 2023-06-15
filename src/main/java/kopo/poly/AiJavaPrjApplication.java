/*
package kopo.poly;
import kopo.poly.dto.OcrDTO;
import kopo.poly.service.IOcrService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class AiJavaPrjApplication implements CommandLineRunner {

    // @Service 정의 된 자바 파일
    // Spring Frameworks 실행될 때, @Service 정의한 자바는 자동으로 메모리에 올림
    // 메모리에 올라간 OcrService 객체를 acrService 변수에 객체를 넣어주기
    private final IOcrService ocrService; // 이미지 인식
    public static void main(String[] args) {

        SpringApplication.run(AiJavaPrjApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {

        log.info("자바 프로그래밍 시작!!");

        String filePath = "image"; // 문자열을 인식할 이미지 파일 경로
        String fileName = "sample02.jpg"; // 문자열을 인식할 이미지 파일 이름

        // 전달할 값(파라미터) 약자로 보통 변수명 앞에 p를 붙임
        OcrDTO pDTO = new OcrDTO(); // OcrService의 함수에 정보를 전달할 DTO를 메모리에 올리기

        pDTO.setFilePath(filePath);
        pDTO.setFileName(fileName);

        // 실행 결과(Result) 약자로 보통 변수명 앞에 r을 붙임
        OcrDTO rDTO = ocrService.getReadforImageText(pDTO);

        String result = rDTO.getResult(); // 인식된 문자열

        log.info("인식 된 문자열");
        log.info(result);

        log.info("자바 프로그래밍 종료!!");
    }
}*/

package kopo.poly;
import kopo.poly.dto.StudentDTO;
import kopo.poly.service.INlpService;
import kopo.poly.service.IOcrService;
import kopo.poly.service.IStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class AiJavaPrjApplication implements CommandLineRunner {

    private final IOcrService ocrService; // 이미지 인식
    private final INlpService nlpService; //자연어 처리
    private final IStudentService studentService;

    public static void main(String[] args) {

        SpringApplication.run(AiJavaPrjApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("자바 프로그래밍 시작!!");

        StudentDTO pDTO; // 학생 등록, 수정, 삭제에 활용될 DTO 선언
        List<StudentDTO> rList; // DB 조회 결과를 표현하기 위해 List 선언


        // 입력
        pDTO = new StudentDTO();

        pDTO.setUserId("hglee67");
        pDTO.setUserName("이협건");
        pDTO.setEmail("hglee67@kopo.ac.kr");
        pDTO.setAddr("서울");

        rList = studentService.insertStudent(pDTO); // 서비스 호출

        rList.forEach(dto -> { // 조회 결과 출력
            log.info("DB에 저장된 아이디 : " + dto.getUserId());
            log.info("DB에 저장된 이름 : " + dto.getUserName());
            log.info("DB에 저장된 이메일 : " + dto.getEmail());
            log.info("DB에 저장된 주소 : " + dto.getAddr());
        });





        // 수정
        pDTO = new StudentDTO();
        pDTO.setUserId("hglee67"); // pk컬럼인 회원아이디를 기준

        pDTO.setUserId("hglee67");
        pDTO.setUserName("이협건_수정");
        pDTO.setEmail("hglee67@kopo.ac.kr_수정");
        pDTO.setAddr("서울_수정");

        rList = studentService.updateStudent(pDTO);

        rList.forEach(dto -> {
            log.info("DB에 저장된 아이디 : " + dto.getUserId());
            log.info("DB에 저장된 이름 : " + dto.getUserName());
            log.info("DB에 저장된 이메일 : " + dto.getEmail());
            log.info("DB에 저장된 주소 : " + dto.getAddr());
        });





        // 여러개 입력
        List<StudentDTO> pList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            StudentDTO myDTO = new StudentDTO();

            myDTO.setUserId(i + "");
            myDTO.setUserName(i + "");
            myDTO.setEmail(i + "");
            myDTO.setAddr(i + "");

            pList.add(myDTO);

            myDTO = null;
        }

        pList.parallelStream().forEach(dto -> {
            try {
                studentService.insertStudent(dto);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        rList = studentService.getStudentList();

        studentService.insertStudentList(pList);
        rList = studentService.getStudentList();






        /*// 삭제
        pDTO = new StudentDTO();

        pDTO.setUserId("hglee67"); // pk컬럼인 회원아이디를 기준


        rList.forEach(dto -> {
            log.info("DB에 저장된 아이디 : " + dto.getUserId());
            log.info("DB에 저장된 이름 : " + dto.getUserName());
            log.info("DB에 저장된 이메일 : " + dto.getEmail());
            log.info("DB에 저장된 주소 : " + dto.getAddr());
        });


        log.info("자바 프로그래밍 종료!!");*/
    }
}