/*
 * Copyright Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags and
 * the COPYRIGHT.txt file distributed with this work.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.teiid.infinispan.api;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.teiid.translator.document.Document;

public class InfinispanDocument extends Document {
    private TreeMap<Integer, TableWireFormat> wireMap;
    private boolean matched = true;
    private Map<String, Stats> statsMap = new HashMap<>();

    static class Stats {
        AtomicInteger matched = new AtomicInteger(0);
        AtomicInteger unmatched = new AtomicInteger(0);
    }

    public InfinispanDocument(String name, TreeMap<Integer, TableWireFormat> columnMap, InfinispanDocument parent) {
        super(name, false, parent);
        this.wireMap = columnMap;
    }

    public void addProperty(int wireType, Object value) {
        TableWireFormat twf = this.wireMap.get(wireType);
        if (twf.isArrayType()) {
            super.addArrayProperty(twf.getColumnName(), value);
        } else {
            super.addProperty(twf.getColumnName(), value);
        }
    }

    public TreeMap<Integer, TableWireFormat> getWireMap() {
        return wireMap;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    public void incrementUpdateCount(String childName, boolean matched) {
        Stats s = statsMap.get(childName);
        if (s == null) {
            s = new Stats();
            statsMap.put(childName, s);
        }

        if (matched) {
            s.matched.incrementAndGet();
        } else {
            s.unmatched.incrementAndGet();
        }
    }

    public int getUpdateCount(String childName, boolean matched) {
        Stats s = statsMap.get(childName);
        if (s == null) {
            return 0;
        }

        if (matched) {
            return s.matched.get();
        } else {
            return s.unmatched.get();
        }
    }
}
