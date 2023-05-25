package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.*;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class AddNewContactsTests extends AppiumConfig {

    @BeforeClass
    public void preCondition() {
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("marym@gmail.com").password("Mm12345@").build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
    }


    @Test(invocationCount = 3)
    public void createNewContactSuccess() {
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Bary")
                .lastName("Bo" + i)
                .email("bob" + i + "@gmail.com")
                .phone("98765432" + i)
                .address("London")
                .description("friend")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(), contact.getLastName());
    }

    @Test
    public void createContactWithEmptyName() {
        Contact contact = Contact.builder()

                .lastName("DDDD")
                .email("ddd@gmail.com")
                .phone("9876543222222")
                .address("London")
                .description("Empty name")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorContainsText("{name=must not be blank}");

    }
    public void createNewContactSuccessReq(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact =Contact.builder()
                .name("Wolf")
                .lastName("Wow"+i)
                .email("wow"+i+"@mail.com")
                .phone("1234572345")
                .address("Rehovot")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(),contact.getLastName());


    }
    @Test
    public void addNewContactEmptyLastName(){

        Contact contact =Contact.builder()
                .name("Neg")
                .email("neg@mail.com")
                .phone("1234572345")
                .address("Rehovot")
                .description("The best friend").build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorContainsText("Error");
    }


    @AfterClass
    public void postCondition() {
        new ContactListScreen(driver).logout();
    }
}
