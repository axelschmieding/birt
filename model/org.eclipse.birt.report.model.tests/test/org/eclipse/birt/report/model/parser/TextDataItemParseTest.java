/*******************************************************************************
 * Copyright (c) 2004 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.birt.report.model.parser;

import java.util.List;

import org.eclipse.birt.report.model.api.ErrorDetail;
import org.eclipse.birt.report.model.api.TextDataHandle;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.birt.report.model.api.metadata.PropertyValueException;
import org.eclipse.birt.report.model.util.BaseTestCase;

/**
 * <table border="1" cellpadding="0" cellspacing="0" style="border-collapse:
 * collapse" bordercolor="#111111" width="100%" id="AutoNumber6">
 * 
 * <tr>
 * <td width="33%"><b>Method </b></td>
 * <td width="33%"><b>Test Case </b></td>
 * <td width="34%"><b>Expected Result </b></td>
 * </tr>
 * 
 * <tr>
 * <td width="33%">{@link #testParser()}</td>
 * <td width="33%">Test all properties</td>
 * <td width="34%">the correct value returned.</td>
 * </tr>
 * 
 * <tr>
 * <td width="33%">{@link #testWriter()}</td>
 * <td width="33%">Set new value to properties and save it.</td>
 * <td width="34%">new value should be save into the output file, and output
 * file is same as golden file.</td>
 * </tr>
 * 
 * <tr>
 * <td>{@link #testSemanticError()}</td>
 * <td>Test semantic errors with the design file input.</td>
 * <td>The errors are collected, that is the value expr can not be empty.</td>
 * </tr>
 * </table>
 * 
 */

public class TextDataItemParseTest extends BaseTestCase
{

	String fileName = "TextDataItemParseTest.xml"; //$NON-NLS-1$	
	String outFileName = "TextDataItemParseTest_out.xml"; //$NON-NLS-1$
	String goldenFileName = "TextDataItemParseTest_golden.xml"; //$NON-NLS-1$
	String checkFileName = "TextDataItemParseTest_1.xml"; //$NON-NLS-1$

	/*
	 * @see BaseTestCase#setUp()
	 */
	protected void setUp( ) throws Exception
	{
		super.setUp( );
	}

	/**
	 * Test all properties.
	 * 
	 * @throws Exception
	 *             if opening design file failed.
	 */
	public void testParser( ) throws Exception
	{
		openDesign( fileName );
		TextDataHandle dataHandle = (TextDataHandle) designHandle
				.findElement( "Text Data" ); //$NON-NLS-1$
		assertNotNull( dataHandle.getElement( ) );
		assertEquals( "value expr", dataHandle.getValueExpr( ) ); //$NON-NLS-1$
		assertEquals( DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_AUTO,
				dataHandle.getContentType( ) );
	}

	/**
	 * This test sets properties, writes the design file and compares it with
	 * golden file.
	 * 
	 * @throws Exception
	 *             if opening or saving design file failed.
	 */
	public void testWriter( ) throws Exception
	{
		openDesign( fileName );

		TextDataHandle dataHandle = (TextDataHandle) designHandle
				.findElement( "Text Data" ); //$NON-NLS-1$
		assertNotNull( dataHandle.getElement( ) );
		dataHandle.setValueExpr( "new value expr" ); //$NON-NLS-1$
		dataHandle
				.setContentType( DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_RTF );

		saveAs( outFileName );
		assertTrue( compareTextFile( goldenFileName, outFileName ) );

	}

	/**
	 * Checks the semantic error of ReportDesign.
	 * 
	 * @throws Exception
	 */

	public void testSemanticError( ) throws Exception
	{
		openDesign( checkFileName );

		List errors = design.getErrorList( );

		printSemanticErrors( );
		assertEquals( 1, errors.size( ) );

		int i = 0;

		assertEquals( PropertyValueException.DESIGN_EXCEPTION_VALUE_REQUIRED,
				( (ErrorDetail) errors.get( i++ ) ).getErrorCode( ) );
	}

}
