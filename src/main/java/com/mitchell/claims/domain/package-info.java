@XmlSchema(
        namespace = "http://www.mitchell.com/examples/claim",
        xmlns = @XmlNs(prefix = "cla", namespaceURI = "http://www.mitchell.com/examples/claim"),
        elementFormDefault = XmlNsForm.QUALIFIED
        )
package com.mitchell.claims.domain;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;