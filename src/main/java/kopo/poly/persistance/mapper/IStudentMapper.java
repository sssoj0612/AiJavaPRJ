package kopo.poly.persistance.mapper;
import kopo.poly.dto.StudentDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface IStudentMapper {
    void insertStudent(StudentDTO pDTO) throws Exception; // 입력

    List<StudentDTO> getStudentList() throws Exception; // 조회 결과 여러명 (List로 결과 받기)

    StudentDTO getStudent(StudentDTO pDTO) throws Exception; // 조회 결과 한 명 (DTO로 결과 받기)

    void updateStudent(StudentDTO pDTO) throws Exception; // 수정

    void deleteStudent(StudentDTO pDTO) throws Exception; // 삭제
}