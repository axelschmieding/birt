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

package org.eclipse.birt.report.model.extension;

import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.i18n.ThreadResources;
import org.eclipse.birt.report.model.metadata.PeerExtensionLoader;
import org.eclipse.birt.report.model.util.BaseTestCase;

import com.ibm.icu.util.ULocale;

/**
 * Tests the extension pointer of org.eclipse.birt.report.model.reportItem.
 */

public class CompatibleReportItemExtensionTest extends BaseTestCase
{

	private String fileName = "CompatibleExtensionTest.xml"; //$NON-NLS-1$
	private String outFileName = "CompatibleExtensionTest_out.xml"; //$NON-NLS-1$
	private String goldenFileName = "CompatibleExtensionTest_golden.xml"; //$NON-NLS-1$

	private String fileName_1 = "CompatibleExtensionTest_1.xml"; //$NON-NLS-1$
	
	private String outFileName_1 = "CompatibleExtensionTest_out_1.xml"; //$NON-NLS-1$
	private String goldenFileName_1 = "CompatibleExtensionTest_golden_1.xml"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */

	protected void setUp( ) throws Exception
	{
		super.setUp( );
		new PeerExtensionLoader( ).load( );
		ThreadResources.setLocale( ULocale.ENGLISH );
	}

	/**
	 * Tests the backward compatibility for the extended item that in the design
	 * file of which the version is smaller than "3.1.0".
	 * 
	 * @throws Exception
	 * 
	 */

	public void testCompatibleBoundDataColumns( ) throws Exception
	{
		openDesignAsResource( CompatibleReportItemExtensionTest.class, fileName );

		saveAsInTempDir( outFileName );
		assertTrue( compareTextFileAsResource( ReportItemExtensionTest.class,
				goldenFileName, outFileName ) );
	}

	/**
	 * Tests to remove unused columns for extended item like matrix.
	 * 
	 * @throws Exception
	 * 
	 */

	public void testRemoveBoundDataColumns( ) throws Exception
	{
		openDesignAsResource( CompatibleReportItemExtensionTest.class, fileName_1 );

		ExtendedItemHandle extendedItem = (ExtendedItemHandle) designHandle
				.findElement( "right extended item" ); //$NON-NLS-1$

		extendedItem.removedUnusedColumnBindings( );

		saveAsInTempDir( outFileName_1 );
		assertTrue( compareTextFileAsResource( ReportItemExtensionTest.class,
				goldenFileName_1, outFileName_1 ) );
	}
}