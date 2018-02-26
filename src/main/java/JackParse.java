import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;



import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;

public class JackParse {
    ClassLoader classLoader = getClass().getClassLoader();

    /*private static final DateTimeFormatter DTF_RESPONSE = DateTimeFormat
            .forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");*/

    public boolean parse() {
        File file = new File(classLoader.getResource("data.json").getFile());
        ObjectMapper mapper = new ObjectMapper();

        try {

            JsonNode node = mapper.readTree(file);
            /*System.out.println(node.get("list").get(0).get("weather").get(0).get("temp").asText());*/


            ArrayNode resultList = (ArrayNode) node.findPath("result");

            Iterator iterator = resultList.iterator();

            while (iterator.hasNext()) {

                JsonNode userNode = (JsonNode) iterator.next();

                String actionType = userNode.get("action").get("type").asText();
                System.out.println("actionType = " + actionType);

                if(actionType.equals("change_setting")) {

                    String strDate = userNode.get("when").asText();
                    /*Timestamp activeDateTimestamp = new Timestamp(
                            DTF_RESPONSE.parseDateTime(strDate).toDate().getTime());*/

                    /*System.out.println("activeDateTimestamp = " + activeDateTimestamp);*/

                }
                //System.out.println("iterator = " + iterator.get("action"));

            }

            //System.out.println("resultList = " + resultList);

            return true;

        }catch (Exception e) {
            System.out.println("e = " + e);
        }

        return true;
    }

    public boolean dateTest() {

        Date now = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        String today = df.format(now);

        LocalDate localDateToday = LocalDate.parse ( today );
        LocalDate twoYrAgo = localDateToday.plusYears(-2);

        System.out.println ( "localDate: " + localDateToday + " and twoYrAgo: " + twoYrAgo.toString() );


        return true;
    }
}
