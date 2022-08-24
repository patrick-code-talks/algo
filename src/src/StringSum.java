public class StringSum {
    public static void main(String[] args) {
        StringSum s = new StringSum();

        String s1 = "1", s2 = "2", s3 = "102";
        boolean result = s.solution(s1, s2, s3);
        System.out.println("result = " + result);
    }

    public boolean solution(String first, String second, String third) {
        if (first == null || second == null || third == null) {
            return false;
        }

        if (first.length() < 1) {
            return false;
        }

        // 1' + 2 = 3 <==> 1' = 3 - 2
        String candidate = getCandidate(third, second);

        return isValidCandidate(first, candidate);
    }

    private String getCandidate(String s1, String s2) {
        return Integer.toString(Integer.valueOf(s1) - Integer.valueOf(s2));
    }

    private boolean isValidCandidate(String first, String candidate) {
        if (first.equals(candidate)) {
            return true;
        }

        for (int i = 0; i < first.length(); i++) {
            String modified = first.substring(0, i) + first.substring(i);
            if (modified.startsWith("0")) {
                continue;
            }

            if (modified.equals(candidate)) {
                return true;
            }
        }

        return false;
    }
}
