package course05.question03;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

public class BiodomeForever03 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("연구일지 폴더 경로를 입력해주세요.");
            return;
        }

        try {
            ResearchJournalExtractor extractor = new ResearchJournalExtractor(args[0]);
            Path outputFile = extractor.savePlantAddresses();

            System.out.printf("파일 저장을 완료했습니다: %s%n", outputFile.getFileName());
            System.out.printf("저장된 식물 정보: %d건%n", extractor.getSavedPlantCount());
        } catch (FileNotFoundException e) {
            System.out.println("연구일지 폴더를 찾을 수 없습니다. 경로를 다시 확인해주세요.");
        } catch (NoDataAvailableException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("연구일지 파일을 읽거나 저장하는 중 오류가 발생했습니다.");
        }
    }
}
