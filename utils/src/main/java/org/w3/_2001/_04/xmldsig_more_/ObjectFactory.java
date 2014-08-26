/*
 * Copyright 2011 by Graz University of Technology, Austria
 * MOCCA has been developed by the E-Government Innovation Center EGIZ, a joint
 * initiative of the Federal Chancellery Austria and Graz University of Technology.
 *
 * Licensed under the EUPL, Version 1.1 or - as soon they will be approved by
 * the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * http://www.osor.eu/eupl/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 *
 * This product combines work with different licenses. See the "NOTICE" text
 * file for details on the various modules and licenses.
 * The "NOTICE" text file is part of the distribution. Any derivative works
 * that you distribute must include a readable copy of the "NOTICE" text file.
 */


//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-520 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.07.21 at 09:30:44 AM GMT 
//


package org.w3._2001._04.xmldsig_more_;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.w3._2001._04.xmldsig_more_ package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ECDSAKeyValue_QNAME = new QName("http://www.w3.org/2001/04/xmldsig-more#", "ECDSAKeyValue");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.w3._2001._04.xmldsig_more_
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OddCharExtensionFieldParamsType }
     * 
     */
    public OddCharExtensionFieldParamsType createOddCharExtensionFieldParamsType() {
        return new OddCharExtensionFieldParamsType();
    }

    /**
     * Create an instance of {@link DomainParamsType }
     * 
     */
    public DomainParamsType createDomainParamsType() {
        return new DomainParamsType();
    }

    /**
     * Create an instance of {@link ExplicitParamsType }
     * 
     */
    public ExplicitParamsType createExplicitParamsType() {
        return new ExplicitParamsType();
    }

    /**
     * Create an instance of {@link PrimeFieldElemType }
     * 
     */
    public PrimeFieldElemType createPrimeFieldElemType() {
        return new PrimeFieldElemType();
    }

    /**
     * Create an instance of {@link CharTwoFieldElemType }
     * 
     */
    public CharTwoFieldElemType createCharTwoFieldElemType() {
        return new CharTwoFieldElemType();
    }

    /**
     * Create an instance of {@link CurveParamsType }
     * 
     */
    public CurveParamsType createCurveParamsType() {
        return new CurveParamsType();
    }

    /**
     * Create an instance of {@link DomainParamsType.NamedCurve }
     * 
     */
    public DomainParamsType.NamedCurve createDomainParamsTypeNamedCurve() {
        return new DomainParamsType.NamedCurve();
    }

    /**
     * Create an instance of {@link ECPointType }
     * 
     */
    public ECPointType createECPointType() {
        return new ECPointType();
    }

    /**
     * Create an instance of {@link PrimeFieldParamsType }
     * 
     */
    public PrimeFieldParamsType createPrimeFieldParamsType() {
        return new PrimeFieldParamsType();
    }

    /**
     * Create an instance of {@link TnBFieldParamsType }
     * 
     */
    public TnBFieldParamsType createTnBFieldParamsType() {
        return new TnBFieldParamsType();
    }

    /**
     * Create an instance of {@link ECDSAKeyValueType }
     * 
     */
    public ECDSAKeyValueType createECDSAKeyValueType() {
        return new ECDSAKeyValueType();
    }

    /**
     * Create an instance of {@link OddCharExtensionFieldElemType }
     * 
     */
    public OddCharExtensionFieldElemType createOddCharExtensionFieldElemType() {
        return new OddCharExtensionFieldElemType();
    }

    /**
     * Create an instance of {@link PnBFieldParamsType }
     * 
     */
    public PnBFieldParamsType createPnBFieldParamsType() {
        return new PnBFieldParamsType();
    }

    /**
     * Create an instance of {@link BasePointParamsType }
     * 
     */
    public BasePointParamsType createBasePointParamsType() {
        return new BasePointParamsType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ECDSAKeyValueType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2001/04/xmldsig-more#", name = "ECDSAKeyValue")
    public JAXBElement<ECDSAKeyValueType> createECDSAKeyValue(ECDSAKeyValueType value) {
        return new JAXBElement<ECDSAKeyValueType>(_ECDSAKeyValue_QNAME, ECDSAKeyValueType.class, null, value);
    }

}