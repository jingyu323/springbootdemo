public class StringTest {

    public static void main(String[] args) {

        String ssss = "'JWds!#%832'|sssd|llll";
        String[] ss = ssss.split("\\|");

        for (String st : ss) {

            System.out.println(st);
        }


    }
}
