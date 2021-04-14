package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pageobjects.AutomationPracticeFormPage;


public class AutomationPracticeFormTests extends TestBase {

    AutomationPracticeFormPage steps = new AutomationPracticeFormPage();

    @Test
    void positiveScenario() {
        steps.openPage();
        steps.fillForm();
        steps.sendForm();
        steps.checkForm();
    }

    @Test
    void negativeScenario() {
        steps.openPage();
        steps.fillForm();
        steps.sendFormNegative();
        steps.checkForm();
    }

}

