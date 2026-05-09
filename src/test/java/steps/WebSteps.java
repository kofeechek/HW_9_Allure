package steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;


public class WebSteps {

    @Step("Открываем страницу юзера на github")
    public void openUserPage() {
        open("https://github.com/kofeechek");
    }

    @Step("Заходим в раздел Repositories")
    public void clickRepositories() {
        $("a[href='/kofeechek?tab=repositories']").click();
    }

    @Step("Переходим в репозиторий HW_9_Allure")
    public void clickAllureRepository() {
        $("a[href='/kofeechek/HW_9_Allure']").click();
    }

    @Step("Раздел Issues существует")
    public void existIssues() {
        $(withText("Issues")).should(Condition.exist);
    }

}
