package steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import testData.TestData;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;


public class WebSteps {
    TestData testData = new TestData();
    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("https://github.com");
    }
    @Step("Нажимаем на поиск")
    public void clickSearchButton() {
        $x("//button[@data-action='click:qbsearch-input#handleExpand']").click();
    }
    @Step("Поиск репозитория")
    public void searchRepository() {
        $x("//input[@id='query-builder-test']").sendKeys(testData.repository);
        $x("//input[@id='query-builder-test']").submit();
    }
    @Step("Нажимаем на Issues")
    public void clickIssues() {
        $x("//a[@href='/LazVal/qaGuru-Allure']").click();
        $("#issues-tab").click();
    }
    @Step("Проверка наличия Issues")
    public void existIssues() {
        $(withText("TestIssues")).should(Condition.exist);
    }

}