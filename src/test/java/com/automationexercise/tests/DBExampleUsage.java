package com.automationexercise.tests;
import com.automationexercise.utils.DBUtils;
import com.automationexercise.utils.Util;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Epic("Regression Tests")
@Feature("User")
public class DBExampleUsage extends TestBasic {

    @Test(description = "Test Case DBExampleUsage: Verify username and password from database")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Verify username and password from database")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify username and password from database""")
    public static void getUserDetails() {
        // Example 1: select a single user by username
        String sqlSingle = "SELECT * FROM users WHERE username = ?";
        List<Map<String, Object>> result = DBUtils.executeSelect(sqlSingle, "alice");

        if (result.isEmpty()) {
            System.out.println("No user found for username=alice ");
        } else {
            Map<String, Object> row = result.get(0);
            String username = Objects.toString(row.get("username"), null);
            String password = Objects.toString(row.get("password"), null);

            System.out.printf("username=%s, password=%s%n", username, password);

//            Assert.assertEquals(errorLoginText, "Your email or password is incorrect!", "Verify error 'Your email or password is incorrect!' is visible");

        }

//        // Example 2: select multiple rows (active users)
//        String sqlMany = "SELECT id, username FROM users WHERE active = ?";
//        List<Map<String, Object>> rows = DBUtils.executeSelect(sqlMany, true);
//
//        for (Map<String, Object> r : rows) {
//            Object idVal = r.get("id");
//            Object usernameVal = r.get("username");
//            System.out.printf("id=%s, username=%s%n", idVal, usernameVal);
//        }


    }
}


