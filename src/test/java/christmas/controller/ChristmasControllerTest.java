package christmas.controller;

import static christmas.config.Configuration.christmasController;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import camp.nextstep.edu.missionutils.Console;
import christmas.config.Configuration;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.Duration;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChristmasControllerTest {
    private PrintStream standardOut;
    private OutputStream captor;

    @BeforeEach
    void setUp() {
        standardOut = System.out;
        captor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
        System.out.println(captor.toString().trim());
        Console.close();
    }

    @DisplayName("게임의 정상 실행 확인")
    @Test
    void run_() {
        command("25", "양송이수프-1,티본스테이크-1,초코케이크-1,레드와인-1");
        christmasController().run();
        assertThat(captor.toString())
                .contains(
                        "12월 25일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!",
                        "<주문 메뉴>",
                        "양송이수프 1개",
                        "티본스테이크 1개",
                        "초코케이크 1개",
                        "레드와인 1개",
                        "<할인 전 총주문 금액>",
                        "136,000원",
                        "<증정 메뉴>",
                        "샴페인 1개",
                        "<혜택 내역>",
                        "크리스마스 디데이 할인: -3,400원",
                        "평일 할인: -2,023원",
                        "특별 할인: -1,000원",
                        "증정 이벤트: -25,000원",
                        "<총혜택 금액>",
                        "-31,423원",
                        "<할인 후 예상 결제 금액>",
                        "129,577원",
                        "<12월 이벤트 배지>",
                        "산타"
                );
    }

    @DisplayName("날짜에 대한 예외 발생시 반복 입력 확인")
    @Test
    void run_withInvalidDate() {
        command(
                "a", // 문자가 입력된 경우
                "", // 아무 입력도 없는 경우
                "123", // 범위를 초과하는 입력인 경우
                "32", // 범위를 초과하는 입력인 경우
                "0" // 범위를 초과하는 입력인 경우
        );
        try {
            assertTimeoutPreemptively(Duration.ofSeconds(1L), christmasController()::run);
        } catch (NoSuchElementException ignored) {
        }
    }

    @DisplayName("메뉴에 대한 예외 발생시 반복 입력 확인")
    @Test
    void run_WithInvalidMenuAndCount() {
        command(
                "25", // 날짜는 정상 입력
                "", // 빈 입력
                "abc",
                "김치찌개-1,타파스-1,양송이수프-1,티본스테이크-1", // 없는 메뉴
                "타파스-1/양송이수프-1/티본스테이크-1", // 구분자 오류
                "양송이수프:1/티본스테이크:1/레드와인:1", // 구분자 오류
                "양송이수프-1,양송이수프-1,바비큐립-1", // 중복 메뉴
                "양송이수프-19,티본스테이크-2,제로콜라-1" // 메뉴 20개 초과
        );
        try {
            assertTimeoutPreemptively(Duration.ofSeconds(1L), christmasController()::run);
        } catch (NoSuchElementException ignored) {
        }
    }

    private void command(final String... args) {
        final byte[] buf = String.join("\n", args).getBytes();
        System.setIn(new ByteArrayInputStream(buf));
    }
}