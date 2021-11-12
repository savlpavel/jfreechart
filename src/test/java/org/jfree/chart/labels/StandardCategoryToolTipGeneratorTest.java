/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2021, by David Gilbert and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates. 
 * Other names may be trademarks of their respective owners.]
 *
 * -----------------------------------------
 * StandardCategoryToolTipGeneratorTest.java
 * -----------------------------------------
 * (C) Copyright 2004-2021, by David Gilbert and Contributors.
 *
 * Original Author:  David Gilbert;
 * Contributor(s):   Tracy Hiltbrand;
 *
 */

package org.jfree.chart.labels;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.jfree.chart.TestUtils;
import org.jfree.chart.util.PublicCloneable;

import org.junit.jupiter.api.Test;

/**
 * Tests for the {@link StandardCategoryToolTipGenerator} class.
 */
public class StandardCategoryToolTipGeneratorTest {

    /**
     * Use EqualsVerifier to ensure correct implementation of equals and
     * hashCode.
     */
    @Test
    public void testEqualsHashCode() {
        EqualsVerifier.forClass(StandardCategoryToolTipGenerator.class)
                .suppress(Warning.STRICT_INHERITANCE)
                .suppress(Warning.NONFINAL_FIELDS)
                .withRedefinedSuperclass()
                .verify();
    }

    /**
     * Tests the equals() method.
     */
    @Test
    public void testEquals() {

        StandardCategoryToolTipGenerator g1
                = new StandardCategoryToolTipGenerator();
        StandardCategoryToolTipGenerator g2
                = new StandardCategoryToolTipGenerator();
        assertTrue(g1.equals(g2));
        assertTrue(g2.equals(g1));

        g1 = new StandardCategoryToolTipGenerator("{0}",
                new DecimalFormat("0.000"));
        assertFalse(g1.equals(g2));
        g2 = new StandardCategoryToolTipGenerator("{0}",
                new DecimalFormat("0.000"));
        assertTrue(g1.equals(g2));

        g1 = new StandardCategoryToolTipGenerator("{1}",
                new DecimalFormat("0.000"));
        assertFalse(g1.equals(g2));
        g2 = new StandardCategoryToolTipGenerator("{1}",
                new DecimalFormat("0.000"));
        assertTrue(g1.equals(g2));

        g1 = new StandardCategoryToolTipGenerator("{2}",
                new SimpleDateFormat("d-MMM"));
        assertFalse(g1.equals(g2));
        g2 = new StandardCategoryToolTipGenerator("{2}",
                new SimpleDateFormat("d-MMM"));
        assertTrue(g1.equals(g2));

    }

    /**
     * Simple check that hashCode is implemented.
     */
    @Test
    public void testHashCode() {
        StandardCategoryToolTipGenerator g1
                = new StandardCategoryToolTipGenerator();
        StandardCategoryToolTipGenerator g2
                = new StandardCategoryToolTipGenerator();
        assertTrue(g1.equals(g2));
        assertTrue(g1.hashCode() == g2.hashCode());
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        StandardCategoryToolTipGenerator g1
                = new StandardCategoryToolTipGenerator();
        StandardCategoryToolTipGenerator g2 
                = (StandardCategoryToolTipGenerator) g1.clone();
        assertTrue(g1 != g2);
        assertTrue(g1.getClass() == g2.getClass());
        assertTrue(g1.equals(g2));
    }

    /**
     * Check to ensure that this class implements PublicCloneable.
     */
    @Test
    public void testPublicCloneable() {
        StandardCategoryToolTipGenerator g1
                = new StandardCategoryToolTipGenerator();
        assertTrue(g1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        StandardCategoryToolTipGenerator g1
                = new StandardCategoryToolTipGenerator("{2}",
                DateFormat.getInstance());
        StandardCategoryToolTipGenerator g2 = TestUtils.serialised(g1);
        assertEquals(g1, g2);
    }

    /**
     * A test for bug 1481087.
     */
    @Test
    public void testEquals1481087() {
        StandardCategoryToolTipGenerator g1
                = new StandardCategoryToolTipGenerator("{0}",
                new DecimalFormat("0.00"));
        StandardCategoryItemLabelGenerator g2
                = new StandardCategoryItemLabelGenerator("{0}",
                new DecimalFormat("0.00"));
        assertFalse(g1.equals(g2));
    }

}
