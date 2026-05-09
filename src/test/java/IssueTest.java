import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import steps.WebSteps;
import testData.TestData;
import io.qameta.allure.selenide.AllureSelenide;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.step;


public class IssueTest {
    @Test
    @Feature("Github Issue")
    @Story("Создание Issue")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Чистый Selenide (с Listener)")
    @Tag("Test")
    @Owner("SvetlitskayaTS")
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com");

        $x("//button[@data-action='click:qbsearch-input#handleExpand']").click();
        $x("//input[@id='query-builder-test']").sendKeys("kofeechek/HW_9_Allure");
        $x("//input[@id='query-builder-test']").submit();

        $x("//a[@href='/LazVal/qaGuru-Allure']").click();
        $("#issues-tab").click();
        $(withText("TestIssues")).should(Condition.exist);
    }

    TestData testData = new TestData();
    @Test
    @Feature("Github Issue")
    @Story("Создание Issue")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Лямбда шаги через step (name, () -> {})")
    @Tag("Test")
    @Owner("SvetlitskayaTS")
    public void testIssueSearchLambda() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });

        step("Нажимаем на поиск", () -> {
            $x("//button[@data-action='click:qbsearch-input#handleExpand']").click();
        });

        step("Ищем репозиторий", () -> {
            $x("//input[@id='query-builder-test']").sendKeys(testData.repository);
            $x("//input[@id='query-builder-test']").submit();
        });

        step("Нажимаем на Issues", () -> {
            $x("//a[@href='/kofeechek/HW_9_Allure']").click();
            $("#issues-tab").click();
        });

        step("Проверка наличия Issue", () -> {
            $(withText("TestIssues")).should(Condition.exist);
        });
    }

    @Test
    @Feature("Github Issue")
    @Story("Создание Issue")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Шаги с аннотацией @Step")
    @Tag("Test")
    @Owner("SvetlitskayaTS")
    public void testIssueSearchSteps() {
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.clickSearchButton();
        steps.searchRepository();
        steps.clickIssues();
        steps.existIssues();
    }

}