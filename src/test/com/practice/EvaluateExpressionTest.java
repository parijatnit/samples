package com.practice;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by pbhattacharya on 4/7/17.
 */
public class EvaluateExpressionTest {


	@Test
	public void testEval() {
		String str1 = "{id} > 5000";
		String str2 = "{id} >= 5000";
		String str3 = "{id} / 5000";

		String regex = ">=|>|/";

		String[] strings1 = str1.split(regex);
		String[] strings2 = str2.split(regex);
		String[] strings3 = str3.split(regex);

		System.out.printf("done");
	}


	@Test
	public void testSolution() {
		int afterRetryCount = 5;
		int beforeRetryCount = 5;
		afterRetryCount = (afterRetryCount == beforeRetryCount) ? ++afterRetryCount : afterRetryCount;

		System.out.println("afterRetryCount: " + afterRetryCount);
	}

	private static final String SQL_ALIAS_REGEX_PATTERN = "AS\\s+%1$s(\\s* |,)";

	@Test
	public void testFindColumnNameFromAlias() {
		String rightExpression = "1066631";
		String[] values = rightExpression.split(",");
		for(String value: values) {
			System.out.println("value is: " + value);
		}
	}

	@Test
	public void testRegex() {
		String SQL_ALIAS_REGEX_PATTERN = ".*(?i)AS\\s+%1$s(\\s* |,).*";
		String columnName = "BindSource";
		String sql = "SELECT FormID, EIN1, EIN2, TFSCutoverDateFS, TFSCutoverDateIOP, TFSFormName, BindSourceTest as BindSource, BindXMLFolder, Description, Directory, DisplayName, DisplayText, EFileAgency, EffectiveDate, EFileDesc, EFilePDFfile, EFileXML, EndDate, FilingFrequency, HasOverpayChoice, HHDisplay, HHScheduleDependentPayGroup, IsArchived, IsSupportedByTFSLegacy, ItemsPerPage, JITDataHandler, Jurisdiction, JurisdictionType, FormName, PDFfile, Required, SBDisplay, SBScheduleDependentPayGroup, WhereFiled, XMLfile, TFSPDFGenerationCutoverDateFS, TFSPDFGenerationCutoverDateIOP, CategoryID, NextPageFormID, ScheduleTypeId, TypeID FROM GForms WHERE ((FormName = ?) AND ((EffectiveDate <= ?) AND (EndDate >= ?)))";
		Assert.assertTrue(sql.matches(String.format(SQL_ALIAS_REGEX_PATTERN, columnName)));
	}




}
