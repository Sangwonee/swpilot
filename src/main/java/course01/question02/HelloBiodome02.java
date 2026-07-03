package course01.question02;

public class HelloBiodome02 {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("세 가지 에너지 생산량을 입력 받아주세요.");
            return;
        }

        int solar;
        int wind;
        int geothermal;

        try {
            solar = Integer.parseInt(args[0]);
            wind = Integer.parseInt(args[1]);
            geothermal = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("에너지 생산량은 정수여야 합니다.");
            return;
        }

        if (solar < 0 || wind < 0 || geothermal < 0) {
            System.out.println("에너지 생산량은 음수일 수 없습니다.");
            return;
        }
        if (solar > 30000 || wind > 30000 || geothermal > 30000) {
            System.out.println("에너지 생산량은 30,000을 넘을 수 없습니다.");
            return;
        }

        int total = solar + wind + geothermal;
        System.out.println("총 에너지 사용량은 " + total + "입니다.");

        if (total == 0) {
            System.out.println("태양광 0.0%, 풍력 0.0%, 지열 0.0%");
            return;
        }

        double solarPercentage = solar / (double) total * 100;
        double windPercentage = wind / (double) total * 100;
        double geothermalPercentage = geothermal / (double) total * 100;
        
        System.out.println("태양광 " + solarPercentage + "%, 풍력 " + windPercentage + "%, 지열 " + geothermalPercentage + "%");
        
    }
}
