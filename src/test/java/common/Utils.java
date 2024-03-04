package common;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Utils {
    public static String getJsonPayload() {
        int[] lookupIds = {1, 2, 3, 5, 6, 7, 8, 9, 10};
        int[] lookupTypeId = {1, 2, 6, 7};
        JSONObject searchCriteriaObject = new JSONObject();
        searchCriteriaObject.put("lookupTypeId", getRandomNumberFromArray(lookupTypeId));
        searchCriteriaObject.put("lookupId", lookupIds);
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(searchCriteriaObject);
        JSONObject actualBodyObject = new JSONObject();
        actualBodyObject.put("searchCriteria", jsonArray);
        actualBodyObject.put("checkIn", getCheckInDate());
        actualBodyObject.put("checkOut", getNextDate(actualBodyObject.getString("checkIn"), getRandomNumber(1,10)));
        actualBodyObject.put("sortBy", "string");
        actualBodyObject.put("pageNo", 1);
        System.out.println("Payload" + actualBodyObject.toString());
        return actualBodyObject.toString();
    }

    private static String getCheckInDate() {
        Faker faker = new Faker();
        int maxDepartureDaysFromNow = getRandomNumber(30,70);
        String pattern = "yyyy-MM-dd";
        Date departureDate = faker.date().future(maxDepartureDaysFromNow, 2, TimeUnit.DAYS);
        String departureDateStr = formatDate(departureDate, pattern);
        return departureDateStr;
    }

    public static String getNextDate(String curDate, int noOfDays) {
        String nextDate = "";
        try {
            Calendar today = Calendar.getInstance();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(curDate);
            today.setTime(date);
            today.add(Calendar.DAY_OF_YEAR, noOfDays);
            nextDate = format.format(today.getTime());
        } catch (Exception e) {
            return nextDate;
        }
        return nextDate;
    }

    private static String formatDate(
            final Date date,
            final String pattern) {
        if (date == null || StringUtils.isBlank(pattern)) {
            throw new IllegalArgumentException("Date and/or pattern are missing.");
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    private static int getRandomNumberFromArray(int[] lookupTypeId) {
        Random r = new Random();
        int randomNumber = r.nextInt(lookupTypeId.length - 1);
        return lookupTypeId[randomNumber];
    }

    private static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
