package data;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

public class FakerData {
    @DataProvider
    public Object[][] fakeRegistrationData(){
        Faker faker=new Faker();
        return new Object[][]{
                {
                    faker.name().username(),
                    faker.internet().emailAddress(),
                    faker.regexify("[A-Za-z0-9_+=]{12}")
                }
        };
    }

}
