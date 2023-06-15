package kopo.poly.dto;

import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
public class StudentDTO {
    private String userId; // 회원아이디
    private String userName; // 회원이름
    private String email; // 이메일
    private String addr; // 주소
}