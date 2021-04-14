package pageobjects;

import com.github.javafaker.Faker;
import tests.TestBase;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class AutomationPracticeFormPage extends TestBase {

    Faker faker = new Faker();

    private String site = "https://demoqa.com/automation-practice-form",
            firstName = faker.name().firstName(),
            lastName  = faker.name().lastName(),
            userEmail = faker.internet().emailAddress();

    public void openPage() {
        step("Open students registration form", () -> {
            open(site);
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        });
    }

    public void fillForm() {
        step("Fill students registration form", () -> {
            step("Fill common data", () -> {
                $("#firstName").setValue(firstName);
                $("#lastName").setValue(lastName);
                $("#userEmail").setValue(userEmail);
                $("label[for='gender-radio-1']").click();
                $("#userNumber").setValue("0123456789");
            });
            step("Set date", () -> {
                $("#dateOfBirthInput").click();
                $(".react-datepicker__month-select").selectOptionByValue("0");
                $(".react-datepicker__year-select").selectOptionByValue("1980");
                $(".react-datepicker__day.react-datepicker__day--001").click();
            });
            step("Set subjects", () -> {
                $("#subjectsInput").setValue("English").pressEnter();
                $("#subjectsInput").setValue("Hindi").pressEnter();
            });
            step("Set hobbies", () -> $("[for='hobbies-checkbox-1']").click());
            step("Upload image", () -> $("#uploadPicture").uploadFromClasspath("test.png"));
            step("Set address", () -> {
                $("#currentAddress").setValue("Saint-Petersburg, Singer House");
                $("#react-select-3-input").setValue("NCR").pressEnter();
                $("#react-select-4-input").setValue("Delhi").pressEnter();
            });
        });
    }

    public void sendForm() {
        step("Send form", () -> $("#submit").click());
    }

    public void sendFormNegative() {
        step("Send form", () -> $("#submit1").click());
    }

    public void checkForm() {
        step("Verify successful form submit", () -> {
            $(".table").shouldHave(text(firstName),
                    text(lastName),
                    text(userEmail),
                    text("Male"),
                    text("0123456789"),
                    text("1 January,1980"),
                    text("English, Hindi"),
                    text("Sports"),
                    text("test.png"),
                    text("Saint-Petersburg, Singer House"),
                    text("NCR Delhi"));
        });
    }

}
