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
		// ������ �о� �ɴϴ�. java�� ���� p917
		File csvFile = new File("c:\\test\\TestData.csv");
		// �Է� ��Ʈ�����κ��� ���ڸ� �д´�.
		BufferedReader br = new BufferedReader(new FileReader(csvFile));

		// �����͸� ���� list�� �����Ѵ�.
		List<List<String>> list = new ArrayList<>();

		String StrLine = null;

		// �ѹ��о ù��°�� ����--->Ȯ�� ���ڸ� ���� �Ͱ� ���� ������� title�� �о��ش�.
		// br.readLine();
		StrLine = br.readLine();

		// ���ڿ� ���پ� �о� �´�.

		String line = null;
		boolean bFirstLine = true;

		while ((line = br.readLine()) != null) {

			String[] lineArr = line.split(",");
			// list2�� �ڸ��� �ϳ��� �ֱ�
			// bolean fist Line []�� ä���ش�.
			// IndexOutOfBoundsException �ذ�--->list���� ���� ���ܳ��� ���� (listũ�Ⱑ �ȸ�����)
			if (bFirstLine) {
				for (int i = 0; i < lineArr.length; i++)
					// ������� list�� �־��ش�.
					list.add(new ArrayList<String>());
				bFirstLine = false;
			}

			for (int i = 0; i < lineArr.length; i++) {
				// �࿡�� �ڸ� ���� �־��ش�.
				list.get(i).add(lineArr[i]);
			}

		}
		// �� �÷��� ��µȴ�.

		br.close();

		// ���θ��� ������ ����� �ش�.
		// ���� ������� ������ ��� ����
		String fileName = "C:\\test\\NewFile.csv";

		try {
			// ���� ��ü ����
			File file = new File(fileName);
			BufferedWriter bfWriter = new BufferedWriter(new FileWriter(file));

			if (bfWriter != null)
				;

			// ����� ���� lsit
			ArrayList<String> maxlist = new ArrayList<String>();
			ArrayList<String> minlist = new ArrayList<String>();
			ArrayList<String> avglist = new ArrayList<String>();

			// title���� ���
			bfWriter.write(StrLine);
			bfWriter.write("\r\n");

			// System.out.println(list);
			// 1�� 2�� 3��
			for (int i = 0; i < list.size(); i++) {

				// �÷��� ��Ҹ� list�� ����ش�.
				List<String> valueList = list.get(i);
				// double Ÿ���� �ּڰ� 4.9E-324
				// Double max = Double.MIN_VALUE;
				Double max = 0.0;
				// double Ÿ���� �ִ밪 (1.7976931348623157E308)
				Double min = Double.MAX_VALUE;
				Double sum = 0.0;
				Double avg = 0.0;
				// �� ó��
				for (String val : valueList) {
					// �����̳� ���ڰ� �ƴϸ� (�Ѵ� true)
					if (val.isEmpty() && !isStringDouble(val))
						continue;
					// �÷��� ��Ҹ� �޾Ƽ� ������ش�.
					Double num = Double.parseDouble(val);

					// �ִ밪
					if (max < num)
						max = num;
					// �ּҰ�
					if (min > num)
						min = num;

					sum += num;

				}

				// max��
				String maxSplit = max.toString();
				maxlist.add(maxSplit);

				// min��
				String minSplit = min.toString();
				minlist.add(minSplit);

				// avg��
				avg = sum / valueList.size();
				String avgSplit = avg.toString();
				avglist.add(avgSplit);

			} // for�� ��
				// ������ ����Ʈ����Ƽ� ����.

			// max-->���

			for (int c = 0; c < maxlist.size(); c++) {

				bfWriter.write(maxlist.get(c) + ",");
			}

			// �����ٷ� �ѱ��
			bfWriter.write("\r\n");

			// min-->���
			for (int d = 0; d < minlist.size(); d++) {

				bfWriter.write(minlist.get(d) + ",");
			}

			// �����ٷ� �ѱ��
			bfWriter.write("\r\n");

			// avg-->���
			for (int e = 0; e < avglist.size(); e++) {

				bfWriter.write(avglist.get(e) + ",");

			}

			bfWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ���� value���� �����̸� true return
	private static boolean isStringDouble(String value) {
		try {
			// empty String �߻�
			Double.parseDouble(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}