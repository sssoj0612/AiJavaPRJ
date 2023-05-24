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
}
