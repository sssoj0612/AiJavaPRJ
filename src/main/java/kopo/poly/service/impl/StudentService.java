package kopo.poly.service.impl;

import kopo.poly.dto.StudentDTO;
import kopo.poly.persistance.mapper.IStudentMapper;
import kopo.poly.service.IStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentService implements IStudentService {
    private final IStudentMapper studentMapper;

    // 입력
    @Override
    public List<StudentDTO> insertStudent(StudentDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".insertStudent Start!");

        // 테이블에 등록 된 학생 아이디가 존재하는지 체크하기 위해 DB 조회
        Optional<StudentDTO> res = Optional.ofNullable(
                studentMapper.getStudent(pDTO)
        );

        // DB 조회 결과 존재하지 않는다면
        if (!res.isPresent()) {
            studentMapper.insertStudent(pDTO); // 학생 등록 sql 실행
        }

        // 학생 테이블 전체 조회
        List<StudentDTO> rList = Optional.ofNullable(
                studentMapper.getStudentList()
        ).orElseGet(ArrayList::new);

        log.info(this.getClass().getName() + ".insertStudent End!");

        return rList;
    }




    // 여러명 입력
    @Override
    public List<StudentDTO> insertStudentList(List<StudentDTO> pList) throws Exception {
        log.info(this.getClass().getName() + ".insertStudent Start!");
        Optional<StudentDTO> res = Optional.ofNullable(
                studentMapper.getStudent((StudentDTO) pList)
        );

        // DB 조회 결과 존재하지 않는다면
        if (!res.isPresent()) {
            studentMapper.insertStudent((StudentDTO) pList); // 학생 등록 sql 실행
        }

        // 학생 테이블 전체 조회
        List<StudentDTO> rList = Optional.ofNullable(
                studentMapper.getStudentList()
        ).orElseGet(ArrayList::new);

        log.info(this.getClass().getName() + ".insertStudent End!");

        return pList;
    }





    // 조회
    @Override
    public List<StudentDTO> getStudentList() throws Exception {
        log.info(this.getClass().getName() + ".getStudent Start!");
        return null;
    }





    // 수정
    @Override
    public List<StudentDTO> updateStudent(StudentDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".updateStudent Start!");

        Optional<StudentDTO> res = Optional.ofNullable(
                studentMapper.getStudent(pDTO));

        if (res.isPresent()) {
            studentMapper.updateStudent(pDTO);
            log.info(pDTO.getUserId() + "님이 수정되었습니다.");
        } else {
            log.info("회원이 존재하지 않아 수정되지 못했습니다.");
        }

        List<StudentDTO> rList = Optional.ofNullable( // 학생 테이블 전체 조회하기
                studentMapper.getStudentList()).orElseGet(ArrayList::new);

        log.info(this.getClass().getName() + ".updateStudent End!");

        return rList;
    }





    // 삭제
    @Override
    public List<StudentDTO> deleteStudent(StudentDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".deleteStudent Start!");

        Optional<StudentDTO> res = Optional.ofNullable(
                studentMapper.getStudent(pDTO)
        );

        if (res.isPresent()) {
            studentMapper.deleteStudent(pDTO);
            log.info(pDTO.getUserId() + "님이 삭제되었습니다.");
        } else {
            log.info("회원이 존재하지 않아 삭제되지 못했습니다.");
        }

        List<StudentDTO> rList = Optional.ofNullable( // 학생 테이블 전체 조회하기
                studentMapper.getStudentList()
        ).orElseGet(ArrayList::new);

        log.info(this.getClass().getName() + ".deleteStudent End!");

        return rList;
    }
}