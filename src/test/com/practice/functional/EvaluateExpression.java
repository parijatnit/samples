package com.practice.functional;

/**
 * Created by pbhattacharya on 4/7/17.
 */
public class EvaluateExpression {

	// {id} > 5000 || {id} % 1000


	public static final String OR_OPERATOR = "OR";
	public static final String GREATER_THAN_OR_EQUAL_TO_OPERATOR = ">=";
	public static final String GREATER_THAN_OPERATOR = ">";
	public static final String DIVISION_OPERATOR = "/";

	public static boolean evaluateExpression(String expression, long value) {
		try {
			if (expression != null) {
				if (expression.contains(OR_OPERATOR)) {
					String[] expressions = expression.split(OR_OPERATOR);
					for (String subExpression : expressions) {
						if (evaluateSubExpression(subExpression, value)) {
							return true;
						}
					}

				}
			}
		} catch (Exception ex) {

		}
		return false;
	}


	public static boolean evaluateSubExpression(String expression, long value) {
		String[] pair = expression.split(GREATER_THAN_OR_EQUAL_TO_OPERATOR + "|" + GREATER_THAN_OPERATOR + "|" + DIVISION_OPERATOR);
		long rightExpression = Long.parseLong(pair[1]);
		if(expression.contains(GREATER_THAN_OPERATOR)) {
			return (value >= rightExpression);
		} else if(expression.contains(GREATER_THAN_OR_EQUAL_TO_OPERATOR)) {
			return (value >= rightExpression);

		} else if(expression.contains(DIVISION_OPERATOR)) {
			return (value/rightExpression == 0);
		}
		return false;
	}




}
