/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.lang.text.translate;

import java.io.IOException;
import java.io.Writer;

/**
 * Translates codepoints to their unicode escape value. 
 * @since 3.0
 */
public class UnicodeEscaper extends CodePointTranslator {

    /**
     * {@inheritDoc}
     */
    public boolean translate(int codepoint, Writer out) throws IOException {
        if (codepoint > 0xffff) {
            // TODO: Figure out what to do. Output as two unicodes?
            //       Does this make this a Java-specific output class?
            out.write("\\u" + hex(codepoint));
        } else if (codepoint > 0xfff) {
            out.write("\\u" + hex(codepoint));
        } else if (codepoint > 0xff) {
            out.write("\\u0" + hex(codepoint));
        } else if (codepoint > 0xf) {
            out.write("\\u00" + hex(codepoint));
        } else {
            out.write("\\u000" + hex(codepoint));
        }
        return true;
    }
}