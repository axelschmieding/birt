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

package org.eclipse.birt.report.model.metadata;

import java.math.BigDecimal;
import com.ibm.icu.util.ULocale;

import org.eclipse.birt.report.model.api.metadata.PropertyValueException;
import org.eclipse.birt.report.model.i18n.ThreadResources;

/**
 * Test case for BooleanPropertyType.
 * <p>
 * In this setUp method , prepare test datas and expected datas for supporting
 * testGetterSetters , testBuild , testValidateValue method in
 * PropertyTypeTestCase class
 *  
 */
public class BooleanPropertyTypeTest extends PropertyTypeTestCase
{

	BooleanPropertyType type = new BooleanPropertyType( );

	PropertyDefn propDefn = new PropertyDefnFake( );

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.report.model.metadata.PropertyTypeTestCase#testGetTypeCode()
	 */
	public void testGetTypeCode( )
	{
		assertEquals( PropertyType.BOOLEAN_TYPE, type.getTypeCode( ) );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.report.model.metadata.PropertyTypeTestCase#testGetName()
	 */
	public void testGetName( )
	{
		assertEquals( PropertyType.BOOLEAN_TYPE_NAME, type.getName( ) );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.report.model.metadata.PropertyTypeTestCase#testValidateValue()
	 */
	public void testValidateValue( ) throws PropertyValueException
	{
		assertEquals( null, type.validateValue( design, propDefn, null ) );

		// String
		assertEquals( null, type.validateValue( design, propDefn, " " ) ); //$NON-NLS-1$
		assertEquals( Boolean.TRUE, type.validateValue( design, propDefn,
				"true" ) ); //$NON-NLS-1$
		assertEquals( Boolean.FALSE, type.validateValue( design, propDefn,
				"false" ) ); //$NON-NLS-1$

		ThreadResources.setLocale( TEST_LOCALE );
		assertEquals( Boolean.TRUE, type.validateValue( design, propDefn,
				"\u771f" ) ); //$NON-NLS-1$
		assertEquals( Boolean.FALSE, type.validateValue( design, propDefn,
				"\u5047" ) ); //$NON-NLS-1$

		// Boolean
		assertEquals( Boolean.TRUE, type.validateValue( design, propDefn,
				Boolean.TRUE ) ); //$NON-NLS-1$
		assertEquals( Boolean.FALSE, type.validateValue( design, propDefn,
				Boolean.FALSE ) ); //$NON-NLS-1$

		// Integer
		assertEquals( Boolean.TRUE, type.validateValue( design, propDefn,
				new Integer( 1 ) ) ); //$NON-NLS-1$
		assertEquals( Boolean.TRUE, type.validateValue( design, propDefn,
				new Integer( 2 ) ) ); //$NON-NLS-1$
		assertEquals( Boolean.FALSE, type.validateValue( design, propDefn,
				new Integer( 0 ) ) ); //$NON-NLS-1$

		// Double
		assertEquals( Boolean.TRUE, type.validateValue( design, propDefn,
				new Double( 1.0d ) ) ); //$NON-NLS-1$
		assertEquals( Boolean.TRUE, type.validateValue( design, propDefn,
				new Double( 2.0d ) ) ); //$NON-NLS-1$
		assertEquals( Boolean.FALSE, type.validateValue( design, propDefn,
				new Double( 0.0d ) ) ); //$NON-NLS-1$

		// BigDecimal
		assertEquals( Boolean.TRUE, type.validateValue( design, propDefn,
				new BigDecimal( 1.0d ) ) ); //$NON-NLS-1$
		assertEquals( Boolean.TRUE, type.validateValue( design, propDefn,
				new BigDecimal( 2.0d ) ) ); //$NON-NLS-1$
		assertEquals( Boolean.FALSE, type.validateValue( design, propDefn,
				new BigDecimal( 0.0d ) ) ); //$NON-NLS-1$

		// Wrong type
		try
		{
			type.validateValue( design, propDefn, new Object( ) );
			fail( );
		}
		catch ( PropertyValueException e )
		{
			assertEquals( PropertyValueException.DESIGN_EXCEPTION_INVALID_VALUE, e
					.getErrorCode( ) );
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.report.model.metadata.PropertyTypeTestCase#testValidateInputString()
	 */
	public void testValidateInputString( ) throws PropertyValueException
	{
		assertEquals( null, type.validateInputString( design, propDefn, null ) );

		assertEquals( null, type.validateInputString( design, propDefn, " " ) ); //$NON-NLS-1$
		assertEquals( Boolean.TRUE, type.validateInputString( design, propDefn,
				"true" ) ); //$NON-NLS-1$
		assertEquals( Boolean.FALSE, type.validateInputString( design,
				propDefn, "false" ) ); //$NON-NLS-1$

		ThreadResources.setLocale( TEST_LOCALE );
		assertEquals( Boolean.TRUE, type.validateInputString( design, propDefn,
				"\u771f" ) ); //$NON-NLS-1$
		assertEquals( Boolean.FALSE, type.validateInputString( design,
				propDefn, "\u5047" ) ); //$NON-NLS-1$

		// Wrong type
		try
		{
			type.validateInputString( design, propDefn, "wrong-type-value" ); //$NON-NLS-1$
			fail( );
		}
		catch ( PropertyValueException e )
		{
			assertEquals( PropertyValueException.DESIGN_EXCEPTION_INVALID_VALUE, e
					.getErrorCode( ) );
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.report.model.metadata.PropertyTypeTestCase#testValidateXml()
	 */
	public void testValidateXml( ) throws PropertyValueException
	{
		assertEquals( null, type.validateXml( design, propDefn, null ) );
		assertEquals( null, type.validateXml( design, propDefn, " " ) ); //$NON-NLS-1$
		assertEquals( Boolean.TRUE, type.validateXml( design, propDefn, "true" ) ); //$NON-NLS-1$
		assertEquals( Boolean.FALSE, type.validateXml( design, propDefn,
				"false" ) ); //$NON-NLS-1$

		try
		{
			type.validateXml( design, propDefn, "wrong-xml-value" ); //$NON-NLS-1$
			fail( );
		}
		catch ( PropertyValueException e )
		{
			assertEquals( PropertyValueException.DESIGN_EXCEPTION_INVALID_VALUE, e
					.getErrorCode( ) );
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.report.model.metadata.PropertyTypeTestCase#testToDouble()
	 */
	public void testToDouble( )
	{
		assertEquals( 0.0d, type.toDouble( design, null ), 1 );
		assertEquals( 1.0d, type.toDouble( design, Boolean.TRUE ), 1 );
		assertEquals( 0.0d, type.toDouble( design, Boolean.FALSE ), 1 );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.report.model.metadata.PropertyTypeTestCase#testToInteger()
	 */
	public void testToInteger( )
	{
		assertEquals( 0, type.toInteger( design, null ) );
		assertEquals( 1, type.toInteger( design, Boolean.TRUE ) );
		assertEquals( 0, type.toInteger( design, Boolean.FALSE ) );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.report.model.metadata.PropertyTypeTestCase#testToXml()
	 */
	public void testToXml( )
	{
		assertEquals( "true", type.toXml( design, propDefn, Boolean.TRUE ) ); //$NON-NLS-1$
		assertEquals( "false", type.toXml( design, propDefn, Boolean.FALSE ) ); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.report.model.metadata.PropertyTypeTestCase#testToString()
	 */
	public void testToString( )
	{
		assertEquals( null, type.toString( design, propDefn, null ) );

		assertEquals( "true", type.toString( design, propDefn, Boolean.TRUE ) ); //$NON-NLS-1$
		assertEquals( "false", type.toString( design, propDefn, Boolean.FALSE ) ); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.report.model.metadata.PropertyTypeTestCase#testToDisplayString()
	 */
	public void testToDisplayString( )
	{
		assertEquals( null, type.toDisplayString( design, propDefn, null ) ); //$NON-NLS-1$

		ThreadResources.setLocale( ULocale.ENGLISH );
		assertEquals(
				"true", type.toDisplayString( design, propDefn, Boolean.TRUE ) ); //$NON-NLS-1$

		ThreadResources.setLocale( TEST_LOCALE );
		assertEquals(
				"\u771f", type.toDisplayString( design, propDefn, Boolean.TRUE ) ); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.report.model.metadata.PropertyTypeTestCase#testToNumber()
	 */
	public void testToNumber( )
	{
		assertEquals( 0.0d, type.toNumber( design, null ).doubleValue( ), 1 );
		assertEquals( 1.0d,
				type.toNumber( design, Boolean.TRUE ).doubleValue( ), 1 );
		assertEquals( 0.0d, type.toNumber( design, Boolean.FALSE )
				.doubleValue( ), 1 );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.report.model.metadata.PropertyTypeTestCase#testToBoolean()
	 */
	public void testToBoolean( )
	{
		assertEquals( false, type.toBoolean( design, null ) );
		assertEquals( true, type.toBoolean( design, Boolean.TRUE ) );
		assertEquals( false, type.toBoolean( design, Boolean.FALSE ) );
	}

}