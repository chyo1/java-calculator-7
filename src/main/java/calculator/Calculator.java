package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Calculator {
	private String inputStr;
	private long totalSum;
	private final char[] separator = new char[3];
	private int separatorCnt;

	public Calculator() {
		inputStr = "";
		totalSum = 0;
		separator[0] = ',';
		separator[1] = ':';
		separatorCnt = 2;
	}

	public void run() {
		enter();
		checkPersonalSeparator(inputStr);
		parsingNumsAndCalculate(inputStr);
		print();
	}

	private void enter() {
		System.out.println("덧셈할 문자열을 입력해 주세요.");
		inputStr = Console.readLine();
		Console.close();
	}

	private void print() {
		System.out.println("결과 : " + totalSum);
	}

	private void calculateNums(long num) {
		totalSum += num;
	}

	private void checkPersonalSeparator(String str) {
		int startPos = str.indexOf("//");
		int endPos = str.indexOf("\\n");

		if (startPos == -1 && endPos == -1) {
			return;
		}
		System.out.println("startPos = " + startPos);
		System.out.println("endPos = " + endPos);
		// 커스텀 구분자가 처음에 위치하지 않거나, 커스텀 구분자로 1개 이상의 문자 입력 시
		if (startPos != 0 || endPos != 3) {
			throw new IllegalArgumentException();
		}

		// 커스텀 구분자로 숫자가 들어왔을 때
		char newSeparator = str.charAt(2);
		if ('0' <= newSeparator && newSeparator <= '9') {
			throw new IllegalArgumentException();
		}

		// 커스텀 구분자 저장
		separator[2] = newSeparator;

		// 커스텀 구분자 지정 부분 문자열 제거
		inputStr = inputStr.substring(5);

		// 구분자 개수 추가
		separatorCnt++;
	}

	private long stringToLong(String strNum) {
		long num = Long.parseLong(strNum);

		if (num < Integer.MIN_VALUE || Integer.MAX_VALUE < num) {
			throw new IllegalArgumentException();
		}
		return num;
	}

	private void parsingNumsAndCalculate(String str) {

		// 처음과 끝에 구분자가 나올 때
		if (!Character.isDigit(str.charAt(0))
				|| !Character.isDigit(str.charAt(str.length() - 1))) {
			throw new IllegalArgumentException();
		}

		int startIdx = 0, endIdx = 0, strLen = str.length();

		while (endIdx < strLen) {
			while (endIdx < strLen && Character.isDigit(str.charAt(endIdx))) {
				endIdx++;
			}

			// 구분자 2개 이상
			if (startIdx == endIdx) {
				throw new IllegalArgumentException();
			}

			long num = stringToLong(str.substring(startIdx, endIdx));

			if (endIdx == strLen) {
				calculateNums(num);
				return;
			}

			// 구분자 이외의 문자 입력 시 에러
			int idx = 0;
			while (idx < separatorCnt) {
				if (str.charAt(endIdx) == separator[idx]) {
					break;
				}
				idx++;
			}
			if (idx == separatorCnt) {
				throw new IllegalArgumentException();
			}

			calculateNums(num);
			endIdx++;
			startIdx = endIdx;
		}
	}
}
