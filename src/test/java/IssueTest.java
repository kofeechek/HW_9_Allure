import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import io.qameta.allure.selenide.AllureSelenide;
import steps.WebSteps;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.step;


public class IssueTest {
    @AfterEach
    void closeWebDriver() {
        Selenide.closeWebDriver();
    }
    @Test
    @Feature("Github Issue")
    @Story("Создание Issue")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Чистый Selenide (с Listener)")
    @Tag("Test")
    @Owner("SvetlitskayaTS")
    public void issueSearchListenerTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com/kofeechek");

        $("a[href='/kofeechek?tab=repositories']").click();
        $("a[href='/kofeechek/HW_9_Allure']").click();

        $(withText("Issues")).should(Condition.exist);
    }

    @Test
    @Feature("Github Issue")
    @Story("Создание Issue")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Лямбда шаги через step (name, () -> {})")
    @Tag("Test")
    @Owner("SvetlitskayaTS")
    public void issueSearchLambdaTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем страницу юзера", () -> {
            open("https://github.com/kofeechek");
        });

        step("Заходим в раздел Repositories", () -> {
            $("a[href='/kofeechek?tab=repositories']").click();
        });

        step("Переходим в репозиторий HW_9_Allure", () -> {
            $("a[href='/kofeechek/HW_9_Allure']").click();
        });

        step("Раздел Issues существует", () -> {
            $(withText("Issues")).should(Condition.exist);
        });

    }

    @Test
    @Feature("Github Issue")
    @Story("Создание Issue")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Шаги с аннотацией @Step")
    @Tag("Test")
    @Owner("SvetlitskayaTS")
    public void issueSearchStepsTest() {
        WebSteps steps = new WebSteps();

        steps.openUserPage();
        steps.clickRepositories();
        steps.clickAllureRepository();
        steps.existIssues();
    }

}