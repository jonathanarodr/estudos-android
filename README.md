# estudos-android

```java
@RunWith(AndroidJUnit.class)
@LargeTest
public class LoginActivityTest {
    @Rule
    ActivityTestRule(LoginActivity) rule = new Activity<>(LoginActivity.class);
    @Test
    public void loginSuccess throws Exception() {
        onView(withId(R.id.login_txt_user)).perform(typeText(username), closeSoftKeyboard());
        onView(withId(R.id.login_txt_pass)).perform(typeText(password), closeSoftKeyboard());
        onView(withText(R.string.login_str_signIn)).perform(click());
        Thread.sleep(3000);
        onView(withTest(email)).check(matches(isDisplayed));
    }
}
```
