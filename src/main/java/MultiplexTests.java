import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MultiplexTests {

    By menuTextx = By.cssSelector(".mob_container > div.menu-block > div");

    @Test
    public void checkMenuBtn() {
        // Устанавливаем путь к ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/tools/chromedriver.exe");
        // Инициализируем ChromeDriver (создаем объект ChromeDriver)
        ChromeDriver driver = new ChromeDriver();

        // Ожидание загрузки страницы
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        // Разворачиваем браузер на весь экран
        driver.manage().window().maximize();

        // Открываем Url multiplex.ua
        driver.get("https://multiplex.ua");
        // Нажимаем на кнопку "ДА" в Pop Up выбора города
        driver.findElement(By.cssSelector(".yes")).click();
        // Нажимаем на левое боковое меню
        driver.findElement(By.cssSelector(".menu-btn")).click();

        //Ожидание загрузки элемента .mob_container > div.menu-block > div
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(menuTextx));

        // Сохраняем в переменную menuText текст из элемента ".mob_container > div.menu-block > div"
        String menuText = driver
                .findElement(menuTextx)
                .getText();

        assertThat(menuText, equalTo("ОСОБИСТИЙ КАБІНЕТ"));

        //Закрываем браузер
        driver.quit();
    }

}
