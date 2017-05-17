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

package org.teiid.translator.file;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;

import org.junit.Test;
import org.mockito.Mockito;
import org.teiid.core.util.UnitTestUtil;
import org.teiid.language.Argument;
import org.teiid.language.Argument.Direction;
import org.teiid.language.Call;
import org.teiid.language.Literal;
import org.teiid.translator.FileConnection;
import org.teiid.translator.ProcedureExecution;
import org.teiid.translator.TypeFacility;

@SuppressWarnings("nls")
public class TestFileExecutionFactory {

	@Test public void testGetTextFiles() throws Exception {
		FileExecutionFactory fef = new FileExecutionFactory();
		FileConnection fc = Mockito.mock(FileConnection.class);
		Mockito.stub(fc.getFile("*.txt")).toReturn(new File(UnitTestUtil.getTestDataPath(), "*.txt"));
		Call call = fef.getLanguageFactory().createCall("getTextFiles", Arrays.asList(new Argument(Direction.IN, new Literal("*.txt", TypeFacility.RUNTIME_TYPES.STRING), TypeFacility.RUNTIME_TYPES.STRING, null)), null);
		ProcedureExecution pe = fef.createProcedureExecution(call, null, null, fc);
		pe.execute();
		int count = 0;
		while (true) {
			if (pe.next() == null) {
				break;
			}
			count++;
		}
		assertEquals(2, count);
		
		
		call = fef.getLanguageFactory().createCall("getTextFiles", Arrays.asList(new Argument(Direction.IN, new Literal("*1*", TypeFacility.RUNTIME_TYPES.STRING), TypeFacility.RUNTIME_TYPES.STRING, null)), null);
		pe = fef.createProcedureExecution(call, null, null, fc);
		Mockito.stub(fc.getFile("*1*")).toReturn(new File(UnitTestUtil.getTestDataPath(), "*1*"));
		pe.execute();
		count = 0;
		while (true) {
			if (pe.next() == null) {
				break;
			}
			count++;
		}
		assertEquals(1, count);
	}
	
}
