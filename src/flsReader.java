import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class flsReader {
	public static void main(String[] args) throws IOException {
		// 파일을 읽어 옵니다. java의 정석 p917
		File csvFile = new File("c:\\test\\TestData.csv");
		// 입력 스트림으로부터 문자를 읽는다.
		BufferedReader br = new BufferedReader(new FileReader(csvFile));

		// 데이터를 담을 list를 생성한다.
		List<List<String>> list = new ArrayList<>();

		String StrLine = null;

		// 한번읽어서 첫번째줄 무시--->확장 숫자를 읽은 것과 같은 방식으로 title값 읽어준다.
		// br.readLine();
		StrLine = br.readLine();

		// 문자열 한줄씩 읽어 온다.

		String line = null;
		boolean bFirstLine = true;

		while ((line = br.readLine()) != null) {

			String[] lineArr = line.split(",");
			// list2에 자른값 하나씩 넣기
			// bolean fist Line []로 채워준다.
			// IndexOutOfBoundsException 해결--->list에서 자주 생겨나는 오류 (list크기가 안맞을때)
			if (bFirstLine) {
				for (int i = 0; i < lineArr.length; i++)
					// 빈공간에 list를 넣어준다.
					list.add(new ArrayList<String>());
				bFirstLine = false;
			}

			for (int i = 0; i < lineArr.length; i++) {
				// 행에서 자른 값을 넣어준다.
				list.get(i).add(lineArr[i]);
			}

		}
		// 한 컬럼이 출력된다.

		br.close();

		// 새로만들 파일을 만들어 준다.
		// 새로 만들어진 파일의 경로 지정
		String fileName = "C:\\test\\NewFile.csv";

		try {
			// 파일 객체 생성
			File file = new File(fileName);
			BufferedWriter bfWriter = new BufferedWriter(new FileWriter(file));

			if (bfWriter != null)
				;

			// 출력을 위한 lsit
			ArrayList<String> maxlist = new ArrayList<String>();
			ArrayList<String> minlist = new ArrayList<String>();
			ArrayList<String> avglist = new ArrayList<String>();

			// title값을 출력
			bfWriter.write(StrLine);
			bfWriter.write("\r\n");

			// System.out.println(list);
			// 1열 2열 3열
			for (int i = 0; i < list.size(); i++) {

				// 컬럼의 요소를 list에 담아준다.
				List<String> valueList = list.get(i);
				// double 타입의 최솟값 4.9E-324
				// Double max = Double.MIN_VALUE;
				Double max = 0.0;
				// double 타입의 최대값 (1.7976931348623157E308)
				Double min = Double.MAX_VALUE;
				Double sum = 0.0;
				Double avg = 0.0;
				// 열 처리
				for (String val : valueList) {
					// 공백이나 숫자가 아니면 (둘다 true)
					if (val.isEmpty() && !isStringDouble(val))
						continue;
					// 컬럼의 요소를 받아서 계산해준다.
					Double num = Double.parseDouble(val);

					// 최대값
					if (max < num)
						max = num;
					// 최소값
					if (min > num)
						min = num;

					sum += num;

				}

				// max행
				String maxSplit = max.toString();
				maxlist.add(maxSplit);

				// min행
				String minSplit = min.toString();
				minlist.add(minSplit);

				// avg행
				avg = sum / valueList.size();
				String avgSplit = avg.toString();
				avglist.add(avgSplit);

			} // for문 끝
				// 파일을 리스트에담아서 쓴다.

			// max-->출력

			for (int c = 0; c < maxlist.size(); c++) {

				bfWriter.write(maxlist.get(c) + ",");
			}

			// 다음줄로 넘기기
			bfWriter.write("\r\n");

			// min-->출력
			for (int d = 0; d < minlist.size(); d++) {

				bfWriter.write(minlist.get(d) + ",");
			}

			// 다음줄로 넘기기
			bfWriter.write("\r\n");

			// avg-->출력
			for (int e = 0; e < avglist.size(); e++) {

				bfWriter.write(avglist.get(e) + ",");

			}

			bfWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 받은 value값이 문자이면 true return
	private static boolean isStringDouble(String value) {
		try {
			// empty String 발생
			Double.parseDouble(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}