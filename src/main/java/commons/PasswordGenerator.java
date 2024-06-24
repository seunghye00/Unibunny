package commons;
import java.util.Random;
//비밀번호 랜덤 생성
public class PasswordGenerator {

    // 비밀번호 길이 설정
    private final int PASSWORD_LENGTH = 8;

    // 비밀번호 생성 메서드
    public String generateRandomPassword() {
        // 사용할 문자열 정의
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";

        // StringBuilder를 이용하여 비밀번호 생성
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }
}
