import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class SelenideTest {

    @BeforeEach
    public void setupTest() {
        open("http://localhost:9999");
    }

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldDeliveryCard() {
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=city] input").setValue("Чебоксары");
        $("[data-test-id=date] input").doubleClick();
        String planningDate = generateDate(4);
        $("[data-test-id=date] input").sendKeys(planningDate);
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+79999999999");
        form.$("[data-test-id=agreement]").click();
        form.$(byText("Забронировать")).click();
        $(byText("Успешно!"))
                .shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldDeliveryCardSecond() {
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=city] input").setValue("Воронеж");
        $("[data-test-id=date] input").doubleClick();
        String planningDate = generateDate(5);
        $("[data-test-id=date] input").sendKeys(planningDate);
        form.$("[data-test-id=name] input").setValue("Иванов-Петров Иван");
        form.$("[data-test-id=phone] input").setValue("+79999999999");
        form.$("[data-test-id=agreement]").click();
        form.$(byText("Забронировать")).click();
        $(byText("Успешно!"))
                .shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldDeliveryCardThird() {
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=city] input").setValue("Барнаул");
        $("[data-test-id=date] input").doubleClick();
        String planningDate = generateDate(7);
        $("[data-test-id=date] input").sendKeys(planningDate);
        form.$("[data-test-id=name] input").setValue("Иванов Иван Младший");
        form.$("[data-test-id=phone] input").setValue("+79999999999");
        form.$("[data-test-id=agreement]").click();
        form.$(byText("Забронировать")).click();
        $(byText("Успешно!"))
                .shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldDeliveryCardForth() {
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=city] input").setValue("Барнаул");
        $("[data-test-id=date] input").doubleClick();
        String planningDate = generateDate(6);
        $("[data-test-id=date] input").sendKeys(planningDate);
        form.$("[data-test-id=name] input").setValue("Иванов Иван Младший");
        form.$("[data-test-id=phone] input").setValue("+79999999999");
        form.$("[data-test-id=agreement]").click();
        form.$(byText("Забронировать")).click();
        $(byText("Успешно!"))
                .shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
}