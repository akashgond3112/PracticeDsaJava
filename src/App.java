import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {

    class Result {

        /*
         * Complete the 'implementAPI' function below.
         *
         * The function is expected to return a STRING_ARRAY.
         * The function accepts STRING_ARRAY logs as parameter.

         */

        public static List<String> implementAPI(List<String> logs) {


            // Write your code here
            List<String> result = new ArrayList<>();

            if (logs.size() <= 0) {
                return result;
            }

            Map<String, String> userData = new HashMap<>();
            Map<String, Boolean> userLogin = new HashMap<>();


            int noOfLogs = Integer.parseInt(logs.get(0));

            for (int i = 1; i <= noOfLogs; i++) {
                String[] data = logs.get(i).split(" ");

                if (data[0].contains("register")) {

                    if (userData.get(data[1]) == null) {
                        userData.put(data[1], data[2]);
                        result.add("Registered Successfully");
                    } else {
                        result.add("Username already exists");
                    }

                } else if (data[0].contains("login") && userData.get(data[1]) != null) {


                    if (userData.get(data[1]).equals(data[2])) {
                        userLogin.put(data[1], true);
                        result.add("Logged In Successfully");
                    } else {
                        result.add("Login Unsuccessful");
                    }

                } else if (data[0].contains("logout")) {

                    if (userLogin.get(data[1]) != null && userLogin.get(data[1])) {
                        result.add("logout " + data[1]);
                    } else {
                        result.add("Logout Unsuccessful");
                    }
                }
            }

            return result;
        }

    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        List<String> logs = new ArrayList<>();
        logs.add("6");
        logs.add("register Vikash gond123");
        logs.add("register Vikash gond123");
        logs.add("login Vikash gond123");
        logs.add("login Vikash gond12");
        logs.add("logout Vikash");
        logs.add("logout Akash");


        List<String> result = Result.implementAPI(logs);
        result.forEach(System.out::println);
    }
}
