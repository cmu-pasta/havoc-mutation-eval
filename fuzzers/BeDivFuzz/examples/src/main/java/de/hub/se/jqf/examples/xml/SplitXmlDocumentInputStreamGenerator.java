/*
 * Copyright (c) 2017-2018 The Regents of the University of California
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package de.hub.se.jqf.examples.xml;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import edu.berkeley.cs.jqf.examples.common.Dictionary;
import edu.berkeley.cs.jqf.examples.xml.XMLDocumentUtils;
import org.w3c.dom.Document;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * A generator for XML documents. Adapted from.
 *
 * @author Hoang Lam Nguyen
 */
public class SplitXmlDocumentInputStreamGenerator extends Generator<InputStream> {

    private SplitXmlDocumentGenerator XmlDocGen;

    public SplitXmlDocumentInputStreamGenerator() {
        super(InputStream.class);
        XmlDocGen = new SplitXmlDocumentGenerator();
    }

    /**
     * Configures the string generator used by this generator to use
     * a dictionary. This is useful for overriding the default
     * arbitrary string generator with something that pulls tag names
     * from a predefined list.
     *
     * @param dict the dictionary file
     * @throws IOException if the dictionary file cannot be read
     */
    public void configure(Dictionary dict) throws IOException {
        XmlDocGen.configure(dict);
    }


    /**
     * Generators a random XML document.
     * @param random a source of pseudo-random values
     * @param status generation state
     * @return a randomly-generated XML document
     */
    @Override
    public InputStream generate(SourceOfRandomness random, GenerationStatus status) {
        String doc = XmlDocGen.generate(random, status);
        return new ByteArrayInputStream(doc.getBytes(StandardCharsets.UTF_8));

    }

}
