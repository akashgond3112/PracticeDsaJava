import java.util.*;

public class App {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
//        List<String> logs = new ArrayList<>();
//        logs.add("6");
//        logs.add("register Vikash gond123");
//        logs.add("register Vikash gond123");
//        logs.add("login Vikash gond123");
//        logs.add("login Vikash gond12");
//        logs.add("logout Vikash");
//        logs.add("logout Akash");
//
//
//        List<String> result = Result.implementAPI(logs);
//        result.forEach(System.out::println);

        List<Integer> teamA = new ArrayList<>();
        List<Integer> teamB = new ArrayList<>();

        teamA.add(2);
        teamA.add(10);
        teamA.add(5);
        teamA.add(4);
        teamA.add(8);

        teamB.add(3);
        teamB.add(1);
        teamB.add(7);
        teamB.add(8);

        Result.counts(teamA, null).forEach(System.out::println);

    }

    static class Result {

        /*
         * Complete the 'implementAPI' function below.
         *
         * The function is expected to return a STRING_ARRAY.
         * The function accepts STRING_ARRAY logs as parameter.

         */

        /*
         * Complete the 'counts' function below.
         *
         * The function is expected to return an INTEGER_ARRAY.
         * The function accepts following parameters:
         *  1. INTEGER_ARRAY teamA
         *  2. INTEGER_ARRAY teamB
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

        public static List<Integer> counts(List<Integer> teamA, List<Integer> teamB) {

            List<Integer> result = new ArrayList<>();
            int[] counts = new int[teamB.size()];

            for (int i = 0; i < teamB.size(); i++) {

                for (Integer integer : teamA) {
                    if (integer <= teamB.get(i)) {
                        counts[i] += 1;
                    }
                }
            }

            for (int count : counts) {
                result.add(count);
            }

            return result;

        }

        public static List<Integer> counts1(List<Integer> teamA, List<Integer> teamB) {
            ArrayList<Integer> res = new ArrayList<Integer>();
            Collections.sort(teamA);
            for (int i = 0; i < teamB.size(); i++) {
                int low = 0;
                int high = teamA.size() - 1;
                while (low <= high) {
                    int mid = (low + high) / 2;
                    if (teamA.get(mid) > teamB.get(i))
                        high = mid - 1;
                    else
                        low = mid + 1;
                }
                res.add(low);
            }
            return res;


        }

    }
}
