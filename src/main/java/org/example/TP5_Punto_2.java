package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TP5_Punto_2 {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false).setSlowMo(1000));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            //Login fallido

            String Msg_error_login ="Epic sadface: Username and password do not match any user in this service";

            page.navigate("https://www.saucedemo.com/");
            page.locator("[data-test=\"username\"]").click();
            page.locator("[data-test=\"username\"]").fill("standard_user");
            page.locator("[data-test=\"password\"]").click();
            page.locator("[data-test=\"password\"]").fill("secret_sauce2");
            page.locator("[data-test=\"login-button\"]").click();

            //Localiza el elemento del error en la página.
            Locator errorElement = page.locator("[data-test=\"error\"]");

            //Verifica que el elemento del error contiene el texto esperado.
            assertThat(errorElement).hasText(Msg_error_login);
            System.out.println("Validación de mensaje de error correcta.");






        }
    }
}