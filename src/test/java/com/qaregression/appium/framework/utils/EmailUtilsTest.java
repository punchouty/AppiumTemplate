package com.qaregression.appium.framework.utils;

import org.testng.Assert;
import org.testng.annotations.Test;

import javax.mail.MessagingException;

public class EmailUtilsTest {

    @Test
    public void testEmailConnection() throws MessagingException {
        EmailUtils emailUtils = new EmailUtils();
        int messageCount = emailUtils.getNumberOfMessages();
        Assert.assertTrue(messageCount > 0);
    }
}
