/*******************************************************************************
 * Copyright (c) 2015, 2019 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 * Sebastien Rius
 ******************************************************************************/
package org.eclipse.yasson.internal.model;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * TreeMap with a reverse ordering by default.
 *
 * @param <K> comparable key
 * @param <V> value
 */
public class ReverseTreeMap<K extends Comparable<? super K>, V> extends TreeMap<K, V> {

    /**
     * Default constructor of a TreeMap with reverse order.
     */
    public ReverseTreeMap() {
        super(Comparator.reverseOrder());
    }
}
