package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class demoqaStudentRegistrationForm {

    @BeforeEach
    public void initSelenideListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    String url = "https://demoqa.com/automation-practice-form",
            firstName = "dmitriy",
            lastName = "lupachev",
            email = "user@stable.ru",
            mobile = "0123456789",
            dayBirth = "09",
            monthBirth = "April",
            yearBirth = "1987",
            subject1 = "Maths",
            subject1prefix = "m",
            subject2 = "Physics",
            subject2prefix = "p",
            hobby1 = "Reading",
            hobby2 = "Sports",
            picture = "3.jpg",
            currentAddress = "New address, 1",
            state = "Haryana",
            city = "Panipat";

    @Test
    void positiveStudentRegistrationFormTest() {
        step("Открываем страницу " + url, () -> open(url));
        step("Вводим значение " + firstName + " в поле First Name", () -> {
            $("#firstName").val(firstName);
        });
        step("Вводим значение " + lastName + " в поле Last Name", () -> {
            $("#lastName").val(lastName);
        });
        step("Вводим значение " + email + " в поле Email", () -> {
            $("#userEmail").val(email);
        });
        step("Выбираем пол (Gender)", () -> $("#genterWrapper").$(byText("Male")).click());
        step("Вводим значение " + mobile + " в поле Mobile", () -> {
            $("#userNumber").scrollTo().val(mobile);
        });
        step("Выбираем дату рождения " + dayBirth + " " + monthBirth + " " + yearBirth, () -> {
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption(monthBirth);
            $(".react-datepicker__year-select").selectOption(yearBirth);
            $(".react-datepicker__day--0" + dayBirth).click();
        });
        step("Заполняем поле Subjects значениями" + subject1 + " и " + subject2, () -> {
            $("#subjectsInput").val(subject1prefix);
            $(".subjects-auto-complete__menu-list").$(byText(subject1)).click();
            $("#subjectsInput").val(subject2prefix);
            $(".subjects-auto-complete__menu-list").$(byText(subject2)).click();
        });
        step("Выбираем значения " + hobby1 + " и " + hobby2 + " в поле Hobbies", () -> {
            $("#hobbiesWrapper").$(byText(hobby1)).click();
            $("#hobbiesWrapper").$(byText(hobby2)).click();
        });
        step("Загружаем картинку " + picture, () -> {
            $("#uploadPicture").uploadFromClasspath(picture);
        });
        step("Заполняем поле Current Address значением " + currentAddress, () -> {
            $("#currentAddress").val(currentAddress);
        });
        step("Выбираем значение " + state + " в поле State", () -> {
            $("#state").scrollTo().click();
            $("#stateCity-wrapper").$(byText(state)).click();
        });
        step("Выбираем значение " + city + " в поле City", () -> {
            $("#city").scrollTo().click();
            $("#stateCity-wrapper").$(byText(city)).click();
        });
        step("Нажимаем кнопку Submit", () -> $("#submit").scrollTo().click());
        step("Проверяем наличие появившейся формы", () -> {
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        });
        step("Проверяем корректность заполненных данных", () -> {
            $(".table-responsive").shouldHave(text(firstName + " " + lastName), text(email), text("Male"));

            $x("//td[text()='Mobile']").parent().shouldHave(text(mobile));

            $x("//td[text()='Date of Birth']").parent().shouldHave(text(dayBirth + " " + monthBirth + "," + yearBirth));

            $x("//td[text()='Subjects']").parent().shouldHave(text(subject1 + ", " + subject2));

            $x("//td[text()='Hobbies']").parent().shouldHave(text(hobby1 + ", " + hobby2));

            $x("//td[text()='Picture']").parent().shouldHave(text(picture));

            $x("//td[text()='Address']").parent().shouldHave(text(currentAddress));

            $x("//td[text()='State and City']").parent().shouldHave(text(state + " " + city));

        });
    }
}