package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class ContactListScreen extends BaseScreen {
    public ContactListScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    AndroidElement activityTextView;
    @FindBy(xpath = "//*[@content-desc='More options']")
    AndroidElement menuOptions;
    @FindBy(xpath = "//*[@text='Logout']")
    AndroidElement logoutButton;
    @FindBy(xpath = "//*[@content-desc = 'add']")
    AndroidElement plusButton;
    @FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/rowName']")
    List<AndroidElement> contactNameList;

    public AddNewContactScreen openContactForm() {
       // should(plusButton,10);
        if(activityTextView.getText().equals("Contact list"))
        plusButton.click();
        return new AddNewContactScreen(driver);
    }

    public ContactListScreen isAccountOpened() {
        Assert.assertTrue(isActivityTitleDisplayed("Contact list"));
        return this;
    }

    public boolean isActivityTitleDisplayed(String text) {
        // return activityTextView.getText().contains("Contact List");
        return isShouldHave(activityTextView, text, 8);
    }

    public AuthenticationScreen logout() {
        if(activityTextView.getText().equals("Contact list")) {
            menuOptions.click();
            logoutButton.click();
        }
            return new AuthenticationScreen(driver);


    }

    public ContactListScreen isContactAddedByName(String name, String lastName) {
        isShouldHave(activityTextView, "Contact list", 15);
        System.out.println("size of list " + contactNameList.size());
        boolean isPresent = false;

        for (AndroidElement el : contactNameList) {
            if (el.getText().equals(name + " " + lastName)) {
                isPresent = true;
                break;
            }
        }
        Assert.assertTrue(isPresent);
        return this;
    }

}
